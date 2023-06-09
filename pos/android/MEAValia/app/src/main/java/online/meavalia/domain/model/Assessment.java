package online.meavalia.domain.model;

public class Assessment {
    private final Criteria criteria;
    private final Note note;

    public Assessment(Criteria criteria, Note note) {
        this.criteria = criteria;
        this.note = note;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public Note getNote() {
        return note;
    }
}
