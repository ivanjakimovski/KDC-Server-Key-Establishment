package mk.ukim.finki.ib.lab2.answer;

/**
 * This class represents the yB answer that the KDC Server sends as a response back to Alice.
 */
public class BobAnswer {
    private byte[] encryptedSessionKey;
    private byte[] encryptedIDA;
    private byte[] encryptedLifetime;

    public BobAnswer(byte[] encryptedSessionKey, byte[] encryptedIDA, byte[]  encryptedLifetime) {
        this.encryptedSessionKey = encryptedSessionKey;
        this.encryptedIDA = encryptedIDA;
        this.encryptedLifetime = encryptedLifetime;
    }

    public BobAnswer() {
    }

    public byte[] getEncryptedSessionKey() {
        return encryptedSessionKey;
    }

    public void setEncryptedSessionKey(byte[] encryptedSessionKey) {
        this.encryptedSessionKey = encryptedSessionKey;
    }

    public byte[] getEncryptedIDA() {
        return encryptedIDA;
    }

    public void setEncryptedIDA(byte[] encryptedIDA) {
        this.encryptedIDA = encryptedIDA;
    }

    public byte[]  getEncryptedLifetime() {
        return encryptedLifetime;
    }

    public void setEncryptedLifetime(byte[] encryptedLifetime) {
        this.encryptedLifetime = encryptedLifetime;
    }
}
