package online.meavalia.infrastructure.repository;

import java.util.ArrayList;
import java.util.List;

public final class Collection<T> {
    private static Collection<?> instance;
    public List<T> values;

    private Collection() {
        this.values = new ArrayList<>();
    }

    public static <T> Collection<T> getInstance() {
        if (instance == null) {
            instance = new Collection<>();
        }
        return (Collection<T>) instance;
    }

    T add(T t) {
        values.add(t);
        return t;
    }

    List<T> getAll() {
        return values;
    }
}