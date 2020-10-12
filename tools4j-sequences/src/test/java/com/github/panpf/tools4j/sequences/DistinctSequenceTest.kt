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

package com.github.panpf.tools4j.sequences

import org.junit.Assert
import org.junit.Test

class DistinctSequenceTest {

    @Test
    fun test() {
        val sequence = Sequencex.sequenceOf("4", "5", "4", "5", "3", "1", "4")
        val nullSequence1 = null as Sequence<String>?

        Assert.assertEquals("4, 5, 3, 1", Sequencex.joinToString(DistinctSequence(sequence) { it }))
        Assert.assertEquals("", Sequencex.joinToString(DistinctSequence(nullSequence1) { it }))
    }
}