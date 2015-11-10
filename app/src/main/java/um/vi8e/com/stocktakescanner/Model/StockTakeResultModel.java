package um.vi8e.com.stocktakescanner.Model;

import android.support.annotation.Nullable;

import um.vi8e.com.stocktakescanner.provider.stocktakeresult.StocktakeresultModel;
/**
 * Created by Fixer on 11/10/2015.
 */
public class StockTakeResultModel implements StocktakeresultModel {

String StocktakeId,Barcode,Qty,DatetimeScannned;

public StockTakeResultModel(String stocktakeId, String barcode, String qty, String datetimeScannned) {
	StocktakeId = stocktakeId;
	Barcode = barcode;
	Qty = qty;
	DatetimeScannned = datetimeScannned;
}

@Nullable @Override public String getStocktakeId() {
	return StocktakeId;
}

public void setStocktakeId(String stocktakeId) {
	StocktakeId = stocktakeId;
}

@Nullable @Override public String getBarcode() {
	return Barcode;
}

public void setBarcode(String barcode) {
	Barcode = barcode;
}

@Nullable @Override public String getQty() {
	return Qty;
}

public void setQty(String qty) {
	Qty = qty;
}

@Nullable @Override public String getDatetimeScannned() {
	return DatetimeScannned;
}

public void setDatetimeScannned(String datetimeScannned) {
	DatetimeScannned = datetimeScannned;
}
}
