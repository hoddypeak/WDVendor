package number.android.wdvendor.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.List;

import number.android.wdvendor.R;
import number.android.wdvendor.activities.entities.UserSession;
import number.android.wdvendor.adapters.OrdersPagesAdapter;
import number.android.wdvendor.adapters.entities.Customer;
import number.android.wdvendor.adapters.entities.Order;
import number.android.wdvendor.cloud.entities.request.CustomersReq;
import number.android.wdvendor.cloud.entities.request.OrdersReq;
import number.android.wdvendor.cloud.entities.response.CustomersRes;
import number.android.wdvendor.cloud.entities.response.OrdersRes;
import number.android.wdvendor.cloud.rest.Callbacks;
import number.android.wdvendor.cloud.rest.Client;
import number.android.wdvendor.utilities.DateTime;
import number.android.wdvendor.utilities.Icons;
import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrdersFragment extends Fragment {

    TabLayout orders_segment_tabs;
    ViewPager orders_history_pages;
    View view;
    OrdersPagesAdapter orderHistoryPagesAdapter;


    public OrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_orders, container, false);
        initView(view);

        // Inflate the layout for this fragment
        return view;
    }

    private void initView(View view) {

        orders_segment_tabs = (TabLayout) view.findViewById(R.id.orders_segment_tabs);
        orders_segment_tabs.addOnTabSelectedListener(new OnSegmentTabsSelected());

        orders_history_pages = (ViewPager) view.findViewById(R.id.orders_history_pages);
        orderHistoryPagesAdapter = new OrdersPagesAdapter(getChildFragmentManager(),orders_segment_tabs.getTabCount());
        orders_history_pages.setAdapter(orderHistoryPagesAdapter);
        orders_history_pages.addOnPageChangeListener(new OnOrderHistoryPageChange());

        Bundle extras = getArguments();
        orders_segment_tabs.getTabAt( extras.getInt("request_type",0) ).select();

    }

    private class OnSegmentTabsSelected implements TabLayout.OnTabSelectedListener {

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            orders_history_pages.setCurrentItem(tab.getPosition(),true);
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }

    private class OnOrderHistoryPageChange implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            orders_segment_tabs.getTabAt(position).select();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}
