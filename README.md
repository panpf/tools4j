# Tools4j

[![Platform][platform_java_icon]][platform_java_link]
[![Platform][platform_kotlin_icon]][platform_kotlin_link]
![SourceCompatibility][source_compatibility_icon]
![TargetCompatibility][target_compatibility_icon]
![KotlinJvmTarget][kotlin_jvmtarget_icon]
[![License][license_icon]][license_link]

Extensions to the Java standard library, some tool methods related to File, IO, primitive types, String, Array, and Collection

## Warning

`Currently in the development stage, some APIs have not been tested yet, please use them carefully`

## Getting Started

Add the following maven repository to your project `build.gradle` file:
```groovy
allprojects {
    repositories {
        maven { url "https://dl.bintray.com/panpf/maven/" }
    }
}
```

Add the following dependencies to your module `build.gradle` file ：
```grovvy
implementation "com.github.panpf.tools4j:tools4j:$LAST_VERSION"
implementation "com.github.panpf.tools4j:tools4j-ktx:$LAST_VERSION" // Kotlin extension, not required
```

Please replace `$LAST_VERSION` with the latest version: [![Download][version_icon]][version_link]

The "com.github.panpf.tools4j:tools4j:$LAST_VERSION" dependency will add all the modules included in tools4j to your project. If you only need of one of the modules, you can just add it to your project, all supported modules as follows:
```groovy
implementation "com.github.panpf.tools4j:tools4j-annotation:$LAST_VERSION"
implementation "com.github.panpf.tools4j:tools4j-annotation-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4j:tools4j-base64:$LAST_VERSION"
implementation "com.github.panpf.tools4j:tools4j-base64-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4j:tools4j-collections:$LAST_VERSION"
implementation "com.github.panpf.tools4j:tools4j-collections-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4j:tools4j-compare:$LAST_VERSION"

implementation "com.github.panpf.tools4j:tools4j-crypto:$LAST_VERSION"
implementation "com.github.panpf.tools4j:tools4j-crypto-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4j:tools4j-date:$LAST_VERSION"
implementation "com.github.panpf.tools4j:tools4j-date-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4j:tools4j-grouping:$LAST_VERSION"

implementation "com.github.panpf.tools4j:tools4j-io:$LAST_VERSION"
implementation "com.github.panpf.tools4j:tools4j-io-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4j:tools4j-lang:$LAST_VERSION"
implementation "com.github.panpf.tools4j:tools4j-lang-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4j:tools4j-math:$LAST_VERSION"
implementation "com.github.panpf.tools4j:tools4j-math-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4j:tools4j-net:$LAST_VERSION"

implementation "com.github.panpf.tools4j:tools4j-premise:$LAST_VERSION"
implementation "com.github.panpf.tools4j:tools4j-premise-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4j:tools4j-ranges:$LAST_VERSION"
implementation "com.github.panpf.tools4j:tools4j-ranges-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4j:tools4j-ranges-date:$LAST_VERSION"
implementation "com.github.panpf.tools4j:tools4j-ranges-date-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4j:tools4j-reflect:$LAST_VERSION"
implementation "com.github.panpf.tools4j:tools4j-reflect-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4j:tools4j-regex:$LAST_VERSION"
implementation "com.github.panpf.tools4j:tools4j-regex-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4j:tools4j-security:$LAST_VERSION"
implementation "com.github.panpf.tools4j:tools4j-security-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4j:tools4j-sequences:$LAST_VERSION"

implementation "com.github.panpf.tools4j:tools4j-zip:$LAST_VERSION"
implementation "com.github.panpf.tools4j:tools4j-zip-ktx:$LAST_VERSION" // Kotlin extension, not required
```

Dependencies：
* org.jetbrains:annotations: 13.0
* org.jetbrains.kotlin:kotlin-stdlib-jdk7: 1.4.0（Only the '\*-ktx' library dependencies it）

## Modules

`Representatives with the (Test) tag have passed the test and can be used with confidence.`

### tools4j-annotation
* Annotationx: [Annotationx.java] ([Test][AnnotationxTest.kt]) | [Annotationx.kt] ([Test][AnnotationxTest.kt])
    * getAnnotationFromEnum

### tools4j-base64
* Base64x: [Base64x.java] ([Test][Base64xTest.kt]) | [Base64x.kt] ([Test][Base64xTest.kt])
    * encode, encodeToString, decode, decodeToString

