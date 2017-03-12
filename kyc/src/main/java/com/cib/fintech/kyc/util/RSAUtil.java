package com.cib.fintech.kyc.util;
import com.cib.fintech.kyc.api.blockchain.User;
import com.cib.fintech.kyc.api.blockchain.User.UserInfo;
import com.google.common.collect.Maps;  
import com.google.gson.Gson;

import sun.misc.BASE64Decoder;  
import sun.misc.BASE64Encoder;  
  



import javax.crypto.Cipher;  

import java.io.UnsupportedEncodingException;
import java.security.*;  
import java.security.interfaces.RSAPrivateKey;  
import java.security.interfaces.RSAPublicKey;  
import java.security.spec.PKCS8EncodedKeySpec;  
import java.security.spec.X509EncodedKeySpec;  
import java.util.Map;  
  
/** 
 * Created by xiang.li on 2015/3/3. 
 * RSA 加解密工具类 
 */  
public class RSAUtil {  
    /** 
     * 定义加密方式 
     */  
    private final static String KEY_RSA = "RSA";  
    /** 
     * 定义签名算法 
     */  
    private final static String KEY_RSA_SIGNATURE = "MD5withRSA";  
    /** 
     * 定义公钥算法 
     */  
    private final static String KEY_RSA_PUBLICKEY = "RSAPublicKey";  
    /** 
     * 定义私钥算法 
     */  
    private final static String KEY_RSA_PRIVATEKEY = "RSAPrivateKey";  
  
