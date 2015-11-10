package um.vi8e.com.stocktakescanner.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import um.vi8e.com.stocktakescanner.BuildConfig;
import um.vi8e.com.stocktakescanner.provider.stocktake.StocktakeColumns;
import um.vi8e.com.stocktakescanner.provider.stocktakeresult.StocktakeresultColumns;
import um.vi8e.com.stocktakescanner.provider.user.UserColumns;

public class UmSQLiteHelper extends SQLiteOpenHelper {
    private static final String TAG = UmSQLiteHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "um.db";
    private static final int DATABASE_VERSION = 4;
    private static UmSQLiteHelper sInstance;
    private final Context mContext;
    private final UmSQLiteHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    public static final String SQL_CREATE_TABLE_STOCKTAKE = "CREATE TABLE IF NOT EXISTS "
            + StocktakeColumns.TABLE_NAME + " ( "
            + StocktakeColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + StocktakeColumns.DATETIME_STARTED + " TEXT DEFAULT '0', "
            + StocktakeColumns.DATETIME_ENDED + " TEXT DEFAULT '0', "
            + StocktakeColumns.STATUS + " TEXT DEFAULT '0', "
            + StocktakeColumns.USERNAME + " TEXT DEFAULT '0', "
            + StocktakeColumns.DEVICE_DETAIL + " TEXT DEFAULT '0' "
            + " );";

    public static final String SQL_CREATE_TABLE_STOCKTAKERESULT = "CREATE TABLE IF NOT EXISTS "
            + StocktakeresultColumns.TABLE_NAME + " ( "
            + StocktakeresultColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + StocktakeresultColumns.STOCKTAKE_ID + " TEXT DEFAULT '0', "
            + StocktakeresultColumns.BARCODE + " TEXT DEFAULT '0', "
            + StocktakeresultColumns.QTY + " TEXT DEFAULT '0', "
            + StocktakeresultColumns.DATETIME_SCANNNED + " TEXT DEFAULT '0' "
            + " );";

    public static final String SQL_CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS "
            + UserColumns.TABLE_NAME + " ( "
            + UserColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + UserColumns.USER_TITLE + " TEXT DEFAULT '0', "
            + UserColumns.IMG_PATH + " TEXT DEFAULT '0' "
            + " );";

    // @formatter:on

    public static UmSQLiteHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static UmSQLiteHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static UmSQLiteHelper newInstancePreHoneycomb(Context context) {
        return new UmSQLiteHelper(context);
    }

    private UmSQLiteHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new UmSQLiteHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static UmSQLiteHelper newInstancePostHoneycomb(Context context) {
        return new UmSQLiteHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private UmSQLiteHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new UmSQLiteHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_STOCKTAKE);
        db.execSQL(SQL_CREATE_TABLE_STOCKTAKERESULT);
        db.execSQL(SQL_CREATE_TABLE_USER);
        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            setForeignKeyConstraintsEnabled(db);
        }
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setForeignKeyConstraintsEnabledPreJellyBean(db);
        } else {
            setForeignKeyConstraintsEnabledPostJellyBean(db);
        }
    }

    private void setForeignKeyConstraintsEnabledPreJellyBean(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setForeignKeyConstraintsEnabledPostJellyBean(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
    }
}
