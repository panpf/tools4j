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

package com.github.panpf.tools4j.crypto;

import com.github.panpf.tools4j.base64.Base64x;
import org.jetbrains.annotations.NotNull;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA encryption and decryption tool method
 */
@SuppressWarnings("WeakerAccess")
public class Rsax {

    private static final String ALGORITHM = "RSA";
    private static final String ALGORITHM_SIGN = "MD5withRSA";

    public static final String DEFAULT = "RSA";
    public static final String ECB_PKCS1 = "RSA/ECB/PKCS1Padding";
    public static final String ECB_OAEP = "RSA/ECB/OAEPPadding";

    private Rsax() {
    }

    /**
     * Create a pair of RSA keys
     *
     * @param keySize Key length, usually a multiple of 1024
     */
    @NotNull
    public static KeyPair createKey(int keySize) {
        KeyPairGenerator keyPairGen;
        try {
            keyPairGen = KeyPairGenerator.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        keyPairGen.initialize(keySize);
        return keyPairGen.generateKeyPair();
    }

    /**
     * Parse the Base 64 string into a RSA public key
     *
     * @throws InvalidKeySpecException Invalid public key
     */
    @NotNull
    public static PublicKey pubKeyFromBase64(@NotNull String base64PublicKeyText) throws InvalidKeySpecException {
        byte[] buffer = Base64x.decode(base64PublicKeyText.getBytes());
        KeyFactory keyFactory;
        try {
            keyFactory = KeyFactory.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * Parse the Base 64 string into a RSA private key
     *
     * @throws InvalidKeySpecException Private key is invalid
     */
    @NotNull
    public static PrivateKey priKeyFromBase64(@NotNull String base64PrivateKeyText) throws InvalidKeySpecException {
        byte[] buffer = Base64x.decode(base64PrivateKeyText.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
        KeyFactory keyFactory;
        try {
            keyFactory = KeyFactory.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return keyFactory.generatePrivate(keySpec);
    }


    /**
     * Generate a RSA digital signature for the information with the private key
     *
     * @param textBytes Original data
     * @param priKey    Private key
     * @throws InvalidKeyException Private key is invalid
     * @throws SignatureException  Signature exception
     */
    @NotNull
    public static byte[] sign(@NotNull byte[] textBytes, @NotNull PrivateKey priKey)
            throws InvalidKeyException, SignatureException {
        Signature signature;
        try {
            signature = Signature.getInstance(ALGORITHM_SIGN);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        signature.initSign(priKey);
        signature.update(textBytes);

        return signature.sign();
    }

    /**
     * Generate a RSA digital signature for the information with the private key
     *
     * @param text   Original text
     * @param priKey Private key
     * @throws InvalidKeyException Private key is invalid
     * @throws SignatureException  Signature exception
     */
    @NotNull
    public static byte[] sign(@NotNull String text, @NotNull PrivateKey priKey)
            throws InvalidKeyException, SignatureException {
        return sign(text.getBytes(), priKey);
    }

    /**
     * Generate a RSA digital signature of the information with a private key and return a Base64 string
     *
     * @param textBytes Original data
     * @param priKey    Private key
     * @throws InvalidKeyException Private key is invalid
     * @throws SignatureException  Signature exception
     */
    @NotNull
    public static String signToBase64(@NotNull byte[] textBytes, @NotNull PrivateKey priKey)
            throws InvalidKeyException, SignatureException {
        return Base64x.encodeToString(sign(textBytes, priKey), Base64x.NO_WRAP);
    }

    /**
     * Generate a RSA digital signature of the information with a private key and return a Base64 string
     *
     * @param text   Original text
     * @param priKey Private key
     * @throws InvalidKeyException Private key is invalid
     * @throws SignatureException  Signature exception
     */
    @NotNull
    public static String signToBase64(@NotNull String text, @NotNull PrivateKey priKey)
            throws InvalidKeyException, SignatureException {
        return Base64x.encodeToString(sign(text.getBytes(), priKey), Base64x.NO_WRAP);
    }


    /**
     * Verify the RSA signature with the public key
     *
     * @param signBytes signature
     * @param data      Original data
     * @param pubKey    Public key
     * @throws InvalidKeyException Invalid public key
     * @throws SignatureException  Signature exception
     */
    public static boolean verify(@NotNull byte[] signBytes, @NotNull byte[] data, @NotNull PublicKey pubKey)
            throws InvalidKeyException, SignatureException {
        Signature signature;
        try {
            signature = Signature.getInstance(ALGORITHM_SIGN);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        signature.initVerify(pubKey);
        signature.update(data);
        return signature.verify(signBytes);
    }

    /**
     * Verify the RSA signature with the public key
     *
     * @param signBytes signature
     * @param text      Original text
     * @param pubKey    Public key
     * @throws InvalidKeyException Invalid public key
     * @throws SignatureException  Signature exception
     */
    public static boolean verify(@NotNull byte[] signBytes, @NotNull String text, @NotNull PublicKey pubKey)
            throws InvalidKeyException, SignatureException {
        return verify(signBytes, text.getBytes(), pubKey);
    }

    /**
     * Verify the RSA signature with the public key
     *
     * @param base64Sign Base64 encoded signature
     * @param data       Original data
     * @param pubKey     Public key
     * @throws InvalidKeyException Invalid public key
     * @throws SignatureException  Signature exception
     */
    public static boolean verifyFromBase64(@NotNull String base64Sign, @NotNull byte[] data, @NotNull PublicKey pubKey)
            throws InvalidKeyException, SignatureException {
        return verify(Base64x.decode(base64Sign.getBytes()), data, pubKey);
    }

    /**
     * Verify the RSA signature with the public key
     *
     * @param base64Sign Base64 encoded signature
     * @param text       Original text
     * @param pubKey     Public key
     * @throws InvalidKeyException Invalid public key
     * @throws SignatureException  Signature exception
     */
    public static boolean verifyFromBase64(@NotNull String base64Sign, @NotNull String text, @NotNull PublicKey pubKey)
            throws InvalidKeyException, SignatureException {
        return verify(Base64x.decode(base64Sign), text.getBytes(), pubKey);
    }


    /**
     * Encrypt raw data using the RSA algorithm
     *
     * @param rawData   Raw data to be encrypted
     * @param algorithm RSA encryption algorithm, The following values ​​are available: {@link #DEFAULT},{@link #ECB_PKCS1},{@link #ECB_OAEP}
     * @param key       Secret key
     * @throws InvalidKeyException       Invalid key
     * @throws BadPaddingException       Padding error
     * @throws IllegalBlockSizeException Block size error
     */
    @NotNull
    public static byte[] encrypt(@NotNull byte[] rawData, @NotNull String algorithm, @NotNull Key key)
            throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = createCipher(Cipher.ENCRYPT_MODE, algorithm, key);
        return blockDoCipher(cipher, Cipher.ENCRYPT_MODE, rawData);
    }

    /**
     * Encrypt raw text using the RSA algorithm
     *
     * @param rawText   Raw text to be encrypted
     * @param algorithm RSA encryption algorithm, The following values ​​are available: {@link #DEFAULT},{@link #ECB_PKCS1},{@link #ECB_OAEP}
     * @param key       Secret key
     * @throws InvalidKeyException       Invalid key
     * @throws BadPaddingException       Padding error
     * @throws IllegalBlockSizeException Block size error
     */
    @NotNull
    public static byte[] encrypt(@NotNull String rawText, @NotNull String algorithm, @NotNull Key key)
            throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return encrypt(rawText.getBytes(), algorithm, key);
    }

    /**
     * Encrypt the raw data using the RSA algorithm and convert the encrypted result to Base64 encoding
     *
     * @param rawData   Raw data to be encrypted
     * @param algorithm RSA encryption algorithm, The following values ​​are available: {@link #DEFAULT},{@link #ECB_PKCS1},{@link #ECB_OAEP}
     * @param key       Secret key
     * @throws InvalidKeyException       Invalid key
     * @throws BadPaddingException       Padding error
     * @throws IllegalBlockSizeException Block size error
     */
    @NotNull
    public static String encryptToBase64(@NotNull byte[] rawData, @NotNull String algorithm, @NotNull Key key)
            throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return Base64x.encodeToString(encrypt(rawData, algorithm, key), Base64x.NO_WRAP);
    }

    /**
     * Encrypt the raw text using the RSA algorithm and convert the encrypted result to Base64 encoding
     *
     * @param rawText   Raw text to be encrypted
     * @param algorithm RSA encryption algorithm, The following values ​​are available: {@link #DEFAULT},{@link #ECB_PKCS1},{@link #ECB_OAEP}
     * @param key       Secret key
     * @throws InvalidKeyException       Invalid key
     * @throws BadPaddingException       Padding error
     * @throws IllegalBlockSizeException Block size error
     */
    @NotNull
    public static String encryptToBase64(@NotNull String rawText, @NotNull String algorithm, @NotNull Key key)
            throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return Base64x.encodeToString(encrypt(rawText.getBytes(), algorithm, key), Base64x.NO_WRAP);
    }


    /**
     * Decrypt ciphertext encrypted using the RSA algorithm
     *
     * @param cipherData Ciphertext to be decrypted
     * @param algorithm  RSA encryption algorithm, The following values ​​are available: {@link #DEFAULT},{@link #ECB_PKCS1},{@link #ECB_OAEP}
     * @param key        Secret key
     * @throws InvalidKeyException       Invalid key
     * @throws BadPaddingException       Padding error
     * @throws IllegalBlockSizeException Block size error
     */
    @NotNull
    public static byte[] decrypt(@NotNull byte[] cipherData, @NotNull String algorithm, @NotNull Key key)
            throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = createCipher(Cipher.DECRYPT_MODE, algorithm, key);
        return blockDoCipher(cipher, Cipher.DECRYPT_MODE, cipherData);
    }

    /**
     * Decryption uses the RSA algorithm to encrypt and then use Base64 encoded ciphertext
     *
     * @param baseCipherText Ciphertext to be decrypted
     * @param algorithm      RSA encryption algorithm, The following values ​​are available: {@link #DEFAULT},{@link #ECB_PKCS1},{@link #ECB_OAEP}
     * @param key            Secret key
     * @throws InvalidKeyException       Invalid key
     * @throws BadPaddingException       Padding error
     * @throws IllegalBlockSizeException Block size error
     */
    @NotNull
    public static byte[] decryptFromBase64(@NotNull String baseCipherText, @NotNull String algorithm, @NotNull Key key)
            throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return decrypt(Base64x.decode(baseCipherText.getBytes()), algorithm, key);
    }

    /**
     * Decrypt ciphertext encrypted using the RSA algorithm
     *
     * @param cipherData Ciphertext to be decrypted
     * @param algorithm  RSA encryption algorithm, The following values ​​are available: {@link #DEFAULT},{@link #ECB_PKCS1},{@link #ECB_OAEP}
     * @param key        Secret key
     * @throws InvalidKeyException       Invalid key
     * @throws BadPaddingException       Padding error
     * @throws IllegalBlockSizeException Block size error
     */
    @NotNull
    public static String decryptToString(@NotNull byte[] cipherData, @NotNull String algorithm, @NotNull Key key)
            throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return new String(decrypt(cipherData, algorithm, key));
    }