### tools4j-collections
* Arrayx: [Arrayx.java] | [Arrayx.kt]
    * isNullOrEmpty, isNotNullOrEmpty, joinToArrayString, arrayOf,
    * isEmpty, isNotEmpty, orEmpty, toTypedArray, toCollection,
    * toList, toSet, toHashSet, asList, map, mapTo, mapIndexed,
    * mapIndexedTo, mapNotNull, mapNotNullTo, mapIndexedNotNull,
    * reverse, reversed, sort, sortDescending, sortWith, sortBy,
    * sortByDescending, sorted, sortedDescending, sortedWith,
    * sortedBy, sortedByDescending, sortedArray, sortedArrayDescending,
    * sortedArrayWith, single, singleOrNull, any, all, iterator,
    * asIterable, groupBy, groupByTo, count, max, maxBy, maxWith,
    * min, minBy, minWith, joinTo, joinToString, sum, sumBy,
    * sumByDouble, plus, plusElement, indices, indexOf, indexOfFirst
    * lastIndexOf, contains, forEach, forEachIndexed, filter,
    * filterIndexed, filterIndexedTo, filterIsInstance,
    * filterIsInstanceTo, filterNot, filterNotNullTo, filterNotTo,
    * filterTo, zip, partition, first, firstOrNull, last, lastOrNull
    * find, findLast, getOrElse, getOrNull, elementAt, elementAtOrElse,
    * elementAtOrNull, flatMap, flatMapTo, averageOf average, none,
    * reduce, reduceIndexed, reduceRight, reduceRightIndexed, fold,
    * foldIndexed, foldRight, foldRightIndexed, intersect, subtract,
    * drop, dropLast, dropLastWhile, dropWhile, take, takeLast,
    * takeLastWhile, takeWhile, distinct, distinctBy, withIndex
* Arrayx2: [Arrayx2.java]
    * contentHashCode, contentDeepEquals, contentDeepHashCode,
    * contentDeepToString, contentToString, contentEquals,
    * binarySearch, copyOf, copyOfRange, slice, sliceArray,
    * associate, associateBy, associateByTo, associateTo
* Collectionx: [Collectionx.java] | [Collectionx.kt]
    * isNullOrEmpty, isNotNullOrEmpty, joinToArrayString, linkedListOf,
    * collectionSizeOrDefault, isEmpty, isNotEmpty, orEmpty,
    * immutableListOf, mutableListOf, arrayListOf, immutableSetOf,
    * mutableSetOf, hashSetOf, linkedSetOf, sortedSetOf, filterTo,
    * filterNotTo, filterNotNullTo, filterIndexedTo, filterIsInstanceTo,
    * filter, filterNot, filterNotNull, filterIndexed, filterIsInstance,
    * filterInPlace, mapTo, mapIndexedTo, mapIndexed, mapNotNullTo,
    * mapNotNull, mapIndexedNotNullTo, mapIndexedNotNull, 
    * flatMap, flatMapTo, flatMapIndexed, flatMapIndexedTo, 
    * joinTo, joinToString, count, averageOf, sumOf, sumBy,
    * first, firstOrNull, last, lastOrNull, find, findLast, getOrElse,
    * getOrNull, maxOf, max, maxBy, maxWith, minOf, min, minBy, minWith,
    * addAll, removeAll, partition, toCollection, toList, toSet,
    * toSortedSet, toHashSet, safeToConvertToSet,
    * convertToSetForSetOperationWith, convertToSetForSetOperation,
    * union, all, any, contains, forEach, forEachIndexed, chunked, minus,
    * minusElement, plus, plusElement, indices, groupByTo, groupBy,
    * sort, sortWith, sortBy, sortedDescending, sortByDescending,
    * sortedWith, sortedBy, sorted, sortedByDescending, reverse,
    * indexOf, indexOfFirst, indexOfLast, lastIndexOf, fold, foldIndexed,
    * foldRight, foldRightIndexed, reduce, reduceIndexed, reduceRight,
    * reduceRightIndexed, slice, take, takeLast, takeLastWhile, takeWhile,
    * distinct, distinctBy, intersect, retainAll, associateTo, associate,
    * associateByTo, associateBy, drop, dropLast, dropLastWhile, dropWhile,
    * single, singleOrNull, elementAtOrElse, elementAt, elementAtOrNull,
    * fill, shuffle, shuffled, zip, zipWithNext, iterator, to\*Array
* Mapx: [Mapx.java] ([Test][MapxTest.kt]) | [Mapx.kt] ([Test][MapxTest.kt])
    * builder, isNullOrEmpty, isNotNullOrEmpty, immutableMapOf, mutableMapOf,
    * hashMapOf, linkedMapOf, sortedMapOf, isEmpty, isNotEmpty, orEmpty,
    * putAll, plus, plusAssign, remove, minus, minusAssign, set,
    * get, getOrElse, getValue, getOrPut, contains, containsKey,
    * containsValue, all, any, count, forEach, onEach, maxBy, maxWith,
    * minBy, minWith, none, filterKeys, filterValues, filterTo, filter,
    * filterNotTo, filterNot, iterator, toPair, toMap, toList, asIterable,
    * mapValuesTo, mapValues, mapKeysTo, mapKeys, mapTo, map, mapNotNullTo,
    * mapNotNull, flatMapTo, flatMap, capacity

### tools4j-compare
* Comparex: [Comparex.java] ([Test][ComparexTest.kt])
    * areEqual, compareValues, compareValuesBy, compareBy,
    * compareByDescending, naturalOrder, reverseOrder, maxOf, minOf

### tools4j-crypto
* Aesx: [Aesx.java] ([Test][AesxTest.kt]) | [Aesx.kt] ([Test][AesxTest.kt])
    * createKey, createKeyByPassword, keyFromBytes, keyFromBase64, encrypt,
    * encryptToBase64, decrypt, decryptFromBase64, decryptToString,
    * decryptToStringFromBase64
