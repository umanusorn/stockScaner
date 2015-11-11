package um.vi8e.com.stocktakescanner.Activity.viewStockTake;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import um.vi8e.com.stocktakescanner.Activity.CoreActivity;
import um.vi8e.com.stocktakescanner.Model.ModelType;
import um.vi8e.com.stocktakescanner.R;
import um.vi8e.com.stocktakescanner.utils.ActivityUi;
import um.vi8e.com.stocktakescanner.utils.RecycleUtil;

public class viewStockTakeActivity extends CoreActivity {

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_view_stocktake);
	Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
	setSupportActionBar(toolbar);

	RecycleUtil.setUpRecycleFragment(savedInstanceState, thisActivity, ModelType.STOCK_TAKE);
	ActivityUi.setToolBar(thisActivity,toolbar,"VIEW STOCKTAKE");

}

}
