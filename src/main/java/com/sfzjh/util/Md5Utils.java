package com.sfzjh.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author  孙飞
 * @Date  2021年03月09日 11:58
 * @PackageName  com.sfzjh.util
 * @Name  MD5Utils
 * @Version  1.0
 * @Description  TODO
 * Created with IntelliJ IDEA.
 */
public class Md5Utils {

    /**
     * MD5加密
     * @param string 要加密的字符串
     * @return        加密后的字符串
     */
    public static String code(String string){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(string.getBytes());
            byte[] byteDigest = messageDigest.digest();
            int i;
            StringBuilder buffer = new StringBuilder();
            for (byte b : byteDigest) {
                i = b;
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buffer.append("0");
                }
                buffer.append(Integer.toHexString(i));
            }
            //32位加密
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // public static void main(String[] args) {
    //     System.out.println(Md5Utils.code("123456"));
    // }
}
