package com.SXSQ.project.utils;

import java.util.regex.Pattern;

public class IpUtil {
    private static final String IPV4_REGEX =
            "^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$";

    private static final Pattern IPv4_PATTERN = Pattern.compile(IPV4_REGEX);

    public static boolean isValidIPV4ByCustomRegex(String ip) {
        if (ip == null || ip.trim().isEmpty()) {
            return false;
        }
        if (!IPv4_PATTERN.matcher(ip).matches()) {
            return false;
        }
        String[] parts = ip.split("\\.");
        try {
            for (String segment : parts) {
                if (Integer.parseInt(segment) > 255 ||
                        (segment.length() > 1 && segment.startsWith("0"))) {
                    return false;
                }
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
