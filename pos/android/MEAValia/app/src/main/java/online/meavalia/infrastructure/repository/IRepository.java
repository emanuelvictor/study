package online.meavalia.infrastructure.repository;

import java.util.List;

public interface IRepository<T, ID> {

    T save(T t);

    List<T> findAll();

    T findById(ID id);
}
