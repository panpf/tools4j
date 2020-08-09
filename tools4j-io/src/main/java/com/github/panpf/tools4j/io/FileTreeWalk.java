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

package com.github.panpf.tools4j.io;

import com.github.panpf.tools4j.iterable.AbstractIterator;
import com.github.panpf.tools4j.common.Action;
import com.github.panpf.tools4j.common.LazyValue;
import com.github.panpf.tools4j.common.Premisex;
import com.github.panpf.tools4j.common.Transformer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;

/**
 * This class is intended to implement different file traversal methods.
 * It allows to iterate through all files inside a given directory.
 * <p>
 * Use [File.walk], [File.walkTopDown] or [File.walkBottomUp] getExtension functions to instantiate a `FileTreeWalk` instance.
 * <p>
 * If the file path given is just a file, walker iterates only it.
 * If the file path given does not exist, walker iterates nothing, i.e. it's equivalent to an empty sequence.
 */
public class FileTreeWalk implements Iterable<File> {

    @NotNull
    private final File start;
    @NotNull
    private final FileWalkDirection direction;
    @Nullable
    private Transformer<File, Boolean> onEnter;
    @Nullable
    private Action<File> onLeave;
    @Nullable
    private OnFailed onFail;
    private int maxDepth = Integer.MAX_VALUE;

    public FileTreeWalk(@NotNull File start, @NotNull FileWalkDirection direction, @Nullable Transformer<File, Boolean> onEnter, @Nullable Action<File> onLeave, @Nullable OnFailed onFail, int maxDepth) {
        this.start = start;
        this.direction = direction;
        this.onEnter = onEnter;
        this.onLeave = onLeave;
        this.onFail = onFail;
        this.maxDepth = maxDepth;
    }

    public FileTreeWalk(@NotNull File start, @NotNull FileWalkDirection direction) {
        this.start = start;
        this.direction = direction;
    }

    /**
     * Returns an iterator walking through files.
     */
    @NotNull
    @Override
    public Iterator<File> iterator() {
        return new FileTreeWalkIterator();
    }

    /**
     * Abstract class that encapsulates file visiting in some order, beginning from a given [root]
     */
    private abstract static class WalkState {
        @NotNull
        protected File root;

        public WalkState(@NotNull File root) {
            this.root = root;
        }

        /**
         * Call of this function proceeds to a next file for visiting and returns it
         */
        @Nullable
        public abstract File step();
    }

    /**
     * Abstract class that encapsulates directory visiting in some order, beginning from a given [rootDir]
     */
    private abstract static class DirectoryState extends WalkState {

        public DirectoryState(@NotNull File rootDir) {
            super(rootDir);
        }
    }

    private class FileTreeWalkIterator extends AbstractIterator<File> {

        // Stack of directory states, beginning from the start directory
        private final Stack<WalkState> state = new Stack<>();

        public FileTreeWalkIterator() {
            if (start.isDirectory()) {
                state.push(directoryState(start));
            } else if (start.isFile()) {
                state.push(new SingleFileState(start));
            } else {
                done();
            }
        }

        @Override
        public void computeNext() {
            File nextFile = gotoNext();
            if (nextFile != null)
                setNext(nextFile);
            else
                done();
        }


        private DirectoryState directoryState(@NotNull File root) {
            return direction == FileWalkDirection.TOP_DOWN ? new TopDownDirectoryState(root) : new BottomUpDirectoryState(root);
        }

        @Nullable
        private File gotoNext() {
            if (state.empty()) {
                // There is nothing in the state
                return null;
            }
            // Take next file from the top of the stack
            WalkState topState = state.peek();
            File file = topState.step();
            if (file == null) {
                // There is nothing more on the top of the stack, go back
                state.pop();
                return gotoNext();
            } else {
                // Check that file/directory matches the filter
                if (file == topState.root || !file.isDirectory() || state.size() >= maxDepth) {
                    // Proceed to a root directory or a simple file
                    return file;
                } else {
                    // Proceed to a sub-directory
                    state.push(directoryState(file));
                    return gotoNext();
                }
            }
        }

        @Override
        public void remove() {

        }

        /**
         * Visiting in bottom-up order
         */
        private class BottomUpDirectoryState extends DirectoryState {

            private boolean rootVisited = false;

            @Nullable
            private File[] fileList = null;

            private int fileIndex = 0;

            private boolean failed = false;

            BottomUpDirectoryState(@NotNull File rootDir) {
                super(rootDir);
            }

