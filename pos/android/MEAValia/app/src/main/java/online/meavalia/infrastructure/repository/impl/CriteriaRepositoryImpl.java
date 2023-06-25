package online.meavalia.infrastructure.repository.impl;


import android.content.Context;

import java.util.List;

import online.meavalia.domain.model.Criteria;
import online.meavalia.domain.repository.CriteriaRepository;
import online.meavalia.infrastructure.repository.Database;

public class CriteriaRepositoryImpl implements CriteriaRepository {

    private final Database database;

    public CriteriaRepositoryImpl(final Context context) {
        database = Database.getDatabase(context);
    }

    @Override
    public Criteria save(final Criteria criteria) {
        final long id = database.criteriaRepositoryRoom().insert(criteria);
        return database.criteriaRepositoryRoom().findById(id);
    }

    @Override
    public Criteria update(Criteria criteria) {
        database.criteriaRepositoryRoom().update(criteria);
        return database.criteriaRepositoryRoom().findById(criteria.getId());
    }

    @Override
    public List<Criteria> getAll() {
        return database.criteriaRepositoryRoom().findAll();
    }

    @Override
    public Criteria getById(int id) {
        return database.criteriaRepositoryRoom().findById(id);
    }

    @Override
    public void remove(final Criteria criteria) {
        database.criteriaRepositoryRoom().remove(criteria);
    }
}
