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

package com.github.panpf.tools4j.compare;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class NaturalOrderComparator<T extends Comparable<T>> implements Comparator<T> {

    @SuppressWarnings("rawtypes")
    public static final NaturalOrderComparator INSTANCE = new NaturalOrderComparator();

    @Override
    public int compare(T a, T b) {
        return a.compareTo(b);
    }

    @NotNull
    public Comparator<T> reversed() {
        //noinspection unchecked
        return (Comparator<T>) ReverseOrderComparator.INSTANCE;
    }
}
