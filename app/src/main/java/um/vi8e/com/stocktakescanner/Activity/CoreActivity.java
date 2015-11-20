package um.vi8e.com.stocktakescanner.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import um.vi8e.com.stocktakescanner.R;
import um.vi8e.com.stocktakescanner.utils.IntentCaller;

public class CoreActivity extends AppCompatActivity implements  ActionMode.Callback  {
public static AppCompatActivity thisActivity;

protected final String TAG = this.getClass().getSimpleName();
public static Toolbar toolbar;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_core);
	toolbar = (Toolbar) findViewById(R.id.toolbar);
	setSupportActionBar(toolbar);
	//setActionBar(toolbar);

	thisActivity = this;
	Log.d(TAG, "enter " + TAG);

}

@Override public boolean onCreateActionMode(ActionMode mode, Menu menu) {
	return false;
}

@Override public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
	return false;
}

@Override public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
	return false;
}

@Override public void onDestroyActionMode(ActionMode mode) {

}

@Override
public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.menu_landing, menu);
	return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {
	// Handle action bar item clicks here. The action bar will
	// automatically handle clicks on the Home/Up button, so long
	// as you specify a parent activity in AndroidManifest.xml.
	int id = item.getItemId();

	//noinspection SimplifiableIfStatement
	if (id == R.id.action_settings) {
		Toast.makeText(getApplicationContext(),"testCore",Toast.LENGTH_LONG).show();
		return true;
	}

	return super.onOptionsItemSelected(item);
}

public void onClickFab(View view){

	IntentCaller.startTake(thisActivity);

}

}
