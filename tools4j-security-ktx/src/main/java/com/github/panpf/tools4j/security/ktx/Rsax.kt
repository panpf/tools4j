/*
 * Copyright (C) 2018 Peng fei Pan <panpfpanpf@outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("NOTHING_TO_INLINE")

package com.github.panpf.tools4j.security.ktx

import com.github.panpf.tools4j.security.Rsax
import java.security.*
import java.security.spec.InvalidKeySpecException
import javax.crypto.BadPaddingException
import javax.crypto.IllegalBlockSizeException


/*
 * RSA encryption and decryption related extension methods or properties
 */

/**
 * Create a pair of RSA keys
 *
 * @param keySize Key length, usually a multiple of 1024
 */
fun createRsaKey(keySize: Int): KeyPair = Rsax.createKey(keySize)

/**
 * Parse the Base 64 string into a RSA public key
 *
 * @throws InvalidKeySpecException Invalid public key
 */
@Throws(InvalidKeySpecException::class)
inline fun String.toRsaPubKeyFromBase64(): PublicKey = Rsax.pubKeyFromBase64(this)

/**
 * Parse the Base 64 string into a RSA private key
 *
 * @throws InvalidKeySpecException Private key is invalid
 */
@Throws(InvalidKeySpecException::class)
inline fun String.toRsaPriKeyFromBase64(): PrivateKey = Rsax.priKeyFromBase64(this)


/**
 * Generate a RSA digital signature for the information with the private key
 *
 * @param priKey    Private key
 * @throws InvalidKeyException Private key is invalid
 * @throws SignatureException  Signature exception
 */
@Throws(InvalidKeyException::class, SignatureException::class)
inline fun ByteArray.rsaSign(priKey: PrivateKey): ByteArray = Rsax.sign(this, priKey)

/**
 * Generate a RSA digital signature for the information with the private key
 *
 * @param priKey Private key
 * @throws InvalidKeyException Private key is invalid
 * @throws SignatureException  Signature exception
 */
@Throws(InvalidKeyException::class, SignatureException::class)
inline fun String.rsaSign(priKey: PrivateKey): ByteArray = Rsax.sign(this, priKey)

/**
 * Generate a RSA digital signature of the information with a private key and return a Base64 string
 *
 * @param priKey    Private key
 * @throws InvalidKeyException Private key is invalid
 * @throws SignatureException  Signature exception
 */
@Throws(InvalidKeyException::class, SignatureException::class)
inline fun ByteArray.rsaSignToBase64(priKey: PrivateKey): String = Rsax.signToBase64(this, priKey)

/**
 * Generate a RSA digital signature of the information with a private key and return a Base64 string
 *
 * @param priKey Private key
 * @throws InvalidKeyException Private key is invalid
 * @throws SignatureException  Signature exception
 */
@Throws(InvalidKeyException::class, SignatureException::class)
inline fun String.rsaSignToBase64(priKey: PrivateKey): String = Rsax.signToBase64(this, priKey)


/**
 * Verify the RSA signature with the public key
 *
 * @param data      Original data
 * @param pubKey    Public key
 * @throws InvalidKeyException Invalid public key
 * @throws SignatureException  Signature exception
 */
@Throws(InvalidKeyException::class, SignatureException::class)
inline fun ByteArray.rsaVerify(data: ByteArray, pubKey: PublicKey): Boolean = Rsax.verify(this, data, pubKey)

/**
 * Verify the RSA signature with the public key
 *
 * @param text      Original text
 * @param pubKey    Public key
 * @throws InvalidKeyException Invalid public key
 * @throws SignatureException  Signature exception
 */
@Throws(InvalidKeyException::class, SignatureException::class)
inline fun ByteArray.rsaVerify(text: String, pubKey: PublicKey): Boolean = Rsax.verify(this, text, pubKey)

/**
 * Verify the RSA signature with the public key
 *
 * @param data       Original data
 * @param pubKey     Public key
 * @throws InvalidKeyException Invalid public key
 * @throws SignatureException  Signature exception
 */
@Throws(InvalidKeyException::class, SignatureException::class)
inline fun String.rsaVerifyFromBase64(data: ByteArray, pubKey: PublicKey): Boolean = Rsax.verifyFromBase64(this, data, pubKey)

/**
 * Verify the RSA signature with the public key
 *
 * @param text       Original text
 * @param pubKey     Public key
 * @throws InvalidKeyException Invalid public key
 * @throws SignatureException  Signature exception
 */
@Throws(InvalidKeyException::class, SignatureException::class)
inline fun String.rsaVerifyFromBase64(text: String, pubKey: PublicKey): Boolean = Rsax.verifyFromBase64(this, text, pubKey)


