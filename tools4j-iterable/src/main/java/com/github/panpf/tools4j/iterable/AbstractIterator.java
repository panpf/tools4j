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

package com.github.panpf.tools4j.iterable;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A base class to simplify implementing iterators so that implementations only have to implement [computeNext]
 * to implement the iterator, calling [done] when the iteration is complete.
 */
public abstract class AbstractIterator<T> implements Iterator<T> {

    @NotNull
    private State state = State.NotReady;
    @Nullable
    private T nextValue = null;

    @Override
    public boolean hasNext() {
        if (state == State.Failed) {
            throw new IllegalStateException("State is Failed");
        }
        if (state == State.Done) {
            return false;
        } else if (state == State.Ready) {
            return true;
        } else {
            return tryToComputeNext();
        }
    }

    @Override
    @NotNull
    public T next() {
        if (!hasNext()) throw new NoSuchElementException();
        state = State.NotReady;
        if (nextValue == null) throw new NoSuchElementException();
        return nextValue;
    }

    private Boolean tryToComputeNext() {
        state = State.Failed;
        computeNext();
        return state == State.Ready;
    }

    /**
     * Computes the next item in the iterator.
     * <p>
     * This callback method should call one of these two methods:
     * <p>
     * * [setNext] with the next value of the iteration
     * * [done] to indicate there are no more elements
     * <p>
     * Failure to call either method will result in the iteration terminating with a failed state
     */
    protected abstract void computeNext();

    /**
     * Sets the next value in the iteration, called from the [computeNext] function
     */
    protected void setNext(T value) {
        nextValue = value;
        state = State.Ready;
    }

    /**
     * Sets the state to done so that the iteration terminates.
     */
    protected void done() {
        state = State.Done;
    }
}