            /**
             * First all children, then root directory
             */
            @Override
            @Nullable
            public File step() {
                if (!failed && fileList == null) {
                    if (onEnter != null && !onEnter.transform(root)) {
                        return null;
                    }

                    fileList = root.listFiles();
                    if (fileList == null) {
                        if (onFail != null) {
                            try {
                                onFail.onError(root, new AccessDeniedException(root, null, "Cannot list files in a directory"));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        failed = true;
                    }
                }
                if (fileList != null && fileIndex < fileList.length) {
                    // First visit all files
                    return fileList[fileIndex++];
                } else if (!rootVisited) {
                    // Then visit root
                    rootVisited = true;
                    return root;
                } else {
                    // That's all
                    if (onLeave != null) {
                        onLeave.action(root);
                    }
                    return null;
                }
            }
        }

        /**
         * Visiting in top-down order
         */
        private class TopDownDirectoryState extends DirectoryState {

            private boolean rootVisited = false;

            @Nullable
            private File[] fileList = null;

            private int fileIndex = 0;

            TopDownDirectoryState(@NotNull File rootDir) {
                super(rootDir);
            }

            /**
             * First root directory, then all children
             */
            @Override
            @Nullable
            public File step() {
                if (!rootVisited) {
                    // First visit root
                    if (onEnter != null && !onEnter.transform(root)) {
                        return null;
                    }

                    rootVisited = true;
                    return root;
                } else if (fileList == null || fileIndex < fileList.length) {
                    if (fileList == null) {
                        // Then read an array of files, if any
                        fileList = root.listFiles();
                        if (fileList == null) {
                            if (onFail != null) {
                                try {
                                    onFail.onError(root, new AccessDeniedException(root, null, "Cannot list files in a directory"));
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                        if (fileList == null || fileList.length == 0) {
                            if (onLeave != null) {
                                onLeave.action(root);
                            }
                            return null;
                        }
                    }
                    // Then visit all files
                    return fileList[fileIndex++];
                } else {
                    // That's all
                    if (onLeave != null) {
                        onLeave.action(root);
                    }
                    return null;
                }
            }
        }

        private class SingleFileState extends WalkState {

            SingleFileState(@NotNull File rootFile) {
                super(rootFile);
                if (!rootFile.isFile()) {
                    throw new IllegalArgumentException(String.format("The file pointed to by the parameter '%s' is not a file: %s", rootFile, rootFile.getPath()));
                }
            }

            private boolean visited = false;

            @Override
            @Nullable
            public File step() {
                if (visited) return null;
                visited = true;
                return root;
            }
        }
    }

    /**
     * Sets a predicate [function], that is called on any entered directory before its files are visited
     * and before it is visited itself.
     * <p>
     * If the [function] returns `false` the directory is not entered and neither it nor its files are visited.
     */
    @NotNull
    public FileTreeWalk onEnter(@Nullable Transformer<File, Boolean> newOnEnter) {
        return new FileTreeWalk(start, direction, newOnEnter, onLeave, onFail, maxDepth);
    }

    /**
     * Sets a callback [function], that is called on any left directory after its files are visited and after it is visited itself.
     */
    @NotNull
    public FileTreeWalk onLeave(@Nullable Action<File> newOnLeave) {
        return new FileTreeWalk(start, direction, onEnter, newOnLeave, onFail, maxDepth);
    }

    /**
     * Set a callback [function], that is called on a directory when it's impossible to get its file list.
     * <p>
     * [onEnter] and [onLeave] callback functions are called even in this case.
     */
    @NotNull
    public FileTreeWalk onFail(@Nullable OnFailed newOnFail) {
        return new FileTreeWalk(start, direction, onEnter, onLeave, newOnFail, maxDepth);
    }

    /**
     * Sets the maximum [depth] of a directory tree to traverse. By default there is no limit.
     * <p>
     * The value must be positive and [Int.MAX_VALUE] is used to specify an unlimited depth.
     * <p>
     * With a value of 1, walker visits only the origin directory and all its immediate children,
     * with a value of 2 also grandchildren, etc.
     */
    @NotNull
    public FileTreeWalk maxDepth(final int newDepth) {
        Premisex.require(newDepth > 0, new LazyValue<String>() {
            @NotNull
            @Override
            public String get() {
                return String.format("depth must be positive, but was %d.", newDepth);
            }
        });
        return new FileTreeWalk(start, direction, onEnter, onLeave, onFail, newDepth);
    }
}
