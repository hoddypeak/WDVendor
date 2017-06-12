package number.android.wdvendor.activities;

import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang3.text.WordUtils;

import number.android.wdvendor.R;
import number.android.wdvendor.activities.entities.UserSession;
import number.android.wdvendor.adapters.entities.Customer;
import number.android.wdvendor.cloud.entities.request.CustomerMembershipReq;
import number.android.wdvendor.cloud.entities.request.UpdateCustomerReq;
import number.android.wdvendor.cloud.entities.response.CustomerMembershipRes;
import number.android.wdvendor.cloud.entities.response.UpdateCustomerRes;
import number.android.wdvendor.cloud.rest.Callbacks;
import number.android.wdvendor.cloud.rest.Client;
import number.android.wdvendor.utilities.Session;
import number.android.wdvendor.utilities.WDProgressDialog;
import retrofit2.Call;
import retrofit2.Response;

public class CustomerSummaryActivity extends AppCompatActivity implements View.OnClickListener {


    Customer customer;
    EditText landmark;
    Button update;
    TableRow action;
    WDProgressDialog wdProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_summary);
        new Session(this).start();
        wdProgressDialog = new WDProgressDialog(this);
        /**
         * get data from intent
         */
        Bundle bundle = this.getIntent().getExtras();
        customer = (Customer) bundle.getSerializable("customer");

        /**
         * initialize the view
         */
        initView();

    }

    private void initView(){

        /**
         * Can count for the month
         */
        TextView can_count_label = (TextView) findViewById(R.id.can_count_label);
        Resources res = getResources();
        String text = String.format(res.getString(R.string.can_count_label), "Cans", "November 2016");
        CharSequence styledText = Html.fromHtml(text);
        can_count_label.setText(styledText);

        // set customer name
        TextView name = (TextView) findViewById(R.id.name);
        name.setText(WordUtils.capitalize(customer.getName()));
        // set mobile number
        TextView mobile_number = (TextView) findViewById(R.id.mobile_number);
        mobile_number.setText(customer.getMobile_number());
        // set address
        TextView address = (TextView) findViewById(R.id.address);
        address.setText(customer.getCustomerAddress().getAddress());
        // set landmark
        landmark = (EditText) findViewById(R.id.landmark);
        landmark.setText(customer.getCustomerAddress().getLandmark());

        update = (Button) findViewById(R.id.update);
        update.setOnClickListener(this);

        action = (TableRow) findViewById(R.id.action);

        Button accept_customer = (Button) findViewById(R.id.accept_customer);
        accept_customer.setOnClickListener(this);

        Button reject_customer = (Button) findViewById(R.id.reject_customer);
        reject_customer.setOnClickListener(this);

        switch (customer.getStatus()){
            case 1:
                landmark.setEnabled(false);
                update.setVisibility(View.INVISIBLE);
                action.setVisibility(View.VISIBLE);
                break;
            case 2:
                landmark.setEnabled(true);
                update.setVisibility(View.VISIBLE);
                action.setVisibility(View.INVISIBLE);
                break;
            case 3:
                landmark.setEnabled(false);
                update.setVisibility(View.INVISIBLE);
                action.setVisibility(View.INVISIBLE);
                break;
            default:
                landmark.setEnabled(false);
                update.setVisibility(View.INVISIBLE);
                action.setVisibility(View.VISIBLE);
                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.update:
                if(validateVendorVerification())
                    updateCustomerDetails();
                break;
            case R.id.accept_customer:
                updateMembershipStatus(customer.getRequest_id(),2);
                break;
            case R.id.reject_customer:
                updateMembershipStatus(customer.getRequest_id(),3);
                break;
        }
    }

    boolean validateVendorVerification() {

        boolean L = true;
        if (landmark.getText().toString().isEmpty()) {
            landmark.setBackgroundResource(R.drawable.edit_text_border_error);
            landmark.setHintTextColor(ContextCompat.getColor(this, R.color.red));
            landmark.setHint("Enter Landmark");
            L = false;
        }

        if(L){
            return true;
        } else {
            return false;
        }
    }

    void updateCustomerDetails(){

        final UpdateCustomerReq updateCustomerReq = new UpdateCustomerReq();

        updateCustomerReq.setUser(getUser());
        updateCustomerReq.setUserLocation(getUserLocation());

        Client client = new Client();
        Call<UpdateCustomerRes> call = client.get().UpdateCustomerDetails(updateCustomerReq);
        call.enqueue(new Callbacks<UpdateCustomerRes>() {
            @Override
            public void onResponse(Call<UpdateCustomerRes> call, Response<UpdateCustomerRes> updateCustomerResResponse) {
                if(updateCustomerResResponse.code() == 200) {
                    processResponse(updateCustomerResResponse.body().message);
                } else {
                    Toast.makeText(CustomerSummaryActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    UpdateCustomerReq.User getUser(){
        UpdateCustomerReq.User user = new UpdateCustomerReq.User();
        user.setId(customer.getId());
        return user;
    }

    UpdateCustomerReq.UserLocation getUserLocation(){
        UpdateCustomerReq.UserLocation customerLocation = new UpdateCustomerReq.UserLocation();
        customerLocation.setLandmark(landmark.getText().toString());
        return customerLocation;
    }

    /**
     *
     * @param message
     */
    void processResponse(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }


    public void updateMembershipStatus(int request_id,final int status){

        wdProgressDialog.setMessage("Updating status...");
        wdProgressDialog.show();

        CustomerMembershipReq customerMembershipReq = new CustomerMembershipReq();
        //customerMembershipReq.setUserId(UserSession.getInstance().getUser_id());
        customerMembershipReq.setVendor_id(UserSession.getVendor_id());
        customerMembershipReq.setStatus(status);
        customerMembershipReq.setRequest_id(request_id);

        Client client = new Client();
        Call<CustomerMembershipRes> call = client.get().CustomerMembership(customerMembershipReq);
        call.enqueue(new Callbacks<CustomerMembershipRes>() {
            @Override
            public void onResponse(Call<CustomerMembershipRes> call, Response<CustomerMembershipRes> customerMembershipResResponse) {
                if(customerMembershipResResponse.code() == 200) {
                    processResponse(customerMembershipResResponse.body().getMessage(),status);
                } else {
                    Toast.makeText(CustomerSummaryActivity.this, "Some thing went wrong", Toast.LENGTH_LONG).show();
                }
                wdProgressDialog.dismissWdProgressDialog();
            }

            @Override
            public void onFailure(Call<CustomerMembershipRes> call, Throwable t) {
                wdProgressDialog.dismissWdProgressDialog();
            }
        });
    }

    void processResponse(String message,int status){

        action.setVisibility(View.INVISIBLE);
        if(status == 2){
            landmark.setEnabled(true);
            update.setVisibility(View.VISIBLE);
            action.setVisibility(View.INVISIBLE);
        } else if(status == 3) {
            landmark.setEnabled(false);
            update.setVisibility(View.INVISIBLE);
            action.setVisibility(View.INVISIBLE);
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
