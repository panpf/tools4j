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

package com.github.panpf.tools4j.base64.ktx

import com.github.panpf.tools4j.base64.Base64x


/*
 * Base64 related extension methods or properties
 */


/**
 * Base64-encode the given data and return a newly allocated
 * byte[] with the result.
 *
 * @receiver the data to encode
 * @param offset the position within the input array at which to
 * start
 * @param len    the number of bytes of input to encode
 * @param flags  controls certain features of the encoded output.
 * Passing `DEFAULT` results in output that
 * adheres to RFC 2045.
 */
inline fun ByteArray.base64Encode(offset: Int, len: Int, flags: Int): ByteArray = Base64x.encode(this, offset, len, flags)

/**
 * Base64-encode the given data and return a newly allocated
 * byte[] with the result.
 *
 * @receiver the data to encode
 * @param offset the position within the input array at which to
 * start
 * @param len    the number of bytes of input to encode
 */
inline fun ByteArray.base64Encode(offset: Int, len: Int): ByteArray = Base64x.encode(this, offset, len)

/**
 * Base64-encode the given data and return a newly allocated
 * byte[] with the result.
 *
 * @receiver the data to encode
 * @param flags controls certain features of the encoded output.
 * Passing `DEFAULT` results in output that
 * adheres to RFC 2045.
 */
inline fun ByteArray.base64Encode(flags: Int): ByteArray = Base64x.encode(this, flags)

/**
 * Base64-encode the given data and return a newly allocated
 * byte[] with the result.
 *
 * @receiver the data to encode
 */
inline fun ByteArray.base64Encode(): ByteArray = Base64x.encode(this)

/**
 * Base64-encode the given data and return a newly allocated
 * byte[] with the result.
 *
 * @receiver the data to encode
 * @param flags controls certain features of the encoded output.
 * Passing `DEFAULT` results in output that
 * adheres to RFC 2045.
 */
inline fun String.base64Encode(flags: Int): ByteArray = Base64x.encode(this, flags)

/**
 * Base64-encode the given data and return a newly allocated
 * byte[] with the result.
 *
 * @receiver the data to encode
 */
inline fun String.base64Encode(): ByteArray = Base64x.encode(this)


/**
 * Base64-encode the given data and return a newly allocated
 * String with the result.
 *
 * @receiver the data to encode
 * @param offset the position within the input array at which to
 * start
 * @param len    the number of bytes of input to encode
 * @param flags  controls certain features of the encoded output.
 * Passing `DEFAULT` results in output that
 * adheres to RFC 2045.
 */
inline fun ByteArray.base64EncodeToString(offset: Int, len: Int, flags: Int): String = Base64x.encodeToString(this, offset, len, flags)

/**
 * Base64-encode the given data and return a newly allocated
 * String with the result.
 *
 * @receiver the data to encode
 * @param offset the position within the input array at which to
 * start
 * @param len    the number of bytes of input to encode
 */
inline fun ByteArray.base64EncodeToString(offset: Int, len: Int): String = Base64x.encodeToString(this, offset, len)

/**
 * Base64-encode the given data and return a newly allocated
 * String with the result.
 *
 * @receiver the data to encode
 * @param flags controls certain features of the encoded output.
 * Passing `DEFAULT` results in output that
 * adheres to RFC 2045.
 */
inline fun ByteArray.base64EncodeToString(flags: Int): String = Base64x.encodeToString(this, flags)

/**
 * Base64-encode the given data and return a newly allocated
 * String with the result.
 *
 * @receiver the data to encode
 */
inline fun ByteArray.base64EncodeToString(): String = Base64x.encodeToString(this)

/**
 * Base64-encode the given data and return a newly allocated
 * String with the result.
 *
 * @receiver the data to encode
 * @param flags controls certain features of the encoded output.
 * Passing `DEFAULT` results in output that
 * adheres to RFC 2045.
 */
inline fun String.base64EncodeToString(flags: Int): String = Base64x.encodeToString(this, flags)

/**
 * Base64-encode the given data and return a newly allocated
 * String with the result.
 *
 * @receiver the data to encode
 */
inline fun String.base64EncodeToString(): String = Base64x.encodeToString(this)

//  --------------------------------------------------------
//  decoding
//  --------------------------------------------------------

/**
 * Decode the Base64-encoded data in input and return the data in
 * a new byte array.
 *
 *
 * The padding '=' characters at the end are considered optional, but
 * if any are present, there must be the correct number of them.
 *
 * @receiver the data to decode
 * @param offset the position within the input array at which to start
 * @param len    the number of bytes of input to decode
 * @param flags  controls certain features of the decoded output.
 * Pass `DEFAULT` to decode standard Base64.
 * @throws IllegalArgumentException if the input contains
 * incorrect padding
 */
inline fun ByteArray.base64Decode(offset: Int, len: Int, flags: Int): ByteArray = Base64x.decode(this, offset, len, flags)

/**
 * Decode the Base64-encoded data in input and return the data in
 * a new byte array.
 *
 *
 * The padding '=' characters at the end are considered optional, but
 * if any are present, there must be the correct number of them.
 *
 * @receiver the data to decode
 * @param offset the position within the input array at which to start
 * @param len    the number of bytes of input to decode
 * @throws IllegalArgumentException if the input contains incorrect padding
 */
