package number.android.wdvendor.services;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import org.greenrobot.eventbus.EventBus;

import number.android.wdvendor.activities.entities.DeviceToken;
import number.android.wdvendor.database.WDSharedPreferences;
import number.android.wdvendor.events.FragmentReloadEvent;

public class WDFireBaseInstanceIdService extends FirebaseInstanceIdService {
    private static final String TAG = "WaterDropFireBaseID";

    @Override
    public void onTokenRefresh() {

        //Getting registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        saveToken(refreshedToken);
    }

    private void saveToken(String token) {
        //Store the token on your server
        DeviceToken.getInstance().setToken(token);

        WDSharedPreferences wdSharedPreferences = new WDSharedPreferences(this);
        wdSharedPreferences.setDeviceToken(token);
    }

}

