package um.vi8e.com.stocktakescanner.Activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import um.vi8e.com.stocktakescanner.R;
import um.vi8e.com.stocktakescanner.utils.ActivityUi;
import um.vi8e.com.stocktakescanner.utils.IntentCaller;
import um.vi8e.com.stocktakescanner.utils.QueryHelper;

public class LandingActivity extends CoreActivity {

Button viewStockTake;


@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_landing);
	Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
	setSupportActionBar(toolbar);
	ActivityUi.setToolBar(this, toolbar, "Stocktake Scanner");

	viewStockTake=(Button)findViewById(R.id.viewStockTake);

	viewStockTake.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			IntentCaller.viewStockTake(thisActivity);
		}
	});

	QueryHelper.genListAndTask(getApplicationContext());



}


}
