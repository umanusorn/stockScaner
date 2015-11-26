package um.vi8e.com.stocktakescanner.Activity;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;

import java.util.HashMap;

import um.vi8e.com.stocktakescanner.R;
import um.vi8e.com.stocktakescanner.provider.stocktakeresult.StocktakeresultColumns;
import um.vi8e.com.stocktakescanner.utils.ActivityUi;
import um.vi8e.com.stocktakescanner.utils.ProductApiKey;
import um.vi8e.com.stocktakescanner.utils.networkUtil;

public class BarcodeDetailActivity extends CoreActivity {

TextView barcode, dateTimeScanned, qty,price,desc, fullDetail;

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


	dateTimeScanned = (TextView) findViewById(R.id.dateTimeScanned);
	price =(TextView)findViewById(R.id.price);
	desc = (TextView)findViewById(R.id.desc);
	fullDetail=(TextView)findViewById(R.id.fulldetail);
	dateTimeScanned.setText(extras.getString(StocktakeresultColumns.DATETIME_SCANNNED));
	barcode = (TextView) findViewById(R.id.barcode_detail);
	barcode.setText(extras.getString(StocktakeresultColumns.BARCODE));


	// check if you are connected or not
	if (isConnected()) {
		//dateTimeScanned.setBackgroundColor(0xFF00CC00);
		//dateTimeScanned.setText("You are conncted");
	}
	else {
		//dateTimeScanned.setText("You are NOT conncted");
	}


	String banner = "CS";

	String test3 = "http://staging.uobapi.vi9e.com/product/CS/121/5156441/686686/7b04dbce9373f29617eb53d1bb38463e";
	String test1 = "http://hmkcode.appspot.com/rest/controller/get.json";
	new HttpAsyncTaskGET(this).execute(test3);

}

void setViewFromJson(HashMap<String, String> productInfo){
	barcode.setText(productInfo.get(ProductApiKey.BARCODE));
	price.setText(productInfo.get(ProductApiKey.REGULAR_PRICE));
	desc.setText(productInfo.get(ProductApiKey.DESCRIPTION));
}

public boolean isConnected() {
	ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
	NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	if (networkInfo != null && networkInfo.isConnected())
		return true;
	else
		return false;
}

public class HttpAsyncTaskGET extends AsyncTask<String, Void, String> {
	private BarcodeDetailActivity mBarcodeDetailActivity;

	public String getResult() {
		return result;
	}

	String result;

	public HttpAsyncTaskGET(BarcodeDetailActivity barcodeDetailActivity) {
		mBarcodeDetailActivity = barcodeDetailActivity;
	}

	@Override
	protected String doInBackground(String... urls) {

		return networkUtil.GET(urls[0]);
	}

	// onPostExecute displays the results of the AsyncTask.
	@Override
	protected void onPostExecute(String result) {
		this.result = result;
		try {

			setViewFromJson(networkUtil.jsonToMap(result));

		}
		catch (JSONException e) {
			e.printStackTrace();
		}

	}


}

}
