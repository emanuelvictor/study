package online.meavalia.domain.model;

public class Criteria {
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
}
