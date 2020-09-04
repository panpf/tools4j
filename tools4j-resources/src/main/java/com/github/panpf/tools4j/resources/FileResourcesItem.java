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
import com.github.panpf.tools4j.security.MessageDigestx;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

class FileResourcesItem implements ResourcesItem {

    @NotNull
    private final File file;
    @NotNull
    private final String sourceDirPath;

    public FileResourcesItem(@NotNull File file, @NotNull String sourceDirPath) {
        this.file = file;
        this.sourceDirPath = sourceDirPath;
    }

    @Override
    public void outFile(@NotNull File outFile) throws IOException {
        Filex.copyTo(file, outFile, true);
    }

    @NotNull
    @Override
    public String getPath() {
        return file.getPath();
    }

    @NotNull
    @Override
    public String getPathWithoutSourceDirPath() {
        return file.getPath().replace(sourceDirPath, "");
    }

    @NotNull
    @Override
    public String getMD5Digest() throws IOException {
        return MessageDigestx.getMD5(file);
    }
}
