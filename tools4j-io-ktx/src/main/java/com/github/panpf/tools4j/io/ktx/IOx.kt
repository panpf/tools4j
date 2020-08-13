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

package com.github.panpf.tools4j.io.ktx

import com.github.panpf.tools4j.io.CopyListener
import com.github.panpf.tools4j.io.IOx
import java.io.*


/*
 * I/O related extension methods or properties
 */


/**
 * Quietly close
 */
inline fun Closeable?.closeQuietly() = IOx.closeQuietly(this)


/**
 * Reads this stream completely into a byte array and close stream.
 */
@Throws(IOException::class)
inline fun InputStream.readBytesAndClose(estimatedSize: Int): ByteArray = IOx.readBytesAndClose(this, estimatedSize)

/**
 * Reads this stream completely into a byte array and close stream.
 */
@Throws(IOException::class)
inline fun InputStream.readBytesAndClose(): ByteArray = IOx.readBytesAndClose(this)

/**
 * Reads this reader completely as a String and close stream.
 *
 * @return the string with corresponding file content.
 */
@Throws(IOException::class)
inline fun Reader.readTextAndClose(): String = IOx.readTextAndClose(this)

/**
 * Writes the specified byte to this output stream and close stream.
 */
@Throws(IOException::class)
inline fun OutputStream.writeByteAndClose(b: Int) = IOx.writeByteAndClose(this, b)

/**
 * Writes the specified byte array to this output stream and close stream.
 */
@Throws(IOException::class)
inline fun OutputStream.writeBytesAndClose(data: ByteArray) = IOx.writeBytesAndClose(this, data)

/**
 * Writes the specified byte array to this output stream and close stream.
 */
@Throws(IOException::class)
inline fun OutputStream.writeBytesAndClose(data: ByteArray, off: Int, len: Int) = IOx.writeBytesAndClose(this, data, off, len)

/**
 * Writes the specified text to this output stream and close stream.
 */
@Throws(IOException::class)
inline fun Writer.writeTextAndClose(text: String) = IOx.writeTextAndClose(this, text)

/**
 * Writes the specified text to this output stream and close stream.
 */
@Throws(IOException::class)
inline fun Writer.writeTextAndClose(text: String, off: Int, len: Int) = IOx.writeTextAndClose(this, text, off, len)

/**
 * Writes the specified char to this output stream and close stream.
 */
@Throws(IOException::class)
inline fun Writer.writeCharAndClose(c: Int) = IOx.writeCharAndClose(this, c)

/**
 * Writes the specified char array to this output stream and close stream.
 */
@Throws(IOException::class)
inline fun Writer.writeCharsAndClose(chars: CharArray) = IOx.writeCharsAndClose(this, chars)

/**
 * Writes the specified char array to this output stream and close stream.
 */
@Throws(IOException::class)
inline fun Writer.writeCharsAndClose(chars: CharArray, off: Int, len: Int) = IOx.writeCharsAndClose(this, chars, off, len)


/**
 * Copies this stream to the given output stream, returning the number of bytes copied
 *
 * **Note** It is the caller's responsibility to close both of these resources.
 */
@Throws(IOException::class)
inline fun InputStream.copyTo(out: OutputStream, bufferSize: Int, listener: CopyListener): Long =
        IOx.copyTo(this, out, bufferSize, listener)

/**
 * Copies this stream to the given output stream, returning the number of bytes copied
 *
 * **Note** It is the caller's responsibility to close both of these resources.
 */
@Throws(IOException::class)
inline fun InputStream.copyTo(out: OutputStream, listener: CopyListener): Long =
        IOx.copyTo(this, out, listener)


/**
 * Copies this reader to the given [out] writer, returning the number of characters copied.
 * **Note** it is the caller's responsibility to close both of these resources.
 *
 * @param out        writer to write to.
 * @param bufferSize size of character buffer to use in process.
 * @return number of characters copied.
 */
@Throws(IOException::class)
inline fun Reader.copyTo(out: Writer, bufferSize: Int, listener: CopyListener): Long =
        IOx.copyTo(this, out, bufferSize, listener)


/**
 * Copies this reader to the given [out] writer, returning the number of characters copied.
 * **Note** it is the caller's responsibility to close both of these resources.
 *
 * @param out        writer to write to.
 * @return number of characters copied.
 */
@Throws(IOException::class)
inline fun Reader.copyTo(out: Writer, listener: CopyListener): Long =
        IOx.copyTo(this, out, listener)