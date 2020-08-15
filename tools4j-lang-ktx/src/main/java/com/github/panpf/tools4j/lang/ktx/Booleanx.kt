@file:Suppress("NOTHING_TO_INLINE")

package com.github.panpf.tools4j.lang.ktx

import com.github.panpf.tools4j.lang.Booleanx

/*
 * Boolean tool method
 */

fun Boolean?.isTrue(): Boolean = Booleanx.isTrue(this)

fun Boolean?.isFalse(): Boolean = Booleanx.isFalse(this)

fun Boolean?.isNullOrTrue(): Boolean = Booleanx.isNullOrTrue(this)

fun Boolean?.isNullOrFalse(): Boolean = Booleanx.isNullOrFalse(this)