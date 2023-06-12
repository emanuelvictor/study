package online.meavalia.domain.model;

import android.annotation.SuppressLint;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Assessment implements Serializable {
    private final Criteria criteria;
    private final Note note;
    private final LocalDateTime time;

    @SuppressLint("NewApi")
    public Assessment(final Criteria criteria, final Note note) {
        this.criteria = criteria;
        this.note = note;
        this.time = LocalDateTime.now();
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public Note getNote() {
        return note;
    }
}
