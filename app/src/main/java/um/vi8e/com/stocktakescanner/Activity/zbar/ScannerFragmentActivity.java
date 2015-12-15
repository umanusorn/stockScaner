package um.vi8e.com.stocktakescanner.Activity.zbar;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import me.dm7.barcodescanner.zbar.BarcodeFormat;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;
import um.vi8e.com.stocktakescanner.Activity.StartStockTakeActivity;
import um.vi8e.com.stocktakescanner.Activity.viewStockTake.StocktakeModel;
import um.vi8e.com.stocktakescanner.Activity.viewStockTakeResult.StocktakeresultModel;
import um.vi8e.com.stocktakescanner.R;
import um.vi8e.com.stocktakescanner.provider.stocktake.StocktakeColumns;
import um.vi8e.com.stocktakescanner.utils.Const;
import um.vi8e.com.stocktakescanner.utils.CustomDialog;
import um.vi8e.com.stocktakescanner.utils.DateTimeHelper;
import um.vi8e.com.stocktakescanner.utils.IntentCaller;
import um.vi8e.com.stocktakescanner.utils.networkUtil;

public class ScannerFragmentActivity extends AppCompatActivity {

private ScannerFragment mScannerFragment;
ZBarBtnFragment mZBarBtnFragment;
public ZBarBtnTopInfo mZBarBtnTopInfo;
String barcode;
public static String                  mStocktakeId;
private AppCompatActivity       thisActivity;
private HashMap<String, String> mProductInfo;

public static String          dateScanned;
public static String          location;
public        ZBarScannerView mScannerView;
private       boolean         isGotData;
public static boolean isBack = false;

@Override
public void onCreate(Bundle state) {
	super.onCreate(state);
	setContentView(R.layout.activity_scanner_fragment);
	FragmentManager fragmentManager;
	fragmentManager = getSupportFragmentManager();
	FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
	thisActivity = this;
	mScannerFragment = new ScannerFragment();
	fragmentTransaction.add(R.id.scannerFrame, mScannerFragment);
	mZBarBtnFragment = new ZBarBtnFragment();
	fragmentTransaction.add(R.id.zbarBtnFrame, mZBarBtnFragment);
	mZBarBtnTopInfo = new ZBarBtnTopInfo();
	fragmentTransaction.add(R.id.zbarTopInfo, mZBarBtnTopInfo);
	fragmentTransaction.commit();

	Bundle extras;
	extras = getIntent().getExtras();
	location = extras.getString(StocktakeColumns.LOCATION);
	dateScanned = extras.getString(StocktakeColumns.DATETIME_STARTED);
	try {

		mStocktakeId = extras.getString(StocktakeColumns._ID);
	}
	catch (NullPointerException e) {
		mStocktakeId = null;
	}


}

@Override protected void onResumeFragments() {
	super.onResumeFragments();
	hideTopInfo();

	mZBarBtnFragment.mAddItemTv.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			//mScannerFragment.setIsScan(!mScannerFragment.isScan());

			//	ScannerActivity.saveToDBFromStartStockTake(getApplicationContext(), barcode, mStocktakeId);
			//	Toast.makeText(getApplicationContext(), "saved" + barcode + " to db", Toast.LENGTH_SHORT).show();

			StocktakeModel stocktakeModel = new StocktakeModel(dateScanned, dateScanned, Const.Status.PENDING, location,
			                                                   "dummy",
			                                                   "null");
			//mProductInfo.put(ProductApiKey.BARCODE,barcode);
			//	mProductInfo.put(ProductApiKey.ITEM_CODE,itemCode);

			if (mProductInfo == null) {
				Toast.makeText(getApplicationContext(), "Not found product info. Please scan again", Toast.LENGTH_SHORT).show();
			}
			else {
				CustomDialog.showQtyDialog(thisActivity, mProductInfo, stocktakeModel);
			}
		}
	});
	mZBarBtnFragment.mCancelTv.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			finish();
		}
	});
	isGotData = false;

}

public void hideTopInfo() {

	mZBarBtnTopInfo.hidTopInfo();
}

public void showTopInfo() {
	mZBarBtnTopInfo.showTopInfo();
}

public void setZBarBtnTopInfo(String barcode) {
	mZBarBtnTopInfo.setEmptyText(barcode);
	new HttpAsyncTaskGET().execute(Const.getApiUrl(barcode));
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

	@Override
	protected void onPostExecute(String result) {
		this.result = result;
		try {
			mProductInfo = mZBarBtnTopInfo.setViewFromJson(networkUtil.jsonToMap(result), barcode);
			Date date = new Date();
			String timeScanned = DateTimeHelper.getFormatedDate(date);

			StocktakeresultModel stocktakeresultModel = new StocktakeresultModel(mStocktakeId, barcode, "1", timeScanned);
			mScannerView.stopCamera();
			isGotData = true;
			IntentCaller.barcode(thisActivity, stocktakeresultModel, location);

		}
		catch (JSONException e) {

			e.printStackTrace();
			Log.d("async", "wrong url");
			Toast.makeText(getApplicationContext(), "Not found product info from this barcode", Toast.LENGTH_SHORT).show();
			mScannerView.startCamera();
		}

	}

}

