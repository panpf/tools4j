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

package com.github.panpf.tools4j.common;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Pair<A, B> {

    @NotNull
    public final A first;

    @NotNull
    public final B second;

    public Pair(@NotNull A var1, @NotNull B var2) {
        this.first = var1;
        this.second = var2;
    }

    @NotNull
    @Override
    public String toString() {
        return "(" + this.first + ", " + this.second + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return first.equals(pair.first) &&
                second.equals(pair.second);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{first, second});
    }

    @NotNull
    public static <A, B> Pair<A, B> of(@NotNull A var0, @NotNull B var1) {
        return new Pair<A, B>(var0, var1);
    }
}