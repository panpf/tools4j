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

package com.github.panpf.tools4j.run

import org.junit.Assert
import org.junit.Test

class RunxTest {

    @Test
    fun testIsInJar() {
        Assert.assertFalse(Runx.isClassInJar(Runx::class.java))
        Assert.assertFalse(Runx.isClassInJar(RunxTest::class.java))
    }

    @Test
    fun testGetWorkspaceDir() {
        Assert.assertEquals("/Users/panpf/Workspace/tools4j/tools4j-run", Runx.getWorkspaceDir().path)
    }

    @Test
    fun testGetClassInDir() {
        Assert.assertEquals("/Users/panpf/Workspace/tools4j/tools4j-run/build/classes/kotlin/test/com/github/panpf/tools4j/run", Runx.getClassInDir(Runx::class.java)?.path)
        Assert.assertEquals("/Users/panpf/Workspace/tools4j/tools4j-run/build/classes/kotlin/test/com/github/panpf/tools4j/run", Runx.getClassInDir(RunxTest::class.java)?.path)
    }

    @Test
    fun testGetClassInJarFile() {
        Assert.assertNull(Runx.getClassInJarFile(Runx::class.java)?.path)
        Assert.assertNull(Runx.getClassInJarFile(RunxTest::class.java)?.path)
    }
}