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

package com.github.panpf.tools4j.iterable;

import java.util.Iterator;

/**
 * An iterator over a sequence of values of type `Integer`.
 */
public abstract class IntIterator implements Iterator<Integer> {

    @Override
    public final Integer next() {
        return nextInt();
    }

    /**
     * Returns the next value in the sequence without boxing.
     */
    public abstract Integer nextInt();
}
