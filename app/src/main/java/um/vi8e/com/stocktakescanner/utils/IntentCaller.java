package um.vi8e.com.stocktakescanner.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import um.vi8e.com.stocktakescanner.Activity.BarcodeDetailActivity;
import um.vi8e.com.stocktakescanner.Activity.DeveloperActivity;
import um.vi8e.com.stocktakescanner.Activity.ScannerActivity;
import um.vi8e.com.stocktakescanner.Activity.StartStockTakeActivity;
import um.vi8e.com.stocktakescanner.Activity.viewStockTake.StocktakeModel;
import um.vi8e.com.stocktakescanner.Activity.viewStockTake.viewStockTakeActivity;
import um.vi8e.com.stocktakescanner.Activity.viewStockTakeResult.StockResultActivity;
import um.vi8e.com.stocktakescanner.Activity.viewStockTakeResult.StocktakeresultModel;
import um.vi8e.com.stocktakescanner.Activity.zbar.ScannerFragmentActivity;
import um.vi8e.com.stocktakescanner.provider.stocktake.StocktakeColumns;
import um.vi8e.com.stocktakescanner.provider.stocktakeresult.StocktakeresultColumns;


/**
 * Created by um.anusorn on 9/3/2015.
 */
public
class IntentCaller {


public static
void developer ( Activity activity ) {
	Intent intent = new Intent ( activity, DeveloperActivity.class);
	intent.addFlags ( Intent.FLAG_ACTIVITY_NEW_TASK );
	activity.startActivity ( intent );

}

public static
void startTake(Activity activity) {
	Intent intent = new Intent ( activity, StartStockTakeActivity.class);
	intent.addFlags ( Intent.FLAG_ACTIVITY_NEW_TASK );
	StartStockTakeActivity.isFinished=false;
	activity.startActivity ( intent );

}

public static
void startTakeFromBarCode ( Activity activity,Bundle bundle) {
	Intent intent = new Intent ( activity, StartStockTakeActivity.class);
	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	StartStockTakeActivity.isFinished=false;
	intent.putExtras(bundle);
	activity.startActivity ( intent );

}

public static
void barcode ( Activity activity,StocktakeresultModel stocktakeresultModel) {
	Intent intent = new Intent ( activity, BarcodeDetailActivity.class);
	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	intent.putExtra(StocktakeresultColumns._ID, stocktakeresultModel.getId());
	intent.putExtra(StocktakeresultColumns.BARCODE, stocktakeresultModel.getBarcode());
	intent.putExtra(StocktakeresultColumns.DATETIME_SCANNNED, stocktakeresultModel.getDatetimeScannned());
	intent.putExtra(StocktakeresultColumns.QTY, stocktakeresultModel.getQty());
	intent.putExtra(StocktakeresultColumns.STOCKTAKE_ID, stocktakeresultModel.getStocktakeId());
	activity.startActivity ( intent );
}

public static
void barcode ( Activity activity,StocktakeresultModel stocktakeresultModel,String location) {
	Intent intent = new Intent ( activity, BarcodeDetailActivity.class);
	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	intent.putExtra(StocktakeresultColumns._ID, stocktakeresultModel.getId());
	intent.putExtra(StocktakeresultColumns.BARCODE, stocktakeresultModel.getBarcode());
	intent.putExtra(StocktakeresultColumns.DATETIME_SCANNNED, stocktakeresultModel.getDatetimeScannned());
	intent.putExtra(StocktakeresultColumns.QTY, stocktakeresultModel.getQty());
	intent.putExtra(StocktakeresultColumns.STOCKTAKE_ID, stocktakeresultModel.getStocktakeId());
	intent.putExtra(StocktakeColumns.LOCATION, location);
	activity.startActivity ( intent );
}
public static
 void viewStockTake ( Activity activity ) {
	Intent intent = new Intent ( activity, viewStockTakeActivity.class);
	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	activity.startActivity ( intent );

}

public static
void scanner(Activity activity) {
	Intent intent = new Intent ( activity, ScannerActivity.class);
	intent.addFlags ( Intent.FLAG_ACTIVITY_NEW_TASK );
	activity.startActivity ( intent );

}
/*
public static
void zBarscanner(Activity activity) {
	Intent intent = new Intent ( activity, ZBarScannerActivity.class);
	intent.addFlags ( Intent.FLAG_ACTIVITY_NEW_TASK );
	activity.startActivity ( intent );

}*/

public static
void zBarscannerFragment(Activity activity) {
	Intent intent = new Intent ( activity, ScannerFragmentActivity.class);
	intent.addFlags ( Intent.FLAG_ACTIVITY_NEW_TASK );
	activity.startActivity ( intent );

}

public static
void scannerFromBarCode ( Activity activity,Bundle bundle) {
	Intent intent = new Intent ( activity, ScannerFragmentActivity.class);
	//Intent intent = new Intent ( activity, ScannerActivity.class);
	intent.addFlags ( Intent.FLAG_ACTIVITY_NEW_TASK );
	intent.putExtras(bundle);
	activity.startActivity ( intent );

}

public static
void viewStockTakeResult ( Activity activity,StocktakeModel stocktakeModel ) {
	Intent intent = new Intent ( activity, StockResultActivity.class);
	intent.putExtra ( StocktakeColumns._ID, stocktakeModel.getId() );
	intent.putExtra ( StocktakeColumns.LOCATION, stocktakeModel.getLocation() );
	intent.putExtra ( StocktakeColumns.STATUS, stocktakeModel.getStatus() );
	intent.putExtra ( StocktakeColumns.DATETIME_STARTED, stocktakeModel.getDatetimeStarted() );
	intent.addFlags ( Intent.FLAG_ACTIVITY_NEW_TASK );
	activity.startActivity ( intent );

}

/*
public static
void taskNoteActivity ( Context context, TaskModel taskModel ) {
	Intent intent = new Intent ( context, TaskNoteActivity.class );
	intent.putExtra ( TaskColumns.TASK_TITLE, taskModel.getTitle () );
	intent.putExtra ( TaskColumns.NOTE,taskModel.getNote () );
	intent.putExtra ( TaskColumns._ID, taskModel.getId () );
	intent.addFlags ( Intent.FLAG_ACTIVITY_NEW_TASK );
	context.startActivity ( intent );
}*/


}
