package mk.ukim.finki.ib.lab2;

import mk.ukim.finki.ib.lab2.aes.AES;
import mk.ukim.finki.ib.lab2.answer.AliceAnswer;
import mk.ukim.finki.ib.lab2.answer.BobAnswer;
import mk.ukim.finki.ib.lab2.answer.KDCAnswer;

import java.sql.Time;
import java.util.Random;

/**
 * This class represents the KDC server.
 * It has methods that handle the request made by Alice.
 */
public class KDCServer {
    private String AliceKey;
    private String BobKey;
    private Random random;

    public KDCServer() {
        this.AliceKey = "test1234";
        this.BobKey = "1234test";
        this.random = new Random();
    }

    /**
     * This method generates a random session key using the generateRandomSessionKey() method,
     * it generates a lifetime using the generateLifetime() method. Then it encrypts the session key,
     * the nonce from Alice, the lifetime and the ID of Bob and it creates an AliceAnswer yA object.
     * It does the same for the BobAnswer yB, it encrypts the session key, the Alice ID and the lifetime.
     * It returns a KDCAnswer object consisted of AliceAnswer and BobAnswer.
     *
     * @param user this is Alice when she requests the session key.
     * @return {@link KDCAnswer}.
     */
    public KDCAnswer requestSessionKey(User user) {

        byte[] randomSessionKey = generateRandomSessionKey();
        Time lifetime = generateLifetime();

        KDCAnswer kdcAnswer = new KDCAnswer();
        AliceAnswer aliceAnswer = new AliceAnswer();
        BobAnswer bobAnswer = new BobAnswer();

        AES aesAlice = new AES();
        aesAlice.setKey(AliceKey);

        byte[] encryptedRandomSessionA = aesAlice.encrypt(randomSessionKey);
        byte[] encryptedNonceA = aesAlice.encrypt(user.getNonce());
        byte[] encryptedLifeTimeA = aesAlice.encrypt(String.valueOf(lifetime.getTime()).getBytes());
        byte[] encryptedIDB = aesAlice.encrypt(user.getOtherUserID().getBytes());

        aliceAnswer.setEncryptedSessionKey(encryptedRandomSessionA);
        aliceAnswer.setEncryptedNonceA(encryptedNonceA);
        aliceAnswer.setEncryptedLifetime(encryptedLifeTimeA);
        aliceAnswer.setEncryptedIDB(encryptedIDB);

        AES aesBob = new AES();
        aesBob.setKey(BobKey);

        byte[] encryptedRandomSessionB = aesBob.encrypt(randomSessionKey);
        byte[] encryptedIDA = aesBob.encrypt(user.getID().getBytes());
        byte[] encryptedLifetimeB = aesBob.encrypt(String.valueOf(lifetime.getTime()).getBytes());

        bobAnswer.setEncryptedSessionKey(encryptedRandomSessionB);
        bobAnswer.setEncryptedIDA(encryptedIDA);
        bobAnswer.setEncryptedLifetime(encryptedLifetimeB);

        kdcAnswer.setAliceAnswer(aliceAnswer);
        kdcAnswer.setBobAnswer(bobAnswer);

        return kdcAnswer;

    }

    /**
     * This method generates a random 16 byte array which represents the random session key.
     *
     * @return byte[].
     */
    private byte[] generateRandomSessionKey() {
        byte[] randomSessionKey = new byte[16];
        random.nextBytes(randomSessionKey);
        return randomSessionKey;
    }

    /**
     * This methods returns a Time object 10 minutes in the future from the current time whitch represents
     * the lifetime used in the KDCAnswer.
     *
     * @return {@link Time}.
     */
    public Time generateLifetime() {
        return new Time(System.currentTimeMillis() + (600 * 1000 * 10));
    }
}
