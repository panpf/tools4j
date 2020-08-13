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

import com.github.panpf.tools4j.crypto.Desx
import java.security.InvalidKeyException
import java.security.Key
import java.security.spec.InvalidKeySpecException
import javax.crypto.BadPaddingException
import javax.crypto.IllegalBlockSizeException


/*
 * DES encryption and decryption related extension methods or properties
 */


/**
 * Generate a secret key based on the password. The fixed password always generates a fixed key.
 *
 * @throws InvalidKeyException     Invalid password
 * @throws InvalidKeySpecException Invalid password
 */
@Throws(InvalidKeyException::class, InvalidKeySpecException::class)
inline fun String.createDesKeyByPassword(): Key = Desx.createKeyByPassword(this)


/**
 * Encrypt raw data using the DES algorithm
 *
 * @param algorithm DES encryption algorithm, The following values ​​are available: [Desx.DEFAULT],[Desx.ECB_NO],[Desx.ECB_PKCS5],[Desx.ECB_PKCS7],
 * [Desx.CBC_ISO10126],[Desx.CBC_NO],[Desx.CBC_PKCS5],[Desx.CBC_PKCS7],[Desx.CBC_ISO10126]
 * @param key       Secret key
 * @throws InvalidKeyException       Invalid key
 * @throws BadPaddingException       Padding error
 * @throws IllegalBlockSizeException Block size error
 */
@Throws(InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
inline fun ByteArray.desEncrypt(algorithm: String, key: Key): ByteArray = Desx.encrypt(this, algorithm, key)

/**
 * Encrypt raw text using the DES algorithm
 *
 * @throws InvalidKeyException       Invalid key
 * @throws BadPaddingException       Padding error
 * @throws IllegalBlockSizeException Block size error
 */
@Throws(InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
inline fun String.desEncrypt(algorithm: String, key: Key): ByteArray = Desx.encrypt(this, algorithm, key)

/**
 * Encrypt the raw data using the DES algorithm and convert the encrypted result to Base64 encoding
 *
 * @throws InvalidKeyException       Invalid key
 * @throws BadPaddingException       Padding error
 * @throws IllegalBlockSizeException Block size error
 */
@Throws(InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
inline fun ByteArray.desEncryptToBase64(algorithm: String, key: Key): String = Desx.encryptToBase64(this, algorithm, key)

/**
 * Encrypt the raw text using the DES algorithm and convert the encrypted result to Base64 encoding
 *
 * @throws InvalidKeyException       Invalid key
 * @throws BadPaddingException       Padding error
 * @throws IllegalBlockSizeException Block size error
 */
@Throws(InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
inline fun String.desEncryptToBase64(algorithm: String, key: Key): String = Desx.encryptToBase64(this, algorithm, key)


/**
 * Decrypt ciphertext encrypted using the DES algorithm
 *
 * @param algorithm  DES encryption algorithm, The following values ​​are available: [Desx.DEFAULT],[Desx.ECB_NO],[Desx.ECB_PKCS5],[Desx.ECB_PKCS7],
 * [Desx.CBC_ISO10126],[Desx.CBC_NO],[Desx.CBC_PKCS5],[Desx.CBC_PKCS7],[Desx.CBC_ISO10126]
 * @param key        Secret key
 * @throws InvalidKeyException       Invalid key
 * @throws BadPaddingException       Padding error
 * @throws IllegalBlockSizeException Block size error
 */
@Throws(InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
inline fun ByteArray.desDecrypt(algorithm: String, key: Key): ByteArray = Desx.decrypt(this, algorithm, key)

/**
 * Decryption uses the DES algorithm to encrypt and then use Base64 encoded ciphertext
 *
 * @param algorithm        DES encryption algorithm, The following values ​​are available: [Desx.DEFAULT],[Desx.ECB_NO],[Desx.ECB_PKCS5],[Desx.ECB_PKCS7],
 * [Desx.CBC_ISO10126],[Desx.CBC_NO],[Desx.CBC_PKCS5],[Desx.CBC_PKCS7],[Desx.CBC_ISO10126]
 * @param key              Secret key
 * @throws InvalidKeyException       Invalid key
 * @throws BadPaddingException       Padding error
 * @throws IllegalBlockSizeException Block size error
 */
@Throws(InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
inline fun String.desDecryptFromBase64(algorithm: String, key: Key): ByteArray = Desx.decryptFromBase64(this, algorithm, key)

/**
 * Decrypt ciphertext encrypted using the DES algorithm
 *
 * @param algorithm  DES encryption algorithm, The following values ​​are available: [Desx.DEFAULT],[Desx.ECB_NO],[Desx.ECB_PKCS5],[Desx.ECB_PKCS7],
 * [Desx.CBC_ISO10126],[Desx.CBC_NO],[Desx.CBC_PKCS5],[Desx.CBC_PKCS7],[Desx.CBC_ISO10126]
 * @param key        Secret key
 * @throws InvalidKeyException       Invalid key
 * @throws BadPaddingException       Padding error
 * @throws IllegalBlockSizeException Block size error
 */
@Throws(InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
inline fun ByteArray.desDecryptToString(algorithm: String, key: Key): String = Desx.decryptToString(this, algorithm, key)

/**
 * Decryption uses the DES algorithm to encrypt and then use Base64 encoded ciphertext
 *
 * @param algorithm        DES encryption algorithm, The following values ​​are available: [Desx.DEFAULT],[Desx.ECB_NO],[Desx.ECB_PKCS5],[Desx.ECB_PKCS7],
 * [Desx.CBC_ISO10126],[Desx.CBC_NO],[Desx.CBC_PKCS5],[Desx.CBC_PKCS7],[Desx.CBC_ISO10126]
 * @param key              Secret key
 * @throws InvalidKeyException       Invalid key
 * @throws BadPaddingException       Padding error
 * @throws IllegalBlockSizeException Block size error
 */
@Throws(InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
inline fun String.desDecryptToStringFromBase64(algorithm: String, key: Key): String = Desx.decryptToStringFromBase64(this, algorithm, key)
