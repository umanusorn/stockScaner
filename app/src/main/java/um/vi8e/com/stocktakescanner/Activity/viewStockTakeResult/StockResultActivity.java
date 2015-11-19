package um.vi8e.com.stocktakescanner.Activity.viewStockTakeResult;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import um.vi8e.com.stocktakescanner.Activity.CoreActivity;
import um.vi8e.com.stocktakescanner.Model.ModelType;
import um.vi8e.com.stocktakescanner.R;
import um.vi8e.com.stocktakescanner.provider.stocktake.StocktakeColumns;
import um.vi8e.com.stocktakescanner.provider.stocktakeresult.StocktakeresultColumns;
import um.vi8e.com.stocktakescanner.utils.ActivityUi;
import um.vi8e.com.stocktakescanner.utils.IntentCaller;
import um.vi8e.com.stocktakescanner.utils.RecycleUtil;

public class StockResultActivity extends CoreActivity {
public static String currentStockTakeId;
public static Bundle thisSavedInstanceState;
StocktakeresultModel mStocktakeresultModel;
Bundle extras;

@Override public boolean onCreateActionMode(ActionMode mode, Menu menu) {
	MenuInflater inflater = mode.getMenuInflater();
	inflater.inflate(R.menu.menu_viewstock, menu);
	return false;
}
@Override public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
	return false;
}
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_view_stocktake_result);
	Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
	setSupportActionBar(toolbar);
	extras = getIntent().getExtras();
	ActivityUi.setToolBar(thisActivity,
	                      toolbar,
	                      extras.getString(StocktakeColumns.DATETIME_STARTED) +
	                      " " +
	                      extras.getString(StocktakeColumns.LOCATION));

	thisSavedInstanceState = savedInstanceState;

	RecycleUtil.setUpRecycleFragment(savedInstanceState, thisActivity, ModelType.STOCK_RESULT);

	currentStockTakeId = extras.getString(StocktakeColumns._ID);
	TextView location, date, status, save, cancel;

	mStocktakeresultModel =
			new StocktakeresultModel(extras.getString(StocktakeresultColumns._ID),
			                         extras.getString(StocktakeresultColumns.BARCODE),
			                         extras.getString(StocktakeresultColumns.QTY),
			                         extras.getString(StocktakeresultColumns
					                                          .DATETIME_SCANNNED));
	location = (TextView) findViewById(R.id.locationTitle);
	date = (TextView) findViewById(R.id.dateTitle);
	status = (TextView) findViewById(R.id.statusTitle);
	save = (TextView) findViewById(R.id.save);
	cancel = (TextView) findViewById(R.id.cancel);

	location.setText(extras.getString(StocktakeColumns.LOCATION));
	date.setText(extras.getString(StocktakeColumns.DATETIME_STARTED));
	status.setText(extras.getString(StocktakeColumns.STATUS));

	save.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			ViewStockResultFragment.mDataSet.get(0);
			for (StocktakeresultModel stocktakeresultModel :
					ViewStockResultFragment.mDataSet) {
				String id = stocktakeresultModel.getId();
				Uri uri = Uri.parse(String.valueOf(StocktakeresultColumns.CONTENT_URI) + "/" + id);
				thisActivity.getContentResolver().update(uri, stocktakeresultModel.getValues(), null, null);
			}

			Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
			finish();
		}
	});

	cancel.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			finish();
		}
	});
}

@Override
public void onClickFab(View view) {

	IntentCaller.startTakeFromBarCode(thisActivity, extras);

}



@Override
protected void  onResumeFragments(){
	super.onResumeFragments();
	RecycleUtil.setUpRecycleFragment(thisSavedInstanceState, thisActivity, ModelType.STOCK_RESULT);

}
}
