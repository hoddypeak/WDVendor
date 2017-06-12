package number.android.wdvendor.activities.entities;

/**
 * Created by user on 10/10/2016.
 */

public class UserSession {

    private static UserSession ourInstance = new UserSession();

    public static UserSession getInstance() {
        return ourInstance;
    }

    private static int vendor_id = 0;
    private static String vendor_name;

    private UserSession() {
    }

    public static int getVendor_id() {
        return vendor_id;
    }

    public static void setVendor_id(int vendor_id) {
        UserSession.vendor_id = vendor_id;
    }

    public static UserSession getOurInstance() {
        return ourInstance;
    }

    public static void setOurInstance(UserSession ourInstance) {
        UserSession.ourInstance = ourInstance;
    }

    public static String getVendor_name() {
        return vendor_name;
    }

    public static void setVendor_name(String vendor_name) {
        UserSession.vendor_name = vendor_name;
    }
}
