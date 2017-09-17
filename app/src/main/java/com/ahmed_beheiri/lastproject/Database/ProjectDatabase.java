package com.ahmed_beheiri.lastproject.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ahmed_beheiri on 15/09/17.
 */

public class ProjectDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "project.db";
    public static final int DATABASE_VEIRSION = 1;

    public ProjectDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VEIRSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String createDDlNames = "CREATE TABLE " + Contract.Tablename + " (" +
                Contract.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Contract.Name + " TEXT NOT NULL, " +
                Contract.Gender + " TEXT, " +
                Contract.Type + " TEXT, " +
                Contract.Age + " INTEGER );";

        db.execSQL(createDDlNames);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Contract.Tablename);
        onCreate(db);

    }
}
