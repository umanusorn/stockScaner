/*
* Copyright (C) 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package um.vi8e.com.stocktakescanner.Activity.viewStockTakeResult;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import um.vi8e.com.stocktakescanner.Model.ModelType;
import um.vi8e.com.stocktakescanner.R;
import um.vi8e.com.stocktakescanner.provider.stocktakeresult.StocktakeresultSelection;
import um.vi8e.com.stocktakescanner.utils.ConfirmDialog;
import um.vi8e.com.stocktakescanner.utils.IntentCaller;
import um.vi8e.com.stocktakescanner.utils.RecycleUtil;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class ViewStockResultAdapter extends RecyclerView.Adapter<ViewStockResultAdapter.ViewHolder> {
private static final String TAG = "ViewStockResultAdapter";

private ArrayList<StocktakeresultModel> mDataSet;
private Context                         mContext;

/**
 * Provide a reference to the type of views that you are using (custom ViewHolder)
 */
public static class ViewHolder extends RecyclerView.ViewHolder {
	TextView barCode, qty;
	ImageView plus, minus, delete;
	public StocktakeresultModel model;

	public TextView getBarCode() {
		return barCode;
	}

	public TextView getQty() {
		return qty;
	}

	public ViewHolder(View view) {
		super(view);
		// Define click listener for the ViewHolder's View.
		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(TAG, "Element " + getPosition() + " clicked.");
			}
		});
		barCode = (TextView) view.findViewById(R.id.barcode);
		qty = (TextView) view.findViewById(R.id.qty);
		plus = (ImageView) view.findViewById(R.id.plusBtn);
		minus = (ImageView) view.findViewById(R.id.minusBtn);
		delete = (ImageView) view.findViewById(R.id.deleteBtn);
	}

}


@Override
public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

	View v = LayoutInflater.from(viewGroup.getContext())
	                       .inflate(R.layout.list_row_stocktake_result, viewGroup, false);
	return new ViewHolder(v);
}

@Override
public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
	Log.d(TAG, "Element " + position + " set.");
	final StocktakeresultModel listModel = mDataSet.get(position);
	viewHolder.model = listModel;
	viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			IntentCaller.barcodeNoContinue(StockResultActivity.thisActivity, listModel);
		}
	});

	viewHolder.qty.setText(listModel.getQty());
	viewHolder.barCode.setText(listModel.getBarcode());
	viewHolder.delete.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			deleteBarcode(viewHolder);
		}
	});
	viewHolder.plus.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			plusQty(1, viewHolder);
		}
	});

	viewHolder.minus.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			plusQty(-1, viewHolder);
		}
	});
	final int currentQty = Integer.parseInt(listModel.getQty());
	if(currentQty<=1)
		viewHolder.minus.setEnabled(false);



}

private void plusQty(int amount, ViewHolder viewHolder) {

	final StocktakeresultModel listModel = viewHolder.model;
	final int currentQty = Integer.parseInt(listModel.getQty());
	int newQty = currentQty + amount;
	if (newQty <= 1) {
		viewHolder.minus.setEnabled(false);

		if(newQty<1){
			Toast.makeText(mContext, "Please press delete", Toast.LENGTH_SHORT).show();
			return;
		}

	}
	else{
		viewHolder.minus.setEnabled(true);
	}
	String newQtyString = String.valueOf(newQty);
	listModel.setQty(newQtyString);
	viewHolder.getQty().setText(newQtyString);

}

void deleteBarcode(ViewHolder viewHolder) {
//viewHolder.itemView.setVisibility(View.GONE);
//delete then update recycleView

	ConfirmDialog.show(mContext, viewHolder.model.Barcode, getConfirmListener(viewHolder), "s");

}

@NonNull private ConfirmDialog.ConfirmListener getConfirmListener(final ViewHolder viewHolder) {
	return new ConfirmDialog.ConfirmListener() {
		@Override public void onConfirm(String key) {
			StocktakeresultSelection where = new StocktakeresultSelection();
			where.id(Long.parseLong(viewHolder.model.id));
			where.delete(mContext);
			RecycleUtil.setUpRecycleFragment(StockResultActivity.thisSavedInstanceState, StockResultActivity
					.thisActivity, ModelType.STOCK_RESULT);
		}
	};
}


public ViewStockResultAdapter(ArrayList<StocktakeresultModel> dataSet, Context context) {
	mDataSet = dataSet;
	mContext = context;
}

@Override
public int getItemCount() {
	return mDataSet.size();
}
}
