package com.example.gitHubRepos.data.database;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseDao<T> {
    private static final String SELECTION_WHERE_ID = "_id = ?";

    private Converter<T> converter;
    private GitHubReposDBHelper gitHubReposDBHelper;
    private String tableName;

    public BaseDao(GitHubReposDBHelper gitHubReposDBHelper,
                   Converter<T> converter, String tableName) {
        this.converter = converter;
        this.gitHubReposDBHelper = gitHubReposDBHelper;
        this.tableName = tableName;
    }

    public void addAll(List<T> entities) {
        SQLiteDatabase db = null;
        try {
            db = gitHubReposDBHelper.getWritableDatabase();
            db.beginTransactionNonExclusive();
            for (T entity : entities) {
                db.insert(tableName, null, converter.fromEntity(entity));
            }
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
    }

    public void update(T entity, String id) {
        String[] idArg = {id};
        try (SQLiteDatabase db = gitHubReposDBHelper.getWritableDatabase()) {
            db.update(tableName, converter.fromEntity(entity),
                    SELECTION_WHERE_ID, idArg);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public T get(String id) {
        T entity = null;
        String[] idArg = {id};
        try (
                SQLiteDatabase db = gitHubReposDBHelper.getReadableDatabase();
                Cursor cursor = db.query(tableName, null, SELECTION_WHERE_ID, idArg,
                        null, null, null)) {
            while (cursor.moveToNext()) {
                entity = converter.toEntity(cursor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    public List<T> getAll() {
        List<T> entities = new ArrayList<>();
        try (
                SQLiteDatabase db = gitHubReposDBHelper.getReadableDatabase();
                Cursor cursor = db.query(tableName, null, null, null,
                        null, null, null)) {
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    entities.add(converter.toEntity(cursor));
                    cursor.moveToNext();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }
}
