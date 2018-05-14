package com.example.ckpenep.stackoverflow.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.ckpenep.stackoverflow.model.Question;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface DataDao {
    @Insert
    void insert(Question question);

    @Delete
    void delete(Question question);

    @Query("SELECT * FROM Question")
    Flowable<List<Question>> getAllQuestions();
}
