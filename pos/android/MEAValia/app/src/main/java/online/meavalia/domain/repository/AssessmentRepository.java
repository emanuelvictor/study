package online.meavalia.domain.repository;

import java.util.List;

import online.meavalia.domain.model.Assessment;

public interface AssessmentRepository {
    Assessment save(Assessment assessment);
    List<Assessment> getAll();
}
