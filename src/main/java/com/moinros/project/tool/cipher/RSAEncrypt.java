package com.moinros.project.tool.cipher;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

/**
 * 类注释: RSA加密算法工具类
 *
 * @Title: RSAEncrypt
 * @Author moinros
 * @Blog https://www.moinros.com
 * @Date 2019年11月13日 下午6:13:10
 * @Version 1.0
 */
public class RSAEncrypt {

    /**
     * CHARSET : 编码格式
     */
    public static final String CHARSET = "UTF-8";
    /**
     * RSA_ALGORITHM : 加密方式[RSA]
     */
    public static final String RSA_ALGORITHM = "RSA";
    /**
     * keySize : 密钥长度
     */
    private static final int KEY_SIZE = 2048;
    /**
     * ENCODER : 使用Base64编码
     */
    private Encoder ENCODER;
    /**
     * DECODER : 使用Base64解码
     */
    private Decoder DECODER;

    /**
     * 注释: 使用Base64对密钥串进行编码
     */
    public String encoder(String key) {
        return encoder(key.getBytes());
    }

    /**
     * 注释: 使用Base64对密钥串进行编码
     */
    public String encoder(byte[] key) {
        newBase64Object();
        return ENCODER.encodeToString(key);
    }

    /**
     * 注释: 使用Base64对密钥进行解码
     */
    public byte[] decoder(String key) {
        return decoder(key.getBytes());
    }

    /**
     * 注释: 使用Base64对密钥进行解码
     */
    public byte[] decoder(byte[] key) {
        newBase64Object();
        return DECODER.decode(key);
    }

    private void newBase64Object() {
        if (ENCODER == null) {
            ENCODER = Base64.getEncoder();
        }
        if (DECODER == null) {
            DECODER = Base64.getDecoder();
        }
    }

    public RSAEncrypt() {

    }

    /**
     * 注释:创建密匙对
     *
     * @return EncryptKey 封装秘钥的实体类
     * @throws NoSuchAlgorithmException
     */
    public EncryptKey createKeyPair() throws NoSuchAlgorithmException {
        // 为RSA算法创建一个KeyPairGenerator对象
        KeyPairGenerator kpg;
        try {
            kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm -->[" + RSA_ALGORITHM + "]");
        }

        // 初始化KeyPairGenerator对象,密钥长度
        kpg.initialize(KEY_SIZE);
        // 生成密匙对
        KeyPair keyPair = kpg.generateKeyPair();

        // 得到公钥
        PublicKey publicKey = keyPair.getPublic();

        // 得到私钥
        PrivateKey privateKey = keyPair.getPrivate();
        EncryptKey ek = new EncryptKey(KEY_SIZE, publicKey.getEncoded(), privateKey.getEncoded());
        ek.setPublicKey(encoder(publicKey.getEncoded()));
        ek.setPrivateKey(encoder(privateKey.getEncoded()));
        return ek;
    }

    /**
     * 注释: RSA公钥加密
     *
     * @param code      使用Base64编码后的加密字符串
     * @param publicKey 公钥
     * @return String 使用Base64编码后的密文,如过加密失败则返回null
     * @throws UnsupportedEncodingException 加密过程中的异常信息
     */
    public String encrypt(String code, String publicKey) throws UnsupportedEncodingException {
        // Base64编码的公钥
        byte[] decoded = decoder(publicKey);
        byte[] key = code.getBytes(CHARSET);
        try {
            byte[] value = encrypt(key, decoded);
            return encoder(value);
        } catch (InvalidKeyException | InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException
                | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 注释: RSA公钥加密
     *
     * @param code       二进制格式的加密字符串
     * @param privateKey 二进制格式的公钥
     * @return byte[] 二进制格式的密文
     */
    private byte[] encrypt(byte[] code, byte[] privateKey) throws InvalidKeySpecException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance(RSA_ALGORITHM)
                .generatePublic(new X509EncodedKeySpec(privateKey));
        // RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] key = cipher.doFinal(code);
        return key;
    }

    /**
     * 注释: RSA私钥解密
     *
     * @param code       使用Base64编码后的加密字符串
     * @param privateKey 使用Base64编码后的私钥
     * @return String 密码明文
     * @throws UnsupportedEncodingException 加密过程中的异常信息
     */
    public String decrypt(String code, String privateKey) throws UnsupportedEncodingException {
        // 64位编码加密后的字符串
        byte[] inputByte = decoder(code.getBytes(CHARSET));
        // base64编码的私钥
        byte[] decoded = decoder(privateKey);
        // RSA私钥解密
        try {
            byte[] value = decrypt(inputByte, decoded);
            return new String(value);
        } catch (InvalidKeyException | InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 注释: RSA私钥解密
     *
     * @param code       二进制格式的加密字符串
     * @param privateKey 二进制格式的私钥
     * @return byte[] 二进制格式的密码明文
     */
    private byte[] decrypt(byte[] code, byte[] privateKey) throws InvalidKeySpecException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance(RSA_ALGORITHM)
                .generatePrivate(new PKCS8EncodedKeySpec(privateKey));
        // RSA解密
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return cipher.doFinal(code);
    }

    /**
     * 类注释: 存储密匙的实体
     */
    public class EncryptKey {

        private int modulus; // 模数
        private long timeLimit; // 密匙生成时间
        private String publicKey; // 公钥
        private String privateKey; // 私钥
        private String publicKeyCheckcode; // 公钥效验码
        private String privateKeyCheckcode; // 私钥效验码
        private byte[] publicKeyByte; // 二进制格式的公钥
        private byte[] privateKeyByte; // 二进制格式的私钥

        public EncryptKey() {
        }

        public EncryptKey(int modulus, String publicKey, String privateKey) {
            this.modulus = modulus;
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        public EncryptKey(String publicKey, String privateKey) {
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        public EncryptKey(byte[] publicKey, byte[] privateKey) {
            this.publicKeyByte = publicKey;
            this.privateKeyByte = privateKey;
        }

        public EncryptKey(int modulus, byte[] publicKey, byte[] privateKey) {
            this.modulus = modulus;
            this.publicKeyByte = publicKey;
            this.privateKeyByte = privateKey;
        }

        public int getModulus() {
            return modulus;
        }

        public void setModulus(int modulus) {
            this.modulus = modulus;
        }

        public long getTimeLimit() {
            return timeLimit;
        }

        public void setTimeLimit(long timeLimit) {
            this.timeLimit = timeLimit;
        }

        public String getPublicKeyCheckcode() {
            return publicKeyCheckcode;
        }

        public void setPublicKeyCheckcode(String publicKeyCheckcode) {
            this.publicKeyCheckcode = publicKeyCheckcode;
        }

        public String getPrivateKeyCheckcode() {
            return privateKeyCheckcode;
        }

        public void setPrivateKeyCheckcode(String privateKeyCheckcode) {
            this.privateKeyCheckcode = privateKeyCheckcode;
        }

        public String getPublicKey() {
            return publicKey;
        }

        public void setPublicKey(String publicKey) {
            this.publicKey = publicKey;
        }

        public String getPrivateKey() {
            return privateKey;
        }

        public void setPrivateKey(String privateKey) {
            this.privateKey = privateKey;
        }

        public byte[] getPublicKeyByte() {
            return publicKeyByte;
        }

        public void setPublicKeyByte(byte[] publicKeyByte) {
            this.publicKeyByte = publicKeyByte;
        }

        public byte[] getPrivateKeyByte() {
            return privateKeyByte;
        }

        public void setPrivateKeyByte(byte[] privateKeyByte) {
            this.privateKeyByte = privateKeyByte;
        }
    }
}
