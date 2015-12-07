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

package um.vi8e.com.stocktakescanner.Activity.zbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import um.vi8e.com.stocktakescanner.R;

/**
 * Demonstrates the use of {@link RecyclerView} with a {@link LinearLayoutManager} and a
 * {@link GridLayoutManager}.
 */
public class ZBarBtnFragment extends Fragment {

private static final String TAG = "ZBar";
protected RecyclerView.LayoutManager mLayoutManager;
public    ToggleButton               mZbarBtn;
public    TextView                   mAddItemTv, mCancelTv;

@Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
//	initDataSet(getContext());
}

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState)
{
	Log.d(TAG, "onCreateView");
	View rootView = inflater.inflate(R.layout.zbar_btn, container, false);
	rootView.setTag(TAG);
	setView(savedInstanceState, rootView);

	return rootView;
}


private void setView(Bundle savedInstanceState, View rootView) {
// BEGIN_INCLUDE(initializeRecyclerView)
	Log.d(TAG, "setView");
	mZbarBtn = (ToggleButton) rootView.findViewById(R.id.zbarBtn);
	mAddItemTv = (TextView) rootView.findViewById(R.id.addItemTv);
	mCancelTv = (TextView) rootView.findViewById(R.id.cancelAddItemTv);
}

@Override
public void onSaveInstanceState(Bundle savedInstanceState) {
	// Save currently selected layout manager.
	super.onSaveInstanceState(savedInstanceState);
}
}
