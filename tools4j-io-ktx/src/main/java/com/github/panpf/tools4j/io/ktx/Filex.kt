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

import com.github.panpf.tools4j.io.Filex
import com.github.panpf.tools4j.io.FilePathComponents
import java.io.*


/*
 * File related extension methods or properties
 */


/* ******************************************* mkdirs and create *******************************************/


/**
 * Create a directory and throw an exception if it cannot be created
 * @throws IOException Can't create directory
 */
@Throws(IOException::class)
inline fun File.mkdirsOrThrow(): File = Filex.mkdirsOrThrow(this)

/**
 * Create a directory
 * @return If true, the creation is successful.
 */
inline fun File.mkdirsOrCheck(): Boolean = Filex.mkdirsOrCheck(this)

/**
 * Create a file, create its parent directory first, and throw an exception if it cannot be created
 * @throws IOException Can't create file or parent directory
 */
@Throws(IOException::class)
inline fun File.createNewFileOrThrow(): File = Filex.createNewFileOrThrow(this)

/**
 * Create a file, create its parent directory first
 * @return If true, the creation is successful.
 */
inline fun File.createNewFileOrCheck(): Boolean = Filex.createNewFileOrCheck(this)

/**
 * If the specified file already exists, add a number to the file name to return the new file.
 *
 * @param allExtensionMode If true, for example logo.9.png will return logo(1).9.png, otherwise return to logo.9(1).png
 * @param maxNumber        If the appended number exceeds this limit, it will throw an exception, Cannot be less than 1
 * @return Certainly non-existent files
 */
inline fun File.ensureFileNotExist(allExtensionMode: Boolean, maxNumber: Int): File = Filex.ensureFileNotExist(this, allExtensionMode, maxNumber)

/**
 * If the specified file already exists, add a number to the file name to return the new file.
 *
 * @param maxNumber        If the appended number exceeds this limit, it will throw an exception, Cannot be less than 1
 * @return Certainly non-existent files
 */
inline fun File.ensureFileNotExist(maxNumber: Int): File = Filex.ensureFileNotExist(this, maxNumber)

/**
 * If the specified file already exists, add a number to the file name to return the new file.
 *
 * @param allExtensionMode If true, for example logo.9.png will return logo(1).9.png, otherwise return to logo.9(1).png
 * @return Certainly non-existent files
 */
inline fun File.ensureFileNotExist(allExtensionMode: Boolean): File = Filex.ensureFileNotExist(this, allExtensionMode)

/**
 * If the specified file already exists, add a number to the file name to return the new file.
 *
 * @return Certainly non-existent files
 */
inline fun File.ensureFileNotExist(): File = Filex.ensureFileNotExist(this)

/**
 * If the specified dir already exists, add a number to the file name to return the new dir.
 *
 * @param maxNumber If the appended number exceeds this limit, it will throw an exception, Cannot be less than 1
 * @return Certainly non-existent dir
 */
inline fun File.ensureDirNotExist(maxNumber: Int): File = Filex.ensureDirNotExist(this, maxNumber)

/**
 * If the specified dir already exists, add a number to the file name to return the new dir.
 *
 * @return Certainly non-existent dir
 */
inline fun File.ensureDirNotExist(): File = Filex.ensureDirNotExist(this)


/* ******************************************* clean *******************************************/


/**
 * Delete all subfiles in the current directory (excluding subfolders)
 *
 * @return If true, the clean is successful, otherwise the clean fails.
 */
inline fun File.clean(): Boolean = Filex.clean(this)


/**
 * Loop delete all files in the directory
 * @return If true, the clean is successful, otherwise the clean fails.
 */
inline fun File.cleanRecursively(): Boolean = Filex.cleanRecursively(this)


/* ******************************************* length *******************************************/


/**
 * Get the length of the file or dir, if it is a directory, it will superimpose the length of all subfiles
 */
inline fun File.lengthRecursively(): Long = Filex.lengthRecursively(this)

/**
 * Get the length of the files or dirs, if it is a directory, it will superimpose the length of all subfiles
 */
inline fun Array<File>.lengthRecursively(): Long = Filex.lengthRecursively(this)

/**
 * Get the length of the files or dirs, if it is a directory, it will superimpose the length of all subfiles
 */
inline fun Iterable<File>.lengthRecursively(): Long = Filex.lengthRecursively(this)


/* ******************************************* list *******************************************/


/**
 * Return the path to the file under this directory and all its subdirectories
 */
inline fun File.listRecursively(fileFilter: FileFilter): Array<String>? = Filex.listRecursively(this, fileFilter)

/**
 * Return the path to the file under this directory and all its subdirectories
 */
inline fun File.listRecursively(filenameFilter: FilenameFilter): Array<String>? = Filex.listRecursively(this, filenameFilter)

/**
 * Return the path to the file under this directory and all its subdirectories
 */
inline fun File.listRecursively(): Array<String>? = Filex.listRecursively(this)

