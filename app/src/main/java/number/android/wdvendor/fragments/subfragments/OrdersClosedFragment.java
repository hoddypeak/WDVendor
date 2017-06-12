package number.android.wdvendor.fragments.subfragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.List;

import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;
import number.android.wdvendor.R;
import number.android.wdvendor.activities.entities.UserSession;
import number.android.wdvendor.adapters.ClosedOrdersListAdapter;
import number.android.wdvendor.adapters.OpenOrdersListAdapter;
import number.android.wdvendor.adapters.entities.Order;
import number.android.wdvendor.cloud.entities.request.OrdersReq;
import number.android.wdvendor.cloud.entities.response.OrdersRes;
import number.android.wdvendor.cloud.rest.Callbacks;
import number.android.wdvendor.cloud.rest.Client;
import number.android.wdvendor.utilities.DateTime;
import number.android.wdvendor.utilities.WDProgressDialog;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Delivered orders list
 */
public class OrdersClosedFragment extends Fragment {

    View view;
    ListView closed_orders;
    ClosedOrdersListAdapter closedOrdersListAdapter;
    ArrayList<Order> closedOrders = new ArrayList<>();

    View emptyView;
    WaveSwipeRefreshLayout mWSRefresh;

    WDProgressDialog wdProgressDialog;

    public OrdersClosedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_orders_closed, container, false);
        wdProgressDialog = new WDProgressDialog(getActivity());
        initView();
        return view;
    }


    public void initView(){
        closed_orders = (ListView) view.findViewById(R.id.closed_orders);
        emptyView = view.findViewById(android.R.id.empty);
        closed_orders.setEmptyView(emptyView);

        mWSRefresh = (WaveSwipeRefreshLayout) view.findViewById(R.id.refresh);
        mWSRefresh.setWaveColor(ContextCompat.getColor(getActivity(),R.color.colorPrimary));
        mWSRefresh.setColorSchemeColors(ContextCompat.getColor(getActivity(),R.color.white));
        mWSRefresh.setShadowRadius(2);
        bindAdapter();
        setWaveSwipeRefreshLayout();
    }

    public void bindAdapter(){

        wdProgressDialog.setMessage("Loading...");
        wdProgressDialog.show();
        loadOrdersFromCloud();

        closedOrdersListAdapter = new ClosedOrdersListAdapter(getActivity(), closedOrders);
        closed_orders.setAdapter(closedOrdersListAdapter);
    }

    public void setWaveSwipeRefreshLayout(){
        mWSRefresh.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override public void onRefresh() {
                loadOrdersFromCloud();
            }
        });
    }


    public void loadOrdersFromCloud(){
        OrdersReq ordersReq = new OrdersReq();
        ordersReq.setVendor_id(UserSession.getVendor_id());

        Client client = new Client();
        Call<OrdersRes> call = client.get().Orders(ordersReq);
        call.enqueue(new Callbacks<OrdersRes>() {
            @Override
            public void onResponse(Call<OrdersRes> call, Response<OrdersRes> ordersResResponse) {
                processResponse(ordersResResponse.body());
                stopRefresh();
                wdProgressDialog.dismissWdProgressDialog();
            }

            @Override
            public void onFailure(Call<OrdersRes> call, Throwable t) {
                stopRefresh();
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
        closedOrders.clear();
        for (OrdersRes.Order order : ordersRes.getOrders()) {
            Order order_ = new Order();
            if(order.getStatus() > 1) {
                order_.setId(order.getId());
                order_.setVendor_id(order.getVendorId());
                order_.setCustomer(WordUtils.capitalize(order.getCustomer().getName()));
                order_.setCustomer_address(order.getCustomer().getCustomerAddress().getAddress());
                order_.setCustomer_landmark(order.getCustomer().getCustomerAddress().getLandmark());
                order_.setQuantity(order.getQuantity());
                order_.setStatus(order.getStatus());
                order_.setDate(DateTime.ServerFormatToCustom(order.getUpdated_date(),"d MMM"));
                closedOrders.add(order_);
            }
        }
        closedOrdersListAdapter.notifyDataSetChanged();

        // update customers list
        //orderHistoryPagesAdapter.updateOrdersOpenFragment(openOrders);
        //orderHistoryPagesAdapter.updateOrdersClosedFragment(closedOrders);
        //orderHistoryPagesAdapter.notifyDataSetChanged();
    }

    public void stopRefresh(){
        if(mWSRefresh.isRefreshing()){
            mWSRefresh.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mWSRefresh.setRefreshing(false);
                    //Toast.makeText(getActivity(),"Done",Toast.LENGTH_SHORT).show();
                }
            },1000);
        }
    }
}