* Desx: [Desx.java] ([Test][DesxTest.kt]) | [Desx.kt] ([Test][DesxTest.kt])
    * createKeyByPassword, encrypt, encryptToBase64,
    * decrypt, decryptFromBase64, decryptToString, decryptToStringFromBase64
* Keyx: [Keyx.java] ([Test][KeyxTest.kt]) | [Keyx.kt] ([Test][KeyxTest.kt])
    * toBase64, toBytes
* Rsax: [Rsax.java] ([Test][RsaxTest.kt]) | [Rsax.kt] ([Test][RsaxTest.kt])
    * createKey, pubKeyFromBase64, priKeyFromBase64, sign, signToBase64,
    * verify, verifyFromBase64, encrypt, encryptToBase64, decrypt,
    * decryptFromBase64, decryptToString, decryptToStringFromBase64

### tools4j-date
* Datex: [Datex.java] ([Test][DatexTest.kt]) | [Datex.kt] ([Test][DatexTest.kt])
    * createCalendar, toDate, format, formatTimeLength, getCalendarField,
    * addToDate, addCalendarField, isSame\[Year, Month...\], differField,

### tools4j-grouping
* Groupingx: [Groupingx.java] ([Test][GroupingxTest.kt])
    * groupingBy, aggregate, aggregateTo, fold, foldTo, reduce, reduceTo,
    * eachCount, eachCountTo

### tools4j-io
* Filex: [Filex.java] ([Test][FilexTest.kt]) | [Filex.kt] ([Test][FilexTest.kt])
    * mkdirsOrThrow, mkdirsOrCheck, createNewFileOrThrow, createNewFileOrCheck,
    * ensureFileNotExist, ensureDirNotExist, clean, cleanRecursively,
    * lengthRecursively, listRecursively, listFilesRecursively, listCount,
    * listCountRecursively, getAllExtension, getNameWithoutAllExtension,
    * createFileTree, compareFilePath, formatFileSize, formatMediumFileSize
    * formatShortFileSize, formatCompactFileSize, formatMediumCompactFileSize
    * formatShortCompactFileSize, requireExist, requireIsDir, requireIsFile,
    * copyTo, copyRecursively, deleteRecursively, startsWith, endsWith,
    * normalize, resolve, resolveSibling, createTempDir, createTempFile,
    * getExtension, getNameWithoutExtension, toRelativeString, relativeTo,
    * relativeToOrSelf, relativeToOrNull, isRooted, toComponents,
    * getInvariantSeparatorsPath, subPath, inputStream, bufferedInputStream,
    * reader, bufferedReader, readBytes, readText, readLines, useLines,
    * forEachBlock, forEachLine, outputStream, bufferedOutputStream, writer,
    * bufferedWriter, printWriter, writeBytes, appendBytes, writeText,
    * appendText, walk, walkTopDown, walkBottomUp
* IOx: [IOx.java] ([Test][IOxTest.kt]) | [IOx.kt] ([Test][IOxTest.kt])
    * closeQuietly, readBytesAndClose, readTextAndClose, writeByteAndClose,
    * writeBytesAndClose, writeTextAndClose, writeCharAndClose,
    * writeCharsAndClose, copyTo, inputStream, byteInputStream,
    * reader, writer, buffered, bufferedReader, bufferedWriter,
    * readLines, readBytes, readText, lineIterable, useLines, forEachLine

### tools4j-lang
* Booleanx: [Booleanx.java] ([Test][BooleanxTest.kt]) | [Booleanx.kt] ([Test][BooleanxTest.kt])
    * isTrue, isFalse, isNullOrTrue, isNullOrFalse
* Charx: [Charx.java] ([Test][CharxTest.kt]) | [Charx.kt] ([Test][CharxTest.kt])
    * isBlank, isNotBlank, notBlankOr, isChinese, isNotChinese, chineseOr,
    * isNotDigit, digitOr, isNotLetter, letterOr, isNotLetterOrDigit,
    * letterOrDigitOr, isDigit, isLetter, isLetterOrDigit, equals
* Numberx: [Numberx.java] ([Test][NumberxTest.kt]) | [Numberx.kt] ([Test][NumberxTest.kt])
    * requireNotZero, pad, orZero, to\*OrDefault, to\*OrZero, toHexString,
    * toHexStringOr, toHexStringOrNull, toBinaryString, toBinaryStringOr,
    * toBinaryStringOrNull, toOctalString, toOctalStringOr, toOctalStringOrNull
* Objectx: [Objectx.java] ([Test][ObjectxTest.kt]) | [Objectx.kt] ([Test][ObjectxTest.kt])
    * toSimpleString
