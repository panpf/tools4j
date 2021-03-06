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

package com.github.panpf.tools4j.sequences;

import com.github.panpf.tools4j.iterable.EmptyIterator;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class EmptySequence implements Sequence<Object>, DropTakeSequence<Object> {

    public static final EmptySequence INSTANCE = new EmptySequence();

    private EmptySequence() {
    }

    @NotNull
    @Override
    public Iterator<Object> iterator() {
        return EmptyIterator.INSTANCE;
    }

    @NotNull
    @Override
    public Sequence<Object> drop(int n) {
        return EmptySequence.INSTANCE;
    }

    @NotNull
    @Override
    public Sequence<Object> take(int n) {
        return EmptySequence.INSTANCE;
    }
}
