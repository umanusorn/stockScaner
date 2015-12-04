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

package um.vi8e.com.stocktakescanner.zbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import um.vi8e.com.stocktakescanner.Activity.viewStockTake.StocktakeModel;
import um.vi8e.com.stocktakescanner.Activity.viewStockTake.ViewStockAdapter;
import um.vi8e.com.stocktakescanner.R;

/**
 * Demonstrates the use of {@link RecyclerView} with a {@link LinearLayoutManager} and a
 * {@link GridLayoutManager}.
 */
public class ZBarBtnFragment extends Fragment {

private static final String TAG = "ViewStockResultFragment";
protected RecyclerView               mRecyclerView;
protected ViewStockAdapter           mAdapter;
protected RecyclerView.LayoutManager mLayoutManager;
public static ArrayList<StocktakeModel>  mDataSet;

public RecyclerView getRecyclerView() {
	return mRecyclerView;
}

@Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
//	initDataSet(getContext());
}

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState)
{Log.d(TAG,"onCreateView");
	View rootView = inflater.inflate(R.layout.zbar_btn, container, false);
	rootView.setTag(TAG);
	setView(savedInstanceState, rootView);


	return rootView;
}


private void setView(Bundle savedInstanceState, View rootView) {
// BEGIN_INCLUDE(initializeRecyclerView)
	Log.d(TAG,"setView");
	//mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
  //mRecyclerView.addOnScrollListener(viewStockTakeActivity.getOnScroll());

	// LinearLayoutManager is used here, this will layout the elements in a similar fashion
	// to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
	// elements are laid out.
	mLayoutManager = new LinearLayoutManager(getActivity());

}

@Override
public void onSaveInstanceState(Bundle savedInstanceState) {
	// Save currently selected layout manager.
	super.onSaveInstanceState(savedInstanceState);
}
}
