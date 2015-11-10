package um.vi8e.com.stocktakescanner.provider.stocktakeresult;

import android.net.Uri;
import android.provider.BaseColumns;

import um.vi8e.com.stocktakescanner.provider.MasterProvider;

/**
 * Columns for the {@code stocktakeresult} table.
 */
public class StocktakeresultColumns implements BaseColumns {
    public static final String TABLE_NAME = "stocktakeresult";
    public static final Uri CONTENT_URI = Uri.parse(MasterProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String STOCKTAKE_ID = "stocktake_id";

    public static final String BARCODE = "barcode";

    public static final String QTY = "qty";

    public static final String DATETIME_SCANNNED = "datetime_scannned";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            STOCKTAKE_ID,
            BARCODE,
            QTY,
            DATETIME_SCANNNED
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(STOCKTAKE_ID) || c.contains("." + STOCKTAKE_ID)) return true;
            if (c.equals(BARCODE) || c.contains("." + BARCODE)) return true;
            if (c.equals(QTY) || c.contains("." + QTY)) return true;
            if (c.equals(DATETIME_SCANNNED) || c.contains("." + DATETIME_SCANNNED)) return true;
        }
        return false;
    }

}
