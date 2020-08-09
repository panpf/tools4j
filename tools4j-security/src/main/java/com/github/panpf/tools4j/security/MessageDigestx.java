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

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Message digest tool method
 */
public class MessageDigestx {

    private static final char[] HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private MessageDigestx() {
    }


    /* ******************************************* InputStream *******************************************/

    /**
     * Get the message digest of the input stream using the specified [algorithm]
     */
    @NotNull
    public static String getDigest(@NotNull InputStream inputStream, @NotNull String algorithm, @Nullable MessageDigestListener listener) throws IOException {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        byte[] buffer = new byte[1024 * 8];
        int readLength;
        long completedLength = 0;
        while (listener == null || !listener.isCanceled()) {
            readLength = inputStream.read(buffer);
            if (readLength == -1) {
                break;
            } else {
                messageDigest.update(buffer, 0, readLength);
            }
            if (listener != null && !listener.isCanceled()) {
                completedLength += readLength;
                listener.onUpdateProgress(completedLength);
            }
        }

        byte[] result = messageDigest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte aMd5Byte : result) {
            sb.append(HEX[(aMd5Byte & 0xff) / 16]);
            sb.append(HEX[(aMd5Byte & 0xff) % 16]);
        }
        return sb.toString();
    }

    /**
     * Get the message digest of the input stream using the specified [algorithm]
     */
    @NotNull
    public static String getDigest(@NotNull InputStream inputStream, @NotNull String algorithm) throws IOException {
        return getDigest(inputStream, algorithm, null);
    }

    /**
     * Get the message digest of the input stream using the specified [algorithm]
     */
    @NotNull
    public static String getDigestOrEmpty(@NotNull InputStream inputStream, @NotNull String algorithm, @Nullable MessageDigestListener listener) {
        try {
            return getDigest(inputStream, algorithm, listener);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Get the message digest of the input stream using the specified [algorithm]
     */
    @NotNull
    public static String getDigestOrEmpty(@NotNull InputStream inputStream, @NotNull String algorithm) {
        try {
            return getDigest(inputStream, algorithm);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Get the message digest of the input stream using the specified [algorithm]
     */
    @Nullable
    public static String getDigestOrNull(@NotNull InputStream inputStream, @NotNull String algorithm, @Nullable MessageDigestListener listener) {
        try {
            return getDigest(inputStream, algorithm, listener);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get the message digest of the input stream using the specified [algorithm]
     */
    @Nullable
    public static String getDigestOrNull(@NotNull InputStream inputStream, @NotNull String algorithm) {
        try {
            return getDigest(inputStream, algorithm);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get the message digest of the input stream using the MD5 algorithm
     */
    @NotNull
    public static String getMD5(@NotNull InputStream inputStream, @Nullable MessageDigestListener listener) throws IOException {
        return getDigest(inputStream, "MD5", listener);
    }

    /**
     * Get the message digest of the input stream using the MD5 algorithm
     */
    @NotNull
    public static String getMD5(@NotNull InputStream inputStream) throws IOException {
        return getDigest(inputStream, "MD5");
    }

    /**
     * Get the message digest of the input stream using the MD5 algorithm
     */
    @NotNull
    public static String getMD5OrEmpty(@NotNull InputStream inputStream, @Nullable MessageDigestListener listener) {
        try {
            return getMD5(inputStream, listener);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Get the message digest of the input stream using the MD5 algorithm
     */
    @NotNull
    public static String getMD5OrEmpty(@NotNull InputStream inputStream) {
        try {
            return getMD5(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Get the message digest of the input stream using the MD5 algorithm
     */
    @Nullable
    public static String getMD5OrNull(@NotNull InputStream inputStream, @Nullable MessageDigestListener listener) {
        try {
            return getMD5(inputStream, listener);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get the message digest of the input stream using the MD5 algorithm
     */
    @Nullable
    public static String getMD5OrNull(@NotNull InputStream inputStream) {
        try {
            return getMD5(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get the message digest of the input stream using the MD5 algorithm, only the middle 16 bits are reserved
     */
    @NotNull
    public static String getMD5_16(@NotNull InputStream inputStream, @Nullable MessageDigestListener listener) throws IOException {
        return getDigest(inputStream, "MD5", listener).substring(8, 24);
    }

    /**
     * Get the message digest of the input stream using the MD5 algorithm, only the middle 16 bits are reserved
     */
    @NotNull
    public static String getMD5_16(@NotNull InputStream inputStream) throws IOException {
        return getDigest(inputStream, "MD5").substring(8, 24);
    }

    /**
     * Get the message digest of the input stream using the MD5 algorithm, only the middle 16 bits are reserved
     */
    @NotNull
    public static String getMD5_16OrEmpty(@NotNull InputStream inputStream, @Nullable MessageDigestListener listener) {
        try {
            return getMD5_16(inputStream, listener);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Get the message digest of the input stream using the MD5 algorithm, only the middle 16 bits are reserved
     */
    @NotNull
    public static String getMD5_16OrEmpty(@NotNull InputStream inputStream) {
        try {
            return getMD5_16(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Get the message digest of the input stream using the MD5 algorithm, only the middle 16 bits are reserved
     */
    @Nullable
    public static String getMD5_16OrNull(@NotNull InputStream inputStream, @Nullable MessageDigestListener listener) {
        try {
            return getMD5_16(inputStream, listener);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get the message digest of the input stream using the MD5 algorithm, only the middle 16 bits are reserved
     */
    @Nullable
    public static String getMD5_16OrNull(@NotNull InputStream inputStream) {
        try {
            return getMD5_16(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get the message digest of the input stream using the SHA1 algorithm
     */
    @NotNull
    public static String getSHA1(@NotNull InputStream inputStream, @Nullable MessageDigestListener listener) throws IOException {
        return getDigest(inputStream, "SHA1", listener);
    }

    /**
     * Get the message digest of the input stream using the SHA1 algorithm
     */
    @NotNull
    public static String getSHA1(@NotNull InputStream inputStream) throws IOException {
        return getDigest(inputStream, "SHA1");
    }

    /**
     * Get the message digest of the input stream using the SHA1 algorithm
     */
    @NotNull
    public static String getSHA1OrEmpty(@NotNull InputStream inputStream, @Nullable MessageDigestListener listener) {
        try {
            return getSHA1(inputStream, listener);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Get the message digest of the input stream using the SHA1 algorithm
     */
    @NotNull
    public static String getSHA1OrEmpty(@NotNull InputStream inputStream) {
        try {
            return getSHA1(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Get the message digest of the input stream using the SHA1 algorithm
     */
    @Nullable
    public static String getSHA1OrNull(@NotNull InputStream inputStream, @Nullable MessageDigestListener listener) {
        try {
            return getSHA1(inputStream, listener);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get the message digest of the input stream using the SHA1 algorithm
     */
    @Nullable
    public static String getSHA1OrNull(@NotNull InputStream inputStream) {
        try {
            return getSHA1(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get the message digest of the input stream using the SHA-256 algorithm
     */
    @NotNull
    public static String getSHA256(@NotNull InputStream inputStream, @Nullable MessageDigestListener listener) throws IOException {
        return getDigest(inputStream, "SHA-256", listener);
    }

    /**
     * Get the message digest of the input stream using the SHA-256 algorithm
     */
    @NotNull
    public static String getSHA256(@NotNull InputStream inputStream) throws IOException {
        return getDigest(inputStream, "SHA-256");
    }

    /**
     * Get the message digest of the input stream using the SHA-256 algorithm
     */
    @NotNull
    public static String getSHA256OrEmpty(@NotNull InputStream inputStream, @Nullable MessageDigestListener listener) {
        try {
            return getSHA256(inputStream, listener);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Get the message digest of the input stream using the SHA-256 algorithm
     */
    @NotNull
    public static String getSHA256OrEmpty(@NotNull InputStream inputStream) {
        try {
            return getSHA256(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Get the message digest of the input stream using the SHA-256 algorithm
     */
    @Nullable
    public static String getSHA256OrNull(@NotNull InputStream inputStream, @Nullable MessageDigestListener listener) {
        try {
            return getSHA256(inputStream, listener);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get the message digest of the input stream using the SHA-256 algorithm
     */
    @Nullable
    public static String getSHA256OrNull(@NotNull InputStream inputStream) {
        try {
            return getSHA256(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get the message digest of the input stream using the SHA-512 algorithm
     */
    @NotNull
    public static String getSHA512(@NotNull InputStream inputStream, @Nullable MessageDigestListener listener) throws IOException {
        return getDigest(inputStream, "SHA-512", listener);
    }

    /**
     * Get the message digest of the input stream using the SHA-512 algorithm
     */
    @NotNull
    public static String getSHA512(@NotNull InputStream inputStream) throws IOException {
        return getDigest(inputStream, "SHA-512");
    }

    /**
     * Get the message digest of the input stream using the SHA-512 algorithm
     */
    @NotNull
    public static String getSHA512OrEmpty(@NotNull InputStream inputStream, @Nullable MessageDigestListener listener) {
        try {
            return getSHA512(inputStream, listener);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Get the message digest of the input stream using the SHA-512 algorithm
     */
    @NotNull
    public static String getSHA512OrEmpty(@NotNull InputStream inputStream) {
        try {
            return getSHA512(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Get the message digest of the input stream using the SHA-512 algorithm
     */
    @Nullable
    public static String getSHA512OrNull(@NotNull InputStream inputStream, @Nullable MessageDigestListener listener) {
        try {
            return getSHA512(inputStream, listener);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get the message digest of the input stream using the SHA-512 algorithm
     */
    @Nullable
    public static String getSHA512OrNull(@NotNull InputStream inputStream) {
        try {
            return getSHA512(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /* ******************************************* bytes *******************************************/


    /**
     * Get the message digest of the bytes using the specified [algorithm]
     */
    @NotNull
    public static String getDigest(@NotNull byte[] data, @NotNull String algorithm) {
        InputStream inputStream = new ByteArrayInputStream(data);
        try {
            return getDigest(inputStream, algorithm);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the bytes using the MD5 algorithm
     */
    @NotNull
    public static String getMD5(@NotNull byte[] data) {
        InputStream inputStream = new ByteArrayInputStream(data);
        try {
            return getMD5(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the bytes using the MD5 algorithm, only the middle 16 bits are reserved
     */
    @NotNull
    public static String getMD5_16(@NotNull byte[] data) {
        InputStream inputStream = new ByteArrayInputStream(data);
        try {
            return getMD5_16(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the bytes using the SHA1 algorithm
     */
    @NotNull
    public static String getSHA1(@NotNull byte[] data) {
        InputStream inputStream = new ByteArrayInputStream(data);
        try {
            return getSHA1(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the bytes using the SHA-256 algorithm
     */
    @NotNull
    public static String getSHA256(@NotNull byte[] data) {
        InputStream inputStream = new ByteArrayInputStream(data);
        try {
            return getSHA256(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the bytes using the SHA-512 algorithm
     */
    @NotNull
    public static String getSHA512(@NotNull byte[] data) {
        InputStream inputStream = new ByteArrayInputStream(data);
        try {
            return getSHA512(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /* ******************************************* String *******************************************/


    /**
     * Get the message digest of the text using the specified [algorithm]
     */
    @NotNull
    public static String getDigest(@NotNull String text, @NotNull String algorithm) {
        InputStream inputStream = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
        try {
            return getDigest(inputStream, algorithm);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the text using the MD5 algorithm
     */
    @NotNull
    public static String getMD5(@NotNull String text) {
        InputStream inputStream = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
        try {
            return getMD5(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the text using the MD5 algorithm, only the middle 16 bits are reserved
     */
    @NotNull
    public static String getMD5_16(@NotNull String text) {
        InputStream inputStream = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
        try {
            return getMD5_16(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the text using the SHA1 algorithm
     */
    @NotNull
    public static String getSHA1(@NotNull String text) {
        InputStream inputStream = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
        try {
            return getSHA1(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the text using the SHA-256 algorithm
     */
    @NotNull
    public static String getSHA256(@NotNull String text) {
        InputStream inputStream = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
        try {
            return getSHA256(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the text using the SHA-512 algorithm
     */
    @NotNull
    public static String getSHA512(@NotNull String text) {
        InputStream inputStream = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
        try {
            return getSHA512(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /* ******************************************* File *******************************************/


    /**
     * Get the message digest of the file using the specified [algorithm]
     */
    @NotNull
    public static String getDigest(@NotNull File file, @NotNull String algorithm, @Nullable MessageDigestListener listener) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getDigest(inputStream, algorithm, listener);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the specified [algorithm]
     */
    @NotNull
    public static String getDigest(@NotNull File file, @NotNull String algorithm) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getDigest(inputStream, algorithm);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the specified [algorithm]
     */
    @NotNull
    public static String getDigestOrEmpty(@NotNull File file, @NotNull String algorithm, @Nullable MessageDigestListener listener) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getDigest(inputStream, algorithm, listener);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the specified [algorithm]
     */
    @NotNull
    public static String getDigestOrEmpty(@NotNull File file, @NotNull String algorithm) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getDigest(inputStream, algorithm);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the specified [algorithm]
     */
    @Nullable
    public static String getDigestOrNull(@NotNull File file, @NotNull String algorithm, @Nullable MessageDigestListener listener) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getDigest(inputStream, algorithm, listener);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the specified [algorithm]
     */
    @Nullable
    public static String getDigestOrNull(@NotNull File file, @NotNull String algorithm) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getDigest(inputStream, algorithm);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the MD5 algorithm
     */
    @NotNull
    public static String getMD5(@NotNull File file, @Nullable MessageDigestListener listener) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getMD5(inputStream, listener);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the MD5 algorithm
     */
    @NotNull
    public static String getMD5(@NotNull File file) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getMD5(inputStream);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the MD5 algorithm
     */
    @NotNull
    public static String getMD5OrEmpty(@NotNull File file, @Nullable MessageDigestListener listener) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getMD5(inputStream, listener);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the MD5 algorithm
     */
    @NotNull
    public static String getMD5OrEmpty(@NotNull File file) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getMD5(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the MD5 algorithm
     */
    @Nullable
    public static String getMD5OrNull(@NotNull File file, @Nullable MessageDigestListener listener) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getMD5(inputStream, listener);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the MD5 algorithm
     */
    @Nullable
    public static String getMD5OrNull(@NotNull File file) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getMD5(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the MD5 algorithm, only the middle 16 bits are reserved
     */
    @NotNull
    public static String getMD5_16(@NotNull File file, @Nullable MessageDigestListener listener) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getMD5_16(inputStream, listener);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the MD5 algorithm, only the middle 16 bits are reserved
     */
    @NotNull
    public static String getMD5_16(@NotNull File file) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getMD5_16(inputStream);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the MD5 algorithm, only the middle 16 bits are reserved
     */
    @NotNull
    public static String getMD5_16OrEmpty(@NotNull File file, @Nullable MessageDigestListener listener) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getMD5_16(inputStream, listener);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the MD5 algorithm, only the middle 16 bits are reserved
     */
    @NotNull
    public static String getMD5_16OrEmpty(@NotNull File file) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getMD5_16(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the MD5 algorithm, only the middle 16 bits are reserved
     */
    @Nullable
    public static String getMD5_16OrNull(@NotNull File file, @Nullable MessageDigestListener listener) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getMD5_16(inputStream, listener);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the MD5 algorithm, only the middle 16 bits are reserved
     */
    @Nullable
    public static String getMD5_16OrNull(@NotNull File file) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getMD5_16(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the SHA1 algorithm
     */
    @NotNull
    public static String getSHA1(@NotNull File file, @Nullable MessageDigestListener listener) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getSHA1(inputStream, listener);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the SHA1 algorithm
     */
    @NotNull
    public static String getSHA1(@NotNull File file) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getSHA1(inputStream);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the SHA1 algorithm
     */
    @NotNull
    public static String getSHA1OrEmpty(@NotNull File file, @Nullable MessageDigestListener listener) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getSHA1(inputStream, listener);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the SHA1 algorithm
     */
    @NotNull
    public static String getSHA1OrEmpty(@NotNull File file) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getSHA1(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the SHA1 algorithm
     */
    @Nullable
    public static String getSHA1OrNull(@NotNull File file, @Nullable MessageDigestListener listener) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getSHA1(inputStream, listener);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the SHA1 algorithm
     */
    @Nullable
    public static String getSHA1OrNull(@NotNull File file) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getSHA1(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the SHA-256 algorithm
     */
    @NotNull
    public static String getSHA256(@NotNull File file, @Nullable MessageDigestListener listener) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getSHA256(inputStream, listener);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the SHA-256 algorithm
     */
    @NotNull
    public static String getSHA256(@NotNull File file) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getSHA256(inputStream);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the SHA-256 algorithm
     */
    @NotNull
    public static String getSHA256OrEmpty(@NotNull File file, @Nullable MessageDigestListener listener) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getSHA256(inputStream, listener);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the SHA-256 algorithm
     */
    @NotNull
    public static String getSHA256OrEmpty(@NotNull File file) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getSHA256(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the SHA-256 algorithm
     */
    @Nullable
    public static String getSHA256OrNull(@NotNull File file, @Nullable MessageDigestListener listener) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getSHA256(inputStream, listener);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the SHA-256 algorithm
     */
    @Nullable
    public static String getSHA256OrNull(@NotNull File file) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getSHA256(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the SHA-512 algorithm
     */
    @NotNull
    public static String getSHA512(@NotNull File file, @Nullable MessageDigestListener listener) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getSHA512(inputStream, listener);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the SHA-512 algorithm
     */
    @NotNull
    public static String getSHA512(@NotNull File file) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getSHA512(inputStream);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the SHA-512 algorithm
     */
    @NotNull
    public static String getSHA512OrEmpty(@NotNull File file, @Nullable MessageDigestListener listener) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getSHA512(inputStream, listener);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the SHA-512 algorithm
     */
    @NotNull
    public static String getSHA512OrEmpty(@NotNull File file) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getSHA512(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the SHA-512 algorithm
     */
    @Nullable
    public static String getSHA512OrNull(@NotNull File file, @Nullable MessageDigestListener listener) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getSHA512(inputStream, listener);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the message digest of the file using the SHA-512 algorithm
     */
    @Nullable
    public static String getSHA512OrNull(@NotNull File file) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getSHA512(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}