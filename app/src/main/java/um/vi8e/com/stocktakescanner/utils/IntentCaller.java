package um.vi8e.com.stocktakescanner.utils;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import um.vi8e.com.stocktakescanner.Activity.DeveloperActivity;


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
