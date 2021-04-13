package mk.ukim.finki.ib.lab2.answer;

/**
 * This class represents the yA answer that the KDC Server sends as a response back to Alice.
 */
public class AliceAnswer {
    private byte[] encryptedSessionKey;
    private byte[] encryptedNonceA;
    private byte[] encryptedLifetime;
    private byte[] encryptedIDB;

    public AliceAnswer() {
    }

    public AliceAnswer(byte[] encryptedSessionKey, byte[] encryptedNonceA, byte[] encryptedLifetime, byte[] encryptedIDB) {
        this.encryptedSessionKey = encryptedSessionKey;
        this.encryptedNonceA = encryptedNonceA;
        this.encryptedLifetime = encryptedLifetime;
        this.encryptedIDB = encryptedIDB;
    }

    public byte[] getEncryptedSessionKey() {
        return encryptedSessionKey;
    }

    public void setEncryptedSessionKey(byte[] encryptedSessionKey) {
        this.encryptedSessionKey = encryptedSessionKey;
    }

    public byte[] getEncryptedNonceA() {
        return encryptedNonceA;
    }

    public void setEncryptedNonceA(byte[] encryptedNonceA) {
        this.encryptedNonceA = encryptedNonceA;
    }

    public byte[] getEncryptedLifetime() {
        return encryptedLifetime;
    }

    public void setEncryptedLifetime(byte[] encryptedLifetime) {
        this.encryptedLifetime = encryptedLifetime;
    }

    public byte[] getEncryptedIDB() {
        return encryptedIDB;
    }

    public void setEncryptedIDB(byte[] encryptedIDB) {
        this.encryptedIDB = encryptedIDB;
    }
}
