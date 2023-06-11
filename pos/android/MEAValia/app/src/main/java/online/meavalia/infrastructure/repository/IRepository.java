package online.meavalia.infrastructure.repository;

import java.util.Set;

public interface IRepository<T, ID> {

    T save(T t);

    Set<T> findAllByKey(final String key);

    T findById(ID id);
}
