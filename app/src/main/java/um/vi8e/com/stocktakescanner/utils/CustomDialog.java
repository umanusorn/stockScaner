package um.vi8e.com.stocktakescanner.utils;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.HashMap;

import um.vi8e.com.stocktakescanner.Activity.ScannerActivity;
import um.vi8e.com.stocktakescanner.Activity.viewStockTake.StocktakeModel;
import um.vi8e.com.stocktakescanner.R;
/**
 * Created by Fixer on 12/8/2015.
 */


public class CustomDialog {

public static String qty = "1";
public String stockId=null;

public static void showQtyDialog(final Activity thisContext, final HashMap<String, String> productInfo, final StocktakeModel stocktakeModel) {


	final MaterialDialog scoreDialog = new MaterialDialog.Builder(thisContext)
			.customView(R.layout.dialog_qty, true)
			.show();

		/*scoreDialog.setOnDismissListener ( new DialogInterface.OnDismissListener () {
			@Override public
			void onDismiss ( DialogInterface dialog ) {
				thisContext.finishAffinity ();
			}
		} );*/

	TextView itemCode =(TextView)scoreDialog.findViewById(R.id.qtyDialogHeader);
	final TextView qtyTextDialog=(TextView)scoreDialog.findViewById(R.id.qtyText);
	itemCode.setText(thisContext.getString(R.string.qty_dialog_header) + " " + productInfo.get(ProductApiKey.ITEM_CODE));

	final ImageView minusBtn = (ImageView) scoreDialog.findViewById(R.id.minusBtn);
	minusBtn.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			plusQty(-1, qtyTextDialog, minusBtn, thisContext);
		}
	});

	final ImageView plusBtn = (ImageView) scoreDialog.findViewById(R.id.plusBtn);
	plusBtn.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			plusQty(1, qtyTextDialog, minusBtn, thisContext);
		}
	});


	scoreDialog.findViewById(R.id.saveNExit).setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {

			ScannerActivity.saveToDB(thisContext,productInfo.get(ProductApiKey.BARCODE),stocktakeModel.getId(),stocktakeModel.getLocation(),stocktakeModel.getDatetimeStarted(),qtyTextDialog.getText().toString() );
			scoreDialog.dismiss();
		}
	});


	scoreDialog.findViewById(R.id.closeQty).setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			scoreDialog.dismiss();
		}
	});


}

public static void plusQty(int amount, TextView qtyTextDialog,ImageView minusBtn, Activity activity) {

	final int currentQty = Integer.parseInt(qtyTextDialog.getText().toString());
	int newQty = currentQty + amount;
	if (newQty <= 1) {

		minusBtn.setEnabled(false);

		if (newQty < 1) {
			Toast.makeText(activity, "Please press delete", Toast.LENGTH_SHORT).show();
			return;
		}

	}
	else {

		minusBtn.setEnabled(true);
	}

	String newQtyString = String.valueOf(newQty);

	//listModel.setQty(newQtyString);
	qty = newQtyString;
	qtyTextDialog.setText(qty);
}

}

