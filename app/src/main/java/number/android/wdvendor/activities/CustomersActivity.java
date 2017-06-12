package number.android.wdvendor.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import number.android.wdvendor.R;
import number.android.wdvendor.activities.entities.UserSession;
import number.android.wdvendor.fragments.CustomersFragment;
import number.android.wdvendor.utilities.Session;
import number.android.wdvendor.utilities.WDMaterialDrawer;
import number.android.wdvendor.utilities.WDProgressDialog;

public class CustomersActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);
        new Session(this).start();
        initView();
    }

    private void initView(){

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Customers");
        setSupportActionBar(toolbar);

        WDMaterialDrawer wdMaterialDrawer = new WDMaterialDrawer(this,toolbar);

        Fragment customers = new CustomersFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, customers, "1");
        transaction.commit();
    }
}
