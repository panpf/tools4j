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

import java.io.File;

public class ZipEntryNameTransformer implements Transformer<File, String> {
    @NotNull
    private final String sourceDirParentPath;

    public ZipEntryNameTransformer(@NotNull String sourceDirParentPath) {
        this.sourceDirParentPath = sourceDirParentPath;
    }

    @NotNull
    @Override
    public String transform(@NotNull File file) {
        return file.getPath().replace(sourceDirParentPath, "");
    }

    @NotNull
    public static ZipEntryNameTransformer createByParent(@NotNull File sourceDir) {
        return new ZipEntryNameTransformer(sourceDir.getParent() + File.separator);
    }

    @NotNull
    public static ZipEntryNameTransformer createBySelf(@NotNull File sourceDir) {
        return new ZipEntryNameTransformer(sourceDir.getPath() + File.separator);
    }
}
