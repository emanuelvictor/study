package online.meavalia.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Criteria implements Serializable {

    private final String name;
    private final String sentence;
    private final CriteriaType type;
    private String document;
    private String email;
    private BigDecimal avg;
    private final List<Assessment> assessments = new ArrayList<>();

    public Criteria(String name, String sentence) {
        this.name = name;
        this.sentence = sentence;
        this.type = CriteriaType.NORMAL_CRITERIA;
    }

    public Criteria(String name, String sentence, String document, String email, CriteriaType type) {
        this.name = name;
        this.sentence = sentence;
        this.document = document;
        this.email = email;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getSentence() {
        return sentence;
    }

    public String getDocument() {
        return document;
    }

    public String getEmail() {
        return email;
    }

    public CriteriaType getType() {
        return type;
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
        if (type != criteria.type) return false;
        if (!Objects.equals(document, criteria.document))
            return false;
        return Objects.equals(email, criteria.email);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (sentence != null ? sentence.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (document != null ? document.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}