* Stringx: [Stringx.java] ([Test][StringxTest.kt]) | [Stringx.kt] ([Test][StringxTest.kt])
    * isSafe, isNotSafe, safeOr, safeOrNull, requireSafe, requireNotSafe,
    * notBlankOr, isNotNullOrBlank, notNullOrBlankOr, notEmptyOr,
    * isNotNullOrEmpty, notNullOrEmptyOr, isChinese, isNotChinese, chineseOr,
    * isDigit, isNotDigit, digitOr, isLetter, isNotLetter, letterOr,
    * isLetterOrDigit, isNotLetterOrDigit, letterOrDigitOr, containsAny,
    * containsAll, orEmpty, orDefault, emptyToNull, blankToNull,
    * filterBlank, removeChar, removeFirstChar, removeLastChar, removeIndex,
    * limit, replaceNoRepeat, firstLetterUpperCase, firstLetterUpperCase
    * hiddenStartChars, hiddenMiddleChars, hiddenEndChars,
    * isBlank, isNotBlank, isNullOrBlank, isEmpty, isNotEmpty, isNullOrEmpty,
    * indices, lastIndex, hasSurrogatePairAt, startsWith, endsWith, equals,
    * removeRange, removePrefix, removeSuffix, removeSurrounding,
    * capitalize, decapitalize, padStart, padEnd, matches, regionMatches,
    * regionMatchesImpl, find, findLast, findAnyOf, findLastAnyOf,
    * first, firstOrNull, last, lastOrNull, getOrElse, getOrNull, indexOf,
    * indexOfAny, indexOfFirst, indexOfLast, lastIndexOf, lastIndexOfAny,
    * subSequence, substring, substringBefore, substringAfter,
    * substringBeforeLast, substringAfterLast, toByteArray, reversed,
    * filterTo, filter, filterIndexedTo, filterIndexed, filterNotTo, filterNot,
    * trim, trimStart, trimEnd, iterator, iterable, replaceRange,
    * replaceBefore, replaceBeforeLast, replaceAfter, replaceAfterLast,
    * replace, replaceFirst, commonPrefixWith, commonSuffixWith, contains,
    * splitToIterable, split, lineIterable, lines, elementAt, elementAtOrElse,
    * elementAtOrNull, single, singleOrNull, drop, dropLast, dropLastWhile,
    * dropWhile, slice, take, takeLast, takeLastWhile, takeWhile,
    * associateTo, associate, associateByTo, associateBy,
    * toCollection, toHashSet, toList, toSet, toSortedSet,
    * flatMap, flatMapTo, groupBy, groupByTo, mapTo, map, mapIndexedTo,
    * mapIndexed, mapNotNullTo, mapNotNull, mapIndexedNotNullTo,
    * mapIndexedNotNull, withIndex, all, any, count, fold, foldIndexed,
    * foldRight, foldRightIndexed, forEach, forEachIndexed, max, maxBy, maxWith,
    * min, minBy, minWith, none, onEach, reduce, reduceIndexed, reduceRight,
    * reduceRightIndexed, sumBy, sumByDouble, chunked, chunkedIterable,
    * partition, windowed, windowedIterable, zip, zipWithNext, asIterable
* Throwablex: [Throwablex.java] ([Test][ThrowablexTest.kt]) | [Throwablex.kt] ([Test][ThrowablexTest.kt])
    * stackTraceToString

### tools4j-math
* Mathx: [Mathx.java] ([Test][MathxTest.kt]) | [Mathx.kt] ([Test][MathxTest.kt])
    * divide, divideToInt, divideToLong, scale, proportion, percent,
    * format, formatPercentWith

### tools4j-net
* Netx: [Netx.java] ([Test][NetxTest.kt])
    * isIPV4, isIPV6, isMacAddress, getLocalIPAddress, getLocalIPV4Address,
    * getExternalIPV4AddressFrom, getExternalIPV4AddressFromSohu,
    * getExternalIPV4Address, getCityFromSohu, getCity, ipStringToLong,
    * ipLongToString, matchMimeType, guessFileName,
    * guessFileNameFromContentDisposition, guessFileNameFromUrl

### tools4j-premise
* Premisex: [Premisex.java] ([Test][PremisexTest.kt]) | [Premisex.kt] ([Test][PremisexTest.kt])
    * require, check, requireNotNull, checkNotNull

### tools4j-ranges
* Rangex: [Rangex.java] ([Test][RangexTest.kt]) | [Rangex.kt] ([Test][RangexTest.kt])
    * rangeTo, until, downTo, requireInRange, requireNotInRange, in, notIn,
    * reversed, step, coerceAtLeast, coerceAtMost, coerceIn

### tools4j-ranges-date
* DateRangex: [DateRangex.java] ([Test][DateRangexTest.kt]) | [DateRangex.kt] ([Test][DateRangexTest.kt])
    * \[Year, Month...\]RangeTo, \[Year, Month...\]Until,
    * \[Year, Month...\]DownTo, reversed, step

### tools4j-reflect
* Reflectx: [Reflectx.java] ([Test][ReflectxTest.kt]) | [Reflectx.kt] ([Test][ReflectxTest.kt])
    * getDeclaredFieldRecursive, getDeclaredFieldsRecursive, getFieldValue,
    * getStaticFieldValue, setFieldValue, setStaticFieldValue,
    * getDeclaredMethodRecursive, getDeclaredMethodsRecursive, callMethod,
    * callStaticMethod, getDeclaredConstructorRecursive,
    * getDeclaredConstructorsRecursive, getClassHierarchy,
    * isTypeArray, isTypeCollection

