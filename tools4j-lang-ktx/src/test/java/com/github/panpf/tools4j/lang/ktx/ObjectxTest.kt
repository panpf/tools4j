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

package com.github.panpf.tools4j.lang.ktx

import org.junit.Assert.assertEquals
import org.junit.Test

class ObjectxTest {

    @Test
    fun testToSimpleString() {
        val `object` = ObjectxTest()
        assertEquals(`object`.javaClass.simpleName + "@" + `object`.hashCode().toHexString(), `object`.toSimpleString())
        assertEquals(`object`.javaClass.name + "@" + `object`.hashCode().toHexString(), `object`.toString())
    }
}