inline fun ByteArray.base64Decode(offset: Int, len: Int): ByteArray = Base64x.decode(this, offset, len)

/**
 * Decode the Base64-encoded data in input and return the data in
 * a new byte array.
 *
 *
 * The padding '=' characters at the end are considered optional, but
 * if any are present, there must be the correct number of them.
 *
 * @receiver the input array to decode
 * @param flags controls certain features of the decoded output.
 * Pass `DEFAULT` to decode standard Base64.
 * @throws IllegalArgumentException if the input contains incorrect padding
 */
inline fun ByteArray.base64Decode(flags: Int): ByteArray = Base64x.decode(this, flags)

/**
 * Decode the Base64-encoded data in input and return the data in
 * a new byte array.
 *
 *
 * The padding '=' characters at the end are considered optional, but
 * if any are present, there must be the correct number of them.
 *
 * @receiver the input array to decode
 * @throws IllegalArgumentException if the input contains incorrect padding
 */
inline fun ByteArray.base64Decode(): ByteArray = Base64x.decode(this)

/**
 * Decode the Base64-encoded data in input and return the data in
 * a new byte array.
 *
 *
 * The padding '=' characters at the end are considered optional, but
 * if any are present, there must be the correct number of them.
 *
 * @receiver the input String to decode, which is converted to
 * bytes using the default charset
 * @param flags controls certain features of the decoded output.
 * Pass `DEFAULT` to decode standard Base64.
 * @throws IllegalArgumentException if the input contains
 * incorrect padding
 */
inline fun String.base64Decode(flags: Int): ByteArray = Base64x.decode(this, flags)

/**
 * Decode the Base64-encoded data in input and return the data in
 * a new byte array.
 *
 *
 * The padding '=' characters at the end are considered optional, but
 * if any are present, there must be the correct number of them.
 *
 * @receiver the input String to decode, which is converted to
 * bytes using the default charset
 * @throws IllegalArgumentException if the input contains
 * incorrect padding
 */
inline fun String.base64Decode(): ByteArray = Base64x.decode(this)


/**
 * Decode the Base64-encoded data in input and return the data in
 * a new byte array.
 *
 *
 * The padding '=' characters at the end are considered optional, but
 * if any are present, there must be the correct number of them.
 *
 * @receiver the data to decode
 * @param offset the position within the input array at which to start
 * @param len    the number of bytes of input to decode
 * @param flags  controls certain features of the decoded output.
 * Pass `DEFAULT` to decode standard Base64.
 * @throws IllegalArgumentException if the input contains
 * incorrect padding
 */
inline fun ByteArray.base64DecodeToString(offset: Int, len: Int, flags: Int): String = Base64x.decodeToString(this, offset, len, flags)

/**
 * Decode the Base64-encoded data in input and return the data in
 * a new byte array.
 *
 *
 * The padding '=' characters at the end are considered optional, but
 * if any are present, there must be the correct number of them.
 *
 * @receiver the data to decode
 * @param offset the position within the input array at which to start
 * @param len    the number of bytes of input to decode
 * @throws IllegalArgumentException if the input contains incorrect padding
 */
inline fun ByteArray.base64DecodeToString(offset: Int, len: Int): String = Base64x.decodeToString(this, offset, len)

/**
 * Decode the Base64-encoded data in input and return the data in
 * a new byte array.
 *
 *
 * The padding '=' characters at the end are considered optional, but
 * if any are present, there must be the correct number of them.
 *
 * @receiver the input array to decode
 * @param flags controls certain features of the decoded output.
 * Pass `DEFAULT` to decode standard Base64.
 * @throws IllegalArgumentException if the input contains incorrect padding
 */
inline fun ByteArray.base64DecodeToString(flags: Int): String = Base64x.decodeToString(this, flags)

/**
 * Decode the Base64-encoded data in input and return the data in
 * a new byte array.
 *
 *
 * The padding '=' characters at the end are considered optional, but
 * if any are present, there must be the correct number of them.
 *
 * @receiver the input array to decode
 * @throws IllegalArgumentException if the input contains incorrect padding
 */
inline fun ByteArray.base64DecodeToString(): String = Base64x.decodeToString(this)

/**
 * Decode the Base64-encoded data in input and return the data in
 * a new byte array.
 *
 *
 * The padding '=' characters at the end are considered optional, but
 * if any are present, there must be the correct number of them.
 *
 * @receiver the input String to decode, which is converted to
 * bytes using the default charset
 * @param flags controls certain features of the decoded output.
 * Pass `DEFAULT` to decode standard Base64.
 * @throws IllegalArgumentException if the input contains
 * incorrect padding
 */
inline fun String.base64DecodeToString(flags: Int): String = Base64x.decodeToString(this, flags)

/**
 * Decode the Base64-encoded data in input and return the data in
 * a new byte array.
 *
 *
 * The padding '=' characters at the end are considered optional, but
 * if any are present, there must be the correct number of them.
 *
 * @receiver the input String to decode, which is converted to
 * bytes using the default charset
 * @throws IllegalArgumentException if the input contains
 * incorrect padding
 */
inline fun String.base64DecodeToString(): String = Base64x.decodeToString(this)
