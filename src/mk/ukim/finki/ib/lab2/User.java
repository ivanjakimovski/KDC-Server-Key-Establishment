package mk.ukim.finki.ib.lab2;

import mk.ukim.finki.ib.lab2.aes.AES;
import mk.ukim.finki.ib.lab2.answer.AliceAnswer;
import mk.ukim.finki.ib.lab2.answer.AliceBobAnswer;
import mk.ukim.finki.ib.lab2.answer.BobAnswer;
import mk.ukim.finki.ib.lab2.exceptions.*;

import java.security.SecureRandom;
import java.sql.Time;
import java.util.Arrays;

/**
 * This class represents a User.
 */
public class User {
    private String name;
    private String ID;
    private byte[] nonce;
    private String otherUserID;
    private String key;
    private BobAnswer bobAnswer;
    private byte[] sessionKey;

    public User(String name, String ID, String otherUserID, String key) {
        this.name = name;
        this.ID = ID;
        this.otherUserID = otherUserID;
        this.nonce = new byte[16];
        this.key = key;
        generateNonce();
    }

    /**
     * This method verifies the answer returned by the KDC Server.
     * It first decrypts the session key, Alice nonce, the lifetime and the Bob ID.
     * It then verifies each of them and if one of them fails and exception is thrown.
     * Then it generates a timestamp and encrypts the Alice ID and the timestamp using the session key.
     *
     * @param aliceAnswer this is the answer yA that is returned by the KDC Server.
     * @param bobAnswer this is the answer yB that is returned by the KDC Server.
     * @return {@link AliceBobAnswer}.
     */
    public AliceBobAnswer verifyAlice(AliceAnswer aliceAnswer, BobAnswer bobAnswer) {
        this.bobAnswer = bobAnswer;

        AES aesAlice = new AES();
        aesAlice.setKey(key);
        byte[] decryptedSessionKey = aesAlice.decrypt(aliceAnswer.getEncryptedSessionKey());
        byte[] decryptedNonce = aesAlice.decrypt(aliceAnswer.getEncryptedNonceA());
        byte[] decryptedLifetime = aesAlice.decrypt(aliceAnswer.getEncryptedLifetime());
        byte[] decryptedIDB = aesAlice.decrypt(aliceAnswer.getEncryptedIDB());

        this.sessionKey = decryptedSessionKey;

        String lifetime = new String(decryptedLifetime);
        Time time = new Time(Long.parseLong(lifetime));
        Time currentTime = new Time(System.currentTimeMillis());

        if(!Arrays.equals(decryptedNonce, nonce)) {
            throw new NonceNotValidException();
        }

        if(!otherUserID.equals(new String(decryptedIDB))) {
            throw new IDBNotValidException();
        }

        if(currentTime.after(time)) {
            throw new LifetimeNotValidException();
        }

        AES aesSession = new AES();
        aesSession.setKey(new String(decryptedSessionKey));

        byte[] encryptedIDA = aesSession.encrypt(this.getID().getBytes());
        byte[] encryptedTimestamp = aesSession.encrypt(String.valueOf(currentTime.getTime()).getBytes());

        AliceBobAnswer aliceBobAnswer = new AliceBobAnswer();
        aliceBobAnswer.setEncryptedIDA(encryptedIDA);
        aliceBobAnswer.setEncryptedTimestamp(encryptedTimestamp);

        return aliceBobAnswer;
    }

    /**
     * This method verifies the answer that Alice sends to Bob.
     * It first decrypts the session key, the Alice ID and the lifetime from the BobAnswer yB answer.
     * Then it decrypts Alice ID and the timestamp from the AliceBobAnswer yAB answer.
     * It then verifies the Alice ID, the lifetime and the timestamp and if one of them fails
     * an exception is thrown.
     *
     * @param aliceBobAnswer this is the answer yAB that Alice sends to Bob.
     * @param bobAnswer this is the answer yB that Alice sends to Bob.
     */
    public void verifyBob(AliceBobAnswer aliceBobAnswer, BobAnswer bobAnswer) {

        AES aesBob = new AES();
        aesBob.setKey(this.key);

        byte[] decryptedSessionKey = aesBob.decrypt(bobAnswer.getEncryptedSessionKey());
        byte[] decryptedIDA = aesBob.decrypt(bobAnswer.getEncryptedIDA());
        byte[] decryptedLifetime = aesBob.decrypt(bobAnswer.getEncryptedLifetime());

        this.sessionKey = decryptedSessionKey;

        Time lifetime = new Time(Long.valueOf(new String(decryptedLifetime)));


        Time currentTime = new Time(System.currentTimeMillis());
        
        AES aesSession = new AES();
        aesSession.setKey(new String(decryptedSessionKey));

        byte[] decryptedIDASession = aesSession.decrypt(aliceBobAnswer.getEncryptedIDA());
        byte[] decryptedTimestamp = aesSession.decrypt(aliceBobAnswer.getEncryptedTimestamp());

        Time timestamp = new Time(Long.valueOf(new String(decryptedTimestamp)));

        if(!Arrays.equals(decryptedIDA, decryptedIDASession)) {
            throw new IDANotValidException();
        }

        if(currentTime.after(lifetime)) {
            throw new LifetimeNotValidException();
        }

        if(timestamp.after(lifetime)) {
            throw new TimestampNotValidException();
        }

    }

    /**
     * This method encrypts the message that needs to be send.
     *
     * @param message the message that needs to be encrypted.
     * @return the encrypted message.
     */
    public byte[] getMessage(String message) {
        AES aesSession = new AES();
        aesSession.setKey(new String(this.sessionKey));
        return aesSession.encrypt(message.getBytes());
    }

    /**
     * This method decrypts and prints out the message.
     *
     * @param message the encrypted message.
     */
    public void readMessage(byte[] message) {
        AES aesSession = new AES();
        aesSession.setKey(new String(this.sessionKey));
        byte[] decryptedMessage = aesSession.decrypt(message);
        System.out.println(new String(decryptedMessage));
    }

    /**
     * This method generates a random nonce in the nonce byte array.
     */
    private void generateNonce() {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(this.nonce);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public byte[] getNonce() {
        return nonce;
    }

    public void setNonce(byte[] nonce) {
        this.nonce = nonce;
    }

    public String getOtherUserID() {
        return otherUserID;
    }

    public void setOtherUserID(String otherUserID) {
        this.otherUserID = otherUserID;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public BobAnswer getBobAnswer() {
        return bobAnswer;
    }

    public void setBobAnswer(BobAnswer bobAnswer) {
        this.bobAnswer = bobAnswer;
    }
}
