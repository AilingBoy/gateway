package com.example.gateway.utils;


import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 滕楠
 */
@Component
public class RsaUtil {

    private static Map<String, Object> keys = new HashMap<>(2);

    private static final String PRIVATE_KEY = "privateKey";
    private static final String PUBLIC_KEY = "publicKey";


    /*
      初始化rsa公钥私钥
     */
    static {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen;
        try {
            keyPairGen = KeyPairGenerator.getInstance("RSA");
            // 初始化密钥对生成器，密钥大小为96-1024位
            keyPairGen.initialize(1024, new SecureRandom());
            // 生成一个密钥对，保存在keyPair中
            KeyPair keyPair = keyPairGen.generateKeyPair();
            keys.put(PRIVATE_KEY, keyPair.getPrivate());
            keys.put(PUBLIC_KEY, keyPair.getPublic());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重新生成rsa公钥私钥
     */
    public static void genKeyPair() throws Exception {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        //存入map
        keys.put(PRIVATE_KEY, keyPair.getPrivate());
        keys.put(PUBLIC_KEY, keyPair.getPublic());
    }

    /**
     * 获取公钥
     * @return 公钥字符串
     */
    public static String getPublicKey() {
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keys.get(PUBLIC_KEY);
        byte[] encoded = publicKey.getEncoded();
        // 得到公钥字符串
        return new String(Base64.encodeBase64(encoded));
    }

    /**
     * 获取私钥
     * @return 私钥字符串
     */
    public static String getPrivateKey() {
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keys.get(PRIVATE_KEY);
        byte[] encoded = privateKey.getEncoded();
        // 得到私钥字符串
        return new String(Base64.encodeBase64(encoded));
    }

    /**
     * RSA公钥加密
     *
     * @param str       加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String encrypt(String str, String publicKey) throws Exception {
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return Base64.encodeBase64String(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * RSA私钥解密
     *
     * @param str        加密字符串
     * @param privateKey 私钥
     * @return 铭文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decrypt(String str, String privateKey) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes(StandardCharsets.UTF_8));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(inputByte));
    }

    public static void main(String[] args) throws Exception {

        String privateKey = RsaUtil.getPrivateKey();
        System.out.println("私钥:  " + privateKey);

        String publicKey = RsaUtil.getPublicKey();
        System.out.println("公钥:  " + publicKey);

        String data = "hello world";

        String encrypt = RsaUtil.encrypt(data, publicKey);
        System.out.println("加密后的密文:  " + encrypt);

        String decrypt = RsaUtil.decrypt(encrypt, privateKey);
        System.out.println("解密后的:  " + decrypt);
    }
}