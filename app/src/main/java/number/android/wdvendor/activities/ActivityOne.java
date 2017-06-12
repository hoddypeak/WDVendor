package number.android.wdvendor.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import number.android.wdvendor.R;
import number.android.wdvendor.activities.entities.UserSession;
import number.android.wdvendor.database.WDSharedPreferences;
import number.android.wdvendor.events.NetworkState;

public class ActivityOne extends AppCompatActivity {

    WDSharedPreferences wdSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_one);

        if(!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isRegistered()){
                    UserSession.getInstance().setVendor_id( wdSharedPreferences.getVendorId() );
                    UserSession.getInstance().setVendor_name( wdSharedPreferences.getVendorName() );
                    toVendorScreen();
                } else {
                    toVerificationScreen();
                }
            }
        }, 1000);
    }

    @Override
    protected void onStop() {
        if(EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe
    public void onNetworkState(NetworkState event) {
        if (!event.isInternetConnected()) {
            Toast.makeText(this, "No Internet connection!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Yes Internet connection!", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isRegistered(){
        wdSharedPreferences = new WDSharedPreferences(this);
        return wdSharedPreferences.get();
    }

    void toVerificationScreen(){
        Intent intent = new Intent(ActivityOne.this,VendorKeyActivity.class);
        startActivity(intent);
        finish();
    }

    void toVendorScreen(){
        Intent intent = new Intent(ActivityOne.this,WaterDropVendorActivity.class);
        startActivity(intent);
        finish();
    }
}
