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

import com.github.panpf.tools4j.common.*;
import com.github.panpf.tools4j.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.*;

/**
 * Collection tool method
 */
public class Collectionx {

    private Collectionx() {
    }


    /* ******************************************* null or empty ******************************************* */


    /**
     * Returns `true` if the collection is null or empty (contains no elements), `false` otherwise.
     */
    public static <T> boolean isNullOrEmpty(@Nullable Collection<T> collection) {
        return collection == null || collection.size() == 0;
    }

    /**
     * Returns `true` if the collection is not null and not empty.
     */
    public static <T> boolean isNotNullOrEmpty(@Nullable Collection<T> collection) {
        return collection != null && collection.size() > 0;
    }


    /* ******************************************* joinToArrayString ******************************************* */


    /**
     * Creates a string from all the elements separated using ', ' and using the given '[' and ']' if supplied.
     */
    @NotNull
    public static <T> String joinToArrayString(@Nullable Iterable<T> iterable, @Nullable Transformer<T, CharSequence> transform) {
        return joinToString(iterable, ", ", "[", "]", -1, null, transform);
    }

    /**
     * Creates a string from all the elements separated using ', ' and using the given '[' and ']' if supplied.
     */
    @NotNull
    public static <T> String joinToArrayString(@Nullable Iterable<T> iterable) {
        return joinToString(iterable, ", ", "[", "]", -1, null, null);
    }


    /* ******************************************* listOf ******************************************* */

    /**
     * Returns a new readable and writable linked list of given elements
     */
    @NotNull
    @SafeVarargs
    public static <T> LinkedList<T> linkedListOf(@NotNull T... elements) {
        if (elements.length > 0) {
            LinkedList<T> list = new LinkedList<>();
            addAll(list, elements);
            return list;
        } else {
            return new LinkedList<>();
        }
    }


    /*
     * *****************************************************************************************************************
     * From kotlin standard library
     * *****************************************************************************************************************
     */


    public static int collectionSizeOrDefault(@Nullable Iterable<?> iterable, int defaultValue) {
        if (iterable == null) return 0;
        return iterable instanceof Collection ? ((Collection<?>) iterable).size() : defaultValue;
    }


    /* ******************************************* empty ******************************************* */


    /**
     * Returns `true` if the collection is not null and empty (contains no elements), `false` otherwise.
     */
    public static <T> boolean isEmpty(@NotNull Collection<T> collection) {
        return collection.size() == 0;
    }

    /**
     * Returns `true` if the collection is not null and not empty.
     */
    public static <T> boolean isNotEmpty(@NotNull Collection<T> collection) {
        return collection.size() > 0;
    }


    /* ******************************************* orEmpty ******************************************* */

    /**
     * Returns this Collection if it's not `null` and the empty list otherwise.
     */
    @NotNull
    public static <T> Collection<T> orEmpty(@Nullable Collection<T> collection) {
        //noinspection unchecked
        return collection != null ? collection : (Collection<T>) Collectionx.arrayListOf();
    }

    /**
     * Returns this List if it's not `null` and the empty list otherwise.
     */
    @NotNull
    public static <T> List<T> orEmpty(@Nullable List<T> list) {
        //noinspection unchecked
        return list != null ? list : (List<T>) Collectionx.arrayListOf();
    }


    /* ******************************************* listOf ******************************************* */


    /**
     * Returns an empty read-only list.  The returned list is serializable (JVM).
     */
    @NotNull
    public static <T> List<T> immutableListOf() {
        //noinspection unchecked
        return (List<T>) Collections.EMPTY_LIST;
    }

    /**
     * Returns an immutable list containing only the specified object [element].
     * The returned list is serializable.
     */
    @NotNull
    public static <T> List<T> immutableListOf(@Nullable T element) {
        return Collections.singletonList(element);
    }

    /**
     * Returns a new read-only list of given elements.  The returned list is serializable (JVM).
     */
    @NotNull
    @SafeVarargs
    public static <T> List<T> immutableListOf(@NotNull T... elements) {
        //noinspection unchecked
        return elements.length > 0 ? Arrays.asList(elements) : (List<T>) Collections.EMPTY_LIST;
    }

    /**
     * Returns a new readable and writable list of given elements
     */
    @NotNull
    @SafeVarargs
    public static <T> List<T> mutableListOf(@NotNull T... elements) {
        return arrayListOf(elements);
    }

    /**
     * Returns a new readable and writable array list of given elements
     */
    @NotNull
    @SafeVarargs
    public static <T> ArrayList<T> arrayListOf(@NotNull T... elements) {
        if (elements.length > 0) {
            ArrayList<T> list = new ArrayList<>(elements.length);
            addAll(list, elements);
            return list;
        } else {
            return new ArrayList<>(0);
        }
    }


    /* ******************************************* setOf ******************************************* */


    /**
     * Returns a new read-only set of given elements
     */
    @NotNull
    public static <T> Set<T> immutableSetOf() {
        //noinspection unchecked
        return (Set<T>) Collections.EMPTY_SET;
    }

    /**
     * Returns a new read-only set of given elements
     */
    @NotNull
    public static <T> Set<T> immutableSetOf(@Nullable T element) {
        return Collections.singleton(element);
    }

    /**
     * Returns a new read-only set of given elements
     */
    @NotNull
    @SafeVarargs
    public static <T> Set<T> immutableSetOf(@NotNull T... elements) {
        if (elements.length > 0) {
            return Arrayx.toCollection(elements, new LinkedHashSet<T>());
        } else {
            //noinspection unchecked
            return Collections.EMPTY_SET;
        }
    }

    /**
     * Returns a new readable and writable set of given elements
     */
    @NotNull
    public static <T> Set<T> mutableSetOf() {
        return new LinkedHashSet<>();
    }

    /**
     * Returns a new readable and writable set of given elements
     */
    @NotNull
    @SafeVarargs
    public static <T> Set<T> mutableSetOf(@NotNull T... elements) {
        return Arrayx.toCollection(elements, new LinkedHashSet<T>(Mapx.capacity(elements.length)));
    }

    /**
     * Returns an empty new [HashSet]
     */
    @NotNull
    public static <T> HashSet<T> hashSetOf() {
        return new HashSet<>();
    }

    /**
     * Returns a new [HashSet] with the given elements.
     */
    @NotNull
    @SafeVarargs
    public static <T> HashSet<T> hashSetOf(@NotNull T... elements) {
        return Arrayx.toCollection(elements, new HashSet<T>(Mapx.capacity(elements.length)));
    }

    /**
     * Returns an empty new [LinkedHashSet]
     */
    @NotNull
    public static <T> LinkedHashSet<T> linkedSetOf() {
        return new LinkedHashSet<>();
    }

    /**
     * Returns a new [LinkedHashSet] with the given elements.
     * Elements of the set are iterated in the order they were specified.
     */
    @NotNull
    @SafeVarargs
    public static <T> LinkedHashSet<T> linkedSetOf(@NotNull T... elements) {
        return Arrayx.toCollection(elements, new LinkedHashSet<T>(Mapx.capacity(elements.length)));
    }

    /**
     * Returns a new [java.util.SortedSet] with the given elements.
     */
    @NotNull
    @SafeVarargs
    public static <T> TreeSet<T> sortedSetOf(@Nullable T... elements) {
        return Arrayx.toCollection(elements, new TreeSet<T>());
    }

    /**
     * Returns a new [java.util.SortedSet] with the given [comparator] and elements.
     */
    @NotNull
    @SafeVarargs
    public static <T> TreeSet<T> sortedSetOf(@NotNull Comparator<T> comparator, @Nullable T... elements) {
        return Arrayx.toCollection(elements, new TreeSet<>(comparator));
    }


    /* ******************************************* filter ******************************************* */


    /**
     * Appends all elements matching the given [predicate] to the given [destination].
     */
    @NotNull
    public static <T, C extends Collection<T>> C filterTo(@Nullable Iterable<T> iterable, @NotNull C destination,
                                                          @NotNull Predicate<T> predicate) {
        if (iterable != null) {
            for (T element : iterable) {
                if (predicate.accept(element)) {
                    destination.add(element);
                }
            }
        }
        return destination;
    }

    /**
     * Appends all elements not matching the given [predicate] to the given [destination].
     */
    @NotNull
    public static <T, C extends Collection<T>> C filterNotTo(@Nullable Iterable<T> iterable, @NotNull C destination,
                                                             @NotNull Predicate<T> predicate) {
        if (iterable != null) {
            for (T element : iterable) {
                if (!predicate.accept(element)) {
                    destination.add(element);
                }
            }
        }
        return destination;
    }

    /**
     * Appends all elements that are not `null` to the given [destination].
     */
    @NotNull
    public static <C extends Collection<T>, T> C filterNotNullTo(@Nullable Iterable<T> iterable, @NotNull C destination) {
        if (iterable != null) {
            for (T element : iterable) {
                if (element != null) {
                    destination.add(element);
                }
            }
        }
        return destination;
    }

    /**
     * Appends all elements matching the given [predicate] to the given [destination].
     *
     * @param predicate function that takes the index of an element and the element itself
     *                  and returns the result of predicate evaluation on the element.
     */
    @NotNull
    public static <T, C extends Collection<T>> C filterIndexedTo(@Nullable Iterable<T> iterable, @NotNull C destination,
                                                                 @NotNull IndexedPredicate<T> predicate) {
        int index = 0;
        if (iterable != null) {
            for (T element : iterable) {
                if (predicate.accept(index++, element)) {
                    destination.add(element);
                }
            }
        }
        return destination;
    }

    /**
     * Appends all elements that are instances of specified class to the given [destination].
     */
    @NotNull
    public static <C extends Collection<R>, R> C filterIsInstanceTo(@Nullable Iterable<?> iterable, @NotNull C destination,
                                                                    @NotNull Class<R> clazz) {
        if (iterable != null) {
            for (Object element : iterable) {
                if (clazz.isInstance(element)) {
                    //noinspection unchecked
                    destination.add((R) element);
                }
            }
        }
        return destination;
    }

    /**
     * Returns a list containing only elements matching the given [predicate].
     */
    @NotNull
    public static <T> List<T> filter(@Nullable Iterable<T> iterable, @NotNull Predicate<T> predicate) {
        return filterTo(iterable, new ArrayList<T>(), predicate);
    }

    /**
     * Returns a list containing all elements not matching the given [predicate].
     */
    @NotNull
    public static <T> List<T> filterNot(@Nullable Iterable<T> iterable, @NotNull Predicate<T> predicate) {
        return filterNotTo(iterable, new ArrayList<T>(), predicate);
    }

    /**
     * Returns a list containing all elements that are not `null`.
     */
    @NotNull
    public static <T> List<T> filterNotNull(@Nullable Iterable<T> iterable) {
        return filterNotNullTo(iterable, new ArrayList<T>());
    }

    /**
     * Returns a list containing only elements matching the given [predicate].
     *
     * @param predicate function that takes the index of an element and the element itself
     *                  and returns the result of predicate evaluation on the element.
     */
    @NotNull
    public static <T> List<T> filterIndexed(@Nullable Iterable<T> iterable, @NotNull IndexedPredicate<T> predicate) {
        return filterIndexedTo(iterable, new ArrayList<T>(), predicate);
    }

    /**
     * Returns a list containing all elements that are instances of specified class.
     */
    @NotNull
    public static <R> List<R> filterIsInstance(@Nullable Iterable<?> iterable, @NotNull Class<R> clazz) {
        return filterIsInstanceTo(iterable, new ArrayList<R>(), clazz);
    }

    public static <T> boolean filterInPlace(@Nullable Iterable<T> iterable, @NotNull Predicate<T> predicate, boolean predicateResultToRemove) {
        boolean result = false;
        if (iterable != null) {
            Iterator<T> iterator = iterable.iterator();
            while (iterator.hasNext()) {
                if (predicate.accept(iterator.next()) == predicateResultToRemove) {
                    iterator.remove();
                    result = true;
                }
            }
        }
        return result;
    }

    private static <T> boolean filterInPlace(@Nullable List<T> list, @NotNull Predicate<T> predicate, boolean predicateResultToRemove) {
        if (list instanceof RandomAccess) {
            int writeIndex = 0;
            for (int readIndex = 0, size = list.size(); readIndex < size; readIndex++) {
                T element = list.get(readIndex);
                if (predicate.accept(element) == predicateResultToRemove) {
                    continue;
                }

                if (writeIndex != readIndex) {
                    list.set(writeIndex, element);
                }

                writeIndex++;
            }
            if (writeIndex < list.size()) {
                list.subList(writeIndex, list.size()).clear();
                return true;
            } else {
                return false;
            }
        } else {
            return filterInPlace(((Iterable<T>) list), predicate, predicateResultToRemove);
        }
    }


    /* ******************************************* map ******************************************* */


