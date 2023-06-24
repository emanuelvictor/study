package online.meavalia.domain.model;

import java.io.Serializable;

public enum Priority implements Serializable {

    HIGH_PRIORITY(0),
    MEDIUM_PRIORITY(1),
    LOW_PRIORITY(2);

    private final int value;

    Priority(final int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static Priority enumFromValue(final int name) {
        if (name == (Priority.HIGH_PRIORITY.value))
            return Priority.HIGH_PRIORITY;
        else if (name == (Priority.MEDIUM_PRIORITY.value))
            return Priority.MEDIUM_PRIORITY;
        else
            return Priority.LOW_PRIORITY;
    }

//    public static String[] getValues() {
//        return new String[]{Priority.HIGH_PRIORITY.getValue(), Priority.MEDIUM_PRIORITY.getValue(), Priority.LOW_PRIORITY.getValue()};
//    }

}
