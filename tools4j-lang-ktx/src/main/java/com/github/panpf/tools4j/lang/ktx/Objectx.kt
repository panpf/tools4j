@file:Suppress("NOTHING_TO_INLINE")

package com.github.panpf.tools4j.lang.ktx

import com.github.panpf.tools4j.lang.Objectx

/*
 * Object tool method
 */

/**
 * Returns a simple string representation of the object.
 * value of:
 * <blockquote>
 * <pre>
 * getClass().getSimpleName() + '@' + Integer.toHexString(hashCode())
 * </pre></blockquote>
 *
 * @return  a string representation of the object.
 */
inline fun Any?.toSimpleString(): String = Objectx.toSimpleString(this)