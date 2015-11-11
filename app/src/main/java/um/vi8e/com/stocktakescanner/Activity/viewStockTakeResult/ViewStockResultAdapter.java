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
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import um.vi8e.com.stocktakescanner.R;

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
	}

}


@Override
public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

	View v = LayoutInflater.from(viewGroup.getContext())
	                       .inflate(R.layout.list_row_stocktake_result, viewGroup, false);
	return new ViewHolder(v);
}

@Override
public void onBindViewHolder(ViewHolder viewHolder, final int position) {
	Log.d(TAG, "Element " + position + " set.");
	StocktakeresultModel listModel = mDataSet.get(position);
	viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {

		}
	});
viewHolder.qty.setText(listModel.getQty());
	viewHolder.barCode.setText(listModel.getBarcode());
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
