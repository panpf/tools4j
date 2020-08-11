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

public class Pair<A, B> {
    public final A first;
    public final B second;

    public Pair(A var1, B var2) {
        this.first = var1;
        this.second = var2;
    }

    public String toString() {
        return "(" + this.first + ", " + this.second + ")";
    }

    public boolean equals(Object var1) {
        return var1 instanceof Pair
                && (this.first == ((Pair) var1).first || (this.first != null && this.first.equals(((Pair) var1).first)))
                && (this.second == ((Pair) var1).second || (this.second != null && this.second.equals(((Pair) var1).second)));
    }

    public int hashCode() {
        if (this.first == null) {
            return this.second == null ? 0 : this.second.hashCode() + 1;
        } else {
            return this.second == null ? this.first.hashCode() + 2 : this.first.hashCode() * 17 + this.second.hashCode();
        }
    }

    public static <A, B> Pair<A, B> of(A var0, B var1) {
        return new Pair<>(var0, var1);
    }
}