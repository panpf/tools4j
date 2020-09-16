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

package com.github.panpf.tools4j.resources;

import com.github.panpf.tools4j.io.Filex;
import com.github.panpf.tools4j.environment.Environmentx;
import com.github.panpf.tools4j.security.MessageDigestx;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ResourcesCacheHelper {

    @NotNull
    private final Class<?> targetClazz;
    @NotNull
    private final File cacheDir;

    private final List<DevResourcesDirCreator> devResourcesDirCreators = new LinkedList<>();
    public final boolean isInJar;
    @Nullable
    private File resourcesDir = null;

    /**
     * @param targetClazz Any Class in the same jar as the Resources file
     */
    public ResourcesCacheHelper(@NotNull Class<?> targetClazz, @NotNull File cacheDir) {
        this.targetClazz = targetClazz;
        this.cacheDir = cacheDir;

        this.isInJar = Environmentx.isClassInJar(targetClazz);
        this.devResourcesDirCreators.add(new GradleBuildResourcesDirCreator());
    }

    @NotNull
    public File getFile(@NotNull String filePathInResources) throws IOException {
        ResourcesItem item;
        if (isInJar) {
            File jarFile = Environmentx.getClassInJarFile(targetClazz);
            if (jarFile == null) throw new IOException("Not get jar file");
            ZipFile zipFile = new ZipFile(jarFile);

            ZipEntry targetZipEntry = null;
            Enumeration<?> zipEntryEnumeration = zipFile.entries();
            while (zipEntryEnumeration.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) zipEntryEnumeration.nextElement();
                if (!zipEntry.isDirectory() && zipEntry.getName().equals(filePathInResources)) {
                    targetZipEntry = zipEntry;
                    break;
                }
            }
            if (targetZipEntry == null)
                throw new FileNotFoundException(String.format("Not found file '%s' in jar file", filePathInResources));
            item = new ZipResourcesItem(jarFile, zipFile, targetZipEntry, filePathInResources);
        } else {
            File sourceDir = getResourcesDir();
            File sourceFile = new File(sourceDir, filePathInResources);
            if (!sourceFile.exists()) {
                throw new FileNotFoundException(String.format("Not found source resources file. %s", sourceFile.getPath()));
            }
            item = new FileResourcesItem(sourceFile, sourceDir.getPath());
        }

        File outFile = new File(cacheDir, filePathInResources);
        if (!outFile.exists()) {
            item.outFile(outFile);
            if (outFile.exists()) {
                System.out.printf("Create resources cache file. %s -> %s%n%n", item.getPath(), outFile.getPath());
            } else {
                throw new IOException(String.format("Copy to cache dir failed. %s -> %s", item.getPath(), outFile.getPath()));
            }
        } else {
            String sourceFileMd5 = item.getMD5Digest();
            String outFileMd5 = MessageDigestx.getMD5(outFile, null);
            if (!sourceFileMd5.equals(outFileMd5)) {
                item.outFile(outFile);
                System.out.printf("Update resources cache file. %s -> %s%n", item.getPath(), outFile.getPath());
            } else {
                System.out.printf("Use resources cache file. %s%n", outFile.getPath());
            }
        }

        return outFile;
    }

    @NotNull
    public File getDir(@NotNull String dirPathInResources) throws IOException {
        List<ResourcesItem> sourceChildResItems;
        if (isInJar) {
            File jarFile = Environmentx.getClassInJarFile(targetClazz);
            if (jarFile == null) throw new IOException("Not get jar file");
            ZipFile zipFile = new ZipFile(jarFile);
            List<ResourcesItem> sourceChildFilesTemp = new ArrayList<>();
            Enumeration<?> zipEntryEnumeration = zipFile.entries();
            while (zipEntryEnumeration.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) zipEntryEnumeration.nextElement();
                if (!zipEntry.isDirectory() && zipEntry.getName().startsWith(dirPathInResources)) {
                    sourceChildFilesTemp.add(new ZipResourcesItem(jarFile, zipFile, zipEntry, dirPathInResources));
                }
            }
            if (sourceChildFilesTemp.isEmpty()) {
                throw new FileNotFoundException(String.format("Source resources dir is empty. %s!%s", jarFile.getPath(), dirPathInResources));
            }

            sourceChildResItems = sourceChildFilesTemp;
        } else {
            File sourceDir = new File(getResourcesDir(), dirPathInResources);
            if (!sourceDir.exists()) {
                throw new FileNotFoundException(String.format("Not found source resources dir. %s", sourceDir.getPath()));
            }
            File[] sourceChildFiles = Filex.listFilesRecursively(sourceDir, new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.isFile();
                }
            });
            if (sourceChildFiles == null || sourceChildFiles.length <= 0) {
                throw new FileNotFoundException(String.format("Source resources dir is empty. %s", sourceDir.getPath()));
            }

            sourceChildResItems = new ArrayList<>(sourceChildFiles.length);
            for (File sourceChildFile : sourceChildFiles) {
                sourceChildResItems.add(new FileResourcesItem(sourceChildFile, sourceDir.getPath()));
            }
        }

        File outDir = new File(cacheDir, dirPathInResources);
        try {
            // Delete deleted files in the source directory
            File[] outDirChildFiles = Filex.listFilesRecursively(outDir, new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.isFile();
                }
            });
            if (outDirChildFiles != null) {
                for (File outChildFile : outDirChildFiles) {
                    ResourcesItem sameItem = null;
                    for (ResourcesItem sourceChildFile : sourceChildResItems) {
                        String sourceChildFileWithoutSourceDirPath = removeStartSeparator(sourceChildFile.getPathWithoutSourceDirPath());
                        String outChildFileWithoutOutDirPath = removeStartSeparator(outChildFile.getPath().replace(outDir.getPath(), ""));
                        if (sourceChildFileWithoutSourceDirPath.equals(outChildFileWithoutOutDirPath)) {
                            sameItem = sourceChildFile;
                            break;
                        }
                    }
                    if (sameItem == null) {
                        //noinspection ResultOfMethodCallIgnored
                        outChildFile.delete();
                        System.out.printf("Deleted resources cache file. %s%n", outChildFile.getPath());
                    }
                }
            }

            // Overwrite old files or add new files
            for (ResourcesItem sourceChildFile : sourceChildResItems) {
                String withoutSourceDirPath = removeStartSeparator(sourceChildFile.getPathWithoutSourceDirPath());
                File outChildFile = new File(outDir, withoutSourceDirPath);
                if (!outChildFile.exists()) {
                    sourceChildFile.outFile(outChildFile);
                    if (outChildFile.exists()) {
                        System.out.printf("Create resources cache file. %s -> %s%n", sourceChildFile.getPath(), outChildFile.getPath());
                    } else {
                        throw new IOException(String.format("Copy to cache dir failed. %s -> %s", sourceChildFile.getPath(), outChildFile.getPath()));
                    }
                } else {
                    String sourceChildFileMd5 = sourceChildFile.getMD5Digest();
                    String outChildFileMd5 = MessageDigestx.getMD5(outChildFile);
                    if (!sourceChildFileMd5.equals(outChildFileMd5)) {
                        sourceChildFile.outFile(outChildFile);
                        System.out.printf("Update resources cache file. %s -> %s%n", sourceChildFile.getPath(), outChildFile.getPath());
                    } else {
                        System.out.printf("Use resources cache file. %s%n", outChildFile.getPath());
                    }
                }
            }
            return outDir;
        } catch (Exception e) {
            Filex.deleteRecursively(outDir);
            throw e;
        }
    }

    @NotNull
    private String removeStartSeparator(@NotNull String path) {
        return path.startsWith(File.separator) ? path.substring(File.separator.length()) : path;
    }

    @NotNull
    public File getResourcesDir() {
        if (isInJar) {
            throw new UnsupportedOperationException("Method 'getResourcesDir' not supported run in jar");
        }

        final File resourcesDir = this.resourcesDir;
        if (resourcesDir != null) {
            return resourcesDir;
        }

        File newResourcesDir = null;
        for (DevResourcesDirCreator devResourcesDirCreator : devResourcesDirCreators) {
            File resourcesDirTemp = devResourcesDirCreator.createDevResourceDir(targetClazz);
            if (resourcesDirTemp != null && resourcesDirTemp.exists()) {
                newResourcesDir = resourcesDirTemp;
                break;
            }
        }
        if (newResourcesDir != null) {
            this.resourcesDir = newResourcesDir;
            return newResourcesDir;
        } else {
            throw new UnsupportedResourcesDirectoryException("You can implement 'DevResourcesDirCreator' interface to adapt your resource directory");
        }
    }

    @NotNull
    public ResourcesCacheHelper addDevResourcesDirCreator(@NotNull DevResourcesDirCreator devResourcesDirCreator) {
        this.devResourcesDirCreators.add(0, devResourcesDirCreator);
        return this;
    }
}