### tools4j-regex
* Regexx: [Regexx.java] ([Test][RegexxTest.kt]) | [Regexx.kt] ([Test][RegexxTest.kt])
    * matches, find, lookingAt, getFirst, getAll, firstGroup, allGroup,
    * replaceFirst, replaceAll, IPV4, IPV6, MAC_ADDRESS, CHINESE,
    * CHINESE_SYMBOL, BLANK, EMAIL, URI, FLOAT_NUMBER, INTEGER

### tools4j-resources
* ResourcesCacheHelper: [ResourcesCacheHelper.java] ([Test][ResourcesCacheHelperTest.kt])
    * getFile, getDir, getResourcesDir

### tools4j-run
* Runx: [Runx.java] ([Test][RunxTest.kt])
    * getWorkspaceDir, isClassInJar, getClassInDir, getClassInJarFile

### tools4j-security
* MessageDigestx: [MessageDigestx.java] ([Test][MessageDigestxTest.kt]) | [MessageDigestx.kt] ([Test][MessageDigestxTest.kt])
    * getDigest, getMD5, getMD5_16, getSHA1, getSHA256, getSHA512

### tools4j-sequences
* Sequencex: [Sequencex.java] | [Sequencex.kt]
    * joinToArrayString, asSequence, asIterable, sequenceOf, emptySequence,
    * constrainOnce, generateSequence, flatten, contains, elementAt,
    * elementAtOrElse, elementAtOrNull, find, findLast, first, firstOrNull,
    * indexOf, indexOfFirst, indexOfLast, last, lastIndexOf, lastOrNull,
    * single, singleOrNull, drop, dropWhile, filter, filterIndexed,
    * filterIndexedTo, filterIsInstance, filterIsInstanceTo, filterNot,
    * filterNotNull, filterNotNullTo, filterNotTo, filterTo, take, takeWhile,
    * sorted, sortedBy, sortedByDescending, sortedWith, associate,
    * associateBy, associateByTo, associateTo, toCollection, toMutableList,
    * toMutableSet, toHashSet, toSortedSet, toMap, flatMap, flatMapOfIterable, 
    * flatMapOfIterableTo, flatMapTo, flatMapIndexed, flatMapIndexedOfIterable, 
    * flatMapIndexedOfIterableTo, flatMapIndexedTo,
    * groupBy, groupByTo, map, withIndex, mapIndexedNotNull,
    * mapIndexedNotNullTo, mapIndexedTo, mapNotNull, mapNotNullTo, mapTo,
    * withIndex, distinct, distinctBy, all, any, count, fold, foldIndexed,
    * forEach, forEachIndexed, onEach, maxOrNull, maxByOrNull, maxWithOrNull, 
    * maxOf, maxOfOrNull, maxOfWith, maxOfWithOrNull, minOrNull, minByOrNull, 
    * minWithOrNull, minOf, minOfOrNull, minOfWith, minOfWithOrNull, none, reduce,
    * reduceIndexed, sumBy, sumByDouble, sumOf, requireNoNulls, minus,
    * minusElement, partition, plus, plusElement, zip, unzip,
    * joinTo, joinToString, averageOf

### tools4j-zip
* Zipx: [Zipx.java] ([Test][ZipxTest.kt]) | [Zipx.kt] ([Test][ZipxTest.kt])
    * compress, decompress, gzipCompress, gzipDecompress, compressFilesTo,
    * compressFilesTo, compressFileTo, compressFile, compressChildFileTo,
    * compressChildFile, decompressTo, decompress, getCompressDstFile,
    * getDecompressDstDir, getTrueSize, listEntry, listEntryName, size

## Change Log

Please view the [CHANGELOG.md] file


## License
    Copyright (C) 2020 panpf <panpfpanpf@outlook.com>

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


[platform_java_icon]: https://img.shields.io/badge/Platform-Java-red.svg
[platform_java_link]: https://www.java.com
[platform_kotlin_icon]: https://img.shields.io/badge/Platform-Kotlin-blue.svg
[platform_kotlin_link]: http://kotlinlang.org
[license_icon]: https://img.shields.io/badge/License-Apache%202-blue.svg
[license_link]: https://www.apache.org/licenses/LICENSE-2.0
[source_compatibility_icon]: https://img.shields.io/badge/SourceCompatibility-1.7-red.svg
[target_compatibility_icon]: https://img.shields.io/badge/TargetCompatibility-1.7-red.svg
[kotlin_jvmtarget_icon]: https://img.shields.io/badge/KotlinJvmTarget-1.6-red.svg
[version_icon]: https://api.bintray.com/packages/panpf/maven/tools4j/images/download.svg
[version_link]:https://bintray.com/panpf/maven/tools4j/_latestVersion

[CHANGELOG.md]: CHANGELOG.md

[Annotationx.java]: tools4j-annotation/src/main/java/com/github/panpf/tools4j/annotation/Annotationx.java
[AnnotationxTest.kt]: tools4j-annotation/src/test/java/com/github/panpf/tools4j/annotation/AnnotationxTest.kt
[Annotationx.kt]: tools4j-annotation-ktx/src/main/java/com/github/panpf/tools4j/annotation/ktx/Annotationx.kt
[AnnotationxTest.kt]: tools4j-annotation-ktx/src/test/java/com/github/panpf/tools4j/annotation/ktx/AnnotationxTest.kt

