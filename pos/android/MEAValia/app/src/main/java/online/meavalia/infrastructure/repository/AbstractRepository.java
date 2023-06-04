package online.meavalia.infrastructure.repository;

import java.util.List;

public abstract class AbstractRepository<T, ID> implements IRepository<T, ID> {

    private final Collection<T> collection = Collection.getInstance();

    @Override
    public T save(T t) {
        return collection.add(t);
    }

    @Override
    public List<T> findAll() {
        return collection.getAll();
    }

    @Override
    public T findById(ID id) {
        return null;
    }
}
