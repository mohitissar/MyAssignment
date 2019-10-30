package com.mohit.myassignment.service.repository.storge;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mohit.myassignment.service.repository.storge.model.Facts;

import java.util.List;

/**
 * Data Access Object for the movies table.
 */
@Dao
public interface FactDao {

    /**
     * Get the Movies from the table.
     * -------------------------------
     * Since the DB use as caching, we don't return LiveData.
     * We don't need to get update every time the database update.
     * We using the get query when application start. So, we able to display
     * data fast and in case we don't have connection to work offline.
     * @return the movies from the table
     */
    @Query("SELECT * FROM facts")
    List<Facts> getFacts();

    /**
     * Insert a facts in the database. If the facts already exists, replace it.
     *
     * @param facts the facts to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFacts(Facts facts);

    @Query("DELETE FROM facts")
    abstract void deleteAllMovies();
}
