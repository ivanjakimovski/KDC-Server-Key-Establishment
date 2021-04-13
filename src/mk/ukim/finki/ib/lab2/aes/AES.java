package mk.ukim.finki.ib.lab2.aes;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * This class is a wrapper for the AES algorithm and contains methods
 * for encryption and decryption.
 */
public class AES {

    private  SecretKeySpec secretKey;
    private  byte[] key;

    /**
     * This method sets the key.
     *
     * @param myKey the key that is used to initialize the AES.
     */
    public  void setKey(String myKey)
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-256");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method gets a byte array as input and encrypts it using AES and returns
     * the encrypted array.
     *
     * @param byteArr this is the input byte array that needs to be encrypted.
     * @return byte[].
     */
    public byte[] encrypt(byte[] byteArr)
    {
        try
        {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(byteArr);
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    /**
     * This method gets a byte array as input and decrypts it using AES and returns
     * the decrypted array.
     *
     * @param byteArr this is the input byte array that needs to be decrypted.
     * @return byte[].
     */
    public byte[] decrypt(byte[] byteArr)
    {
        try
        {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(byteArr);
        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}