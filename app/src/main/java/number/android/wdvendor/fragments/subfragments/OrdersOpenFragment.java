package number.android.wdvendor.fragments.subfragments;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.hudomju.swipe.SwipeToDismissTouchListener;
import com.hudomju.swipe.adapter.ListViewAdapter;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import number.android.wdvendor.R;
import number.android.wdvendor.activities.entities.UserSession;
import number.android.wdvendor.adapters.OpenOrdersListAdapter;
import number.android.wdvendor.adapters.entities.Order;
import number.android.wdvendor.cloud.entities.request.OrdersReq;
import number.android.wdvendor.cloud.entities.request.UpdateOrderStatusReq;
import number.android.wdvendor.cloud.entities.response.OrdersRes;
import number.android.wdvendor.cloud.entities.response.UpdateOrderStatusRes;
import number.android.wdvendor.cloud.rest.Callbacks;
import number.android.wdvendor.cloud.rest.Client;
import number.android.wdvendor.utilities.DateTime;
import number.android.wdvendor.utilities.Icons;
import number.android.wdvendor.utilities.WDProgressDialog;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Delivered orders list
 */
public class OrdersOpenFragment extends Fragment {

    private static final int TIME_TO_AUTOMATICALLY_DISMISS_ITEM = 1500;
    View view;
    ListView open_orders;
    List<Order> openOrders = new ArrayList<>();
    OpenOrdersListAdapter openOrdersListAdapter;
    View emptyView;
    //WaveSwipeRefreshLayout mWSRefresh;
    Icons icons;

    WDProgressDialog wdProgressDialog;

    int month;
    int year;

