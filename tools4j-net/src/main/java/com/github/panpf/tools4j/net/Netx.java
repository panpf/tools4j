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

package com.github.panpf.tools4j.net;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("WeakerAccess")
public class Netx {

    /**
     * IP v4
     */
    public static final Pattern IPV4 = Pattern.compile("((?:(?:25[0-5]|2[0-4]\\d|(?:1\\d{2}|[1-9]?\\d))\\.){3}(?:25[0-5]|2[0-4]\\d|(?:1\\d{2}|[1-9]?\\d)))");

    /**
     * IP v6. Supports converged IP v4 format
     */
    public static final Pattern IPV6 = Pattern.compile("\\s*((([0-9A-Fa-f]{1,4}:){7}(([0-9A-Fa-f]{1,4})|:))|(([0-9A-Fa-f]{1,4}:){6}(:|((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})|(:[0-9A-Fa-f]{1,4})))|(([0-9A-Fa-f]{1,4}:){5}((:((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(([0-9A-Fa-f]{1,4}:){4}(:[0-9A-Fa-f]{1,4}){0,1}((:((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(([0-9A-Fa-f]{1,4}:){3}(:[0-9A-Fa-f]{1,4}){0,2}((:((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(([0-9A-Fa-f]{1,4}:){2}(:[0-9A-Fa-f]{1,4}){0,3}((:((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(([0-9A-Fa-f]{1,4}:)(:[0-9A-Fa-f]{1,4}){0,4}((:((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(:(:[0-9A-Fa-f]{1,4}){0,5}((:((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})))(%.+)?\\s*");

    /**
     * Mac Address. Support for splitting in ':' and '-'
     */
    public static final Pattern MAC_ADDRESS = Pattern.compile("([A-Fa-f0-9]{2}(-[A-Fa-f0-9]{2}){5})|([A-Fa-f0-9]{2}(:[A-Fa-f0-9]{2}){5})");

    private Netx() {
    }

    /**
     * Return true if the given ip address is IP v4
     */
    public static boolean isIPV4(@Nullable CharSequence ipAddress) {
        return IPV4.matcher(ipAddress != null ? ipAddress : "").matches();
    }

    /**
     * Return true if the given ip address is IP v6. Supports converged IP v4 format
     */
    public static boolean isIPV6(@Nullable CharSequence ipAddress) {
        return IPV6.matcher(ipAddress != null ? ipAddress : "").matches();
    }

    /**
     * Return true if the given char sequence is mac address. Support for splitting in ':' and '-'
     */
    public static boolean isMacAddress(@Nullable CharSequence ipAddress) {
        return MAC_ADDRESS.matcher(ipAddress != null ? ipAddress : "").matches();
    }

    /**
     * Get local IP address
     */
    @Nullable
    public static String getLocalIPAddress() {
        String ipAddress = null;
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface networkInterface = en.nextElement();
                for (Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses(); inetAddresses.hasMoreElements(); ) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        ipAddress = inetAddress.getHostAddress();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* Filter fake mac addresses */
        // fe80::7063:1bff:feb3:2dfc%dummy0 from android
        // fe80:0:0:0:0:0:0:1%lo0 from java on mac
        boolean result;
        if (ipAddress != null) {
            result = true;
            String lowerCase = ipAddress.toLowerCase();
            for (String item : new String[]{"fe80:", "%"}) {
                if (!lowerCase.contains(item.toLowerCase())) {
                    result = false;
                    break;
                }
            }
        } else {
            result = false;
        }
        if (result) {
            ipAddress = null;
        }

        return ipAddress != null && !"".equals(ipAddress) ? ipAddress : null;
    }

    /**
     * Get local IP address
     */
    @NotNull
    public static String getLocalIPAddress(@NotNull String defaultIpAddress) {
        String ipAddress = getLocalIPAddress();
        return ipAddress != null && !"".equals(ipAddress) ? ipAddress : defaultIpAddress;
    }

    /**
     * Get local IPV4 address
     */
    @Nullable
    public static String getLocalIPV4Address() {
        String ipAddress = null;
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface networkInterface = en.nextElement();
                for (Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses(); inetAddresses.hasMoreElements(); ) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()) {
                        ipAddress = inetAddress.getHostAddress();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ipAddress != null && !"".equals(ipAddress) ? ipAddress : null;
    }

    /**
     * Get local IPV4 address
     */
    @NotNull
    public static String getLocalIPV4Address(@NotNull String defaultIpAddress) {
        String ipAddress = getLocalIPV4Address();
        return ipAddress != null && !"".equals(ipAddress) ? ipAddress : defaultIpAddress;
    }

