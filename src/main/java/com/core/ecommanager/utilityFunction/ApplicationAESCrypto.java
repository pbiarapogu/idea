package com.core.ecommanager.utilityFunction;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * Created by PGangal on 8/28/2014.
 *
 * Merged FileCryptoService into ApplicationAESCrypto: 11/20/2018 by BSlocum
 */
public class ApplicationAESCrypto {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationAESCrypto.class);
    private static final String AES_PROPS_FILE = "ApplicationAESCrypto.properties";
    private static final String AES_PW_PROP = "aes.crypto.pw";
    private static final String AES_SALT_PROP = "aes.crypto.salt";
    private static final String AES_IV_PROP = "aes.crypto.iv";
    private static final String AES_ENCODING_PROP = "aes.crypto.encoding";
    private static final String AES_ALGORITHM_PROP = "aes.crypto.algorithm";
    private static final String CIPHER_TRANSFORMATION = "AES/CBC/PKCS5Padding";

    private byte[] ivBytes;
    private SecretKeySpec secret;
    private String encoding;

    public ApplicationAESCrypto() {
        try {
            PropertyHandler propertyHandler = new PropertyHandler();
            Properties properties = propertyHandler.getProperties(AES_PROPS_FILE);
            encoding = properties.getProperty(AES_ENCODING_PROP);
            String pw = properties.getProperty(AES_PW_PROP);
            String salt = properties.getProperty(AES_SALT_PROP);
            String ivString = properties.getProperty(AES_IV_PROP);
            byte[] saltBytes = salt.getBytes(encoding);
            ivBytes = ivString.getBytes(encoding);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(properties.getProperty(AES_ALGORITHM_PROP));
            // Derive the key
            int pswdIterations = 65536;
            int keySize = 128;
            PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), saltBytes, pswdIterations, keySize);
            SecretKey secretKey = factory.generateSecret(spec);
            secret = new SecretKeySpec(secretKey.getEncoded(), "AES");
        } catch (GeneralSecurityException | IOException e) {
            logger.error("Error initializing cryptography.", e);
        }
    }

    public String encrypt(String plainText) throws GeneralSecurityException, IOException {
        Cipher cipherEncrypt = Cipher.getInstance(CIPHER_TRANSFORMATION);
        cipherEncrypt.init(Cipher.ENCRYPT_MODE, secret, new IvParameterSpec(ivBytes));

        byte[] encryptedTextBytes = cipherEncrypt.doFinal(plainText.getBytes(encoding));
        return new org.apache.commons.codec.binary.Base64().encodeAsString(encryptedTextBytes);
    }

    public String decrypt(String encryptedText) throws GeneralSecurityException {
        byte[] encryptedTextBytes = new org.apache.commons.codec.binary.Base64().decode(encryptedText);
        Cipher cipherDecrypt = Cipher.getInstance(CIPHER_TRANSFORMATION);
        cipherDecrypt.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(ivBytes));
        byte[] decryptedTextBytes = cipherDecrypt.doFinal(encryptedTextBytes);

        return new String(decryptedTextBytes);
    }

    public void encryptFile(File plainFile) throws ServiceException {
        try {
            String content = FileUtils.readFileToString(plainFile, encoding);
            String encryptedContent = encrypt(content);
            FileUtils.writeStringToFile(plainFile, encryptedContent, encoding, false);
        } catch (IOException e) {
            throw new ServiceException("Error while reading or writing file during encryption", e);
        } catch (GeneralSecurityException e) {
            throw new ServiceException("Error encrypting file", e);
        }
    }

    public void decryptFile (File encryptedFile) throws ServiceException {
        try {
            String content = FileUtils.readFileToString(encryptedFile, encoding);
            String decryptedContent = decrypt(content);
            FileUtils.writeStringToFile(encryptedFile, decryptedContent, encoding, false);
        } catch (IOException e) {
            throw new ServiceException("Error while reading or writing file during decryption", e);
        } catch (GeneralSecurityException e) {
            throw new ServiceException("Error decrypting file", e);
        }
    }
}
