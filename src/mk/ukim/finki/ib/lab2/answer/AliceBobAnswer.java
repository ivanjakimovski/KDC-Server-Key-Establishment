package mk.ukim.finki.ib.lab2.answer;

/**
 * This class represents the yAB answer that Alice sends to Bob.
 */
public class AliceBobAnswer {
    private byte[] encryptedIDA;
    private byte[] encryptedTimestamp;

    public AliceBobAnswer() {
    }

    public byte[] getEncryptedIDA() {
        return encryptedIDA;
    }

    public void setEncryptedIDA(byte[] encryptedIDA) {
        this.encryptedIDA = encryptedIDA;
    }

    public byte[] getEncryptedTimestamp() {
        return encryptedTimestamp;
    }

    public void setEncryptedTimestamp(byte[] encryptedTimestamp) {
        this.encryptedTimestamp = encryptedTimestamp;
    }
}
