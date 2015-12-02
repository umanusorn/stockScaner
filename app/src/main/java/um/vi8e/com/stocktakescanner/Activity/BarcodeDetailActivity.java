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
import java.util.Map;

import um.vi8e.com.stocktakescanner.R;
import um.vi8e.com.stocktakescanner.provider.stocktakeresult.StocktakeresultColumns;
import um.vi8e.com.stocktakescanner.utils.ActivityUi;
import um.vi8e.com.stocktakescanner.utils.ProductApiKey;
import um.vi8e.com.stocktakescanner.utils.networkUtil;

public class BarcodeDetailActivity extends CoreActivity {

TextView barcodeTv, dateTimeScannedTv, qty, priceTv, descTv, fullDetailTv;

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


	final String barcode = extras.getString(StocktakeresultColumns.BARCODE);
	final String dateTime = extras.getString(StocktakeresultColumns.DATETIME_SCANNNED);

	dateTimeScannedTv = (TextView) findViewById(R.id.dateTimeScanned);
	priceTv = (TextView) findViewById(R.id.price);
	descTv = (TextView) findViewById(R.id.desc);
	fullDetailTv = (TextView) findViewById(R.id.fulldetail);
	barcodeTv = (TextView) findViewById(R.id.barcode_detail);

	barcodeTv.setText(barcode);
	dateTimeScannedTv.setText(dateTime);


	// check if you are connected or not
	if (isConnected()) {
		//dateTimeScannedTv.setBackgroundColor(0xFF00CC00);
		//dateTimeScannedTv.setText("You are conncted");
	}
	else {
		//dateTimeScannedTv.setText("You are NOT conncted");
	}

	//686686

	String test3 = "http://staging.uobapi.vi9e.com/product/CS/121/5156441/"+barcode+"/7b04dbce9373f29617eb53d1bb38463e";
	String test1 = "http://hmkcode.appspot.com/rest/controller/get.json";
	new HttpAsyncTaskGET().execute(test3);

}

void setViewFromJson(HashMap<String, String> productInfo){
	//barcodeTv.setText(productInfo.get(ProductApiKey.BARCODE));


	String fulldetail="";
	for (Map.Entry<String, String> entry : productInfo.entrySet())
	{
		fulldetail+= entry.getKey() + ":\t\t" + entry.getValue()+"\n";
	}
	fullDetailTv.setText(fulldetail);

	if(productInfo.get(ProductApiKey.STATUS).equals("AS")){
		priceTv.setText(productInfo.get(ProductApiKey.REGULAR_PRICE));
		descTv.setText(productInfo.get(ProductApiKey.DESCRIPTION));
	}
	else{
		priceTv.setText("-");
		descTv.setText("-");
	}
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

	public String getResult() {
		return result;
	}

	String result;

	public HttpAsyncTaskGET() {
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
