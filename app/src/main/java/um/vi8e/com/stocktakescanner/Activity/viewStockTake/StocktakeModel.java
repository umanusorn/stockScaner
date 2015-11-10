package um.vi8e.com.stocktakescanner.Activity.viewStockTake;

import android.content.ContentValues;
import android.support.annotation.Nullable;

import um.vi8e.com.stocktakescanner.provider.stocktake.StocktakeColumns;
/**
 * Created by Fixer on 11/10/2015.
 */
public class StocktakeModel implements um.vi8e.com.stocktakescanner.provider.stocktake.StocktakeModel {

String id,DatetimeStarted,DatetimeEnded,Status,Username,DeviceDetail;

public StocktakeModel(String datetimeStarted,
                      String datetimeEnded,
                      String status,
                      String username,
                      String deviceDetail)
{
	DatetimeStarted = datetimeStarted;
	DatetimeEnded = datetimeEnded;
	Status = status;
	Username = username;
	DeviceDetail = deviceDetail;
}



public StocktakeModel(ContentValues listValues) {
	setValues(listValues);
}

public StocktakeModel setValues(ContentValues values) {
	id=values.getAsString(StocktakeColumns._ID);
	DatetimeStarted = values.getAsString(StocktakeColumns.DATETIME_STARTED);
	DatetimeEnded=values.getAsString(StocktakeColumns.DATETIME_ENDED);
	Status=values.getAsString(StocktakeColumns.STATUS);
	Username=values.getAsString(StocktakeColumns.USERNAME);
	DeviceDetail=values.getAsString(StocktakeColumns.DEVICE_DETAIL);

	return this;
}

public
ContentValues getValues () {
	ContentValues values = new ContentValues ();
	values.put ( StocktakeColumns.DEVICE_DETAIL, DeviceDetail );
	values.put ( StocktakeColumns.USERNAME, getUsername() );
	values.put ( StocktakeColumns.STATUS, getStatus() );
	values.put ( StocktakeColumns.DATETIME_ENDED, getDatetimeEnded() );
	values.put ( StocktakeColumns.DATETIME_STARTED, getDatetimeStarted());
	values.put ( StocktakeColumns._ID,id );
	return values;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

@Nullable @Override public String getDatetimeStarted() {
	return DatetimeStarted;
}

public void setDatetimeStarted(String datetimeStarted) {
	DatetimeStarted = datetimeStarted;
}

@Nullable @Override public String getDatetimeEnded() {
	return DatetimeEnded;
}

public void setDatetimeEnded(String datetimeEnded) {
	DatetimeEnded = datetimeEnded;
}

@Nullable @Override public String getStatus() {
	return Status;
}

public void setStatus(String status) {
	Status = status;
}

@Nullable @Override public String getUsername() {
	return Username;
}

public void setUsername(String username) {
	Username = username;
}

@Nullable @Override public String getDeviceDetail() {
	return DeviceDetail;
}

public void setDeviceDetail(String deviceDetail) {
	DeviceDetail = deviceDetail;
}
}
