package um.vi8e.com.stocktakescanner.provider.stocktake;

import android.support.annotation.Nullable;

import um.vi8e.com.stocktakescanner.provider.base.BaseModel;

/**
 * Data model for the {@code stocktake} table.
 */
public interface StocktakeModel extends BaseModel {

    /**
     * Get the {@code datetime_started} value.
     * Can be {@code null}.
     */
    @Nullable
    String getDatetimeStarted();

    /**
     * Get the {@code datetime_ended} value.
     * Can be {@code null}.
     */
    @Nullable
    String getDatetimeEnded();

    /**
     * Get the {@code status} value.
     * Can be {@code null}.
     */
    @Nullable
    String getStatus();

    /**
     * Get the {@code username} value.
     * Can be {@code null}.
     */
    @Nullable
    String getUsername();

    /**
     * Get the {@code device_detail} value.
     * Can be {@code null}.
     */
    @Nullable
    String getDeviceDetail();
}
