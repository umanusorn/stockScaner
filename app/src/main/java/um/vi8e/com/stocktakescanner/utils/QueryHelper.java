package um.vi8e.com.stocktakescanner.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import um.vi8e.com.stocktakescanner.Activity.viewStockTake.StocktakeModel;
import um.vi8e.com.stocktakescanner.Activity.viewStockTakeResult.StocktakeresultModel;
import um.vi8e.com.stocktakescanner.provider.stocktake.StocktakeColumns;
import um.vi8e.com.stocktakescanner.provider.stocktakeresult.StocktakeresultColumns;
import um.vi8e.com.stocktakescanner.provider.stocktakeresult.StocktakeresultSelection;


/**
 * Created by um.anusorn on 8/31/2015.
 */
public class QueryHelper {

/*public static
Cursor getSubTaskValueCursor ( Context context ) {
	SubtaskSelection selection = new SubtaskSelection ();
	String[] projection = SubtaskColumns.ALL_COLUMNS;
	SubtaskCursor cursor = selection.query ( context.getContentResolver (), projection );
	return cursor;
}

public static
void deleteAllListValues ( Context context ) {
	ListSelection listSelection = new ListSelection ();
	listSelection.delete ( context.getContentResolver () );
}*/

public static int getQtyCount(StocktakeModel listModel, Context context) {
	//Log.d ( TAG, "getCurrentTaskCount" );
	StocktakeresultSelection where = new StocktakeresultSelection();
	where.stocktakeId(listModel.getId());
	Cursor cursor = where.query(context.getContentResolver());
//	cursor.moveToFirst();
	int sum = 0;

	while (cursor.moveToNext()) {
		int qtyIndex = cursor.getColumnIndex(StocktakeresultColumns.QTY);
		sum += cursor.getInt(qtyIndex);
	}
	int count = where.count(context.getContentResolver());
	Log.d("getCurrentTaskCount", "listid=" + listModel.getId() + " count=" + count);
	return sum;
}


public static List<ContentValues> getValuesFromCursor(Cursor c, String[] ALL_COLUMNS) {
	List<ContentValues> values = new ArrayList<ContentValues>();
	int i = 0;
	String key;
	int index;
	c.moveToFirst();
	if (c.getCount() > 0) {
		do {
			ContentValues value = new ContentValues();
			//Log.d ( "InWhile cursor=", c.getCount () + "  Values=" + values.size () );
			for (int j = 0; j < c.getColumnCount(); j++) {
				key = ALL_COLUMNS[j];
				index = c.getColumnIndex(key);
				//Log.d ( "InFor",key+">>"+index );
				value.put(key, c.getString(index));
			}
			i++;
			values.add(value);
		}
		while (c.moveToNext());
	}
	Log.d("getCount cursor=", c.getCount() + "  Values=" + values.size());

	return values;
}
/*
public static
 void addListToDB ( Context context, String title,ListView listView ) {

	Log.d ( "addListToDb", "" );
	Uri uri = addListToDB ( context, title );
	Log.d ( "ChkColumn ", "title" + title + "newId=" + getIdFromUri ( uri ) );
	updateListAdapter ( title, listView, uri );
}

public static
void addSubTaskToDB ( Context context, String title,String taskId,ListView listView ) {

	Log.d ( "addListToDb", "" );
	Uri uri = addSubTaskToDB ( context, title,taskId );
	Log.d ( "ChkColumn ", "title" + title + "newId=" + getIdFromUri ( uri ) );
	//updateListAdapter ( title, listView, uri );
}*/



/*public static
 void updateListAdapter ( String title, ListView listView, Uri uri ) {
	LandingActivity.mLandingListAdapter.add ( 0, new ListModel ( getIdFromUri ( uri ), title ) );
	UiMng.setTaskListViewHeight(listView);
}




public static
Uri addListToDB ( Context context, String title ) {
	ListModel model = new ListModel ( title );
	return context.getContentResolver ().insert ( ListColumns.CONTENT_URI, model.getValues () );
}

public static
Uri addSubTaskToDB ( Context context, String title, String taskId ) {
	SubTaskModel subTaskModel = new SubTaskModel ( title,taskId );
	return context.getContentResolver ().insert ( SubtaskColumns.CONTENT_URI, subTaskModel.getValues () );
}

public static
Uri addCommentToDB ( Context context, String title, String taskId ) {
	CommentModel model = new CommentModel ( title,taskId );
	return context.getContentResolver ().insert ( TaskCommentColumns.CONTENT_URI, model.getValues () );
}

public static
void updateListAdapter ( ListModel model, ListView listView) {

	LandingActivity.mLandingListAdapter.add ( 0,model );
	UiMng.setTaskListViewHeight(listView);
}

public static
Uri addListToDB ( Context context, ListModel model ) {
	return context.getContentResolver ().insert ( ListColumns.CONTENT_URI, model.getValues () );
}

public static
void addTaskToDB ( Context context, TaskModel taskModel, TaskAdapter taskAdapter, ListView listView ) {

	Uri uri = addTaskToDB ( context, taskModel );
	taskModel.setId ( getIdFromUri ( uri ) );
	taskAdapter.insert ( new TaskModel ( getIdFromUri ( uri ), taskModel ), 0 );
	UiMng.setTaskListViewHeight(listView);
}

public static
Uri addTaskToDB ( Context context, TaskModel taskModel ) {
	Log.d ( "addTaskToDb", "" );
	Uri uri = context.getContentResolver ().insert ( TaskColumns.CONTENT_URI, taskModel.getValues () );
	Log.d ( "ChkColumn ", "title" + taskModel.getTitle () + "taskId=" + getIdFromUri ( uri ) + "listId" + taskModel.getListId () );
return uri;
}

public static
void genListAndTask ( Context context ) {
	for ( int i = 0 ; i < 10 ; i++ ) {
		Uri uri = addListToDB ( context, "Category " + ( i + 1 ) );

		for ( int j = 0 ; j < i ; j++ ) {
			boolean isStar, isComplete = false;
			if ( j % 2 == 0 ) {
				isStar = true;
				if ( j % 3 != 0 ) {
					isComplete = false;
				}
				else
				{
					isComplete = true;
				}
			}
			else {
				isStar = false;
			}

			TaskModel taskModel = new TaskModel ( "Task "+( i + 1 )+"-"+(j+1),
			                                      String.valueOf ( isStar ),
			                                      String.valueOf ( isComplete ),
			                                      uri.getPathSegments ().get ( 1 ),
			                                      System.currentTimeMillis () );
			if(j>2)
					taskModel.setNote ( "GeneratedNote"+( i + 1 )+"-"+(j-2) );
			context.getContentResolver ().insert ( TaskColumns.CONTENT_URI, taskModel.getValues () );
		}

	}
}*/

public static void genListAndTask(Context context) {

	for (int i = 0; i < 10; i++) {

		StocktakeModel stocktakeModel = new StocktakeModel("timeStart" + i, "timeEnd", "Pending", "location " + i, "Um",
		                                                   "DeviceDetail");
		Uri uri = context.getContentResolver().insert(StocktakeColumns.CONTENT_URI, stocktakeModel.getValues());

		for (int k = 0; k < 3; k++) {
			StocktakeresultModel
					stocktakeresultModel =
					new StocktakeresultModel(uri.getPathSegments().get(1),
					                         "dummyBarCode" + k + "," + uri.getPathSegments().get(1),
					                         "1",
					                         "dummyDate");
			context.getContentResolver().insert(StocktakeresultColumns.CONTENT_URI, stocktakeresultModel.getValues());
		}
	}

}


public static String getIdFromUri(Uri uri) {
	return uri.getPathSegments().get(1);
}
}
