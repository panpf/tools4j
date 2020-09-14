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

package com.github.panpf.tools4j.collections;

import com.github.panpf.tools4j.iterable.EmptyIterator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.*;

public class EmptyList implements List<Object>, Serializable, RandomAccess {

    @NotNull
    public static final EmptyList INSTANCE = new EmptyList();
    private static final long serialVersionUID = -7390468764508069838L;

    @Override
    public boolean equals(@Nullable Object other) {
        return other instanceof List && ((List<?>) other).isEmpty();
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "[]";
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean contains(@NotNull Object element) {
        return false;
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> elements) {
        return elements.isEmpty();
    }

    @Override
    public boolean addAll(@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Object get(int index) {
        throw new IndexOutOfBoundsException("Empty list doesn't contain element at index " + index + ".");
    }

    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, Object element) {

    }

    @Override
    public Object remove(int index) {
        return null;
    }

    @Override
    public int indexOf(@NotNull Object element) {
        return -1;
    }

    @Override
    public int lastIndexOf(@NotNull Object element) {
        return -1;
    }

    @NotNull
    @Override
    public Iterator<Object> iterator() {
        return EmptyIterator.INSTANCE;
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] a) {
        if (a.length > 0) {
            throw new IllegalArgumentException("T[] must be empty");
        }
        return a;
    }

    @Override
    public boolean add(Object o) {
        //noinspection Contract
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @NotNull
    @Override
    public ListIterator<Object> listIterator() {
        return EmptyIterator.INSTANCE;
    }

    @NotNull
    @Override
    public ListIterator<Object> listIterator(int index) {
        if (index != 0) throw new IndexOutOfBoundsException("Index: " + index);
        return EmptyIterator.INSTANCE;
    }

    @NotNull
    @Override
    public List<Object> subList(int fromIndex, int toIndex) {
        if (fromIndex == 0 && toIndex == 0) return this;
        throw new IndexOutOfBoundsException("fromIndex: " + fromIndex + ", toIndex: " + toIndex);
    }

    private Object readResolve() {
        return EmptyList.INSTANCE;
    }
}