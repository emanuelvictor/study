package online.meavalia.infrastructure.repository;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public final class Collection<T> {
    private static Collection<?> instance;
    public Map<String, List<T>> values;

    private Collection() {
        this.values = new HashMap<>();
    }

    public static <T> Collection<T> getInstance() {
        if (instance == null) {
            instance = new Collection<>();
        }
        return (Collection<T>) instance;
    }

    @SuppressLint("NewApi")
    T add(T t) {
        final String key = t.getClass().toString();
        createByKey(key);

        int indexOfElement = Objects.requireNonNull(values.get(key)).indexOf(t);
        if (indexOfElement >= 0) {
            Objects.requireNonNull(values.get(key)).set(indexOfElement, t);
        } else
            Objects.requireNonNull(values.get(key)).add(t);

        return t;
    }

    private void createByKey(final String key) {
        if (values.get(key) == null)
            values.put(key, new ArrayList<>());
    }

    List<T> getAllByKey(final String key) {
        createByKey(key);
        return values.get(key);
    }
}