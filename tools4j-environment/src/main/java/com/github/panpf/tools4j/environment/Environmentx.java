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

package com.github.panpf.tools4j.environment;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.net.URL;

public class Environmentx {


    /**
     * Get the dir of the workspace
     */
    @NotNull
    public static File getWorkspaceDir() {
        return new File("").getAbsoluteFile();
    }


    /**
     * If it returns true, it means that the specified class is contained in the jar
     */
    public static boolean isClassInJar(@NotNull Class<?> clazz) {
        // resourceUrl example: 'jar:file:/Users/panpf/Workspace/tools4j/tools4j-environment/build/libs/tools4j-environment.jar!/com/github/panpf/tools4j/environment'
        // resourceUrl example: 'file:/Users/panpf/Workspace/tools4j/tools4j-environment/build/classes/kotlin/test/com/github/panpf/tools4j/environment/'
        URL resourceUrl = clazz.getResource("");
        return resourceUrl != null && "jar".equals(resourceUrl.getProtocol());
    }


    /**
     * If it returns true, it means that the specified class is contained in the jar
     */
    @Nullable
    public static File getClassInDir(@NotNull Class<?> clazz) {
        // resourceUrl example: 'jar:file:/Users/panpf/Workspace/tools4j/tools4j-environment/build/libs/tools4j-environment.jar!/com/github/panpf/tools4j/environment'
        // resourceUrl example: 'file:/Users/panpf/Workspace/tools4j/tools4j-environment/build/classes/kotlin/test/com/github/panpf/tools4j/environment/'
        URL resourceUrl = clazz.getResource("");
        if (resourceUrl != null && "file".equals(resourceUrl.getProtocol())) {
            return new File(resourceUrl.getPath());
        } else {
            return null;
        }
    }


    /**
     * Get the jar file where the specified class is located，example: '/Users/panpf/Workspace/tools4j/tools4j-environment/build/libs/tools4j-environment.jar'
     */
    @Nullable
    public static File getClassInJarFile(@NotNull Class<?> clazz) {
        // resourceUrl example: 'jar:file:/Users/panpf/Workspace/tools4j/tools4j-environment/build/libs/tools4j-environment.jar!/com/github/panpf/tools4j/environment'
        // resourceUrl example: 'file:/Users/panpf/Workspace/tools4j/tools4j-environment/build/classes/kotlin/test/com/github/panpf/tools4j/environment/'
        URL resourceUrl = clazz.getResource("");
        if (resourceUrl == null) {
            System.err.println("resourceUrl is null");
            return null;
        }

        // urlPath example: 'file:/Users/panpf/Workspace/tools4j/tools4j-environment/build/libs/tools4j-environment.jar!/com/github/panpf/tools4j/environment'
        String urlPath = resourceUrl.getPath();
        String startFlag = "file:";
        if (!"jar".equals(resourceUrl.getProtocol()) || !urlPath.startsWith(startFlag)) {
            System.err.println("resourceUrl does not start with 'jar:file:': " + resourceUrl);
            return null;
        }
        String endFlag = ".jar!";
        int jarFlagIndex = urlPath.indexOf(endFlag);
        if (jarFlagIndex == -1) {
            System.err.println("resourceUrl not included '" + endFlag + "': " + resourceUrl);
            return null;
        }

        String jarPath = urlPath.substring(startFlag.length(), jarFlagIndex + endFlag.length() - 1);
        return new File(jarPath);
    }

    // For testing only
//    public static void main(@Nullable String[] args) {
//        System.out.println("Runx.isInJar: " + Runx.isInJar(Runx.class));
//        System.out.println("Runx.getWorkspaceDir: " + Runx.getWorkspaceDir());
//        System.out.println("Runx.getClassInDir: " + Runx.getClassInDir(Runx.class));
//        System.out.println("Runx.getClassInJarFile: " + Runx.getClassInJarFile(Runx.class));
//    }
}
