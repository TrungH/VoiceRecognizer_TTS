package hoang.trung.tintin.speaktolearnenglish.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import hoang.trung.tintin.speaktolearnenglish.common.Common;

/**
 * Created by Tintin on 4/20/2017.
 */

public class SqlLiteHelper extends SQLiteOpenHelper {
    /* Inner class that defines the table contents */
    public static class FeedWord implements BaseColumns {
        public static final String TABLE_NAME = "words";
        public static final String COLUMN_NAME_WORD = "word";
        public static final String COLUMN_NAME_MEANING = "meaning";
        public static final String COLUMN_NAME_SENTENCE1 = "sentence1";
        public static final String COLUMN_NAME_SENTENCE2 = "sentence2";
    }
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "com.android.speak.to.learn.english.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedWord.TABLE_NAME + " (" +
                    FeedWord._ID + " INTEGER PRIMARY KEY," +
                    FeedWord.COLUMN_NAME_WORD + " TEXT," +
                    FeedWord.COLUMN_NAME_SENTENCE1 + " TEXT," +
                    FeedWord.COLUMN_NAME_SENTENCE2 + " TEXT," +
                    FeedWord.COLUMN_NAME_MEANING + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedWord.TABLE_NAME;
    public SqlLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        //db.execSQL(SQL_DELETE_ENTRIES);
        //onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //onUpgrade(db, oldVersion, newVersion);
    }
    public void addWord(String word, String meaning){
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedWord.COLUMN_NAME_WORD, word);
        values.put(FeedWord.COLUMN_NAME_MEANING, meaning);

// Insert the new row, returning the primary key value of the new row
        db.insert(FeedWord.TABLE_NAME, null, values);
    }
    public String readWord(String word){
        SQLiteDatabase db = getReadableDatabase();
        String result = null;
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                FeedWord._ID,
                FeedWord.COLUMN_NAME_WORD,
                FeedWord.COLUMN_NAME_SENTENCE1,
                FeedWord.COLUMN_NAME_SENTENCE2,
                FeedWord.COLUMN_NAME_MEANING
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = FeedWord.COLUMN_NAME_WORD + " = ?";
        String[] selectionArgs = { word };



        Cursor cursor = db.query(
                FeedWord.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        while(cursor.moveToNext()) {
            //Log.d(Common.TAG, "scan data");
            long itemId = cursor.getColumnIndexOrThrow(FeedWord.COLUMN_NAME_MEANING);
            result = cursor.getString((int)itemId);
        }
        cursor.close();

        return result;
    }

}
