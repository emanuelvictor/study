package online.meavalia.infrastructure.repository;


import java.util.List;

import online.meavalia.domain.model.Criteria;

public abstract class AbstractRepository<T, ID> implements IRepository<T, ID> {

    private final Collection<T> collection = Collection.getInstance();

    @Override
    public T save(T t) {
        collection.add(t);
        return t;
    }

    @Override
    public List<T> findAllByKey(final String key) {
        return collection.getAllByKey(key);
    }

    @Override
    public T findById(ID id) {
        return null;
    }

    @Override
    public void remove(final String key, T t) {
        findAllByKey(key).remove(t);
    }
}
