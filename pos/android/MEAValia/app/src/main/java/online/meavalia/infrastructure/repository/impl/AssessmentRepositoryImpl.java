package online.meavalia.infrastructure.repository.impl;


import java.util.List;
import java.util.Set;

import online.meavalia.domain.model.Assessment;
import online.meavalia.domain.repository.AssessmentRepository;
import online.meavalia.infrastructure.repository.AbstractRepository;

public class AssessmentRepositoryImpl extends AbstractRepository<Assessment, Long> implements AssessmentRepository {

    @Override
    public Assessment save(Assessment assessment) {
        return super.save(assessment);
    }

    @Override
    public List<Assessment> getAll() {
        return super.findAllByKey(Assessment.class.toString());
    }
}
