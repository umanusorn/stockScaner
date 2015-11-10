package um.vi8e.com.stocktakescanner.provider.stocktake;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import um.vi8e.com.stocktakescanner.provider.base.AbstractSelection;

/**
 * Selection for the {@code stocktake} table.
 */
public class StocktakeSelection extends AbstractSelection<StocktakeSelection> {
    @Override
    protected Uri baseUri() {
        return StocktakeColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code StocktakeCursor} object, which is positioned before the first entry, or null.
     */
    public StocktakeCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new StocktakeCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public StocktakeCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code StocktakeCursor} object, which is positioned before the first entry, or null.
     */
    public StocktakeCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new StocktakeCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public StocktakeCursor query(Context context) {
        return query(context, null);
    }


    public StocktakeSelection id(long... value) {
        addEquals("stocktake." + StocktakeColumns._ID, toObjectArray(value));
        return this;
    }

    public StocktakeSelection idNot(long... value) {
        addNotEquals("stocktake." + StocktakeColumns._ID, toObjectArray(value));
        return this;
    }

    public StocktakeSelection orderById(boolean desc) {
        orderBy("stocktake." + StocktakeColumns._ID, desc);
        return this;
    }

    public StocktakeSelection orderById() {
        return orderById(false);
    }

    public StocktakeSelection datetimeStarted(String... value) {
        addEquals(StocktakeColumns.DATETIME_STARTED, value);
        return this;
    }

    public StocktakeSelection datetimeStartedNot(String... value) {
        addNotEquals(StocktakeColumns.DATETIME_STARTED, value);
        return this;
    }

    public StocktakeSelection datetimeStartedLike(String... value) {
        addLike(StocktakeColumns.DATETIME_STARTED, value);
        return this;
    }

    public StocktakeSelection datetimeStartedContains(String... value) {
        addContains(StocktakeColumns.DATETIME_STARTED, value);
        return this;
    }

    public StocktakeSelection datetimeStartedStartsWith(String... value) {
        addStartsWith(StocktakeColumns.DATETIME_STARTED, value);
        return this;
    }

    public StocktakeSelection datetimeStartedEndsWith(String... value) {
        addEndsWith(StocktakeColumns.DATETIME_STARTED, value);
        return this;
    }

    public StocktakeSelection orderByDatetimeStarted(boolean desc) {
        orderBy(StocktakeColumns.DATETIME_STARTED, desc);
        return this;
    }

    public StocktakeSelection orderByDatetimeStarted() {
        orderBy(StocktakeColumns.DATETIME_STARTED, false);
        return this;
    }

    public StocktakeSelection datetimeEnded(String... value) {
        addEquals(StocktakeColumns.DATETIME_ENDED, value);
        return this;
    }

    public StocktakeSelection datetimeEndedNot(String... value) {
        addNotEquals(StocktakeColumns.DATETIME_ENDED, value);
        return this;
    }

    public StocktakeSelection datetimeEndedLike(String... value) {
        addLike(StocktakeColumns.DATETIME_ENDED, value);
        return this;
    }

    public StocktakeSelection datetimeEndedContains(String... value) {
        addContains(StocktakeColumns.DATETIME_ENDED, value);
        return this;
    }

    public StocktakeSelection datetimeEndedStartsWith(String... value) {
        addStartsWith(StocktakeColumns.DATETIME_ENDED, value);
        return this;
    }

    public StocktakeSelection datetimeEndedEndsWith(String... value) {
        addEndsWith(StocktakeColumns.DATETIME_ENDED, value);
        return this;
    }

    public StocktakeSelection orderByDatetimeEnded(boolean desc) {
        orderBy(StocktakeColumns.DATETIME_ENDED, desc);
        return this;
    }

    public StocktakeSelection orderByDatetimeEnded() {
        orderBy(StocktakeColumns.DATETIME_ENDED, false);
        return this;
    }

    public StocktakeSelection status(String... value) {
        addEquals(StocktakeColumns.STATUS, value);
        return this;
    }

    public StocktakeSelection statusNot(String... value) {
        addNotEquals(StocktakeColumns.STATUS, value);
        return this;
    }

    public StocktakeSelection statusLike(String... value) {
        addLike(StocktakeColumns.STATUS, value);
        return this;
    }

    public StocktakeSelection statusContains(String... value) {
        addContains(StocktakeColumns.STATUS, value);
        return this;
    }

    public StocktakeSelection statusStartsWith(String... value) {
        addStartsWith(StocktakeColumns.STATUS, value);
        return this;
    }

    public StocktakeSelection statusEndsWith(String... value) {
        addEndsWith(StocktakeColumns.STATUS, value);
        return this;
    }

    public StocktakeSelection orderByStatus(boolean desc) {
        orderBy(StocktakeColumns.STATUS, desc);
        return this;
    }

    public StocktakeSelection orderByStatus() {
        orderBy(StocktakeColumns.STATUS, false);
        return this;
    }

    public StocktakeSelection username(String... value) {
        addEquals(StocktakeColumns.USERNAME, value);
        return this;
    }

    public StocktakeSelection usernameNot(String... value) {
        addNotEquals(StocktakeColumns.USERNAME, value);
        return this;
    }

    public StocktakeSelection usernameLike(String... value) {
        addLike(StocktakeColumns.USERNAME, value);
        return this;
    }

    public StocktakeSelection usernameContains(String... value) {
        addContains(StocktakeColumns.USERNAME, value);
        return this;
    }

    public StocktakeSelection usernameStartsWith(String... value) {
        addStartsWith(StocktakeColumns.USERNAME, value);
        return this;
    }

    public StocktakeSelection usernameEndsWith(String... value) {
        addEndsWith(StocktakeColumns.USERNAME, value);
        return this;
    }

    public StocktakeSelection orderByUsername(boolean desc) {
        orderBy(StocktakeColumns.USERNAME, desc);
        return this;
    }

    public StocktakeSelection orderByUsername() {
        orderBy(StocktakeColumns.USERNAME, false);
        return this;
    }

    public StocktakeSelection deviceDetail(String... value) {
        addEquals(StocktakeColumns.DEVICE_DETAIL, value);
        return this;
    }

    public StocktakeSelection deviceDetailNot(String... value) {
        addNotEquals(StocktakeColumns.DEVICE_DETAIL, value);
        return this;
    }

    public StocktakeSelection deviceDetailLike(String... value) {
        addLike(StocktakeColumns.DEVICE_DETAIL, value);
        return this;
    }

    public StocktakeSelection deviceDetailContains(String... value) {
        addContains(StocktakeColumns.DEVICE_DETAIL, value);
        return this;
    }

    public StocktakeSelection deviceDetailStartsWith(String... value) {
        addStartsWith(StocktakeColumns.DEVICE_DETAIL, value);
        return this;
    }

    public StocktakeSelection deviceDetailEndsWith(String... value) {
        addEndsWith(StocktakeColumns.DEVICE_DETAIL, value);
        return this;
    }

    public StocktakeSelection orderByDeviceDetail(boolean desc) {
        orderBy(StocktakeColumns.DEVICE_DETAIL, desc);
        return this;
    }

    public StocktakeSelection orderByDeviceDetail() {
        orderBy(StocktakeColumns.DEVICE_DETAIL, false);
        return this;
    }
}
