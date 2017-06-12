package number.android.wdvendor.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import number.android.wdvendor.R;
import number.android.wdvendor.activities.ActivityOne;
import number.android.wdvendor.activities.CustomersActivity;
import number.android.wdvendor.activities.PreferencesActivity;
import number.android.wdvendor.activities.WaterDropVendorActivity;
import number.android.wdvendor.activities.entities.UserSession;

/**
 * Created by user on 12/27/2016.
 */

public class WDMaterialDrawer {

    Activity activity;

    SecondaryDrawerItem orders,customers,settings;

    Drawer result;

    Icons icons;
    public WDMaterialDrawer(final Activity activity, Toolbar toolbar) {
        this.activity = activity;
        icons = new Icons(activity);

        orders = new SecondaryDrawerItem().withIdentifier(1)
                .withName(R.string.drawer_item_orders)
                .withIcon(icons.receiptIcon(R.color.colorPrimary)).withSelectable(false);

        customers = new SecondaryDrawerItem().withIdentifier(2)
                .withName(R.string.drawer_item_customers)
                .withIcon(icons.personIcon()).withSelectable(false);

        settings = new SecondaryDrawerItem().withIdentifier(3)
                .withName(R.string.drawer_item_settings)
                .withIcon(icons.settingsIcon(R.color.colorPrimary)).withSelectable(false);


        result = new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)
                .withDisplayBelowStatusBar(false)
                .withTranslucentStatusBar(false)
                .withAccountHeader(getDrawerHeader())
                .addDrawerItems(
                        new SectionDrawerItem().withName(UserSession.getInstance().getVendor_name()),
                        orders,
                        customers,
                        settings
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        switch (position){
                            case 2:
                                toOrdersScreen();
                                break;
                            case 3:
                                toCustomersScreen();
                                break;
                            case 4:
                                toSettingsScreen();
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                })
                .build();
    }

    public void setActiveDrawerItem(int itemId){
        //result.setSelection(itemId);
    }


    private AccountHeader getDrawerHeader(){
        AccountHeader header = new AccountHeaderBuilder()
                .withActivity(activity)
                .withHeaderBackground(R.color.colorPrimary)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withSelectable(false)
                                .withNameShown(true)
                )
                .withSelectionListEnabledForSingleProfile(false)
                .build();
        return header;
    }

    void toCustomersScreen(){
        Intent intent = new Intent(activity,CustomersActivity.class);
        activity.startActivity(intent);
    }

    void toOrdersScreen(){
        Intent intent = new Intent(activity,WaterDropVendorActivity.class);
        activity.startActivity(intent);
    }

    void toSettingsScreen(){
        Intent intent = new Intent(activity,PreferencesActivity.class);
        activity.startActivity(intent);
    }


}