/**
 * Encrypt raw data using the RSA algorithm
 *
 * @param algorithm RSA encryption algorithm, The following values ​​are available: [.RSA],[.RSA_ECB_PKCS1],[.RSA_ECB_OAEP]
 * @param key       Secret key
 * @throws InvalidKeyException       Invalid key
 * @throws BadPaddingException       Padding error
 * @throws IllegalBlockSizeException Block size error
 */
@Throws(InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
inline fun ByteArray.rsaEncrypt(algorithm: String, key: Key): ByteArray = Rsax.encrypt(this, algorithm, key)

/**
 * Encrypt raw text using the RSA algorithm
 *
 * @param algorithm RSA encryption algorithm, The following values ​​are available: [.RSA],[.RSA_ECB_PKCS1],[.RSA_ECB_OAEP]
 * @param key       Secret key
 * @throws InvalidKeyException       Invalid key
 * @throws BadPaddingException       Padding error
 * @throws IllegalBlockSizeException Block size error
 */
@Throws(InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
inline fun String.rsaEncrypt(algorithm: String, key: Key): ByteArray = Rsax.encrypt(this, algorithm, key)

/**
 * Encrypt the raw data using the RSA algorithm and convert the encrypted result to Base64 encoding
 *
 * @param algorithm RSA encryption algorithm, The following values ​​are available: [.RSA],[.RSA_ECB_PKCS1],[.RSA_ECB_OAEP]
 * @param key       Secret key
 * @throws InvalidKeyException       Invalid key
 * @throws BadPaddingException       Padding error
 * @throws IllegalBlockSizeException Block size error
 */
@Throws(InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
inline fun ByteArray.rsaEncryptToBase64(algorithm: String, key: Key): String = Rsax.encryptToBase64(this, algorithm, key)

/**
 * Encrypt the raw text using the RSA algorithm and convert the encrypted result to Base64 encoding
 *
 * @param algorithm RSA encryption algorithm, The following values ​​are available: [.RSA],[.RSA_ECB_PKCS1],[.RSA_ECB_OAEP]
 * @param key       Secret key
 * @throws InvalidKeyException       Invalid key
 * @throws BadPaddingException       Padding error
 * @throws IllegalBlockSizeException Block size error
 */
@Throws(InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
inline fun String.rsaEncryptToBase64(algorithm: String, key: Key): String = Rsax.encryptToBase64(this, algorithm, key)


/**
 * Decrypt ciphertext encrypted using the RSA algorithm
 *
 * @param algorithm  RSA encryption algorithm, The following values ​​are available: [.RSA],[.RSA_ECB_PKCS1],[.RSA_ECB_OAEP]
 * @param key        Secret key
 * @throws InvalidKeyException       Invalid key
 * @throws BadPaddingException       Padding error
 * @throws IllegalBlockSizeException Block size error
 */
@Throws(InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
inline fun ByteArray.rsaDecrypt(algorithm: String, key: Key): ByteArray = Rsax.decrypt(this, algorithm, key)

/**
 * Decryption uses the RSA algorithm to encrypt and then use Base64 encoded ciphertext
 *
 * @param algorithm      RSA encryption algorithm, The following values ​​are available: [.RSA],[.RSA_ECB_PKCS1],[.RSA_ECB_OAEP]
 * @param key            Secret key
 * @throws InvalidKeyException       Invalid key
 * @throws BadPaddingException       Padding error
 * @throws IllegalBlockSizeException Block size error
 */
@Throws(InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
inline fun String.rsaDecryptFromBase64(algorithm: String, key: Key): ByteArray = Rsax.decryptFromBase64(this, algorithm, key)

/**
 * Decrypt ciphertext encrypted using the RSA algorithm
 *
 * @param algorithm  RSA encryption algorithm, The following values ​​are available: [.RSA],[.RSA_ECB_PKCS1],[.RSA_ECB_OAEP]
 * @param key        Secret key
 * @throws InvalidKeyException       Invalid key
 * @throws BadPaddingException       Padding error
 * @throws IllegalBlockSizeException Block size error
 */
@Throws(InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
inline fun ByteArray.rsaDecryptToString(algorithm: String, key: Key): String = Rsax.decryptToString(this, algorithm, key)

/**
 * Decryption uses the RSA algorithm to encrypt and then use Base64 encoded ciphertext
 *
 * @param algorithm      RSA encryption algorithm, The following values ​​are available: [.RSA],[.RSA_ECB_PKCS1],[.RSA_ECB_OAEP]
 * @param key            Secret key
 * @throws InvalidKeyException       Invalid key
 * @throws BadPaddingException       Padding error
 * @throws IllegalBlockSizeException Block size error
 */
@Throws(InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
inline fun String.rsaDecryptToStringFromBase64(algorithm: String, key: Key): String = Rsax.decryptToStringFromBase64(this, algorithm, key)
