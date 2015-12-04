package um.vi8e.com.stocktakescanner.zbar;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import um.vi8e.com.stocktakescanner.R;

public class ScannerFragmentActivity extends AppCompatActivity {

private ScannerFragment mScannerFragment;
ZBarBtnFragment mZBarBtnFragment;

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
        fragmentTransaction.commit();


    }

@Override protected void onResumeFragments() {
    super.onResumeFragments();/*
    mScannerFragment.mScannerView.stopCamera();
    mScannerFragment.mScannerView.startCamera();*/
   // mScannerFragment.mScannerView.setVisibility(View.INVISIBLE);

    mZBarBtnFragment.mZbarBtn.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
            mScannerFragment.setIsScan(!mScannerFragment.isScan());
        }
    });
}
}
