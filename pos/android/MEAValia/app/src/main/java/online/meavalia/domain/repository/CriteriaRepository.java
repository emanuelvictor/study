package online.meavalia.domain.repository;

import java.util.Set;

import online.meavalia.domain.model.Criteria;

public interface CriteriaRepository {
    Criteria save(Criteria criteria);

    Set<Criteria> getAll();
}
