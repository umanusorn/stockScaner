package um.vi8e.com.stocktakescanner.provider.stocktake;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;

import um.vi8e.com.stocktakescanner.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code stocktake} table.
 */
public class StocktakeContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return StocktakeColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable StocktakeSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable StocktakeSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public StocktakeContentValues putDatetimeStarted(@Nullable String value) {
        mContentValues.put(StocktakeColumns.DATETIME_STARTED, value);
        return this;
    }

    public StocktakeContentValues putDatetimeStartedNull() {
        mContentValues.putNull(StocktakeColumns.DATETIME_STARTED);
        return this;
    }

    public StocktakeContentValues putDatetimeEnded(@Nullable String value) {
        mContentValues.put(StocktakeColumns.DATETIME_ENDED, value);
        return this;
    }

    public StocktakeContentValues putDatetimeEndedNull() {
        mContentValues.putNull(StocktakeColumns.DATETIME_ENDED);
        return this;
    }

    public StocktakeContentValues putStatus(@Nullable String value) {
        mContentValues.put(StocktakeColumns.STATUS, value);
        return this;
    }

    public StocktakeContentValues putStatusNull() {
        mContentValues.putNull(StocktakeColumns.STATUS);
        return this;
    }

    public StocktakeContentValues putUsername(@Nullable String value) {
        mContentValues.put(StocktakeColumns.USERNAME, value);
        return this;
    }

    public StocktakeContentValues putUsernameNull() {
        mContentValues.putNull(StocktakeColumns.USERNAME);
        return this;
    }

    public StocktakeContentValues putDeviceDetail(@Nullable String value) {
        mContentValues.put(StocktakeColumns.DEVICE_DETAIL, value);
        return this;
    }

    public StocktakeContentValues putDeviceDetailNull() {
        mContentValues.putNull(StocktakeColumns.DEVICE_DETAIL);
        return this;
    }

    public StocktakeContentValues putLocation(@Nullable String value) {
        mContentValues.put(StocktakeColumns.LOCATION, value);
        return this;
    }

    public StocktakeContentValues putLocationNull() {
        mContentValues.putNull(StocktakeColumns.LOCATION);
        return this;
    }
}
