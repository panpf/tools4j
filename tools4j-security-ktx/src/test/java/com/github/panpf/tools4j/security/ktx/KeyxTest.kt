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

package com.github.panpf.tools4j.security.ktx

import com.github.panpf.tools4j.base64.Base64x
import org.junit.Assert
import org.junit.Test

class KeyxTest {

    @Test
    fun testToBase64() {
        val seed = "" + System.currentTimeMillis()

        val key1 = seed.createAesKeyByPassword(16).toBase64()
        val key2 = seed.createAesKeyByPassword(16).toBase64()

        Assert.assertEquals("testToBase64", key1, key2)
    }

    @Test
    fun testToBytes() {
        val seed = "" + System.currentTimeMillis()

        val key1 = Base64x.encodeToString(seed.createAesKeyByPassword(16).toBytes(), Base64x.NO_WRAP)
        val key2 = Base64x.encodeToString(seed.createAesKeyByPassword(16).toBytes(), Base64x.NO_WRAP)

        Assert.assertEquals("testToBytes", key1, key2)
    }
}
