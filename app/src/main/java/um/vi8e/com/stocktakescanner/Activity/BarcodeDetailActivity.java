package um.vi8e.com.stocktakescanner.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import um.vi8e.com.stocktakescanner.R;
import um.vi8e.com.stocktakescanner.provider.stocktakeresult.StocktakeresultColumns;
import um.vi8e.com.stocktakescanner.utils.ActivityUi;

public class BarcodeDetailActivity extends CoreActivity {

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_barcode);
	Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
	setSupportActionBar(toolbar);
	tabLayout = (TabLayout) findViewById(R.id.tabLayout);
	tabLayout.setVisibility(View.GONE);
	ActivityUi.setToolBar(this, toolbar, "Barcode Detail");
	Bundle extras = getIntent().getExtras();

	TextView barcode,dateTimeScanned,qty;



	dateTimeScanned=(TextView)findViewById(R.id.dateTimeScanned);
	dateTimeScanned.setText(extras.getString(StocktakeresultColumns.DATETIME_SCANNNED));
	barcode= (TextView)findViewById(R.id.barcode_detail);
	barcode.setText(extras.getString(StocktakeresultColumns.BARCODE));
}

}
