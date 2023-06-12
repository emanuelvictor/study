package online.meavalia.domain.model;

import java.io.Serializable;

public class Note implements Serializable {
    private final int value;

    public Note(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
