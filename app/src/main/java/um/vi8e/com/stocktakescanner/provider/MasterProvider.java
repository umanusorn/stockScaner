package um.vi8e.com.stocktakescanner.provider;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import java.util.Arrays;

import um.vi8e.com.stocktakescanner.BuildConfig;
import um.vi8e.com.stocktakescanner.provider.base.BaseContentProvider;
import um.vi8e.com.stocktakescanner.provider.stocktake.StocktakeColumns;
import um.vi8e.com.stocktakescanner.provider.stocktakeresult.StocktakeresultColumns;
import um.vi8e.com.stocktakescanner.provider.user.UserColumns;

public class MasterProvider extends BaseContentProvider {
    private static final String TAG = MasterProvider.class.getSimpleName();

    private static final boolean DEBUG = BuildConfig.DEBUG;

    private static final String TYPE_CURSOR_ITEM = "vnd.android.cursor.item/";
    private static final String TYPE_CURSOR_DIR = "vnd.android.cursor.dir/";

    public static final String AUTHORITY = "um.vi8e.com.stocktakescanner.provider";
    public static final String CONTENT_URI_BASE = "content://" + AUTHORITY;

    private static final int URI_TYPE_STOCKTAKE = 0;
    private static final int URI_TYPE_STOCKTAKE_ID = 1;

    private static final int URI_TYPE_STOCKTAKERESULT = 2;
    private static final int URI_TYPE_STOCKTAKERESULT_ID = 3;

    private static final int URI_TYPE_USER = 4;
    private static final int URI_TYPE_USER_ID = 5;



    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, StocktakeColumns.TABLE_NAME, URI_TYPE_STOCKTAKE);
        URI_MATCHER.addURI(AUTHORITY, StocktakeColumns.TABLE_NAME + "/#", URI_TYPE_STOCKTAKE_ID);
        URI_MATCHER.addURI(AUTHORITY, StocktakeresultColumns.TABLE_NAME, URI_TYPE_STOCKTAKERESULT);
        URI_MATCHER.addURI(AUTHORITY, StocktakeresultColumns.TABLE_NAME + "/#", URI_TYPE_STOCKTAKERESULT_ID);
        URI_MATCHER.addURI(AUTHORITY, UserColumns.TABLE_NAME, URI_TYPE_USER);
        URI_MATCHER.addURI(AUTHORITY, UserColumns.TABLE_NAME + "/#", URI_TYPE_USER_ID);
    }

    @Override
    protected SQLiteOpenHelper createSqLiteOpenHelper() {
        return UmSQLiteHelper.getInstance(getContext());
    }

    @Override
    protected boolean hasDebug() {
        return DEBUG;
    }

    @Override
    public String getType(Uri uri) {
        int match = URI_MATCHER.match(uri);
        switch (match) {
            case URI_TYPE_STOCKTAKE:
                return TYPE_CURSOR_DIR + StocktakeColumns.TABLE_NAME;
            case URI_TYPE_STOCKTAKE_ID:
                return TYPE_CURSOR_ITEM + StocktakeColumns.TABLE_NAME;

            case URI_TYPE_STOCKTAKERESULT:
                return TYPE_CURSOR_DIR + StocktakeresultColumns.TABLE_NAME;
            case URI_TYPE_STOCKTAKERESULT_ID:
                return TYPE_CURSOR_ITEM + StocktakeresultColumns.TABLE_NAME;

            case URI_TYPE_USER:
                return TYPE_CURSOR_DIR + UserColumns.TABLE_NAME;
            case URI_TYPE_USER_ID:
                return TYPE_CURSOR_ITEM + UserColumns.TABLE_NAME;

        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (DEBUG) Log.d(TAG, "insert uri=" + uri + " values=" + values);
        return super.insert(uri, values);
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        if (DEBUG) Log.d(TAG, "bulkInsert uri=" + uri + " values.length=" + values.length);
        return super.bulkInsert(uri, values);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (DEBUG) Log.d(TAG, "update uri=" + uri + " values=" + values + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        return super.update(uri, values, selection, selectionArgs);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        if (DEBUG) Log.d(TAG, "delete uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        return super.delete(uri, selection, selectionArgs);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (DEBUG)
            Log.d(TAG, "query uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs) + " sortOrder=" + sortOrder
                    + " groupBy=" + uri.getQueryParameter(QUERY_GROUP_BY) + " having=" + uri.getQueryParameter(QUERY_HAVING) + " limit=" + uri.getQueryParameter(QUERY_LIMIT));
        return super.query(uri, projection, selection, selectionArgs, sortOrder);
    }

    @Override
    protected QueryParams getQueryParams(Uri uri, String selection, String[] projection) {
        QueryParams res = new QueryParams();
        String id = null;
        int matchedId = URI_MATCHER.match(uri);
        switch (matchedId) {
            case URI_TYPE_STOCKTAKE:
            case URI_TYPE_STOCKTAKE_ID:
                res.table = StocktakeColumns.TABLE_NAME;
                res.idColumn = StocktakeColumns._ID;
                res.tablesWithJoins = StocktakeColumns.TABLE_NAME;
                res.orderBy = StocktakeColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_STOCKTAKERESULT:
            case URI_TYPE_STOCKTAKERESULT_ID:
                res.table = StocktakeresultColumns.TABLE_NAME;
                res.idColumn = StocktakeresultColumns._ID;
                res.tablesWithJoins = StocktakeresultColumns.TABLE_NAME;
                res.orderBy = StocktakeresultColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_USER:
            case URI_TYPE_USER_ID:
                res.table = UserColumns.TABLE_NAME;
                res.idColumn = UserColumns._ID;
                res.tablesWithJoins = UserColumns.TABLE_NAME;
                res.orderBy = UserColumns.DEFAULT_ORDER;
                break;

            default:
                throw new IllegalArgumentException("The uri '" + uri + "' is not supported by this ContentProvider");
        }

        switch (matchedId) {
            case URI_TYPE_STOCKTAKE_ID:
            case URI_TYPE_STOCKTAKERESULT_ID:
            case URI_TYPE_USER_ID:
                id = uri.getLastPathSegment();
        }
        if (id != null) {
            if (selection != null) {
                res.selection = res.table + "." + res.idColumn + "=" + id + " and (" + selection + ")";
            } else {
                res.selection = res.table + "." + res.idColumn + "=" + id;
            }
        } else {
            res.selection = selection;
        }
        return res;
    }
}