    /**
     * Decryption uses the RSA algorithm to encrypt and then use Base64 encoded ciphertext
     *
     * @param baseCipherText Ciphertext to be decrypted
     * @param algorithm      RSA encryption algorithm, The following values ​​are available: {@link #DEFAULT},{@link #ECB_PKCS1},{@link #ECB_OAEP}
     * @param key            Secret key
     * @throws InvalidKeyException       Invalid key
     * @throws BadPaddingException       Padding error
     * @throws IllegalBlockSizeException Block size error
     */
    @NotNull
    public static String decryptToStringFromBase64(@NotNull String baseCipherText, @NotNull String algorithm, @NotNull Key key)
            throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return new String(decryptFromBase64(baseCipherText, algorithm, key));
    }


    private static Cipher createCipher(int opMode, @NotNull String algorithm, @NotNull Key key) throws InvalidKeyException {
        Cipher cipher;
        try {
            cipher = Cipher.getInstance(algorithm);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new IllegalArgumentException(e);
        }

        cipher.init(opMode, key);

        return cipher;
    }

    private static byte[] blockDoCipher(@NotNull Cipher cipher, int opMode, @NotNull byte[] data) throws BadPaddingException, IllegalBlockSizeException {
        int dataLength = data.length;
        int blockSize = cipher.getBlockSize();
        if (blockSize <= 0) blockSize = opMode == Cipher.ENCRYPT_MODE ? 117 : 128;
        if (dataLength > blockSize) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int offset = 0;
            while (offset < dataLength) {
                int length = offset + blockSize <= dataLength ? blockSize : dataLength - offset;
                byte[] cache = cipher.doFinal(data, offset, length);
                try {
                    outputStream.write(cache);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                offset += length;
            }
            return outputStream.toByteArray();
        } else {
            return cipher.doFinal(data);
        }
    }
}