    /**
     * Applies the given [transform] function to each element of the original collection
     * and appends the results to the given [destination].
     */
    @NotNull
    public static <T, R, D extends Collection<R>> D mapTo(@Nullable Iterable<T> iterable, @NotNull D destination,
                                                          @NotNull Transformer<T, R> transform) {
        if (iterable != null) {
            for (T t : iterable) {
                destination.add(transform.transform(t));
            }
        }
        return destination;
    }

    /**
     * Returns a list containing the results of applying the given [transform] function
     * to each element in the original collection.
     */
    @NotNull
    public static <T, R> List<R> map(@Nullable Iterable<T> iterable, @NotNull Transformer<T, R> transform) {
        return mapTo(iterable, new ArrayList<R>(collectionSizeOrDefault(iterable, 10)), transform);
    }


    /**
     * Applies the given [transform] function to each element and its index in the original collection
     * and appends the results to the given [destination].
     *
     * @param transform function that takes the index of an element and the element itself
     *                  and returns the result of the transform applied to the element.
     */
    @NotNull
    public static <T, R, D extends Collection<R>> D mapIndexedTo(@Nullable Iterable<T> iterable, @NotNull D destination,
                                                                 @NotNull IndexedTransformer<T, R> transform) {
        int index = 0;
        if (iterable != null) {
            for (T t : iterable) {
                destination.add(transform.transform(index, t));
            }
        }
        return destination;
    }

    /**
     * Returns a list containing the results of applying the given [transform] function
     * to each element and its index in the original collection.
     *
     * @param transform function that takes the index of an element and the element itself
     *                  and returns the result of the transform applied to the element.
     */
    @NotNull
    public static <T, R> List<R> mapIndexed(@Nullable Iterable<T> iterable, @NotNull IndexedTransformer<T, R> transform) {
        return mapIndexedTo(iterable, new ArrayList<R>(collectionSizeOrDefault(iterable, 10)), transform);
    }


    /**
     * Applies the given [transform] function to each element in the original collection
     * and appends only the non-null results to the given [destination].
     */
    @NotNull
    public static <T, R, D extends Collection<R>> D mapNotNullTo(@Nullable Iterable<T> iterable, @NotNull D destination,
                                                                 @NotNull NullableTransformer<T, R> transform) {
        if (iterable != null) {
            for (T t : iterable) {
                R r = transform.transform(t);
                if (r != null) {
                    destination.add(r);
                }
            }
        }
        return destination;
    }

    /**
     * Returns a list containing only the non-null results of applying the given [transform] function
     * to each element in the original collection.
     */
    @NotNull
    public static <T, R> List<R> mapNotNull(@Nullable Iterable<T> iterable, @NotNull NullableTransformer<T, R> transform) {
        return mapNotNullTo(iterable, new ArrayList<R>(collectionSizeOrDefault(iterable, 10)), transform);
    }


    /**
     * Applies the given [transform] function to each element and its index in the original collection
     * and appends only the non-null results to the given [destination].
     *
     * @param transform function that takes the index of an element and the element itself
     *                  and returns the result of the transform applied to the element.
     */
    @NotNull
    public static <T, R, D extends Collection<R>> D mapIndexedNotNullTo(@Nullable Iterable<T> iterable, @NotNull D destination,
                                                                        @NotNull NullableIndexedTransformer<T, R> transform) {
        int index = 0;
        if (iterable != null) {
            for (T t : iterable) {
                R r = transform.transform(index, t);
                if (r != null) {
                    destination.add(r);
                }
            }
        }
        return destination;
    }

    /**
     * Returns a list containing only the non-null results of applying the given [transform] function
     * to each element and its index in the original collection.
     *
     * @param transform function that takes the index of an element and the element itself
     *                  and returns the result of the transform applied to the element.
     */
    @NotNull
    public static <T, R> List<R> mapIndexedNotNull(@Nullable Iterable<T> iterable,
                                                   @NotNull NullableIndexedTransformer<T, R> transform) {
        return mapIndexedNotNullTo(iterable, new ArrayList<R>(collectionSizeOrDefault(iterable, 10)), transform);
    }


    /**
     * Appends all elements yielded from results of [transform] function being invoked on each element of original collection, to the given [destination].
     */
    @NotNull
    public static <T, R, C extends Collection<R>> C flatMapTo(@Nullable Iterable<T> iterable, @NotNull C destination, @NotNull Transformer<T, Iterable<R>> transform) {
        if (iterable != null) {
            for (T element : iterable) {
                Iterable<R> list = transform.transform(element);
                addAll(destination, list);
            }
        }
        return destination;
    }

    /**
     * Returns a single list of all elements yielded from results of [transform] function being invoked on each element of original collection.
     */
    @NotNull
    public static <T, R> List<R> flatMap(@Nullable Iterable<T> iterable, @NotNull Transformer<T, Iterable<R>> transform) {
        return flatMapTo(iterable, new ArrayList<R>(), transform);
    }


    /* ******************************************* join ******************************************* */


    /**
     * Appends the string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static <T, A extends Appendable> A joinTo(@Nullable Iterable<T> iterable, @NotNull A buffer, @Nullable CharSequence separator,
                                                     @Nullable CharSequence prefix, @Nullable CharSequence postfix, int limit,
                                                     @Nullable CharSequence truncated, @Nullable Transformer<T, CharSequence> transform) {
        if (separator == null) separator = ", ";
        if (prefix == null) prefix = "";
        if (postfix == null) postfix = "";
        if (truncated == null) truncated = "...";

        appendElement(buffer, prefix, null);
        int count = 0;
        if (iterable != null) {
            for (T element : iterable) {
                if (++count > 1) {
                    appendElement(buffer, separator, null);
                }
                if (limit < 0 || count <= limit) {
                    appendElement(buffer, element, transform);
                } else {
                    break;
                }
            }
        }
        if (limit >= 0 && count > limit) {
            appendElement(buffer, truncated, null);
        }
        appendElement(buffer, postfix, null);
        return buffer;
    }

    /**
     * Appends the string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static <T, A extends Appendable> A joinTo(@Nullable Iterable<T> iterable, @NotNull A buffer, @Nullable CharSequence separator) {
        return joinTo(iterable, buffer, separator, null, null, -1, null, null);
    }

    /**
     * Appends the string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static <T, A extends Appendable> A joinTo(@Nullable Iterable<T> iterable, @NotNull A buffer) {
        return joinTo(iterable, buffer, null, null, null, -1, null, null);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static <T> String joinToString(@Nullable Iterable<T> iterable, @Nullable CharSequence separator,
                                          @Nullable CharSequence prefix, @Nullable CharSequence postfix, int limit,
                                          @Nullable CharSequence truncated, @Nullable Transformer<T, CharSequence> transform) {
        return joinTo(iterable, new StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString();
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static <T> String joinToString(@Nullable Iterable<T> iterable, @Nullable CharSequence separator, @NotNull Transformer<T, CharSequence> transform) {
        return joinToString(iterable, separator, null, null, -1, null, transform);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static <T> String joinToString(@Nullable Iterable<T> iterable, @NotNull Transformer<T, CharSequence> transform) {
        return joinToString(iterable, null, null, null, -1, null, transform);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static <T> String joinToString(@Nullable Iterable<T> iterable, @Nullable CharSequence separator) {
        return joinToString(iterable, separator, null, null, -1, null, null);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static <T> String joinToString(@Nullable Iterable<T> iterable) {
        return joinToString(iterable, null, null, null, -1, null, null);
    }


    /* ******************************************* count ******************************************* */


    /**
     * Returns the number of elements in this collection.
     */
    public static <T> int count(@Nullable Iterable<T> iterable) {
        if (iterable == null) return 0;
        if (iterable instanceof Collection) {
            return ((Collection<T>) iterable).size();
        }
        int count = 0;
        for (T ignored : iterable) {
            count++;
        }
        return count;
    }

