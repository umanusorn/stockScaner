package um.vi8e.com.stocktakescanner.Activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import um.vi8e.com.stocktakescanner.R;
import um.vi8e.com.stocktakescanner.utils.ActivityUi;

public class StartStockTake extends CoreActivity {

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_start_stock_take);
	Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
	setSupportActionBar(toolbar);
	ActivityUi.setToolBar(this,toolbar,"START STOCKTAKE");
}

}
