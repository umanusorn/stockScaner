package um.vi8e.com.stocktakescanner.Activity.viewStockTake;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import um.vi8e.com.stocktakescanner.Activity.CoreActivity;
import um.vi8e.com.stocktakescanner.Model.ModelType;
import um.vi8e.com.stocktakescanner.R;
import um.vi8e.com.stocktakescanner.utils.QueryHelper;
import um.vi8e.com.stocktakescanner.utils.RecycleUtil;

public class viewStockTakeActivity extends CoreActivity implements ActionBar.TabListener{
ViewStockFragment viewStockFragment;
private DrawerLayout mDrawerLayout;
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_view_stocktake);
	mToolbar= (Toolbar) findViewById(R.id.toolbar);
	setSupportActionBar(mToolbar);
	//ActivityUi.setToolBar(thisActivity, mToolbar, "VIEW STOCKTAKE");
	QueryHelper.genListAndTask(getApplicationContext());
  viewStockFragment = (ViewStockFragment) RecycleUtil.setUpRecycleFragment(savedInstanceState,
	                                                                                           thisActivity, ModelType
			                                                                                           .STOCK_TAKE);

	final ActionBar ab = getSupportActionBar();
	ab.setHomeAsUpIndicator(R.drawable.ic_menu);
	ab.setDisplayHomeAsUpEnabled(true);


	tabLayout = (TabLayout) findViewById(R.id.tabLayout);
	tabLayout.addTab(tabLayout.newTab().setText("TIME"));
	tabLayout.addTab(tabLayout.newTab().setText("QTY"));
	tabLayout.addTab(tabLayout.newTab().setText("LOCATION"));
	tabLayout.addTab(tabLayout.newTab().setText("STATUS"));
	tabLayout.setClickable(false);
	tabLayout.setEnabled(false);

	mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

	NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
	if (navigationView != null) {
		setupDrawerContent(navigationView);
	}
}

private void setupDrawerContent(NavigationView navigationView) {
	navigationView.setNavigationItemSelectedListener(
			new NavigationView.OnNavigationItemSelectedListener() {
				@Override
				public boolean onNavigationItemSelected(MenuItem menuItem) {
					menuItem.setChecked(true);
					mDrawerLayout.closeDrawers();
					return true;
				}
			});
}

@Override public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

}

@Override public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

}

@Override public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

}

@Override
public boolean onOptionsItemSelected(MenuItem item) {
	switch (item.getItemId()) {
		case android.R.id.home:
			mDrawerLayout.openDrawer(GravityCompat.START);
			return true;
	}
	return super.onOptionsItemSelected(item);
}
}
