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

 #if !defined(MBEDTLS_CONFIG_FILE)
 #include "mbedtls/config.h"
 #else
 #include MBEDTLS_CONFIG_FILE
 #endif

 #if defined(MBEDTLS_PLATFORM_C)
 #include "mbedtls/platform.h"
 #else
 #include <stdio.h>
 #define mbedtls_fprintf    fprintf
 #define mbedtls_printf     printf
 #endif

 #include "mbedtls/aes.h"
 #include "mbedtls/md.h"

 #include <stdio.h>
 #include <stdlib.h>
 #include <string.h>

 #if defined(_WIN32)
 #include <windows.h>
 #if !defined(_WIN32_WCE)
 #include <io.h>
 #endif
 #else
 #include <sys/types.h>
 #include <unistd.h>
 #endif

#include "cs2014coin.h"
#include "cs2014coin-int.h"
#include "mbedtls/entropy.h"
#include "mbedtls/ctr_drbg.h"
#include "mbedtls/ecdsa.h"
#include "mbedtls/sha256.h"

#if !defined(MBEDTLS_AES_C) || !defined(MBEDTLS_SHA256_C) || \
    !defined(MBEDTLS_FS_IO) || !defined(MBEDTLS_MD_C)
int main( void )
{
    mbedtls_printf("MBEDTLS_AES_C and/or MBEDTLS_SHA256_C "
                    "and/or MBEDTLS_FS_IO and/or MBEDTLS_MD_C "
                    "not defined.\n");
    return( 0 );
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
#else
int cs2014coin_make(int bits, unsigned char *buf, int *buflen)
{
	int ciphersuite = 0;				//Ciphersuite value, default 0
	int bits = 5;					//Difficulty value (in bits)

	int keylen = 158;				//Length of public key (in bytes) - 9E
	unsigned char *keyval;				//Key value

	int noncelen = 32;				//Length of nonce (in bytes) - 20
	unsigned char *nonceval;			//Nonce value

	int hashlen = 32;				//Hash length (in bytes) - 20
	unsigned char *hashval;				//Hash value

	int siglen = 138;				//Length of signature (in bytes) - 8A
	unsigned char *sigval;				//Signature value

  mbedtls_pk_context key;
  mbedtls_entropy_context entropy;
  mbedtls_ctr_drbg_context ctr_drbg;
  char buf[1024];
  const char *pers = "gen_key";

  mbedtls_pk_init( &key );
  mbedtls_ctr_drbg_init( &ctr_drbg );
  mbedtls_entropy_init( &entropy );

  //Seeding random number generator
  if( ( ret = mbedtls_ctr_drbg_seed( &ctr_drbg, mbedtls_entropy_func, &entropy,
                             (const unsigned char *) pers,
                             strlen( pers ) ) ) != 0 )
  {
      mbedtls_printf( " failed\n  ! mbedtls_ctr_drbg_seed returned -0x%04x\n", -ret );
      goto exit;
  }

  //Setting up generating a key
  if( ( ret = mbedtls_pk_setup( &key, mbedtls_pk_info_from_type(MBEDTLS_PK_ECKEY) ) ) != 0 )
  {
      mbedtls_printf( " failed\n  !  mbedtls_pk_setup returned -0x%04x", -ret );
      goto exit;
  }

  //Generating actual key
  ret = mbedtls_ecp_gen_key( opt.ec_curve, mbedtls_pk_ec( key ),
                    mbedtls_ctr_drbg_random, &ctr_drbg );
  if( ret != 0 )
  {
      mbedtls_printf( " failed\n  !  mbedtls_rsa_gen_key returned -0x%04x", -ret );
      goto exit;
  }

  //Writing public key
  unsigned char output_buf[keylen];
  unsigned char *c = output_buf;
  if( ( ret = mbedtls_pk_write_pubkey_der( key, output_buf, keylen ) ) < 0 )
      return( ret );

  len = ret;
  c = output_buf + sizeof(output_buf) - len - 1;
  //Copy keyval over to variable
  keyval = c;
  printf("%u", keyval);
/**
	//Random number generation for nonce
	//Produces nonce

	//Initialising SHA256 Hash
  mbedtls_aes_context aes_ctx;
	mbedtls_md_context_t sha_ctx;
	mbedtls_aes_init( &aes_ctx );
	mbedtls_md_init( &sha_ctx );

	ret = mbedtls_md_setup( &sha_ctx, mbedtls_md_info_from_type( MBEDTLS_MD_SHA256 ), 1 );
	if( ret != 0 )
	{
			mbedtls_printf( "  ! mbedtls_md_setup() returned -0x%04x\n", -ret );
			goto exit;
	}

	//SHA-256 hashing the nonce and giving pow hash
	boolean finished = false;

	while(!finished){
    //Generate nonce
    ret = mbedtls_ctr_drbg_random( &ctr_drbg, &nonceval, &noncelen );
  	if( ret != 0 )
  	{
  		mbedtls_printf("failed!\n");
  		goto cleanup;
  	}
		//start hash
		mbedtls_md_starts( &sha_ctx );
		//SHA-256(bits 0...)
		mbedtls_md_update( &sha_ctx, &nonce, &noncelen );
		//hashval = SHA-256(nonceval)
		mbedtls_md_finish( &sha_ctx, &hashval );


		//check if hashval (powhash) ending zeros equal difficulty
		//if true, finished = true
	}

  //Signing key using pow hash
  if( ( ret = mbedtls_pk_sign( &key, MBEDTLS_MD_SHA256, &hashval, &hashlen, &sigval, &siglen,
                       mbedtls_ctr_drbg_random, &ctr_drbg ) ) != 0 )
  {
      mbedtls_printf( " failed\n  ! mbedtls_pk_sign returned -0x%04x\n", -ret );
      goto exit;
  }*/
  int xxxx = 0;
  return xxxx;
}
