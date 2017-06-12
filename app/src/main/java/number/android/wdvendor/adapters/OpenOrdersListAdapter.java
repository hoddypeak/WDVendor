package number.android.wdvendor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import number.android.wdvendor.R;
import number.android.wdvendor.adapters.entities.Order;

/**
 * Created by user on 8/25/2016.
 */
public class OpenOrdersListAdapter extends BaseAdapter {

    Context context;
    List<Order> orders;

    public OpenOrdersListAdapter(Context context, List<Order> orders) {
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

    public void remove(int position) {
        orders.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.open_orders_list_item, null);
        }

        ViewHolder viewHolder = new ViewHolder(view);

        Order openOrder = getItem(position);

        String quantity = String.format("%d", openOrder.getQuantity());
        String landmark_address = String.format("%s - %s", openOrder.getCustomer(),openOrder.getCustomer_address());
        viewHolder.quantity.setText(quantity);
        viewHolder.landmark.setText(openOrder.getCustomer_landmark());
        viewHolder.landmark_address.setText(landmark_address);
        viewHolder.date.setText(openOrder.getDate());

        return view;
    }

    private class ViewHolder {

        TextView quantity,landmark,landmark_address,date;

        ViewHolder(View row) {
            this.quantity = (TextView) row.findViewById(R.id.quantity);
            this.landmark = (TextView) row.findViewById(R.id.landmark);
            this.landmark_address = (TextView) row.findViewById(R.id.landmark_address);
            this.date = (TextView) row.findViewById(R.id.date);
        }
    }
}
