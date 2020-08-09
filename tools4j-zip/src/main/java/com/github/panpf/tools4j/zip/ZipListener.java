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

import org.jetbrains.annotations.NotNull;

import java.util.zip.ZipEntry;

public interface ZipListener {

    void onEntryStart(@NotNull ZipEntry zipEntry);

    void onUpdateProgress(long totalLength, long totalCompletedLength, @NotNull ZipEntry zipEntry, long entryTotalLength, long entryCompletedLength);

    void onEntryEnd(@NotNull ZipEntry zipEntry);

    boolean isCanceled();
}
