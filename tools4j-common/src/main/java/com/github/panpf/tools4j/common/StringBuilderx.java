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

package com.github.panpf.tools4j.common;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

/**
 * StringBuilder tool method
 */
// todo rename to Appendablex
@SuppressWarnings("WeakerAccess")
public class StringBuilderx {

    private StringBuilderx() {
    }


    /*
     * *****************************************************************************************************************
     * From kotlin standard library
     * *****************************************************************************************************************
     */


    public static <T> void appendElement(@NotNull Appendable appendable, @NotNull T element, @Nullable Transformer<T, CharSequence> transform) {
        try {
            if (transform != null) {
                appendable.append(transform.transform(element));
            } else if (element instanceof CharSequence) {
                appendable.append((CharSequence) element);
            } else if (element instanceof Character) {
                appendable.append((Character) element);
            } else {
                appendable.append(element.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
