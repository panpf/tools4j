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
package com.github.panpf.tools4j.premise

import org.junit.Assert
import org.junit.Test

class PremisexTest {

    @Test
    fun testRequire() {
        Premisex.require(true)
        try {
            Premisex.require(false)
            Assert.fail()
        } catch (e: Exception) {
            // e.printStackTrace();
        }
        try {
            Premisex.require(false) { "is false" }
            Assert.fail()
        } catch (e: Exception) {
            // e.printStackTrace();
        }
        try {
            Premisex.require(true) { "is true" }
        } catch (e: Exception) {
            // e.printStackTrace();
        }
    }

    @Test
    fun testCheck() {
        Premisex.check(true)
        try {
            Premisex.check(false)
            Assert.fail()
        } catch (e: Exception) {
            // e.printStackTrace();
        }
        try {
            Premisex.check(false) { "is false" }
            Assert.fail()
        } catch (e: Exception) {
            // e.printStackTrace();
        }
        try {
            Premisex.check(true) { "is true" }
        } catch (e: Exception) {
            // e.printStackTrace();
        }
    }

    @Test
    fun testRequireNotNull() {
        Premisex.requireNotNull("", { "is null" })
        try {
            Premisex.requireNotNull<Any>(null, { "is null" })
            Assert.fail()
        } catch (e: Exception) {
            // e.printStackTrace();
        }
        Premisex.requireNotNull("")
        try {
            Premisex.requireNotNull<Any>(null)
            Assert.fail()
        } catch (e: Exception) {
            // e.printStackTrace();
        }
        Premisex.requireNotNull("", "param")
        try {
            Premisex.requireNotNull<Any>(null, "param")
            Assert.fail()
        } catch (e: Exception) {
            // e.printStackTrace();
        }
    }

    @Test
    fun testCheckNotNull() {
        Premisex.checkNotNull("", { "is null" })
        try {
            Premisex.checkNotNull<Any>(null, { "is null" })
            Assert.fail()
        } catch (e: Exception) {
            // e.printStackTrace();
        }
        Premisex.checkNotNull("")
        try {
            Premisex.checkNotNull<Any>(null)
            Assert.fail()
        } catch (e: Exception) {
            // e.printStackTrace();
        }
        Premisex.checkNotNull("", "param")
        try {
            Premisex.checkNotNull<Any>(null, "param")
            Assert.fail()
        } catch (e: Exception) {
            // e.printStackTrace();
        }
    }
}