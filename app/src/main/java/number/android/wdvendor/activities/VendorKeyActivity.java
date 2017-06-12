package number.android.wdvendor.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import number.android.wdvendor.R;
import number.android.wdvendor.activities.entities.DeviceToken;
import number.android.wdvendor.activities.entities.UserSession;
import number.android.wdvendor.cloud.entities.request.VendorVerificationReq;
import number.android.wdvendor.cloud.entities.response.VendorVerificationRes;
import number.android.wdvendor.cloud.rest.Callbacks;
import number.android.wdvendor.cloud.rest.Client;
import number.android.wdvendor.database.WDSharedPreferences;
import retrofit2.Call;
import retrofit2.Response;

public class VendorKeyActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_continue,btn_verify;
    EditText vendor_key,vendor_mobile;
    LinearLayout wizard1,wizard2;

    Client client;

    WDSharedPreferences wdSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_key);
        initView();
    }

    private void initView() {
        vendor_key = (EditText) findViewById(R.id.vendor_key);
        vendor_mobile = (EditText) findViewById(R.id.vendor_mobile);

        btn_continue = (Button) findViewById(R.id.btn_continue);
        btn_continue.setOnClickListener(this);

        btn_verify = (Button) findViewById(R.id.btn_verify);
        btn_verify.setOnClickListener(this);

        wizard1 = (LinearLayout) findViewById(R.id.wizard1);
        wizard2 = (LinearLayout) findViewById(R.id.wizard2);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_continue:
                if( validateVendorMobile() ){
                    hideWizard1();
                }
                break;
            case R.id.btn_verify:
                if(validateVendorVerification())
                    verifyVendor();
                break;
        }
    }

    public void showWizard1(){
        wizard1.setVisibility(View.VISIBLE);
    }

    public void hideWizard1(){
        wizard1.animate().alpha(0.0f).setDuration(500).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                wizard1.setVisibility(View.GONE);
                showWizard2();
            }
        });

    }

    public void showWizard2(){
        wizard1.animate().alpha(1.0f).setDuration(500).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                wizard2.setVisibility(View.VISIBLE);
            }
        });

    }

    public void hideWizard2(){
        wizard2.setVisibility(View.GONE);
    }


    public void toOrdersScreen() {
        Intent intent = new Intent(VendorKeyActivity.this, WaterDropVendorActivity.class);
        startActivity(intent);
    }

    public void verifyVendor(){

        if (validateDeviceToken()) {
            Toast.makeText(this,"Verifying...",Toast.LENGTH_LONG).show();

            VendorVerificationReq vendorVerificationReq = new VendorVerificationReq();
            vendorVerificationReq.setMobile_number(vendor_mobile.getText().toString());
            vendorVerificationReq.setVerificationCode(vendor_key.getText().toString());
            vendorVerificationReq.setDeviceToken(DeviceToken.getInstance().getToken());
            vendorVerificationReq.setDeviceOs("A");
            vendorVerificationReq.setDeviceOsVersion(Build.VERSION.RELEASE);

            client = new Client();
            Call<VendorVerificationRes> call = client.get().VerifyVendor(vendorVerificationReq);
            call.enqueue(new Callbacks<VendorVerificationRes>() {
                @Override
                public void onResponse(Call<VendorVerificationRes> call, Response<VendorVerificationRes> response) {
                    if (response.code() == 200) {
                        processResponse(response.body());
                    } else {
                        Toast.makeText(VendorKeyActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    void processResponse(VendorVerificationRes vendorVerificationRes){

        WDSharedPreferences wdSharedPreferences = new WDSharedPreferences(this);
        wdSharedPreferences.set();

        wdSharedPreferences.setVendorId( vendorVerificationRes.getVendorId() );
        wdSharedPreferences.setVendorName( vendorVerificationRes.getName() );

        UserSession.getInstance().setVendor_id( vendorVerificationRes.getVendorId() );
        UserSession.getInstance().setVendor_name( vendorVerificationRes.getName() );

        toOrdersScreen();
    }

    boolean validateVendorVerification() {
        if (vendor_key.getText().toString().isEmpty()) {
            vendor_key.setBackgroundResource(R.drawable.edit_text_border_error);
            vendor_key.setHintTextColor(ContextCompat.getColor(this, R.color.red));
            vendor_key.setHint("Enter verification Code");
            return false;
        } else {
            return true;
        }
    }

    boolean validateVendorMobile() {

        if (vendor_mobile.getText().toString().isEmpty()) {
            vendor_mobile.setBackgroundResource(R.drawable.edit_text_border_error);
            vendor_mobile.setHintTextColor(ContextCompat.getColor(this, R.color.red));
            vendor_mobile.setHint("Enter mobile number");
            return false;
        } else {
            if( vendor_mobile.getText().toString().length() != 10 ) {
                vendor_mobile.setBackgroundResource(R.drawable.edit_text_border_error);
                vendor_mobile.setHintTextColor(ContextCompat.getColor(this, R.color.red));
                return false;
            } else {
                return true;
            }
        }
    }

    boolean validateDeviceToken() {
        wdSharedPreferences = new WDSharedPreferences(this);
        if(wdSharedPreferences.getDeviceToken() != null && wdSharedPreferences.getDeviceToken() != "") {
            return true;
        } else {
            Toast.makeText(this,"Device token empty",Toast.LENGTH_LONG).show();
            return false;
        }
    }



}
