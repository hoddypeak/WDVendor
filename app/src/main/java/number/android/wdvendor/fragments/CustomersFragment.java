package number.android.wdvendor.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import number.android.wdvendor.R;
import number.android.wdvendor.activities.CustomerSummaryActivity;
import number.android.wdvendor.activities.VendorKeyActivity;
import number.android.wdvendor.activities.entities.UserSession;
import number.android.wdvendor.adapters.CustomersListAdapter;
import number.android.wdvendor.adapters.OpenOrdersListAdapter;
import number.android.wdvendor.adapters.entities.Customer;
import number.android.wdvendor.adapters.entities.Order;
import number.android.wdvendor.cloud.entities.request.CustomersReq;
import number.android.wdvendor.cloud.entities.response.CustomersRes;
import number.android.wdvendor.cloud.rest.Callbacks;
import number.android.wdvendor.cloud.rest.Client;
import number.android.wdvendor.utilities.WDProgressDialog;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Delivered orders list
 */
public class CustomersFragment extends Fragment {

    View view;
    ListView customers;

    ArrayList<Customer> customer = new ArrayList<>();

    CustomersListAdapter customersListAdapter;

    Client client;

    View emptyView;
    WDProgressDialog wdProgressDialog;

    public CustomersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_customers, container, false);

        wdProgressDialog = new WDProgressDialog(getActivity());
        initView();

        return view;
    }

    @Override
    public void onDestroyView() {
//        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    public void initView(){
        customers = (ListView) view.findViewById(R.id.customers);
        emptyView = view.findViewById(android.R.id.empty);
        customers.setEmptyView(emptyView);
        bindAdapter();
    }

    void onFragmentReloadEvent(){
        Toast.makeText(getActivity(),"event",Toast.LENGTH_LONG);
    }

    public void bindAdapter(){
        loadCustomersFromCloud();

        customersListAdapter = new CustomersListAdapter(getActivity(), customer);
        customers.setAdapter(customersListAdapter);

        customers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Customer customer_ = customer.get(position);
                toCustomerScreen(customer_);
            }
        });

    }

    void toCustomerScreen(Customer customer){
        Intent intent = new Intent(getActivity(),CustomerSummaryActivity.class);
        intent.putExtra("customer",customer);
        startActivity(intent);
    }

    public void loadCustomersFromCloud(){

        wdProgressDialog.setMessage("Loading...");
        wdProgressDialog.show();

        CustomersReq customersReq = new CustomersReq();
        customersReq.setVendor_id(UserSession.getVendor_id());
//      customersReq.setLastUpdatedDatetime("");

        client = new Client();
        Call<CustomersRes> call = client.get().Customers(customersReq);
        call.enqueue(new Callbacks<CustomersRes>() {
            @Override
            public void onResponse(Call<CustomersRes> call, Response<CustomersRes> customersRes) {
                processResponse(customersRes.body());
                wdProgressDialog.dismissWdProgressDialog();
            }

            @Override
            public void onFailure(Call<CustomersRes> call, Throwable t) {
                wdProgressDialog.dismissWdProgressDialog();
            }
        });
    }

    /**
     * Process the response data from customers list WS
     *
     * @param customersRes
     */
    void processResponse(CustomersRes customersRes){
        for (CustomersRes.CustomerList customers : customersRes.getCustomerList()) {
            Customer customer_ = new Customer();
            customer_.setId(customers.getCustomerPid());
            customer_.setRequest_id(customers.getId());
            customer_.setName(customers.getCustomer().getName());
            customer_.setMobile_number(customers.getCustomer().getMobileNumber());
            customer_.setStatus(customers.getStatus());
            customer_.setCustomerAddress(customers.getCustomer().getCustomerAddress());
            customer.add(customer_);
        }
        // update customers list
        customersListAdapter.notifyDataSetChanged();
    }
}
