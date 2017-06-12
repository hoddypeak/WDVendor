package number.android.wdvendor.cloud.rest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;


public abstract class Callbacks<T> implements Callback<T> {

    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }

    //public abstract void failure(Errors restError);

//    @Override
//    public void failure( error) {
//
//        Errors restError = (Errors) error.getBodyAs(RestError.class); // create your own class as
//        // how the error message gonna showup from server side if there is an error
//        if (restError != null && isJSONValid(restError.getErrorMessage()))
//            failure(restError);
//        else {
//            failure(new RestError(error.getMessage()));
//        }
//    }

    public boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
}