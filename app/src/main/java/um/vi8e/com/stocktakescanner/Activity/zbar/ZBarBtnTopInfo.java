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

import java.util.HashMap;
import java.util.Map;

import um.vi8e.com.stocktakescanner.R;
import um.vi8e.com.stocktakescanner.utils.ProductApiKey;

/**
 * Demonstrates the use of {@link RecyclerView} with a {@link LinearLayoutManager} and a
 * {@link GridLayoutManager}.
 */
public class ZBarBtnTopInfo extends Fragment {

private static final String TAG = "ZBar";

public TextView itemCodeTv, barCodeTv, titleTv, descriptionTv;

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
	View rootView = inflater.inflate(R.layout.zbar_topinfo, container, false);
	rootView.setTag(TAG);
	setView(savedInstanceState, rootView);


	return rootView;
}


private void setView(Bundle savedInstanceState, View rootView) {
// BEGIN_INCLUDE(initializeRecyclerView)
	Log.d(TAG, "setView");
	//mZbarBtn = (ToggleButton) rootView.findViewById(R.id.zbarBtn);
	itemCodeTv = (TextView) rootView.findViewById(R.id.itemcodeValue);
	barCodeTv = (TextView) rootView.findViewById(R.id.barcodeValue);
	titleTv = (TextView) rootView.findViewById(R.id.titleTv);
	descriptionTv = (TextView) rootView.findViewById(R.id.descriptionTv);

}

public void setViewFromJson(HashMap<String, String> productInfo){
	//barcodeTv.setText(productInfo.get(ProductApiKey.BARCODE));


	String fulldetail="";
	for (Map.Entry<String, String> entry : productInfo.entrySet())
	{
		fulldetail+= entry.getKey() + ":\t\t" + entry.getValue()+"\n";
	}
	//fullDetailTv.setText(fulldetail);

	if(productInfo.get(ProductApiKey.STATUS).equals("AS")){
		//priceTv.setText(productInfo.get(ProductApiKey.REGULAR_PRICE));
		titleTv.setText(productInfo.get(ProductApiKey.DESCRIPTION));
		barCodeTv.setText(productInfo.get(ProductApiKey.BARCODE));
		itemCodeTv.setText(productInfo.get(ProductApiKey.ITEM_CODE));
		descriptionTv.setText("-");
	}
	else{

		titleTv.setText("-");
		barCodeTv.setText("-");
		itemCodeTv.setText("-");
		descriptionTv.setText("-");
	}
}

@Override
public void onSaveInstanceState(Bundle savedInstanceState) {
	// Save currently selected layout manager.
	super.onSaveInstanceState(savedInstanceState);
}
}
