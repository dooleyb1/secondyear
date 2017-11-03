/*!
 * @file cs2014coin-make.c
 * @brief This is the implementation of the cs2014 coin maker
 *
 * It should go without saying that these coins are for play:-)
 *
 * This is part of CS2014
 *    https://down.dsg.cs.tcd.ie/cs2014/examples/c-progs-2/README.html
 */

/*
 * Copyright (c) 2017 stephen.farrell@cs.tcd.ie
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */
#include <stdio.h>
#include <string.h>
#include <unistd.h>

#include <netinet/in.h>
#include <arpa/inet.h>

#include "cs2014coin.h"
#include "cs2014coin-int.h"

#if defined(MBEDTLS_PLATFORM_C)
#include "mbedtls/platform.h"
#else
#include <stdio.h>
#define mbedtls_//printf     //printf
#endif

#include "mbedtls/entropy.h"
#include "mbedtls/ctr_drbg.h"
#include "mbedtls/ecdsa.h"
#include "mbedtls/sha256.h"
#include "mbedtls/ecp.h"
#include "mbedtls/pk.h"

#define STANDARDLENGTH 4
#define KEYLENGTH 158
#define NONCELENGTH 32
#define HASHLENGTH 32
#define SIGLENGTH CC_BUFSIZ
#define CIPHERSUITEVAL 0
#define POWBUFLENGTH 242
#define MAX_ITERATIONS 1000000

/*!
	 * @brief increment nonce by one, wrap around if needed
	 * @param ptr is a pointer to just beyond the end of the nonce
	 * @param guard_ptr is a pointer to the LHS of the buffer, beyond which we mustn't go
	 * @return void, we make it work:-)
	 *
	 * This'll incrememnt the nonce starting from the end of the
	 * buffer and walking backwards as incremented bytes wrap
	 * around. We never fail here and do want go faster stripes.
	 * If you provide bad input, bad things may happen!
	 * We do depend here on the limit on iterations in the calling
	 * code.
	 * We'll recurse - stack hit in 1/256 calls not too bad
	 * for this.
	 * Guard pointer will be hit with probability ~2^248
	 * But if it were (e.g. by a bad caller) then we'd be
	 * sad and scribble on memory so let's not do that.
	 * Note that if guard pointer is hit, then we'll return
	 * an unmodified buffer (well, 2nd time), so this code
	 * depends on the higher layer CS2014COIN_MAXITER limit
	 * being enforced.
	 */
	void incr_nonce(unsigned char *ptr, unsigned char* guard_ptr)
	{
		if ((ptr-1)==guard_ptr) return;
		unsigned char ch=*(ptr-1);
		if (ch==255) {
			incr_nonce(ptr-1,guard_ptr);
			*(ptr-1)=0;
		} else {
			*(ptr-1)=(ch+1);
		}
		return;
	}

/*!
 * @brief make a coin
 * @param bits specifies how many bits need to be zero in the hash-output
 * @param buf is an allocated buffer for the coid
 * @param buflen is an in/out parameter reflecting the buffer-size/actual-coin-size
 * @return the random byte
 *
 * Make me a coin of the required quality/strength
 *
 */
