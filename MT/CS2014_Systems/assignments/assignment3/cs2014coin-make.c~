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
#define mbedtls_printf     printf
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
#define SIGLENGTH 138
#define CIPHERSUITEVAL 0
#define POWBUFLENGTH 242
#define MAX_ITERATIONS 1000000

void print_coin (cs2014coin_t *coin)
{
  /*
  //printing ciphersuite value
  unsigned char cipherbuf[STANDARDLENGTH];
  size_t cipherbuf_size = STANDARDLENGTH;
  memcpy(cipherbuf, &ciphersuite, cipherbuf_size);
  dumpbuf("Ciphersuite value:", cipherbuf, cipherbuf_size);

  //printing difficulty value
  unsigned char difficultybuf[STANDARDLENGTH];
  size_t difficultybuf_size = STANDARDLENGTH;
  //Reversing bits
  int newbits = ntohl(bits);
  memcpy(difficultybuf, &newbits, difficultybuf_size);
  dumpbuf("Difficulty (bits):", difficultybuf, difficultybuf_size);

  //Printing keylength
  //printing ciphersuite value
  unsigned char keylenbuf[STANDARDLENGTH];
  size_t keylenbuf_size = STANDARDLENGTH;
  int newkeylen = ntohl(keylen);
  memcpy(keylenbuf, &newkeylen, keylenbuf_size);
  dumpbuf("Public key length:", keylenbuf, keylenbuf_size);

  dumpbuf("Public key value", key_output_buf, key_buf_size);

  //printing signature length
  unsigned char siglenbuf[STANDARDLENGTH];
  size_t siglenbuf_size = STANDARDLENGTH;
  int newsiglen = ntohl(siglen);
  memcpy(siglenbuf, &newsiglen, siglenbuf_size);
  dumpbuf("Length of signature:", siglenbuf, siglenbuf_size);

  //Printing signal value
  dumpbuf("Signature value:", signvalue, siglength);
*/
}

void reverse(unsigned char *pointer)
{
   /*
    int i = 0;
    unsigned char temp;
    int length;

    for(i; *(pointer+i) != '\0'; i++)
    {
        length++;                        
    }

    i = 0;
    for(i; i < length--; i++)
    {
        temp = pointer[i];               
        pointer[i] = pointer[length-i];
        pointer[length-i] = temp;
    }
  */
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
  memcpy(coinbuf, &mycoin.ciphersuite, STANDARDLENGTH);
  dumpbuf("Added ciphersuite:", coinbuf, 4);
 

  //Inserting bits(difficulty) to coin buffer
  int newbits = ntohl(mycoin.bits);
  mycoin.bits = newbits;
  memcpy(coinbuf+4, &mycoin.bits, STANDARDLENGTH);

  dumpbuf("Added difficulty:", coinbuf, 8);

  //Inserting keylen to coin buffer
  int newkeylen = ntohl(mycoin.keylen);
  mycoin.keylen = newkeylen;
  memcpy(coinbuf+8, &mycoin.keylen, STANDARDLENGTH);
  dumpbuf("Added keylength:", coinbuf, 12);

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
      mbedtls_printf( " failed\n  ! mbedtls_ctr_drbg_seed returned -0x%04x\n", -ret );
  }

  //Setting up generating a key
  if( ( ret = mbedtls_pk_setup( &key, mbedtls_pk_info_from_type(MBEDTLS_PK_ECKEY) ) ) != 0 )
  {
      mbedtls_printf( " failed\n  !  mbedtls_pk_setup returned -0x%04x", -ret );
  }

  //Generating actual key
  ret = mbedtls_ecp_gen_key( MBEDTLS_ECP_DP_SECP521R1, mbedtls_pk_ec( key ),
                    mbedtls_ctr_drbg_random, &ctr_drbg );
  if( ret != 0 )
  {
      mbedtls_printf( " failed\n  !  mbedtls_rsa_gen_key returned -0x%04x", -ret );
  }

  //Adding public key value to coin buf
  unsigned char key_output_buf[KEYLENGTH];
  size_t key_buf_size = KEYLENGTH;
  memset(key_output_buf,0xAA,KEYLENGTH);

  ret = mbedtls_pk_write_pubkey_der( &key, key_output_buf, key_buf_size);
  mycoin.keyval = key_output_buf;
  memcpy(coinbuf+12, &mycoin.keyval, KEYLENGTH);
  dumpbuf("Added public key:", coinbuf, 12+KEYLENGTH);
  
  //Initialising SHA256 Hash
  mbedtls_md_context_t sha_ctx;
  mbedtls_md_init( &sha_ctx );

  ret = mbedtls_md_setup( &sha_ctx, mbedtls_md_info_from_type( MBEDTLS_MD_SHA256 ), 1 );
  if( ret != 0 )
  {
  	mbedtls_printf( "  ! mbedtls_md_setup() returned -0x%04x\n", -ret );
  }
	
  //Inserting nonce length into coin buf
  int newnoncelen = ntohl(mycoin.noncelen);
  mycoin.noncelen = newnoncelen;
  memcpy(coinbuf+12+KEYLENGTH, &mycoin.noncelen, STANDARDLENGTH);
  dumpbuf("Added nonce length:", coinbuf, 16+KEYLENGTH);

  //GenerateNonce	
  unsigned char noncevalue[NONCELENGTH]; 
  ret = mbedtls_ctr_drbg_random( &ctr_drbg, noncevalue, NONCELENGTH );
  if( ret != 0 )
  {
	mbedtls_printf("failed!\n");
  }

  //Inserting nonce into coin buf
  int nonceint = atoi(noncevalue);
  int newnonceint = ntohl(nonceint);
  memcpy(noncevalue, &newnonceint, NONCELENGTH);
  mycoin.nonceval = noncevalue;
  memcpy(coinbuf+16+KEYLENGTH, &mycoin.nonceval, NONCELENGTH);
  dumpbuf("Added nonce value:", coinbuf, 16+KEYLENGTH+NONCELENGTH);

