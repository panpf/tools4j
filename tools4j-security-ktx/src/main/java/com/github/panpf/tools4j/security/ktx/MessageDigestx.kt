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

import com.github.panpf.tools4j.security.MessageDigestListener
import com.github.panpf.tools4j.security.MessageDigestx
import java.io.File
import java.io.IOException
import java.io.InputStream


/*
 * Message digest related extension methods or properties
 */


/* ******************************************* InputStream *******************************************/


/**
 * Get the message digest of the input stream using the specified [algorithm]
 */
@Throws(IOException::class)
inline fun InputStream.getDigest(algorithm: String, listener: MessageDigestListener? = null): String = MessageDigestx.getDigest(this, algorithm, listener)

/**
 * Get the message digest of the input stream using the specified [algorithm]
 */
@Throws(IOException::class)
inline fun InputStream.getDigestOrEmpty(algorithm: String, listener: MessageDigestListener? = null): String = MessageDigestx.getDigestOrEmpty(this, algorithm, listener)

/**
 * Get the message digest of the input stream using the specified [algorithm]
 */
@Throws(IOException::class)
inline fun InputStream.getDigestOrNull(algorithm: String, listener: MessageDigestListener? = null): String? = MessageDigestx.getDigestOrNull(this, algorithm, listener)

/**
 * Get the message digest of the input stream using the MD5 algorithm
 */
inline fun InputStream.getMD5Digest(listener: MessageDigestListener? = null): String = MessageDigestx.getMD5(this, listener)

/**
 * Get the message digest of the input stream using the MD5 algorithm
 */
inline fun InputStream.getMD5DigestOrEmpty(listener: MessageDigestListener? = null): String = MessageDigestx.getMD5OrEmpty(this, listener)

/**
 * Get the message digest of the input stream using the MD5 algorithm
 */
inline fun InputStream.getMD5DigestOrNull(listener: MessageDigestListener? = null): String? = MessageDigestx.getMD5OrNull(this, listener)

/**
 * Get the message digest of the input stream using the MD5 algorithm, only the middle 16 bits are reserved
 */
inline fun InputStream.getMD5_16Digest(listener: MessageDigestListener? = null): String = MessageDigestx.getMD5_16(this, listener)

/**
 * Get the message digest of the input stream using the MD5 algorithm, only the middle 16 bits are reserved
 */
inline fun InputStream.getMD5_16DigestOrEmpty(listener: MessageDigestListener? = null): String = MessageDigestx.getMD5_16OrEmpty(this, listener)

/**
 * Get the message digest of the input stream using the MD5 algorithm, only the middle 16 bits are reserved
 */
inline fun InputStream.getMD5_16DigestOrNull(listener: MessageDigestListener? = null): String? = MessageDigestx.getMD5_16OrNull(this, listener)

/**
 * Get the message digest of the input stream using the SHA1 algorithm
 */
inline fun InputStream.getSHA1Digest(listener: MessageDigestListener? = null): String = MessageDigestx.getSHA1(this, listener)

/**
 * Get the message digest of the input stream using the SHA1 algorithm
 */
inline fun InputStream.getSHA1DigestOrEmpty(listener: MessageDigestListener? = null): String = MessageDigestx.getSHA1OrEmpty(this, listener)

/**
 * Get the message digest of the input stream using the SHA1 algorithm
 */
inline fun InputStream.getSHA1DigestOrNull(listener: MessageDigestListener? = null): String? = MessageDigestx.getSHA1OrNull(this, listener)

/**
 * Get the message digest of the input stream using the SHA-256 algorithm
 */
inline fun InputStream.getSHA256Digest(listener: MessageDigestListener? = null): String = MessageDigestx.getSHA256(this, listener)

/**
 * Get the message digest of the input stream using the SHA-256 algorithm
 */
inline fun InputStream.getSHA256DigestOrEmpty(listener: MessageDigestListener? = null): String = MessageDigestx.getSHA256OrEmpty(this, listener)

