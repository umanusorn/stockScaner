package um.vi8e.com.stocktakescanner.Activity.viewStockTake;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import um.vi8e.com.stocktakescanner.Activity.CoreActivity;
import um.vi8e.com.stocktakescanner.Model.ModelType;
import um.vi8e.com.stocktakescanner.R;
import um.vi8e.com.stocktakescanner.utils.ActivityUi;
import um.vi8e.com.stocktakescanner.utils.RecycleUtil;

public class viewStockTakeActivity extends CoreActivity {
ViewStockFragment viewStockFragment;
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_view_stocktake);
	mToolbar= (Toolbar) findViewById(R.id.toolbar);
	setSupportActionBar(mToolbar);
	ActivityUi.setToolBar(thisActivity, mToolbar, "VIEW STOCKTAKE");
viewStockFragment = (ViewStockFragment) RecycleUtil.setUpRecycleFragment(savedInstanceState,
	                                                                                           thisActivity, ModelType
			                                                                                           .STOCK_TAKE);
}



}
