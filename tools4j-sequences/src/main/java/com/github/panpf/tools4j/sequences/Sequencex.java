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

import com.github.panpf.tools4j.common.*;
import com.github.panpf.tools4j.iterable.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.*;

/**
 * Sequence tool method
 */
public class Sequencex {

    private Sequencex() {
    }


    /* ******************************************* joinToArrayString ******************************************* */


    /**
     * Creates a string from all the elements separated using ', ' and using the given '[' and ']' if supplied.
     */
    @NotNull
    public static <T> String joinToArrayString(@Nullable Sequence<T> sequence, @NotNull Transformer<T, CharSequence> transform) {
        return joinToString(sequence, null, "[", "]", -1, null, transform);
    }

    /**
     * Creates a string from all the elements separated using ', ' and using the given '[' and ']' if supplied.
     */
    @NotNull
    public static <T> String joinToArrayString(@Nullable Sequence<T> sequence) {
        return joinToString(sequence, null, "[", "]", -1, null, null);
    }


    /*
     * *****************************************************************************************************************
     * From kotlin standard library
     * *****************************************************************************************************************
     */


    /* ******************************************* as ******************************************* */


    /**
     * Creates a sequence that returns all values from this enumeration. The sequence is constrained to be iterated only once.
     */
    @NotNull
    public static <T> Sequence<T> asSequence(@Nullable final Enumeration<T> enumeration) {
        return asSequence(new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return enumeration != null && enumeration.hasMoreElements();
            }

            @Override
            public T next() {
                if (enumeration != null) {
                    return enumeration.nextElement();
                } else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("remove");
            }
        });
    }

    /**
     * Creates a sequence that returns all elements from this iterator. The sequence is constrained to be iterated only once.
     */
    @NotNull
    public static <T> Sequence<T> asSequence(@Nullable final Iterator<T> iterator) {
        return constrainOnce(new Sequence<T>() {
            @NotNull
            @Override
            public Iterator<T> iterator() {
                return iterator != null ? iterator : Sequencex.<T>emptySequence().iterator();
            }
        });
    }

    /**
     * Creates a [Sequence] instance that wraps the original collection returning its elements when being iterated.
     */
    @NotNull
    public static <T> Sequence<T> asSequence(@Nullable final Iterable<T> iterable) {
        return new Sequence<T>() {
            @NotNull
            @Override
            public Iterator<T> iterator() {
                return iterable != null ? iterable.iterator() : Sequencex.<T>emptySequence().iterator();
            }
        };
    }

    /**
     * Returns this sequence as a [Sequence].
     */
    @NotNull
    public static <T> Sequence<T> asSequence(@Nullable Sequence<T> sequence) {
        return sequence != null ? sequence : Sequencex.<T>emptySequence();
    }

    /**
     * Creates a [Sequence] instance that wraps the original map returning its entries when being iterated.
     */
    @NotNull
    public static <K, V> Sequence<Map.Entry<K, V>> asSequence(@Nullable Map<K, V> map) {
        return asSequence((map != null ? map : new LinkedHashMap<K, V>(0)).entrySet());
    }


    /**
     * Creates a [Sequence] instance that wraps the original array returning its elements when being iterated.
     */
    @NotNull
    public static <T> Sequence<T> asSequence(@Nullable final T[] elements) {
        return new Sequence<T>() {
            @NotNull
            @Override
            public Iterator<T> iterator() {
                return new ArrayIterator<T>(elements);
            }
        };
    }

    /**
     * Creates a [Sequence] instance that wraps the original array returning its elements when being iterated.
     */
    @NotNull
    public static Sequence<Byte> asSequence(@Nullable final byte[] elements) {
        return new Sequence<Byte>() {
            @NotNull
            @Override
            public Iterator<Byte> iterator() {
                return new ArrayByteIterator(elements);
            }
        };
    }

    /**
     * Creates a [Sequence] instance that wraps the original array returning its elements when being iterated.
     */
    @NotNull
    public static Sequence<Short> asSequence(@Nullable final short[] elements) {
        return new Sequence<Short>() {
            @NotNull
            @Override
            public Iterator<Short> iterator() {
                return new ArrayShortIterator(elements);
            }
        };
    }

    /**
     * Creates a [Sequence] instance that wraps the original array returning its elements when being iterated.
     */
    @NotNull
    public static Sequence<Integer> asSequence(@Nullable final int[] elements) {
        return new Sequence<Integer>() {
            @NotNull
            @Override
            public Iterator<Integer> iterator() {
                return new ArrayIntIterator(elements);
            }
        };
    }

    /**
     * Creates a [Sequence] instance that wraps the original array returning its elements when being iterated.
     */
    @NotNull
    public static Sequence<Long> asSequence(@Nullable final long[] elements) {
        return new Sequence<Long>() {
            @NotNull
            @Override
            public Iterator<Long> iterator() {
                return new ArrayLongIterator(elements);
            }
        };
    }

    /**
     * Creates a [Sequence] instance that wraps the original array returning its elements when being iterated.
     */
    @NotNull
    public static Sequence<Float> asSequence(@Nullable final float[] elements) {
        return new Sequence<Float>() {
            @NotNull
            @Override
            public Iterator<Float> iterator() {
                return new ArrayFloatIterator(elements);
            }
        };
    }

    /**
     * Creates a [Sequence] instance that wraps the original array returning its elements when being iterated.
     */
    @NotNull
    public static Sequence<Double> asSequence(@Nullable final double[] elements) {
        return new Sequence<Double>() {
            @NotNull
            @Override
            public Iterator<Double> iterator() {
                return new ArrayDoubleIterator(elements);
            }
        };
    }

    /**
     * Creates a [Sequence] instance that wraps the original array returning its elements when being iterated.
     */
    @NotNull
    public static Sequence<Boolean> asSequence(@Nullable final boolean[] elements) {
        return new Sequence<Boolean>() {
            @NotNull
            @Override
            public Iterator<Boolean> iterator() {
                return new ArrayBooleanIterator(elements);
            }
        };
    }

    /**
     * Creates a [Sequence] instance that wraps the original array returning its elements when being iterated.
     */
    @NotNull
    public static Sequence<Character> asSequence(@Nullable final char[] elements) {
        return new Sequence<Character>() {
            @NotNull
            @Override
            public Iterator<Character> iterator() {
                return new ArrayCharIterator(elements);
            }
        };
    }

    /**
     * Creates an [Iterable] instance that wraps the original sequence returning its elements when being iterated.
     */
    @NotNull
    public static <T> Iterable<T> asIterable(@Nullable final Sequence<T> sequence) {
        return new Iterable<T>() {
            @NotNull
            @Override
            public Iterator<T> iterator() {
                return sequence != null ? sequence.iterator() : Sequencex.<T>emptySequence().iterator();
            }
        };
    }


    /* ******************************************* create ******************************************* */


    /**
     * Creates a sequence that returns the specified values.
     */
    @NotNull
    public static <T> Sequence<T> sequenceOf(T... elements) {
        return elements.length > 0 ? asSequence(elements) : Sequencex.<T>emptySequence();
    }

    /**
     * Returns an empty sequence.
     */
    @NotNull
    public static <T> Sequence<T> emptySequence() {
        //noinspection unchecked
        return (Sequence<T>) EmptySequence.INSTANCE;
    }

    /**
     * Returns a wrapper sequence that provides values of this sequence, but ensures it can be iterated only one time.
     * <p>
     * The operation is _intermediate_ and _stateless_.
     * <p>
     * [IllegalStateException] is thrown on iterating the returned sequence from the second time.
     */
    @NotNull
    public static <T> Sequence<T> constrainOnce(@NotNull Sequence<T> sequence) {
        return sequence instanceof ConstrainedOnceSequence ? sequence : new ConstrainedOnceSequence<T>(sequence);
    }

    /**
     * Returns a sequence which invokes the function to calculate the next value on each iteration until the function returns `null`.
     * <p>
     * The returned sequence is constrained to be iterated only once.
     *
     * @see #constrainOnce
     */
    @NotNull
    public static <T> Sequence<T> generateSequence(@NotNull final InitialValue<T> nextFunction) {
        return constrainOnce(new GeneratorSequence<T>(nextFunction, new NextValue<T>() {
            @Nullable
            @Override
            public T next(@NotNull T o) {
                return nextFunction.get();
            }
        }));
    }

    /**
     * Returns a sequence defined by the starting value [seed] and the function [nextFunction],
     * which is invoked to calculate the next value based on the previous one on each iteration.
     * <p>
     * The sequence produces values until it encounters first `null` value.
     * If [seed] is `null`, an empty sequence is produced.
     * <p>
     * The sequence can be iterated multiple times, each time starting with [seed].
     */
    @NotNull
    public static <T> Sequence<T> generateSequence(@Nullable final T seed, @NotNull NextValue<T> nextFunction) {
        //noinspection unchecked
        return seed == null ? (Sequence<T>) EmptySequence.INSTANCE : new GeneratorSequence<T>(new InitialValue<T>() {
            @NotNull
            @Override
            public T get() {
                return seed;
            }
        }, nextFunction);
    }

    /**
     * Returns a sequence defined by the function [seedFunction], which is invoked to produce the starting value,
     * and the [nextFunction], which is invoked to calculate the next value based on the previous one on each iteration.
     * <p>
     * The sequence produces values until it encounters first `null` value.
     * If [seedFunction] returns `null`, an empty sequence is produced.
     * <p>
     * The sequence can be iterated multiple times.
     */
    @NotNull
    public static <T> Sequence<T> generateSequence(@NotNull InitialValue<T> seedFunction, @NotNull NextValue<T> nextFunction) {
        return new GeneratorSequence<T>(seedFunction, nextFunction);
    }


    /* ******************************************* flatten ******************************************* */


    /**
     * Returns a sequence of all elements from all sequences in this sequence.
     * <p>
     * The operation is _intermediate_ and _stateless_.
     */
    public static <T> Sequence<T> flatten(@Nullable Sequence<Sequence<T>> sequence) {
        return flatten(sequence, new Transformer<Sequence<T>, Iterator<T>>() {
            @NotNull
            @Override
            public Iterator<T> transform(@NotNull Sequence<T> tSequence) {
                return tSequence.iterator();
            }
        });
    }

    /**
     * Returns a sequence of all elements from all iterables in this sequence.
     * <p>
     * The operation is _intermediate_ and _stateless_.
     */
    @NotNull
    public static <T> Sequence<T> flattenOfIterable(@Nullable Sequence<Iterable<T>> sequence) {
        return flatten(sequence, new Transformer<Iterable<T>, Iterator<T>>() {
            @NotNull
            @Override
            public Iterator<T> transform(@NotNull Iterable<T> ts) {
                return ts.iterator();
            }
        });
    }

    @NotNull
    private static <T, R> Sequence<R> flatten(@Nullable Sequence<T> sequence, @NotNull Transformer<T, Iterator<R>> transformer) {
        if (sequence instanceof TransformingSequence) {
            return ((TransformingSequence<?, T>) sequence).flatten(transformer);
        }
        return new FlatteningSequence<T, T, R>(sequence, new Transformer<T, T>() {
            @NotNull
            @Override
            public T transform(@NotNull T t) {
                return t;
            }
        }, transformer);
    }


    /* ******************************************* contains ******************************************* */


    /**
     * Returns `true` if [element] is found in the sequence.
     * <p>
     * The operation is _terminal_.
     */
    public static <T> boolean contains(@Nullable Sequence<T> sequence, @NotNull T element) {
        return indexOf(sequence, element) >= 0;
    }


    /* ******************************************* elementAt ******************************************* */


    /**
     * Returns an element at the given [index] or throws an [IndexOutOfBoundsException] if the [index] is out of bounds of this sequence.
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T> T elementAt(@Nullable Sequence<T> sequence, int index) {
        return elementAtOrElse(sequence, index, new IndexedDefaultValue<T>() {
            @NotNull
            @Override
            public T get(int index) {
                throw new IndexOutOfBoundsException("Sequence doesn't contain element at index " + index + ".");
            }
        });
    }

    /**
     * Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this sequence.
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T> T elementAtOrElse(@Nullable Sequence<T> sequence, int index, @NotNull IndexedDefaultValue<T> defaultValue) {
        if (index < 0) return defaultValue.get(index);
        int count = 0;
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T element = iterator.next();
                if (index == count++)
                    return element;
            }
        }
        return defaultValue.get(index);
    }

    /**
     * Returns an element at the given [index] or `null` if the [index] is out of bounds of this sequence.
     * <p>
     * The operation is _terminal_.
     */
    @Nullable
    public static <T> T elementAtOrNull(@Nullable Sequence<T> sequence, int index) {
        if (index < 0) return null;
        int count = 0;
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T element = iterator.next();
                if (index == count++)
                    return element;
            }
        }
        return null;
    }


    /* ******************************************* find ******************************************* */


    /**
     * Returns the first element matching the given [predicate], or `null` if no such element was found.
     * <p>
     * The operation is _terminal_.
     */
    @Nullable
    public static <T> T find(@Nullable Sequence<T> sequence, @NotNull Predicate<T> predicate) {
        return firstOrNull(sequence, predicate);
    }

    /**
     * Returns the last element matching the given [predicate], or `null` if no such element was found.
     * <p>
     * The operation is _terminal_.
     */
    @Nullable
    public static <T> T findLast(@Nullable Sequence<T> sequence, @NotNull Predicate<T> predicate) {
        return lastOrNull(sequence, predicate);
    }


    /* ******************************************* first ******************************************* */


    /**
     * Returns first element.
     *
     * @throws NoSuchElementException if the sequence is empty.
     *                                <p>
     *                                The operation is _terminal_.
     */
    @NotNull
    public static <T> T first(@Nullable Sequence<T> sequence) {
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator == null || !iterator.hasNext())
            throw new NoSuchElementException("Sequence is empty.");
        return iterator.next();
    }

    /**
     * Returns the first element matching the given [predicate].
     *
     * @throws NoSuchElementException if no such element is found.
     *                                <p>
     *                                The operation is _terminal_.
     */
    @NotNull
    public static <T> T first(@Nullable Sequence<T> sequence, @NotNull Predicate<T> predicate) {
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        while (iterator != null && iterator.hasNext()) {
            T element = iterator.next();
            if (predicate.accept(element)) return element;
        }
        throw new NoSuchElementException("Sequence contains no element matching the predicate.");
    }

    /**
     * Returns the first element, or `null` if the sequence is empty.
     * <p>
     * The operation is _terminal_.
     */
    @Nullable
    public static <T> T firstOrNull(@Nullable Sequence<T> sequence) {
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator == null || !iterator.hasNext())
            return null;
        return iterator.next();
    }

    /**
     * Returns the first element matching the given [predicate], or `null` if element was not found.
     * <p>
     * The operation is _terminal_.
     */
    @Nullable
    public static <T> T firstOrNull(@Nullable Sequence<T> sequence, @NotNull Predicate<T> predicate) {
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T element = iterator.next();
                if (predicate.accept(element)) return element;
            }
        }
        return null;
    }


    /* ******************************************* indexOf ******************************************* */


    /**
     * Returns first index of [element], or -1 if the sequence does not contain element.
     * <p>
     * The operation is _terminal_.
     */
    public static <T> int indexOf(@Nullable Sequence<T> sequence, @NotNull T element) {
        int index = 0;
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T item = iterator.next();
                if (element == item)
                    return index;
                index++;
            }
        }
        return -1;
    }

    /**
     * Returns index of the first element matching the given [predicate], or -1 if the sequence does not contain such element.
     * <p>
     * The operation is _terminal_.
     */
    public static <T> int indexOfFirst(@Nullable Sequence<T> sequence, @NotNull Predicate<T> predicate) {
        int index = 0;
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T item = iterator.next();
                if (predicate.accept(item))
                    return index;
                index++;
            }
        }
        return -1;
    }

    /**
     * Returns index of the last element matching the given [predicate], or -1 if the sequence does not contain such element.
     * <p>
     * The operation is _terminal_.
     */
    public static <T> int indexOfLast(@Nullable Sequence<T> sequence, @NotNull Predicate<T> predicate) {
        int lastIndex = -1;
        int index = 0;
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T item = iterator.next();
                if (predicate.accept(item))
                    lastIndex = index;
                index++;
            }
        }
        return lastIndex;
    }


    /* ******************************************* last ******************************************* */


    /**
     * Returns the last element.
     *
     * @throws NoSuchElementException if the sequence is empty.
     *                                <p>
     *                                The operation is _terminal_.
     */
    @NotNull
    public static <T> T last(@Nullable Sequence<T> sequence) {
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator == null || !iterator.hasNext())
            throw new NoSuchElementException("Sequence is empty.");
        T last = iterator.next();
        while (iterator.hasNext())
            last = iterator.next();
        return last;
    }

    /**
     * Returns the last element matching the given [predicate].
     *
     * @throws NoSuchElementException if no such element is found.
     *                                <p>
     *                                The operation is _terminal_.
     */
    @NotNull
    public static <T> T last(@Nullable Sequence<T> sequence, @NotNull Predicate<T> predicate) {
        @Nullable
        T last = null;
        boolean found = false;
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T element = iterator.next();
                if (predicate.accept(element)) {
                    last = element;
                    found = true;
                }
            }
        }
        if (!found) throw new NoSuchElementException("Sequence contains no element matching the predicate.");
        return last;
    }

    /**
     * Returns last index of [element], or -1 if the sequence does not contain element.
     * <p>
     * The operation is _terminal_.
     */
    public static <T> int lastIndexOf(@Nullable Sequence<T> sequence, @NotNull T element) {
        int lastIndex = -1;
        int index = 0;
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T item = iterator.next();
                if (element == item)
                    lastIndex = index;
                index++;
            }
        }
        return lastIndex;
    }

    /**
     * Returns the last element, or `null` if the sequence is empty.
     * <p>
     * The operation is _terminal_.
     */
    @Nullable
    public static <T> T lastOrNull(@Nullable Sequence<T> sequence) {
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator == null || !iterator.hasNext())
            return null;
        T last = iterator.next();
        while (iterator.hasNext())
            last = iterator.next();
        return last;
    }

    /**
     * Returns the last element matching the given [predicate], or `null` if no such element was found.
     * <p>
     * The operation is _terminal_.
     */
    @Nullable
    public static <T> T lastOrNull(@Nullable Sequence<T> sequence, @NotNull Predicate<T> predicate) {
        @Nullable
        T last = null;
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T element = iterator.next();
                if (predicate.accept(element)) last = element;
            }
        }
        return last;
    }


    /* ******************************************* single ******************************************* */


    /**
     * Returns the single element, or throws an exception if the sequence is empty or has more than one element.
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T> T single(@Nullable Sequence<T> sequence) {
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator == null || !iterator.hasNext())
            throw new NoSuchElementException("Sequence is empty.");
        T single = iterator.next();
        if (iterator.hasNext())
            throw new IllegalArgumentException("Sequence has more than one element.");
        return single;
    }

    /**
     * Returns the single element matching the given [predicate], or throws exception if there is no or more than one matching element.
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T> T single(@Nullable Sequence<T> sequence, @NotNull Predicate<T> predicate) {
        @Nullable
        T single = null;
        boolean found = false;
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T element = iterator.next();
                if (predicate.accept(element)) {
                    if (found) throw new IllegalArgumentException("Sequence contains more than one matching element.");
                    single = element;
                    found = true;
                }
            }
        }
        if (!found) throw new NoSuchElementException("Sequence contains no element matching the predicate.");
        return single;
    }

    /**
     * Returns single element, or `null` if the sequence is empty or has more than one element.
     * <p>
     * The operation is _terminal_.
     */
    @Nullable
    public static <T> T singleOrNull(@Nullable Sequence<T> sequence) {
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator == null || !iterator.hasNext())
            return null;
        T single = iterator.next();
        if (iterator.hasNext())
            return null;
        return single;
    }

    /**
     * Returns the single element matching the given [predicate], or `null` if element was not found or more than one element was found.
     * <p>
     * The operation is _terminal_.
     */
    @Nullable
    public static <T> T singleOrNull(@Nullable Sequence<T> sequence, @NotNull Predicate<T> predicate) {
        @Nullable
        T single = null;
        boolean found = false;
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T element = iterator.next();
                if (predicate.accept(element)) {
                    if (found) return null;
                    single = element;
                    found = true;
                }
            }
        }
        if (!found) return null;
        return single;
    }


    /* ******************************************* drop ******************************************* */


    /**
     * Returns a sequence containing all elements except first [n] elements.
     * <p>
     * The operation is _intermediate_ and _stateless_.
     *
     * @throws IllegalArgumentException if [n] is negative.
     */
    @NotNull
    public static <T> Sequence<T> drop(@Nullable Sequence<T> sequence, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than to zero.");
        }
        if (n == 0) {
            return sequence != null ? sequence : Sequencex.<T>emptySequence();
        } else if (sequence instanceof DropTakeSequence) {
            return ((DropTakeSequence<T>) sequence).drop(n);
        } else {
            return new DropSequence<T>(sequence, n);
        }
    }

    /**
     * Returns a sequence containing all elements except first elements that satisfy the given [predicate].
     * <p>
     * The operation is _intermediate_ and _stateless_.
     */
    @NotNull
    public static <T> Sequence<T> dropWhile(@Nullable Sequence<T> sequence, @NotNull Predicate<T> predicate) {
        return new DropWhileSequence<T>(sequence, predicate);
    }


    /* ******************************************* take ******************************************* */


    /**
     * Returns a sequence containing first [n] elements.
     * <p>
     * The operation is _intermediate_ and _stateless_.
     */
    @NotNull
    public static <T> Sequence<T> take(@Nullable Sequence<T> sequence, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than to zero.");
        }
        if (n == 0) {
            return Sequencex.emptySequence();
        } else if (sequence instanceof DropTakeSequence) {
            return ((DropTakeSequence<T>) sequence).take(n);
        } else {
            return new TakeSequence<T>(sequence, n);
        }
    }

    /**
     * Returns a sequence containing first elements satisfying the given [predicate].
     * <p>
     * The operation is _intermediate_ and _stateless_.
     */
    @NotNull
    public static <T> Sequence<T> takeWhile(@Nullable Sequence<T> sequence, @NotNull Predicate<T> predicate) {
        return new TakeWhileSequence<T>(sequence, predicate);
    }


    /* ******************************************* filter ******************************************* */


    /**
     * Returns a sequence containing only elements matching the given [predicate].
     * <p>
     * The operation is _intermediate_ and _stateless_.
     */
    @NotNull
    public static <T> Sequence<T> filter(@Nullable Sequence<T> sequence, @NotNull Predicate<T> predicate) {
        return new FilteringSequence<T>(sequence, true, predicate);
    }

    /**
     * Appends all elements matching the given [predicate] to the given [destination].
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T, C extends Collection<T>> C filterTo(@Nullable Sequence<T> sequence, @NotNull C destination, Predicate<T> predicate) {
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T element = iterator.next();
                if (predicate.accept(element)) destination.add(element);
            }
        }
        return destination;
    }

    /**
     * Returns a sequence containing only elements matching the given [predicate].
     *
     * @param predicate function that takes the index of an element and the element itself
     *                  and returns the result of predicate evaluation on the element.
     *                  <p>
     *                  The operation is _intermediate_ and _stateless_.
     */
    @NotNull
    public static <T> Sequence<T> filterIndexed(@Nullable Sequence<T> sequence, @NotNull final IndexedPredicate<T> predicate) {
        return new TransformingSequence<IndexedValue<T>, T>(
                new FilteringSequence<IndexedValue<T>>(new IndexingSequence<T>(sequence), true, new Predicate<IndexedValue<T>>() {
                    @Override
                    public boolean accept(@NotNull IndexedValue<T> it) {
                        return predicate.accept(it.index, it.value);
                    }
                }),
                new Transformer<IndexedValue<T>, T>() {
                    @NotNull
                    @Override
                    public T transform(@NotNull IndexedValue<T> it) {
                        return it.value;
                    }
                }
        );
    }

    /**
     * Appends all elements matching the given [predicate] to the given [destination].
     *
     * @param predicate function that takes the index of an element and the element itself
     *                  and returns the result of predicate evaluation on the element.
     *                  <p>
     *                  The operation is _terminal_.
     */
    @NotNull
    public static <T, C extends Collection<T>> C filterIndexedTo(@Nullable Sequence<T> sequence, @NotNull final C destination, @NotNull final IndexedPredicate<T> predicate) {
        forEachIndexed(sequence, new IndexedAction<T>() {
            @Override
            public void action(int index, @NotNull T element) {
                if (predicate.accept(index, element)) destination.add(element);
            }
        });
        return destination;
    }

