package online.meavalia.domain.model;

import java.io.Serializable;
import java.util.Objects;

public class Criteria implements Serializable {
    private String name;
    private String sentence;
    private String document;
    private String email;
    private CriteriaType type;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Criteria criteria = (Criteria) o;

        if (!Objects.equals(name, criteria.name)) return false;
        if (!Objects.equals(sentence, criteria.sentence))
            return false;
        if (!Objects.equals(document, criteria.document))
            return false;
        if (!Objects.equals(email, criteria.email)) return false;
        return type == criteria.type;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (sentence != null ? sentence.hashCode() : 0);
        result = 31 * result + (document != null ? document.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
