package online.meavalia.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Criteria implements Serializable {

    private String name;
    private String sentence;
    private Priority priority;
    private Boolean legalPerson;
    private String document;
    private String email;
    private BigDecimal avg;
    private int countOfAssessments = 0;

    public Criteria(String name, String sentence, final int valueOfPriority) {
        this.name = name;
        this.sentence = sentence;
        this.priority = Priority.enumFromValue(valueOfPriority);
    }

    public Criteria(String name, String sentence, String document, String email, int valueOfPriority, Boolean legalPerson) {
        this.name = name;
        this.sentence = sentence;
        this.document = document;
        this.email = email;
        this.priority = Priority.enumFromValue(valueOfPriority);
        this.legalPerson = legalPerson;
    }

    public String getName() {
        return name;
    }

    public String getSentence() {
        return sentence;
    }

    public void addAssessment(final int newNote) {

        if (avg == null) {
            avg = BigDecimal.valueOf(newNote);
            countOfAssessments++;
            return;
        }
        final BigDecimal avgMultiplied = avg.multiply(BigDecimal.valueOf(countOfAssessments));
        avg = avgMultiplied.add(BigDecimal.valueOf(newNote)).divide(BigDecimal.valueOf(countOfAssessments + 1), 2, RoundingMode.HALF_UP);
        this.countOfAssessments++;
    }

    public BigDecimal getAvg() {
        return avg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Criteria criteria = (Criteria) o;

        if (!Objects.equals(name, criteria.name)) return false;
        if (!Objects.equals(sentence, criteria.sentence))
            return false;
        if (priority != criteria.priority) return false;
        if (!Objects.equals(document, criteria.document))
            return false;
        return Objects.equals(email, criteria.email);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (sentence != null ? sentence.hashCode() : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        result = 31 * result + (document != null ? document.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}