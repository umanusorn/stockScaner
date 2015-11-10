package um.vi8e.com.stocktakescanner.provider.stocktakeresult;

import android.support.annotation.Nullable;

import um.vi8e.com.stocktakescanner.provider.base.BaseModel;

/**
 * Data model for the {@code stocktakeresult} table.
 */
public interface StocktakeresultModel extends BaseModel {

    /**
     * Get the {@code stocktake_id} value.
     * Can be {@code null}.
     */
    @Nullable
    String getStocktakeId();

    /**
     * Get the {@code barcode} value.
     * Can be {@code null}.
     */
    @Nullable
    String getBarcode();

    /**
     * Get the {@code qty} value.
     * Can be {@code null}.
     */
    @Nullable
    String getQty();

    /**
     * Get the {@code datetime_scannned} value.
     * Can be {@code null}.
     */
    @Nullable
    String getDatetimeScannned();
}
