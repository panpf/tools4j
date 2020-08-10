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

package com.github.panpf.tools4j.grouping;

import com.github.panpf.tools4j.common.Transformer;
import com.github.panpf.tools4j.common.Transformer2;
import com.github.panpf.tools4j.iterable.ArrayIterator;
import com.github.panpf.tools4j.iterable.CharSequenceIterator;
import com.github.panpf.tools4j.iterable.EmptyIterator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Groupingx {

    private Groupingx() {
    }


    /*
     * *****************************************************************************************************************
     * From kotlin standard library
     * *****************************************************************************************************************
     */

    /**
     * Creates a [Grouping] source from a char sequence to be used later with one of group-and-fold operations
     * using the specified [keySelector] function to extract a key from each character.
     */
    @NotNull
    public static <K> Grouping<Character, K> groupingBy(@Nullable final CharSequence charSequence, @NotNull final Transformer<Character, K> keySelector) {
        return new Grouping<Character, K>() {
            @NotNull
            @Override
            public Iterator<Character> sourceIterator() {
                return new CharSequenceIterator(charSequence);
            }

            @NotNull
            @Override
            public K keyOf(@NotNull Character element) {
                return keySelector.transform(element);
            }
        };
    }

    /**
     * Creates a [Grouping] source from an array to be used later with one of group-and-fold operations
     * using the specified [keySelector] function to extract a key from each element.
     */
    @NotNull
    public static <T, K> Grouping<T, K> groupingBy(@Nullable final T[] elements, @NotNull final Transformer<T, K> keySelector) {
        return new Grouping<T, K>() {
            @NotNull
            @Override
            public Iterator<T> sourceIterator() {
                return new ArrayIterator<>(elements);
            }

            @NotNull
            @Override
            public K keyOf(@NotNull T element) {
                return keySelector.transform(element);
            }
        };
    }

    /**
     * Creates a [Grouping] source from a collection to be used later with one of group-and-fold operations
     * using the specified [keySelector] function to extract a key from each element.
     */
    @NotNull
    public static <T, K> Grouping<T, K> groupingBy(@Nullable final Iterable<T> iterable, @NotNull final Transformer<T, K> keySelector) {
        return new Grouping<T, K>() {
            @NotNull
            @Override
            public Iterator<T> sourceIterator() {
                //noinspection unchecked
                return iterable != null ? iterable.iterator() : (Iterator<T>) EmptyIterator.INSTANCE;
            }

            @NotNull
            @Override
            public K keyOf(@NotNull T element) {
                return keySelector.transform(element);
            }
        };
    }


    /**
     * Groups elements from the [Grouping] source by key and applies [operation] to the elements of each group sequentially,
     * passing the previously accumulated value and the current element as arguments, and stores the results in a new map.
     * <p>
     * The key for each element is provided by the [Grouping.keyOf] function.
     *
     * @param operation function is invoked on each element with the following parameters:
     *                  - `key`: the key of the group this element belongs to;
     *                  - `accumulator`: the current value of the accumulator of the group, can be `null` if it's the first `element` encountered in the group;
     *                  - `element`: the element from the source being aggregated;
     *                  - `first`: indicates whether it's the first `element` encountered in the group.
     * @return a [Map] associating the key of each group with the result of aggregation of the group elements.
     */
    @NotNull
    public static <T, K, R> Map<K, R> aggregate(@Nullable Grouping<T, K> grouping, @NotNull AggregateOperation<T, K, R> operation) {
        return aggregateTo(grouping, new LinkedHashMap<>(0), operation);
    }

    /**
     * Groups elements from the [Grouping] source by key and applies [operation] to the elements of each group sequentially,
     * passing the previously accumulated value and the current element as arguments,
     * and stores the results in the given [destination] map.
     * <p>
     * The key for each element is provided by the [Grouping.keyOf] function.
     *
     * @param operation a function that is invoked on each element with the following parameters:
     *                  - `key`: the key of the group this element belongs to;
     *                  - `accumulator`: the current value of the accumulator of the group, can be `null` if it's the first `element` encountered in the group;
     *                  - `element`: the element from the source being aggregated;
     *                  - `first`: indicates whether it's the first `element` encountered in the group.
     *                  <p>
     *                  If the [destination] map already has a value corresponding to some key,
     *                  then the elements being aggregated for that key are never considered as `first`.
     * @return the [destination] map associating the key of each group with the result of aggregation of the group elements.
     */
    @NotNull
    public static <T, K, R, M extends Map<K, R>> M aggregateTo(@Nullable Grouping<T, K> grouping, @NotNull M destination, @NotNull AggregateOperation<T, K, R> operation) {
        if (grouping != null) {
            Iterator<T> iterator = grouping.sourceIterator();
            while (iterator.hasNext()) {
                T e = iterator.next();
                K key = grouping.keyOf(e);
                R accumulator = destination.get(key);
                destination.put(key, operation.operation(key, accumulator, e, accumulator == null && !destination.containsKey(key)));
            }
        }
        return destination;
    }


    /**
     * Groups elements from the [Grouping] source by key and applies [operation] to the elements of each group sequentially,
     * passing the previously accumulated value and the current element as arguments, and stores the results in a new map.
     * An initial value of accumulator is provided by [initialValueSelector] function.
     *
     * @param initialValueSelector a function that provides an initial value of accumulator for each group.
     *                             It's invoked with parameters:
     *                             - `key`: the key of the group;
     *                             - `element`: the first element being encountered in that group.
     * @param operation            a function that is invoked on each element with the following parameters:
     *                             - `key`: the key of the group this element belongs to;
     *                             - `accumulator`: the current value of the accumulator of the group;
     *                             - `element`: the element from the source being accumulated.
     * @return a [Map] associating the key of each group with the result of accumulating the group elements.
     */
    @NotNull
    public static <T, K, R> Map<K, R> fold(@Nullable Grouping<T, K> grouping, @NotNull final Transformer2<K, T, R> initialValueSelector, @NotNull final FoldOperation<T, K, R> operation) {
        return aggregate(grouping, (key, accumulator, element, first) -> {
            R nextAccumulator;
            if (first) {
                nextAccumulator = initialValueSelector.transform(key, element);
            } else {
                if (accumulator == null) {
                    throw new IllegalArgumentException("Param 'accumulator' is null");
                }
                nextAccumulator = accumulator;
            }
            return operation.operation(key, nextAccumulator, element);
        });
    }

    /**
     * Groups elements from the [Grouping] source by key and applies [operation] to the elements of each group sequentially,
     * passing the previously accumulated value and the current element as arguments,
     * and stores the results in the given [destination] map.
     * An initial value of accumulator is provided by [initialValueSelector] function.
     *
     * @param initialValueSelector a function that provides an initial value of accumulator for each group.
     *                             It's invoked with parameters:
     *                             - `key`: the key of the group;
     *                             - `element`: the first element being encountered in that group.
     *                             <p>
     *                             If the [destination] map already has a value corresponding to some key, that value is used as an initial value of
     *                             the accumulator for that group and the [initialValueSelector] function is not called for that group.
     * @param operation            a function that is invoked on each element with the following parameters:
     *                             - `key`: the key of the group this element belongs to;
     *                             - `accumulator`: the current value of the accumulator of the group;
     *                             - `element`: the element from the source being accumulated.
     * @return the [destination] map associating the key of each group with the result of accumulating the group elements.
     */
    @NotNull
    public static <T, K, R, M extends Map<K, R>> M foldTo(@Nullable Grouping<T, K> grouping, @NotNull M destination,
                                                          @NotNull final Transformer2<K, T, R> initialValueSelector,
                                                          @NotNull final FoldOperation<T, K, R> operation) {
        return aggregateTo(grouping, destination, (key, accumulator, element, first) -> {
            R nextAccumulator;
            if (first) {
                nextAccumulator = initialValueSelector.transform(key, element);
            } else {
                if (accumulator == null) {
                    throw new IllegalArgumentException("Param 'accumulator' is null");
                }
                nextAccumulator = accumulator;
            }
            return operation.operation(key, nextAccumulator, element);
        });
    }


    /**
     * Groups elements from the [Grouping] source by key and applies [operation] to the elements of each group sequentially,
     * passing the previously accumulated value and the current element as arguments, and stores the results in a new map.
     * An initial value of accumulator is the same [initialValue] for each group.
     *
     * @param operation a function that is invoked on each element with the following parameters:
     *                  - `accumulator`: the current value of the accumulator of the group;
     *                  - `element`: the element from the source being accumulated.
     * @return a [Map] associating the key of each group with the result of accumulating the group elements.
     */
    @NotNull
    public static <T, K, R> Map<K, R> fold(@Nullable Grouping<T, K> grouping, @NotNull final R initialValue, @NotNull final Transformer2<R, T, R> operation) {
        return aggregate(grouping, (key, accumulator, element, first) -> {
            R r = first ? initialValue : accumulator;
            if (r == null) {
                throw new IllegalArgumentException("Param 'r' is null");
            }
            return operation.transform(r, element);
        });
    }

    /**
     * Groups elements from the [Grouping] source by key and applies [operation] to the elements of each group sequentially,
     * passing the previously accumulated value and the current element as arguments,
     * and stores the results in the given [destination] map.
     * An initial value of accumulator is the same [initialValue] for each group.
     * <p>
     * If the [destination] map already has a value corresponding to the key of some group,
     * that value is used as an initial value of the accumulator for that group.
     *
     * @param operation a function that is invoked on each element with the following parameters:
     *                  - `accumulator`: the current value of the accumulator of the group;
     *                  - `element`: the element from the source being accumulated.
     * @return the [destination] map associating the key of each group with the result of accumulating the group elements.
     */
    @NotNull
    public static <T, K, R, M extends Map<K, R>> M foldTo(@Nullable Grouping<T, K> grouping, @NotNull M destination, @NotNull final R initialValue, @NotNull final Transformer2<R, T, R> operation) {
        return aggregateTo(grouping, destination, (key, accumulator, element, first) -> {
            R r = first ? initialValue : accumulator;
            if (r == null) {
                throw new IllegalArgumentException("Param 'r' is null");
            }
            return operation.transform(r, element);
        });
    }


    /**
     * Groups elements from the [Grouping] source by key and applies the reducing [operation] to the elements of each group
     * sequentially starting from the second element of the group,
     * passing the previously accumulated value and the current element as arguments,
     * and stores the results in a new map.
     * An initial value of accumulator is the first element of the group.
     *
     * @param operation a function that is invoked on each subsequent element of the group with the following parameters:
     *                  - `key`: the key of the group this element belongs to;
     *                  - `accumulator`: the current value of the accumulator of the group;
     *                  - `element`: the element from the source being accumulated.
     * @return a [Map] associating the key of each group with the result of accumulating the group elements.
     */
    @NotNull
    public static <S, T extends S, K> Map<K, S> reduce(@Nullable Grouping<T, K> grouping, @NotNull final ReduceOperation<T, K, S> operation) {
        return aggregate(grouping, (key, accumulator, element, first) -> {
            if (first) {
                return element;
            } else {
                if (accumulator == null) {
                    throw new IllegalArgumentException("Param 'accumulator' is null");
                }
                return operation.operation(key, accumulator, element);
            }
        });
    }

    /**
     * Groups elements from the [Grouping] source by key and applies the reducing [operation] to the elements of each group
     * sequentially starting from the second element of the group,
     * passing the previously accumulated value and the current element as arguments,
     * and stores the results in the given [destination] map.
     * An initial value of accumulator is the first element of the group.
     * <p>
     * If the [destination] map already has a value corresponding to the key of some group,
     * that value is used as an initial value of the accumulator for that group and the first element of that group is also
     * subjected to the [operation].
     *
     * @param operation a function that is invoked on each subsequent element of the group with the following parameters:
     *                  - `accumulator`: the current value of the accumulator of the group;
     *                  - `element`: the element from the source being folded;
     * @return the [destination] map associating the key of each group with the result of accumulating the group elements.
     */
    @NotNull
    public static <S, T extends S, K, M extends Map<K, S>> M reduceTo(@Nullable Grouping<T, K> grouping, @NotNull M destination, @NotNull final ReduceOperation<T, K, S> operation) {
        return aggregateTo(grouping, destination, (key, accumulator, element, first) -> {
            if (first) {
                return element;
            } else {
                if (accumulator == null) {
                    throw new IllegalArgumentException("Param 'accumulator' is null");
                }
                return operation.operation(key, accumulator, element);
            }
        });
    }


    /**
     * Groups elements from the [Grouping] source by key and counts elements in each group.
     *
     * @return a [Map] associating the key of each group with the count of elements in the group.
     */
    @NotNull
    public static <T, K> Map<K, Integer> eachCount(@Nullable Grouping<T, K> grouping) {
        return mapValuesInPlace(foldTo(grouping, new LinkedHashMap<>(0), (k, t) -> new Ref.IntRef(), (key, accumulator, element) -> {
            accumulator.element += 1;
            return accumulator;
        }), kIntRefEntry -> kIntRefEntry.getValue().element);
    }

    /**
     * Groups elements from the [Grouping] source by key and counts elements in each group to the given [destination] map.
     * <p>
     * If the [destination] map already has a value corresponding to the key of some group,
     * that value is used as an initial value of the counter for that group.
     *
     * @return the [destination] map associating the key of each group with the count of elements in the group.
     */
    @NotNull
    public static <T, K, M extends Map<K, Integer>> M eachCountTo(@Nullable Grouping<T, K> grouping, @NotNull M destination) {
        return foldTo(grouping, destination, 0, (accumulator, t) -> accumulator + 1);
    }

    @NotNull
    private static <K, V, R> Map<K, R> mapValuesInPlace(@NotNull Map<K, V> map, @NotNull final Transformer<Map.Entry<K, V>, R> f) {
        // tricks with erased generics go here, do not repeat on reified platforms
        for (Map.Entry<K, V> entry : map.entrySet()) {
            //noinspection unchecked
            ((Map.Entry<K, R>) entry).setValue(f.transform(entry));
        }
        //noinspection unchecked
        return (Map<K, R>) map;
    }
}
