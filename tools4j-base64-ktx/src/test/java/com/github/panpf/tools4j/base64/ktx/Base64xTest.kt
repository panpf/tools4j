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
package com.github.panpf.tools4j.base64.ktx

import com.github.panpf.tools4j.base64.Base64x
import org.junit.Assert
import org.junit.Test
import java.nio.charset.StandardCharsets

class Base64xTest {

    companion object {
        private const val SOURCE = "上山打老虎"
        private val SOURCE_BYTES = SOURCE.toByteArray(StandardCharsets.UTF_8)
    }

    @Test
    fun testBytes() {
        Assert.assertArrayEquals(SOURCE_BYTES, SOURCE_BYTES.base64Encode(0, SOURCE_BYTES.size, Base64x.DEFAULT).let { it.base64Decode(0, it.size, Base64x.DEFAULT) })
        Assert.assertArrayEquals(SOURCE_BYTES, SOURCE_BYTES.base64Encode(0, SOURCE_BYTES.size).let { it.base64Decode(0, it.size) })
        Assert.assertArrayEquals(SOURCE_BYTES, SOURCE_BYTES.base64Encode(Base64x.DEFAULT).base64Decode(Base64x.DEFAULT))
        Assert.assertArrayEquals(SOURCE_BYTES, SOURCE_BYTES.base64Encode().base64Decode())

        Assert.assertArrayEquals(SOURCE_BYTES, SOURCE_BYTES.base64EncodeToString(0, SOURCE_BYTES.size, Base64x.DEFAULT).base64Decode(Base64x.DEFAULT))
        Assert.assertArrayEquals(SOURCE_BYTES, SOURCE_BYTES.base64EncodeToString(0, SOURCE_BYTES.size).base64Decode())
        Assert.assertArrayEquals(SOURCE_BYTES, SOURCE_BYTES.base64EncodeToString(Base64x.DEFAULT).base64Decode(Base64x.DEFAULT))
        Assert.assertArrayEquals(SOURCE_BYTES, SOURCE_BYTES.base64EncodeToString().base64Decode())
    }

    @Test
    fun testString() {
        Assert.assertEquals(SOURCE, SOURCE.base64Encode(Base64x.DEFAULT).let { it.base64DecodeToString(0, it.size, Base64x.DEFAULT) })
        Assert.assertEquals(SOURCE, SOURCE.base64Encode().let { it.base64DecodeToString(0, it.size) })
        Assert.assertEquals(SOURCE, SOURCE.base64Encode(Base64x.DEFAULT).base64DecodeToString(Base64x.DEFAULT))
        Assert.assertEquals(SOURCE, SOURCE.base64Encode().base64DecodeToString())

        Assert.assertEquals(SOURCE, SOURCE.base64EncodeToString(Base64x.DEFAULT).base64DecodeToString(Base64x.DEFAULT))
        Assert.assertEquals(SOURCE, SOURCE.base64EncodeToString().base64DecodeToString())
    }

    @Test
    fun testBlankChar() {
        val source = "电影《我不是药神》热映，口碑爆表，是一部近年来少有佳作。之所以引起了广泛的共鸣，是因为影片中情与法的冲突让人震撼：\n" +
                "\n" +
                "1.特效药的研发成本很高，如果不允许卖高价，并对专利进行保护，就无法积累资金、保障持续的新药研制，这样的话就会导致疾病无药可治。\n" +
                "\n" +
                "2.特效药的价格很高，买不起的病人就意味着等死，就是无钱可治。\n" +
                "\n" +
                "如何才能让患者摆脱要么病死，要么穷死的困境，值得我们每个人深思。\n" +
                "\n" +
                "作者：知守观保\n" +
                "链接：https://www.jianshu.com/p/f5170bdd7fc7\n" +
                "來源：简书\n" +
                "简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。"
        val encrypted = "55S15b2x44CK5oiR5LiN5piv6I2v56We44CL54Ot5pig\n" +
                "77yM5Y+j56KR54iG6KGo77yM5piv5LiA6YOo6L+R5bm05p2l5bCR5pyJ5" +
                " L2z5L2c44CC5LmL5omA5Lul5byV6LW35LqG5bm/5rOb55qE5YWx6bij77yM" +
                "   5piv5Zug5Li65b2x54mH5Lit5oOF5LiO5rOV55qE5Yay56qB6K6p5Lq66ZyH5pK" +
                "   877yaCgoxLueJueaViOiNr+eahOeglOWPkeaIkOacrOW+iOmrmO+8jOWmguaenOS4je" +
                "           WFgeiuuOWNlumrmOS7t++8jOW5tuWvueS4k+WIqei/m+ihjOS/neaKpO+8jOWwseaXoOaz\n\n\n" +
                "leenr+e0r+i1hOmHkeOAgeS/nemanOaMgee7reeahOaWsOiNr+eglOWItu+8jOi/meagt+eahOivneWwseS8muWvvOiHtOeWvueXheaXoOiNr+WPr+ayu+OAggoKMi7nibnmlYjoja/nmoTku7fmoLzlvojpq5jvvIzkubDkuI3otbfnmoTnl4XkurrlsLHmhI/lkbPnnYDnrYnmrbvvvIzlsLHmmK/ml6DpkrHlj6/msrvjgIIKCuWmguS9leaJjeiDveiuqeaCo+iAheaRhuiEseimgeS5iOeXheatu++8jOimgeS5iOept+atu+eahOWbsOWig++8jOWAvOW+l+aIkeS7rOavj+S4quS6uua3seaAneOAggoK5L2c6ICF77ya55+l5a6I6KeC5L+dCumTvuaOpe+8mmh0dHBzOi8vd3d3LmppYW5zaHUuY29tL3AvZjUxNzBiZGQ3ZmM3CuS+hua6kO+8mueugOS5pgrnroDkuabokZfkvZzmnYPlvZLkvZzogIXmiYDmnInvvIzku7vkvZXlvaLlvI/nmoTovazovb3pg73or7fogZTns7vkvZzogIXojrflvpfmjojmnYPlubbms6jmmI7lh7rlpITjgII="
        Assert.assertEquals(source, encrypted.base64DecodeToString())
        Assert.assertEquals(source, encrypted.toByteArray().base64DecodeToString())
    }
}