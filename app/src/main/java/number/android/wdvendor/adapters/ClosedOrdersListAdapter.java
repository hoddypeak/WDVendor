package number.android.wdvendor.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import number.android.wdvendor.R;
import number.android.wdvendor.adapters.entities.Order;

/**
 * Created by user on 8/25/2016.
 */
public class ClosedOrdersListAdapter extends BaseAdapter {

    Context context;
    ArrayList<Order> orders;

    public ClosedOrdersListAdapter(Context context, ArrayList<Order> orders) {
        this.context = context;
        this.orders = orders;
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Order getItem(int position) {
        return orders.get(position);
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
            view = layoutInflater.inflate(R.layout.closed_orders_list_item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Order deliveredOrder = getItem(position);

        String quantity = String.format("%d", deliveredOrder.getQuantity());
        String landmark_address = String.format("%s - %s", deliveredOrder.getCustomer(),deliveredOrder.getCustomer_address());
        viewHolder.quantity.setText(quantity);
        viewHolder.landmark.setText(deliveredOrder.getCustomer_landmark());
        viewHolder.landmark_address.setText(landmark_address);
        viewHolder.date.setText(deliveredOrder.getDate());
        switch (deliveredOrder.getStatus()){
            case 2:
                viewHolder.status.setText("Delivered");
                viewHolder.status.setTextColor(ContextCompat.getColor(context,R.color.colorPrimary));
                break;
            case 3:
                viewHolder.status.setText("Confirmed");
                viewHolder.status.setTextColor(ContextCompat.getColor(context,R.color.green));
                break;
            case 4:
                viewHolder.status.setText("Rejected");
                viewHolder.status.setTextColor(ContextCompat.getColor(context,R.color.red));
                break;
        }

        return view;
    }

    private class ViewHolder {

        TextView quantity,landmark,landmark_address,date,status;

        ViewHolder(View row) {
            this.quantity = (TextView) row.findViewById(R.id.quantity);
            this.landmark = (TextView) row.findViewById(R.id.landmark);
            this.landmark_address = (TextView) row.findViewById(R.id.landmark_address);
            this.date = (TextView) row.findViewById(R.id.date);
            this.status = (TextView) row.findViewById(R.id.status);
        }
    }
}
