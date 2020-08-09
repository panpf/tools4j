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
import java.security.*;
import java.security.spec.InvalidKeySpecException;

public class RsaxTest {

    private static final String SOURCE = "{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}";

    private static final String SOURCE_OAEP = "小红那年七岁，她跟着爸妈去赶集，站在一个卖童装的摊位旁边";

    /**
     * 测试公钥加密私钥解密
     */
    @Test
    public void testPubPriBytes() throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        KeyPair keyPair = Rsax.createKey(1024);
        byte[] encryptResult = Rsax.encrypt(SOURCE.getBytes(), Rsax.DEFAULT, keyPair.getPublic());
        String decryptResult = Rsax.decryptToString(encryptResult, Rsax.DEFAULT, keyPair.getPrivate());
        Assert.assertEquals("testPubPriBytes", SOURCE, decryptResult);
    }

    /**
     * 测试私钥加密公钥解密
     */
    @Test
    public void testPriPubBytes() throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        KeyPair keyPair = Rsax.createKey(1024);
        byte[] encryptResult = Rsax.encrypt(SOURCE, Rsax.DEFAULT, keyPair.getPrivate());
        String decryptResult = Rsax.decryptToString(encryptResult, Rsax.DEFAULT, keyPair.getPublic());
        Assert.assertEquals("testPriPubBytes", SOURCE, decryptResult);
    }

    /**
     * 测试公钥加密私钥解密转 Base64
     */
    @Test
    public void testPubPriWithBase64() throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        KeyPair keyPair = Rsax.createKey(1024);
        String encryptResult = Rsax.encryptToBase64(SOURCE, Rsax.DEFAULT, keyPair.getPublic());
        String decryptResult = Rsax.decryptToStringFromBase64(encryptResult, Rsax.DEFAULT, keyPair.getPrivate());
        Assert.assertEquals("testPubPriWithBase64", SOURCE, decryptResult);
    }

    /**
     * 测试私钥加密公钥解密转 Base64
     */
    @Test
    public void testPriPubWithBase64() throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        KeyPair keyPair = Rsax.createKey(1024);
        String encryptResult = Rsax.encryptToBase64(SOURCE.getBytes(), Rsax.DEFAULT, keyPair.getPrivate());
        String decryptResult = Rsax.decryptToStringFromBase64(encryptResult, Rsax.DEFAULT, keyPair.getPublic());
        Assert.assertEquals("testPriPubWithBase64", SOURCE, decryptResult);
    }


    /**
     * 测试签名、验证
     */
    @Test
    public void testSignBytes() throws InvalidKeyException, SignatureException {
        KeyPair keyPair = Rsax.createKey(1024);
        byte[] bytesSign = Rsax.sign(SOURCE, keyPair.getPrivate());
        Assert.assertTrue("testSignBytes", Rsax.verify(bytesSign, SOURCE, keyPair.getPublic()));
    }

    /**
     * 测试签名、验证 Base64
     */
    @Test
    public void testSignWithBase64() throws InvalidKeyException, SignatureException {
        KeyPair keyPair = Rsax.createKey(1024);
        String base64Sign = Rsax.signToBase64(SOURCE, keyPair.getPrivate());
        Assert.assertTrue("testSignWithBase64", Rsax.verifyFromBase64(base64Sign, SOURCE, keyPair.getPublic()));
    }

    /**
     * 使用错误的 KEY 解密
     */
    @Test
    public void testErrorKey() throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] encryptBytes = Rsax.encrypt(SOURCE.getBytes(), Rsax.DEFAULT, Rsax.createKey(1024).getPublic());
        String bytesPriKeyDecryptResult = null;
        try {
            bytesPriKeyDecryptResult = Rsax.decryptToString(encryptBytes, Rsax.DEFAULT, Rsax.createKey(1024).getPrivate());
        } catch (Exception ignored) {
        }
        Assert.assertNotEquals("testErrorKey", SOURCE, bytesPriKeyDecryptResult);
    }

    @Test
    public void testEcbPKCS1Padding() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        KeyPair keyPair = Rsax.createKey(1024);
        byte[] encryptResult = Rsax.encrypt(SOURCE.getBytes(), Rsax.ECB_PKCS1, keyPair.getPublic());
        String decryptResult = Rsax.decryptToString(encryptResult, Rsax.ECB_PKCS1, keyPair.getPrivate());
        Assert.assertEquals("testEcbPKCS1Padding", SOURCE, decryptResult);
    }

    @Test
    public void testEcbOAEPPadding() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        KeyPair keyPair = Rsax.createKey(1024);
        byte[] encryptResult = Rsax.encrypt(SOURCE_OAEP.getBytes(), Rsax.ECB_OAEP, keyPair.getPublic());
        String decryptResult = Rsax.decryptToString(encryptResult, Rsax.ECB_OAEP, keyPair.getPrivate());
        Assert.assertEquals("testEcbOAEPPadding", SOURCE_OAEP, decryptResult);
    }

    @Test
    public void testKeyToBase64() throws InvalidKeySpecException, SignatureException, InvalidKeyException {
        KeyPair keyPair = Rsax.createKey(1024);

        PublicKey pubKey = Rsax.pubKeyFromBase64(Keyx.toBase64(keyPair.getPublic()));
        PrivateKey priKey = Rsax.priKeyFromBase64(Keyx.toBase64(keyPair.getPrivate()));

        String base64Sign = Rsax.signToBase64(SOURCE.getBytes(), priKey);
        Assert.assertTrue(Rsax.verifyFromBase64(base64Sign, SOURCE.getBytes(), pubKey));
    }
}
