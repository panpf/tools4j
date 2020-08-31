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

import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CharSequenceIterator implements Iterator<Character> {

    @Nullable
    private final CharSequence charSequence;

    private int index;

    public CharSequenceIterator(@Nullable CharSequence charSequence) {
        this.charSequence = charSequence;
    }

    @Override
    public boolean hasNext() {
        return charSequence != null && index < charSequence.length();
    }

    @Override
    public Character next() {
        if (charSequence == null) throw new NoSuchElementException("elements is null");
        final int nextIndex = index++;
        try {
            return charSequence.charAt(nextIndex);
        } catch (StringIndexOutOfBoundsException e) {
            index -= 1;
            throw new NoSuchElementException(String.valueOf(nextIndex));
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove");
    }
}
