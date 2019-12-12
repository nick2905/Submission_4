package com.dicoding.submission_4.Database;

import android.provider.BaseColumns;

public class DatabaseContract {
    public static String TABLE_FAV_MOVIE = "fav_movie";
    public static String TABLE_FAV_TVSHOW = "fav_tvshow";

    public static final class NoteColumns implements BaseColumns{
        public static String TITLE = "title";
        public static String DATE = "date";
        public static String RATE = "rate";
        public static String DESCRIPTION = "description";
        public static String PHOTO = "photo";
        public static String LANGUAGE = "language";
    }
}