    public OrdersOpenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.fragment_orders_open, container, false);
        wdProgressDialog = new WDProgressDialog(getActivity());
        icons = new Icons(getActivity());

        // set current month and year
        month = Calendar.getInstance().get(Calendar.MONTH);
        year = Calendar.getInstance().get(Calendar.YEAR);

        return view;
    }

    @Override
    public void onResume() {
        initView();
        super.onResume();
    }

    public void initView(){


        open_orders = (ListView) view.findViewById(R.id.open_orders);
        emptyView = view.findViewById(android.R.id.empty);
        open_orders.setEmptyView(emptyView);

//        mWSRefresh = (WaveSwipeRefreshLayout) view.findViewById(R.id.refresh);
//        mWSRefresh.setWaveColor(ContextCompat.getColor(getActivity(),R.color.colorPrimary));
//        mWSRefresh.setColorSchemeColors(ContextCompat.getColor(getActivity(),R.color.white));
//        mWSRefresh.setShadowRadius(2);
        bindAdapter();

        setWaveSwipeRefreshLayout();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.activity_water_drop_menu, menu);

        MenuItem refresh = menu.getItem(1);
        refresh.setVisible(true);
        refresh.setIcon(icons.refreshIcon());

        MenuItem month_picker = menu.getItem(0);
        month_picker.setVisible(true);
        month_picker.setIcon(icons.calendarIcon());

        return;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_refresh:
                loadOrdersFromCloud();
                break;
            case R.id.month_picker:
                //showDatePickerDialog();
                showMonthPickerDialog();
                break;
        }
        return true;
    }

    public void bindAdapter(){
        loadOrdersFromCloud();

        openOrdersListAdapter = new OpenOrdersListAdapter(getActivity(), openOrders);
        open_orders.setAdapter(openOrdersListAdapter);

        final SwipeToDismissTouchListener<ListViewAdapter> touchListener =
                new SwipeToDismissTouchListener<>(
                        new ListViewAdapter(open_orders),
                        new SwipeToDismissTouchListener.DismissCallbacks<ListViewAdapter>() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onPendingDismiss(ListViewAdapter recyclerView, int position) {
                            }

                            @Override
                            public void onDismiss(ListViewAdapter view, int position) {
                                // Toast.makeText(getActivity(),"Delivered",Toast.LENGTH_LONG).show();
                                Order order = openOrders.get(position);
                                updateOrderStatus(order.getId(),position);
                                openOrdersListAdapter.remove(position);
                            }
                        });

        touchListener.setDismissDelay(TIME_TO_AUTOMATICALLY_DISMISS_ITEM);

        open_orders.setOnTouchListener(touchListener);
        open_orders.setOnScrollListener((AbsListView.OnScrollListener) touchListener.makeScrollListener());

    }

    public void loadOrdersFromCloud(){

        wdProgressDialog.setMessage("Loading...");
        wdProgressDialog.show();

        OrdersReq ordersReq = new OrdersReq();
        ordersReq.setVendor_id(UserSession.getVendor_id());
        ordersReq.setMonth(month);
        ordersReq.setYear(year);

        Client client = new Client();
        Call<OrdersRes> call = client.get().Orders(ordersReq);
        call.enqueue(new Callbacks<OrdersRes>() {
            @Override
            public void onResponse(Call<OrdersRes> call, Response<OrdersRes> ordersResResponse) {
                processResponse(ordersResResponse.body());
                wdProgressDialog.dismissWdProgressDialog();
            }

            @Override
            public void onFailure(Call<OrdersRes> call, Throwable t) {
                wdProgressDialog.dismissWdProgressDialog();
            }
        });
    }

    /**
     * Process the response data from customers list WS
     *
     * @param ordersRes
     */
    void processResponse(OrdersRes ordersRes){
        openOrders.clear();
        for (OrdersRes.Order order : ordersRes.getOrders()) {
            Order order_ = new Order();
            if(order.getStatus() == 1) {
                order_.setId(order.getId());
                order_.setVendor_id(order.getVendorId());
                if(order.getCustomer() != null){
                    order_.setCustomer(WordUtils.capitalize(order.getCustomer().getName()));
                    order_.setCustomer_address(order.getCustomer().getCustomerAddress().getAddress());
                    order_.setCustomer_landmark(order.getCustomer().getCustomerAddress().getLandmark());
                }
                order_.setQuantity(order.getQuantity());
                order_.setStatus(order.getStatus());
                order_.setDate(DateTime.ServerFormatToCustom(order.getCreated_date(),"d MMM"));
                openOrders.add(order_);
            }
        }

        openOrdersListAdapter.notifyDataSetChanged();

        // update customers list
        //orderHistoryPagesAdapter.updateOrdersOpenFragment(openOrders);
        //orderHistoryPagesAdapter.updateOrdersClosedFragment(closedOrders);
        //orderHistoryPagesAdapter.notifyDataSetChanged();
    }

    public void setWaveSwipeRefreshLayout(){
//        mWSRefresh.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
//            @Override public void onRefresh() {
//                loadOrdersFromCloud();
//            }
//        });
    }

    public void updateOrderStatus(int order_id, final int position){

        UpdateOrderStatusReq updateOrderStatusReq = new UpdateOrderStatusReq();
        updateOrderStatusReq.setVendorId(UserSession.getVendor_id());
        updateOrderStatusReq.setOrderId(order_id);
        updateOrderStatusReq.setStatus(2);

        Client client = new Client();
        Call<UpdateOrderStatusRes> call = client.get().UpdateOrderStatus(updateOrderStatusReq);
        call.enqueue(new Callbacks<UpdateOrderStatusRes>() {
            @Override
            public void onResponse(Call<UpdateOrderStatusRes> call, Response<UpdateOrderStatusRes> orderResResponse) {
                if(orderResResponse.code() == 200) {
                    processResponse(orderResResponse.body().getMessage(),position);
                } else {
                    Toast.makeText(getActivity(), "Some thing went wrong", Toast.LENGTH_LONG).show();
                }
                wdProgressDialog.dismissWdProgressDialog();
            }

            @Override
            public void onFailure(Call<UpdateOrderStatusRes> call, Throwable t) {
                wdProgressDialog.dismissWdProgressDialog();
            }
        });

    }

    void processResponse(String message,int position){
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        //openOrders.get(position).setStatus(2);
        //openOrdersListAdapter.notifyDataSetChanged();
    }

    public void showMonthPickerDialog(){

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_month_picker,null,false);

        final NumberPicker months = (NumberPicker) view.findViewById(R.id.months);
        final String[] monthsString= new String[]{"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
        months.setMinValue(0);
        months.setMaxValue(monthsString.length - 1);
        months.setDisplayedValues(monthsString);
        months.setValue( month - 1);
//        months.setFormatter(new NumberPicker.Formatter() {
//            @Override
//            public String format(int value) {
//                return monthsString[value];
//            }
//        });
        months.setWrapSelectorWheel(true);

        final NumberPicker years = (NumberPicker) view.findViewById(R.id.years);
        years.setMinValue(2017);
        years.setMaxValue(2027);
        months.setValue( year );
        years.setWrapSelectorWheel(false);

        MaterialDialog dialog =  new MaterialDialog.Builder(getActivity()).customView(view,true).title("Filter")
                .positiveText("filter").negativeText("cancel")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {


                        month = months.getValue() +1;
                        year = years.getValue();
                        Toast.makeText(getActivity(),"month/year :"+month+"/"+year,Toast.LENGTH_SHORT).show();
                        loadOrdersFromCloud();
                        // months.getValue();
                    }
                })
                .build();

        dialog.show();
    }

    public void showDatePickerDialog() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
        }
    }





}
