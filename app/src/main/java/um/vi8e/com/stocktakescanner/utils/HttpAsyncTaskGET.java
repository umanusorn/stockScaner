package um.vi8e.com.stocktakescanner.utils;

import android.os.AsyncTask;

import um.vi8e.com.stocktakescanner.Activity.BarcodeDetailActivity;
/**
 * Created by Fixer on 11/26/2015.
 */
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
	this.result=result;

	}


}
