package com.SXSQ.project;

import com.SXSQ.project.utils.IpUtil;

public class Main {
    public static void main(String[] args) {
        String ip = "192.168.23.256";
        System.out.println( IpUtil.isValidIPV4ByCustomRegex(ip));
    }
}
