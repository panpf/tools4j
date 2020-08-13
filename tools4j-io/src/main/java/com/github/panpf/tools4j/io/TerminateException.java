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

package com.github.panpf.tools4j.io;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class TerminateException extends FileSystemException {
    /**
     * A base exception class for file system exceptions.
     *
     * @param file the file on which the failed operation was performed.
     */
    public TerminateException(@NotNull File file) {
        super(file, null, null);
    }
}
