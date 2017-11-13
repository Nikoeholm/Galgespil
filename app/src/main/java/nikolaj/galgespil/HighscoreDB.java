package nikolaj.galgespil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nikolaj on 13-11-2017.
 */

public class HighscoreDB extends SQLiteOpenHelper {
    static final int VERSION = 1;
    static final String DATABASE = "database.db";
    static final String TABLE = "spillere";

    static final String ID ="id";
    static final String NAVN = "navn";
    static final String SCORE = "score";

    public HighscoreDB(Context context) {
        super(context,DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE + " (" + ID + " int primary_key, " + NAVN + " text, " + SCORE + " int" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table" + TABLE);
        this.onCreate(db);

    }
}
