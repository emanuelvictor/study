package online.meavalia.infrastructure.repository.impl;

import java.util.List;

import online.meavalia.domain.model.Criteria;
import online.meavalia.domain.repository.CriteriaRepository;
import online.meavalia.infrastructure.repository.AbstractRepository;

public class CriteriaRepositoryImpl extends AbstractRepository<Criteria, Long> implements CriteriaRepository {

    @Override
    public Criteria save(Criteria criteria) {
        return super.save(criteria);
    }

    @Override
    public List<Criteria> getAll() {
        return super.findAll();
    }
}
