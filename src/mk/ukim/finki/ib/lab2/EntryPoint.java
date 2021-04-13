package mk.ukim.finki.ib.lab2;

import mk.ukim.finki.ib.lab2.answer.AliceBobAnswer;
import mk.ukim.finki.ib.lab2.answer.KDCAnswer;

/**
 * This is the Entry Point where the main function is called.
 * A KDCServer object is created, then Alice and Bob instances of the User class are created.
 * Then the requestSessionKey method on the KDC Server is called with the Alice User, this method
 * returns the KDCAnswer.
 * The method verifyAlice is called on the Alice User object which verifies the answer and returns
 * AliceBobAnswer that gets send to Bob.
 * Bob then verifies the answer and then they can send messages between each other.
 */
public class EntryPoint {
    public static void main(String[] args) {
        KDCServer server = new KDCServer();

        User Alice = new User("Alice", "AliceID", "BobID", "test1234");
        User Bob = new User("Bob", "BobID", "AliceID", "1234test");

        KDCAnswer kdcAnswer = server.requestSessionKey(Alice);

        AliceBobAnswer aliceBobAnswer = Alice.verifyAlice(kdcAnswer.getAliceAnswer(), kdcAnswer.getBobAnswer());

        Bob.verifyBob(aliceBobAnswer, Alice.getBobAnswer());

        byte[] message = Alice.getMessage("This is a test message from Alice.");
        Bob.readMessage(message);

        byte[] messageFromBob = Bob.getMessage("This is a test message from Bob.");
        Alice.readMessage(messageFromBob);

    }
}
