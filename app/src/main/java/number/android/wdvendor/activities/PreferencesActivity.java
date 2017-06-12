package number.android.wdvendor.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import number.android.wdvendor.R;
import number.android.wdvendor.activities.entities.UserSession;
import number.android.wdvendor.adapters.OpenOrdersListAdapter;
import number.android.wdvendor.adapters.PreferencesListAdapter;
import number.android.wdvendor.adapters.entities.Order;
import number.android.wdvendor.adapters.entities.Preference;
import number.android.wdvendor.cloud.entities.request.ProfileUpdateReq;
import number.android.wdvendor.cloud.entities.response.ProfileUpdateRes;
import number.android.wdvendor.cloud.rest.Callbacks;
import number.android.wdvendor.cloud.rest.Client;
import number.android.wdvendor.utilities.Icons;
import number.android.wdvendor.utilities.Session;
import number.android.wdvendor.utilities.WDMaterialDrawer;
import retrofit2.Call;
import retrofit2.Response;

public class PreferencesActivity extends AppCompatActivity {

    ListView preferences;
    PreferencesListAdapter preferencesListAdapter;

    ArrayList<Preference> preferenceList = new ArrayList<Preference>();

    Icons icons;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        icons = new Icons(this);
        new Session(this).start();
        initView();
    }

    private void initView(){

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Settings");
        setSupportActionBar(toolbar);

        WDMaterialDrawer wdMaterialDrawer = new WDMaterialDrawer(this,toolbar);

        Preference edit_profile = new Preference(1,icons.personIcon(),"Profile address change request");
        preferenceList.add(edit_profile);

//        Preference deactivate_account = new Preference(2,icons.trashIcon(), "Deactivate account");
//        preferenceList.add(deactivate_account);

        preferences = (ListView) findViewById(R.id.preferences);

        preferencesListAdapter = new PreferencesListAdapter(this,preferenceList);
        preferences.setAdapter(preferencesListAdapter);
        preferences.setOnItemClickListener(new OnPreferenceClickListener());
    }

    class OnPreferenceClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Preference preference = preferencesListAdapter.getItem(position);
            toScreen(preference.getId());
        }
    }

    void toScreen(int id){
        switch (id) {
            case 1:
                updateProfile();
                break;
            case 3:
                Intent shareVia=new Intent(Intent.ACTION_SEND);
                shareVia.setType("text/plain");
                shareVia.putExtra(Intent.EXTRA_SUBJECT,"Subject test");
                shareVia.putExtra(Intent.EXTRA_TEXT, "extra text that you want to put");
                startActivity(Intent.createChooser(shareVia,"Share via"));
                break;
        }
    }

    void updateProfile(){
        ProfileUpdateReq profileUpdateReq = new ProfileUpdateReq();
        profileUpdateReq.setVendor_id(UserSession.getVendor_id());

        Client client = new Client();
        Call<ProfileUpdateRes> call = client.get().ProfileUpdate(profileUpdateReq);
        call.enqueue(new Callbacks<ProfileUpdateRes>() {
            @Override
            public void onResponse(Call<ProfileUpdateRes> call, Response<ProfileUpdateRes> profileUpdateResResponse) {
                processResponse(profileUpdateResResponse.body().getMessage());
            }
        });
    }


    void processResponse(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}
