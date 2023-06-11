package online.meavalia.infrastructure.repository;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractRepository<T, ID> implements IRepository<T, ID> {

    private final Collection<T> collection = Collection.getInstance();

    @Override
    public T save(T t) {
        collection.add(t);
        return t;
    }

    @Override
    public Set<T> findAllByKey(final String key) {
        return collection.getAllByKey(key);
    }

    @Override
    public T findById(ID id) {
        return null;
    }
}
