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

import com.github.panpf.tools4j.common.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class MapBuilder<K, V> {

    @NotNull
    private final List<Pair<K, V>> list = new LinkedList<Pair<K, V>>();

    public MapBuilder(@NotNull K k, @Nullable V v) {
        put(k, v);
    }

    @NotNull
    public MapBuilder<K, V> put(@NotNull K k, @Nullable V v) {
        list.add(new Pair<K, V>(k, v));
        return this;
    }

    @NotNull
    public Map<K, V> buildHashMap() {
        Map<K, V> map = new HashMap<K, V>();
        for (Pair<K, V> pair : list) {
            map.put(pair.first, pair.second);
        }
        return map;
    }

    @NotNull
    public WeakHashMap<K, V> buildWeakHashMap() {
        WeakHashMap<K, V> map = new WeakHashMap<K, V>();
        for (Pair<K, V> pair : list) {
            map.put(pair.first, pair.second);
        }
        return map;
    }

    @NotNull
    public LinkedHashMap<K, V> buildLinkedHashMap() {
        LinkedHashMap<K, V> map = new LinkedHashMap<K, V>();
        for (Pair<K, V> pair : list) {
            map.put(pair.first, pair.second);
        }
        return map;
    }

    @NotNull
    public Hashtable<K, V> buildHashtable() {
        Hashtable<K, V> map = new Hashtable<K, V>();
        for (Pair<K, V> pair : list) {
            map.put(pair.first, pair.second);
        }
        return map;
    }

    @NotNull
    public TreeMap<K, V> buildTreeMap() {
        TreeMap<K, V> map = new TreeMap<K, V>();
        for (Pair<K, V> pair : list) {
            map.put(pair.first, pair.second);
        }
        return map;
    }

    @NotNull
    public SortedMap<K, V> buildSortedMap() {
        return buildTreeMap();
    }
}
