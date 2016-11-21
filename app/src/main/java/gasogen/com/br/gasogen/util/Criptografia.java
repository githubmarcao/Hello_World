package gasogen.com.br.gasogen.util;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Dell on 12/11/2016.
 */

public final class Criptografia {

    private static String INICIALIZATION_VETOR = "1234567812345678";
    private static String SALT = "1234567812345678";
    private static int pswdIterations = 2;
    private static int keySize = 128;//256;

//    public static void main(String args[]) {
//        try {
//            String encrip = Criptografia.encrypt("1234", "maria");
//            System.out.println(encrip);
//            String decrip = Criptografia.decrypt(encrip, "maria");
//            System.out.println(decrip);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static String encrypt(String plainText, String password) throws
            NoSuchAlgorithmException,
            InvalidKeySpecException,
            NoSuchPaddingException,
            InvalidParameterSpecException,
            IllegalBlockSizeException,
            BadPaddingException,
            UnsupportedEncodingException,
            InvalidKeyException,
            InvalidAlgorithmParameterException {
        byte[] saltBytes = SALT.getBytes("UTF-8");
        byte[] ivBytes = INICIALIZATION_VETOR.getBytes("UTF-8");
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        PBEKeySpec spec = new PBEKeySpec(
                password.toCharArray(),
                saltBytes,
                pswdIterations,
                keySize
        );
        SecretKey secretKey = factory.generateSecret(spec);
        SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secret, new IvParameterSpec(ivBytes));
        byte[] encryptedTextBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
        return Base64.encodeToString(encryptedTextBytes, Base64.DEFAULT);
    }

    public static String decrypt(String encryptedText, String password) throws
            NoSuchAlgorithmException,
            InvalidKeySpecException,
            NoSuchPaddingException,
            InvalidKeyException,
            InvalidAlgorithmParameterException,
            UnsupportedEncodingException {
        byte[] saltBytes = SALT.getBytes("UTF-8");
        byte[] ivBytes = INICIALIZATION_VETOR.getBytes("UTF-8");
        byte[] encryptedTextBytes = Base64.decode(encryptedText, Base64.DEFAULT);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        PBEKeySpec spec = new PBEKeySpec(
                password.toCharArray(),
                saltBytes,
                pswdIterations,
                keySize
        );
        SecretKey secretKey = factory.generateSecret(spec);
        SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(ivBytes));
        byte[] decryptedTextBytes = null;
        try {
            decryptedTextBytes = cipher.doFinal(encryptedTextBytes);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return new String(decryptedTextBytes);
    }
}
