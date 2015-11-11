package um.vi8e.com.stocktakescanner.utils;
import android.app.Activity;
import android.content.Intent;

import um.vi8e.com.stocktakescanner.Activity.BarcodeActivity;
import um.vi8e.com.stocktakescanner.Activity.DeveloperActivity;
import um.vi8e.com.stocktakescanner.Activity.ScannerActivity;
import um.vi8e.com.stocktakescanner.Activity.StartStockTakeActivity;
import um.vi8e.com.stocktakescanner.Activity.viewStockTake.StocktakeModel;
import um.vi8e.com.stocktakescanner.Activity.viewStockTake.viewStockTakeActivity;
import um.vi8e.com.stocktakescanner.Activity.viewStockTakeResult.StockResultActivity;
import um.vi8e.com.stocktakescanner.provider.stocktake.StocktakeColumns;


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
void start ( Activity activity ) {
	Intent intent = new Intent ( activity, StartStockTakeActivity.class);
	intent.addFlags ( Intent.FLAG_ACTIVITY_NEW_TASK );
	activity.startActivity ( intent );

}

public static
void barcode ( Activity activity ) {
	Intent intent = new Intent ( activity, BarcodeActivity.class);
	intent.addFlags ( Intent.FLAG_ACTIVITY_NEW_TASK );
	activity.startActivity ( intent );

}

public static
 void viewStockTake ( Activity activity ) {
	Intent intent = new Intent ( activity, viewStockTakeActivity.class);
	intent.addFlags ( Intent.FLAG_ACTIVITY_NEW_TASK );
	activity.startActivity ( intent );

}

public static
void simpleScanner ( Activity activity ) {
	Intent intent = new Intent ( activity, ScannerActivity.class);
	intent.addFlags ( Intent.FLAG_ACTIVITY_NEW_TASK );
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
