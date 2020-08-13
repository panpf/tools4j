/*
 * Copyright (C) 2020 panpf <panpfpanpf@outlook.com>
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

package com.github.panpf.tools4j.crypto.ktx

import com.github.panpf.tools4j.crypto.Aesx
import java.security.InvalidKeyException
import java.security.Key
import javax.crypto.BadPaddingException
import javax.crypto.IllegalBlockSizeException


/*
 * AES encryption and decryption related extension methods or properties
 */


/**
 * Create a secret key
 *
 * @param keySize The length of the generated key, optional 16, 32, 64, 128
 */
inline fun createAesKey(keySize: Int): Key = Aesx.createKey(keySize)

/**
 * Generate a secret key based on the password. The password can be of any length. The fixed password always generates a fixed key.
 *
 * @param keySizeInBytes The length of the generated key, optional 16, 32, 64, 128
 */
inline fun String.createAesKeyByPassword(keySizeInBytes: Int): Key = Aesx.createKeyByPassword(this, keySizeInBytes)

/**
 * Parse key from byte array
 */
inline fun ByteArray.toAesKeyFromBytes(): Key = Aesx.keyFromBytes(this)

/**
 * Parse key from Base64 string
 */
inline fun String.toAesKeyFromBase64(): Key = Aesx.keyFromBase64(this)


/**
 * Encrypt raw data using the AES algorithm
 *
 * @param algorithm AES encryption algorithm, The following values ​​are available: [Aesx.DEFAULT],[Aesx.ECB_NO],[Aesx.ECB_PKCS5],[Aesx.ECB_PKCS7],
 * [Aesx.CBC_ISO10126],[Aesx.CBC_NO],[Aesx.CBC_PKCS5],[Aesx.CBC_PKCS7],[Aesx.CBC_ISO10126]
 * @param key       Secret key
 * @throws InvalidKeyException       Invalid key
 * @throws BadPaddingException       Padding error
 * @throws IllegalBlockSizeException Block size error
 */