/**
 * Get the message digest of the input stream using the SHA-256 algorithm
 */
inline fun InputStream.getSHA256DigestOrNull(listener: MessageDigestListener? = null): String? = MessageDigestx.getSHA256OrNull(this, listener)

/**
 * Get the message digest of the input stream using the SHA-512 algorithm
 */
inline fun InputStream.getSHA512Digest(listener: MessageDigestListener? = null): String = MessageDigestx.getSHA512(this, listener)

/**
 * Get the message digest of the input stream using the SHA-512 algorithm
 */
inline fun InputStream.getSHA512DigestOrEmpty(listener: MessageDigestListener? = null): String = MessageDigestx.getSHA512OrEmpty(this, listener)

/**
 * Get the message digest of the input stream using the SHA-512 algorithm
 */
inline fun InputStream.getSHA512DigestOrNull(listener: MessageDigestListener? = null): String? = MessageDigestx.getSHA512OrNull(this, listener)


/* ******************************************* ByteArray *******************************************/


/**
 * Get the message digest of the bytes using the specified [algorithm]
 */
inline fun ByteArray.getDigest(algorithm: String): String = MessageDigestx.getDigest(this, algorithm)

/**
 * Get the message digest of the bytes using the MD5 algorithm
 */
inline fun ByteArray.getMD5Digest(): String = MessageDigestx.getMD5(this)

/**
 * Get the message digest of the bytes using the MD5 algorithm, only the middle 16 bits are reserved
 */
inline fun ByteArray.getMD5_16Digest(): String = MessageDigestx.getMD5_16(this)

/**
 * Get the message digest of the bytes using the SHA1 algorithm
 */
inline fun ByteArray.getSHA1Digest(): String = MessageDigestx.getSHA1(this)

/**
 * Get the message digest of the bytes using the SHA-256 algorithm
 */
inline fun ByteArray.getSHA256Digest(): String = MessageDigestx.getSHA256(this)

/**
 * Get the message digest of the bytes using the SHA-512 algorithm
 */
inline fun ByteArray.getSHA512Digest(): String = MessageDigestx.getSHA512(this)


/* ******************************************* String *******************************************/


/**
 * Get the message digest of the text using the specified [algorithm]
 */
inline fun String.getDigest(algorithm: String): String = MessageDigestx.getDigest(this, algorithm)

/**
 * Get the message digest of the text using the MD5 algorithm
 */
inline fun String.getMD5Digest(): String = MessageDigestx.getMD5(this)

/**
 * Get the message digest of the text using the MD5 algorithm, only the middle 16 bits are reserved
 */
inline fun String.getMD5_16Digest(): String = MessageDigestx.getMD5_16(this)

/**
 * Get the message digest of the text using the SHA1 algorithm
 */
inline fun String.getSHA1Digest(): String = MessageDigestx.getSHA1(this)

/**
 * Get the message digest of the text using the SHA-256 algorithm
 */
inline fun String.getSHA256Digest(): String = MessageDigestx.getSHA256(this)

/**
 * Get the message digest of the text using the SHA-512 algorithm
 */
inline fun String.getSHA512Digest(): String = MessageDigestx.getSHA512(this)


/* ******************************************* File *******************************************/


/**
 * Get the message digest of the file using the specified [algorithm]
 */
@Throws(IOException::class)
inline fun File.getDigest(algorithm: String, listener: MessageDigestListener? = null): String = MessageDigestx.getDigest(this, algorithm, listener)

/**
 * Get the message digest of the file using the specified [algorithm]
 */
@Throws(IOException::class)
inline fun File.getDigestOrEmpty(algorithm: String, listener: MessageDigestListener? = null): String = MessageDigestx.getDigestOrEmpty(this, algorithm, listener)

/**
 * Get the message digest of the file using the specified [algorithm]
 */
@Throws(IOException::class)
inline fun File.getDigestOrNull(algorithm: String, listener: MessageDigestListener? = null): String? = MessageDigestx.getDigestOrNull(this, algorithm, listener)