/**
 * Return files in this directory and all its subdirectories
 */
inline fun File.listFilesRecursively(fileFilter: FileFilter): Array<File>? = Filex.listFilesRecursively(this, fileFilter)

/**
 * Return files in this directory and all its subdirectories
 */
inline fun File.listFilesRecursively(filenameFilter: FilenameFilter): Array<File>? = Filex.listFilesRecursively(this, filenameFilter)

/**
 * Return files in this directory and all its subdirectories
 */
inline fun File.listFilesRecursively(): Array<File>? = Filex.listFilesRecursively(this)


/* ******************************************* listCount ****************************************** */


/**
 * Returns the number of files in the specified folder, excluding subfiles and subfolders
 */
inline fun File.listCount(fileFilter: FileFilter): Int = Filex.listCount(this, fileFilter)

/**
 * Returns the number of files in the specified folder, excluding subfiles and subfolders
 */
inline fun File.listCount(filenameFilter: FilenameFilter): Int = Filex.listCount(this, filenameFilter)

/**
 * Returns the number of files in the specified folder, excluding subfiles and subfolders
 */
inline fun File.listCount(): Int = Filex.listCount(this)

/**
 * Returns the number of files in the specified directory and all its subdirectories
 */
inline fun File.listCountRecursively(fileFilter: FileFilter): Int = Filex.listCountRecursively(this, fileFilter)

/**
 * Returns the number of files in the specified directory and all its subdirectories
 */
inline fun File.listCountRecursively(filenameFilter: FilenameFilter): Int = Filex.listCountRecursively(this, filenameFilter)

/**
 * Returns the number of files in the specified directory and all its subdirectories
 */
inline fun File.listCountRecursively(): Int = Filex.listCountRecursively(this)


/* ******************************************* other ****************************************** */

/**
 * Returns all extensions for this file, or an empty string if none. For example: '/tmp/testExtension.txt.zip' returns 'txt.zip'
 */
val File.allExtension: String
    get() = Filex.getAllExtension(this)

/**
 * Returns file's name without an all extension. For example: '/tmp/testExtension.txt.zip' returns 'testExtension'
 */
val File.nameWithoutAllExtension: String
    get() = Filex.getNameWithoutAllExtension(this)

/**
 * Create a file tree
 *
 * @receiver         Start directory
 * @param maxSpan     Max span. Decide how many files or directories are in the same directory
 * @param maxDepth    Max depth
 * @param fileName    File name. For example 'test.txt', 'test1.txt', 'test2.txt'... will be created in the folder...
 * @param fileContent The content to be written to the file
 * @return Start directory
 */
@Throws(IOException::class)
inline fun File.createFileTree(maxSpan: Int, maxDepth: Int, fileName: String, fileContent: String?): File = Filex.createFileTree(this, maxSpan, maxDepth, fileName, fileContent)

/**
 * Compare file paths, commonly used to sort, for example '/a/b/c' is greater than '/a/b' is greater than '/a/c'
 */
inline fun String?.compareFilePath(filePath2: String?): Int = Filex.compareFilePath(this, filePath2)

/**
 * Compare file paths, commonly used to sort, for example '/a/b/c' is greater than '/a/b' is greater than '/a/c'
 */
inline fun File?.compareFilePath(file2: File?): Int = Filex.compareFilePath(this, file2)


/* ******************************************* fileSize *******************************************/


/**
 * Returns the formatted file length that can be displayed, up to EB
 *
 * @param decimalPlacesLength   Keep a few decimal places
 * @param decimalPlacesFillZero Use 0 instead when the number of decimal places is insufficient
 * @param compact               If true, returns 150KB, otherwise returns 150 KB
 * @return For example: 300 B, 150.25 KB, 500.46 MB, 300 GB
 */
inline fun Long.formatFileSize(decimalPlacesLength: Int = 2, decimalPlacesFillZero: Boolean = false, compact: Boolean = false): String = Filex.formatFileSize(this, decimalPlacesLength, decimalPlacesFillZero, compact)

/**
 * Returns the formatted file length that can be displayed, up to EB
 *
 * @param decimalPlacesFillZero Use 0 instead when the number of decimal places is insufficient
 * @return For example: 300 B, 150.25 KB, 500.46 MB, 300 GB
 */
inline fun Long.formatFileSize(decimalPlacesFillZero: Boolean = false): String = Filex.formatFileSize(this, decimalPlacesFillZero)

/**
 * Returns the length of the formatted file that can be displayed. The default is 1 decimal place. The maximum support is EB.
 *
 * @param decimalPlacesFillZero Use 0 instead when the number of decimal places is insufficient
 * @return For example: 300 B, 150.3 KB, 500.5 MB, 300 GB
 */
inline fun Long.formatMediumFileSize(decimalPlacesFillZero: Boolean = false): String = Filex.formatMediumFileSize(this, decimalPlacesFillZero)

