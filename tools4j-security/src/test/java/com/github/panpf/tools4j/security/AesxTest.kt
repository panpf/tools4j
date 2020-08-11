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

package com.github.panpf.tools4j.security;

import org.junit.Assert;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import java.security.Key;

public class AesxTest {
    private static final String SOURCE = "小红那年七岁，她跟着爸妈去赶集，站在一个卖童装的摊位旁边，盯着那条裙子，无论如何都不肯走。\n" +
            "\n" +
            "她太想要这样一条裙子了，或者说白了，她想要一件新衣服，一件不是妈妈手工做的，而是商店里买来的衣服。\n" +
            "\n" +
            "她听见已经走出了几步的妈妈跟站得更远的爸爸说，“这孩子到底随谁呢？才这么大就这么臭美？我们家里人哪有这种样子的？能不能要点脸？”\n" +
            "\n" +
            "爸爸已经非常烦躁，走上前来，不由分说劈头给了她一个耳光，“就知道穿穿穿，打扮那么排场出去干什么？”\n" +
            "\n" +
            "她哇地一声哭了，妈妈倒是很恼怒地跑过来拉住爸爸，“你有病吗？谁让你打她了？”\n" +
            "\n" +
            "可是裙子最终还是没买。她反而成了全家人的笑柄，一直到很多年后，妈妈提起她小时候，还是笑得肚子疼，“一丁点儿大，臭美得很！”\n" +
            "\n" +
            "然后有兄弟姐妹在旁边起哄揶揄她";

    private static final String SOURCE_NO_PADDING = "小红那年七岁，她跟着爸妈去赶集，站在一个卖童装的摊位旁边，盯着那";

    @Test
    public void testDefaultConfig() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Key key = Aesx.createKey(128);
        byte[] encryptResult = Aesx.encrypt(SOURCE.getBytes(), Aesx.DEFAULT, key);
        String decryptResult = Aesx.decryptToString(encryptResult, Aesx.DEFAULT, key);
        Assert.assertEquals("testDefaultConfig", SOURCE, decryptResult);
    }

    @Test
    public void testDefaultConfigWithBase64() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Key key = Aesx.createKey(128);
        String encryptResult = Aesx.encryptToBase64(SOURCE.getBytes(), Aesx.DEFAULT, key);
        String decryptResult = Aesx.decryptToStringFromBase64(encryptResult, Aesx.DEFAULT, key);
        Assert.assertEquals("testDefaultConfigWithBase64", SOURCE, decryptResult);
    }


    @Test
    public void testEcbNoPadding() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Key key = Aesx.createKey(128);
        byte[] encryptResult = Aesx.encrypt(SOURCE_NO_PADDING.getBytes(), Aesx.ECB_NO, key);
        String decryptResult = Aesx.decryptToString(encryptResult, Aesx.ECB_NO, key);
        Assert.assertEquals("testEcbNoPadding", SOURCE_NO_PADDING, decryptResult);
    }

    @Test
    public void testEcbPKCS5Padding() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Key key = Aesx.createKey(128);
        byte[] encryptResult = Aesx.encrypt(SOURCE.getBytes(), Aesx.ECB_PKCS5, key);
        String decryptResult = Aesx.decryptToString(encryptResult, Aesx.ECB_PKCS5, key);
        Assert.assertEquals("testEcbPKCS5Padding", SOURCE, decryptResult);
    }

    @Test
    public void testEcbISO10126Padding() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Key key = Aesx.createKey(128);
        byte[] encryptResult = Aesx.encrypt(SOURCE.getBytes(), Aesx.ECB_ISO10126, key);
        String decryptResult = Aesx.decryptToString(encryptResult, Aesx.ECB_ISO10126, key);
        Assert.assertEquals("testEcbISO10126Padding", SOURCE, decryptResult);
    }


    @Test
    public void testCbcNoPadding() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Key key = Aesx.createKey(128);
        byte[] encryptResult = Aesx.encrypt(SOURCE_NO_PADDING.getBytes(), Aesx.CBC_NO, key);
        String decryptResult = Aesx.decryptToString(encryptResult, Aesx.CBC_NO, key);
        Assert.assertEquals("testCbcNoPadding", SOURCE_NO_PADDING, decryptResult);
    }

    @Test
    public void testCbcPKCS5Padding() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Key key = Aesx.createKey(128);
        byte[] encryptResult = Aesx.encrypt(SOURCE.getBytes(), Aesx.CBC_PKCS5, key);
        String decryptResult = Aesx.decryptToString(encryptResult, Aesx.CBC_PKCS5, key);
        Assert.assertEquals("testCbcPKCS5Padding", SOURCE, decryptResult);
    }

    @Test
    public void testCbcISO10126Padding() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Key key = Aesx.createKey(128);
        byte[] encryptResult = Aesx.encrypt(SOURCE, Aesx.CBC_ISO10126, key);
        String decryptResult = Aesx.decryptToString(encryptResult, Aesx.CBC_ISO10126, key);
        Assert.assertEquals("testCbcISO10126Padding", SOURCE, decryptResult);
    }


    @Test
    public void testErrorPassword() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Key key = Aesx.createKey(128);
        Key errorKey = Aesx.createKey(128);

        String encryptResult = Aesx.encryptToBase64(SOURCE, Aesx.CBC_PKCS5, key);
        String errorDecryptResult = null;
        try {
            errorDecryptResult = Aesx.decryptToStringFromBase64(encryptResult, Aesx.CBC_PKCS5, errorKey);
        } catch (BadPaddingException ignored) {
        }

        Assert.assertNotEquals("testErrorPassword", SOURCE, errorDecryptResult);
    }

    @Test
    public void testCreateKeyBySeed() {
        String seed = "" + System.currentTimeMillis();

        String key1 = Keyx.toBase64(Aesx.createKeyByPassword(seed, 16));
        String key2 = Keyx.toBase64(Aesx.createKeyByPassword(seed, 16));

        Assert.assertEquals("testCreateKeyBySeed", key1, key2);
    }

    @Test
    public void testFromKey() throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        Key key = Aesx.createKey(128);

        String encodeText = Aesx.encryptToBase64(SOURCE, Aesx.CBC_PKCS5, key);

        byte[] keyBytes = Keyx.toBytes(key);
        Key fromBytesKey = Aesx.keyFromBytes(keyBytes);
        Assert.assertEquals(Aesx.decryptToStringFromBase64(encodeText, Aesx.CBC_PKCS5, fromBytesKey), SOURCE);

        String base64Key = Keyx.toBase64(key);
        Key fromBase64Key = Aesx.keyFromBase64(base64Key);
        Assert.assertEquals(Aesx.decryptToStringFromBase64(encodeText, Aesx.CBC_PKCS5, fromBase64Key), SOURCE);
    }
}
