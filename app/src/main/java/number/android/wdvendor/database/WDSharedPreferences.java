package number.android.wdvendor.database;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by user on 11/17/2016.
 */

public class WDSharedPreferences {

    private Context context;
    public static final String VENDOR = "fresher";
    public static final String VENDOR_ID = "vendor_id";
    public static final String VENDOR_NAME = "vendor_name";
    public static final String DEVICE_TOKEN = "device_token";


    public WDSharedPreferences(Context context) {
        this.context = context;
    }

    public void set(){
        SharedPreferences.Editor vendor_editor = context.getSharedPreferences(VENDOR, MODE_PRIVATE).edit();
        vendor_editor.putBoolean("fresher", true);
        vendor_editor.commit();
    }

    public boolean get(){
        SharedPreferences prefs = context.getSharedPreferences(VENDOR, MODE_PRIVATE);
        return prefs.getBoolean("fresher", false);
    }

    /**
     *  Vendor id
     */
    public void setVendorId(int vendorId){
        SharedPreferences.Editor vendor_editor = context.getSharedPreferences(VENDOR_ID, MODE_PRIVATE).edit();
        vendor_editor.putInt("vendor_id", vendorId);
        vendor_editor.commit();
    }

    public int getVendorId(){
        SharedPreferences prefs = context.getSharedPreferences(VENDOR_ID, MODE_PRIVATE);
        return prefs.getInt("vendor_id",0);
    }

    /**
     *  Vendor name
     */
    public void setVendorName(String vendorName){
        SharedPreferences.Editor vendor_editor = context.getSharedPreferences(VENDOR_NAME, MODE_PRIVATE).edit();
        vendor_editor.putString(VENDOR_NAME, vendorName);
        vendor_editor.commit();
    }

    public String getVendorName(){
        SharedPreferences prefs = context.getSharedPreferences(VENDOR_NAME, MODE_PRIVATE);
        return prefs.getString(VENDOR_NAME,"Waterdrop - Vendor");
    }

    /**
     *  Device token
     */
    public void setDeviceToken(String deviceToken){
        SharedPreferences.Editor vendor_editor = context.getSharedPreferences(DEVICE_TOKEN, MODE_PRIVATE).edit();
        vendor_editor.putString(DEVICE_TOKEN, deviceToken);
        vendor_editor.commit();
    }

    public String getDeviceToken(){
        SharedPreferences prefs = context.getSharedPreferences(DEVICE_TOKEN, MODE_PRIVATE);
        return prefs.getString(DEVICE_TOKEN,"NA");
    }

}
