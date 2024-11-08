package com.bj.dfmanager.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {

    private static final int KEY_LENGTH = 16;
    private static final String PASSWORD = "dfmanager";
    private static final String DEFAULT_V = "0";
    private static final String PK = "AES/ECB/PKCS5Padding";

    /**
     * 加密
     *
     * @param password 密钥
     * @param content  待加密文本
     */
    public static String encrypt(String content, String password) {
        byte[] rawKey = toMakekey(password, KEY_LENGTH, DEFAULT_V).getBytes();
        byte[] result;
        try {
            result = encrypt(rawKey, content.getBytes("utf-8"));
            return StringtoBase64(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param password 密钥
     * @param content  待解密文本
     */
    public static String decrypt(String content, String password) {
        byte[] rawKey = toMakekey(password, KEY_LENGTH, DEFAULT_V).getBytes();
        byte[] enc = Base64toByte(content);
        byte[] result;
        try {
            result = decrypt(rawKey, enc);
            return new String(result, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密(密钥写死)
     *
     * @param content 待加密文本
     */
    public static String encryptN(String content) {
        byte[] rawKey = toMakekey(PASSWORD, KEY_LENGTH, DEFAULT_V).getBytes();
        byte[] result;
        try {
            result = encrypt(rawKey, content.getBytes("utf-8"));
            return StringtoBase64(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密(密钥写死)
     *
     * @param content 待解密文本
     */
    public static String decryptN(String content) {
        byte[] rawKey = toMakekey(PASSWORD, KEY_LENGTH, DEFAULT_V).getBytes();
        byte[] enc = Base64toByte(content);
        byte[] result;
        try {
            result = decrypt(rawKey, enc);
            return new String(result, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 密钥key ,默认补的数字，补全16位数，以保证安全补全至少16位长度,android和ios对接通过
     */
    private static String toMakekey(String str, int strLength, String val) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer buffer = new StringBuffer();
                buffer.append(str).append(val);
                str = buffer.toString();
                strLen = str.length();
            }
        }
        return str;
    }

    /**
     * 真正的加密过程 1.通过密钥得到一个密钥专用的对象SecretKeySpec 2.Cipher 加密算法，加密模式和填充方式三部分或指定加密算
     * (可以只用写算法然后用默认的其他方式)Cipher.getInstance("AES");
     */
    private static byte[] encrypt(byte[] key, byte[] src) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance(PK);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(src);
        return encrypted;
    }

    /**
     * 真正的解密过程
     */
    private static byte[] decrypt(byte[] key, byte[] encrypted) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance(PK);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }

    public static byte[] Base64toByte(String hexString) {
        return Base64.decodeBase64(hexString);
    }

    public static String StringtoBase64(byte[] buf) {
        return encodeBase64String(buf);
    }

    public static String encodeBase64String(final byte[] binaryData) {
        return StringUtils.newStringUtf8(Base64.encodeBase64(binaryData, false, true));
    }

    public static void main(String[] args) {
        String context = "admin";
        String jiami = encryptN(context);
        System.out.println(jiami);
        String jiemi = decryptN(jiami);
        System.out.println(jiemi);
    }

}