public class ScannerFragment extends Fragment implements MessageDialogFragment.MessageDialogListener,
                                                         ZBarScannerView.ResultHandler, FormatSelectorDialogFragment.FormatSelectorDialogListener,
                                                         CameraSelectorDialogFragment.CameraSelectorDialogListener {
	private static final String FLASH_STATE      = "FLASH_STATE";
	private static final String AUTO_FOCUS_STATE = "AUTO_FOCUS_STATE";
	private static final String SELECTED_FORMATS = "SELECTED_FORMATS";
	private static final String CAMERA_ID        = "CAMERA_ID";

	private boolean            mFlash;
	private boolean            mAutoFocus;
	private ArrayList<Integer> mSelectedIndices;
	private int mCameraId = -1;
	Menu         mMenu;
	MenuInflater mMenuInflater;
	private boolean isScan = true;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
		mScannerView = new ZBarScannerView(getActivity());
		mScannerView.setAutoFocus(true);
		if (state != null) {
			mFlash = state.getBoolean(FLASH_STATE, false);
			mAutoFocus = state.getBoolean(AUTO_FOCUS_STATE, true);
			mSelectedIndices = state.getIntegerArrayList(SELECTED_FORMATS);
			mCameraId = state.getInt(CAMERA_ID, -1);
		}
		else {
			mFlash = false;
			mAutoFocus = true;
			mSelectedIndices = null;
			mCameraId = -1;
		}
		setupFormats();

		//mScannerView.setRotation((float) 90.0);
		//mScannerView.setEnabled(false);
		//mScannerView.setVisibility(View.GONE);
//		mScannerView.setRotation((float) 90.0);
		//mScannerView.setBottom();
		//mScannerView.setRotationX((float) 90.0);
	//	mScannerView.setRotationY(90);

		return mScannerView;
	}

	@Override
	public void onCreate(Bundle state) {
		super.onCreate(state);
		setHasOptionsMenu(true);
	}

	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);

		MenuItem menuItem;
		mMenu = menu;
		mMenuInflater = inflater;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_flash:
				mFlash = !mFlash;
				if (mFlash) {
					item.setTitle(R.string.flash_on);
				}
				else {
					item.setTitle(R.string.flash_off);
				}
				mScannerView.setFlash(mFlash);
				return true;
			case R.id.menu_auto_focus:
				mAutoFocus = !mAutoFocus;
				if (mAutoFocus) {
					item.setTitle(R.string.auto_focus_on);
				}
				else {
					item.setTitle(R.string.auto_focus_off);
				}
				mScannerView.setAutoFocus(mAutoFocus);
				return true;
			case R.id.menu_formats:
				DialogFragment fragment = FormatSelectorDialogFragment.newInstance(this, mSelectedIndices);
				fragment.show(getActivity().getSupportFragmentManager(), "format_selector");
				return true;
			case R.id.menu_camera_selector:
				mScannerView.stopCamera();
				DialogFragment cFragment = CameraSelectorDialogFragment.newInstance(this, mCameraId);
				cFragment.show(getActivity().getSupportFragmentManager(), "camera_selector");
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		mScannerView.setResultHandler(this);
		mScannerView.startCamera(mCameraId);
		mScannerView.setFlash(mFlash);
		mScannerView.setAutoFocus(mAutoFocus);
		if (isBack) {
			isBack=false;
			finish();
		}
		setIsScan(false);

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean(FLASH_STATE, mFlash);
		outState.putBoolean(AUTO_FOCUS_STATE, mAutoFocus);
		outState.putIntegerArrayList(SELECTED_FORMATS, mSelectedIndices);
		outState.putInt(CAMERA_ID, mCameraId);
	}

	public boolean isScan() {
		return isScan;
	}

	public void setIsScan(boolean isScan) {
		this.isScan = isScan;
	}

	@Override
	public void handleResult(Result rawResult) {
		isScan = true;
		if (!isGotData) {
			barcode = rawResult.getContents();
			getActivity().setTitle("Last Scanned: " + barcode);

			setZBarBtnTopInfo(barcode);
			showTopInfo();
			StartStockTakeActivity.isFinished = true;


		}

		//StartStockTakeActivity.isFinished=true;
	}

	public void showMessageDialog(String message) {
		DialogFragment fragment = MessageDialogFragment.newInstance("Scan Results", message, this);
		fragment.show(getActivity().getSupportFragmentManager(), "scan_results");
	}

	public void closeMessageDialog() {
		closeDialog("scan_results");
	}

	public void closeFormatsDialog() {
		closeDialog("format_selector");
	}

	public void closeDialog(String dialogName) {
		FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		DialogFragment fragment = (DialogFragment) fragmentManager.findFragmentByTag(dialogName);
		if (fragment != null) {
			fragment.dismiss();
		}
	}

	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		// Resume the camera
		mScannerView.startCamera(mCameraId);
		mScannerView.setFlash(mFlash);
		mScannerView.setAutoFocus(mAutoFocus);
	}

	@Override
	public void onFormatsSaved(ArrayList<Integer> selectedIndices) {
		mSelectedIndices = selectedIndices;
		setupFormats();
	}

	@Override
	public void onCameraSelected(int cameraId) {
		mCameraId = cameraId;
		mScannerView.startCamera(mCameraId);
		mScannerView.setFlash(mFlash);
		mScannerView.setAutoFocus(mAutoFocus);
	}

	public void setupFormats() {
		List<BarcodeFormat> formats = new ArrayList<BarcodeFormat>();
		if (mSelectedIndices == null || mSelectedIndices.isEmpty()) {
			mSelectedIndices = new ArrayList<Integer>();
			for (int i = 0; i < BarcodeFormat.ALL_FORMATS.size(); i++) {
				mSelectedIndices.add(i);
			}
		}

		for (int index : mSelectedIndices) {
			formats.add(BarcodeFormat.ALL_FORMATS.get(index));
		}
		if (mScannerView != null) {
			mScannerView.setFormats(formats);
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		mScannerView.stopCamera();
		closeMessageDialog();
		closeFormatsDialog();
	}
}
}
