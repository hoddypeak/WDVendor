package number.android.wdvendor.network;

/**
 * Created by user on 12/25/2016.
 */
public class Internet {

    private static Internet ourInstance = new Internet();

    public static Internet getInstance() {
        return ourInstance;
    }

    private static boolean internetConnectionStatus;

    private Internet() {
    }

    public static boolean isInternetConnected() {
        return internetConnectionStatus;
    }

    public static void setInternetConnectionStatus(boolean internetConnectionStatus) {
        Internet.internetConnectionStatus = internetConnectionStatus;
    }
}
