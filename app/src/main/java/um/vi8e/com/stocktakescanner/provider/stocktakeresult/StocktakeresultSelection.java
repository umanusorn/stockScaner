package um.vi8e.com.stocktakescanner.provider.stocktakeresult;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import um.vi8e.com.stocktakescanner.provider.base.AbstractSelection;

/**
 * Selection for the {@code stocktakeresult} table.
 */
public class StocktakeresultSelection extends AbstractSelection<StocktakeresultSelection> {
    @Override
    protected Uri baseUri() {
        return StocktakeresultColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code StocktakeresultCursor} object, which is positioned before the first entry, or null.
     */
    public StocktakeresultCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new StocktakeresultCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public StocktakeresultCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code StocktakeresultCursor} object, which is positioned before the first entry, or null.
     */
    public StocktakeresultCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new StocktakeresultCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public StocktakeresultCursor query(Context context) {
        return query(context, null);
    }


    public StocktakeresultSelection id(long... value) {
        addEquals("stocktakeresult." + StocktakeresultColumns._ID, toObjectArray(value));
        return this;
    }

    public StocktakeresultSelection idNot(long... value) {
        addNotEquals("stocktakeresult." + StocktakeresultColumns._ID, toObjectArray(value));
        return this;
    }

    public StocktakeresultSelection orderById(boolean desc) {
        orderBy("stocktakeresult." + StocktakeresultColumns._ID, desc);
        return this;
    }

    public StocktakeresultSelection orderById() {
        return orderById(false);
    }

    public StocktakeresultSelection stocktakeId(String... value) {
        addEquals(StocktakeresultColumns.STOCKTAKE_ID, value);
        return this;
    }

    public StocktakeresultSelection stocktakeIdNot(String... value) {
        addNotEquals(StocktakeresultColumns.STOCKTAKE_ID, value);
        return this;
    }

    public StocktakeresultSelection stocktakeIdLike(String... value) {
        addLike(StocktakeresultColumns.STOCKTAKE_ID, value);
        return this;
    }

    public StocktakeresultSelection stocktakeIdContains(String... value) {
        addContains(StocktakeresultColumns.STOCKTAKE_ID, value);
        return this;
    }

    public StocktakeresultSelection stocktakeIdStartsWith(String... value) {
        addStartsWith(StocktakeresultColumns.STOCKTAKE_ID, value);
        return this;
    }

    public StocktakeresultSelection stocktakeIdEndsWith(String... value) {
        addEndsWith(StocktakeresultColumns.STOCKTAKE_ID, value);
        return this;
    }

    public StocktakeresultSelection orderByStocktakeId(boolean desc) {
        orderBy(StocktakeresultColumns.STOCKTAKE_ID, desc);
        return this;
    }

    public StocktakeresultSelection orderByStocktakeId() {
        orderBy(StocktakeresultColumns.STOCKTAKE_ID, false);
        return this;
    }

    public StocktakeresultSelection barcode(String... value) {
        addEquals(StocktakeresultColumns.BARCODE, value);
        return this;
    }

    public StocktakeresultSelection barcodeNot(String... value) {
        addNotEquals(StocktakeresultColumns.BARCODE, value);
        return this;
    }

    public StocktakeresultSelection barcodeLike(String... value) {
        addLike(StocktakeresultColumns.BARCODE, value);
        return this;
    }

    public StocktakeresultSelection barcodeContains(String... value) {
        addContains(StocktakeresultColumns.BARCODE, value);
        return this;
    }

    public StocktakeresultSelection barcodeStartsWith(String... value) {
        addStartsWith(StocktakeresultColumns.BARCODE, value);
        return this;
    }

    public StocktakeresultSelection barcodeEndsWith(String... value) {
        addEndsWith(StocktakeresultColumns.BARCODE, value);
        return this;
    }

    public StocktakeresultSelection orderByBarcode(boolean desc) {
        orderBy(StocktakeresultColumns.BARCODE, desc);
        return this;
    }

    public StocktakeresultSelection orderByBarcode() {
        orderBy(StocktakeresultColumns.BARCODE, false);
        return this;
    }

    public StocktakeresultSelection qty(String... value) {
        addEquals(StocktakeresultColumns.QTY, value);
        return this;
    }

    public StocktakeresultSelection qtyNot(String... value) {
        addNotEquals(StocktakeresultColumns.QTY, value);
        return this;
    }

    public StocktakeresultSelection qtyLike(String... value) {
        addLike(StocktakeresultColumns.QTY, value);
        return this;
    }

    public StocktakeresultSelection qtyContains(String... value) {
        addContains(StocktakeresultColumns.QTY, value);
        return this;
    }

    public StocktakeresultSelection qtyStartsWith(String... value) {
        addStartsWith(StocktakeresultColumns.QTY, value);
        return this;
    }

    public StocktakeresultSelection qtyEndsWith(String... value) {
        addEndsWith(StocktakeresultColumns.QTY, value);
        return this;
    }

    public StocktakeresultSelection orderByQty(boolean desc) {
        orderBy(StocktakeresultColumns.QTY, desc);
        return this;
    }

    public StocktakeresultSelection orderByQty() {
        orderBy(StocktakeresultColumns.QTY, false);
        return this;
    }

    public StocktakeresultSelection datetimeScannned(String... value) {
        addEquals(StocktakeresultColumns.DATETIME_SCANNNED, value);
        return this;
    }

    public StocktakeresultSelection datetimeScannnedNot(String... value) {
        addNotEquals(StocktakeresultColumns.DATETIME_SCANNNED, value);
        return this;
    }

    public StocktakeresultSelection datetimeScannnedLike(String... value) {
        addLike(StocktakeresultColumns.DATETIME_SCANNNED, value);
        return this;
    }

    public StocktakeresultSelection datetimeScannnedContains(String... value) {
        addContains(StocktakeresultColumns.DATETIME_SCANNNED, value);
        return this;
    }

    public StocktakeresultSelection datetimeScannnedStartsWith(String... value) {
        addStartsWith(StocktakeresultColumns.DATETIME_SCANNNED, value);
        return this;
    }

    public StocktakeresultSelection datetimeScannnedEndsWith(String... value) {
        addEndsWith(StocktakeresultColumns.DATETIME_SCANNNED, value);
        return this;
    }

    public StocktakeresultSelection orderByDatetimeScannned(boolean desc) {
        orderBy(StocktakeresultColumns.DATETIME_SCANNNED, desc);
        return this;
    }

    public StocktakeresultSelection orderByDatetimeScannned() {
        orderBy(StocktakeresultColumns.DATETIME_SCANNNED, false);
        return this;
    }
}
