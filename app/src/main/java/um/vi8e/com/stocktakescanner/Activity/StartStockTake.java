package um.vi8e.com.stocktakescanner.Activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import um.vi8e.com.stocktakescanner.R;
import um.vi8e.com.stocktakescanner.utils.ActivityUi;
import um.vi8e.com.stocktakescanner.utils.IntentCaller;

public class StartStockTake extends CoreActivity {

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_start_stock_take);
	Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
	setSupportActionBar(toolbar);
	ActivityUi.setToolBar(this, toolbar, "START STOCKTAKE");


	Button startNow = (Button)findViewById(R.id.startNow);
	startNow.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			IntentCaller.simpleScanner(thisActivity);
		}
	});

}

}
