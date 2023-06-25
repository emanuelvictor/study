package online.meavalia.domain.repository;

import java.util.List;

import online.meavalia.domain.model.Criteria;

public interface CriteriaRepository {
    Criteria save(Criteria criteria);

    Criteria update(Criteria criteria);

    List<Criteria> getAll();

    Criteria getById(int id);

    void remove(final Criteria criteria);
}
