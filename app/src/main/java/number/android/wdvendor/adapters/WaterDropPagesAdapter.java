package number.android.wdvendor.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import number.android.wdvendor.fragments.CustomersFragment;
import number.android.wdvendor.fragments.OrdersFragment;
import number.android.wdvendor.fragments.PreferencesFragment;
import number.android.wdvendor.fragments.subfragments.OrdersOpenFragment;

/**
 * Created by user on 8/26/2016.
 */
public class WaterDropPagesAdapter extends FragmentStatePagerAdapter {

    int tabCount;

    public WaterDropPagesAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                OrdersFragment ordersFragment = new OrdersFragment();
                return ordersFragment;
            case 1:
                CustomersFragment customersFragment = new CustomersFragment();
                return customersFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

}
