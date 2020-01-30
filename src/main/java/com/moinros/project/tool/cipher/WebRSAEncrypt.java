package com.moinros.project.tool.cipher;

import com.moinros.project.result.sub.StrConst;
import com.moinros.project.tool.cipher.RSAEncrypt.EncryptKey;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class WebRSAEncrypt {

    /**
     * 注释: 获取RSA公钥
     */
    public String getPublicKey(HttpSession session) {
        return getPublicKey(session, StrConst.ENCRYPT_KEY_NAME);
    }

    /**
     * 注释: 获取RSA公钥
     *
     * @param session     HttpSession
     * @param sessionName sessionName
     * @return PublicKey
     */
    public String getPublicKey(HttpSession session, String sessionName) {
        RSAEncrypt rsa = new RSAEncrypt();
        EncryptKey ek = null;
        try {
            ek = rsa.createKeyPair();
            ek.setTimeLimit(System.currentTimeMillis());
            ek.setPublicKeyCheckcode(DigestUtils.md5Hex(ek.getPublicKey()));
            session.setAttribute(sessionName, ek);
            return ek.getPublicKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 注释: REA私钥解密
     *
     * @param password    加密后的密码
     * @param session
     * @param sessionName sessionName
     * @return String 解密后的密码
     */
    public String decrypt(String password, HttpSession session, String sessionName) {
        EncryptKey ek = (EncryptKey) session.getAttribute(StrConst.ENCRYPT_KEY_NAME);
        // 移除session中的值
        session.setAttribute(sessionName, null);
        if (ek != null) {
            RSAEncrypt rsa = new RSAEncrypt();
            String key = null;
            try {
                key = rsa.decrypt(password, ek.getPrivateKey());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return new String(rsa.decoder(key));
        }
        return null;
    }

    /**
     * 注释: REA私钥解密
     *
     * @param password 加密后的密码
     * @param session
     * @return String 解密后的密码
     */
    public String decrypt(String password, HttpSession session) {
        return decrypt(password, session, StrConst.ENCRYPT_KEY_NAME);
    }
}
