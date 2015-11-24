package um.vi8e.com.stocktakescanner.utils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import um.vi8e.com.stocktakescanner.R;


/**
 * Created by um.anusorn on 9/10/2015.
 */
public
class ActivityUi {
public static Toolbar initToolbar (Toolbar toolbar,AppCompatActivity appCompatActivity,ActionBar actionBar) {
	toolbar = (Toolbar) appCompatActivity.findViewById ( R.id.toolbar );
	appCompatActivity.setSupportActionBar(toolbar);
	actionBar = appCompatActivity.getSupportActionBar();
	toolbar.setVisibility ( View.VISIBLE );
	return toolbar;
}

public static void setStatusBar(AppCompatActivity activity) {
	Window window = activity.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
	window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
	window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
	window.setStatusBarColor(activity.getResources().getColor(R.color.colorPrimaryDark));
}

public static
void setToolBar ( final AppCompatActivity activity, Toolbar toolbar, String title ) {
	toolbar = (Toolbar) activity.findViewById ( R.id.toolbar );
	activity.setSupportActionBar(toolbar);

	toolbar.setVisibility(View.VISIBLE);
	ActionBar actionBar = activity.getSupportActionBar();
	toolbar.setTitle ( title );
	actionBar.setTitle(title);
	actionBar.setDisplayShowTitleEnabled(true);
	actionBar.setDisplayHomeAsUpEnabled(true);
	actionBar.setDisplayShowHomeEnabled(true);
	toolbar.setNavigationOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			activity.finish();
		}
	} );
}

public static
void setActiveToolBar (AppCompatActivity thisActivity,Toolbar toolbar,String title,Context context) {

	//mToolbar
	thisActivity.getSupportActionBar ().setTitle ( title );
	toolbar.setBackgroundDrawable ( new ColorDrawable ( context.getResources ().getColor ( R.color.blue_400 ) ) );
}

public static
void setInActiveToolBar (Toolbar toolbar,Context context) {

	toolbar.setTitle ( "" );
	toolbar.setBackgroundDrawable ( new ColorDrawable ( context.getResources ().getColor ( R.color.transparent ) ) );
}

public static
void setActiveList ( RelativeLayout rowListRootView, Context context ) {
	rowListRootView.setBackgroundColor (
			context.getResources ().getColor (
					R.color.blue_400_trans50 ) );
}
}
