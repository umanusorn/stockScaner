package um.vi8e.com.stocktakescanner.utils;

/**
 * Created by um2013 on 2/14/2014.
 */
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;

public class ConfirmDialog {

public static void show(Context context,String msg,final ConfirmListener listener,final String key ){
//context using getActivity or this

	Builder builder = new Builder(context);
	//builder.setIcon( R.drawable.ic_launcher);
	builder.setTitle("Delete");
	builder.setMessage(msg);
	builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {

		public void onClick(DialogInterface dialog, int id) {
			// HomeFragmentActivity.this.finish();
			if(listener != null){
				listener.onConfirm(key);
			}
		}
	});
	builder.setNegativeButton("Cancel", null);
	builder.show();
}




public interface ConfirmListener {
	void onConfirm(String key);
}

}