[Base64x.java]: tools4j-base64/src/main/java/com/github/panpf/tools4j/base64/Base64x.java
[Base64xTest.kt]: tools4j-base64/src/test/java/com/github/panpf/tools4j/base64/Base64xTest.kt
[Base64x.kt]: tools4j-base64-ktx/src/main/java/com/github/panpf/tools4j/base64/ktx/Base64x.kt
[Base64xTest.kt]: tools4j-base64-ktx/src/test/java/com/github/panpf/tools4j/base64/ktx/Base64xTest.kt

[Arrayx.java]: tools4j-collections/src/main/java/com/github/panpf/tools4j/collections/Arrayx.java
[ArrayxTest.kt]: tools4j-collections/src/test/java/com/github/panpf/tools4j/collections/ArrayxTest.kt
[Arrayx.kt]: tools4j-collections-ktx/src/main/java/com/github/panpf/tools4j/collections/ktx/Arrayx.kt
[ArrayxTest.kt]: tools4j-collections-ktx/src/test/java/com/github/panpf/tools4j/collections/ktx/ArrayxTest.kt

[Arrayx2.java]: tools4j-collections/src/main/java/com/github/panpf/tools4j/collections/Arrayx2.java
[Arrayx2Test.kt]: tools4j-collections/src/test/java/com/github/panpf/tools4j/collections/Arrayx2Test.kt

[Collectionx.java]: tools4j-collections/src/main/java/com/github/panpf/tools4j/collections/Collectionx.java
[CollectionxTest.kt]: tools4j-collections/src/test/java/com/github/panpf/tools4j/collections/CollectionxTest.kt
[Collectionx.kt]: tools4j-collections-ktx/src/main/java/com/github/panpf/tools4j/collections/ktx/Collectionx.kt
[CollectionxTest.kt]: tools4j-collections-ktx/src/test/java/com/github/panpf/tools4j/collections/ktx/CollectionxTest.kt

[Mapx.java]: tools4j-collections/src/main/java/com/github/panpf/tools4j/collections/Mapx.java
[MapxTest.kt]: tools4j-collections/src/test/java/com/github/panpf/tools4j/collections/MapxTest.kt
[Mapx.kt]: tools4j-collections-ktx/src/main/java/com/github/panpf/tools4j/collections/ktx/Mapx.kt
[MapxTest.kt]: tools4j-collections-ktx/src/test/java/com/github/panpf/tools4j/collections/ktx/MapxTest.kt

[Comparex.java]: tools4j-compare/src/main/java/com/github/panpf/tools4j/compare/Comparex.java
[ComparexTest.kt]: tools4j-compare/src/test/java/com/github/panpf/tools4j/compare/ComparexTest.kt

[Aesx.java]: tools4j-crypto/src/main/java/com/github/panpf/tools4j/crypto/Aesx.java
[AesxTest.kt]: tools4j-crypto/src/test/java/com/github/panpf/tools4j/crypto/AesxTest.kt
[Aesx.kt]: tools4j-crypto-ktx/src/main/java/com/github/panpf/tools4j/crypto/ktx/Aesx.kt
[AesxTest.kt]: tools4j-crypto-ktx/src/test/java/com/github/panpf/tools4j/crypto/ktx/AesxTest.kt

[Desx.java]: tools4j-crypto/src/main/java/com/github/panpf/tools4j/crypto/Desx.java
[DesxTest.kt]: tools4j-crypto/src/test/java/com/github/panpf/tools4j/crypto/DesxTest.kt
[Desx.kt]: tools4j-crypto-ktx/src/main/java/com/github/panpf/tools4j/crypto/ktx/Desx.kt
[DesxTest.kt]: tools4j-crypto-ktx/src/test/java/com/github/panpf/tools4j/crypto/ktx/DesxTest.kt

[Keyx.java]: tools4j-crypto/src/main/java/com/github/panpf/tools4j/crypto/Keyx.java
[KeyxTest.kt]: tools4j-crypto/src/test/java/com/github/panpf/tools4j/crypto/KeyxTest.kt
[Keyx.kt]: tools4j-crypto-ktx/src/main/java/com/github/panpf/tools4j/crypto/ktx/Keyx.kt
[KeyxTest.kt]: tools4j-crypto-ktx/src/test/java/com/github/panpf/tools4j/crypto/ktx/KeyxTest.kt

[Rsax.java]: tools4j-crypto/src/main/java/com/github/panpf/tools4j/crypto/Rsax.java
[RsaxTest.kt]: tools4j-crypto/src/test/java/com/github/panpf/tools4j/crypto/RsaxTest.kt
[Rsax.kt]: tools4j-crypto-ktx/src/main/java/com/github/panpf/tools4j/crypto/ktx/Rsax.kt
[RsaxTest.kt]: tools4j-crypto-ktx/src/test/java/com/github/panpf/tools4j/crypto/ktx/RsaxTest.kt