/**
 * Get the message digest of the file using the MD5 algorithm
 */
@Throws(IOException::class)
inline fun File.getMD5Digest(listener: MessageDigestListener? = null): String = MessageDigestx.getMD5(this, listener)

/**
 * Get the message digest of the file using the MD5 algorithm
 */
@Throws(IOException::class)
inline fun File.getMD5DigestOrEmpty(listener: MessageDigestListener? = null): String = MessageDigestx.getMD5OrEmpty(this, listener)

/**
 * Get the message digest of the file using the MD5 algorithm
 */
@Throws(IOException::class)
inline fun File.getMD5DigestOrNull(listener: MessageDigestListener? = null): String? = MessageDigestx.getMD5OrNull(this, listener)

/**
 * Get the message digest of the file using the MD5 algorithm, only the middle 16 bits are reserved
 */
@Throws(IOException::class)
inline fun File.getMD5_16Digest(listener: MessageDigestListener? = null): String = MessageDigestx.getMD5_16(this, listener)

/**
 * Get the message digest of the file using the MD5 algorithm, only the middle 16 bits are reserved
 */
@Throws(IOException::class)
inline fun File.getMD5_16DigestOrEmpty(listener: MessageDigestListener? = null): String = MessageDigestx.getMD5_16OrEmpty(this, listener)

/**
 * Get the message digest of the file using the MD5 algorithm, only the middle 16 bits are reserved
 */
@Throws(IOException::class)
inline fun File.getMD5_16DigestOrNull(listener: MessageDigestListener? = null): String? = MessageDigestx.getMD5_16OrNull(this, listener)

/**
 * Get the message digest of the file using the SHA1 algorithm
 */
@Throws(IOException::class)
inline fun File.getSHA1Digest(listener: MessageDigestListener? = null): String = MessageDigestx.getSHA1(this, listener)

/**
 * Get the message digest of the file using the SHA1 algorithm
 */
@Throws(IOException::class)
inline fun File.getSHA1DigestOrEmpty(listener: MessageDigestListener? = null): String = MessageDigestx.getSHA1OrEmpty(this, listener)

/**
 * Get the message digest of the file using the SHA1 algorithm
 */
@Throws(IOException::class)
inline fun File.getSHA1DigestOrNull(listener: MessageDigestListener? = null): String? = MessageDigestx.getSHA1OrNull(this, listener)

/**
 * Get the message digest of the file using the SHA-256 algorithm
 */
@Throws(IOException::class)
inline fun File.getSHA256Digest(listener: MessageDigestListener? = null): String = MessageDigestx.getSHA256(this, listener)

/**
 * Get the message digest of the file using the SHA-256 algorithm
 */
@Throws(IOException::class)
inline fun File.getSHA256DigestOrEmpty(listener: MessageDigestListener? = null): String = MessageDigestx.getSHA256OrEmpty(this, listener)

/**
 * Get the message digest of the file using the SHA-256 algorithm
 */
@Throws(IOException::class)
inline fun File.getSHA256DigestOrNull(listener: MessageDigestListener? = null): String? = MessageDigestx.getSHA256OrNull(this, listener)

/**
 * Get the message digest of the file using the SHA-512 algorithm
 */
@Throws(IOException::class)
inline fun File.getSHA512Digest(listener: MessageDigestListener? = null): String = MessageDigestx.getSHA512(this, listener)

/**
 * Get the message digest of the file using the SHA-512 algorithm
 */
@Throws(IOException::class)
inline fun File.getSHA512DigestOrEmpty(listener: MessageDigestListener? = null): String = MessageDigestx.getSHA512OrEmpty(this, listener)

/**
 * Get the message digest of the file using the SHA-512 algorithm
 */
@Throws(IOException::class)
inline fun File.getSHA512DigestOrNull(listener: MessageDigestListener? = null): String? = MessageDigestx.getSHA512OrNull(this, listener)