@Throws(InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
inline fun ByteArray.aesEncrypt(algorithm: String, key: Key): ByteArray = Aesx.encrypt(this, algorithm, key)

/**
 * Encrypt raw text using the AES algorithm
 *
 * @param algorithm AES encryption algorithm, The following values ​​are available: [Aesx.DEFAULT],[Aesx.ECB_NO],[Aesx.ECB_PKCS5],[Aesx.ECB_PKCS7],
 * [Aesx.CBC_ISO10126],[Aesx.CBC_NO],[Aesx.CBC_PKCS5],[Aesx.CBC_PKCS7],[Aesx.CBC_ISO10126]
 * @param key       Secret key
 * @throws InvalidKeyException       Invalid key
 * @throws BadPaddingException       Padding error
 * @throws IllegalBlockSizeException Block size error
 */
@Throws(InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
inline fun String.aesEncrypt(algorithm: String, key: Key): ByteArray = Aesx.encrypt(this, algorithm, key)

/**
 * Encrypt the raw data using the AES algorithm and convert the encrypted result to Base64 encoding
 *
 * @param algorithm AES encryption algorithm, The following values ​​are available: [Aesx.DEFAULT],[Aesx.ECB_NO],[Aesx.ECB_PKCS5],[Aesx.ECB_PKCS7],
 * [Aesx.CBC_ISO10126],[Aesx.CBC_NO],[Aesx.CBC_PKCS5],[Aesx.CBC_PKCS7],[Aesx.CBC_ISO10126]
 * @param key       Secret key
 * @throws InvalidKeyException       Invalid key
 * @throws BadPaddingException       Padding error
 * @throws IllegalBlockSizeException Block size error
 */
@Throws(InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
inline fun ByteArray.aesEncryptToBase64(algorithm: String, key: Key): String = Aesx.encryptToBase64(this, algorithm, key)

/**
 * Encrypt the raw text using the AES algorithm and convert the encrypted result to Base64 encoding
 *
 * @param algorithm AES encryption algorithm, The following values ​​are available: [Aesx.DEFAULT],[Aesx.ECB_NO],[Aesx.ECB_PKCS5],[Aesx.ECB_PKCS7],
 * [Aesx.CBC_ISO10126],[Aesx.CBC_NO],[Aesx.CBC_PKCS5],[Aesx.CBC_PKCS7],[Aesx.CBC_ISO10126]
 * @param key       Secret key
 * @throws InvalidKeyException       Invalid key
 * @throws BadPaddingException       Padding error
 * @throws IllegalBlockSizeException Block size error
 */
@Throws(InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
inline fun String.aesEncryptToBase64(algorithm: String, key: Key): String = Aesx.encryptToBase64(this, algorithm, key)


/**
 * Decrypt ciphertext encrypted using the AES algorithm
 *
 * @param algorithm  AES encryption algorithm, The following values ​​are available: [Aesx.DEFAULT],[Aesx.ECB_NO],[Aesx.ECB_PKCS5],[Aesx.ECB_PKCS7],
 * [Aesx.CBC_ISO10126],[Aesx.CBC_NO],[Aesx.CBC_PKCS5],[Aesx.CBC_PKCS7],[Aesx.CBC_ISO10126]
 * @param key        Secret key
 * @throws InvalidKeyException       Invalid key
 * @throws BadPaddingException       Padding error
 * @throws IllegalBlockSizeException Block size error
 */
@Throws(InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
inline fun ByteArray.aesDecrypt(algorithm: String, key: Key): ByteArray = Aesx.decrypt(this, algorithm, key)

/**
 * Decryption uses the AES algorithm to encrypt and then use Base64 encoded ciphertext
 *
 * @param algorithm        AES encryption algorithm, The following values ​​are available: [Aesx.DEFAULT],[Aesx.ECB_NO],[Aesx.ECB_PKCS5],[Aesx.ECB_PKCS7],
 * [Aesx.CBC_ISO10126],[Aesx.CBC_NO],[Aesx.CBC_PKCS5],[Aesx.CBC_PKCS7],[Aesx.CBC_ISO10126]
 * @param key              Secret key
 * @throws InvalidKeyException       Invalid key
 * @throws BadPaddingException       Padding error
 * @throws IllegalBlockSizeException Block size error
 */
@Throws(InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
inline fun String.aesDecryptFromBase64(algorithm: String, key: Key): ByteArray = Aesx.decryptFromBase64(this, algorithm, key)

/**
 * Decrypt ciphertext encrypted using the AES algorithm
 *
 * @param algorithm  AES encryption algorithm, The following values ​​are available: [Aesx.DEFAULT],[Aesx.ECB_NO],[Aesx.ECB_PKCS5],[Aesx.ECB_PKCS7],
 * [Aesx.CBC_ISO10126],[Aesx.CBC_NO],[Aesx.CBC_PKCS5],[Aesx.CBC_PKCS7],[Aesx.CBC_ISO10126]
 * @param key        Secret key
 * @throws InvalidKeyException       Invalid key
 * @throws BadPaddingException       Padding error
 * @throws IllegalBlockSizeException Block size error
 */
@Throws(InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
inline fun ByteArray.aesDecryptToString(algorithm: String, key: Key): String = Aesx.decryptToString(this, algorithm, key)

/**
 * Decryption uses the AES algorithm to encrypt and then use Base64 encoded ciphertext
 *
 * @param algorithm        AES encryption algorithm, The following values ​​are available: [Aesx.DEFAULT],[Aesx.ECB_NO],[Aesx.ECB_PKCS5],[Aesx.ECB_PKCS7],
 * [Aesx.CBC_ISO10126],[Aesx.CBC_NO],[Aesx.CBC_PKCS5],[Aesx.CBC_PKCS7],[Aesx.CBC_ISO10126]
 * @param key              Secret key
 * @throws InvalidKeyException       Invalid key
 * @throws BadPaddingException       Padding error
 * @throws IllegalBlockSizeException Block size error
 */
@Throws(InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
inline fun String.aesDecryptToStringFromBase64(algorithm: String, key: Key): String = Aesx.decryptToStringFromBase64(this, algorithm, key)