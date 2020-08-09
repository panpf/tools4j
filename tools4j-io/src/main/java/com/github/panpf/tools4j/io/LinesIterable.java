package com.github.panpf.tools4j.io;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class LinesIterable implements Iterable<String> {
    @NotNull
    private final BufferedReader reader;

    public LinesIterable(@NotNull BufferedReader reader) {
        this.reader = reader;
    }

    @NotNull
    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {

            private String nextValue = null;
            private boolean done = false;

            @Override
            public boolean hasNext() {
                if (nextValue == null && !done) {
                    try {
                        nextValue = reader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new IllegalStateException("hasNextIOException", e);
                    }
                    if (nextValue == null) {
                        done = true;
                    }
                }
                return nextValue != null;
            }

            @Override
            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                String answer = nextValue;
                nextValue = null;
                return answer;
            }

            @Override
            public void remove() {

            }
        };
    }
}
