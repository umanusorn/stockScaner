package um.vi8e.com.stocktakescanner.utils;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
/**
 * Created by Fixer on 11/20/2015.
 */
public abstract class HidingScrollListener extends RecyclerView.OnScrollListener {
private static final int HIDE_THRESHOLD = 20;
private int scrolledDistance = 0;
private boolean controlsVisible = true;

@Override
public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
	super.onScrolled(recyclerView, dx, dy);
	Log.d("scrolling","scrolllll");

	if (scrolledDistance > HIDE_THRESHOLD && controlsVisible) {
		onHide();
		Log.d("scrolling", "hide");
		controlsVisible = false;
		scrolledDistance = 0;
	} else if (scrolledDistance < -HIDE_THRESHOLD && !controlsVisible) {
		Log.d("scrolling","show");
		onShow();

		controlsVisible = true;
		scrolledDistance = 0;
	}

	if((controlsVisible && dy>0) || (!controlsVisible && dy<0)) {
		scrolledDistance += dy;
	}
}

public abstract void onHide();
public abstract void onShow();

}
