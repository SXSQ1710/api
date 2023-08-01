package com.sxsq.sxinterface.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

import java.util.Map;

/**
 * @title: SignUtils
 * @Author SXSQ
 * @Description //TODO
 * @Date 2023/8/1 22:18
 **/

public class SignUtils {

    public static String getSign(String body, String secretKey) {
        Digester md5 = new Digester(DigestAlgorithm.SHA256);
        return md5.digestHex(body + '.' + secretKey);
    }
}