    /**
     * Request the specified URL and then extract the external IP v4 address with the given regular expression
     */
    @Nullable
    public static String getExternalIPV4AddressFrom(@NotNull URL url, @NotNull Pattern pattern) {
        try {
            String urlContent = readText(url);
            Matcher matcher = pattern.matcher(urlContent);
            return matcher.find() ? matcher.group() : null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get the external IP v4 address from the API provided by Sohu
     */
    @Nullable
    public static String getExternalIPV4AddressFromSohu() {
        String ipv4 = null;
        try {
            ipv4 = getExternalIPV4AddressFrom(new URL("http://pv.sohu.com/cityjson?ie=utf-8"), IPV4);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (ipv4 == null || "".equals(ipv4)) {
            try {
                ipv4 = getExternalIPV4AddressFrom(new URL("http://txt.go.sohu.com/ip/soip"), IPV4);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return ipv4 != null && !"".equals(ipv4) ? ipv4 : null;
    }

    /**
     * Get external network IP v4 address
     */
    @Nullable
    public static String getExternalIPV4Address() {
        return getExternalIPV4AddressFromSohu();
    }

    /**
     * Get city info
     */
    @Nullable
    public static City getCityFromSohu() {
        String response;
        try {
            response = readText(new URL("http://pv.sohu.com/cityjson?ie=utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        String cipValue = null;
        final String cipKey = "\"cip\"";
        int cipIndex = response.indexOf(cipKey);
        if (cipIndex != -1) {
            int cipValueStartIndex = response.indexOf("\"", cipIndex + cipKey.length());
            if (cipValueStartIndex != -1) {
                int cipValueEndIndex = response.indexOf("\"", cipValueStartIndex + 1);
                if (cipValueEndIndex != -1) {
                    cipValue = response.substring(cipValueStartIndex + 1, cipValueEndIndex);
                }
            }
        }

        String cidValue = null;
        final String cidKey = "\"cid\"";
        int cidIndex = response.indexOf(cidKey);
        if (cidIndex != -1) {
            int cidValueStartIndex = response.indexOf("\"", cidIndex + cidKey.length());
            if (cidValueStartIndex != -1) {
                int cidValueEndIndex = response.indexOf("\"", cidValueStartIndex + 1);
                if (cidValueEndIndex != -1) {
                    cidValue = response.substring(cidValueStartIndex + 1, cidValueEndIndex);
                }
            }
        }

        String cnameValue = null;
        final String cnameKey = "\"cname\"";
        int cnameIndex = response.indexOf(cnameKey);
        if (cnameIndex != -1) {
            int cnameValueStartIndex = response.indexOf("\"", cnameIndex + cnameKey.length());
            if (cnameValueStartIndex != -1) {
                int cnameValueEndIndex = response.indexOf("\"", cnameValueStartIndex + 1);
                if (cnameValueEndIndex != -1) {
                    cnameValue = response.substring(cnameValueStartIndex + 1, cnameValueEndIndex);
                }
            }
        }

        return cipValue != null && cidValue != null && cnameValue != null ? new City(cidValue, cnameValue, cipValue) : null;
    }

    /**
     * Get city info
     */
    @Nullable
    public static City getCity() {
        return getCityFromSohu();
    }

    public static long ipStringToLong(String ipString) {
        long[] ip = new long[4];
        int position1 = ipString.indexOf(".");
        int position2 = ipString.indexOf(".", position1 + 1);
        int position3 = ipString.indexOf(".", position2 + 1);
        ip[0] = Long.parseLong(ipString.substring(0, position1));
        ip[1] = Long.parseLong(ipString.substring(position1 + 1, position2));
        ip[2] = Long.parseLong(ipString.substring(position2 + 1, position3));
        ip[3] = Long.parseLong(ipString.substring(position3 + 1));
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
    }

    public static String ipLongToString(long ipLong) {
        return (ipLong >>> 24) +
                "." +
                ((ipLong & 0x00FFFFFF) >>> 16) +
                "." +
                ((ipLong & 0x0000FFFF) >>> 8) +
                "." +
                (ipLong & 0x000000FF);
    }

    /**
     * Match MimeType
     *
     * @param template For example: application/*
     * @param mimeType For example: application/zip
     */
    public static boolean matchMimeType(@NotNull String template, @Nullable String mimeType) {
        String[] templateItems = template.split("/");
        String[] mimeItems = (mimeType != null ? mimeType : "").split("/");
        boolean result = true;
        if (templateItems.length > 0 && templateItems.length == mimeItems.length) {
            for (int index = 0; index < templateItems.length; index++) {
                String templateItem = templateItems[index].trim();
                String mimeItem = mimeItems[index].trim();
                result = "*".equals(templateItem) || templateItem.toLowerCase().equals(mimeItem.toLowerCase());
                if (!result) {
                    break;
                }
            }
        } else {
            result = false;
        }
        return result;
    }

    /**
     * Guess file name based on contentDisposition, contentLocation, url
     */
    @Nullable
    public static String guessFileName(@Nullable String contentDisposition, @Nullable String contentLocation, @Nullable String url) {
        String fileName = guessFileNameFromContentDisposition(contentDisposition);
        if (fileName != null && !"".equals(fileName)) return fileName;

        fileName = guessFileNameFromUrl(contentLocation);
        if (fileName != null && !"".equals(fileName)) return fileName;

        fileName = guessFileNameFromUrl(url);
        if (fileName != null && !"".equals(fileName)) return fileName;

        return null;
    }

    /**
     * Parse the Content-Disposition HTTP Header. The format of the header is
     * defined here: http://www.w3.org/Protocols/rfc2616/rfc2616-sec19.html This
     * header provides a filename for content that is going to be downloaded to
     * the file system. We only support the attachment type.
     */
    @Nullable
    public static String guessFileNameFromContentDisposition(@Nullable String contentDisposition) {
        /*
         * attachment; filename="test.zip"; filename*=utf-8' 'test.zip
         * attachment; filename=test.zip; filename*=utf-8' 'test.zip
         */

        if (contentDisposition == null || "".equals(contentDisposition)) {
            return null;
        }

        // parse 'filename="test.zip"', and support 'filename=test.zip'
        String fileName = null;
        String[] items = contentDisposition.split(";");
        final String startFlag = "filename=";
        String fileNameItem = null;
        for (String element : items) {
            element = element.trim();
            if (element.startsWith(startFlag)) {
                fileNameItem = element;
                break;
            }
        }
        fileNameItem = fileNameItem != null ? fileNameItem : "";

        if (fileNameItem.length() > startFlag.length()) {
            int startIndex = fileNameItem.charAt(startFlag.length()) == '"' ? startFlag.length() : startFlag.length() - 1;
            int endIndex = fileNameItem.charAt(fileNameItem.length() - 1) == '"' ? fileNameItem.length() - 1 : fileNameItem.length();
            fileName = fileNameItem.substring(startIndex + 1, endIndex);
        }

        // It may be with a partial path, for example: 'storage/games/test.zip'
        if (fileName != null && !"".equals(fileName)) {
            int lastPathSeparatorIndex = fileName.lastIndexOf('/');
            if (lastPathSeparatorIndex >= 0) {
                fileName = fileName.substring(lastPathSeparatorIndex + 1);
            }
        }

        return fileName != null && !"".equals(fileName) ? fileName : null;
    }

    /**
     * Guess file name from url
     */
    @Nullable
    public static String guessFileNameFromUrl(@Nullable final String url) {
        /*
         * http://bing.com/dl/sample.apk
         */

        if (url == null || "".equals(url)) {
            return null;
        }

        String decodedUrl;
        try {
            decodedUrl = URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        // Intercept the contents before '?'
        final String finalUrl;
        final int paramsStartIndex = decodedUrl.indexOf('?');
        if (paramsStartIndex >= 0) {
            finalUrl = decodedUrl.substring(0, paramsStartIndex);
        } else {
            finalUrl = decodedUrl;
        }

        final int index = finalUrl.lastIndexOf('/');
        String fileName;
        if (index == finalUrl.length() - 1) {
            fileName = "";
        } else if (index >= 0) {
            fileName = finalUrl.substring(index + 1);
        } else {
            fileName = finalUrl;
        }

        int dotIndex = fileName.indexOf(".");
        if (!"".equals(fileName) && dotIndex > -1 && dotIndex < fileName.length() - 1) {
            return fileName;
        } else {
            return null;
        }
    }

    /**
     * Reads the entire content of this URL as a String using UTF-8.
     * This method is not recommended on huge files.
     *
     * @return a string with this URL entire content.
     */
    @NotNull
    private static String readText(@NotNull URL url) throws IOException {
        InputStream inputStream = url.openStream();
        byte[] urlContentBytes;
        try {
            ByteArrayOutputStream bufferOutputStream = new ByteArrayOutputStream(Math.max(1024 * 8, inputStream.available()));
            byte[] buffer = new byte[1024 * 8];
            int bytes;
            while (true) {
                bytes = inputStream.read(buffer);
                if (bytes < 0) break;
                bufferOutputStream.write(buffer, 0, bytes);
            }
            urlContentBytes = bufferOutputStream.toByteArray();
        } finally {
            inputStream.close();
        }
        return new String(urlContentBytes, StandardCharsets.UTF_8);
    }

    public static class City {

        @NotNull
        private final String id;
        @NotNull
        private final String name;
        @NotNull
        private final String ip;

        public City(@NotNull String id, @NotNull String name, @NotNull String ip) {
            this.id = id;
            this.name = name;
            this.ip = ip;
        }

        @NotNull
        public String getId() {
            return id;
        }

        @NotNull
        public String getName() {
            return name;
        }

        @NotNull
        public String getIp() {
            return ip;
        }
    }
}