    /** 
     * 初始化密钥 
     * @return 
     */  
    public static Map<String, Object> init() {  
        Map<String, Object> map = null;  
        try {  
            KeyPairGenerator generator = KeyPairGenerator.getInstance(KEY_RSA);  
            generator.initialize(1024);  
            KeyPair keyPair = generator.generateKeyPair();  
            // 公钥  
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  
            // 私钥  
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();  
            // 将密钥封装为map  
            map = Maps.newHashMap();  
            map.put(KEY_RSA_PUBLICKEY, publicKey);  
            map.put(KEY_RSA_PRIVATEKEY, privateKey);  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
        return map;  
    }  
  
    /** 
     * 用私钥对信息生成数字签名 
     * @param data 加密数据 
     * @param privateKey 私钥 
     * @return 
     */  
    public static String sign(byte[] data, String privateKey) {  
        String str = "";  
        try {  
            // 解密由base64编码的私钥  
            byte[] bytes = decryptBase64(privateKey);  
            // 构造PKCS8EncodedKeySpec对象  
            PKCS8EncodedKeySpec pkcs = new PKCS8EncodedKeySpec(bytes);  
            // 指定的加密算法  
            KeyFactory factory = KeyFactory.getInstance(KEY_RSA);  
            // 取私钥对象  
            PrivateKey key = factory.generatePrivate(pkcs);  
            // 用私钥对信息生成数字签名  
            Signature signature = Signature.getInstance(KEY_RSA_SIGNATURE);  
            signature.initSign(key);  
            signature.update(data);  
            str = encryptBase64(signature.sign());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return str;  
    }  
  
    /** 
     * 校验数字签名 
     * @param data 加密数据 
     * @param publicKey 公钥 
     * @param sign 数字签名 
     * @return 校验成功返回true，失败返回false 
     */  
    public static boolean verify(byte[] data, String publicKey, String sign) {  
        boolean flag = false;  
        try {  
            // 解密由base64编码的公钥  
            byte[] bytes = decryptBase64(publicKey);  
            // 构造X509EncodedKeySpec对象  
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);  
            // 指定的加密算法  
            KeyFactory factory = KeyFactory.getInstance(KEY_RSA);  
            // 取公钥对象  
            PublicKey key = factory.generatePublic(keySpec);  
            // 用公钥验证数字签名  
            Signature signature = Signature.getInstance(KEY_RSA_SIGNATURE);  
            signature.initVerify(key);  
            signature.update(data);  
            flag = signature.verify(decryptBase64(sign));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return flag;  
    }  
  
    /** 
     * 私钥解密 
     * @param data 加密数据 
     * @param key 私钥 
     * @return 
     */  
    public static byte[] decryptByPrivateKey(byte[] data, String key) {  
        byte[] result = null;  
        try {  
            // 对私钥解密  
            byte[] bytes = decryptBase64(key);  
            // 取得私钥  
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);  
            KeyFactory factory = KeyFactory.getInstance(KEY_RSA);  
            PrivateKey privateKey = factory.generatePrivate(keySpec);  
            // 对数据解密  
            Cipher cipher = Cipher.getInstance(factory.getAlgorithm());  
            cipher.init(Cipher.DECRYPT_MODE, privateKey);  
            result = cipher.doFinal(data);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
  
    /** 
     * 私钥解密 
     * @param data 加密数据 
     * @param key 公钥 
     * @return 
     */  
    public static byte[] decryptByPublicKey(byte[] data, String key) {  
        byte[] result = null;  
        try {  
            // 对公钥解密  
            byte[] bytes = decryptBase64(key);  
            // 取得公钥  
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);  
            KeyFactory factory = KeyFactory.getInstance(KEY_RSA);  
            PublicKey publicKey = factory.generatePublic(keySpec);  
            // 对数据解密  
            Cipher cipher = Cipher.getInstance(factory.getAlgorithm());  
            cipher.init(Cipher.DECRYPT_MODE, publicKey);  
            result = cipher.doFinal(data);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
  
    /** 
     * 公钥加密 
     * @param data 待加密数据 
     * @param key 公钥 
     * @return 
     */  
    public static byte[] encryptByPublicKey(byte[] data, String key) {  
        byte[] result = null;  
        try {  
            byte[] bytes = decryptBase64(key);  
            // 取得公钥  
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);  
            KeyFactory factory = KeyFactory.getInstance(KEY_RSA);  
            PublicKey publicKey = factory.generatePublic(keySpec);  
            // 对数据加密  
            Cipher cipher = Cipher.getInstance(factory.getAlgorithm());  
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
            result = cipher.doFinal(data);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
  
    /** 
     * 私钥加密 
     * @param data 待加密数据 
     * @param key 私钥 
     * @return 
     */  
    public static byte[] encryptByPrivateKey(byte[] data, String key) {  
        byte[] result = null;  
        try {  
            byte[] bytes = decryptBase64(key);  
            // 取得私钥  
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);  
            KeyFactory factory = KeyFactory.getInstance(KEY_RSA);  
            PrivateKey privateKey = factory.generatePrivate(keySpec);  
            // 对数据加密  
            Cipher cipher = Cipher.getInstance(factory.getAlgorithm());  
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);  
            result = cipher.doFinal(data);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
  
    /** 
     * 获取公钥 
     * @param map 
     * @return 
     */  
    public static String getPublicKey(Map<String, Object> map) {  
        String str = "";  
        try {  
            Key key = (Key) map.get(KEY_RSA_PUBLICKEY);  
            str = encryptBase64(key.getEncoded());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return str;  
    }  
  
    /** 
     * 获取私钥 
     * @param map 
     * @return 
     */  
    public static String getPrivateKey(Map<String, Object> map) {  
        String str = "";  
        try {  
            Key key = (Key) map.get(KEY_RSA_PRIVATEKEY);  
            str = encryptBase64(key.getEncoded());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return str;  
    }  
  
    /** 
     * BASE64 解密 
     * @param key 需要解密的字符串 
     * @return 字节数组 
     * @throws Exception 
     */  
    public static byte[] decryptBase64(String key) throws Exception {  
        return (new BASE64Decoder()).decodeBuffer(key);  
    }  
  
    /** 
     * BASE64 加密 
     * @param key 需要加密的字节数组 
     * @return 字符串 
     * @throws Exception 
     */  
    public static String encryptBase64(byte[] key) throws Exception {  
        return (new BASE64Encoder()).encodeBuffer(key);  
    }  
  
    /** 
     * 测试方法 
     * @param args 
     * @throws UnsupportedEncodingException 
     */  
    public static void main(String[] args) throws UnsupportedEncodingException {  
        /*String privateKey = "";  
        String publicKey = "";  
        // 生成公钥私钥  
        Map<String, Object> map = init();  
        publicKey = getPublicKey(map);  
        privateKey = getPrivateKey(map);*/
        
    	String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhK2ZztbnGRz1+IxKMSrThqZCvvMUoiOrm06x7r1Enki44+COSDefgzbltZuLJyi1D0LSqwVzBeE7s2Yr5xfrwJe9Kp3Hw7lf2BE06KH+Hoo9uNC/ivnDjgLwfJGgCi2Tz/eNLNQbbbQXGWXRt3iNbSPDDYDyilDIDYMBxV6zE1/ND1n5TJmT8dAdAuzyaqP9VSLrMT5/ewVfCztW7s8Zupp2C8vHcs7xDPlGQWeM7lH8lyYc+AjDCTRv1RPjnqRWtmCYokwdj0n93wuj4McxqoHIPd6sjRXbkTR4+0w4+eskYEWMDHa4n60bCksl4I/jVvv5CWPyiI8Dc+KFzPUqEQIDAQAB";
    	String privateKey="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCErZnO1ucZHPX4jEoxKtOGpkK+8xSiI6ubTrHuvUSeSLjj4I5IN5+DNuW1m4snKLUPQtKrBXMF4TuzZivnF+vAl70qncfDuV/YETToof4eij240L+K+cOOAvB8kaAKLZPP940s1BtttBcZZdG3eI1tI8MNgPKKUMgNgwHFXrMTX80PWflMmZPx0B0C7PJqo/1VIusxPn97BV8LO1buzxm6mnYLy8dyzvEM+UZBZ4zuUfyXJhz4CMMJNG/VE+OepFa2YJiiTB2PSf3fC6PgxzGqgcg93qyNFduRNHj7TDj56yRgRYwMdrifrRsKSyXgj+NW+/kJY/KIjwNz4oXM9SoRAgMBAAECggEAGUWDzW0LmbH1fAEkxJa2sagLgJmGbZfDwRJNO/8bCdcqOGV2VHUb0f9r45qb97p+OkjtHZwtG4qaN4KXjC33PrjoVCy3dyVhByPH4yOv5ptIRGKmiU8nm24d5mxKgoXJ6MvrPQWtr8ikuba2Km8w6P/t0C2mhQf8Au7YMILoMyE712rU6rsnP4wQ/x2alhv7Tq3QniIE+CxsmQ1sJUlIRsDzd4kHYFer/RwxKiGDKl7K44NNMpoWuFiMUdFZgV5k4Q4OHrRtbrrDBkQnOKVcIXJ1X8ujRAVnrtX1sH5QuSJ1KmWMePV7LYHVEkSp0/f0sJbUECoW4b4ImgFrGV1V0QKBgQD5dvBMFqEwuPVCHuAH0y5eDRudG2qNywL0CBHgL5iVKveFetrK04C+TGM2OtFHqQPZnI94ZgTceACe1XKoiaki9wfmBdDKqbJ/DOT/E93W+9cCcbvTn5vNYpvz0ETKb1iaR1/U9XGmGkQLwLYyLPXt0W6dOF8E82pbQT43mo8K9QKBgQCIJ2vINUrEqUXQJBWTqiFogwG4hbS1XrAFoc+DHoEdLhimhdDfjhPttOnaKlbAZwqjzZxZmHLnPCvTPzYHh+jXraZGhOLtRusR8aW93X2Rv/vyUJip+LjLjAQ8xBROaq3PDDqz3BoCEvSlIvNOid9NogbqgrJNAl61MvLzeXMpLQKBgQCHB9xWM0GY7+TBn5jAY40Ll6jEx5xLmAHHOb8DTIAY7iMA+1kGxKlBWHOzXO7/DPtqhRYbo40qEUK0tvDTTYBhzrlVxvwXVvepUb96WrSPs83UIt/6UsCw0Zl8xdUWhmK9T7kNurWDpWADxJJaYn/T9LMdIvA76848Cvq0elLHKQKBgF4y1/E2XbpeWry8mmkFhgOmz+P4LaRmFGZxIv7WdgHYSJm9b1ucEtGdH0YfkNGg+M2Hjaq3PJgbGz8WNq1TLJc7vxR/D/mV3UEW7NLFkQDr0MY/WMAQItpAzxWzm1D9oa7kCSEzIJNaxgQSgD9QSDyP4zUQo4eAIkI/M7ObzT8pAoGAZ/dCrNtTuXX0QUIjKjdDaW3q7g1KsLGRX3QbX/q9OD24Zd0B5GVb2CEhk7WnGewlSFsoE/kLKGTxavZD8dRyMrNO3Ek9upcdcjw/ZtnquMURVzON3drVyShSE0sTezbiz6+KrUz+5BX4X+Vu2R5Zo40q8vphkFwyzj9drwPPuQQ=";

        System.out.println("公钥: \n\r" + publicKey);  
        System.out.println("私钥： \n\r" + privateKey);  
        System.out.println("公钥加密--------私钥解密");  
        //String word = "你好，世界！";
        User.UserInfo userInfo = new User().new UserInfo();
		Gson gson = new Gson();
		String word = gson.toJson(userInfo);
        byte[] encWord = encryptByPublicKey(word.getBytes(), publicKey);
        System.out.println("加密后密文: "+BASE64Util.encode(encWord));
        String decWord = new String(decryptByPrivateKey(encWord, privateKey));  
        System.out.println("加密前: " + word + "\n\r" + "解密后: " + decWord);  
        System.out.println("私钥加密--------公钥解密");  
        //String english = "Hello, World!";  
        String english = word;
        byte[] encEnglish = encryptByPrivateKey(english.getBytes(), privateKey);  
        String decEnglish = new String(decryptByPublicKey(encEnglish, publicKey));  
        System.out.println("加密前: " + english + "\n\r" + "解密后: " + decEnglish);  
        System.out.println("私钥签名——公钥验证签名");  
        // 产生签名  
        String sign = sign(encEnglish, privateKey);  
        System.out.println("签名:\r" + sign);  
        // 验证签名  
        boolean status = verify(encEnglish, publicKey, sign);  
        System.out.println("状态:\r" + status);
    }
}