/*
  //Inserting reversed hash length into coin buf
  int newhashlen = ntohl(mycoin.hashlen);
  mycoin.hashlen = newhashlen;
  memcpy(&buf+16+mycoin.keylen+mycoin.noncelen, &mycoin.hashlen, STANDARDLENGTH);

  //Add 0x00 pow value into coinbuf
  int zero = 0x00;
  memcpy(mycoin.hashval, &zero, mycoin.hashlen);
  memcpy(&buf+20+mycoin.keylen+mycoin.noncelen, &mycoin.hashval, mycoin.hashlen);
 
  //SHA-256 hashing the nonce and giving pow hash
  //Generate nonce
  //if checker == 1 LS0's are correct (equal to difficulty)
  int checker = 0;


  //Increment until hash LSB zero's == difficulty
  int i=0;
  for(i;i<CS2014COIN_MAXITER;i++)
  {
	  if(checker == 1) {
		printf("Success! Found correct nonce.");
		break;
	  }
	  
  	  //Add nonce into powbuf
	  mycoin.nonceval = noncevalue;
	  memcpy(powbuf+20+keylen, &noncevalue, noncelen);
  
	  //start hash
	  mbedtls_md_starts( &sha_ctx );
	  //SHA-256(bits 0...)
	  mbedtls_md_update( &sha_ctx, &buf, POWBUFLENGTH );
	  //hashval = SHA-256(nonceval)
	  mbedtls_md_finish( &sha_ctx, mycoin.hashval );

	  //check if hashval (powhash) ending zeros equal difficulty
          checker = zero_bits(bits, mycoin.hashval, mycoin.hashlen);
          nonceint = atoi(noncevalue);
	  nonceint++;
	  newnonceint = ntohl(nonceint);
          memcpy(noncevalue, &newnonceint, mycoin.noncelen);
  }
  
  if(checker == 0)
  {
	printf("Error, unable to produce correct nonce.");
	return(0);
  }

  //Inserting new pow hash value into coin buf
  memcpy(&buf+20+mycoin.keylen+mycoin.noncelen, &mycoin.hashval, mycoin.hashlen);

  //Signing key using pow hash
  if( ( ret = mbedtls_pk_sign( &key, MBEDTLS_MD_SHA256, mycoin.hashval, mycoin.hashlen, mycoin.sigval, &mycoin.siglen,
                       mbedtls_ctr_drbg_random, &ctr_drbg ) ) != 0 )
  {
      mbedtls_printf( " failed\n  ! mbedtls_pk_sign returned -0x%04x\n", -ret );
  }
  
  //Inserting siglen to coin buf
  int newsiglen = ntohl(mycoin.siglen);
  mycoin.siglen = newsiglen;
  memcpy(&buf+20+mycoin.keylen+mycoin.noncelen+mycoin.hashlen, &mycoin.siglen, STANDARDLENGTH);

  //Inserting sigval to coin buf
  memcpy(&buf+24+mycoin.keylen+mycoin.noncelen+mycoin.hashlen, &mycoin.sigval, mycoin.siglen);

  //Verifying signature
  int rv;
  rv = mbedtls_pk_verify(&key, MBEDTLS_MD_SHA256, mycoin.hashval, mycoin.hashlen, mycoin.sigval, mycoin.siglen);
  if (rv!=0) {
  	printf("Failed to verify signature\n");
  }
  else {
	printf("Successfully verified signature!\n");
  }
  */
  int xxxx = 0;
  return xxxx;
}
