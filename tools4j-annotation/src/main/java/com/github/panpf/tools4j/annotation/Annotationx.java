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

package com.github.panpf.tools4j.annotation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.annotation.Annotation;

/**
 * Annotation tool method
 */
public class Annotationx {

    private Annotationx() {
    }

    /**
     * Get an annotation of the specified type on an enumeration
     */
    @Nullable
    public static <T extends Annotation> T getAnnotationFromEnum(@NotNull Enum<?> enumObject, @NotNull Class<T> annotationClass) {
        try {
            return enumObject.getClass().getField(enumObject.name()).getAnnotation(annotationClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}