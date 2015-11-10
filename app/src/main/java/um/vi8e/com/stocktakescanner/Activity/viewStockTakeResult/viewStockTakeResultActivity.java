package um.vi8e.com.stocktakescanner.Activity.viewStockTakeResult;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import um.vi8e.com.stocktakescanner.Activity.CoreActivity;
import um.vi8e.com.stocktakescanner.R;
import um.vi8e.com.stocktakescanner.utils.ActivityUi;

public class viewStockTakeResultActivity extends CoreActivity {

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_view_stocktake_result);
	Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
	setSupportActionBar(toolbar);
	ActivityUi.setToolBar(thisActivity,toolbar,"12 Nov Location 1");

}

}
