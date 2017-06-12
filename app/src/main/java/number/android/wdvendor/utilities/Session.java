package number.android.wdvendor.utilities;

import android.content.Context;

import number.android.wdvendor.activities.entities.UserSession;
import number.android.wdvendor.database.WDSharedPreferences;

/**
 * Created by user on 1/5/2017.
 */

public class Session {

    WDSharedPreferences wdSharedPreferences;
    Context context;

    public Session(Context context) {
        this.context = context;
    }

    public boolean isRegistered(){
        wdSharedPreferences = new WDSharedPreferences(context);
        return wdSharedPreferences.get();
    }

    public void start(){
        if(isRegistered()) {
            UserSession.getInstance().setVendor_id(wdSharedPreferences.getVendorId());
            UserSession.getInstance().setVendor_name(wdSharedPreferences.getVendorName());
        }
    }

    public void stop(){

    }

}
