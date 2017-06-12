package number.android.wdvendor.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import number.android.wdvendor.adapters.entities.Order;
import number.android.wdvendor.fragments.subfragments.OrdersClosedFragment;
import number.android.wdvendor.fragments.subfragments.OrdersOpenFragment;

/**
 * Created by user on 8/26/2016.
 */
public class OrdersPagesAdapter extends FragmentStatePagerAdapter {

    int tabCount;
    OrdersOpenFragment ordersOpenFragment;
    OrdersClosedFragment ordersClosedFragment;

    public OrdersPagesAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
        ordersOpenFragment = new OrdersOpenFragment();
        ordersClosedFragment = new OrdersClosedFragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return ordersOpenFragment;
            case 1:
                return ordersClosedFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
