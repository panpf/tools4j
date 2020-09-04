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
import com.github.panpf.tools4j.io.IOx;
import com.github.panpf.tools4j.security.MessageDigestx;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

class ZipResourcesItem implements ResourcesItem {

    @NotNull
    private final File jarFile;
    @NotNull
    private final ZipFile zipFile;
    @NotNull
    private final ZipEntry zipEntry;
    @NotNull
    private final String sourceDirPath;

    public ZipResourcesItem(@NotNull File jarFile, @NotNull ZipFile zipFile, @NotNull ZipEntry zipEntry, @NotNull String sourceDirPath) {
        this.jarFile = jarFile;
        this.zipFile = zipFile;
        this.zipEntry = zipEntry;
        this.sourceDirPath = sourceDirPath;
    }

    @Override
    public void outFile(@NotNull File outFile) throws IOException {
        //noinspection ResultOfMethodCallIgnored
        outFile.delete();
        Filex.createNewFileOrThrow(outFile);
        FileOutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            outputStream = Filex.outputStream(outFile);
            inputStream = zipFile.getInputStream(zipEntry);
            IOx.copyTo(inputStream, outputStream);
        } finally {
            IOx.closeQuietly(outputStream);
            IOx.closeQuietly(inputStream);
        }
    }

    @NotNull
    @Override
    public String getPath() {
        return jarFile.getPath() + "!" + zipEntry.getName();
    }

    @NotNull
    @Override
    public String getPathWithoutSourceDirPath() {
        return zipEntry.getName().replace(sourceDirPath, "");
    }

    @NotNull
    @Override
    public String getMD5Digest() throws IOException {
        InputStream inputStream = zipFile.getInputStream(zipEntry);
        try {
            return MessageDigestx.getMD5(inputStream);
        } finally {
            IOx.closeQuietly(inputStream);
        }
    }
}