[Datex.java]: tools4j-date/src/main/java/com/github/panpf/tools4j/date/Datex.java
[DatexTest.kt]: tools4j-date/src/test/java/com/github/panpf/tools4j/date/DatexTest.kt
[Datex.kt]: tools4j-date-ktx/src/main/java/com/github/panpf/tools4j/date/ktx/Datex.kt
[DatexTest.kt]: tools4j-date-ktx/src/test/java/com/github/panpf/tools4j/date/ktx/DatexTest.kt

[Groupingx.java]: tools4j-grouping/src/main/java/com/github/panpf/tools4j/grouping/Groupingx.java
[GroupingxTest.kt]: tools4j-grouping/src/test/java/com/github/panpf/tools4j/grouping/GroupingxTest.kt

[Filex.java]: tools4j-io/src/main/java/com/github/panpf/tools4j/io/Filex.java
[FilexTest.kt]: tools4j-io/src/test/java/com/github/panpf/tools4j/io/FilexTest.kt
[Filex.kt]: tools4j-io-ktx/src/main/java/com/github/panpf/tools4j/io/ktx/Filex.kt
[FilexTest.kt]: tools4j-io-ktx/src/test/java/com/github/panpf/tools4j/io/ktx/FilexTest.kt

[IOx.java]: tools4j-io/src/main/java/com/github/panpf/tools4j/io/IOx.java
[IOxTest.kt]: tools4j-io/src/test/java/com/github/panpf/tools4j/io/IOxTest.kt
[IOx.kt]: tools4j-io-ktx/src/main/java/com/github/panpf/tools4j/io/ktx/IOx.kt
[IOxTest.kt]: tools4j-io-ktx/src/test/java/com/github/panpf/tools4j/io/ktx/IOxTest.kt

[Booleanx.java]: tools4j-lang/src/main/java/com/github/panpf/tools4j/lang/Booleanx.java
[BooleanxTest.kt]: tools4j-lang/src/test/java/com/github/panpf/tools4j/lang/BooleanxTest.kt
[Booleanx.kt]: tools4j-lang-ktx/src/main/java/com/github/panpf/tools4j/lang/ktx/Booleanx.kt
[BooleanxTest.kt]: tools4j-lang-ktx/src/test/java/com/github/panpf/tools4j/lang/ktx/BooleanxTest.kt

[Charx.java]: tools4j-lang/src/main/java/com/github/panpf/tools4j/lang/Charx.java
[CharxTest.kt]: tools4j-lang/src/test/java/com/github/panpf/tools4j/lang/CharxTest.kt
[Charx.kt]: tools4j-lang-ktx/src/main/java/com/github/panpf/tools4j/lang/ktx/Charx.kt
[CharxTest.kt]: tools4j-lang-ktx/src/test/java/com/github/panpf/tools4j/lang/ktx/CharxTest.kt

[Numberx.java]: tools4j-lang/src/main/java/com/github/panpf/tools4j/lang/Numberx.java
[NumberxTest.kt]: tools4j-lang/src/test/java/com/github/panpf/tools4j/lang/NumberxTest.kt
[Numberx.kt]: tools4j-lang-ktx/src/main/java/com/github/panpf/tools4j/lang/ktx/Numberx.kt
[NumberxTest.kt]: tools4j-lang-ktx/src/test/java/com/github/panpf/tools4j/lang/ktx/NumberxTest.kt

[Objectx.java]: tools4j-lang/src/main/java/com/github/panpf/tools4j/lang/Objectx.java
[ObjectxTest.kt]: tools4j-lang/src/test/java/com/github/panpf/tools4j/lang/ObjectxTest.kt
[Objectx.kt]: tools4j-lang-ktx/src/main/java/com/github/panpf/tools4j/lang/ktx/Objectx.kt
[ObjectxTest.kt]: tools4j-lang-ktx/src/test/java/com/github/panpf/tools4j/lang/ktx/ObjectxTest.kt

[Stringx.java]: tools4j-lang/src/main/java/com/github/panpf/tools4j/lang/Stringx.java
[StringxTest.kt]: tools4j-lang/src/test/java/com/github/panpf/tools4j/lang/StringxTest.kt
[Stringx.kt]: tools4j-lang-ktx/src/main/java/com/github/panpf/tools4j/lang/ktx/Stringx.kt
[StringxTest.kt]: tools4j-lang-ktx/src/test/java/com/github/panpf/tools4j/lang/ktx/StringxTest.kt

[Throwablex.java]: tools4j-lang/src/main/java/com/github/panpf/tools4j/lang/Throwablex.java
[ThrowablexTest.kt]: tools4j-lang/src/test/java/com/github/panpf/tools4j/lang/ThrowablexTest.kt
[Throwablex.kt]: tools4j-lang-ktx/src/main/java/com/github/panpf/tools4j/lang/ktx/Throwablex.kt
[ThrowablexTest.kt]: tools4j-lang-ktx/src/test/java/com/github/panpf/tools4j/lang/ktx/ThrowablexTest.kt

[Mathx.java]: tools4j-math/src/main/java/com/github/panpf/tools4j/math/Mathx.java
[MathxTest.kt]: tools4j-math/src/test/java/com/github/panpf/tools4j/math/MathxTest.kt
[Mathx.kt]: tools4j-math-ktx/src/main/java/com/github/panpf/tools4j/math/ktx/Mathx.kt
[MathxTest.kt]: tools4j-math-ktx/src/test/java/com/github/panpf/tools4j/math/ktx/MathxTest.kt

