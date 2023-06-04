package online.meavalia.domain.model;

public enum CriteriaType {

    NORMAL_CRITERIA("NORMAL CRITERIA"),
    PHYSIC_PERSON("PHYSIC PERSON"),
    JURIDIC_PERSON("JURIDIC PERSON");

    private final String value;

    CriteriaType(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
