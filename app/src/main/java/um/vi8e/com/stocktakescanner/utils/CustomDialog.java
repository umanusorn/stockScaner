package um.vi8e.com.stocktakescanner.utils;

import android.app.Activity;
import android.content.DialogInterface;

import com.afollestad.materialdialogs.MaterialDialog;

import um.vi8e.com.stocktakescanner.R;
/**
 * Created by Fixer on 12/8/2015.
 */
public class CustomDialog {
public static
void showPassCodeLocked ( final Activity thisContext,String itemCode ) {


		MaterialDialog scoreDialog = new MaterialDialog.Builder ( thisContext )
				.customView ( R.layout.dialog_score, WRAP_IN_SCORE_VIEW )
				.title("Added Item")
				.positiveText ( "Ok" )
				.show ();

		scoreDialog.setOnDismissListener ( new DialogInterface.OnDismissListener () {
			@Override public
			void onDismiss ( DialogInterface dialog ) {
				thisContext.finishAffinity ();
			}
		} );
	}
}

