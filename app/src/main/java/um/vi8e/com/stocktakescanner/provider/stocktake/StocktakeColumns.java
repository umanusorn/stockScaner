package um.vi8e.com.stocktakescanner.provider.stocktake;

import android.net.Uri;
import android.provider.BaseColumns;

import um.vi8e.com.stocktakescanner.provider.MasterProvider;

/**
 * Columns for the {@code stocktake} table.
 */
public class StocktakeColumns implements BaseColumns {
    public static final String TABLE_NAME = "stocktake";
    public static final Uri CONTENT_URI = Uri.parse(MasterProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String DATETIME_STARTED = "datetime_started";

    public static final String DATETIME_ENDED = "datetime_ended";

    public static final String STATUS = "status";

    public static final String USERNAME = "username";

    public static final String DEVICE_DETAIL = "device_detail";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            DATETIME_STARTED,
            DATETIME_ENDED,
            STATUS,
            USERNAME,
            DEVICE_DETAIL
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(DATETIME_STARTED) || c.contains("." + DATETIME_STARTED)) return true;
            if (c.equals(DATETIME_ENDED) || c.contains("." + DATETIME_ENDED)) return true;
            if (c.equals(STATUS) || c.contains("." + STATUS)) return true;
            if (c.equals(USERNAME) || c.contains("." + USERNAME)) return true;
            if (c.equals(DEVICE_DETAIL) || c.contains("." + DEVICE_DETAIL)) return true;
        }
        return false;
    }

}
