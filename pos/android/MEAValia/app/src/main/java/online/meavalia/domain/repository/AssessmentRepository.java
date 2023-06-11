package online.meavalia.domain.repository;

import java.util.Set;

import online.meavalia.domain.model.Assessment;

public interface AssessmentRepository {
    Assessment save(Assessment assessment);

    Set<Assessment> getAll();
}
