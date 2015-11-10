package um.vi8e.com.stocktakescanner.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import um.vi8e.com.stocktakescanner.R;


/**
 * Created by um.anusorn on 9/23/2015.
 */
public class UiMng {
public static void setRedText(Context context, TextView textView) {
	int color = (context.getResources().getColor(R.color.red_400));
	textView.setTextColor(color);
	View view = (View) textView.getParent();
	try {
		//ImageView imageView = (ImageView) view.findViewById(R.id.reminderImg);
		//imageView.setColorFilter(color);
	}
	catch (Exception e) {
		Log.d("", "Error on try to color the image");
	}
}

public static void setBlueText(Context context, TextView textView) {
	int color = (context.getResources().getColor(R.color.blue_400));
	textView.setTextColor(color);
	View view = (View) textView.getParent();
	try {
		//ImageView imageView = (ImageView) view.findViewById(R.id.reminderImg);
		//imageView.setColorFilter(color);
	}
	catch (Exception e) {
		Log.d("", "Error on try to color the image");
	}

}

public static void setBlackText(Context context, TextView textView) {
	textView.setTextColor(context.getResources().getColor(R.color.grey_800));
}

public static int setTaskListViewHeight(ListView listView) {

	ListAdapter listAdapter = listView.getAdapter();
	if (listAdapter == null) {
		// pre-condition
		return 0;
	}

	int totalHeight = 0;
	int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
	for (int i = 0; i < listAdapter.getCount(); i++) {
		View listItem = listAdapter.getView(i, null, listView);
		listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);//UNSPECIFIED

		totalHeight += listItem.getMeasuredHeight();
		Log.d("setTaskListViewHeight", "height= " + totalHeight);
	}
	ViewGroup.LayoutParams params = listView.getLayoutParams();

	Log.d("setTaskListViewHeight ",
	      "height= " + totalHeight + "listAdapter.count " + listAdapter.getCount() + "x " + listView.getDividerHeight());
	totalHeight += (listView.getDividerHeight() * (listAdapter.getCount() - 1));

	params.height = totalHeight;
	listView.setLayoutParams(params);
	Log.d("setTaskListViewHeight ",
	      "height= " + totalHeight + "listAdapter.count " + listAdapter.getCount() + "x " + listView.getDividerHeight());
	listView.requestLayout();
	return totalHeight;
}

public static int setListViewHeight(ListView listView) {

	ListAdapter listAdapter = listView.getAdapter();
	if (listAdapter == null) {
		// pre-condition
		return 0;
	}

	int totalHeight = 0;
	int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
	for (int i = 0; i < listAdapter.getCount(); i++) {
		View listItem = listAdapter.getView(i, null, listView);
		listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);//UNSPECIFIED

		totalHeight += listItem.getMeasuredHeight();
		Log.d("setTaskListViewHeight", "height= " + totalHeight);
	}
	ViewGroup.LayoutParams params = listView.getLayoutParams();

	Log.d("setTaskListViewHeight ",
	      "height= " + totalHeight + "listAdapter.count " + listAdapter.getCount() + "x " + listView.getDividerHeight());
	totalHeight += (listView.getDividerHeight() * (listAdapter.getCount() - 1));

	try {
		//totalHeight += getActionBarHeight(LandingActivity.thisActivity);
	}
	catch (NullPointerException e) {
		Log.d("setTaskListViewHeight", "hack to add height only for landingpage");
	}

	params.height = totalHeight;
	listView.setLayoutParams(params);
	Log.d("setTaskListViewHeight ",
	      "height= " + totalHeight + "listAdapter.count " + listAdapter.getCount() + "x " + listView.getDividerHeight());
	listView.requestLayout();
	return totalHeight;
}

public static void setDrawbleColorFilter(Context context, Drawable drawable, int color) {

	//int COLOR2 = Color.parseColor ( "#FF" + getColor () );
	PorterDuff.Mode mMode = PorterDuff.Mode.SRC_ATOP;
	drawable.setColorFilter(color, mMode);
}

public static String getVersionName(Context context) {
	try {
		return String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName);
	}
	catch (PackageManager.NameNotFoundException e) {
		e.printStackTrace();
	}
	return "null";
}

public static String getVersionCode(Context context) {
	try {
		return String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
	}
	catch (PackageManager.NameNotFoundException e) {
		e.printStackTrace();
	}
	return "null";
}

public static int getActionBarHeight(Activity activity) {
	TypedValue tv = new TypedValue();
	if (activity.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
		return TypedValue.complexToDimensionPixelSize(tv.data, activity.getResources().getDisplayMetrics());
	}
	return -1;
}

public static DisplayMetrics getScreenSize(Activity activity) {
	Display display = activity.getWindowManager().getDefaultDisplay();
	DisplayMetrics outMetrics = new DisplayMetrics();
	display.getMetrics(outMetrics);

	return outMetrics;
}

public static void toastOneInstance(Toast mToast, Context context) {
	if (mToast != null) {
		mToast.cancel();
	}
	mToast = Toast.makeText(
			context,
			"String",
			Toast.LENGTH_LONG
	                       );
	mToast.show();
}
}
