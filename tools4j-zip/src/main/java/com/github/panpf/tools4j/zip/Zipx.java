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

package com.github.panpf.tools4j.zip;

import com.github.panpf.tools4j.common.Transformer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.*;
import java.util.zip.*;

/**
 * ZIP tool method
 */
@SuppressWarnings({"WeakerAccess", "UnusedReturnValue", "TryFinallyCanBeTryWithResources"})
public class Zipx {

    private static final int DEFAULT_BUFFER_SIZE = 1024 * 8;

    private Zipx() {
    }

    /**
     * Compress the specified byte array
     */
    @NotNull
    public static byte[] compress(@NotNull byte[] sourceBytes) {
        Deflater deflater = new Deflater();
        deflater.setInput(sourceBytes);
        deflater.finish();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            byte[] buf = new byte[DEFAULT_BUFFER_SIZE];
            while (true) {
                int readLength = deflater.deflate(buf);
                if (readLength <= 0) {
                    break;
                }
                bos.write(buf, 0, readLength);
            }
            return bos.toByteArray();
        } finally {
            deflater.end();
        }
    }

    /**
     * Decompress the specified byte array
     */
    @NotNull
    public static byte[] decompress(@NotNull byte[] compressedBytes) throws DataFormatException {
        Inflater inflater = new Inflater();
        inflater.setInput(compressedBytes);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            while (true) {
                int readLength = inflater.inflate(buffer);
                if (readLength <= 0) {
                    break;
                }
                bos.write(buffer, 0, readLength);
            }
            return bos.toByteArray();
        } finally {
            inflater.end();
        }
    }

    /**
     * Compress the specified byte array use gzip
     */
    @NotNull
    public static byte[] gzipCompress(@NotNull byte[] sourceBytes) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = null;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(sourceBytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (gzip != null) {
                try {
                    gzip.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    gzip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return out.toByteArray();
    }

    /**
     * Decompress the specified byte array use gzip
     */
    @NotNull
    public static byte[] gzipDecompress(@NotNull byte[] compressedBytes) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPInputStream gzip = null;
        try {
            gzip = new GZIPInputStream(new ByteArrayInputStream(compressedBytes));
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int n;
            while ((n = gzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (gzip != null) {
                try {
                    gzip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return out.toByteArray();
    }


    /**
     * Compress the specified files
     *
     * @param sourceFiles           The file to be compressed
     * @param destinationFile       Output file
     * @param zipEntryNameTransform Get the name of the file compressed into the zip package
     * @param listener              Progress listener
     * @return Output file
     * @throws IOException IO exceptions
     */
    @NotNull
    public static File compressFilesTo(@Nullable File[] sourceFiles, @NotNull File destinationFile,
                                       @NotNull Transformer<File, String> zipEntryNameTransform, @Nullable final ZipListener listener) throws IOException {
        ZipOutputStream zipOutputStream = null;
        try {
            zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(destinationFile, false)));

            Stack<File> files = new Stack<>();
            if (sourceFiles != null) {
                Collections.addAll(files, sourceFiles);
            }

            final long totalLength;
            if (listener != null) {
                long sumLength = 0;
                for (File file : files) {
                    sumLength += lengthRecursively(file);
                }
                totalLength = sumLength;
            } else {
                totalLength = 0;
            }
            final long[] totalCompletedLength = new long[1];
            final ZipEntry[] currentZipEntry = new ZipEntry[1];
            final long[] entrySize = new long[]{-1};

            while (listener == null || !listener.isCanceled()) {
                File childFile;
                try {
                    childFile = files.pop();
                } catch (EmptyStackException e) {
                    break;
                }

                if (!childFile.exists()) {
                    continue;
                }

                if (childFile.isDirectory()) {
                    File[] fileList = childFile.listFiles();
                    if (fileList != null) {
                        Collections.addAll(files, fileList);
                    }
                } else {
                    ZipEntry zipEntry = new ZipEntry(zipEntryNameTransform.transform(childFile));
                    currentZipEntry[0] = zipEntry;
                    entrySize[0] = childFile.length();
                    zipOutputStream.putNextEntry(zipEntry);
                    InputStream inputStream = null;
                    try {
                        inputStream = new BufferedInputStream(new FileInputStream(childFile));
                        if (listener != null && !listener.isCanceled()) listener.onEntryStart(currentZipEntry[0]);

                        long entryCompletedLength = 0;
                        byte[] buffer = new byte[1024 * 8];
                        int readLength;
                        while (listener == null || !listener.isCanceled()) {
                            readLength = inputStream.read(buffer);
                            if (readLength < 0) break;
                            zipOutputStream.write(buffer, 0, readLength);

                            if (listener != null && !listener.isCanceled()) {
                                entryCompletedLength += readLength;
                                totalCompletedLength[0] += readLength;
                                listener.onUpdateProgress(totalLength, totalCompletedLength[0], currentZipEntry[0], entrySize[0], entryCompletedLength);
                            }
                        }

                        if (listener != null && !listener.isCanceled()) listener.onEntryEnd(currentZipEntry[0]);
                    } finally {
                        zipOutputStream.closeEntry();
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            //noinspection ResultOfMethodCallIgnored
            destinationFile.delete();
            throw e;
        } finally {
            if (zipOutputStream != null) {
                zipOutputStream.flush();
                zipOutputStream.close();
            }
        }

        return destinationFile;
    }

    /**
     * Compress the specified files
     *
     * @param sourceFiles           The file to be compressed
     * @param destinationFile       Output file
     * @param zipEntryNameTransform Get the name of the file compressed into the zip package
     * @return Output file
     * @throws IOException IO exceptions
     */
    @NotNull
    public static File compressFilesTo(@Nullable File[] sourceFiles, @NotNull File destinationFile,
                                       @NotNull Transformer<File, String> zipEntryNameTransform) throws IOException {
        return compressFilesTo(sourceFiles, destinationFile, zipEntryNameTransform, null);
    }


    /**
     * Compress the specified file or directory
     *
     * @param sourceFile      The file to be compressed
     * @param destinationFile Output file
     * @param listener        Progress listener
     * @return Output file
     * @throws IOException IO exceptions
     */
    @NotNull
    public static File compressFileTo(@NotNull final File sourceFile, @NotNull File destinationFile, @Nullable final ZipListener listener) throws IOException {
        if (!sourceFile.exists()) {
            throw new FileNotFoundException(String.format("The file pointed to by this parameter 'sourceFile': %s", sourceFile.getPath()));
        }
        return compressFilesTo(new File[]{sourceFile}, destinationFile, ZipEntryNameTransformer.createByParent(sourceFile), listener);
    }

    /**
     * Compress the specified file or directory
     *
     * @param sourceFile      The file to be compressed
     * @param destinationFile Output file
     * @return Output file
     * @throws IOException IO exceptions
     */
    @NotNull
    public static File compressFileTo(@NotNull File sourceFile, @NotNull File destinationFile) throws IOException {
        return compressFileTo(sourceFile, destinationFile, null);
    }

    /**
     * Compress the specified file or directory
     *
     * @param sourceFile The file to be compressed
     * @param listener   Progress listener
     * @return Output file
     * @throws IOException IO exceptions
     */
    @NotNull
    public static File compressFile(@NotNull File sourceFile, @Nullable final ZipListener listener) throws IOException {
        return compressFileTo(sourceFile, getCompressDstFile(sourceFile), listener);
    }

    /**
     * Compress the specified file or directory
     *
     * @param sourceFile The file to be compressed
     * @return Output file
     * @throws IOException IO exceptions
     */
    @NotNull
    public static File compressFile(@NotNull File sourceFile) throws IOException {
        return compressFileTo(sourceFile, getCompressDstFile(sourceFile), null);
    }


    /**
     * Compress the specified file or directory (exclude source directory)
     *
     * @param sourceFile      The file to be compressed
     * @param destinationFile Output file
     * @param listener        Progress listener
     * @return Output file
     * @throws IOException IO exceptions
     */
    @NotNull
    public static File compressChildFileTo(@NotNull final File sourceFile, @NotNull File destinationFile, @Nullable final ZipListener listener) throws IOException {
        if (!sourceFile.exists()) {
            throw new FileNotFoundException(String.format("The file pointed to by this parameter 'sourceFile': %s", sourceFile.getPath()));
        }
        if (sourceFile.isFile()) {
            return compressFilesTo(new File[]{sourceFile}, destinationFile, ZipEntryNameTransformer.createByParent(sourceFile), listener);
        } else {
            return compressFilesTo(sourceFile.listFiles(), destinationFile, ZipEntryNameTransformer.createBySelf(sourceFile), listener);
        }
    }

    /**
     * Compress the specified file or directory (exclude source directory)
     *
     * @param sourceFile      The file to be compressed
     * @param destinationFile Output file
     * @return Output file
     * @throws IOException IO exceptions
     */
    public static File compressChildFileTo(@NotNull final File sourceFile, @NotNull File destinationFile) throws IOException {
        return compressChildFileTo(sourceFile, destinationFile, null);
    }

    /**
     * Compress the specified file or directory (exclude source directory)
     *
     * @param sourceFile The file to be compressed
     * @param listener   Progress listener
     * @return Output file
     * @throws IOException IO exceptions
     */
    @NotNull
    public static File compressChildFile(@NotNull final File sourceFile, @Nullable final ZipListener listener) throws IOException {
        return compressChildFileTo(sourceFile, getCompressDstFile(sourceFile), listener);
    }

    /**
     * Compress the specified file or directory (exclude source directory)
     *
     * @param sourceFile The file to be compressed
     * @return Output file
     * @throws IOException IO exceptions
     */
    @NotNull
    public static File compressChildFile(@NotNull final File sourceFile) throws IOException {
        return compressChildFile(sourceFile, null);
    }


    /**
     * Decompress the ZIP file to the specified folder
     *
     * @param zipSourceFile  ZIP file
     * @param destinationDir Out dir
     * @return Out dir
     * @throws IOException IO exceptions. include ZipException
     */
    @NotNull
    public static File decompressTo(@NotNull File zipSourceFile, @NotNull final File destinationDir, @Nullable final ZipListener listener) throws IOException {
        if (!zipSourceFile.exists()) {
            throw new FileNotFoundException(String.format("The file pointed to by this parameter 'sourceFile': %s", zipSourceFile.getPath()));
        }
        if (destinationDir.exists() && !destinationDir.isDirectory()) {
            throw new IllegalArgumentException(String.format("'%s' not directory", destinationDir.getPath()));
        }

        ZipFile zipFile = new ZipFile(zipSourceFile);
        try {
            final long totalLength = listener != null ? getTrueSize(zipFile) : 0;
            final long[] totalCompletedLength = new long[1];
            final ZipEntry[] currentZipEntry = new ZipEntry[1];

            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements() && (listener == null || !listener.isCanceled())) {
                ZipEntry zipEntry = entries.nextElement();
                currentZipEntry[0] = zipEntry;
                File file = new File(destinationDir, zipEntry.getName());
                if (zipEntry.isDirectory()) {
                    if (!file.exists()) {
                        //noinspection ResultOfMethodCallIgnored
                        file.mkdirs();
                        if (!file.exists()) {
                            throw new IOException("Can't create directory: " + file.getPath());
                        }
                    }
                } else {
                    if (!file.exists()) {
                        File parentDir = file.getParentFile();
                        if (!parentDir.exists()) {
                            //noinspection ResultOfMethodCallIgnored
                            parentDir.mkdirs();
                            if (!parentDir.exists()) {
                                throw new IOException("Can't create directory: " + parentDir.getPath());
                            }
                        }
                        try {
                            //noinspection ResultOfMethodCallIgnored
                            file.createNewFile();
                        } catch (IOException e) {
                            throw new IOException("Can't create file: " + file.getPath(), e);
                        }
                        if (!file.exists()) {
                            throw new IOException("Can't create file: " + file.getPath());
                        }
                    }

                    InputStream inputStream = null;
                    OutputStream outputStream = null;
                    try {
                        inputStream = zipFile.getInputStream(zipEntry);
                        outputStream = new FileOutputStream(file, false);
                        if (listener != null && !listener.isCanceled()) listener.onEntryStart(currentZipEntry[0]);

                        long entryCompletedLength = 0;
                        byte[] buffer = new byte[1024 * 8];
                        int readLength;
                        while (listener == null || !listener.isCanceled()) {
                            readLength = inputStream.read(buffer);
                            if (readLength < 0) break;
                            outputStream.write(buffer, 0, readLength);
                            if (listener != null && !listener.isCanceled()) {
                                entryCompletedLength += readLength;
                                totalCompletedLength[0] += readLength;
                                listener.onUpdateProgress(totalLength, totalCompletedLength[0], currentZipEntry[0], currentZipEntry[0].getSize(), entryCompletedLength);
                            }
                        }

                        if (listener != null && !listener.isCanceled()) listener.onEntryEnd(currentZipEntry[0]);
                    } finally {
                        if (outputStream != null) {
                            outputStream.flush();
                            outputStream.close();
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    }
                }
            }
        } finally {
            zipFile.close();
        }

        return destinationDir;
    }

    /**
     * Decompress the ZIP file to the specified folder
     *
     * @param zipSourceFile  ZIP file
     * @param destinationDir Out dir
     * @return Out dir
     * @throws IOException IO exceptions. include ZipException
     */
    @NotNull
    public static File decompressTo(@NotNull File zipSourceFile, @NotNull final File destinationDir) throws IOException {
        return decompressTo(zipSourceFile, destinationDir, null);
    }

    /**
     * Decompress the ZIP file to its directory, and the output folder name is the name of the ZIP file (without the suffix)
     *
     * @param zipSourceFile ZIP file
     * @param listener      Progress listener
     * @return Out dir
     * @throws IOException IO exceptions. include ZipException
     */
    @NotNull
    public static File decompress(@NotNull File zipSourceFile, @Nullable final ZipListener listener) throws IOException {
        return decompressTo(zipSourceFile, getDecompressDstDir(zipSourceFile), listener);
    }

    /**
     * Decompress the ZIP file to its directory, and the output folder name is the name of the ZIP file (without the suffix)
     *
     * @param zipSourceFile ZIP file
     * @return Out dir
     * @throws IOException IO exceptions. include ZipException
     */
    @NotNull
    public static File decompress(@NotNull File zipSourceFile) throws IOException {
        return decompress(zipSourceFile, null);
    }


    /**
     * Get the default compression dst file for the specified source file
     */
    @NotNull
    public static File getCompressDstFile(@NotNull File sourceFile) {
        return new File(sourceFile.getPath() + ".zip");
    }

    /**
     * Get the default decompression directory for the specified ZIP file
     */
    @NotNull
    public static File getDecompressDstDir(@NotNull File zipSourceFile) {
        return zipSourceFile.getParentFile();
    }


    /**
     * Get the original size of the Zip file
     */
    public static long getTrueSize(@NotNull ZipFile zipFile) {
        long totalSize = 0;
        Enumeration<? extends ZipEntry> elements = zipFile.entries();
        while (elements.hasMoreElements()) {
            totalSize += elements.nextElement().getSize();
        }
        return totalSize;
    }

    /**
     * Get the original size of the Zip file
     */
    public static long getTrueSize(@NotNull File file) throws IOException {
        try (ZipFile zipFile = new ZipFile(file)) {
            return getTrueSize(zipFile);
        }
    }

    @NotNull
    public static ArrayList<ZipEntry> listEntry(@NotNull ZipFile zipFile) {
        ArrayList<ZipEntry> zipEntries = new ArrayList<>(zipFile.size());
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            zipEntries.add(entries.nextElement());
        }
        return zipEntries;
    }

    @NotNull
    public static ArrayList<ZipEntry> listEntry(@NotNull File file) throws IOException {
        try (ZipFile zipFile = new ZipFile(file)) {
            return listEntry(zipFile);
        }
    }

    @NotNull
    public static ArrayList<String> listEntryName(@NotNull ZipFile zipFile) {
        ArrayList<String> zipEntries = new ArrayList<>(zipFile.size());
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            zipEntries.add(entries.nextElement().getName());
        }
        return zipEntries;
    }

    @NotNull
    public static ArrayList<String> listEntryName(@NotNull File file) throws IOException {
        try (ZipFile zipFile = new ZipFile(file)) {
            return listEntryName(zipFile);
        }
    }

    public static int size(@NotNull File file) throws IOException {
        try (ZipFile zipFile = new ZipFile(file)) {
            return zipFile.size();
        }
    }

    private static long lengthRecursively(@NotNull File file) {
        if (!file.exists()) return 0;
        if (file.isFile()) return file.length();

        long length = 0;

        Queue<File> fileQueue = new LinkedList<>();
        fileQueue.add(file);

        File childFile;
        while (true) {
            childFile = fileQueue.poll();
            if (childFile == null || !childFile.exists()) {
                break;
            }

            if (childFile.isFile()) {
                length += childFile.length();
            } else {
                File[] childChildFiles = childFile.listFiles();
                if (childChildFiles != null && childChildFiles.length > 0) {
                    Collections.addAll(fileQueue, childChildFiles);
                }
            }
        }
        return length;
    }
}
