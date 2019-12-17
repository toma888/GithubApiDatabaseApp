package com.example.gitHubRepos.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GitHubReposDBHelper extends SQLiteOpenHelper {

    public static final String DATA_BASE_NAME = "gitHubRepos";
    public static final String TABLE_REPOSITORY = "repository";

    public static final String CREATE_TABLE_REPOSITORY = "create table repository(" +
            "_id                integer primary key autoincrement," +
            "name               text, " +
            "starCount      integer," +
            "forkCount       integer," +
            "fullName       text," +
            "description    text," +
            "url            text," +
            "language       text," +
            "watcherCount   integer," +
            "issuesCount    integer)";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_STAR_COUNT = "starCount";
    public static final String COLUMN_FORK_COUNT = "forkCount";
    public static final String COLUMN_FULL_NAME = "fullName";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_URL = "url";
    public static final String COLUMN_LANGUAGE = "language";
    public static final String COLUMN_WATCHER_COUNT = "watcherCount";
    public static final String COLUMN_ISSUES_COUNT = "issuesCount";

    public static final String DROP_TABLE = "drop table if exists ";

    public GitHubReposDBHelper(Context context) {
        super(context, DATA_BASE_NAME, null, 1);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_REPOSITORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE + TABLE_REPOSITORY);
        onCreate(db);
    }
}
