package um.vi8e.com.stocktakescanner.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import um.vi8e.com.stocktakescanner.Activity.viewStockTake.StocktakeModel;
import um.vi8e.com.stocktakescanner.Activity.viewStockTakeResult.StocktakeresultModel;
import um.vi8e.com.stocktakescanner.R;
import um.vi8e.com.stocktakescanner.provider.stocktake.StocktakeColumns;
import um.vi8e.com.stocktakescanner.provider.stocktakeresult.StocktakeresultColumns;
import um.vi8e.com.stocktakescanner.utils.ActivityUi;
import um.vi8e.com.stocktakescanner.utils.DateTimeHelper;
import um.vi8e.com.stocktakescanner.utils.IntentCaller;

public class StartStockTakeActivity extends CoreActivity {
static CardView cardView;
static TextView setDateTv;
static EditText locationEditText;
static Date selectedDate;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_start_stock_take);
	Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
	setSupportActionBar(toolbar);
	ActivityUi.setToolBar(this, toolbar, "START STOCKTAKE");



	cardView = (CardView) findViewById(R.id.card_viewSetDate);
	cardView.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			showDatePickerDialog(v);
		}
	});
	setDateTv = (TextView) cardView.findViewById(R.id.setDateTv);
	locationEditText = (EditText) findViewById(R.id.locationEditText);
	Button startNow = (Button) findViewById(R.id.startNow);
	startNow.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			IntentCaller.simpleScanner(thisActivity);
		}
	});

	Bundle extras= getIntent().getExtras();
	if(extras!=null){
		setDateTv.setText(extras.getString(StocktakeColumns.DATETIME_STARTED));
		locationEditText.setText(extras.getString(StocktakeColumns.LOCATION));
	}


}


/*public void onActivityResult(int requestCode, int resultCode, Intent intent) {
	if (requestCode == 0) {
		if (resultCode == RESULT_OK) {
			String contents = intent.getStringExtra("SCAN_RESULT");
			String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
			// Handle successful scan
		} else if (resultCode == RESULT_CANCELED) {
			// Handle cancel
		}
	}
}*/

public static void saveToDB(Context context, String barcode) {
	String location = locationEditText.getText().toString();

	String dateScanned = setDateTv.getText().toString();

	StocktakeModel stocktakeModel = new StocktakeModel(dateScanned, "timeEnd", "completed", location, "User Um",
	                                                   "DeviceDetail");
	Uri uri = context.getContentResolver().insert(StocktakeColumns.CONTENT_URI, stocktakeModel.getValues());

	StocktakeresultModel
			stocktakeresultModel =
			new StocktakeresultModel(uri.getPathSegments().get(1),
			                         barcode,
			                         "1",
			                         dateScanned);
	context.getContentResolver().insert(StocktakeresultColumns.CONTENT_URI, stocktakeresultModel.getValues());
}

public void showDatePickerDialog(View v) {
	DialogFragment newFragment = new DatePickerFragment();
	newFragment.show(getSupportFragmentManager(), "datePicker");
}

public static class DatePickerFragment extends DialogFragment
		implements DatePickerDialog.OnDateSetListener {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	public void onDateSet(DatePicker view, int year, int month, int day) {
		//todo update ui, var

		selectedDate = new Date(year - 1900, month, day);
		setDateTv.setText(DateTimeHelper.getFormatedDate(selectedDate));

	}

}
}
