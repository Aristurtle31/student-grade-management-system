/**
 * A single grade entry: one subject and the score the student earned in it.
 *
 * Suggested owner: Member 1 (data model + validation)
 */
public class Grade {
    private final String subject;
    private final double score;

    public Grade(String subject, double score) {
        this.subject = subject;
        this.score = score;
    }

    public String getSubject() {
        return subject;
    }

    public double getScore() {
        return score;
    }
}
