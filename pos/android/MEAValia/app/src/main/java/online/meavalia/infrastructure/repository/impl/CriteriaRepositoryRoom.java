package online.meavalia.infrastructure.repository.impl;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import online.meavalia.domain.model.Criteria;

@Dao
public interface CriteriaRepositoryRoom {
    @Insert
    long insert(Criteria criteria);

    @Delete
    void remove(Criteria criteria);

    @Update
    void update(Criteria criteria);

    @Query("SELECT * FROM criteria WHERE id = :id")
    Criteria findById(long id);

    @Query("SELECT * FROM criteria ORDER BY priority ASC")
    List<Criteria> findAll();
}