[Netx.java]: tools4j-net/src/main/java/com/github/panpf/tools4j/net/Netx.java
[NetxTest.kt]: tools4j-net/src/test/java/com/github/panpf/tools4j/net/NetxTest.kt

[Premisex.java]: tools4j-premise/src/main/java/com/github/panpf/tools4j/premise/Premisex.java
[PremisexTest.kt]: tools4j-premise/src/test/java/com/github/panpf/tools4j/premise/PremisexTest.kt
[Premisex.kt]: tools4j-premise-ktx/src/main/java/com/github/panpf/tools4j/premise/ktx/Premisex.kt
[PremisexTest.kt]: tools4j-premise-ktx/src/test/java/com/github/panpf/tools4j/premise/ktx/PremisexTest.kt

[Rangex.java]: tools4j-ranges/src/main/java/com/github/panpf/tools4j/ranges/Rangex.java
[RangexTest.kt]: tools4j-ranges/src/test/java/com/github/panpf/tools4j/ranges/RangexTest.kt
[Rangex.kt]: tools4j-ranges-ktx/src/main/java/com/github/panpf/tools4j/ranges/ktx/Rangex.kt
[RangexTest.kt]: tools4j-ranges-ktx/src/test/java/com/github/panpf/tools4j/ranges/ktx/RangexTest.kt

[DateRangex.java]: tools4j-ranges-date/src/main/java/com/github/panpf/tools4j/ranges/date/DateRangex.java
[DateRangexTest.kt]: tools4j-ranges-date/src/test/java/com/github/panpf/tools4j/ranges/date/DateRangexTest.kt
[DateRangex.kt]: tools4j-ranges-date-ktx/src/main/java/com/github/panpf/tools4j/ranges/date/ktx/DateRangex.kt
[DateRangexTest.kt]: tools4j-ranges-date-ktx/src/test/java/com/github/panpf/tools4j/ranges/date/ktx/DateRangexTest.kt

[Reflectx.java]: tools4j-reflect/src/main/java/com/github/panpf/tools4j/reflect/Reflectx.java
[ReflectxTest.kt]: tools4j-reflect/src/test/java/com/github/panpf/tools4j/reflect/ReflectxTest.kt
[Reflectx.kt]: tools4j-reflect-ktx/src/main/java/com/github/panpf/tools4j/reflect/ktx/Reflectx.kt
[ReflectxTest.kt]: tools4j-reflect-ktx/src/test/java/com/github/panpf/tools4j/reflect/ktx/ReflectxTest.kt

[Regexx.java]: tools4j-regex/src/main/java/com/github/panpf/tools4j/regex/Regexx.java
[RegexxTest.kt]: tools4j-regex/src/test/java/com/github/panpf/tools4j/regex/RegexxTest.kt
[Regexx.kt]: tools4j-regex-ktx/src/main/java/com/github/panpf/tools4j/regex/ktx/Regexx.kt
[RegexxTest.kt]: tools4j-regex-ktx/src/test/java/com/github/panpf/tools4j/regex/ktx/RegexxTest.kt

[ResourcesCacheHelper.java]: tools4j-resources/src/main/java/com/github/panpf/tools4j/resources/ResourcesCacheHelper.java
[ResourcesCacheHelperTest.kt]: tools4j-resources/src/test/java/com/github/panpf/tools4j/resources/ResourcesCacheHelperTest.kt

[Runx.java]: tools4j-run/src/main/java/com/github/panpf/tools4j/run/Runx.java
[RunxTest.kt]: tools4j-run/src/test/java/com/github/panpf/tools4j/run/RunxTest.kt

[MessageDigestx.java]: tools4j-security/src/main/java/com/github/panpf/tools4j/security/MessageDigestx.java
[MessageDigestxTest.kt]: tools4j-security/src/test/java/com/github/panpf/tools4j/security/MessageDigestxTest.kt
[MessageDigestx.kt]: tools4j-security-ktx/src/main/java/com/github/panpf/tools4j/security/ktx/MessageDigestx.kt
[MessageDigestxTest.kt]: tools4j-security-ktx/src/test/java/com/github/panpf/tools4j/security/ktx/MessageDigestxTest.kt

[Sequencex.java]: tools4j-sequences/src/main/java/com/github/panpf/tools4j/sequences/Sequencex.java
[SequencexTest.kt]: tools4j-sequences/src/test/java/com/github/panpf/tools4j/sequences/SequencexTest.kt

[Zipx.java]: tools4j-zip/src/main/java/com/github/panpf/tools4j/zip/Zipx.java
[ZipxTest.kt]: tools4j-zip/src/test/java/com/github/panpf/tools4j/zip/ZipxTest.kt
[Zipx.kt]: tools4j-zip-ktx/src/main/java/com/github/panpf/tools4j/zip/ktx/Zipx.kt
[ZipxTest.kt]: tools4j-zip-ktx/src/test/java/com/github/panpf/tools4j/zip/ktx/ZipxTest.kt
