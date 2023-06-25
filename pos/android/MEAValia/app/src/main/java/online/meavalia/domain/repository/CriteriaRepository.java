package online.meavalia.domain.repository;

import java.util.List;
import java.util.Set;

import online.meavalia.domain.model.Criteria;

public interface CriteriaRepository {
    Criteria save(Criteria criteria);

    List<Criteria> getAll();

    void remove(final Criteria criteria);
}
