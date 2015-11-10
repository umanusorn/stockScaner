package um.vi8e.com.stocktakescanner.Activity.viewStockTakeResult;

import android.content.ContentValues;
import android.support.annotation.Nullable;

import um.vi8e.com.stocktakescanner.provider.stocktakeresult.StocktakeresultColumns;
/**
 * Created by Fixer on 11/10/2015.
 */
public class StocktakeresultModel2
		implements um.vi8e.com.stocktakescanner.provider.stocktakeresult.StocktakeresultModel {

String id,StocktakeId,Barcode,Qty,DatetimeScannned;

public StocktakeresultModel2(String stocktakeId, String barcode, String qty, String datetimeScannned) {
	StocktakeId = stocktakeId;
	Barcode = barcode;
	Qty = qty;
	DatetimeScannned = datetimeScannned;

}

public StocktakeresultModel2(ContentValues listValues) {
	setValues(listValues);
}


public StocktakeresultModel2 setValues(ContentValues values) {
	id=values.getAsString(StocktakeresultColumns._ID);
	StocktakeId = values.getAsString(StocktakeresultColumns.STOCKTAKE_ID);
	Barcode= values.getAsString(StocktakeresultColumns.BARCODE);
	Qty= values.getAsString(StocktakeresultColumns.QTY);
	DatetimeScannned = values.getAsString(StocktakeresultColumns.DATETIME_SCANNNED);

	return this;
}

public
ContentValues getValues () {
	ContentValues values = new ContentValues ();
	values.put ( StocktakeresultColumns.STOCKTAKE_ID, getStocktakeId() );
	values.put ( StocktakeresultColumns.QTY, getQty() );
	values.put ( StocktakeresultColumns.DATETIME_SCANNNED, getDatetimeScannned() );
	values.put ( StocktakeresultColumns.BARCODE, getBarcode() );

	values.put ( StocktakeresultColumns._ID,id );
	return values;
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
