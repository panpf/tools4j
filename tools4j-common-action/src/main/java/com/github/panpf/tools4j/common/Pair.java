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

public class Pair<A, B> {
    @NotNull
    public final A first;

    @NotNull
    public final B second;

    public Pair(@NotNull A var1, @NotNull B var2) {
        this.first = var1;
        this.second = var2;
    }

    public String toString() {
        return "(" + this.first + ", " + this.second + ")";
    }

    public boolean equals(@Nullable Object var1) {
        Pair<?, ?> var1Cast = var1 instanceof Pair<?, ?> ? (Pair<?, ?>) var1 : null;
        //noinspection ConstantConditions,EqualsReplaceableByObjectsCall,EqualsReplaceableByObjectsCall
        return var1Cast != null
                && (this.first == var1Cast.first || (this.first != null && this.first.equals(var1Cast.first)))
                && (this.second == var1Cast.second || (this.second != null && this.second.equals(var1Cast.second)));
    }

    public int hashCode() {
        //noinspection ConstantConditions
        if (this.first == null) {
            return this.second == null ? 0 : this.second.hashCode() + 1;
        } else {
            //noinspection ConstantConditions
            return this.second == null ? this.first.hashCode() + 2 : this.first.hashCode() * 17 + this.second.hashCode();
        }
    }

    @NotNull
    public static <A, B> Pair<A, B> of(@NotNull A var0, @NotNull B var1) {
        return new Pair<A, B>(var0, var1);
    }
}