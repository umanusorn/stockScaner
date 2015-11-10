package um.vi8e.com.stocktakescanner.provider.stocktakeresult;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;

import um.vi8e.com.stocktakescanner.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code stocktakeresult} table.
 */
public class StocktakeresultContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return StocktakeresultColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable StocktakeresultSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable StocktakeresultSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public StocktakeresultContentValues putStocktakeId(@Nullable String value) {
        mContentValues.put(StocktakeresultColumns.STOCKTAKE_ID, value);
        return this;
    }

    public StocktakeresultContentValues putStocktakeIdNull() {
        mContentValues.putNull(StocktakeresultColumns.STOCKTAKE_ID);
        return this;
    }

    public StocktakeresultContentValues putBarcode(@Nullable String value) {
        mContentValues.put(StocktakeresultColumns.BARCODE, value);
        return this;
    }

    public StocktakeresultContentValues putBarcodeNull() {
        mContentValues.putNull(StocktakeresultColumns.BARCODE);
        return this;
    }

    public StocktakeresultContentValues putQty(@Nullable String value) {
        mContentValues.put(StocktakeresultColumns.QTY, value);
        return this;
    }

    public StocktakeresultContentValues putQtyNull() {
        mContentValues.putNull(StocktakeresultColumns.QTY);
        return this;
    }

    public StocktakeresultContentValues putDatetimeScannned(@Nullable String value) {
        mContentValues.put(StocktakeresultColumns.DATETIME_SCANNNED, value);
        return this;
    }

    public StocktakeresultContentValues putDatetimeScannnedNull() {
        mContentValues.putNull(StocktakeresultColumns.DATETIME_SCANNNED);
        return this;
    }
}
