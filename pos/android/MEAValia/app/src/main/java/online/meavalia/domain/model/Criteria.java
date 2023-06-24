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
    private final List<Assessment> assessments = new ArrayList<>(); // TODO

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

    public void calculateAvg() {

        BigDecimal sum = BigDecimal.ZERO;
        for (int j = 0; j < assessments.size(); j++) {
            sum = sum.add(BigDecimal.valueOf(assessments.get(j).getNote().getValue()));
        }

        if (!sum.equals(BigDecimal.ZERO))
            this.avg = sum.divide(BigDecimal.valueOf(assessments.size()), 2, RoundingMode.HALF_UP);

    }

    public BigDecimal getAvg() {
        return avg;
    }

    public void addAssessment(final Assessment assessment) {
        this.assessments.add(assessment);
        calculateAvg();
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