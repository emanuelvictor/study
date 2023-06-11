package online.meavalia.infrastructure.repository.impl;


import java.util.Set;

import online.meavalia.domain.model.Criteria;
import online.meavalia.domain.repository.CriteriaRepository;
import online.meavalia.infrastructure.repository.AbstractRepository;

public class CriteriaRepositoryImpl extends AbstractRepository<Criteria, Long> implements CriteriaRepository {

    @Override
    public Criteria save(Criteria criteria) {
        return super.save(criteria);
    }

    @Override
    public Set<Criteria> getAll() {
        final Criteria criteria = new Criteria("Atendimento", "Como você avalia atendimento?");
        save(criteria);
        return super.findAllByKey(Criteria.class.toString());
    }
}
