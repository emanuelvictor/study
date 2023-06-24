package online.meavalia.infrastructure.repository.impl;


import android.annotation.SuppressLint;
import android.content.Context;

import java.util.List;

import online.meavalia.R;
import online.meavalia.domain.model.Criteria;
import online.meavalia.domain.model.Priority;
import online.meavalia.domain.repository.CriteriaRepository;
import online.meavalia.infrastructure.repository.AbstractRepository;

public class CriteriaRepositoryImpl extends AbstractRepository<Criteria, Long> implements CriteriaRepository {

    @Override
    public Criteria save(Criteria criteria) {
        return super.save(criteria);
    }

    @Override
    public List<Criteria> getAll() {
        populateFirstCriteria();
        return super.findAllByKey(Criteria.class.toString());
    }

    @SuppressLint("NewApi")
    private void populateFirstCriteria() {
        if (super.findAllByKey(Criteria.class.toString()).stream().noneMatch(it -> it.getName().equals("Atendimento"))) {
            final Criteria criteria = new Criteria("Atendimento", "Como você avalia atendimento?", Priority.HIGH_PRIORITY.getValue());
            save(criteria);
        }
    }
}
