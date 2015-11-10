package um.vi8e.com.stocktakescanner.provider.stocktakeresult;

import android.database.Cursor;
import android.support.annotation.Nullable;

import um.vi8e.com.stocktakescanner.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code stocktakeresult} table.
 */
public class StocktakeresultCursor extends AbstractCursor implements StocktakeresultModel {
    public StocktakeresultCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(StocktakeresultColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code stocktake_id} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getStocktakeId() {
        String res = getStringOrNull(StocktakeresultColumns.STOCKTAKE_ID);
        return res;
    }

    /**
     * Get the {@code barcode} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getBarcode() {
        String res = getStringOrNull(StocktakeresultColumns.BARCODE);
        return res;
    }

    /**
     * Get the {@code qty} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getQty() {
        String res = getStringOrNull(StocktakeresultColumns.QTY);
        return res;
    }

    /**
     * Get the {@code datetime_scannned} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getDatetimeScannned() {
        String res = getStringOrNull(StocktakeresultColumns.DATETIME_SCANNNED);
        return res;
    }
}
