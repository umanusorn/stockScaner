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

package um.vi8e.com.stocktakescanner.Activity.viewStockTake;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import um.vi8e.com.stocktakescanner.R;
import um.vi8e.com.stocktakescanner.utils.IntentCaller;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class ViewStockAdapter extends RecyclerView.Adapter<ViewStockAdapter.ViewHolder> {
private static final String TAG = "ViewStockResultAdapter";

private ArrayList<StocktakeModel> mDataSet;
private Context                   mContext;

// BEGIN_INCLUDE(recyclerViewSampleViewHolder)

/**
 * Provide a reference to the type of views that you are using (custom ViewHolder)
 */
public static class ViewHolder extends RecyclerView.ViewHolder {

TextView dateTieme,location,status;

	public ViewHolder(View view) {
		super(view);
		// Define click listener for the ViewHolder's View.
		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(TAG, "Element " + getPosition() + " clicked.");

			}
		});

		dateTieme = (TextView)view.findViewById(R.id.datetime);
		location = (TextView)view.findViewById(R.id.location);
		status = (TextView)view.findViewById(R.id.status);
	}

}

/**
 * Initialize the dataset of the Adapter.
 *
 * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
 */
public ViewStockAdapter(ArrayList<StocktakeModel> dataSet, Context context) {
	mDataSet = dataSet;
	mContext = context;
}

// Create new views (invoked by the layout manager)
@Override
public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
	// Create a new view.
	View v = LayoutInflater.from(viewGroup.getContext())
	                       .inflate(R.layout.recycle_stocktake, viewGroup, false);
	return new ViewHolder(v);
}

// Replace the contents of a view (invoked by the layout manager)
@Override
public void onBindViewHolder(ViewHolder viewHolder, final int position) {
	Log.d(TAG, "Element " + position + " set.");
	final StocktakeModel listModel = mDataSet.get(position);
	viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			IntentCaller.viewStockTakeResult(viewStockTakeActivity.thisActivity,listModel);
		}
	});
	viewHolder.dateTieme.setText(listModel.getDatetimeStarted());
	viewHolder.location.setText(listModel.getLocation());
	viewHolder.status.setText(listModel.getStatus());

}

@Override
public int getItemCount() {
	return mDataSet.size();
}
}
