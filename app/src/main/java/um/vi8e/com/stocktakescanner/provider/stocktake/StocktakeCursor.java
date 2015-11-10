package um.vi8e.com.stocktakescanner.provider.stocktake;

import android.database.Cursor;
import android.support.annotation.Nullable;

import um.vi8e.com.stocktakescanner.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code stocktake} table.
 */
public class StocktakeCursor extends AbstractCursor implements StocktakeModel {
    public StocktakeCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(StocktakeColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code datetime_started} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getDatetimeStarted() {
        String res = getStringOrNull(StocktakeColumns.DATETIME_STARTED);
        return res;
    }

    /**
     * Get the {@code datetime_ended} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getDatetimeEnded() {
        String res = getStringOrNull(StocktakeColumns.DATETIME_ENDED);
        return res;
    }

    /**
     * Get the {@code status} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getStatus() {
        String res = getStringOrNull(StocktakeColumns.STATUS);
        return res;
    }

    /**
     * Get the {@code username} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getUsername() {
        String res = getStringOrNull(StocktakeColumns.USERNAME);
        return res;
    }

    /**
     * Get the {@code device_detail} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getDeviceDetail() {
        String res = getStringOrNull(StocktakeColumns.DEVICE_DETAIL);
        return res;
    }

    /**
     * Get the {@code location} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getLocation() {
        String res = getStringOrNull(StocktakeColumns.LOCATION);
        return res;
    }
}
