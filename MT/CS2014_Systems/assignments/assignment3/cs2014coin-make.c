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

#include "cs2014coin.h"
#include "cs2014coin-int.h"
#include "mbedtls/entropy.h"
#include "mbedtls/ctr_drbg.h"
#include "mbedtls/ecdsa.h"
#include "mbedtls/sha256.h"

#define ECPARAMS    MBEDTLS_ECP_DP_SECP192R1

#if !defined(ECPARAMS)
#define ECPARAMS    mbedtls_ecp_curve_list()->grp_id
#endif

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
	int length_spacing = 4;				//Space for all length values (bytes)	
	
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

	mbedtls_ecdsa_context ctx_sign, ctx_verify;	//Signiture and verification variables
   	mbedtls_entropy_context entropy;		//entropy variable
    	mbedtls_ctr_drbg_context ctr_drbg;		//random number generator
	mbedtls_sha256_context sha256_ctx;		//sha256 context
	
	unsigned char public_key[public_key_length];	//public key
	unsigned char nonce[nonce_length];		//nonce
    	unsigned char hash[pow_hash_length];		//hash
    	unsigned char sig[siglength];			//signature
    	size_t sig_len;
    	const char *pers = "ecdsa";
    	((void) argv);

   	mbedtls_ecdsa_init( &ctx_sign );		//initialise ecdsa with signature context
   	mbedtls_ctr_drbg_init( &ctr_drbg );		//initialise random number gen
   	mbedtls_sha256_init( &sha256_ctx );		//initialise sha256 with sha256 context
	mbedtls_entropy_init( &entropy );

   	ret = 1;					//return result verifier

	//Initialises random key generator
        if( ( ret = mbedtls_ctr_drbg_seed( &ctr_drbg, mbedtls_entropy_func, &entropy,
                             (const unsigned char *) pers,
                               strlen( pers ) ) ) != 0 )
	{
 	     	  mbedtls_printf( " failed\n  ! mbedtls_ctr_drbg_seed returned %d\n", ret );
  	    	  goto exit;
	}

	//Runs random key generator
	if( ( ret = mbedtls_ecdsa_genkey( &ctx_sign, ECPARAMS,
                              mbedtls_ctr_drbg_random, &ctr_drbg ) ) != 0 )
	{
	        mbedtls_printf( " failed\n  ! mbedtls_ecdsa_genkey returned %d\n", ret );	
 	       goto exit;
	}
	
	//Extracts public key from generator
	public_key = &ctx_sign

	

	printf("I'm a stub!\n");
	return(CS2014COIN_GENERR);
}









































