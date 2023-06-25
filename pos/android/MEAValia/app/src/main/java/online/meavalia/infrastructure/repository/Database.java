package online.meavalia.infrastructure.repository;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import online.meavalia.domain.converters.BigDecimalConverter;
import online.meavalia.domain.model.Criteria;
import online.meavalia.infrastructure.repository.impl.CriteriaRepositoryRoom;

@androidx.room.Database(entities = {Criteria.class}, version = 1, exportSchema = false)
@TypeConverters(BigDecimalConverter.class)
public abstract class Database extends RoomDatabase {

    public abstract CriteriaRepositoryRoom criteriaRepositoryRoom();
    private static Database instance;

    public static Database getDatabase(final Context context) {

        if (instance == null) {

            synchronized (Database.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context,
                            Database.class,
                            "criteria.db").allowMainThreadQueries().build();
                }
            }
        }
        return instance;
    }
}
