package com.example.gitHubRepos.data.database;

import android.content.ContentValues;
import android.database.Cursor;

public abstract class Converter<T> {
    abstract T toEntity(Cursor cursor);

    abstract ContentValues fromEntity(T entity);
}
