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

package com.github.panpf.tools4j.security;

import com.github.panpf.tools4j.base64.Base64x;
import org.jetbrains.annotations.NotNull;

import java.security.Key;

/**
 * Key tool method
 */
public class Keyx {

    private Keyx() {
    }

    /**
     * Convert key to a Base64 string
     */
    @NotNull
    public static String toBase64(Key key) {
        return Base64x.encodeToString(key.getEncoded(), Base64x.NO_WRAP);
    }

    /**
     * Convert key to a byte array
     */
    @NotNull
    public static byte[] toBytes(Key key) {
        return key.getEncoded();
    }
}
