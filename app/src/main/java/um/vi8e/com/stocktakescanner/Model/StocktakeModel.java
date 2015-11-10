package um.vi8e.com.stocktakescanner.Model;

import android.support.annotation.Nullable;
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