int cs2014coin_make(int bits, unsigned char *buf, int *buflen)
{
    unsigned char coinbuf[CS2014COIN_BUFSIZE];
	cs2014coin_t mycoin;
 	mycoin.bits = bits;				//Inserting bits
	mycoin.ciphersuite=CS2014COIN_CS_0;		//Ciphersuite value, default 0
	mycoin.keylen = KEYLENGTH;			        //Length of public key (in bytes) - 9E
	mycoin.noncelen = NONCELENGTH;			//Length of nonce (in bytes) - 20
	mycoin.hashlen = HASHLENGTH;			//Hash length (in bytes) - 20
	mycoin.siglen = SIGLENGTH;			//Length of signature (in bytes) - 8A

  int ret;

  //Inserting ciphersuite val to coin buffer
  int value = ntohl(mycoin.ciphersuite);
  memcpy(coinbuf, &value, STANDARDLENGTH);
  //dumpbuf("Added ciphersuite:", coinbuf, 4);


  //Inserting bits(difficulty) to coin buffer
  int newbits = ntohl(mycoin.bits);
  mycoin.bits = newbits;
  memcpy(coinbuf+4, &newbits, STANDARDLENGTH);

  //dumpbuf("Added difficulty:", coinbuf, 8);

  //Inserting keylen to coin buffer
  int newkeylen = ntohl(mycoin.keylen);
  mycoin.keylen = newkeylen;
  memcpy(coinbuf+8, &newkeylen, STANDARDLENGTH);
  //dumpbuf("Added keylength:", coinbuf, 12);

  //Initialise mbed tls functions
  mbedtls_pk_context key;
  mbedtls_entropy_context entropy;
  mbedtls_ctr_drbg_context ctr_drbg;
  mbedtls_pk_init( &key );
  mbedtls_ctr_drbg_init( &ctr_drbg );
  mbedtls_entropy_init( &entropy );
  const char *pers = "gen_key";

  //Seeding random number generator
  if( ( ret = mbedtls_ctr_drbg_seed( &ctr_drbg, mbedtls_entropy_func, &entropy,
                             (const unsigned char *) pers,
                             strlen( pers ) ) ) != 0 )
  {
      mbedtls_//printf( " failed\n  ! mbedtls_ctr_drbg_seed returned -0x%04x\n", -ret );
  }

  //Setting up generating a key
  if( ( ret = mbedtls_pk_setup( &key, mbedtls_pk_info_from_type(MBEDTLS_PK_ECKEY) ) ) != 0 )
  {
      mbedtls_//printf( " failed\n  !  mbedtls_pk_setup returned -0x%04x", -ret );
  }

  //Generating actual key
  ret = mbedtls_ecp_gen_key( MBEDTLS_ECP_DP_SECP521R1, mbedtls_pk_ec( key ),
                    mbedtls_ctr_drbg_random, &ctr_drbg );
  if( ret != 0 )
  {
      mbedtls_//printf( " failed\n  !  mbedtls_rsa_gen_key returned -0x%04x", -ret );
  }

  unsigned char key_output_buf[KEYLENGTH];
  size_t key_buf_size = KEYLENGTH;
  memset(key_output_buf,0,KEYLENGTH);

  ret = mbedtls_pk_write_pubkey_der( &key, key_output_buf, key_buf_size);
  mycoin.keyval = key_output_buf;
  memcpy(coinbuf+12, &key_output_buf, KEYLENGTH);
  //dumpbuf("Added keyval:", coinbuf, 12+KEYLENGTH);


  //Initialising SHA256 Hash
  mbedtls_md_context_t sha_ctx;
  mbedtls_md_init( &sha_ctx );

  ret = mbedtls_md_setup( &sha_ctx, mbedtls_md_info_from_type( MBEDTLS_MD_SHA256 ), 1 );
  if( ret != 0 )
  {
  	mbedtls_//printf( "  ! mbedtls_md_setup() returned -0x%04x\n", -ret );
  }

  int newnoncelen = ntohl(NONCELENGTH);
  mycoin.noncelen = newnoncelen;
  memcpy(coinbuf+12+KEYLENGTH, &newnoncelen, STANDARDLENGTH);
  //dumpbuf("Added nonce length:", coinbuf, 16+KEYLENGTH);


  //GenerateNonce
  unsigned char noncevalue[NONCELENGTH];
  ret = mbedtls_ctr_drbg_random( &ctr_drbg, noncevalue, NONCELENGTH );
  if( ret != 0 )
  {
	mbedtls_//printf("failed!\n");
  }

  //Inserting nonce into coin buf
  mycoin.nonceval = noncevalue;
  memcpy(coinbuf+16+KEYLENGTH, &noncevalue, NONCELENGTH);
  //dumpbuf("Added nonce value:", coinbuf, 16+KEYLENGTH+NONCELENGTH);

  //Inserting reversed hash length into coin buf
  int newhashlen = ntohl(HASHLENGTH);
  mycoin.hashlen = newhashlen;
  memcpy(coinbuf+16+KEYLENGTH+NONCELENGTH, &newhashlen, STANDARDLENGTH);
  //dumpbuf("Added hash length:", coinbuf, 20+KEYLENGTH+NONCELENGTH);

  //Add 0x00 pow value into coinbuf
  unsigned char zerobuf[HASHLENGTH];
  memset(zerobuf,0x00,HASHLENGTH);
  memcpy(&mycoin.hashval, &zerobuf, HASHLENGTH);
  memcpy(coinbuf+20+KEYLENGTH+NONCELENGTH, &mycoin.hashval, HASHLENGTH);
  //dumpbuf("Added empty hash value:", coinbuf, 20+KEYLENGTH+NONCELENGTH+HASHLENGTH);

  //SHA-256 hashing the nonce and giving pow hash
  //if checker == 1 LS0's are correct (equal to difficulty)
  int checker = 0;


  //Increment until hash LSB zero's == difficulty
  int i=0;
  int newnonceint;
  unsigned char hashbuf[HASHLENGTH];
  unsigned char *ptr = noncevalue+NONCELENGTH;
  unsigned char *guard_ptr = noncevalue;
  for(i;i<CS2014COIN_MAXITER;i++)
  {
	  //start hash
	  mbedtls_md_starts( &sha_ctx );
	  //SHA-256(bits 0...)
	  mbedtls_md_update( &sha_ctx, coinbuf, POWBUFLENGTH );
	  //hashval = SHA-256(nonceval)
	  mbedtls_md_finish( &sha_ctx, hashbuf );

	  //check if hashval (powhash) ending zeros equal difficulty
          checker = zero_bits(bits, hashbuf, HASHLENGTH);

 	  if(checker == 1) {
		  //printf("Success! Found correct nonce.\n");
		  break;
	  }

	  else{
		  //Increment nonce
		  incr_nonce(ptr, guard_ptr);

		  //Store nonce int back into buffer
		  mycoin.nonceval = noncevalue;
		  memcpy(coinbuf+16+KEYLENGTH, &noncevalue, NONCELENGTH);
      }
  }

  if(checker == 0)
  {
	//printf("Error, unable to produce correct nonce.");
	return(0);
  }


  //Inserting correct hash value into coin buf
  mycoin.hashval = hashbuf;
  memcpy(coinbuf+20+KEYLENGTH+NONCELENGTH, &hashbuf, HASHLENGTH);
  //dumpbuf("Added correct hash value:", coinbuf, 20+KEYLENGTH+NONCELENGTH+HASHLENGTH);


  mbedtls_md_starts( &sha_ctx );
  mbedtls_md_update( &sha_ctx, coinbuf, 242 );
  mbedtls_md_finish( &sha_ctx, hashbuf );


  //Signing key using pow hash
  size_t siglength = 150;
  unsigned char sigbuf[150];

  if( ( ret = mbedtls_pk_sign( &key, MBEDTLS_MD_SHA256, hashbuf, 0, sigbuf, &siglength,
                       mbedtls_ctr_drbg_random, &ctr_drbg ) ) != 0 )
  {
      mbedtls_//printf( " failed\n  ! mbedtls_pk_sign returned -0x%04x\n", -ret );
  }

  //Inserting siglen to coin buf
  long int k;
  k = ntohl(siglength);

  mycoin.siglen = siglength;
  memcpy(coinbuf+242, &k, STANDARDLENGTH);
  //dumpbuf("Added signature length:", coinbuf, 24+KEYLENGTH+NONCELENGTH+HASHLENGTH);

  //Inserting sigval to coin buf
  mycoin.sigval = sigbuf;
  memcpy(coinbuf+246, &sigbuf, siglength);
  //dumpbuf("Added signature value:", coinbuf, 24+KEYLENGTH+NONCELENGTH+HASHLENGTH+siglength);

  //Populating actual input buffer
  memcpy(buf, coinbuf, 246 + siglength);
  *buflen = 246 + siglength;
  //dumpbuf("buf", buf, 246 + siglength);


 //Verifying signature
  int rv;

  rv = mbedtls_pk_verify(&key, MBEDTLS_MD_SHA256, hashbuf, HASHLENGTH, sigbuf, siglength);
  if (rv!=0) {
  	//printf("Failed to verify signature\n");
  }
  else {
	//printf("Successfully verified signature!\n");
  }

  return (0);
}
