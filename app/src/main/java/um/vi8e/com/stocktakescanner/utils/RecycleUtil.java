package um.vi8e.com.stocktakescanner.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import um.vi8e.com.stocktakescanner.Model.ModelType;
import um.vi8e.com.stocktakescanner.R;

/**
 * Created by Fixer on 11/5/2015.
 */
public class RecycleUtil {

private static final String TAG = RecycleUtil.class.getSimpleName ();
public static void setUpRecycleFragment(Bundle savedInstanceState, AppCompatActivity activity, String
		modelType)
{
	if (savedInstanceState == null) {
		FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

		Fragment fragment = null;
		switch (modelType) {
			case ModelType.LIST:
			//	fragment = new LandingRecycleFragment();
				break;
			case ModelType.SUB_TASK:
			//	fragment = new SubTaskRecycleFragment();
				break;
			case ModelType.TASK:

				break;
			case ModelType.COMMENT:
			//	fragment = new CommentRecycleFragment();
				break;
			default:
				Log.e(TAG, "ModelType ERROR:" + modelType);
		}
	//	transaction.replace(R.id.recycle_content_fragment, fragment);
		transaction.commit();
	}
}
}
