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

package com.github.panpf.tools4j.resources;

import com.github.panpf.tools4j.run.Runx;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;

public class GradleBuildResourcesDirCreator implements DevResourcesDirCreator {

    @Nullable
    @Override
    public File createDevResourceDir(@NotNull Class<?> targetClazz) {
        File targetClazzInDir = Runx.getClassInDir(targetClazz);
        String targetClazzInDirPath = targetClazzInDir != null ? targetClazzInDir.getPath() : "";

        // gradle project
        String gradleProjectFlag = "/build/classes/";
        int gradleProjectFlagIndex = targetClazzInDirPath.indexOf(gradleProjectFlag);
        if (gradleProjectFlagIndex >= 0) {
            String projectDirPath = targetClazzInDirPath.substring(0, gradleProjectFlagIndex);
            String buildType = targetClazzInDirPath.contains("/test") ? "test" : "main";
            return new File(projectDirPath, "build" + File.separator + "resources" + File.separator + buildType + File.separator);
        } else {
            return null;
        }
    }
}
