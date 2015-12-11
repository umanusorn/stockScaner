package um.vi8e.com.stocktakescanner.utils;

import android.app.Activity;
import android.view.View;
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
	final TextView qtyTextDialog=(TextView)scoreDialog.findViewById(R.id.qtyTextDialog);
	itemCode.setText(thisContext.getString(R.string.qty_dialog_header)+" "+productInfo.get(ProductApiKey.ITEM_CODE));

	scoreDialog.findViewById(R.id.minusBtn).setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			plusQty(-1, scoreDialog,thisContext);
		}
	});

	scoreDialog.findViewById(R.id.plusBtn).setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			plusQty(1, scoreDialog,thisContext);
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

static void plusQty(int amount, MaterialDialog scoreDialog, Activity activity) {

	//final StocktakeresultModel listModel = viewHolder.model;
	//final int currentQty = Integer.parseInt(listModel.getQty());
	TextView qtyTextDialog = (TextView) scoreDialog.findViewById(R.id.qtyTextDialog);
	final int currentQty = Integer.parseInt(qtyTextDialog.getText().toString());
	int newQty = currentQty + amount;
	if (newQty <= 1) {
		scoreDialog.findViewById(R.id.minusBtn).setEnabled(false);

		if (newQty < 1) {
			Toast.makeText(activity, "Please press delete", Toast.LENGTH_SHORT).show();
			return;
		}

	}
	else {
		scoreDialog.findViewById(R.id.minusBtn).setEnabled(true);
	}

	String newQtyString = String.valueOf(newQty);

	//listModel.setQty(newQtyString);
	qty = newQtyString;
	qtyTextDialog.setText(qty);
}

}

