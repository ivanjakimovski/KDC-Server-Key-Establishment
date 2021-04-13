package mk.ukim.finki.ib.lab2.answer;

/**
 * This class represents the KDC Answer consisted of yA and yB.
 */
public class KDCAnswer {
    private AliceAnswer aliceAnswer;
    private BobAnswer bobAnswer;

    public KDCAnswer() {
    }

    public AliceAnswer getAliceAnswer() {
        return aliceAnswer;
    }

    public void setAliceAnswer(AliceAnswer aliceAnswer) {
        this.aliceAnswer = aliceAnswer;
    }

    public BobAnswer getBobAnswer() {
        return bobAnswer;
    }

    public void setBobAnswer(BobAnswer bobAnswer) {
        this.bobAnswer = bobAnswer;
    }
}
