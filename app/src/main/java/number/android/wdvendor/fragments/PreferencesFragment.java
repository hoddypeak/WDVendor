package number.android.wdvendor.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import number.android.wdvendor.R;
import number.android.wdvendor.activities.entities.UserSession;
import number.android.wdvendor.adapters.PreferencesListAdapter;
import number.android.wdvendor.adapters.entities.Preference;
import number.android.wdvendor.cloud.entities.request.OrdersReq;
import number.android.wdvendor.cloud.entities.request.ProfileUpdateReq;
import number.android.wdvendor.cloud.entities.response.OrdersRes;
import number.android.wdvendor.cloud.entities.response.ProfileUpdateRes;
import number.android.wdvendor.cloud.rest.Callbacks;
import number.android.wdvendor.cloud.rest.Client;
import number.android.wdvendor.utilities.Icons;
import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreferencesFragment extends Fragment {

    ListView preferences;
    PreferencesListAdapter preferencesListAdapter;

    ArrayList<Preference> preferenceList = new ArrayList<Preference>();

    View view;
    Icons icons;



    public PreferencesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_preferences, container, false);
        icons = new Icons(getActivity());
        initView(view);
        return view;
    }

    private void initView(View view){

        Preference edit_profile = new Preference(1,icons.personIcon(),"Profile address change request");
        preferenceList.add(edit_profile);

//        Preference deactivate_account = new Preference(2,icons.trashIcon(), "Deactivate account");
//        preferenceList.add(deactivate_account);

        preferences = (ListView) view.findViewById(R.id.preferences);

        preferencesListAdapter = new PreferencesListAdapter(getActivity(),preferenceList);
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
                processResponse(profileUpdateResResponse.message());
            }
        });
    }


    void processResponse(String message){
        Toast.makeText(getActivity(),message,Toast.LENGTH_LONG);
    }





}
