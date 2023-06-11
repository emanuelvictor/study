package online.meavalia.infrastructure.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class Collection<T> {
    private static Collection<?> instance;
    public Map<String, Set<T>> values;

    private Collection() {
        this.values = new HashMap<>();
    }

    public static <T> Collection<T> getInstance() {
        if (instance == null) {
            instance = new Collection<>();
        }
        return (Collection<T>) instance;
    }

    T add(T t) {
        final String key = t.getClass().toString();
        if (values.get(key) == null)
            values.put(key, new HashSet<>());
        values.get(key).add(t);
        return t;
    }

    Set<T> getAllByKey(final String key) {
        if (values.get(key) == null)
            values.put(key, new HashSet<>());
        return values.get(key);
    }
}