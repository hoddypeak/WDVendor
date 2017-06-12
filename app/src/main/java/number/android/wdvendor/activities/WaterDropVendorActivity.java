package number.android.wdvendor.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import number.android.wdvendor.R;
import number.android.wdvendor.activities.entities.UserSession;
import number.android.wdvendor.adapters.WaterDropPagesAdapter;
import number.android.wdvendor.events.FragmentReloadEvent;
import number.android.wdvendor.fragments.CustomersFragment;
import number.android.wdvendor.fragments.OrdersFragment;
import number.android.wdvendor.utilities.Icons;
import number.android.wdvendor.utilities.Session;
import number.android.wdvendor.utilities.WDMaterialDrawer;

public class WaterDropVendorActivity extends AppCompatActivity {

    private TabLayout tabs;
    private ViewPager pages;
    Toolbar toolbar;
    WaterDropPagesAdapter waterDropPagesAdapter;
    Icons icons;

    int default_tab = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_water_drop_vendor);
        setContentView(R.layout.activity_orders);

        new Session(this).start();
        icons = new Icons(this);

    }

    @Override
    protected void onResume() {
        initView();
        super.onResume();
    }

    private void initView(){

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(UserSession.getInstance().getVendor_name());
        setSupportActionBar(toolbar);

        Bundle data = new Bundle();
        try {
            default_tab = getIntent().getIntExtra("request_type",0);
            data.putInt("request_type", default_tab);
        } catch ( Exception e){
            data.putInt("request_type", 0);
        }

        Fragment orders = new OrdersFragment();
        orders.setArguments(data);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.frame, orders, "1");
        transaction.commit();

        WDMaterialDrawer wdMaterialDrawer = new WDMaterialDrawer(this,toolbar);
        wdMaterialDrawer.setActiveDrawerItem(1);

    }

    private void initView_(){

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(UserSession.getInstance().getVendor_name());
        setSupportActionBar(toolbar);

        tabs = (TabLayout) findViewById(R.id.tabs);
        //tabs.addTab(tabs.newTab().setIcon(icons.settingsIcon()));
        tabs.addTab(tabs.newTab().setIcon(icons.receiptIcon(Color.parseColor(Icons.primaryColor))));
        tabs.addTab(tabs.newTab().setIcon(icons.customerIcon(Color.parseColor(Icons.grey_medium))));
        tabs.addOnTabSelectedListener(new OnTabsSelected());

        pages = (ViewPager) findViewById(R.id.pages);
        waterDropPagesAdapter = new WaterDropPagesAdapter(getSupportFragmentManager(),tabs.getTabCount());
        pages.setAdapter(waterDropPagesAdapter);
        pages.addOnPageChangeListener(new OnPageChange());

        tabs.getTabAt(default_tab).select();

        WDMaterialDrawer wdMaterialDrawer = new WDMaterialDrawer(this,toolbar);
        wdMaterialDrawer.setActiveDrawerItem(1);

    }

    void toSettingsScreen(){
        Intent intent = new Intent(WaterDropVendorActivity.this,PreferencesActivity.class);
        startActivity(intent);
    }

    private class OnTabsSelected implements TabLayout.OnTabSelectedListener {

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            pages.setCurrentItem(tab.getPosition(),true);
            OrderTabStateChangeIndicator(tab.getPosition());
            EventBus.getDefault().post(new FragmentReloadEvent());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }

    private class OnPageChange implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            tabs.getTabAt(position).select();
            OrderTabStateChangeIndicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    public void OrderTabStateChangeIndicator(int position){
        if(position == 0) {
            tabs.getTabAt(0).setIcon(icons.receiptIcon(Color.parseColor(Icons.primaryColor)));
            tabs.getTabAt(1).setIcon(icons.customerIcon(Color.parseColor(Icons.grey_medium)));
        } else {
            tabs.getTabAt(0).setIcon(icons.receiptIcon(Color.parseColor(Icons.grey_medium)));
            tabs.getTabAt(1).setIcon(icons.customerIcon(Color.parseColor(Icons.primaryColor)));
        }
    }

}