//    /**
//     * Returns a sequence containing all elements that are instances of specified type parameter R.
//     *
//     * The operation is _intermediate_ and _stateless_.
//     */
//    @NotNull
//    public static <R> Sequence<R> Sequence<*>.filterIsInstance() {
//        @Suppress("UNCHECKED_CAST")
//        return filter { it is R } as Sequence<R>
//    }
//
//    /**
//     * Appends all elements that are instances of specified type parameter R to the given [destination].
//     *
//     * The operation is _terminal_.
//     */
//    public static <reified R, C : Collection<in R>> Sequence<*>.filterIsInstanceTo(@NotNull C destination): C {
//        for (element in this) if (element is R) destination.add(element)
//        return destination
//    }

    /**
     * Returns a sequence containing all elements that are instances of specified class.
     * <p>
     * The operation is _intermediate_ and _stateless_.
     */
    @NotNull
    public static <T, R> Sequence<R> filterIsInstance(@Nullable Sequence<T> sequence, @NotNull final Class<R> clazz) {
        //noinspection unchecked
        return (Sequence<R>) filter(sequence, new Predicate<T>() {
            @Override
            public boolean accept(@NotNull T it) {
                return clazz.isInstance(it);
            }
        });
    }

    /**
     * Appends all elements that are instances of specified class to the given [destination].
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T, C extends Collection<R>, R> C filterIsInstanceTo(
            @Nullable Sequence<T> sequence, @NotNull C destination, @NotNull final Class<R> klass) {
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T element = iterator.next();
                if (klass.isInstance(element)) //noinspection unchecked
                    destination.add((R) element);
            }
        }
        return destination;
    }

    /**
     * Returns a sequence containing all elements not matching the given [predicate].
     * <p>
     * The operation is _intermediate_ and _stateless_.
     */
    @NotNull
    public static <T> Sequence<T> filterNot(@Nullable Sequence<T> sequence, @NotNull Predicate<T> predicate) {
        return new FilteringSequence<T>(sequence, false, predicate);
    }

    /**
     * Appends all elements not matching the given [predicate] to the given [destination].
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T, C extends Collection<T>> C filterNotTo(@Nullable Sequence<T> sequence, @NotNull C destination, @NotNull Predicate<T> predicate) {
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T element = iterator.next();
                if (!predicate.accept(element)) destination.add(element);
            }
        }
        return destination;
    }

    /**
     * Returns a sequence containing all elements that are not `null`.
     * <p>
     * The operation is _intermediate_ and _stateless_.
     */
    @NotNull
    public static <T> Sequence<T> filterNotNull(@Nullable Sequence<T> sequence) {
        return filterNot(sequence, new Predicate<T>() {
            @Override
            public boolean accept(@NotNull T t) {
                //noinspection ConstantConditions
                return t == null;
            }
        });
    }

    /**
     * Appends all elements that are not `null` to the given [destination].
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <C extends Collection<T>, T> C filterNotNullTo(@Nullable Sequence<T> sequence, @NotNull C destination) {
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T element = iterator.next();
                if (element != null) destination.add(element);
            }
        }
        return destination;
    }


    /* ******************************************* sorted ******************************************* */


    /**
     * Returns a sequence that yields elements of this sequence sorted according to their natural sort order.
     * <p>
     * The operation is _intermediate_ and _stateful_.
     */
    @NotNull
    public static <T extends Comparable<T>> Sequence<T> sorted(@NotNull final Sequence<T> sequence) {
        return new Sequence<T>() {
            @NotNull
            @Override
            public Iterator<T> iterator() {
                List<T> sortedList = toMutableList(sequence);
                Collections.sort(sortedList);
                return sortedList.iterator();
            }
        };
    }

    /**
     * Returns a sequence that yields elements of this sequence sorted descending according to their natural sort order.
     * <p>
     * The operation is _intermediate_ and _stateful_.
     */
    @NotNull
    public static <T extends Comparable<T>> Sequence<T> sortedDescending(@Nullable Sequence<T> sequence) {
        return sortedWith(sequence, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o2.compareTo(o1);
            }
        });
    }

    /**
     * Returns a sequence that yields elements of this sequence sorted according to natural sort order of the value returned by specified [selector] function.
     * <p>
     * The operation is _intermediate_ and _stateful_.
     */
    @NotNull
    public static <T, R extends Comparable<R>> Sequence<T> sortedBy(@Nullable Sequence<T> sequence, @NotNull final NullableTransformer<T, R> transformer) {
        return sortedWith(sequence, new Comparator<T>() {
            @Override
            public int compare(@Nullable T o1, @Nullable T o2) {
                R r1 = o1 != null ? transformer.transform(o1) : null;
                R r2 = o2 != null ? transformer.transform(o2) : null;
                return r1 == r2 ? 0 : (r1 == null ? -1 : (r2 == null ? 1 : (r1.compareTo(r2))));
            }
        });
    }

    /**
     * Returns a sequence that yields elements of this sequence sorted descending according to natural sort order of the value returned by specified [selector] function.
     * <p>
     * The operation is _intermediate_ and _stateful_.
     */
    @NotNull
    public static <T, R extends Comparable<R>> Sequence<T> sortedByDescending(@Nullable Sequence<T> sequence, @NotNull final NullableTransformer<T, R> transformer) {
        return sortedWith(sequence, new Comparator<T>() {
            @Override
            public int compare(@Nullable T o1, @Nullable T o2) {
                R r1 = o2 != null ? transformer.transform(o2) : null;
                R r2 = o1 != null ? transformer.transform(o1) : null;
                return r1 == r2 ? 0 : (r1 == null ? -1 : (r2 == null ? 1 : (r1.compareTo(r2))));
            }
        });
    }

    /**
     * Returns a sequence that yields elements of this sequence sorted according to the specified [comparator].
     * <p>
     * The operation is _intermediate_ and _stateful_.
     */
    @NotNull
    public static <T> Sequence<T> sortedWith(@Nullable final Sequence<T> sequence, @NotNull final Comparator<T> comparator) {
        return new Sequence<T>() {
            @NotNull
            @Override
            public Iterator<T> iterator() {
                List<T> sortedList = toMutableList(sequence);
                Collections.sort(sortedList, comparator);
                return sortedList.iterator();
            }
        };
    }


    /* ******************************************* associate ******************************************* */


    /**
     * Returns a [Map] containing key-value pairs provided by [transform] function
     * applied to elements of the given sequence.
     * <p>
     * If any of two pairs would have the same key the last one gets added to the map.
     * <p>
     * The returned map preserves the entry iteration order of the original sequence.
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T, K, V> Map<K, V> associate(@Nullable Sequence<T> sequence, @NotNull Transformer<T, Pair<K, V>> transform) {
        return associateTo(sequence, new LinkedHashMap<K, V>(), transform);
    }

    /**
     * Returns a [Map] containing the elements from the given sequence indexed by the key
     * returned from [keySelector] function applied to each element.
     * <p>
     * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
     * <p>
     * The returned map preserves the entry iteration order of the original sequence.
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T, K> Map<K, T> associateBy(@Nullable Sequence<T> sequence, @NotNull Transformer<T, K> keySelector) {
        return associateByTo(sequence, new LinkedHashMap<K, T>(), keySelector);
    }

    /**
     * Returns a [Map] containing the values provided by [valueTransform] and indexed by [keySelector] functions applied to elements of the given sequence.
     * <p>
     * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
     * <p>
     * The returned map preserves the entry iteration order of the original sequence.
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T, K, V> Map<K, V> associateBy(@Nullable Sequence<T> sequence, @NotNull Transformer<T, K> keySelector, @NotNull Transformer<T, V> valueTransform) {
        return associateByTo(sequence, new LinkedHashMap<K, V>(), keySelector, valueTransform);
    }

    /**
     * Populates and returns the [destination] mutable map with key-value pairs
     * provided by [transform] function applied to each element of the given sequence.
     * <p>
     * If any of two pairs would have the same key the last one gets added to the map.
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T, K, V, M extends Map<K, V>> M associateTo(@Nullable Sequence<T> sequence, @NotNull M destination,
                                                               @NotNull Transformer<T, Pair<K, V>> transform) {
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T element = iterator.next();
                Pair<K, V> pair = transform.transform(element);
                destination.put(pair.first, pair.second);
            }
        }
        return destination;
    }

    /**
     * Populates and returns the [destination] mutable map with key-value pairs,
     * where key is provided by the [keySelector] function applied to each element of the given sequence
     * and value is the element itself.
     * <p>
     * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T, K, M extends Map<K, T>> M associateByTo(@Nullable Sequence<T> sequence, @NotNull M destination, @NotNull Transformer<T, K> keySelector) {
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T element = iterator.next();
                destination.put(keySelector.transform(element), element);
            }
        }
        return destination;
    }

    /**
     * Populates and returns the [destination] mutable map with key-value pairs,
     * where key is provided by the [keySelector] function and
     * and value is provided by the [valueTransform] function applied to elements of the given sequence.
     * <p>
     * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T, K, V, M extends Map<K, V>> M associateByTo(@Nullable Sequence<T> sequence, @NotNull M destination, @NotNull Transformer<T, K> keySelector, @NotNull Transformer<T, V> valueTransform) {
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T element = iterator.next();
                destination.put(keySelector.transform(element), valueTransform.transform(element));
            }
        }
        return destination;
    }


    /* ******************************************* to ******************************************* */


    /**
     * Appends all elements to the given [destination] collection.
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T, C extends Collection<T>> C toCollection(@Nullable Sequence<T> sequence, @NotNull C destination) {
        if (sequence != null) {
            Iterator<T> iterator = sequence.iterator();
            while (iterator.hasNext()) {
                T element = iterator.next();
                destination.add(element);
            }
        }
        return destination;
    }

//    /**
//     * Returns a [List] containing all elements.
//     * <p>
//     * The operation is _terminal_.
//     */
//    public static <T> List<T> toList(@Nullable Sequence<T> sequence) {
//        return toCollection(sequence, new ArrayList<T>());
//    }

    /**
     * Returns a [MutableList] filled with all elements of this sequence.
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T> List<T> toMutableList(@Nullable Sequence<T> sequence) {
        return toCollection(sequence, new ArrayList<T>());
    }

//    /**
//     * Returns a [Set] of all elements.
//     * <p>
//     * The returned set preserves the element iteration order of the original sequence.
//     * <p>
//     * The operation is _terminal_.
//     */
//    @NotNull
//    public static <T> Set<T> toSet(@Nullable Sequence<T> sequence) {
//        return toCollection(sequence, new LinkedHashSet<T>());
//    }

    /**
     * Returns a mutable set containing all distinct elements from the given sequence.
     * <p>
     * The returned set preserves the element iteration order of the original sequence.
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T> Set<T> toMutableSet(@Nullable Sequence<T> sequence) {
        Set<T> set = new LinkedHashSet<T>();
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T element = iterator.next();
                set.add(element);
            }
        }
        return set;
    }

    /**
     * Returns a [HashSet] of all elements.
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T> HashSet<T> toHashSet(@Nullable Sequence<T> sequence) {
        return toCollection(sequence, new HashSet<T>());
    }

    /**
     * Returns a [SortedSet][java.util.SortedSet] of all elements.
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T extends Comparable<T>> SortedSet<T> toSortedSet(@Nullable Sequence<T> sequence) {
        return toCollection(sequence, new TreeSet<T>());
    }

    /**
     * Returns a [SortedSet][java.util.SortedSet] of all elements.
     * <p>
     * Elements in the set returned are sorted according to the given [comparator].
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T> SortedSet<T> toSortedSet(@Nullable Sequence<T> sequence, @NotNull Comparator<T> comparator) {
        return toCollection(sequence, new TreeSet<T>(comparator));
    }


    /**
     * Populates and returns the [destination] mutable map with key-value pairs from the given sequence of pairs.
     */
    @NotNull
    public static <K, V, M extends Map<K, V>> M toMap(@Nullable Sequence<Pair<K, V>> pairs, @NotNull M destination) {
        if (pairs != null) {
            Iterator<Pair<K, V>> iterator = pairs.iterator();
            while (iterator.hasNext()) {
                Pair<K, V> pair = iterator.next();
                destination.put(pair.first, pair.second);
            }
        }
        return destination;
    }

    /**
     * Returns a new map containing all key-value pairs from the given sequence of pairs.
     * <p>
     * The returned map preserves the entry iteration order of the original sequence.
     */
    @NotNull
    public static <K, V> Map<K, V> toMap(@Nullable Sequence<Pair<K, V>> pairs) {
        return toMap(pairs, new LinkedHashMap<K, V>());
    }


    /* ******************************************* flatMap ******************************************* */


    /**
     * Returns a single sequence of all elements from results of [transform] function being invoked on each element of original sequence.
     * <p>
     * The operation is _intermediate_ and _stateless_.
     */
    @NotNull
    public static <T, R> Sequence<R> flatMap(@Nullable Sequence<T> sequence, @NotNull Transformer<T, Sequence<R>> transform) {
        return new FlatteningSequence<T, Sequence<R>, R>(sequence, transform, new Transformer<Sequence<R>, Iterator<R>>() {
            @NotNull
            @Override
            public Iterator<R> transform(@NotNull Sequence<R> sequence) {
                return sequence.iterator();
            }
        });
    }


    /**
     * Returns a single sequence of all elements from results of [transform] function being invoked on each element of original sequence.
     * <p>
     * The operation is _intermediate_ and _stateless_.
     */
    @NotNull
    public static <T, R> Sequence<R> flatMapOfIterable(@Nullable Sequence<T> sequence, @NotNull Transformer<T, Iterable<R>> transform) {
        return new FlatteningSequence<T, Iterable<R>, R>(sequence, transform, new Transformer<Iterable<R>, Iterator<R>>() {
            @NotNull
            @Override
            public Iterator<R> transform(@NotNull Iterable<R> iterable) {
                return iterable.iterator();
            }
        });
    }

    /**
     * Appends all elements yielded from results of [transform] function being invoked on each element of original sequence, to the given [destination].
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T, R, C extends Collection<R>> C flatMapTo(
            @Nullable Sequence<T> sequence, @NotNull C destination, @NotNull Transformer<T, Sequence<R>> transform) {
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T element = iterator.next();
                Sequence<R> list = transform.transform(element);
                Iterator<R> listIterator = list.iterator();
                while (listIterator.hasNext()) {
                    R item = listIterator.next();
                    destination.add(item);
                }
            }
        }
        return destination;
    }

    /**
     * Appends all elements yielded from results of [transform] function being invoked on each element of original sequence, to the given [destination].
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T, R, C extends Collection<R>> C flatMapOfIterableTo(
            @Nullable Sequence<T> sequence, @NotNull C destination, @NotNull Transformer<T, Iterable<R>> transform) {
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T element = iterator.next();
                Iterable<R> list = transform.transform(element);
                for (R item : list) {
                    destination.add(item);
                }
            }
        }
        return destination;
    }

    /**
     * Returns a single sequence of all elements yielded from results of [transform] function being invoked on each element
     * and its index in the original sequence.
     * <p>
     * The operation is _intermediate_ and _stateless_.
     */
    @NotNull
    public static <T, R> Sequence<R> flatMapIndexedOfIterable(
            @Nullable Sequence<T> sequence, @NotNull IndexedTransformer<T, Iterable<R>> transform) {
        return new IndexedFlatteningSequence<T, Iterable<R>, R>(sequence, transform, new Transformer<Iterable<R>, Iterator<R>>() {
            @NotNull
            @Override
            public Iterator<R> transform(@NotNull Iterable<R> iterable) {
                return iterable.iterator();
            }
        });
    }

    /**
     * Returns a single sequence of all elements yielded from results of [transform] function being invoked on each element
     * and its index in the original sequence.
     * <p>
     * The operation is _intermediate_ and _stateless_.
     */
    @NotNull
    public static <T, R> Sequence<R> flatMapIndexed(
            @Nullable Sequence<T> sequence, @NotNull IndexedTransformer<T, Sequence<R>> transform) {
        return new IndexedFlatteningSequence<T, Sequence<R>, R>(sequence, transform, new Transformer<Sequence<R>, Iterator<R>>() {
            @NotNull
            @Override
            public Iterator<R> transform(@NotNull Sequence<R> iterable) {
                return iterable.iterator();
            }
        });
    }

    /**
     * Appends all elements yielded from results of [transform] function being invoked on each element
     * and its index in the original sequence, to the given [destination].
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T, R, C extends Collection<R>> C flatMapIndexedOfIterableTo(
            @Nullable Sequence<T> sequence, @NotNull C destination, @NotNull IndexedTransformer<T, Iterable<R>> transform) {
        int index = 0;
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T element = iterator.next();
                Iterable<R> list = transform.transform(index++, element);
                for (R item : list) {
                    destination.add(item);
                }
            }
        }
        return destination;
    }

    /**
     * Appends all elements yielded from results of [transform] function being invoked on each element
     * and its index in the original sequence, to the given [destination].
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T, R, C extends Collection<R>> C flatMapIndexedTo(
            @Nullable Sequence<T> sequence, @NotNull C destination, @NotNull IndexedTransformer<T, Sequence<R>> transform) {
        int index = 0;
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T element = iterator.next();
                Sequence<R> list = transform.transform(index++, element);
                Iterator<R> listIterator = list.iterator();
                while (listIterator.hasNext()) {
                    R item = listIterator.next();
                    destination.add(item);
                }
            }
        }
        return destination;
    }


    /* ******************************************* group ******************************************* */


    /**
     * Groups elements of the original sequence by the key returned by the given [keySelector] function
     * applied to each element and returns a map where each group key is associated with a list of corresponding elements.
     * <p>
     * The returned map preserves the entry iteration order of the keys produced from the original sequence.
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T, K> Map<K, List<T>> groupBy(@Nullable Sequence<T> sequence, @NotNull Transformer<T, K> keySelector) {
        return groupByTo(sequence, new LinkedHashMap<K, List<T>>(), keySelector);
    }

    /**
     * Groups values returned by the [valueTransform] function applied to each element of the original sequence
     * by the key returned by the given [keySelector] function applied to the element
     * and returns a map where each group key is associated with a list of corresponding values.
     * <p>
     * The returned map preserves the entry iteration order of the keys produced from the original sequence.
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T, K, V> Map<K, List<V>> groupBy(
            @Nullable Sequence<T> sequence, @NotNull Transformer<T, K> keySelector, @NotNull Transformer<T, V> valueTransform) {
        return groupByTo(sequence, new LinkedHashMap<K, List<V>>(), keySelector, valueTransform);
    }

    /**
     * Groups elements of the original sequence by the key returned by the given [keySelector] function
     * applied to each element and puts to the [destination] map each group key associated with a list of corresponding elements.
     *
     * @return The [destination] map.
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T, K, M extends Map<K, List<T>>> M groupByTo(
            @Nullable Sequence<T> sequence, @NotNull M destination, @NotNull Transformer<T, K> keySelector) {
        DefaultValue<List<T>> defaultValue = new DefaultValue<List<T>>() {
            @NotNull
            @Override
            public List<T> get() {
                return new ArrayList<T>();
            }
        };
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T element = iterator.next();
                K key = keySelector.transform(element);

                List<T> list = destination.get(key);
                if (list == null) {
                    list = defaultValue.get();
                    destination.put(key, list);
                }
                list.add(element);
            }
        }
        return destination;
    }

    /**
     * Groups values returned by the [valueTransform] function applied to each element of the original sequence
     * by the key returned by the given [keySelector] function applied to the element
     * and puts to the [destination] map each group key associated with a list of corresponding values.
     *
     * @return The [destination] map.
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T, K, V, M extends Map<K, List<V>>> M groupByTo(
            @Nullable Sequence<T> sequence, @NotNull M destination, @NotNull Transformer<T, K> keySelector, @NotNull Transformer<T, V> valueTransform) {
        DefaultValue<List<V>> defaultValue = new DefaultValue<List<V>>() {
            @NotNull
            @Override
            public List<V> get() {
                return new ArrayList<V>();
            }
        };
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T element = iterator.next();
                K key = keySelector.transform(element);
                List<V> list = destination.get(key);
                if (list == null) {
                    list = defaultValue.get();
                    destination.put(key, list);
                }
                list.add(valueTransform.transform(element));
            }
        }
        return destination;
    }


    /* ******************************************* map ******************************************* */


    /**
     * Returns a sequence containing the results of applying the given [transform] function
     * to each element in the original sequence.
     * <p>
     * The operation is _intermediate_ and _stateless_.
     */
    @NotNull
    public static <T, R> Sequence<R> map(@Nullable Sequence<T> sequence, @NotNull Transformer<T, R> transform) {
        return new TransformingSequence<T, R>(sequence, transform);
    }

    /**
     * Returns a sequence containing only the non-null results of applying the given [transform] function
     * to each element in the original sequence.
     * <p>
     * The operation is _intermediate_ and _stateless_.
     */
    @NotNull
    public static <T, R> Sequence<R> mapNotNull(@Nullable Sequence<T> sequence, @NotNull NullableTransformer<T, R> transform) {
        return filterNotNull(new NullableTransformingSequence<T, R>(sequence, transform));
    }

    /**
     * Returns a sequence containing the results of applying the given [transform] function
     * to each element and its index in the original sequence.
     *
     * @param transform function that takes the index of an element and the element itself
     *                  and returns the result of the transform applied to the element.
     *                  <p>
     *                  The operation is _intermediate_ and _stateless_.
     */
    @NotNull
    public static <T, R> Sequence<R> mapIndexed(@Nullable Sequence<T> sequence, @NotNull IndexedTransformer<T, R> transform) {
        return new TransformingIndexedSequence<T, R>(sequence, transform);
    }

    /**
     * Returns a sequence containing only the non-null results of applying the given [transform] function
     * to each element and its index in the original sequence.
     *
     * @param transform function that takes the index of an element and the element itself
     *                  and returns the result of the transform applied to the element.
     *                  <p>
     *                  The operation is _intermediate_ and _stateless_.
     */
    @NotNull
    public static <T, R> Sequence<R> mapIndexedNotNull(
            @Nullable Sequence<T> sequence, @NotNull NullableIndexedTransformer<T, R> transform) {
        return filterNotNull(new NullableTransformingIndexedSequence<T, R>(sequence, transform));
    }

    /**
     * Applies the given [transform] function to each element of the original sequence
     * and appends the results to the given [destination].
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T, R, C extends Collection<R>> C mapTo(
            @Nullable Sequence<T> sequence, @NotNull C destination, @NotNull Transformer<T, R> transform) {
        if (sequence != null) {
            Iterator<T> iterator = sequence.iterator();
            while (iterator.hasNext()) {
                T item = iterator.next();
                destination.add(transform.transform(item));
            }
        }
        return destination;
    }

    /**
     * Applies the given [transform] function to each element in the original sequence
     * and appends only the non-null results to the given [destination].
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T, R, C extends Collection<R>> C mapNotNullTo(
            @Nullable Sequence<T> sequence, @NotNull final C destination, @NotNull final NullableTransformer<T, R> transform) {
        if (sequence != null) {
            forEach(sequence, new Action<T>() {
                @Override
                public void action(@NotNull T element) {
                    R result = transform.transform(element);
                    if (result != null) destination.add(result);
                }
            });
        }
        return destination;
    }

    /**
     * Applies the given [transform] function to each element and its index in the original sequence
     * and appends the results to the given [destination].
     *
     * @param transform function that takes the index of an element and the element itself
     *                  and returns the result of the transform applied to the element.
     *                  <p>
     *                  The operation is _terminal_.
     */
    @NotNull
    public static <T, R, C extends Collection<R>> C mapIndexedTo(
            @Nullable Sequence<T> sequence, @NotNull C destination, @NotNull IndexedTransformer<T, R> transform) {
        if (sequence != null) {
            int index = 0;
            Iterator<T> iterator = sequence.iterator();
            while (iterator.hasNext()) {
                T item = iterator.next();
                destination.add(transform.transform(index++, item));
            }
        }
        return destination;
    }

    /**
     * Applies the given [transform] function to each element and its index in the original sequence
     * and appends only the non-null results to the given [destination].
     *
     * @param transform function that takes the index of an element and the element itself
     *                  and returns the result of the transform applied to the element.
     *                  <p>
     *                  The operation is _terminal_.
     */
    @NotNull
    public static <T, R, C extends Collection<R>> C mapIndexedNotNullTo(
            @Nullable Sequence<T> sequence, @NotNull final C destination, @NotNull final NullableIndexedTransformer<T, R> transform) {
        if (sequence != null) {
            forEachIndexed(sequence, new IndexedAction<T>() {
                @Override
                public void action(int index, @NotNull T element) {
                    R result = transform.transform(index, element);
                    if (result != null) destination.add(result);
                }
            });
        }
        return destination;
    }


    /* ******************************************* withIndex ******************************************* */


    /**
     * Returns a sequence of [IndexedValue] for each element of the original sequence.
     * <p>
     * The operation is _intermediate_ and _stateless_.
     */
    @NotNull
    public static <T> Sequence<IndexedValue<T>> withIndex(@Nullable Sequence<T> sequence) {
        return new IndexingSequence<T>(sequence);
    }


    /* ******************************************* distinct ******************************************* */


    /**
     * Returns a sequence containing only distinct elements from the given sequence.
     * <p>
     * The elements in the resulting sequence are in the same order as they were in the source sequence.
     * <p>
     * The operation is _intermediate_ and _stateful_.
     */
    @NotNull
    public static <T> Sequence<T> distinct(@Nullable Sequence<T> sequence) {
        return distinctBy(sequence, new Transformer<T, T>() {
            @NotNull
            @Override
            public T transform(@NotNull T t) {
                return t;
            }
        });
    }

    /**
     * Returns a sequence containing only elements from the given sequence
     * having distinct keys returned by the given [selector] function.
     * <p>
     * The elements in the resulting sequence are in the same order as they were in the source sequence.
     * <p>
     * The operation is _intermediate_ and _stateful_.
     */
    @NotNull
    public static <T, K> Sequence<T> distinctBy(@Nullable Sequence<T> sequence, @NotNull Transformer<T, K> transformer) {
        return new DistinctSequence<T, K>(sequence, transformer);
    }


    /* ******************************************* all ******************************************* */


    /**
     * Returns `true` if all elements match the given [predicate].
     * <p>
     * The operation is _terminal_.
     */
    public static <T> boolean all(@Nullable Sequence<T> sequence, @NotNull Predicate<T> predicate) {
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T element = iterator.next();
                if (!predicate.accept(element)) return false;
            }
        }
        return true;
    }


    /* ******************************************* any ******************************************* */


    /**
     * Returns `true` if sequence has at least one element.
     * <p>
     * The operation is _terminal_.
     */
    public static <T> boolean any(@Nullable Sequence<T> sequence) {
        return sequence != null && sequence.iterator().hasNext();
    }

    /**
     * Returns `true` if at least one element matches the given [predicate].
     * <p>
     * The operation is _terminal_.
     */
    public static <T> boolean any(@Nullable Sequence<T> sequence, @NotNull Predicate<T> predicate) {
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T element = iterator.next();
                if (predicate.accept(element)) return true;
            }
        }
        return false;
    }


    /* ******************************************* count ******************************************* */


    /**
     * Returns the number of elements in this sequence.
     * <p>
     * The operation is _terminal_.
     */
    public static <T> int count(@Nullable Sequence<T> sequence) {
        int count = 0;
        if (sequence != null) {
            Iterator<T> iterator = sequence.iterator();
            while (iterator.hasNext()) {
                iterator.next();
                count++;
            }
        }
        return count;
    }

    /**
     * Returns the number of elements matching the given [predicate].
     * <p>
     * The operation is _terminal_.
     */
    public static <T> int count(@Nullable Sequence<T> sequence, @NotNull Predicate<T> predicate) {
        int count = 0;
        if (sequence != null) {
            Iterator<T> iterator = sequence.iterator();
            while (iterator.hasNext()) {
                T element = iterator.next();
                if (predicate.accept(element)) count++;
            }
        }
        return count;
    }


    /* ******************************************* fold ******************************************* */


    /**
     * Accumulates value starting with [initial] value and applying [operation] from left to right to current accumulator value and each element.
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T, R> R fold(@Nullable Sequence<T> sequence, @NotNull R initial, @NotNull Operation<T, R> operation) {
        R accumulator = initial;
        if (sequence != null) {
            Iterator<T> iterator = sequence.iterator();
            while (iterator.hasNext()) {
                T element = iterator.next();
                accumulator = operation.operation(accumulator, element);
            }
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from left to right
     * to current accumulator value and each element with its index in the original sequence.
     *
     * @param operation function that takes the index of an element, current accumulator value
     *                  and the element itself, and calculates the next accumulator value.
     *                  <p>
     *                  The operation is _terminal_.
     */
    @NotNull
    public static <T, R> R foldIndexed(@Nullable Sequence<T> sequence, @NotNull R initial, @NotNull IndexedOperation<T, R> operation) {
        int index = 0;
        R accumulator = initial;
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T element = iterator.next();
                accumulator = operation.operation(index++, accumulator, element);
            }
        }
        return accumulator;
    }


    /* ******************************************* each ******************************************* */


    /**
     * Performs the given [action] on each element.
     * <p>
     * The operation is _terminal_.
     */
    public static <T> void forEach(@Nullable Sequence<T> sequence, @NotNull Action<T> action) {
        if (sequence != null) {
            Iterator<T> iterator = sequence.iterator();
            while (iterator.hasNext()) {
                T element = iterator.next();
                action.action(element);
            }
        }
    }

    /**
     * Performs the given [action] on each element, providing sequential index with the element.
     *
     * @param action function that takes the index of an element and the element itself
     *               and performs the desired action on the element.
     *               <p>
     *               The operation is _terminal_.
     */
    public static <T> void forEachIndexed(@Nullable Sequence<T> sequence, @NotNull IndexedAction<T> action) {
        if (sequence != null) {
            int index = 0;
            Iterator<T> iterator = sequence.iterator();
            while (iterator.hasNext()) {
                T element = iterator.next();
                action.action(index++, element);
            }
        }
    }

    /**
     * Returns a sequence which performs the given [action] on each element of the original sequence as they pass through it.
     * <p>
     * The operation is _intermediate_ and _stateless_.
     */
    @NotNull
    public static <T> Sequence<T> onEach(@Nullable Sequence<T> sequence, @NotNull final Action<T> action) {
        return map(sequence, new Transformer<T, T>() {
            @NotNull
            @Override
            public T transform(@NotNull T t) {
                action.action(t);
                return t;
            }
        });
    }

    /**
     * Returns a sequence which performs the given [action] on each element of the original sequence as they pass through it.
     * <p>
     * The operation is _intermediate_ and _stateless_.
     */
    @NotNull
    public static <T> Sequence<T> onEachIndexed(@Nullable Sequence<T> sequence, @NotNull final IndexedAction<T> action) {
        return mapIndexed(sequence, new IndexedTransformer<T, T>() {
            @NotNull
            @Override
            public T transform(int index, @NotNull T t) {
                action.action(index, t);
                return t;
            }
        });
    }


    /* ******************************************* max ******************************************* */


    /**
     * Returns the largest element or `null` if there are no elements.
     * <p>
     * If any of elements is `NaN` returns `NaN`.
     * <p>
     * The operation is _terminal_.
     */
    @Nullable
    public static Double maxDoubleOrNull(@Nullable Sequence<Double> sequence) {
        if (sequence == null) return null;
        Iterator<Double> iterator = sequence.iterator();
        if (!iterator.hasNext()) return null;
        Double max = iterator.next();
        if (max.isNaN()) return max;
        while (iterator.hasNext()) {
            Double e = iterator.next();
            if (e.isNaN()) return e;
            if (max < e) max = e;
        }
        return max;
    }

    /**
     * Returns the largest element or `null` if there are no elements.
     * <p>
     * If any of elements is `NaN` returns `NaN`.
     * <p>
     * The operation is _terminal_.
     */
    @Nullable
    public static Float maxFloatOrNull(@Nullable Sequence<Float> sequence) {
        if (sequence == null) return null;
        Iterator<Float> iterator = sequence.iterator();
        if (!iterator.hasNext()) return null;
        Float max = iterator.next();
        if (max.isNaN()) return max;
        while (iterator.hasNext()) {
            Float e = iterator.next();
            if (e.isNaN()) return e;
            if (max < e) max = e;
        }
        return max;
    }

    /**
     * Returns the largest element or `null` if there are no elements.
     * <p>
     * The operation is _terminal_.
     */
    @Nullable
    public static <T extends Comparable<T>> T maxOrNull(@Nullable Sequence<T> sequence) {
        if (sequence == null) return null;
        Iterator<T> iterator = sequence.iterator();
        if (!iterator.hasNext()) return null;
        T max = iterator.next();
        while (iterator.hasNext()) {
            T e = iterator.next();
            if (max.compareTo(e) < 0) max = e;
        }
        return max;
    }

    /**
     * Returns the first element yielding the largest value of the given function or `null` if there are no elements.
     * <p>
     * The operation is _terminal_.
     */
    @Nullable
    public static <T, R extends Comparable<R>> T maxByOrNull(@Nullable Sequence<T> sequence, @NotNull Transformer<T, R> transformer) {
        if (sequence == null) return null;
        Iterator<T> iterator = sequence.iterator();
        if (!iterator.hasNext()) return null;
        T maxElem = iterator.next();
        R maxValue = transformer.transform(maxElem);
        while (iterator.hasNext()) {
            T e = iterator.next();
            R v = transformer.transform(e);
            if (maxValue.compareTo(v) < 0) {
                maxElem = e;
                maxValue = v;
            }
        }
        return maxElem;
    }

    /**
     * Returns the first element having the largest value according to the provided [comparator] or `null` if there are no elements.
     * <p>
     * The operation is _terminal_.
     */
    @Nullable
    public static <T> T maxWithOrNull(@Nullable Sequence<T> sequence, @NotNull Comparator<T> comparator) {
        if (sequence == null) return null;
        Iterator<T> iterator = sequence.iterator();
        if (!iterator.hasNext()) return null;
        T max = iterator.next();
        while (iterator.hasNext()) {
            T e = iterator.next();
            if (comparator.compare(max, e) < 0) max = e;
        }
        return max;
    }


    /**
     * Returns the largest value among all values produced by [selector] function
     * applied to each element in the sequence.
     * <p>
     * If any of values produced by [selector] function is `NaN`, the returned result is `NaN`.
     *
     * @throws NoSuchElementException if the sequence is empty.
     *                                <p>
     *                                The operation is _terminal_.
     */
    public static <T> double maxOfDouble(@Nullable Sequence<T> sequence, @NotNull Transformer<T, Double> selector) {
        if (sequence == null) throw new NoSuchElementException();
        Iterator<T> iterator = sequence.iterator();
        if (!iterator.hasNext()) throw new NoSuchElementException();
        double maxValue = selector.transform(iterator.next());
        while (iterator.hasNext()) {
            double v = selector.transform(iterator.next());
            maxValue = Math.max(maxValue, v);
        }
        return maxValue;
    }

    /**
     * Returns the largest value among all values produced by [selector] function
     * applied to each element in the sequence.
     * <p>
     * If any of values produced by [selector] function is `NaN`, the returned result is `NaN`.
     *
     * @throws NoSuchElementException if the sequence is empty.
     *                                <p>
     *                                The operation is _terminal_.
     */
    public static <T> float maxOfFloat(@Nullable Sequence<T> sequence, @NotNull Transformer<T, Float> selector) {
        if (sequence == null) throw new NoSuchElementException();
        Iterator<T> iterator = sequence.iterator();
        if (!iterator.hasNext()) throw new NoSuchElementException();
        float maxValue = selector.transform(iterator.next());
        while (iterator.hasNext()) {
            float v = selector.transform(iterator.next());
            maxValue = Math.max(maxValue, v);
        }
        return maxValue;
    }

    /**
     * Returns the largest value among all values produced by [selector] function
     * applied to each element in the sequence or `null` if there are no elements.
     * <p>
     * If any of values produced by [selector] function is `NaN`, the returned result is `NaN`.
     * <p>
     * The operation is _terminal_.
     */
    @Nullable
    public static <T> Double maxOfDoubleOrNull(@Nullable Sequence<T> sequence, @NotNull Transformer<T, Double> selector) {
        if (sequence == null) return null;
        Iterator<T> iterator = sequence.iterator();
        if (!iterator.hasNext()) return null;
        double maxValue = selector.transform(iterator.next());
        while (iterator.hasNext()) {
            double v = selector.transform(iterator.next());
            maxValue = Math.max(maxValue, v);
        }
        return maxValue;
    }

    /**
     * Returns the largest value among all values produced by [selector] function
     * applied to each element in the sequence or `null` if there are no elements.
     * <p>
     * If any of values produced by [selector] function is `NaN`, the returned result is `NaN`.
     * <p>
     * The operation is _terminal_.
     */
    @Nullable
    public static <T> Float maxOfFloatOrNull(@Nullable Sequence<T> sequence, @NotNull Transformer<T, Float> selector) {
        if (sequence == null) return null;
        Iterator<T> iterator = sequence.iterator();
        if (!iterator.hasNext()) return null;
        float maxValue = selector.transform(iterator.next());
        while (iterator.hasNext()) {
            float v = selector.transform(iterator.next());
            maxValue = Math.max(maxValue, v);
        }
        return maxValue;
    }

    /**
     * Returns the largest value among all values produced by [selector] function
     * applied to each element in the sequence.
     *
     * @throws NoSuchElementException if the sequence is empty.
     *                                <p>
     *                                The operation is _terminal_.
     */
    @NotNull
    public static <T, R extends Comparable<R>> R maxOf(@Nullable Sequence<T> sequence, @NotNull Transformer<T, R> selector) {
        if (sequence == null) throw new NoSuchElementException();
        Iterator<T> iterator = sequence.iterator();
        if (!iterator.hasNext()) throw new NoSuchElementException();
        R maxValue = selector.transform(iterator.next());
        while (iterator.hasNext()) {
            R v = selector.transform(iterator.next());
            if (maxValue.compareTo(v) < 0) {
                maxValue = v;
            }
        }
        return maxValue;
    }

    /**
     * Returns the largest value among all values produced by [selector] function
     * applied to each element in the sequence or `null` if there are no elements.
     * <p>
     * The operation is _terminal_.
     */
    @Nullable
    public static <T, R extends Comparable<R>> R maxOfOrNull(@Nullable Sequence<T> sequence, @NotNull Transformer<T, R> selector) {
        if (sequence == null) return null;
        Iterator<T> iterator = sequence.iterator();
        if (!iterator.hasNext()) return null;
        R maxValue = selector.transform(iterator.next());
        while (iterator.hasNext()) {
            R v = selector.transform(iterator.next());
            if (maxValue.compareTo(v) < 0) {
                maxValue = v;
            }
        }
        return maxValue;
    }

    /**
     * Returns the largest value according to the provided [comparator]
     * among all values produced by [selector] function applied to each element in the sequence.
     *
     * @throws NoSuchElementException if the sequence is empty.
     *                                <p>
     *                                The operation is _terminal_.
     */
    @NotNull
    public static <T, R> R maxOfWith(@Nullable Sequence<T> sequence, @NotNull Comparator<R> comparator, @NotNull Transformer<T, R> selector) {
        if (sequence == null) throw new NoSuchElementException();
        Iterator<T> iterator = sequence.iterator();
        if (!iterator.hasNext()) throw new NoSuchElementException();
        R maxValue = selector.transform(iterator.next());
        while (iterator.hasNext()) {
            R v = selector.transform(iterator.next());
            if (comparator.compare(maxValue, v) < 0) {
                maxValue = v;
            }
        }
        return maxValue;
    }

    /**
     * Returns the largest value according to the provided [comparator]
     * among all values produced by [selector] function applied to each element in the sequence or `null` if there are no elements.
     * <p>
     * The operation is _terminal_.
     */
    @Nullable
    public static <T, R> R maxOfWithOrNull(@Nullable Sequence<T> sequence, @NotNull Comparator<R> comparator, @NotNull Transformer<T, R> selector) {
        if (sequence == null) return null;
        Iterator<T> iterator = sequence.iterator();
        if (!iterator.hasNext()) return null;
        R maxValue = selector.transform(iterator.next());
        while (iterator.hasNext()) {
            R v = selector.transform(iterator.next());
            if (comparator.compare(maxValue, v) < 0) {
                maxValue = v;
            }
        }
        return maxValue;
    }


    /* ******************************************* min ******************************************* */


    /**
     * Returns the smallest element or `null` if there are no elements.
     * <p>
     * If any of elements is `NaN` returns `NaN`.
     * <p>
     * The operation is _terminal_.
     */
    @Nullable
    public static Double minDoubleOrNull(@Nullable Sequence<Double> sequence) {
        if (sequence == null) return null;
        Iterator<Double> iterator = sequence.iterator();
        if (!iterator.hasNext()) return null;
        Double min = iterator.next();
        if (min.isNaN()) return min;
        while (iterator.hasNext()) {
            Double e = iterator.next();
            if (e.isNaN()) return e;
            if (min > e) min = e;
        }
        return min;
    }

    /**
     * Returns the smallest element or `null` if there are no elements.
     * <p>
     * If any of elements is `NaN` returns `NaN`.
     * <p>
     * The operation is _terminal_.
     */
    @Nullable
    public static Float minFloatOrNull(@Nullable Sequence<Float> sequence) {
        if (sequence == null) return null;
        Iterator<Float> iterator = sequence.iterator();
        if (!iterator.hasNext()) return null;
        Float min = iterator.next();
        if (min.isNaN()) return min;
        while (iterator.hasNext()) {
            Float e = iterator.next();
            if (e.isNaN()) return e;
            if (min > e) min = e;
        }
        return min;
    }

    /**
     * Returns the smallest element or `null` if there are no elements.
     * <p>
     * The operation is _terminal_.
     */
    @Nullable
    public static <T extends Comparable<T>> T minOrNull(@Nullable Sequence<T> sequence) {
        if (sequence == null) return null;
        Iterator<T> iterator = sequence.iterator();
        if (!iterator.hasNext()) return null;
        T min = iterator.next();
        while (iterator.hasNext()) {
            T e = iterator.next();
            if (min.compareTo(e) > 0) min = e;
        }
        return min;
    }

    /**
     * Returns the first element yielding the smallest value of the given function or `null` if there are no elements.
     * <p>
     * The operation is _terminal_.
     */
    @Nullable
    public static <T, R extends Comparable<R>> T minByOrNull(@Nullable Sequence<T> sequence, @NotNull Transformer<T, R> transformer) {
        if (sequence == null) return null;
        Iterator<T> iterator = sequence.iterator();
        if (!iterator.hasNext()) return null;
        T minElem = iterator.next();
        R minValue = transformer.transform(minElem);
        while (iterator.hasNext()) {
            T e = iterator.next();
            R v = transformer.transform(e);
            if (minValue.compareTo(v) > 0) {
                minElem = e;
                minValue = v;
            }
        }
        return minElem;
    }

    /**
     * Returns the first element having the smallest value according to the provided [comparator] or `null` if there are no elements.
     * <p>
     * The operation is _terminal_.
     */
    @Nullable
    public static <T> T minWithOrNull(@Nullable Sequence<T> sequence, @NotNull Comparator<T> comparator) {
        if (sequence == null) return null;
        Iterator<T> iterator = sequence.iterator();
        if (!iterator.hasNext()) return null;
        T min = iterator.next();
        while (iterator.hasNext()) {
            T e = iterator.next();
            if (comparator.compare(min, e) > 0) min = e;
        }
        return min;
    }


    /**
     * Returns the smallest value among all values produced by [selector] function
     * applied to each element in the sequence.
     * <p>
     * If any of values produced by [selector] function is `NaN`, the returned result is `NaN`.
     *
     * @throws NoSuchElementException if the sequence is empty.
     *                                <p>
     *                                The operation is _terminal_.
     */
    public static <T> double minOfDouble(@Nullable Sequence<T> sequence, @NotNull Transformer<T, Double> selector) {
        if (sequence == null) throw new NoSuchElementException();
        Iterator<T> iterator = sequence.iterator();
        if (!iterator.hasNext()) throw new NoSuchElementException();
        double minValue = selector.transform(iterator.next());
        while (iterator.hasNext()) {
            double v = selector.transform(iterator.next());
            minValue = Math.min(minValue, v);
        }
        return minValue;
    }

    /**
     * Returns the smallest value among all values produced by [selector] function
     * applied to each element in the sequence.
     * <p>
     * If any of values produced by [selector] function is `NaN`, the returned result is `NaN`.
     *
     * @throws NoSuchElementException if the sequence is empty.
     *                                <p>
     *                                The operation is _terminal_.
     */
    public static <T> float minOfFloat(@Nullable Sequence<T> sequence, @NotNull Transformer<T, Float> selector) {
        if (sequence == null) throw new NoSuchElementException();
        Iterator<T> iterator = sequence.iterator();
        if (!iterator.hasNext()) throw new NoSuchElementException();
        float minValue = selector.transform(iterator.next());
        while (iterator.hasNext()) {
            float v = selector.transform(iterator.next());
            minValue = Math.min(minValue, v);
        }
        return minValue;
    }

    /**
     * Returns the smallest value among all values produced by [selector] function
     * applied to each element in the sequence.
     *
     * @throws NoSuchElementException if the sequence is empty.
     *                                <p>
     *                                The operation is _terminal_.
     */
    @NotNull
    public static <T, R extends Comparable<R>> R minOf(@Nullable Sequence<T> sequence, @NotNull Transformer<T, R> selector) {
        if (sequence == null) throw new NoSuchElementException();
        Iterator<T> iterator = sequence.iterator();
        if (!iterator.hasNext()) throw new NoSuchElementException();
        R minValue = selector.transform(iterator.next());
        while (iterator.hasNext()) {
            R v = selector.transform(iterator.next());
            if (minValue.compareTo(v) > 0) {
                minValue = v;
            }
        }
        return minValue;
    }

    /**
     * Returns the smallest value among all values produced by [selector] function
     * applied to each element in the sequence or `null` if there are no elements.
     * <p>
     * If any of values produced by [selector] function is `NaN`, the returned result is `NaN`.
     * <p>
     * The operation is _terminal_.
     */
    @Nullable
    public static <T> Double minOfDoubleOrNull(@Nullable Sequence<T> sequence, @NotNull Transformer<T, Double> selector) {
        if (sequence == null) return null;
        Iterator<T> iterator = sequence.iterator();
        if (!iterator.hasNext()) return null;
        double minValue = selector.transform(iterator.next());
        while (iterator.hasNext()) {
            double v = selector.transform(iterator.next());
            minValue = Math.min(minValue, v);
        }
        return minValue;
    }

    /**
     * Returns the smallest value among all values produced by [selector] function
     * applied to each element in the sequence or `null` if there are no elements.
     * <p>
     * If any of values produced by [selector] function is `NaN`, the returned result is `NaN`.
     * <p>
     * The operation is _terminal_.
     */
    @Nullable
    public static <T> Float minOfFloatOrNull(@Nullable Sequence<T> sequence, @NotNull Transformer<T, Float> selector) {
        if (sequence == null) return null;
        Iterator<T> iterator = sequence.iterator();
        if (!iterator.hasNext()) return null;
        float minValue = selector.transform(iterator.next());
        while (iterator.hasNext()) {
            float v = selector.transform(iterator.next());
            minValue = Math.min(minValue, v);
        }
        return minValue;
    }

    /**
     * Returns the smallest value among all values produced by [selector] function
     * applied to each element in the sequence or `null` if there are no elements.
     * <p>
     * The operation is _terminal_.
     */
    @Nullable
    public static <T, R extends Comparable<R>> R minOfOrNull(@Nullable Sequence<T> sequence, @NotNull Transformer<T, R> selector) {
        if (sequence == null) return null;
        Iterator<T> iterator = sequence.iterator();
        if (!iterator.hasNext()) return null;
        R minValue = selector.transform(iterator.next());
        while (iterator.hasNext()) {
            R v = selector.transform(iterator.next());
            if (minValue.compareTo(v) > 0) {
                minValue = v;
            }
        }
        return minValue;
    }

    /**
     * Returns the smallest value according to the provided [comparator]
     * among all values produced by [selector] function applied to each element in the sequence.
     *
     * @throws NoSuchElementException if the sequence is empty.
     *                                <p>
     *                                The operation is _terminal_.
     */
    @NotNull
    public static <T, R> R minOfWith(@Nullable Sequence<T> sequence, @NotNull Comparator<R> comparator, @NotNull Transformer<T, R> selector) {
        if (sequence == null) throw new NoSuchElementException();
        Iterator<T> iterator = sequence.iterator();
        if (!iterator.hasNext()) throw new NoSuchElementException();
        R minValue = selector.transform(iterator.next());
        while (iterator.hasNext()) {
            R v = selector.transform(iterator.next());
            if (comparator.compare(minValue, v) > 0) {
                minValue = v;
            }
        }
        return minValue;
    }

    /**
     * Returns the smallest value according to the provided [comparator]
     * among all values produced by [selector] function applied to each element in the sequence or `null` if there are no elements.
     * <p>
     * The operation is _terminal_.
     */
    @Nullable
    public static <T, R> R minOfWithOrNull(@Nullable Sequence<T> sequence, @NotNull Comparator<R> comparator, @NotNull Transformer<T, R> selector) {
        if (sequence == null) return null;
        Iterator<T> iterator = sequence.iterator();
        if (!iterator.hasNext()) return null;
        R minValue = selector.transform(iterator.next());
        while (iterator.hasNext()) {
            R v = selector.transform(iterator.next());
            if (comparator.compare(minValue, v) > 0) {
                minValue = v;
            }
        }
        return minValue;
    }


    /* ******************************************* none ******************************************* */


    /**
     * Returns `true` if the sequence has no elements.
     * <p>
     * The operation is _terminal_.
     */
    public static <T> boolean none(@Nullable Sequence<T> sequence) {
        return sequence == null || !sequence.iterator().hasNext();
    }

    /**
     * Returns `true` if no elements match the given [predicate].
     * <p>
     * The operation is _terminal_.
     */
    public static <T> boolean none(@Nullable Sequence<T> sequence, @NotNull Predicate<T> predicate) {
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T element = iterator.next();
                if (predicate.accept(element)) return false;
            }
        }
        return true;
    }


    /* ******************************************* reduce ******************************************* */


    /**
     * Accumulates value starting with the first element and applying [operation] from left to right to current accumulator value and each element.
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <S, T extends S> S reduce(@Nullable Sequence<T> sequence, @NotNull Operation<T, S> operation) {
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator == null || !iterator.hasNext()) {
            throw new UnsupportedOperationException("Empty sequence can't be reduced.");
        }
        S accumulator = iterator.next();
        while (iterator.hasNext()) {
            accumulator = operation.operation(accumulator, iterator.next());
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with the first element and applying [operation] from left to right
     * to current accumulator value and each element with its index in the original sequence.
     *
     * @param operation function that takes the index of an element, current accumulator value
     *                  and the element itself and calculates the next accumulator value.
     *                  <p>
     *                  The operation is _terminal_.
     */
    @NotNull
    public static <S, T extends S> S reduceIndexed(@Nullable Sequence<T> sequence, @NotNull IndexedOperation<T, S> operation) {
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator == null || !iterator.hasNext()) {
            throw new UnsupportedOperationException("Empty sequence can't be reduced.");
        }
        int index = 1;
        S accumulator = iterator.next();
        while (iterator.hasNext()) {
            accumulator = operation.operation(index++, accumulator, iterator.next());
        }
        return accumulator;
    }


    /* ******************************************* sum ******************************************* */


    /**
     * Returns the sum of all values produced by [selector] function applied to each element in the sequence.
     * <p>
     * The operation is _terminal_.
     */
    public static <T> int sumBy(@Nullable Sequence<T> sequence, @NotNull Transformer<T, Integer> transformer) {
        int sum = 0;
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T element = iterator.next();
                sum += transformer.transform(element);
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all values produced by [selector] function applied to each element in the sequence.
     * <p>
     * The operation is _terminal_.
     */
    public static <T> double sumByDouble(@Nullable Sequence<T> sequence, @NotNull Transformer<T, Double> transformer) {
        double sum = 0.0;
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T element = iterator.next();
                sum += transformer.transform(element);
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all elements in the sequence.
     * <p>
     * The operation is _terminal_.
     */
    public static int sumOfByte(@Nullable Sequence<Byte> sequence) {
        int sum = 0;
        if (sequence != null) {
            Iterator<Byte> iterator = sequence.iterator();
            while (iterator.hasNext()) {
                Byte element = iterator.next();
                sum += element;
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all elements in the sequence.
     * <p>
     * The operation is _terminal_.
     */
    public static int sumOfShort(@Nullable Sequence<Short> sequence) {
        int sum = 0;
        if (sequence != null) {
            Iterator<Short> iterator = sequence.iterator();
            while (iterator.hasNext()) {
                Short element = iterator.next();
                sum += element;
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all elements in the sequence.
     * <p>
     * The operation is _terminal_.
     */
    public static int sumOfInt(@Nullable Sequence<Integer> sequence) {
        int sum = 0;
        if (sequence != null) {
            Iterator<Integer> iterator = sequence.iterator();
            while (iterator.hasNext()) {
                Integer element = iterator.next();
                sum += element;
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all elements in the sequence.
     * <p>
     * The operation is _terminal_.
     */
    public static long sumOfLong(@Nullable Sequence<Long> sequence) {
        long sum = 0L;
        if (sequence != null) {
            Iterator<Long> iterator = sequence.iterator();
            while (iterator.hasNext()) {
                Long element = iterator.next();
                sum += element;
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all elements in the sequence.
     * <p>
     * The operation is _terminal_.
     *
     * @deprecated Please use sumOfLong instead
     */
    @Deprecated
    public static long sum(@Nullable Sequence<Long> sequence) {
        return sumOfLong(sequence);
    }

    /**
     * Returns the sum of all elements in the sequence.
     * <p>
     * The operation is _terminal_.
     */
    public static float sumOfFloat(@Nullable Sequence<Float> sequence) {
        float sum = 0.0f;
        if (sequence != null) {
            Iterator<Float> iterator = sequence.iterator();
            while (iterator.hasNext()) {
                Float element = iterator.next();
                sum += element;
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all elements in the sequence.
     * <p>
     * The operation is _terminal_.
     */
    public static double sumOfDouble(@Nullable Sequence<Double> sequence) {
        double sum = 0.0;
        if (sequence != null) {
            Iterator<Double> iterator = sequence.iterator();
            while (iterator.hasNext()) {
                Double element = iterator.next();
                sum += element;
            }
        }
        return sum;
    }


    /* ******************************************* require ******************************************* */


    /**
     * Returns an original collection containing all the non-`null` elements, throwing an [IllegalArgumentException] if there are any `null` elements.
     * <p>
     * The operation is _intermediate_ and _stateless_.
     */
    @NotNull
    public static <T> Sequence<T> requireNoNulls(@Nullable final Sequence<T> sequence) {
        return map(sequence, new Transformer<T, T>() {
            @NotNull
            @Override
            public T transform(@NotNull T t) {
                //noinspection ConstantConditions
                if (t != null) {
                    return t;
                } else {
                    throw new IllegalArgumentException("null element found in " + sequence + ".");
                }
            }
        });
    }


    /* ******************************************* chunked ******************************************* */


    /**
     * Splits this sequence into a sequence of lists each not exceeding the given [size].
     * <p>
     * The last list in the resulting sequence may have less elements than the given [size].
     *
     * @param size the number of elements to take in each list, must be positive and can be greater than the number of elements in this sequence.
     *             <p>
     *             The operation is _intermediate_ and _stateful_.
     */
    @NotNull
    public static <T> Sequence<List<T>> chunked(@Nullable Sequence<T> sequence, int size) {
        return windowed(sequence, size, size, true);
    }

    /**
     * Splits this sequence into several lists each not exceeding the given [size]
     * and applies the given [transform] function to an each.
     *
     * @param size the number of elements to take in each list, must be positive and can be greater than the number of elements in this sequence.
     *             <p>
     *             The operation is _intermediate_ and _stateful_.
     * @return sequence of results of the [transform] applied to an each list.
     * <p>
     * Note that the list passed to the [transform] function is ephemeral and is valid only inside that function.
     * You should not store it or allow it to escape in some way, unless you made a snapshot of it.
     * The last list may have less elements than the given [size].
     */
    @NotNull
    public static <T, R> Sequence<R> chunked(@Nullable Sequence<T> sequence, int size, @NotNull Transformer<List<T>, R> transform) {
        return windowed(sequence, size, size, true, transform);
    }


    /* ******************************************* minus ******************************************* */


    /**
     * Returns a sequence containing all elements of the original sequence without the first occurrence of the given [element].
     * <p>
     * The operation is _intermediate_ and _stateless_.
     */
    @NotNull
    public static <T> Sequence<T> minus(@Nullable final Sequence<T> sequence, @NotNull final T element) {
        return new Sequence<T>() {
            @NotNull
            @Override
            public Iterator<T> iterator() {
                final boolean[] removed = {false};
                return filter(sequence, new Predicate<T>() {
                    @Override
                    public boolean accept(@NotNull T it) {
                        if (!removed[0] && it == element) {
                            removed[0] = true;
                            return false;
                        } else {
                            return true;
                        }
                    }
                }).iterator();
            }
        };
    }

    /**
     * Returns a sequence containing all elements of original sequence except the elements contained in the given [elements] array.
     * <p>
     * Note that the source sequence and the array being subtracted are iterated only when an `iterator` is requested from
     * the resulting sequence. Changing any of them between successive calls to `iterator` may affect the result.
     * <p>
     * The operation is _intermediate_ and _stateful_.
     */
    @NotNull
    public static <T> Sequence<T> minus(@Nullable final Sequence<T> sequence, @NotNull final T[] elements) {
        if (elements.length > 0) {
            return new Sequence<T>() {
                @NotNull
                @Override
                public Iterator<T> iterator() {
                    final Set<T> other = new HashSet<T>();
                    Collections.addAll(other, elements);
                    return filterNot(sequence, new Predicate<T>() {
                        @Override
                        public boolean accept(@NotNull T t) {
                            return other.contains(t);
                        }
                    }).iterator();
                }
            };
        }
        return sequence != null ? sequence : Sequencex.<T>emptySequence();
    }

    /**
     * Returns a sequence containing all elements of original sequence except the elements contained in the given [elements] collection.
     * <p>
     * Note that the source sequence and the collection being subtracted are iterated only when an `iterator` is requested from
     * the resulting sequence. Changing any of them between successive calls to `iterator` may affect the result.
     * <p>
     * The operation is _intermediate_ and _stateful_.
     */
    @NotNull
    public static <T> Sequence<T> minus(@Nullable final Sequence<T> sequence, @NotNull final Iterable<T> elements) {
        return new Sequence<T>() {
            @NotNull
            @Override
            public Iterator<T> iterator() {
                final Collection<T> other;
                if (elements instanceof Set) {
                    other = (Set<T>) elements;
                } else if (elements instanceof Collection) {
                    if (((Collection<T>) elements).size() > 2 && elements instanceof ArrayList) {
                        other = new HashSet<T>();
                        for (T item : elements) {
                            other.add(item);
                        }
                    } else {
                        other = (Collection<T>) elements;
                    }
                } else {
                    other = new HashSet<T>();
                    for (T item : elements) {
                        other.add(item);
                    }
                }
                if (other.isEmpty()) {
                    return sequence != null ? sequence.iterator() : Sequencex.<T>emptySequence().iterator();
                } else {
                    return filterNot(sequence, new Predicate<T>() {
                        @Override
                        public boolean accept(@NotNull T t) {
                            return other.contains(t);
                        }
                    }).iterator();
                }
            }
        };
    }

    /**
     * Returns a sequence containing all elements of original sequence except the elements contained in the given [elements] sequence.
     * <p>
     * Note that the source sequence and the sequence being subtracted are iterated only when an `iterator` is requested from
     * the resulting sequence. Changing any of them between successive calls to `iterator` may affect the result.
     * <p>
     * The operation is _intermediate_ for this sequence and _terminal_ and _stateful_ for the [elements] sequence.
     */
    @NotNull
    public static <T> Sequence<T> minus(@Nullable final Sequence<T> sequence, @NotNull final Sequence<T> elements) {
        return new Sequence<T>() {
            @NotNull
            @Override
            public Iterator<T> iterator() {
                final Set<T> other = toHashSet(elements);
                if (other.isEmpty()) {
                    return sequence != null ? sequence.iterator() : Sequencex.<T>emptySequence().iterator();
                }
                return filterNot(sequence, new Predicate<T>() {
                    @Override
                    public boolean accept(@NotNull T t) {
                        return other.contains(t);
                    }
                }).iterator();
            }
        };
    }

    /**
     * Returns a sequence containing all elements of the original sequence without the first occurrence of the given [element].
     * <p>
     * The operation is _intermediate_ and _stateless_.
     */
    @NotNull
    public static <T> Sequence<T> minusElement(@Nullable Sequence<T> sequence, @NotNull T element) {
        return minus(sequence, element);
    }


    /* ******************************************* partition ******************************************* */


    /**
     * Splits the original sequence into pair of lists,
     * where *first* list contains elements for which [predicate] yielded `true`,
     * while *second* list contains elements for which [predicate] yielded `false`.
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T> Pair<List<T>, List<T>> partition(@Nullable Sequence<T> sequence, @NotNull Predicate<T> predicate) {
        List<T> first = new ArrayList<T>();
        List<T> second = new ArrayList<T>();
        Iterator<T> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                T element = iterator.next();
                if (predicate.accept(element)) {
                    first.add(element);
                } else {
                    second.add(element);
                }
            }
        }
        return Pair.of(first, second);
    }


    /* ******************************************* plus ******************************************* */


    /**
     * Returns a sequence containing all elements of the original sequence and then the given [element].
     * <p>
     * The operation is _intermediate_ and _stateless_.
     */
    @NotNull
    public static <T> Sequence<T> plus(@Nullable Sequence<T> sequence, @NotNull T element) {
        //noinspection unchecked
        return flatten(sequenceOf(sequence != null ? sequence : Sequencex.<T>emptySequence(), sequenceOf(element)));
    }

    /**
     * Returns a sequence containing all elements of original sequence and then all elements of the given [elements] array.
     * <p>
     * Note that the source sequence and the array being added are iterated only when an `iterator` is requested from
     * the resulting sequence. Changing any of them between successive calls to `iterator` may affect the result.
     * <p>
     * The operation is _intermediate_ and _stateless_.
     */
    @NotNull
    public static <T> Sequence<T> plus(@Nullable Sequence<T> sequence, @NotNull T[] elements) {
        //noinspection unchecked
        return flatten(sequenceOf(sequence != null ? sequence : Sequencex.<T>emptySequence(), asSequence(elements)));
    }

    /**
     * Returns a sequence containing all elements of original sequence and then all elements of the given [elements] collection.
     * <p>
     * Note that the source sequence and the collection being added are iterated only when an `iterator` is requested from
     * the resulting sequence. Changing any of them between successive calls to `iterator` may affect the result.
     * <p>
     * The operation is _intermediate_ and _stateless_.
     */
    @NotNull
    public static <T> Sequence<T> plus(@Nullable Sequence<T> sequence, @NotNull Iterable<T> elements) {
        //noinspection unchecked
        return flatten(sequenceOf(sequence != null ? sequence : Sequencex.<T>emptySequence(), asSequence(elements)));
    }

    /**
     * Returns a sequence containing all elements of original sequence and then all elements of the given [elements] sequence.
     * <p>
     * Note that the source sequence and the sequence being added are iterated only when an `iterator` is requested from
     * the resulting sequence. Changing any of them between successive calls to `iterator` may affect the result.
     * <p>
     * The operation is _intermediate_ and _stateless_.
     */
    @NotNull
    public static <T> Sequence<T> plus(@Nullable Sequence<T> sequence, @NotNull Sequence<T> elements) {
        //noinspection unchecked
        return flatten(sequenceOf(sequence != null ? sequence : Sequencex.<T>emptySequence(), elements));
    }

    /**
     * Returns a sequence containing all elements of the original sequence and then the given [element].
     * <p>
     * The operation is _intermediate_ and _stateless_.
     */
    @NotNull
    public static <T> Sequence<T> plusElement(@Nullable Sequence<T> sequence, @NotNull T element) {
        return plus(sequence != null ? sequence : Sequencex.<T>emptySequence(), element);
    }


    /* ******************************************* windowed ******************************************* */

    /**
     * Returns a sequence of snapshots of the window of the given [size]
     * sliding along this sequence with the given [step], where each
     * snapshot is a list.
     * <p>
     * Several last lists may have less elements than the given [size].
     * <p>
     * Both [size] and [step] must be positive and can be greater than the number of elements in this sequence.
     *
     * @param size           the number of elements to take in each window
     * @param step           the number of elements to move the window forward by on an each step, by default 1
     * @param partialWindows controls whether or not to keep partial windows in the end if any,
     *                       by default `false` which means partial windows won't be preserved
     */
    @NotNull
    public static <T> Sequence<List<T>> windowed(@Nullable final Sequence<T> sequence, final int size, final int step, final boolean partialWindows) {
        if (size <= 0 || step <= 0) {
            if (size != step) {
                throw new IllegalArgumentException("Both size " + size + " and step " + step + " must be greater than zero.");
            } else {
                throw new IllegalArgumentException("size " + size + " must be greater than zero.");
            }
        }
        return new Sequence<List<T>>() {
            @NotNull
            @Override
            public Iterator<List<T>> iterator() {
                return new WindowedIterator<T>(sequence != null ? sequence.iterator() : null, size, step, partialWindows);
            }
        };
    }

    /**
     * Returns a sequence of results of applying the given [transform] function to
     * an each list representing a view over the window of the given [size]
     * sliding along this sequence with the given [step].
     * <p>
     * Note that the list passed to the [transform] function is ephemeral and is valid only inside that function.
     * You should not store it or allow it to escape in some way, unless you made a snapshot of it.
     * Several last lists may have less elements than the given [size].
     * <p>
     * Both [size] and [step] must be positive and can be greater than the number of elements in this sequence.
     *
     * @param size           the number of elements to take in each window
     * @param step           the number of elements to move the window forward by on an each step, by default 1
     * @param partialWindows controls whether or not to keep partial windows in the end if any,
     *                       by default `false` which means partial windows won't be preserved
     */
    @NotNull
    public static <T, R> Sequence<R> windowed(@Nullable final Sequence<T> sequence, final int size, final int step,
                                              final boolean partialWindows, @NotNull Transformer<List<T>, R> transform) {
        if (size <= 0 || step <= 0) {
            if (size != step) {
                throw new IllegalArgumentException("Both size " + size + " and step " + step + " must be greater than zero.");
            } else {
                throw new IllegalArgumentException("size " + size + " must be greater than zero.");
            }
        }
        return map(new Sequence<List<T>>() {
            @NotNull
            @Override
            public Iterator<List<T>> iterator() {
                return new WindowedIterator<T>(sequence != null ? sequence.iterator() : null, size, step, partialWindows);
            }
        }, transform);
    }


    /* ******************************************* zip ******************************************* */


    /**
     * Returns a sequence of values built from the elements of `this` sequence and the [other] sequence with the same index.
     * The resulting sequence ends as soon as the shortest input sequence ends.
     * <p>
     * The operation is _intermediate_ and _stateless_.
     */
    @NotNull
    public static <T, R> Sequence<Pair<T, R>> zip(@Nullable Sequence<T> sequence, @Nullable Sequence<R> other) {
        return new MergingSequence<T, R, Pair<T, R>>(sequence, other, new Transformer2<T, R, Pair<T, R>>() {
            @NotNull
            @Override
            public Pair<T, R> transform(@NotNull T t, @NotNull R r) {
                return Pair.of(t, r);
            }
        });
    }

    /**
     * Returns a sequence of values built from the elements of `this` sequence and the [other] sequence with the same index
     * using the provided [transform] function applied to each pair of elements.
     * The resulting sequence ends as soon as the shortest input sequence ends.
     * <p>
     * The operation is _intermediate_ and _stateless_.
     */
    @NotNull
    public static <T, R, V> Sequence<V> zip(@Nullable Sequence<T> sequence, @Nullable Sequence<R> other, @NotNull Transformer2<T, R, V> transform) {
        return new MergingSequence<T, R, V>(sequence, other, transform);
    }

    /**
     * Returns a pair of lists, where
     * *first* list is built from the first values of each pair from this sequence,
     * *second* list is built from the second values of each pair from this sequence.
     * <p>
     * The operation is _terminal_.
     */
    @NotNull
    public static <T, R> Pair<List<T>, List<R>> unzip(@Nullable Sequence<Pair<T, R>> sequence) {
        List<T> listT = new ArrayList<T>();
        List<R> listR = new ArrayList<R>();
        Iterator<Pair<T, R>> iterator = sequence != null ? sequence.iterator() : null;
        if (iterator != null) {
            while (iterator.hasNext()) {
                Pair<T, R> pair = iterator.next();
                listT.add(pair.first);
                listR.add(pair.second);
            }
        }
        return Pair.of(listT, listR);
    }


///**
// * Returns a sequence of pairs of each two adjacent elements in this sequence.
// *
// * The returned sequence is empty if this sequence contains less than two elements.
// *
// * The operation is _intermediate_ and _stateless_.
// */
//@NotNull
//    public static <T> Sequence<Pair<T, T>> zipWithNext(@Nullable Sequence<T> sequence) {
//        return zipWithNext(sequence, new Transformer2<T, T, Pair<T, T>>() {
//            @NotNull
//            @Override
//            public Pair<T, T> transform(@NotNull T t, @NotNull T t2) {
//                return Pair.of(t, t2);
//            }
//        });
//    }
//
///**
// * Returns a sequence containing the results of applying the given [transform] function
// * to an each pair of two adjacent elements in this sequence.
// *
// * The returned sequence is empty if this sequence contains less than two elements.
// *
// * The operation is _intermediate_ and _stateless_.
// *
// * @sample samples.collections.Collections.Transformations.zipWithNextToFindDeltas
// */
//@NotNull
//    public static <T, R> Sequence<R> zipWithNext(@Nullable Sequence<T> sequence, @NotNull Transformer2<T, T, R> transform) {
//        return buildSequence result@ {
//            val iterator = iterator()
//            if (!iterator.hasNext()) return@result
//                    var current = iterator.next()
//            while (iterator.hasNext()) {
//                val next = iterator.next()
//                yield(transform(current, next))
//                current = next
//            }
//        }
//    }


    /* ******************************************* joinTo ******************************************* */

    /**
     * Appends the string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     * <p>
     * The operation is _terminal_.
     */
    public static <T, A extends Appendable> A joinTo(@Nullable Sequence<T> sequence, @NotNull A buffer, @Nullable CharSequence separator,
                                                     @Nullable CharSequence prefix, @Nullable CharSequence postfix, int limit,
                                                     @Nullable CharSequence truncated, @Nullable Transformer<T, CharSequence> transform) {
        if (separator == null) separator = ", ";
        if (prefix == null) prefix = "";
        if (postfix == null) postfix = "";
        if (truncated == null) truncated = "...";

        try {
            buffer.append(prefix);
            int count = 0;
            if (sequence != null) {
                Iterator<T> iterator = sequence.iterator();
                while (iterator.hasNext()) {
                    T element = iterator.next();
                    if (++count > 1) {
                        buffer.append(separator);
                    }
                    if (limit < 0 || count <= limit) {
                        if (element == null) {
                            buffer.append("null");
                        } else if (transform != null) {
                            buffer.append(transform.transform(element));
                        } else if (element instanceof CharSequence) {
                            buffer.append((CharSequence) element);
                        } else if (element instanceof Character) {
                            buffer.append((Character) element);
                        } else {
                            buffer.append(element.toString());
                        }
                    } else {
                        break;
                    }
                }
            }
            if (limit >= 0 && count > limit) {
                buffer.append(truncated);
            }
            buffer.append(postfix);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return buffer;
    }

    /**
     * Appends the string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     * <p>
     * The operation is _terminal_.
     */
    public static <T, A extends Appendable> A joinTo(
            @Nullable Sequence<T> sequence, @NotNull A buffer, @NotNull CharSequence separator, @NotNull Transformer<T, CharSequence> transform) {
        return joinTo(sequence, buffer, separator, null, null, -1, null, transform);
    }

    /**
     * Appends the string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     * <p>
     * The operation is _terminal_.
     */
    public static <T, A extends Appendable> A joinTo(@Nullable Sequence<T> sequence, @NotNull A buffer, @NotNull Transformer<T, CharSequence> transform) {
        return joinTo(sequence, buffer, null, null, null, -1, null, transform);
    }

    /**
     * Appends the string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     * <p>
     * The operation is _terminal_.
     */
    public static <T, A extends Appendable> A joinTo(@Nullable Sequence<T> sequence, @NotNull A buffer, @NotNull CharSequence separator) {
        return joinTo(sequence, buffer, separator, null, null, -1, null, null);
    }

    /**
     * Appends the string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     * <p>
     * The operation is _terminal_.
     */
    public static <T, A extends Appendable> A joinTo(@Nullable Sequence<T> sequence, @NotNull A buffer) {
        return joinTo(sequence, buffer, null, null, null, -1, null, null);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     * <p>
     * The operation is _terminal_.
     */
    public static <T> String joinToString(@Nullable Sequence<T> sequence, @Nullable CharSequence separator,
                                          @Nullable CharSequence prefix, @Nullable CharSequence postfix, int limit,
                                          @Nullable CharSequence truncated, @Nullable Transformer<T, CharSequence> transform) {
        return joinTo(sequence, new StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString();
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     * <p>
     * The operation is _terminal_.
     */
    public static <T> String joinToString(@Nullable Sequence<T> sequence, @NotNull CharSequence separator, @Nullable Transformer<T, CharSequence> transform) {
        return joinTo(sequence, new StringBuilder(), separator, null, null, -1, null, transform).toString();
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     * <p>
     * The operation is _terminal_.
     */
    public static <T> String joinToString(@Nullable Sequence<T> sequence, @Nullable Transformer<T, CharSequence> transform) {
        return joinTo(sequence, new StringBuilder(), null, null, null, -1, null, transform).toString();
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     * <p>
     * The operation is _terminal_.
     */
    public static <T> String joinToString(@Nullable Sequence<T> sequence, @Nullable CharSequence separator) {
        return joinTo(sequence, new StringBuilder(), separator, null, null, -1, null, null).toString();
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     * <p>
     * The operation is _terminal_.
     */
    public static <T> String joinToString(@Nullable Sequence<T> sequence) {
        return joinTo(sequence, new StringBuilder(), null, null, null, -1, null, null).toString();
    }


    /* ******************************************* average ******************************************* */


    /**
     * Returns an average value of elements in the sequence.
     * <p>
     * The operation is _terminal_.
     */
    public static double averageOfByte(@Nullable Sequence<Byte> sequence) {
        double sum = 0.0;
        int count = 0;
        if (sequence != null) {
            Iterator<Byte> iterator = sequence.iterator();
            while (iterator.hasNext()) {
                Byte element = iterator.next();
                sum += element;
                count += 1;
            }
        }
        return count == 0 ? Double.NaN : sum / count;
    }

    /**
     * Returns an average value of elements in the sequence.
     * <p>
     * The operation is _terminal_.
     */
    public static double averageOfShort(@Nullable Sequence<Short> sequence) {
        double sum = 0.0;
        int count = 0;
        if (sequence != null) {
            Iterator<Short> iterator = sequence.iterator();
            while (iterator.hasNext()) {
                Short element = iterator.next();
                sum += element;
                count += 1;
            }
        }
        return count == 0 ? Double.NaN : sum / count;
    }

    /**
     * Returns an average value of elements in the sequence.
     * <p>
     * The operation is _terminal_.
     */
    public static double averageOfInt(@Nullable Sequence<Integer> sequence) {
        double sum = 0.0;
        int count = 0;
        if (sequence != null) {
            Iterator<Integer> iterator = sequence.iterator();
            while (iterator.hasNext()) {
                Integer element = iterator.next();
                sum += element;
                count += 1;
            }
        }
        return count == 0 ? Double.NaN : sum / count;
    }

    /**
     * Returns an average value of elements in the sequence.
     * <p>
     * The operation is _terminal_.
     */
    public static double averageOfLong(@Nullable Sequence<Long> sequence) {
        double sum = 0.0;
        int count = 0;
        if (sequence != null) {
            Iterator<Long> iterator = sequence.iterator();
            while (iterator.hasNext()) {
                Long element = iterator.next();
                sum += element;
                count += 1;
            }
        }
        return count == 0 ? Double.NaN : sum / count;
    }

    /**
     * Returns an average value of elements in the sequence.
     * <p>
     * The operation is _terminal_.
     */
    public static double averageOfFloat(@Nullable Sequence<Float> sequence) {
        double sum = 0.0;
        int count = 0;
        if (sequence != null) {
            Iterator<Float> iterator = sequence.iterator();
            while (iterator.hasNext()) {
                Float element = iterator.next();
                sum += element;
                count += 1;
            }
        }
        return count == 0 ? Double.NaN : sum / count;
    }

    /**
     * Returns an average value of elements in the sequence.
     * <p>
     * The operation is _terminal_.
     */
    public static double averageOfDouble(@Nullable Sequence<Double> sequence) {
        double sum = 0.0;
        int count = 0;
        if (sequence != null) {
            Iterator<Double> iterator = sequence.iterator();
            while (iterator.hasNext()) {
                Double element = iterator.next();
                sum += element;
                count += 1;
            }
        }
        return count == 0 ? Double.NaN : sum / count;
    }
}
