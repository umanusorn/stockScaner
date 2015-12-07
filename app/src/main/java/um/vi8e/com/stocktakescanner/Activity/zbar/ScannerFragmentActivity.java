package um.vi8e.com.stocktakescanner.Activity.zbar;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.json.JSONException;

import um.vi8e.com.stocktakescanner.R;
import um.vi8e.com.stocktakescanner.utils.Const;
import um.vi8e.com.stocktakescanner.utils.networkUtil;

public class ScannerFragmentActivity extends AppCompatActivity {

private ScannerFragment mScannerFragment;
ZBarBtnFragment mZBarBtnFragment;
public ZBarBtnTopInfo mZBarBtnTopInfo;

@Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_scanner_fragment);
        FragmentManager fragmentManager;
        fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    mScannerFragment = new ScannerFragment();
        fragmentTransaction.add(R.id.scannerFrame, mScannerFragment);
    mZBarBtnFragment=new ZBarBtnFragment();
        fragmentTransaction.add(R.id.zbarBtnFrame, mZBarBtnFragment);
    mZBarBtnTopInfo=new ZBarBtnTopInfo();
    fragmentTransaction.add(R.id.zbarTopInfo, mZBarBtnTopInfo);
        fragmentTransaction.commit();


    }

@Override protected void onResumeFragments() {
    super.onResumeFragments();/*
    mScannerFragment.mScannerView.stopCamera();
    mScannerFragment.mScannerView.startCamera();*/
   // mScannerFragment.mScannerView.setVisibility(View.INVISIBLE);

    mZBarBtnFragment.mAddItemTv.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
            mScannerFragment.setIsScan(!mScannerFragment.isScan());
        }
    });
    mZBarBtnFragment.mCancelTv.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
            finish();
        }
    });
}

public void setZBarBtnTopInfo(String barcode){
    //mZBarBtnTopInfo.
    new HttpAsyncTaskGET().execute(Const.getApiUrl(barcode));
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

           // setViewFromJson(networkUtil.jsonToMap(result));
            mZBarBtnTopInfo.setViewFromJson(networkUtil.jsonToMap(result));

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }

}

}