/**
 * Returns the length of the formatted file that can be displayed. The default is not to retain decimals. The maximum support is EB.
 *
 * @return For example: 300 B, 150 KB, 500 MB, 300 GB
 */
inline fun Long.formatShortFileSize(): String = Filex.formatShortFileSize(this)


/**
 * Returns the compact formatted file length that can be displayed, up to EB
 *
 * @param decimalPlacesLength   Keep a few decimal places
 * @param decimalPlacesFillZero Use 0 instead when the number of decimal places is insufficient
 * @return For example: 300B, 150KB, 500MB, 300GB
 */
inline fun Long.formatCompactFileSize(decimalPlacesLength: Int = 2, decimalPlacesFillZero: Boolean = false): String = Filex.formatCompactFileSize(this, decimalPlacesLength, decimalPlacesFillZero)

/**
 * Returns the compact formatted file length that can be displayed, up to EB
 *
 * @param decimalPlacesFillZero Use 0 instead when the number of decimal places is insufficient
 * @return For example: 300B, 150KB, 500MB, 300GB
 */
inline fun Long.formatCompactFileSize(decimalPlacesFillZero: Boolean = false): String = Filex.formatCompactFileSize(this, decimalPlacesFillZero)

/**
 * Returns the length of the compact formatted file that can be displayed. The default is 1 decimal place. The maximum support is EB.
 *
 * @param decimalPlacesFillZero Use 0 instead when the number of decimal places is insufficient
 * @return For example: 300B, 150KB, 500MB, 300GB
 */
inline fun Long.formatMediumCompactFileSize(decimalPlacesFillZero: Boolean = false): String = Filex.formatMediumCompactFileSize(this, decimalPlacesFillZero)

/**
 * Returns the length of the compact formatted file that can be displayed. The default is not to retain decimals. The maximum support is EB.
 *
 * @return For example: 300B, 150KB, 500MB, 300GB
 */
inline fun Long.formatShortCompactFileSize(): String = Filex.formatShortCompactFileSize(this)


/* ******************************************* require *******************************************/


/**
 * If the given file exist, it returns itself, otherwise it throws an FileNotFoundException
 */
@Throws(FileNotFoundException::class)
inline fun File.requireExist(paramName: String = "unknown"): File = Filex.requireExist(this, paramName)

/**
 * If the given file is a directory, it returns itself, otherwise it throws an IllegalArgumentException
 */
inline fun File.requireIsDir(paramName: String = "unknown"): File = Filex.requireIsDir(this, paramName)

/**
 * If the given file is a file, it returns itself, otherwise it throws an IllegalArgumentException
 */
inline fun File.requireIsFile(paramName: String = "unknown"): File = Filex.requireIsFile(this, paramName)


/*
 * *****************************************************************************************************************
 * From kotlin standard library
 * *****************************************************************************************************************
 */


/* ******************************************* components ****************************************** */


/**
 * Splits the file into path components (the names of containing directories and the name of the file
 * itself) and returns the resulting collection of components.
 */
inline fun File.toComponents(): FilePathComponents = Filex.toComponents(this)


/* ******************************************* path ****************************************** */


/**
 * Returns a relative pathname which is a subsequence of this pathname,
 * beginning from component [beginIndex], inclusive,
 * ending at component [endIndex], exclusive.
 * Number 0 belongs to a component closest to the root,
 * number count-1 belongs to a component farthest from the root.
 *
 * @throws IllegalArgumentException if [beginIndex] is negative,
 * or [endIndex] is greater than existing number of components,
 * or [beginIndex] is greater than [endIndex].
 */
inline fun File.subPath(beginIndex: Int, endIndex: Int): File = Filex.subPath(this, beginIndex, endIndex)


/* ******************************************* inputStream ****************************************** */


/**
 * Returns a new [BufferedInputStream] for reading the content of this file.
 *
 * @param bufferSize necessary size of the buffer.
 */
@Throws(FileNotFoundException::class)
inline fun File.bufferedInputStream(bufferSize: Int): BufferedInputStream = Filex.bufferedInputStream(this, bufferSize)

/**
 * Returns a new [BufferedInputStream] for reading the content of this file.
 */
@Throws(FileNotFoundException::class)
inline fun File.bufferedInputStream(): BufferedInputStream = Filex.bufferedInputStream(this)


/* ******************************************* outputStream ****************************************** */


/**
 * Returns a new [BufferedOutputStream] for writing the content of this file.
 *
 * @param bufferSize necessary size of the buffer.
 */
@Throws(FileNotFoundException::class)
inline fun File.bufferedOutputStream(bufferSize: Int): BufferedOutputStream = Filex.bufferedOutputStream(this, bufferSize)

/**
 * Returns a new [BufferedOutputStream] for writing the content of this file.
 */
@Throws(FileNotFoundException::class)
inline fun File.bufferedOutputStream(): BufferedOutputStream = Filex.bufferedOutputStream(this)