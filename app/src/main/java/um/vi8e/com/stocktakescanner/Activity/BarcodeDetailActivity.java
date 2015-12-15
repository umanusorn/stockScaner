package um.vi8e.com.stocktakescanner.Activity;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import um.vi8e.com.stocktakescanner.Activity.viewStockTakeResult.StocktakeresultModel;
import um.vi8e.com.stocktakescanner.Activity.zbar.ScannerFragmentActivity;
import um.vi8e.com.stocktakescanner.R;
import um.vi8e.com.stocktakescanner.provider.stocktake.StocktakeColumns;
import um.vi8e.com.stocktakescanner.provider.stocktakeresult.StocktakeresultColumns;
import um.vi8e.com.stocktakescanner.utils.ActivityUi;
import um.vi8e.com.stocktakescanner.utils.Const;
import um.vi8e.com.stocktakescanner.utils.CustomDialog;
import um.vi8e.com.stocktakescanner.utils.ProductApiKey;
import um.vi8e.com.stocktakescanner.utils.networkUtil;

public class BarcodeDetailActivity extends CoreActivity {

TextView barcodeTv, dateTimeScannedTv, qty, descTv, fullDetailTv, cancel, saveNExit, saveNContinue;
TextView normalPrice, promoPrice, itemCode;
ImageView minusBtn, plusBtn;
private String  mStockId;
private String  mItemCodeValue;
private Toolbar mToolbar;
private String  mDateTime;
private String  mBarcode;
private String  mLocation;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_barcode);
	mToolbar = (Toolbar) findViewById(R.id.toolbar);
	setSupportActionBar(mToolbar);
	tabLayout = (TabLayout) findViewById(R.id.tabLayout);
	tabLayout.setVisibility(View.GONE);

	Bundle extras = getIntent().getExtras();

	mBarcode = extras.getString(StocktakeresultColumns.BARCODE);
	mDateTime = extras.getString(StocktakeresultColumns.DATETIME_SCANNNED);
	mStockId = extras.getString(StocktakeresultColumns.STOCKTAKE_ID);
	mLocation = extras.getString(StocktakeColumns.LOCATION);


	dateTimeScannedTv = (TextView) findViewById(R.id.dateTimeScanned);
	//priceTv = (TextView) findViewById(R.id.barcodeValue);
	descTv = (TextView) findViewById(R.id.desc);
	fullDetailTv = (TextView) findViewById(R.id.fulldetail);
	barcodeTv = (TextView) findViewById(R.id.barcodeValue);
	cancel = (TextView) findViewById(R.id.cancel);
	saveNExit = (TextView) findViewById(R.id.saveNExit);
	saveNContinue = (TextView) findViewById(R.id.saveNContinue);

	normalPrice = (TextView) findViewById(R.id.normalPrice);
	promoPrice = (TextView) findViewById(R.id.promoPrice);
	itemCode = (TextView) findViewById(R.id.itemCodeValue);
	minusBtn = (ImageView) findViewById(R.id.minusBtn);
	plusBtn = (ImageView) findViewById(R.id.plusBtn);

	Log.d("verifyNoCon", extras.getString(Const.NO_CONTINUE) );
	if (extras.getString(Const.NO_CONTINUE) != null) {
		Log.d("barcodeNoCon", "noCon");
		saveNContinue.setVisibility(View.GONE);
	}

	qty = (TextView) findViewById(R.id.qtyText);


	plusBtn.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			CustomDialog.plusQty(1, qty, minusBtn, thisActivity);
		}
	});

	minusBtn.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			CustomDialog.plusQty(-1, qty, minusBtn, thisActivity);
		}
	});
	barcodeTv.setText(mBarcode);
	dateTimeScannedTv.setText(mDateTime);

	saveNContinue.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			StocktakeresultModel stocktakeresultModel =
					ScannerActivity.saveToDB(getApplicationContext(),
					                         mBarcode, mStockId, mLocation, mDateTime, qty.getText().toString());
			Toast.makeText(getApplicationContext(), "saved", Toast.LENGTH_LONG).show();
			ScannerFragmentActivity.isBack = false;
			ScannerFragmentActivity.mStocktakeId = stocktakeresultModel.getStocktakeId();
			finish();
		}
	});

	saveNExit.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			ScannerActivity.saveToDB(getApplicationContext(),
			                         mBarcode, mStockId, mLocation, mDateTime, qty.getText().toString());
			Toast.makeText(getApplicationContext(), "saved", Toast.LENGTH_LONG).show();
			ScannerFragmentActivity.isBack = true;
			finish();
		}
	});

	cancel.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			finish();
		}
	});

	//686686
	String test3 = Const.getApiUrl(mBarcode);
	new HttpAsyncTaskGET().execute(test3);

}

void setViewFromJson(HashMap<String, String> productInfo) {
	//barcodeTv.setText(productInfo.get(ProductApiKey.BARCODE));
	mItemCodeValue = productInfo.get(ProductApiKey.ITEM_CODE);
	String fulldetail = "";
	for (Map.Entry<String, String> entry : productInfo.entrySet()) {
		fulldetail += entry.getKey() + ":\t\t" + entry.getValue() + "\n";
	}
	fullDetailTv.setText(fulldetail);

	if (productInfo.get(ProductApiKey.STATUS).equals("AS")) {
		descTv.setText(productInfo.get(ProductApiKey.DESCRIPTION));

		itemCode.setText(mItemCodeValue);
		normalPrice.setText(productInfo.get(ProductApiKey.REGULAR_PRICE));
	}
	else {
		descTv.setText("-");
		promoPrice.setText("-");
		normalPrice.setText("-");
		itemCode.setText("-");
	}

	ActivityUi.setToolBar(this, mToolbar, "SKU  " + mItemCodeValue);
	mToolbar.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

}

public boolean isConnected() {
	ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
	NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	if (networkInfo != null && networkInfo.isConnected()) {
		return true;
	}
	else {
		return false;
	}
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