    /**
     * Returns the number of elements matching the given [predicate].
     */
    public static <T> int count(@Nullable Iterable<T> iterable, @NotNull Predicate<T> predicate) {
        if (iterable == null) return 0;
        if (iterable instanceof Collection && ((Collection<T>) iterable).isEmpty()) {
            return 0;
        }
        int count = 0;
        for (T element : iterable) {
            if (predicate.accept(element)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns the number of elements in this collection.
     */
    public static <T> int count(@Nullable Collection<T> collection) {
        return collection == null ? 0 : collection.size();
    }


    /* ******************************************* average ******************************************* */


    /**
     * Returns an average value of elements in the collection.
     */
    public static double averageOfByte(@Nullable Iterable<Byte> iterable) {
        double sum = 0.0;
        int count = 0;
        if (iterable != null) {
            for (byte element : iterable) {
                sum += element;
                count += 1;
            }
        }
        return count == 0 ? Double.NaN : sum / count;
    }

    /**
     * Returns an average value of elements in the collection.
     */
    public static double averageOfShort(@Nullable Iterable<Short> iterable) {
        double sum = 0.0;
        int count = 0;
        if (iterable != null) {
            for (short element : iterable) {
                sum += element;
                count += 1;
            }
        }
        return count == 0 ? Double.NaN : sum / count;
    }

    /**
     * Returns an average value of elements in the collection.
     */
    public static double averageOfInt(@Nullable Iterable<Integer> iterable) {
        double sum = 0.0;
        int count = 0;
        if (iterable != null) {
            for (int element : iterable) {
                sum += element;
                count += 1;
            }
        }
        return count == 0 ? Double.NaN : sum / count;
    }

    /**
     * Returns an average value of elements in the collection.
     */
    public static double averageOfLong(@Nullable Iterable<Long> iterable) {
        double sum = 0.0;
        int count = 0;
        if (iterable != null) {
            for (long element : iterable) {
                sum += element;
                count += 1;
            }
        }
        return count == 0 ? Double.NaN : sum / count;
    }

    /**
     * Returns an average value of elements in the collection.
     */
    public static double averageOfFloat(@Nullable Iterable<Float> iterable) {
        double sum = 0.0;
        int count = 0;
        if (iterable != null) {
            for (float element : iterable) {
                sum += element;
                count += 1;
            }
        }
        return count == 0 ? Double.NaN : sum / count;
    }

    /**
     * Returns an average value of elements in the collection.
     */
    public static double averageOfDouble(@Nullable Iterable<Double> iterable) {
        double sum = 0.0;
        int count = 0;
        if (iterable != null) {
            for (double element : iterable) {
                sum += element;
                count += 1;
            }
        }
        return count == 0 ? Double.NaN : sum / count;
    }


    /* ******************************************* sum ******************************************* */


    /**
     * Returns the sum of all elements in the collection.
     */
    public static int sumOfByte(@Nullable Iterable<Byte> iterable) {
        int sum = 0;
        if (iterable != null) {
            for (byte element : iterable) {
                sum += element;
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all elements in the collection.
     */
    public static int sumOfShort(@Nullable Iterable<Short> iterable) {
        int sum = 0;
        if (iterable != null) {
            for (short element : iterable) {
                sum += element;
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all elements in the collection.
     */
    public static int sumOfInt(@Nullable Iterable<Integer> iterable) {
        int sum = 0;
        if (iterable != null) {
            for (int element : iterable) {
                sum += element;
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all elements in the collection.
     */
    public static long sumOfLong(@Nullable Iterable<Long> iterable) {
        long sum = 0L;
        if (iterable != null) {
            for (long element : iterable) {
                sum += element;
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all elements in the collection.
     */
    public static float sumOfFloat(@Nullable Iterable<Float> iterable) {
        float sum = 0.0f;
        if (iterable != null) {
            for (float element : iterable) {
                sum += element;
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all elements in the collection.
     */
    public static double sumOfDouble(@Nullable Iterable<Double> iterable) {
        double sum = 0.0;
        if (iterable != null) {
            for (double element : iterable) {
                sum += element;
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all values produced by [selector] function applied to each element in the collection.
     */
    public static <T> int sumBy(@Nullable Iterable<T> iterable, @NotNull Transformer<T, Integer> transformer) {
        int sum = 0;
        if (iterable != null) {
            for (T element : iterable) {
                sum += transformer.transform(element);
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all values produced by [selector] function applied to each element in the collection.
     */
    public static <T> double sumByDouble(@Nullable Iterable<T> iterable, @NotNull Transformer<T, Double> transformer) {
        double sum = 0.0;
        if (iterable != null) {
            for (T element : iterable) {
                sum += transformer.transform(element);
            }
        }
        return sum;
    }


    /* ******************************************* first ******************************************* */


    /**
     * Returns first element.
     *
     * @throws NoSuchElementException if the list is empty.
     */
    @NotNull
    public static <T> T first(@Nullable List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns first element.
     *
     * @throws NoSuchElementException if the collection is empty.
     */
    @NotNull
    public static <T> T first(@Nullable Iterable<T> iterable) {
        if (iterable instanceof List) {
            return first((List<T>) iterable);
        } else {
            Iterator<T> iterator = iterable != null ? iterable.iterator() : null;
            if (iterator == null || !iterator.hasNext()) {
                throw new NoSuchElementException("Collection is empty.");
            } else {
                return iterator.next();
            }
        }
    }

    /**
     * Returns the first element matching the given [predicate].
     *
     * @throws NoSuchElementException if no such element is found.
     */
    @NotNull
    public static <T> T first(@Nullable Iterable<T> iterable, @NotNull Predicate<T> predicate) {
        if (iterable != null) {
            for (T element : iterable) {
                if (predicate.accept(element)) {
                    return element;
                }
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    /**
     * Returns the first element, or `null` if the list is empty.
     */
    @Nullable
    public static <T> T firstOrNull(@Nullable List<T> list) {
        return list == null || list.isEmpty() ? null : list.get(0);
    }

    /**
     * Returns the first element, or `null` if the collection is empty.
     */
    @Nullable
    public static <T> T firstOrNull(@Nullable Iterable<T> iterable) {
        if (iterable instanceof List) {
            return firstOrNull((List<T>) iterable);
        } else {
            Iterator<T> iterator = iterable != null ? iterable.iterator() : null;
            return iterator == null || !iterator.hasNext() ? null : iterator.next();
        }
    }

    /**
     * Returns the first element matching the given [predicate], or `null` if element was not found.
     */
    @Nullable
    public static <T> T firstOrNull(@Nullable Iterable<T> iterable, @NotNull Predicate<T> predicate) {
        if (iterable != null) {
            for (T element : iterable) {
                if (predicate.accept(element)) {
                    return element;
                }
            }
        }
        return null;
    }


    /* ******************************************* last ******************************************* */


    /**
     * Returns the last element.
     *
     * @throws NoSuchElementException if the list is empty.
     */
    @NotNull
    public static <T> T last(@Nullable List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        } else {
            return list.get(list.size() - 1);
        }
    }

    /**
     * Returns the last element matching the given [predicate].
     *
     * @throws NoSuchElementException if no such element is found.
     */
    @NotNull
    public static <T> T last(@Nullable List<T> list, @NotNull Predicate<T> predicate) {
        if (list != null) {
            ListIterator<T> iterator = list.listIterator(list.size());
            while (iterator.hasPrevious()) {
                T element = iterator.previous();
                if (predicate.accept(element)) return element;
            }
        }
        throw new NoSuchElementException("List contains no element matching the predicate.");
    }

    /**
     * Returns the last element.
     *
     * @throws NoSuchElementException if the collection is empty.
     */
    @NotNull
    public static <T> T last(@Nullable Iterable<T> iterable) {
        if (iterable instanceof List) {
            return last((List<T>) iterable);
        } else {
            Iterator<T> iterator = iterable != null ? iterable.iterator() : null;
            if (iterator == null || !iterator.hasNext()) {
                throw new NoSuchElementException("Collection is empty.");
            } else {
                T last = iterator.next();
                while (iterator.hasNext()) {
                    last = iterator.next();
                }
                return last;
            }
        }
    }

    /**
     * Returns the last element matching the given [predicate].
     *
     * @throws NoSuchElementException if no such element is found.
     */
    @NotNull
    public static <T> T last(@Nullable Iterable<T> iterable, @NotNull Predicate<T> predicate) {
        T last = null;
        boolean found = false;
        if (iterable != null) {
            for (T element : iterable) {
                if (predicate.accept(element)) {
                    last = element;
                    found = true;
                }
            }
        }
        if (!found) {
            throw new NoSuchElementException("Collection contains no element matching the predicate.");
        } else {
            return last;
        }
    }

    /**
     * Returns the last element, or `null` if the list is empty.
     */
    @Nullable
    public static <T> T lastOrNull(@Nullable List<T> list) {
        return list == null || list.isEmpty() ? null : list.get(list.size() - 1);
    }

    /**
     * Returns the last element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static <T> T lastOrNull(@Nullable List<T> list, @NotNull Predicate<T> predicate) {
        if (list != null) {
            ListIterator<T> iterator = list.listIterator(list.size());
            while (iterator.hasPrevious()) {
                T element = iterator.previous();
                if (predicate.accept(element)) return element;
            }
        }
        return null;
    }

    /**
     * Returns the last element, or `null` if the collection is empty.
     */
    @Nullable
    public static <T> T lastOrNull(@Nullable Iterable<T> iterable) {
        if (iterable instanceof List) {
            List<T> list = (List<T>) iterable;
            return list.isEmpty() ? null : list.get(list.size() - 1);
        } else {
            Iterator<T> iterator = iterable != null ? iterable.iterator() : null;
            if (iterator == null || !iterator.hasNext()) {
                return null;
            }
            T last = iterator.next();
            while (iterator.hasNext()) {
                last = iterator.next();
            }
            return last;
        }
    }

    /**
     * Returns the last element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static <T> T lastOrNull(@Nullable Iterable<T> iterable, @NotNull Predicate<T> predicate) {
        T last = null;
        if (iterable != null) {
            for (T element : iterable) {
                if (predicate.accept(element)) {
                    last = element;
                }
            }
        }
        return last;
    }


    /* ******************************************* find ******************************************* */


    /**
     * Returns the first element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static <T> T find(@Nullable Iterable<T> iterable, @NotNull Predicate<T> predicate) {
        return firstOrNull(iterable, predicate);
    }

    /**
     * Returns the last element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static <T> T findLast(@Nullable Iterable<T> iterable, @NotNull Predicate<T> predicate) {
        return lastOrNull(iterable, predicate);
    }

    /**
     * Returns the last element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static <T> T findLast(@Nullable List<T> list, @NotNull Predicate<T> predicate) {
        return lastOrNull(list, predicate);
    }


    /* ******************************************* get ******************************************* */


    /**
     * Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this list.
     */
    @NotNull
    public static <T> T getOrElse(@Nullable List<T> list, int index, @NotNull Transformer<Integer, T> defaultValue) {
        return list != null && index >= 0 && index <= list.size() - 1 ? list.get(index) : defaultValue.transform(index);
    }

    /**
     * Returns an element at the given [index] or `null` if the [index] is out of bounds of this list.
     */
    @Nullable
    public static <T> T getOrNull(@Nullable List<T> list, int index) {
        return list != null && index >= 0 && index <= list.size() - 1 ? list.get(index) : null;
    }


    /* ******************************************* max ******************************************* */


    /**
     * Returns the largest element or `null` if there are no elements.
     * <p>
     * If any of elements is `NaN` returns `NaN`.
     */
    @Nullable
    public static Double maxOfDouble(@Nullable Iterable<Double> iterable) {
        Iterator<Double> iterator = iterable != null ? iterable.iterator() : null;
        if (iterable == null || !iterator.hasNext()) return null;
        double max = iterator.next();
        if (Double.isNaN(max)) return max;
        while (iterator.hasNext()) {
            double e = iterator.next();
            if (Double.isNaN(e)) return e;
            if (max < e) max = e;
        }
        return max;
    }

    /**
     * Returns the largest element or `null` if there are no elements.
     * <p>
     * If any of elements is `NaN` returns `NaN`.
     */
    @Nullable
    public static Float maxOfFloat(@Nullable Iterable<Float> iterable) {
        Iterator<Float> iterator = iterable != null ? iterable.iterator() : null;
        if (iterable == null || !iterator.hasNext()) return null;
        float max = iterator.next();
        if (Float.isNaN(max)) return max;
        while (iterator.hasNext()) {
            float e = iterator.next();
            if (Float.isNaN(e)) return e;
            if (max < e) max = e;
        }
        return max;
    }

    /**
     * Returns the largest element or `null` if there are no elements.
     */
    @Nullable
    public static <T extends Comparable<T>> T max(@Nullable Iterable<T> iterable) {
        Iterator<T> iterator = iterable != null ? iterable.iterator() : null;
        if (iterable == null || !iterator.hasNext()) return null;
        T max = iterator.next();
        while (iterator.hasNext()) {
            T e = iterator.next();
            if (max.compareTo(e) < 0) max = e;
        }
        return max;
    }

    /**
     * Returns the first element yielding the largest value of the given function or `null` if there are no elements.
     */
    @Nullable
    public static <T, R extends Comparable<R>> T maxBy(@Nullable Iterable<T> iterable, @NotNull Transformer<T, R> transformer) {
        Iterator<T> iterator = iterable != null ? iterable.iterator() : null;
        if (iterable == null || !iterator.hasNext()) return null;
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
     */
    @Nullable
    public static <T> T maxWith(@Nullable Iterable<T> iterable, @NotNull Comparator<T> comparator) {
        Iterator<T> iterator = iterable != null ? iterable.iterator() : null;
        if (iterable == null || !iterator.hasNext()) return null;
        T max = iterator.next();
        while (iterator.hasNext()) {
            T e = iterator.next();
            if (comparator.compare(max, e) < 0) max = e;
        }
        return max;
    }


    /* ******************************************* min ******************************************* */


    /**
     * Returns the smallest element or `null` if there are no elements.
     * <p>
     * If any of elements is `NaN` returns `NaN`.
     */
    @Nullable
    public static Double minOfDouble(@Nullable Iterable<Double> iterable) {
        Iterator<Double> iterator = iterable != null ? iterable.iterator() : null;
        if (iterable == null || !iterator.hasNext()) return null;
        double min = iterator.next();
        if (Double.isNaN(min)) return min;
        while (iterator.hasNext()) {
            double e = iterator.next();
            if (Double.isNaN(e)) return e;
            if (min > e) min = e;
        }
        return min;
    }

    /**
     * Returns the smallest element or `null` if there are no elements.
     * <p>
     * If any of elements is `NaN` returns `NaN`.
     */
    @Nullable
    public static Float minOfFloat(@Nullable Iterable<Float> iterable) {
        Iterator<Float> iterator = iterable != null ? iterable.iterator() : null;
        if (iterable == null || !iterator.hasNext()) return null;
        float min = iterator.next();
        if (Float.isNaN(min)) return min;
        while (iterator.hasNext()) {
            float e = iterator.next();
            if (Float.isNaN(e)) return e;
            if (min > e) min = e;
        }
        return min;
    }

    /**
     * Returns the smallest element or `null` if there are no elements.
     */
    @Nullable
    public static <T extends Comparable<T>> T min(@Nullable Iterable<T> iterable) {
        Iterator<T> iterator = iterable != null ? iterable.iterator() : null;
        if (iterable == null || !iterator.hasNext()) return null;
        T min = iterator.next();
        while (iterator.hasNext()) {
            T e = iterator.next();
            if (min.compareTo(e) > 0) min = e;
        }
        return min;
    }

    /**
     * Returns the first element yielding the smallest value of the given function or `null` if there are no elements.
     */
    @Nullable
    public static <T, R extends Comparable<R>> T minBy(@Nullable Iterable<T> iterable, @NotNull Transformer<T, R> transformer) {
        Iterator<T> iterator = iterable != null ? iterable.iterator() : null;
        if (iterable == null || !iterator.hasNext()) return null;
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
     */
    @Nullable
    public static <T> T minWith(@Nullable Iterable<T> iterable, @NotNull Comparator<T> comparator) {
        Iterator<T> iterator = iterable != null ? iterable.iterator() : null;
        if (iterable == null || !iterator.hasNext()) return null;
        T min = iterator.next();
        while (iterator.hasNext()) {
            T e = iterator.next();
            if (comparator.compare(min, e) > 0) min = e;
        }
        return min;
    }


    /* ******************************************* add ******************************************* */


    /**
     * Adds all elements of the given [elements] collection to this [Collection].
     */
    @SuppressWarnings("UnusedReturnValue")
    public static <T> boolean addAll(@Nullable Collection<T> collection, @Nullable Iterable<T> elements) {
        if (collection == null || elements == null) return false;
        if (elements instanceof Collection) {
            return collection.addAll((Collection<? extends T>) elements);
        } else {
            boolean result = false;
            for (T item : elements) {
                if (collection.add(item)) result = true;
            }
            return result;
        }
    }


    /**
     * Adds all elements of the given [elements] collection to this [Collection].
     */
    @SuppressWarnings("UnusedReturnValue")
    public static <T> boolean addAll(@Nullable Collection<T> collection, @Nullable T[] elements) {
        if (collection == null || elements == null) return false;
        boolean result = false;
        for (T item : elements) {
            if (collection.add(item)) result = true;
        }
        return result;
    }


    /* ******************************************* remove ******************************************* */


    /**
     * Removes all elements from this [Iterable] that match the given [predicate].
     */
    public static <T> boolean removeAll(@Nullable Iterable<T> iterable, @NotNull Predicate<T> predicate) {
        return filterInPlace(iterable, predicate, true);
    }

    /**
     * Removes all elements from this [List] that match the given [predicate].
     */
    public static <T> boolean removeAll(@Nullable List<T> list, @NotNull Predicate<T> predicate) {
        return filterInPlace(list, predicate, true);
    }

    /**
     * Removes all elements from this [MutableCollection] that are also contained in the given [elements] collection.
     */
    public static <T> boolean removeAll(@Nullable Collection<T> collection, @Nullable Iterable<T> elements) {
        return collection != null && elements != null && removeAll(collection, convertToSetForSetOperationWith(elements, collection));
    }

    /**
     * Removes all elements from this [MutableCollection] that are also contained in the given [elements] array.
     */
    public static <T> boolean removeAll(@Nullable Collection<T> collection, @Nullable T[] elements) {
        return Arrayx.isNotNullOrEmpty(elements) && collection != null && collection.removeAll(Arrayx.toHashSet(elements));
    }

    /**
     * Removes all of this collection's elements that are also contained in the specified collection.
     * Allows to overcome type-safety restriction of `removeAll` that requires to pass a collection of type `Collection[E]`.
     *
     * @return `true` if any of the specified elements was removed from the collection, `false` if the collection was not modified.
     */
    public static <T> boolean removeAll(@Nullable Collection<T> collection, @Nullable Collection<T> elements) {
        return collection != null && elements != null && collection.removeAll(elements);
    }


    /* ******************************************* partition ******************************************* */


    /**
     * Splits the original collection into pair of lists,
     * where *first* list contains elements for which [predicate] yielded `true`,
     * while *second* list contains elements for which [predicate] yielded `false`.
     */
    @NotNull
    public static <T> Pair<List<T>, List<T>> partition(@Nullable Iterable<T> iterable, @NotNull Predicate<T> predicate) {
        List<T> first = new ArrayList<>();
        List<T> second = new ArrayList<>();
        if (iterable != null) {
            for (T element : iterable) {
                if (predicate.accept(element)) {
                    first.add(element);
                } else {
                    second.add(element);
                }
            }
        }
        return Pair.of(first, second);
    }


    /* ******************************************* to ******************************************* */


    /**
     * Appends all elements to the given [destination] collection.
     */
    @NotNull
    public static <T, C extends Collection<T>> C toCollection(@Nullable Iterable<T> iterable, @NotNull C destination) {
        if (iterable != null) {
            for (T item : iterable) {
                destination.add(item);
            }
        }
        return destination;
    }

    /**
     * Returns a [List] all elements.
     */
    @NotNull
    public static <T> List<T> toList(@Nullable Iterable<T> iterable) {
        if (iterable instanceof Collection) {
            return toCollection(iterable, new ArrayList<T>(((Collection<T>) iterable).size()));
        } else {
            return toCollection(iterable, new ArrayList<T>());
        }
    }

    /**
     * Returns a [Set] all elements.
     * <p>
     * The returned set preserves the element iteration order of the original collection.
     */
    @NotNull
    public static <T> Set<T> toSet(@Nullable Iterable<T> iterable) {
        if (iterable instanceof Collection) {
            return toCollection(iterable, new LinkedHashSet<T>(Mapx.capacity(collectionSizeOrDefault(iterable, ((Collection<T>) iterable).size()))));
        } else {
            return toCollection(iterable, new LinkedHashSet<T>());
        }
    }

    /**
     * Returns a [SortedSet] of all elements.
     */
    @NotNull
    public static <T extends Comparable<T>> SortedSet<T> toSortedSet(@Nullable Iterable<T> iterable) {
        return toCollection(iterable, new TreeSet<T>());
    }

    /**
     * Returns a [SortedSet] of all elements.
     * Elements in the set returned are sorted according to the given [comparator].
     */
    @NotNull
    public static <T> SortedSet<T> toSortedSet(@Nullable Iterable<T> iterable, @NotNull Comparator<T> comparator) {
        return toCollection(iterable, new TreeSet<>(comparator));
    }

    /**
     * Returns a [Set] all elements.
     * <p>
     * The returned set preserves the element iteration order of the original collection.
     */
    @NotNull
    public static <T> HashSet<T> toHashSet(@Nullable Iterable<T> iterable) {
        return toCollection(iterable, new HashSet<T>(Mapx.capacity(collectionSizeOrDefault(iterable, 12))));
    }

    /**
     * Returns true when it's safe to convert this collection to a set without changing contains method behavior.
     */
    public static <T> boolean safeToConvertToSet(@Nullable Collection<T> collection) {
        return collection != null && collection.size() > 2 && collection instanceof ArrayList;
    }

    /**
     * Converts this collection to a set, when it's worth so and it doesn't change contains method behavior.
     */
    @NotNull
    public static <T> Collection<T> convertToSetForSetOperationWith(@Nullable Iterable<T> iterable, @NotNull Iterable<T> source) {
        if (iterable instanceof Set) {
            return (Set<T>) iterable;
        } else if (iterable instanceof Collection) {
            if (source instanceof Collection && ((Collection) source).size() < 2) {
                return (Collection<T>) iterable;
            } else if (safeToConvertToSet((Collection<T>) iterable)) {
                return toHashSet(iterable);
            } else {
                return (Collection<T>) iterable;
            }
        } else {
            return toHashSet(iterable);
        }
    }

    /**
     * Converts this collection to a set, when it's worth so and it doesn't change contains method behavior.
     */
    @NotNull
    public static <T> Collection<T> convertToSetForSetOperation(@Nullable Iterable<T> iterable) {
        if (iterable instanceof Set) {
            return (Set<T>) iterable;
        } else if (iterable instanceof Collection) {
            if (safeToConvertToSet((Collection<T>) iterable)) {
                return toHashSet(iterable);
            } else {
                return (Collection<T>) iterable;
            }
        } else {
            return toHashSet(iterable);
        }
    }


    /* ******************************************* union ******************************************* */


    /**
     * Returns a set containing all distinct elements from both collections.
     * <p>
     * The returned set preserves the element iteration order of the original collection.
     * Those elements of the [other] collection that are unique are iterated in the end
     * in the order of the [other] collection.
     */
    @NotNull
    public static <T> Set<T> union(@Nullable Iterable<T> self, @Nullable Iterable<T> other) {
        LinkedHashSet<T> set = toCollection(self, new LinkedHashSet<T>());
        addAll(set, other);
        return set;
    }


    /* ******************************************* all ******************************************* */

    /**
     * Returns `true` if all elements match the given [predicate].
     */
    public static <T> boolean all(@Nullable Iterable<T> iterable, @NotNull Predicate<T> predicate) {
        if (iterable == null || iterable instanceof Collection && ((Collection) iterable).isEmpty()) {
            return true;
        }
        for (T element : iterable) {
            if (!predicate.accept(element)) {
                return false;
            }
        }
        return true;
    }


    /* ******************************************* any ******************************************* */

    /**
     * Returns `true` if collection has at least one element.
     */
    public static <T> boolean any(@Nullable Iterable<T> iterable) {
        if (iterable == null) return false;
        return iterable instanceof Collection ? !((Collection) iterable).isEmpty() : iterable.iterator().hasNext();
    }

    /**
     * Returns `true` if at least one element matches the given [predicate].
     */
    public static <T> boolean any(@Nullable Iterable<T> iterable, @NotNull Predicate<T> predicate) {
        if (iterable == null || iterable instanceof Collection && ((Collection) iterable).isEmpty()) return false;
        for (T element : iterable) if (predicate.accept(element)) return true;
        return false;
    }


    /* ******************************************* none ******************************************* */

    /**
     * Returns `true` if the collection has no elements.
     */
    public static <T> boolean none(@Nullable Iterable<T> iterable) {
        if (iterable == null) return true;
        return iterable instanceof Collection ? ((Collection) iterable).isEmpty() : !iterable.iterator().hasNext();
    }

    /**
     * Returns `true` if no elements match the given [predicate].
     */
    public static <T> boolean none(@Nullable Iterable<T> iterable, @NotNull Predicate<T> predicate) {
        if (iterable == null || iterable instanceof Collection && ((Collection) iterable).isEmpty()) return true;
        for (T element : iterable) if (predicate.accept(element)) return false;
        return true;
    }


    /* ******************************************* contains ******************************************* */

    /**
     * Returns `true` if [element] is found in the collection.
     */
    public static <T> boolean contains(@Nullable Iterable<T> iterable, @NotNull T element) {
        if (iterable == null) return false;
        if (iterable instanceof Collection) {
            return ((Collection) iterable).contains(element);
        } else {
            return indexOf(iterable, element) >= 0;
        }
    }


    /* ******************************************* forEach ******************************************* */


    /**
     * Performs the given [action] on each element.
     */
    public static <T> void forEach(@Nullable Iterable<T> iterable, @NotNull Action<T> action) {
        if (iterable == null) return;
        for (T element : iterable) action.action(element);
    }

    /**
     * Performs the given [action] on each element, providing sequential index with the element.
     *
     * @param action function that takes the index of an element and the element itself
     *               and performs the desired action on the element.
     */
    public static <T> void forEachIndexed(@Nullable Iterable<T> iterable, @NotNull IndexedAction<T> action) {
        if (iterable == null) return;
        int index = 0;
        for (T item : iterable) {
            action.action(index++, item);
        }
    }


    /* ******************************************* chunked ******************************************* */


    /**
     * Splits this collection into a list of lists each not exceeding the given [size].
     * <p>
     * The last list in the resulting list may have less elements than the given [size].
     *
     * @param size the number of elements to take in each list, must be positive and can be greater than the number of elements in this collection.
     */
    @NotNull
    public static <T> List<List<T>> chunked(@Nullable Iterable<T> iterable, int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Param 'size' is less than or equal to zero.");
        }

        int listSize = count(iterable);
        int resultSize = (listSize / size) + (listSize % size == 0 ? 0 : 1);
        List<List<T>> resultList = new ArrayList<>(resultSize);
        List<T> chunkedList = null;
        int index = 0;
        if (iterable != null) {
            for (T element : iterable) {
                if (chunkedList == null) {
                    chunkedList = new ArrayList<>(Math.min(size, listSize - index));
                }
                chunkedList.add(element);
                if (chunkedList.size() >= size) {
                    resultList.add(chunkedList);
                    chunkedList = null;
                }
                index++;
            }
        }
        return resultList;
    }

    /**
     * Splits this collection into several lists each not exceeding the given [size]
     * and applies the given [transform] function to an each.
     *
     * @param size the number of elements to take in each list, must be positive and can be greater than the number of elements in this collection.
     * @return list of results of the [transform] applied to an each list.
     * <p>
     * Note that the list passed to the [transform] function is ephemeral and is valid only inside that function.
     * You should not store it or allow it to escape in some way, unless you made a snapshot of it.
     * The last list may have less elements than the given [size].
     */
    @NotNull
    public static <T, R> List<R> chunked(@Nullable Iterable<T> iterable, int size, @NotNull Transformer<List<T>, R> transform) {
        if (size <= 0) {
            throw new IllegalArgumentException("Param 'size' is less than or equal to zero.");
        }

        int listSize = count(iterable);
        int resultSize = (listSize / size) + (listSize % size == 0 ? 0 : 1);
        List<R> resultList = new ArrayList<>(resultSize);
        List<T> chunkedList = null;
        int index = 0;
        if (iterable != null) {
            for (T element : iterable) {
                if (chunkedList == null) {
                    chunkedList = new ArrayList<>(Math.min(size, listSize - index));
                }
                chunkedList.add(element);
                if (chunkedList.size() >= size) {
                    resultList.add(transform.transform(chunkedList));
                    chunkedList = null;
                }
                index++;
            }
        }
        return resultList;
    }


    /* ******************************************* minus ******************************************* */


    /**
     * Returns a list containing all elements of the original collection without the first occurrence of the given [element].
     */
    @NotNull
    public static <T> List<T> minus(@Nullable Iterable<T> iterable, @Nullable final T element) {
        if (element == null) return toList(iterable);
        ArrayList<T> result = new ArrayList<>(collectionSizeOrDefault(iterable, 10));
        final boolean[] removed = new boolean[]{false};
        return filterTo(iterable, result, new Predicate<T>() {
            @Override
            public boolean accept(@NotNull T t) {
                if (!removed[0] && t.equals(element)) {
                    removed[0] = true;
                    return false;
                } else {
                    return true;
                }
            }
        });
    }

    /**
     * Returns a list containing all elements of the original collection except the elements contained in the given [elements] array.
     */
    @NotNull
    public static <T> List<T> minus(@Nullable Iterable<T> iterable, @Nullable T[] elements) {
        if (elements == null || elements.length <= 0) return toList(iterable);
        final HashSet<T> other = Arrayx.toHashSet(elements);
        return filterNot(iterable, new Predicate<T>() {
            @Override
            public boolean accept(@NotNull T t) {
                return other.contains(t);
            }
        });
    }

    /**
     * Returns a list containing all elements of the original collection except the elements contained in the given [elements] collection.
     */
    @NotNull
    public static <T> List<T> minus(@Nullable Iterable<T> iterable, @Nullable Iterable<T> elements) {
        if (elements == null) return toList(iterable);
        final Collection<T> other = iterable != null ? convertToSetForSetOperationWith(elements, iterable) : null;
        if (other == null || other.isEmpty()) return toList(iterable);
        return filterNot(iterable, new Predicate<T>() {
            @Override
            public boolean accept(@NotNull T t) {
                return other.contains(t);
            }
        });
    }

    /**
     * Returns a list containing all elements of the original collection without the first occurrence of the given [element].
     */
    @NotNull
    public static <T> List<T> minusElement(@Nullable Iterable<T> iterable, @NotNull T element) {
        return minus(iterable, element);
    }


    /* ******************************************* plus ******************************************* */


    /**
     * Returns a list containing all elements of the original collection and then the given [element].
     */
    @NotNull
    public static <T> List<T> plus(@Nullable Collection<T> collection, @Nullable T element) {
        if (element == null) return toList(collection);
        ArrayList<T> result = new ArrayList<>(count(collection) + 1);
        if (collection != null) result.addAll(collection);
        result.add(element);
        return result;
    }

    /**
     * Returns a list containing all elements of the original collection and then the given [element].
     */
    @NotNull
    public static <T> List<T> plus(@Nullable Iterable<T> iterable, @Nullable T element) {
        if (element == null) return toList(iterable);
        if (iterable instanceof Collection) {
            return plus(((Collection<T>) iterable), element);
        } else {
            ArrayList<T> result = new ArrayList<>();
            addAll(result, iterable);
            result.add(element);
            return result;
        }
    }

    /**
     * Returns a list containing all elements of the original collection and then all elements of the given [elements] array.
     */
    @NotNull
    public static <T> List<T> plus(@Nullable Collection<T> collection, @Nullable T[] elements) {
        if (elements == null || elements.length <= 0) return toList(collection);
        ArrayList<T> result = new ArrayList<>(count(collection) + elements.length);
        addAll(result, collection);
        addAll(result, elements);
        return result;
    }

    /**
     * Returns a list containing all elements of the original collection and then all elements of the given [elements] array.
     */
    @NotNull
    public static <T> List<T> plus(@Nullable Iterable<T> iterable, @Nullable T[] elements) {
        if (elements == null || elements.length <= 0) return toList(iterable);
        if (iterable instanceof Collection) {
            return plus(((Collection<T>) iterable), elements);
        } else {
            ArrayList<T> result = new ArrayList<>();
            addAll(result, iterable);
            addAll(result, elements);
            return result;
        }
    }

    /**
     * Returns a list containing all elements of the original collection and then all elements of the given [elements] collection.
     */
    @NotNull
    public static <T> List<T> plus(@Nullable Collection<T> collection, @Nullable Iterable<T> elements) {
        if (elements == null) return toList(collection);
        if (elements instanceof Collection) {
            ArrayList<T> result = new ArrayList<>(count(collection) + ((Collection) elements).size());
            addAll(result, collection);
            addAll(result, elements);
            return result;
        } else {
            ArrayList<T> result = new ArrayList<>();
            addAll(result, collection);
            addAll(result, elements);
            return result;
        }
    }

    /**
     * Returns a list containing all elements of the original collection and then all elements of the given [elements] collection.
     */
    @NotNull
    public static <T> List<T> plus(@Nullable Iterable<T> iterable, @Nullable Iterable<T> elements) {
        if (elements == null) return toList(iterable);
        if (iterable instanceof Collection) {
            return plus((Collection<T>) iterable, elements);
        } else {
            ArrayList<T> result = new ArrayList<>();
            addAll(result, iterable);
            addAll(result, elements);
            return result;
        }
    }

    /**
     * Returns a list containing all elements of the original collection and then the given [element].
     */
    @NotNull
    public static <T> List<T> plusElement(@Nullable Collection<T> collection, @Nullable T element) {
        return plus(collection, element);
    }

    /**
     * Returns a list containing all elements of the original collection and then the given [element].
     */
    @NotNull
    public static <T> List<T> plusElement(@Nullable Iterable<T> iterable, @Nullable T element) {
        return plus(iterable, element);
    }


    /* ******************************************* indices ******************************************* */


    /**
     * Returns the range of valid indices for the Collection.
     */
    @NotNull
    public static <T> IntRange indices(@Nullable Collection<T> collection) {
        return new IntRange(0, count(collection) - 1);
    }


    /* ******************************************* group ******************************************* */


    /**
     * Groups elements of the original collection by the key returned by the given [keySelector] function
     * applied to each element and puts to the [destination] map each group key associated with a list of corresponding elements.
     *
     * @return The [destination] map.
     */
    @NotNull
    public static <T, K, M extends Map<K, List<T>>> M groupByTo(@Nullable Iterable<T> iterable, @NotNull M destination, @NotNull Transformer<T, K> keySelector) {
        if (iterable != null) {
            DefaultValue<List<T>> defaultValue = new DefaultValue<List<T>>() {
                @NotNull
                @Override
                public List<T> get() {
                    return new ArrayList<>();
                }
            };
            for (T element : iterable) {
                K key = keySelector.transform(element);
                List<T> list = Mapx.getOrPut(destination, key, defaultValue);
                list.add(element);
            }
        }
        return destination;
    }

    /**
     * Groups values returned by the [valueTransform] function applied to each element of the original collection
     * by the key returned by the given [keySelector] function applied to the element
     * and puts to the [destination] map each group key associated with a list of corresponding values.
     *
     * @return The [destination] map.
     */
    @NotNull
    public static <T, K, V, M extends Map<K, List<V>>> M groupByTo(@Nullable Iterable<T> iterable, @NotNull M destination, @NotNull Transformer<T, K> keySelector, @NotNull Transformer<T, V> valueTransform) {
        if (iterable != null) {
            DefaultValue<List<V>> defaultValue = new DefaultValue<List<V>>() {
                @NotNull
                @Override
                public List<V> get() {
                    return new ArrayList<>();
                }
            };
            for (T element : iterable) {
                K key = keySelector.transform(element);
                List<V> list = Mapx.getOrPut(destination, key, defaultValue);
                list.add(valueTransform.transform(element));
            }
        }
        return destination;
    }

    /**
     * Groups elements of the original collection by the key returned by the given [keySelector] function
     * applied to each element and returns a map where each group key is associated with a list of corresponding elements.
     * <p>
     * The returned map preserves the entry iteration order of the keys produced from the original collection.
     */
    @NotNull
    public static <T, K> Map<K, List<T>> groupBy(@Nullable Iterable<T> iterable, @NotNull Transformer<T, K> keySelector) {
        return groupByTo(iterable, new LinkedHashMap<K, List<T>>(), keySelector);
    }

    /**
     * Groups values returned by the [valueTransform] function applied to each element of the original collection
     * by the key returned by the given [keySelector] function applied to the element
     * and returns a map where each group key is associated with a list of corresponding values.
     * <p>
     * The returned map preserves the entry iteration order of the keys produced from the original collection.
     */
    @NotNull
    public static <T, K, V> Map<K, List<V>> groupBy(@Nullable Iterable<T> iterable, @NotNull Transformer<T, K> keySelector, @NotNull Transformer<T, V> valueTransform) {
        return groupByTo(iterable, new LinkedHashMap<K, List<V>>(), keySelector, valueTransform);
    }


    /* ******************************************* sort ******************************************* */


    /**
     * Sorts elements in the list in-place according to their natural sort order.
     */
    public static <T extends Comparable<T>> void sort(@Nullable List<T> list) {
        if (list != null && list.size() > 1) {
            Collections.sort(list);
        }
    }

    /**
     * Sorts elements in the list in-place according to the order specified with [comparator].
     */
    public static <T> void sortWith(@Nullable List<T> list, @NotNull Comparator<T> comparator) {
        if (list != null && list.size() > 1) {
            Collections.sort(list, comparator);
        }
    }

    /**
     * Sorts elements in the list in-place according to natural sort order of the value returned by specified [selector] function.
     */
    public static <T, R extends Comparable<R>> void sortBy(@Nullable List<T> list, @NotNull final NullableTransformer<T, R> transformer) {
        if (list != null && list.size() > 1) {
            sortWith(list, new Comparator<T>() {
                @Override
                public int compare(@Nullable T o1, @Nullable T o2) {
                    R r1 = o1 != null ? transformer.transform(o1) : null;
                    R r2 = o2 != null ? transformer.transform(o2) : null;
                    return r1 == r2 ? 0 : (r1 == null ? -1 : (r2 == null ? 1 : (r1.compareTo(r2))));
                }
            });
        }
    }

    /**
     * Sorts elements in the list in-place descending according to natural sort order of the value returned by specified [selector] function.
     */
    public static <T, R extends Comparable<R>> void sortByDescending(@Nullable List<T> list, @NotNull final NullableTransformer<T, R> transformer) {
        if (list != null && list.size() > 1) {
            sortWith(list, new Comparator<T>() {
                @Override
                public int compare(@Nullable T o1, @Nullable T o2) {
                    R r1 = o2 != null ? transformer.transform(o2) : null;
                    R r2 = o1 != null ? transformer.transform(o1) : null;
                    return r1 == r2 ? 0 : (r1 == null ? -1 : (r2 == null ? 1 : (r1.compareTo(r2))));
                }
            });
        }
    }

    /**
     * Sorts elements in the list in-place descending according to their natural sort order.
     */
    public static <T extends Comparable<T>> void sortDescending(@Nullable List<T> list) {
        sortWith(list, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o2.compareTo(o1);
            }
        });
    }

    /**
     * Returns a list of all elements sorted according to the specified [comparator].
     */
    @NotNull
    public static <T> List<T> sortedWith(@Nullable Iterable<T> iterable, @NotNull Comparator<T> comparator) {
        if (iterable instanceof Collection) {
            Collection<T> collection = (Collection<T>) iterable;
            if (collection.size() <= 1) {
                return toList(iterable);
            } else {
                List<T> newList = toList(collection);
                sortWith(newList, comparator);
                return newList;
            }
        } else {
            List<T> result = toList(iterable);
            sortWith(result, comparator);
            return result;
        }
    }

    /**
     * Returns a list of all elements sorted according to natural sort order of the value returned by specified [selector] function.
     */
    @NotNull
    public static <T, R extends Comparable<R>> List<T> sortedBy(@Nullable Iterable<T> iterable, @NotNull final NullableTransformer<T, R> transformer) {
        return sortedWith(iterable, new Comparator<T>() {
            @Override
            public int compare(@Nullable T o1, @Nullable T o2) {
                R r1 = o1 != null ? transformer.transform(o1) : null;
                R r2 = o2 != null ? transformer.transform(o2) : null;
                return r1 == r2 ? 0 : (r1 == null ? -1 : (r2 == null ? 1 : (r1.compareTo(r2))));
            }
        });
    }

    /**
     * Returns a list of all elements sorted according to their natural sort order.
     */
    @NotNull
    public static <T extends Comparable<T>> List<T> sorted(@Nullable Iterable<T> iterable) {
        if (iterable instanceof Collection) {
            Collection<T> collection = (Collection<T>) iterable;
            if (collection.size() <= 1) {
                return toList(iterable);
            } else {
                List<T> newList = toList(collection);
                sort(newList);
                return newList;
            }
        } else {
            List<T> result = toList(iterable);
            sort(result);
            return result;
        }
    }

    /**
     * Returns a list of all elements sorted descending according to natural sort order of the value returned by specified [selector] function.
     */
    @NotNull
    public static <T, R extends Comparable<R>> List<T> sortedByDescending(@Nullable Iterable<T> iterable, @NotNull final NullableTransformer<T, R> transformer) {
        return sortedWith(iterable, new Comparator<T>() {
            @Override
            public int compare(@Nullable T o1, @Nullable T o2) {
                R r1 = o2 != null ? transformer.transform(o2) : null;
                R r2 = o1 != null ? transformer.transform(o1) : null;
                return r1 == r2 ? 0 : (r1 == null ? -1 : (r2 == null ? 1 : (r1.compareTo(r2))));
            }
        });
    }

    /**
     * Returns a list of all elements sorted descending according to their natural sort order.
     */
    @NotNull
    public static <T extends Comparable<T>> List<T> sortedDescending(@Nullable Iterable<T> iterable) {
        return sortedWith(iterable, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o2.compareTo(o1);
            }
        });
    }


    /* ******************************************* reverse ******************************************* */


    /**
     * Reverses elements in the list in-place.
     */
    public static <T> void reverse(@Nullable List<T> list) {
        if (list != null) {
            Collections.reverse(list);
        }
    }

    /**
     * Returns a list with elements in reversed order.
     */
    @NotNull
    public static <T> List<T> reversed(@Nullable Iterable<T> iterable) {
        if (iterable instanceof Collection && ((Collection) iterable).size() <= 1) {
            return toList(iterable);
        } else {
            List<T> list = toList(iterable);
            reverse(list);
            return list;
        }
    }


    /* ******************************************* index ******************************************* */


    /**
     * Returns first index of [element], or -1 if the collection does not contain element.
     */
    public static <T> int indexOf(@Nullable Iterable<T> iterable, @Nullable T element) {
        if (iterable instanceof List) {
            return ((List) iterable).indexOf(element);
        } else {
            if (iterable != null) {
                int index = 0;
                for (T item : iterable) {
                    if (element != null ? element.equals(item) : null == item) {
                        return index;
                    }
                    index++;
                }
            }
            return -1;
        }
    }

    /**
     * Returns first index of [element], or -1 if the list does not contain element.
     */
    public static <T> int indexOf(@Nullable List<T> list, @Nullable T element) {
        return list != null ? list.indexOf(element) : -1;
    }

    /**
     * Returns index of the first element matching the given [predicate], or -1 if the collection does not contain such element.
     */
    public static <T> int indexOfFirst(@Nullable Iterable<T> iterable, @NotNull Transformer<T, Boolean> predicate) {
        if (iterable != null) {
            int index = 0;
            for (T item : iterable) {
                if (predicate.transform(item)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    /**
     * Returns index of the first element matching the given [predicate], or -1 if the list does not contain such element.
     */
    public static <T> int indexOfFirst(@Nullable List<T> list, @NotNull Transformer<T, Boolean> predicate) {
        if (list != null) {
            int index = 0;
            for (T item : list) {
                if (predicate.transform(item)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    /**
     * Returns index of the last element matching the given [predicate], or -1 if the collection does not contain such element.
     */
    public static <T> int indexOfLast(@Nullable Iterable<T> iterable, @NotNull Transformer<T, Boolean> predicate) {
        int lastIndex = -1;
        if (iterable != null) {
            int index = 0;
            for (T item : iterable) {
                if (predicate.transform(item)) {
                    lastIndex = index;
                }
                index++;
            }
        }
        return lastIndex;
    }

    /**
     * Returns index of the last element matching the given [predicate], or -1 if the list does not contain such element.
     */
    public static <T> int indexOfLast(@Nullable List<T> list, @NotNull Transformer<T, Boolean> predicate) {
        ListIterator<T> iterator = list != null ? list.listIterator(count(list)) : null;
        if (iterator != null) {
            while (iterator.hasPrevious()) {
                if (predicate.transform(iterator.previous())) {
                    return iterator.nextIndex();
                }
            }
        }
        return -1;
    }

    /**
     * Returns last index of [element], or -1 if the collection does not contain element.
     */
    public static <T> int lastIndexOf(@Nullable Iterable<T> iterable, @Nullable T element) {
        if (iterable instanceof List) {
            return ((List) iterable).lastIndexOf(element);
        } else {
            int lastIndex = -1;
            if (iterable != null) {
                int index = 0;
                for (T item : iterable) {
                    if (element != null ? element.equals(item) : null == item) {
                        lastIndex = index;
                    }
                    index++;
                }
            }
            return lastIndex;
        }
    }

    /**
     * Returns last index of [element], or -1 if the list does not contain element.
     */
    public static <T> int lastIndexOf(@Nullable List<T> list, @Nullable T element) {
        return list != null ? list.lastIndexOf(element) : -1;
    }


    /* ******************************************* fold ******************************************* */


    /**
     * Accumulates value starting with [initial] value and applying [operation] from left to right to current accumulator value and each element.
     */
    @NotNull
    public static <T, R> R fold(@Nullable Iterable<T> iterable, @NotNull R initial, @NotNull Operation<T, R> operation) {
        R accumulator = initial;
        if (iterable != null) {
            for (T element : iterable) {
                accumulator = operation.operation(accumulator, element);
            }
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from left to right
     * to current accumulator value and each element with its index in the original collection.
     *
     * @param operation function that takes the index of an element, current accumulator value
     *                  and the element itself, and calculates the next accumulator value.
     */
    @NotNull
    public static <T, R> R foldIndexed(@Nullable Iterable<T> iterable, @NotNull R initial, @NotNull IndexedOperation<T, R> operation) {
        R accumulator = initial;
        if (iterable != null) {
            int index = 0;
            for (T element : iterable) {
                accumulator = operation.operation(index++, accumulator, element);
            }
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from right to left to each element and current accumulator value.
     */
    @NotNull
    public static <T, R> R foldRight(@Nullable List<T> list, @NotNull R initial, @NotNull RightOperation<T, R> operation) {
        R accumulator = initial;
        if (list != null && !list.isEmpty()) {
            ListIterator<T> iterator = list.listIterator(list.size());
            while (iterator.hasPrevious()) {
                accumulator = operation.operation(iterator.previous(), accumulator);
            }
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from right to left
     * to each element with its index in the original list and current accumulator value.
     *
     * @param operation function that takes the index of an element, the element itself
     *                  and current accumulator value, and calculates the next accumulator value.
     */
    @NotNull
    public static <T, R> R foldRightIndexed(@Nullable List<T> list, @NotNull R initial, @NotNull IndexedRightOperation<T, R> operation) {
        R accumulator = initial;
        if (list != null && !list.isEmpty()) {
            ListIterator<T> iterator = list.listIterator(list.size());
            while (iterator.hasPrevious()) {
                int index = iterator.previousIndex();
                accumulator = operation.operation(index, iterator.previous(), accumulator);
            }
        }
        return accumulator;
    }


    /* ******************************************* reduce ******************************************* */


    /**
     * Accumulates value starting with the first element and applying [operation] from left to right to current accumulator value and each element.
     */
    @NotNull
    public static <S, T extends S> S reduce(@Nullable Iterable<T> iterable, @NotNull Operation<T, S> operation) {
        Iterator<T> iterator = iterable != null ? iterable.iterator() : null;
        if (iterator == null || !iterator.hasNext()) {
            throw new UnsupportedOperationException("Empty collection can't be reduced.");
        }
        S accumulator = iterator.next();
        while (iterator.hasNext()) {
            accumulator = operation.operation(accumulator, iterator.next());
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with the first element and applying [operation] from left to right
     * to current accumulator value and each element with its index in the original collection.
     *
     * @param operation function that takes the index of an element, current accumulator value
     *                  and the element itself and calculates the next accumulator value.
     */
    @NotNull
    public static <S, T extends S> S reduceIndexed(@Nullable Iterable<T> iterable, @NotNull IndexedOperation<T, S> operation) {
        Iterator<T> iterator = iterable != null ? iterable.iterator() : null;
        if (iterator == null || !iterator.hasNext()) {
            throw new UnsupportedOperationException("Empty collection can't be reduced.");
        }
        int index = 1;
        S accumulator = iterator.next();
        while (iterator.hasNext()) {
            accumulator = operation.operation(index++, accumulator, iterator.next());
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with last element and applying [operation] from right to left to each element and current accumulator value.
     */
    @NotNull
    public static <S, T extends S> S reduceRight(@Nullable List<T> list, @NotNull RightOperation<T, S> operation) {
        ListIterator<T> iterator = list != null ? list.listIterator(count(list)) : null;
        if (iterator == null || !iterator.hasPrevious()) {
            throw new UnsupportedOperationException("Empty list can't be reduced.");
        }
        S accumulator = iterator.previous();
        while (iterator.hasPrevious()) {
            accumulator = operation.operation(iterator.previous(), accumulator);
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with last element and applying [operation] from right to left
     * to each element with its index in the original list and current accumulator value.
     *
     * @param operation function that takes the index of an element, the element itself
     *                  and current accumulator value, and calculates the next accumulator value.
     */
    @NotNull
    public static <S, T extends S> S reduceRightIndexed(@Nullable List<T> list, @NotNull IndexedRightOperation<T, S> operation) {
        ListIterator<T> iterator = list != null ? list.listIterator(count(list)) : null;
        if (iterator == null || !iterator.hasPrevious()) {
            throw new UnsupportedOperationException("Empty list can't be reduced.");
        }
        S accumulator = iterator.previous();
        while (iterator.hasPrevious()) {
            int index = iterator.previousIndex();
            accumulator = operation.operation(index, iterator.previous(), accumulator);
        }
        return accumulator;
    }


    /* ******************************************* slice ******************************************* */


    /**
     * Returns a list containing elements at specified [indices].
     */
    @NotNull
    public static <T> List<T> slice(@Nullable List<T> list, @NotNull Iterable<Integer> indices) {
        int size = collectionSizeOrDefault(indices, 10);
        if (size == 0) {
            return Collectionx.arrayListOf();
        } else {
            ArrayList<T> resultList = new ArrayList<>(size);
            for (int index : indices) {
                if (list != null) {
                    resultList.add(list.get(index));
                }
            }
            return resultList;
        }
    }


    /* ******************************************* take ******************************************* */

    /**
     * Returns a list containing first [n] elements.
     */
    @NotNull
    public static <T> List<T> take(@Nullable Iterable<T> iterable, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        if (n == 0) {
            return Collectionx.arrayListOf();
        }
        if (iterable instanceof Collection) {
            if (n >= ((Collection<T>) iterable).size()) {
                return toList(iterable);
            }
            if (n == 1) {
                return mutableListOf(first(iterable));
            }
        }
        int count = 0;
        ArrayList<T> list = new ArrayList<>(n);
        if (iterable != null) {
            for (T item : iterable) {
                if (count++ == n)
                    break;
                list.add(item);
            }
        }
        return list;
    }

    /**
     * Returns a list containing last [n] elements.
     */
    public static <T> List<T> takeLast(@Nullable List<T> list, final int n) {
        if (isNullOrEmpty(list)) {
            return Collectionx.arrayListOf();
        }
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        if (n == 0) {
            return Collectionx.arrayListOf();
        }
        int size = count(list);
        if (n >= size) {
            return toList(list);
        }
        if (n == 1) {
            return mutableListOf(last(list));
        }
        ArrayList<T> resultList = new ArrayList<>(n);
        if (list instanceof RandomAccess) {
            for (int index = size - n; index < size; index++) {
                resultList.add(list.get(index));
            }
        } else {
            ListIterator<T> listIterator = list.listIterator(size - n);
            while (listIterator.hasNext()) {
                T item = listIterator.next();
                resultList.add(item);
            }
        }
        return resultList;
    }

    /**
     * Returns a list containing last elements satisfying the given [predicate].
     */
    @NotNull
    public static <T> List<T> takeLastWhile(@Nullable List<T> list, @NotNull Predicate<T> predicate) {
        if (isNullOrEmpty(list)) {
            return Collectionx.arrayListOf();
        }
        ListIterator<T> iterator = list.listIterator(list.size());
        while (iterator.hasPrevious()) {
            if (!predicate.accept(iterator.previous())) {
                iterator.next();
                int expectedSize = list.size() - iterator.nextIndex();
                if (expectedSize == 0) return Collectionx.arrayListOf();
                ArrayList<T> resultList = new ArrayList<>(expectedSize);
                while (iterator.hasNext()) {
                    resultList.add(iterator.next());
                }
                return resultList;
            }
        }
        return toList(list);
    }

    /**
     * Returns a list containing first elements satisfying the given [predicate].
     */
    @NotNull
    public static <T> List<T> takeWhile(@Nullable Iterable<T> iterable, @NotNull Predicate<T> predicate) {
        ArrayList<T> list = new ArrayList<>();
        if (iterable != null) {
            for (T item : iterable) {
                if (!predicate.accept(item))
                    break;
                list.add(item);
            }
        }
        return list;
    }


    /* ******************************************* distinct ******************************************* */


    /**
     * Returns a list containing only distinct elements from the given collection.
     * <p>
     * The elements in the resulting list are in the same order as they were in the source collection.
     */
    @NotNull
    public static <T> List<T> distinct(@Nullable Iterable<T> iterable) {
        return toList(toSet(iterable));
    }

    /**
     * Returns a list containing only elements from the given collection
     * having distinct keys returned by the given [selector] function.
     * <p>
     * The elements in the resulting list are in the same order as they were in the source collection.
     */
    @NotNull
    public static <T, K> List<T> distinctBy(@Nullable Iterable<T> iterable, @NotNull Transformer<T, K> transformer) {
        HashSet<K> set = new HashSet<>();
        ArrayList<T> list = new ArrayList<>();
        if (iterable != null) {
            for (T e : iterable) {
                K key = transformer.transform(e);
                if (set.add(key)) {
                    list.add(e);
                }
            }
        }
        return list;
    }


    /* ******************************************* intersect ******************************************* */


    /**
     * Returns a set containing all elements that are contained by both this set and the specified collection.
     * <p>
     * The returned set preserves the element iteration order of the original collection.
     */
    @NotNull
    public static <T> Set<T> intersect(@Nullable Iterable<T> iterable, @Nullable Iterable<T> other) {
        Set<T> set = toSet(iterable);
        if (other != null) {
            retainAll(set, other);
        }
        return set;
    }


    /* ******************************************* retain ******************************************* */


    private static boolean retainNothing(@Nullable Collection<?> collection) {
        boolean result = isNotNullOrEmpty(collection);
        if (collection != null) {
            collection.clear();
        }
        return result;
    }

    /**
     * Retains only elements of this [Collection] that are contained in the given [elements] array.
     */
    public static <T> boolean retainAll(@Nullable Collection<T> collection, @NotNull T[] elements) {
        if (Arrayx.isNotNullOrEmpty(elements)) {
            return collection != null && collection.retainAll(Arrayx.toHashSet(elements));
        } else {
            return retainNothing(collection);
        }
    }

    /**
     * Retains only elements of this [Collection] that are contained in the given [elements] collection.
     */
    public static <T> boolean retainAll(@Nullable Collection<T> collection, @NotNull Iterable<T> elements) {
        return collection != null && collection.retainAll(convertToSetForSetOperationWith(elements, collection));
    }

    /**
     * Retains only the elements in this collection that are contained in the specified collection.
     * <p>
     * Allows to overcome type-safety restriction of `retainAll` that requires to pass a collection of type `Collection[E]`.
     *
     * @return `true` if any element was removed from the collection, `false` if the collection was not modified.
     */
    public static <T> boolean retainAll(@Nullable Collection<T> collection, @NotNull Collection<T> elements) {
        return collection != null && collection.retainAll(elements);
    }

    /**
     * Retains only elements of this [ Iterable] that match the given [predicate].
     */
    public static <T> boolean retainAll(@Nullable Iterable<T> iterable, @NotNull Predicate<T> predicate) {
        return filterInPlace(iterable, predicate, false);
    }

    /**
     * Retains only elements of this [MutableList] that match the given [predicate].
     */
    public static <T> boolean retainAll(@Nullable List<T> list, @NotNull Predicate<T> predicate) {
        return filterInPlace(list, predicate, false);
    }


    /* ******************************************* associate ******************************************* */


    /**
     * Populates and returns the [destination] mutable map with key-value pairs
     * provided by [transform] function applied to each element of the given collection.
     * If any of two pairs would have the same key the last one gets added to the map.
     */
    @NotNull
    public static <T, K, V, M extends Map<K, V>> M associateTo(@Nullable Iterable<T> iterable, @NotNull M destination, @NotNull Transformer<T, Pair<K, V>> transform) {
        if (iterable != null) {
            for (T element : iterable) {
                Pair<K, V> pair = transform.transform(element);
                destination.put(pair.first, pair.second);
            }
        }
        return destination;
    }

    /**
     * Returns a [Map] containing key-value pairs provided by [transform] function
     * applied to elements of the given collection.
     * <p>
     * If any of two pairs would have the same key the last one gets added to the map.
     * <p>
     * The returned map preserves the entry iteration order of the original collection.
     */
    @NotNull
    public static <T, K, V> Map<K, V> associate(@Nullable Iterable<T> iterable, @NotNull Transformer<T, Pair<K, V>> transform) {
        int capacity = Math.max(Mapx.capacity(collectionSizeOrDefault(iterable, 10)), 16);
        return associateTo(iterable, new LinkedHashMap<K, V>(capacity), transform);
    }

    /**
     * Populates and returns the [destination] mutable map with key-value pairs,
     * where key is provided by the [keySelector] function and
     * and value is provided by the [valueTransform] function applied to elements of the given collection.
     * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
     */
    @NotNull
    public static <T, K, V, M extends Map<K, V>> M associateByTo(@Nullable Iterable<T> iterable, @NotNull M destination, @NotNull Transformer<T, K> keySelector, @NotNull Transformer<T, V> valueTransform) {
        if (iterable != null) {
            for (T element : iterable) {
                destination.put(keySelector.transform(element), valueTransform.transform(element));
            }
        }
        return destination;
    }

    /**
     * Returns a [Map] containing the values provided by [valueTransform] and indexed by [keySelector] functions applied to elements of the given collection.
     * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
     * The returned map preserves the entry iteration order of the original collection.
     */
    @NotNull
    public static <T, K, V> Map<K, V> associateBy(@Nullable Iterable<T> iterable, @NotNull Transformer<T, K> keySelector, @NotNull Transformer<T, V> valueTransform) {
        int capacity = Math.max(Mapx.capacity(collectionSizeOrDefault(iterable, 10)), 16);
        return associateByTo(iterable, new LinkedHashMap<K, V>(capacity), keySelector, valueTransform);
    }

    /**
     * Populates and returns the [destination] mutable map with key-value pairs,
     * where key is provided by the [keySelector] function applied to each element of the given collection
     * and value is the element itself.
     * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
     */
    @NotNull
    public static <T, K, M extends Map<K, T>> M associateByTo(@Nullable Iterable<T> iterable, @NotNull M destination, @NotNull Transformer<T, K> keySelector) {
        if (iterable != null) {
            for (T element : iterable) {
                destination.put(keySelector.transform(element), element);
            }
        }
        return destination;
    }

    /**
     * Returns a [Map] containing the elements from the given collection indexed by the key
     * returned from [keySelector] function applied to each element.
     * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
     * The returned map preserves the entry iteration order of the original collection.
     */
    @NotNull
    public static <T, K> Map<K, T> associateBy(@Nullable Iterable<T> iterable, @NotNull Transformer<T, K> keySelector) {
        int capacity = Math.max(Mapx.capacity(collectionSizeOrDefault(iterable, 10)), 16);
        return associateByTo(iterable, new LinkedHashMap<K, T>(capacity), keySelector);
    }


    /* ******************************************* drop ******************************************* */


    /**
     * Returns a list containing all elements except first [n] elements.
     */
    @NotNull
    public static <T> List<T> drop(@Nullable Iterable<T> iterable, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        if (n == 0) return toList(iterable);

        ArrayList<T> list;
        if (iterable instanceof Collection) {
            Collection<T> collection = (Collection<T>) iterable;
            int resultSize = collection.size() - n;
            if (resultSize <= 0) {
                return Collectionx.arrayListOf();
            }
            if (resultSize == 1) {
                return mutableListOf(last(collection));
            }

            list = new ArrayList<>(resultSize);
            if (iterable instanceof List) {
                List<T> list1 = (List<T>) iterable;
                if (iterable instanceof RandomAccess) {
                    for (int index = n, size = collection.size(); index < size; index++) {
                        list.add(list1.get(index));
                    }
                } else {
                    ListIterator<T> listIterator = list1.listIterator(n);
                    while (listIterator.hasNext()) {
                        T item = listIterator.next();
                        list.add(item);
                    }
                }
                return list;
            }
        } else {
            list = new ArrayList<>();
        }

        int count = 0;
        if (iterable != null) {
            for (T item : iterable) {
                if (count++ >= n) {
                    list.add(item);
                }
            }
        }
        return list;
    }

    /**
     * Returns a list containing all elements except last [n] elements.
     */
    @NotNull
    public static <T> List<T> dropLast(@Nullable List<T> list, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        return take(list, Math.max(count(list) - n, 0));
    }

    /**
     * Returns a list containing all elements except last elements that satisfy the given [predicate].
     */
    @NotNull
    public static <T> List<T> dropLastWhile(@Nullable List<T> list, @NotNull Predicate<T> predicate) {
        if (!isNullOrEmpty(list)) {
            ListIterator<T> iterator = list.listIterator(list.size());
            while (iterator.hasPrevious()) {
                if (!predicate.accept(iterator.previous())) {
                    return take(list, iterator.nextIndex() + 1);
                }
            }
        }
        return Collectionx.arrayListOf();
    }

    /**
     * Returns a list containing all elements except first elements that satisfy the given [predicate].
     */
    @NotNull
    public static <T> List<T> dropWhile(@Nullable Iterable<T> iterable, @NotNull Predicate<T> predicate) {
        boolean yielding = false;
        ArrayList<T> list = new ArrayList<>();
        if (iterable != null) {
            for (T item : iterable)
                if (yielding) {
                    list.add(item);
                } else if (!predicate.accept(item)) {
                    list.add(item);
                    yielding = true;
                }
        }
        return list;
    }


    /* ******************************************* single ******************************************* */


    /**
     * Returns the single element, or throws an exception if the list is empty or has more than one element.
     */
    @NotNull
    public static <T> T single(@Nullable List<T> list) {
        if (list == null || list.size() == 0) {
            throw new NoSuchElementException("List is empty.");
        } else if (list.size() == 1) {
            return list.get(0);
        } else {
            throw new IllegalArgumentException("List has more than one element.");
        }
    }

    /**
     * Returns the single element, or throws an exception if the collection is empty or has more than one element.
     */
    @NotNull
    public static <T> T single(@Nullable Iterable<T> iterable) {
        if (iterable instanceof List) {
            return single((List<T>) iterable);
        } else {
            Iterator<T> iterator = iterable != null ? iterable.iterator() : null;
            if (iterator == null || !iterator.hasNext()) {
                throw new NoSuchElementException("Collection is empty.");
            }
            T single = iterator.next();
            if (iterator.hasNext()) {
                throw new IllegalArgumentException("Collection has more than one element.");
            } else {
                return single;
            }
        }
    }

    /**
     * Returns the single element matching the given [predicate], or throws exception if there is no or more than one matching element.
     */
    @NotNull
    public static <T> T single(@Nullable Iterable<T> iterable, @NotNull Predicate<T> predicate) {
        T single = null;
        boolean found = false;
        if (iterable != null) {
            for (T element : iterable) {
                if (predicate.accept(element)) {
                    if (found) {
                        throw new IllegalArgumentException("Collection contains more than one matching element.");
                    }
                    single = element;
                    found = true;
                }
            }
        }
        if (!found) {
            throw new NoSuchElementException("Collection contains no element matching the predicate.");
        }
        return single;
    }

    /**
     * Returns single element, or `null` if the list is empty or has more than one element.
     */
    @Nullable
    public static <T> T singleOrNull(@Nullable List<T> list) {
        return list != null && list.size() == 1 ? list.get(0) : null;
    }

    /**
     * Returns single element, or `null` if the collection is empty or has more than one element.
     */
    @Nullable
    public static <T> T singleOrNull(@Nullable Iterable<T> iterable) {
        if (iterable instanceof List) {
            return singleOrNull((List<T>) iterable);
        } else {
            Iterator<T> iterator = iterable != null ? iterable.iterator() : null;
            if (iterator == null || !iterator.hasNext()) {
                return null;
            }
            T single = iterator.next();
            if (iterator.hasNext()) {
                return null;
            } else {
                return single;
            }
        }
    }

    /**
     * Returns the single element matching the given [predicate], or `null` if element was not found or more than one element was found.
     */
    @Nullable
    public static <T> T singleOrNull(@Nullable Iterable<T> iterable, @NotNull Predicate<T> predicate) {
        T single = null;
        boolean found = false;
        if (iterable != null) {
            for (T element : iterable) {
                if (predicate.accept(element)) {
                    if (found) {
                        return null;
                    }
                    single = element;
                    found = true;
                }
            }
        }
        if (!found) {
            return null;
        }
        return single;
    }


    /* ******************************************* element ******************************************* */


    /**
     * Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this collection.
     */
    @NotNull
    public static <T> T elementAtOrElse(@Nullable Iterable<T> iterable, int index, @NotNull Transformer<Integer, T> defaultValue) {
        if (iterable instanceof List) {
            return getOrElse((List<T>) iterable, index, defaultValue);
        } else {
            if (index < 0) {
                return defaultValue.transform(index);
            }
            Iterator<T> iterator = iterable != null ? iterable.iterator() : null;
            if (iterator != null) {
                int count = 0;
                while (iterator.hasNext()) {
                    T element = iterator.next();
                    if (index == count++) {
                        return element;
                    }
                }
            }
            return defaultValue.transform(index);
        }
    }

    /**
     * Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this list.
     */
    @NotNull
    public static <T> T elementAtOrElse(@Nullable List<T> list, int index, @NotNull Transformer<Integer, T> defaultValue) {
        return list != null && index >= 0 && index <= list.size() - 1 ? list.get(index) : defaultValue.transform(index);
    }

    /**
     * Returns an element at the given [index] or throws an [IndexOutOfBoundsException] if the [index] is out of bounds of this collection.
     */
    @NotNull
    public static <T> T elementAt(@Nullable Iterable<T> iterable, final int index) {
        if (iterable instanceof List) {
            return ((List<T>) iterable).get(index);
        } else {
            return elementAtOrElse(iterable, index, new Transformer<Integer, T>() {
                @NotNull
                @Override
                public T transform(@NotNull Integer integer) {
                    throw new IndexOutOfBoundsException("Collection doesn't contain element at index " + index + ".");
                }
            });
        }
    }

    /**
     * Returns an element at the given [index] or throws an [IndexOutOfBoundsException] if the [index] is out of bounds of this list.
     */
    @NotNull
    public static <T> T elementAt(@Nullable List<T> list, int index) {
        if (isNullOrEmpty(list)) {
            throw new IndexOutOfBoundsException("Collection doesn't contain element at index " + index + ".");
        }
        return list.get(index);
    }

    /**
     * Returns an element at the given [index] or `null` if the [index] is out of bounds of this collection.
     */
    @Nullable
    public static <T> T elementAtOrNull(@Nullable Iterable<T> iterable, int index) {
        if (iterable instanceof List) {
            return getOrNull((List<T>) iterable, index);
        } else {
            if (index < 0) {
                return null;
            }
            Iterator<T> iterator = iterable != null ? iterable.iterator() : null;
            if (iterator != null) {
                int count = 0;
                while (iterator.hasNext()) {
                    T element = iterator.next();
                    if (index == count++)
                        return element;
                }
            }
            return null;
        }
    }

    /**
     * Returns an element at the given [index] or `null` if the [index] is out of bounds of this list.
     */
    @Nullable
    public static <T> T elementAtOrNull(@Nullable List<T> list, int index) {
        return getOrNull(list, index);
    }


    /* ******************************************* fill ******************************************* */


    /**
     * Fills the list with the provided [value].
     * Each element in the list gets replaced with the [value].
     */
    public static <T> void fill(@Nullable List<T> list, @NotNull T value) {
        if (list != null) {
            Collections.fill(list, value);
        }
    }


    /* ******************************************* shuffle ******************************************* */


    /**
     * Randomly shuffles elements in this mutable list.
     */
    public static <T> void shuffle(@Nullable List<T> list) {
        if (list != null) {
            Collections.shuffle(list);
        }
    }

    /**
     * Randomly shuffles elements in this mutable list using the specified [random] instance as the source of randomness.
     */
    public static <T> void shuffle(@Nullable List<T> list, @NotNull Random random) {
        if (list != null) {
            Collections.shuffle(list, random);
        }
    }

    /**
     * Returns a new list with the elements of this list randomly shuffled.
     */
    @NotNull
    public static <T> List<T> shuffled(@Nullable Iterable<T> iterable) {
        List<T> result = toList(iterable);
        Collections.shuffle(result);
        return result;
    }

    /**
     * Returns a new list with the elements of this list randomly shuffled
     * using the specified [random] instance as the source of randomness.
     */
    @NotNull
    public static <T> List<T> shuffled(@Nullable Iterable<T> iterable, @NotNull Random random) {
        List<T> result = toList(iterable);
        Collections.shuffle(result, random);
        return result;
    }


    /* ******************************************* window ******************************************* */


///**
// * Returns a list of snapshots of the window of the given [size]
// * sliding along this collection with the given [step], where each
// * snapshot is a list.
// *
// * Several last lists may have less elements than the given [size].
// *
// * Both [size] and [step] must be positive and can be greater than the number of elements in this collection.
// * @param size the number of elements to take in each window
// * @param step the number of elements to move the window forward by on an each step, by default 1
// * @param partialWindows controls whether or not to keep partial windows in the end if any,
// * by default `false` which means partial windows won't be preserved
// *
// * @sample samples.collections.Sequences.Transformations.takeWindows
// */
//    @SinceKotlin("1.2")
//    public fun <T> Iterable<T>.windowed(size: Int, step: Int = 1, partialWindows: Boolean = false): List<List<T>> {
//        checkWindowSizeStep(size, step)
//        if (this is RandomAccess && this is List) {
//            val thisSize = this.size
//            val result = ArrayList<List<T>>((thisSize + step - 1) / step)
//            var index = 0
//            while (index < thisSize) {
//                val windowSize = size.coerceAtMost(thisSize - index)
//                if (windowSize < size && !partialWindows) break
//                result.add(List(windowSize) { this[it + index] })
//                index += step
//            }
//            return result
//        }
//        val result = ArrayList<List<T>>()
//        windowedIterator(iterator(), size, step, partialWindows, reuseBuffer = false).forEach {
//            result.add(it)
//        }
//        return result
//    }
//
///**
// * Returns a list of results of applying the given [transform] function to
// * an each list representing a view over the window of the given [size]
// * sliding along this collection with the given [step].
// *
// * Note that the list passed to the [transform] function is ephemeral and is valid only inside that function.
// * You should not store it or allow it to escape in some way, unless you made a snapshot of it.
// * Several last lists may have less elements than the given [size].
// *
// * Both [size] and [step] must be positive and can be greater than the number of elements in this collection.
// * @param size the number of elements to take in each window
// * @param step the number of elements to move the window forward by on an each step, by default 1
// * @param partialWindows controls whether or not to keep partial windows in the end if any,
// * by default `false` which means partial windows won't be preserved
// *
// * @sample samples.collections.Sequences.Transformations.averageWindows
// */
//    @SinceKotlin("1.2")
//    public fun <T, R> Iterable<T>.windowed(size: Int, step: Int = 1, partialWindows: Boolean = false, transform: (List<T>) -> R): List<R> {
//        checkWindowSizeStep(size, step)
//        if (this is RandomAccess && this is List) {
//            val thisSize = this.size
//            val result = ArrayList<R>((thisSize + step - 1) / step)
//            val window = MovingSubList(this)
//            var index = 0
//            while (index < thisSize) {
//                window.move(index, (index + size).coerceAtMost(thisSize))
//                if (!partialWindows && window.size < size) break
//                result.add(transform(window))
//                index += step
//            }
//            return result
//        }
//        val result = ArrayList<R>()
//        windowedIterator(iterator(), size, step, partialWindows, reuseBuffer = true).forEach {
//            result.add(transform(it))
//        }
//        return result
//    }


    /* ******************************************* zip ******************************************* */


    /**
     * Returns a list of pairs built from the elements of `this` collection and the [other] array with the same index.
     * The returned list has length of the shortest collection.
     */
    @NotNull
    public static <T, R> List<Pair<T, R>> zip(@NotNull Iterable<T> iterable, @NotNull R[] other) {
        return zip(iterable, other, new Transformer2<T, R, Pair<T, R>>() {
            @NotNull
            @Override
            public Pair<T, R> transform(@NotNull T t, @NotNull R r) {
                return Pair.of(t, r);
            }
        });
    }

    /**
     * Returns a list of values built from the elements of `this` collection and the [other] array with the same index
     * using the provided [transform] function applied to each pair of elements.
     * The returned list has length of the shortest collection.
     */
    @NotNull
    public static <T, R, V> List<V> zip(@NotNull Iterable<T> iterable, @NotNull R[] other, @NotNull Transformer2<T, R, V> transform) {
        int arraySize = other.length;
        List<V> list = new ArrayList<V>(Math.min(collectionSizeOrDefault(iterable, 10), arraySize));
        int i = 0;
        for (T element : iterable) {
            if (i >= arraySize) {
                break;
            } else {
                list.add(transform.transform(element, other[i++]));
            }
        }
        return list;
    }

    /**
     * Returns a list of pairs built from the elements of `this` collection and [other] collection with the same index.
     * The returned list has length of the shortest collection.
     */
    @NotNull
    public static <T, R> List<Pair<T, R>> zip(@NotNull Iterable<T> iterable, @NotNull Iterable<R> other) {
        return zip(iterable, other, new Transformer2<T, R, Pair<T, R>>() {
            @NotNull
            @Override
            public Pair<T, R> transform(@NotNull T t, @NotNull R r) {
                return Pair.of(t, r);
            }
        });
    }

    /**
     * Returns a list of values built from the elements of `this` collection and the [other] collection with the same index
     * using the provided [transform] function applied to each pair of elements.
     * The returned list has length of the shortest collection.
     */
    @NotNull
    public static <T, R, V> List<V> zip(@NotNull Iterable<T> iterable, Iterable<R> other, @NotNull Transformer2<T, R, V> transform) {
        Iterator<T> first = iterable.iterator();
        Iterator<R> second = other.iterator();
        List<V> list = new ArrayList<V>(Math.min(collectionSizeOrDefault(iterable, 10), collectionSizeOrDefault(other, 10)));
        while (first.hasNext() && second.hasNext()) {
            list.add(transform.transform(first.next(), second.next()));
        }
        return list;
    }

    /**
     * Returns a list of pairs of each two adjacent elements in this collection.
     * <p>
     * The returned list is empty if this collection contains less than two elements.
     */
    @NotNull
    public static <T> List<Pair<T, T>> zipWithNext(@NotNull Iterable<T> iterable) {
        return zipWithNext(iterable, new Transformer2<T, T, Pair<T, T>>() {
            @NotNull
            @Override
            public Pair<T, T> transform(@NotNull T t, @NotNull T t2) {
                return Pair.of(t, t2);
            }
        });
    }

    /**
     * Returns a list containing the results of applying the given [transform] function
     * to an each pair of two adjacent elements in this collection.
     * <p>
     * The returned list is empty if this collection contains less than two elements.
     */
    @NotNull
    public static <T, R> List<R> zipWithNext(@NotNull Iterable<T> iterable, @NotNull Transformer2<T, T, R> transform) {
        Iterator<T> iterator = iterable.iterator();
        if (!iterator.hasNext()) return Collectionx.mutableListOf();
        List<R> result = Collectionx.mutableListOf();
        T current = iterator.next();
        while (iterator.hasNext()) {
            T next = iterator.next();
            result.add(transform.transform(current, next));
            current = next;
        }
        return result;
    }


    /* ******************************************* iterator ******************************************* */


    /**
     * Creates an [Iterator] for an [java.util.Enumeration], allowing to use it in `for` loops.
     */
    public static <T> Iterator<T> iterator(@Nullable final Enumeration<T> enumeration) {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return enumeration != null && enumeration.hasMoreElements();
            }

            @Override
            public T next() {
                if (enumeration == null) throw new NoSuchElementException();
                return enumeration.nextElement();
            }

            @Override
            public void remove() {

            }
        };
    }


    /* ******************************************* toArray ******************************************* */

    @NotNull
    public static byte[] toByteArray(@Nullable Collection<Byte> collection) {
        byte[] result = new byte[count(collection)];
        if (collection != null) {
            int index = 0;
            for (Byte by : collection) {
                result[index++] = by;
            }
        }
        return result;
    }

    @NotNull
    public static short[] toShortArray(@Nullable Collection<Short> collection) {
        short[] result = new short[count(collection)];
        if (collection != null) {
            int index = 0;
            for (Short by : collection) {
                result[index++] = by;
            }
        }
        return result;
    }

    @NotNull
    public static int[] toIntArray(@Nullable Collection<Integer> collection) {
        int[] result = new int[count(collection)];
        if (collection != null) {
            int index = 0;
            for (Integer by : collection) {
                result[index++] = by;
            }
        }
        return result;
    }

    @NotNull
    public static long[] toLongArray(@Nullable Collection<Long> collection) {
        long[] result = new long[count(collection)];
        if (collection != null) {
            int index = 0;
            for (Long by : collection) {
                result[index++] = by;
            }
        }
        return result;
    }

    @NotNull
    public static float[] toFloatArray(@Nullable Collection<Float> collection) {
        float[] result = new float[count(collection)];
        if (collection != null) {
            int index = 0;
            for (Float by : collection) {
                result[index++] = by;
            }
        }
        return result;
    }

    @NotNull
    public static double[] toDoubleArray(@Nullable Collection<Double> collection) {
        double[] result = new double[count(collection)];
        if (collection != null) {
            int index = 0;
            for (Double by : collection) {
                result[index++] = by;
            }
        }
        return result;
    }

    @NotNull
    public static boolean[] toBooleanArray(@Nullable Collection<Boolean> collection) {
        boolean[] result = new boolean[count(collection)];
        if (collection != null) {
            int index = 0;
            for (Boolean by : collection) {
                result[index++] = by;
            }
        }
        return result;
    }

    @NotNull
    public static char[] toCharArray(@Nullable Collection<Character> collection) {
        char[] result = new char[count(collection)];
        if (collection != null) {
            int index = 0;
            for (Character by : collection) {
                result[index++] = by;
            }
        }
        return result;
    }
    
    static <T> void appendElement(@NotNull Appendable appendable, @NotNull T element, @Nullable Transformer<T, CharSequence> transform) {
        try {
            if (transform != null) {
                appendable.append(transform.transform(element));
            } else if (element instanceof CharSequence) {
                appendable.append((CharSequence) element);
            } else if (element instanceof Character) {
                appendable.append((Character) element);
            } else {
                appendable.append(element.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
