package number.android.wdvendor.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import number.android.wdvendor.R;
import number.android.wdvendor.activities.entities.UserSession;
import number.android.wdvendor.adapters.entities.Customer;
import number.android.wdvendor.adapters.entities.Order;
import number.android.wdvendor.cloud.entities.request.CustomerMembershipReq;
import number.android.wdvendor.cloud.entities.response.CustomerMembershipRes;
import number.android.wdvendor.cloud.entities.response.CustomersRes;
import number.android.wdvendor.cloud.rest.Callbacks;
import number.android.wdvendor.cloud.rest.Client;
import number.android.wdvendor.utilities.Icons;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by user on 8/25/2016.
 */
public class CustomersListAdapter extends BaseAdapter {

    Context context;
    ArrayList<Customer> customers;
    Icons icons;

    public CustomersListAdapter(Context context, ArrayList<Customer> customers) {
        this.context = context;
        this.customers = customers;
        icons = new Icons(context);
    }

    @Override
    public int getCount() {
        return customers.size();
    }

    @Override
    public Customer getItem(int position) {
        return customers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;
        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate( R.layout.customers_list_item , null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Customer customer = getItem(position);

        viewHolder.name.setText(customer.getName());
        viewHolder.mobile_number.setText(customer.getMobile_number());

        int status = customer.getStatus();
        if(status == 1) {
            viewHolder.add_customer.setImageDrawable(icons.customerAddIcon(Color.parseColor(Icons.primaryColor)));
            viewHolder.reject_customer.setImageDrawable(icons.customerRejectIcon(Color.parseColor(Icons.primaryColor)));
            viewHolder.add_customer.setVisibility(View.VISIBLE);
            viewHolder.reject_customer.setVisibility(View.VISIBLE);
            viewHolder.status.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.add_customer.setVisibility(View.INVISIBLE);
            viewHolder.reject_customer.setVisibility(View.INVISIBLE);
            viewHolder.status.setVisibility(View.VISIBLE);
            if(customer.getStatus() == 2){
                viewHolder.status.setText("Active");
            } else {
                viewHolder.status.setText("Rejected");
            }
        }

        viewHolder.add_customer.setOnClickListener(new OnAddCustomerClick(position));
        viewHolder.reject_customer.setOnClickListener(new OnRejectCustomerClick(position));

        return view;
    }

    public class OnAddCustomerClick implements View.OnClickListener {

        int position;
        public OnAddCustomerClick(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            Customer customer = getItem(position);
            updateMembershipStatus(customer.getRequest_id(),position,2);
        }
    }

    public class OnRejectCustomerClick implements View.OnClickListener {

        int position;
        public OnRejectCustomerClick(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            Customer customer = getItem(position);
            updateMembershipStatus(customer.getRequest_id(),position,3);
        }
    }

    private class ViewHolder {

        TextView name,mobile_number,status;
        ImageButton add_customer,reject_customer;

        ViewHolder(View row) {
            this.name = (TextView) row.findViewById(R.id.name);
            this.mobile_number = (TextView) row.findViewById(R.id.mobile_number);
            this.status = (TextView) row.findViewById(R.id.status);
            this.add_customer = (ImageButton) row.findViewById(R.id.add_customer);
            this.reject_customer = (ImageButton) row.findViewById(R.id.reject_customer);
        }
    }

    public void updateMembershipStatus(int request_id, final int position,final int status){
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
                    processResponse(customerMembershipResResponse.body().getMessage(),position,status);
                } else {
                    Toast.makeText(context, "Some thing went wrong", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    void processResponse(String message,int position,int status){
        if(status == 2) {
            customers.get(position).setStatus(status);
        } else if (status == 3) {
            customers.remove(position);
        }
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        notifyDataSetChanged();
    }


}
