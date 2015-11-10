package um.vi8e.com.stocktakescanner.Model;

import android.content.ContentValues;
import android.util.Log;


public
class CommentModel {

String id;
String taskId;
String title;
String dateTime;
String userId;

public
CommentModel ( String title, String taskId, String id, String dateTime, String userId ) {

	//setDefault ();
	Log.d ( "newComment", "id=" + id );

	this.title = title;
	this.taskId = taskId;
	this.id = id;
	this.dateTime = dateTime;
	this.userId = userId;
}

public CommentModel(ContentValues listValues) {
	setValues(listValues);
}

public CommentModel setValues(ContentValues values) {
	/*id=values.getAsString(TaskCommentColumns._ID);
	title = values.getAsString(TaskCommentColumns.COMMENT_TITLE);
	taskId=values.getAsString(TaskCommentColumns.TASK_ID);
	userId=values.getAsString(TaskCommentColumns.USER_ID);
	dateTime=values.getAsString(TaskCommentColumns.DATETIME);*/

	return this;
}

public
CommentModel ( String title, String taskId ) {

	this.title = title;
	this.taskId = taskId;
	if ( getDateTime () == null ) {
		setDateTime ( String.valueOf ( false ) );
	}

}

public
String getDateTime () {
	return dateTime;
}

public
void setDateTime ( String dateTime ) {
	this.dateTime = dateTime;
}

public
String getUserId () {
	return userId;
}

public
void setUserId ( String userId ) {
	this.userId = userId;
}

public
boolean isComplete () {
	return Boolean.valueOf ( dateTime );
}

public
String getTaskId () {
	return taskId;
}

public
void setTaskId ( String taskId ) {
	this.taskId = taskId;
}

public
ContentValues getValues () {
	ContentValues values = new ContentValues ();
	/*values.put ( TaskCommentColumns.COMMENT_TITLE, title );
	values.put ( TaskCommentColumns.TASK_ID, taskId );
	values.put ( TaskCommentColumns.DATETIME, getComplete () );
	values.put ( TaskCommentColumns.USER_ID, getComplete () );
	values.put ( TaskCommentColumns._ID,id );*/
	return values;
}

public
String getComplete () {
	return dateTime;
}

public
String getTitle () {
	return title;
}

public
void setTitle ( String title ) {
	this.title = title;
}

public
String getId () {
	return id;
}

public
void setId ( String id ) {
	this.id = id;
}
}
