package number.android.wdvendor.cloud.entities.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

import number.android.wdvendor.cloud.entities.Customer;

/**
 * Created by user on 11/21/2016.
 */

public class OrdersRes {

    @SerializedName("orders")
    @Expose
    public List<Order> orders = new ArrayList<Order>();

    /**
     * No args constructor for use in serialization
     *
     */
    public OrdersRes() {
    }

    /**
     *
     * @param orders
     */
    public OrdersRes(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public static class Order {

        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("vendor_id")
        @Expose
        public Integer vendorId;
        @SerializedName("customer_pid")
        @Expose
        public Integer customerPid;
        @SerializedName("quantity")
        @Expose
        public Integer quantity;
        @SerializedName("status")
        @Expose
        public Integer status;
        @SerializedName("created_date")
        @Expose
        public String created_date;
        @SerializedName("updated_date")
        @Expose
        public String updated_date;
        @SerializedName("customer")
        @Expose
        public Customer customer;



        /**
         * No args constructor for use in serialization
         *
         */
        public Order() {
        }

        /**
         *
         * @param id
         * @param vendorId
         * @param customerPid
         * @param quantity
         * @param updated_date
         * @param created_date
         * @param status
         * @param customer
         */
        public Order(Integer id, Integer vendorId, Integer customerPid,Integer quantity,String created_date, String updated_date,Integer status, Customer customer) {
            this.id = id;
            this.vendorId = vendorId;
            this.customerPid = customerPid;
            this.quantity = quantity;
            this.updated_date = updated_date;
            this.created_date = created_date;
            this.status = status;
            this.customer = customer;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getVendorId() {
            return vendorId;
        }

        public void setVendorId(Integer vendorId) {
            this.vendorId = vendorId;
        }

        public Integer getCustomerPid() {
            return customerPid;
        }

        public void setCustomerPid(Integer customerPid) {
            this.customerPid = customerPid;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getCreated_date() {
            return created_date;
        }

        public void setCreated_date(String created_date) {
            this.created_date = created_date;
        }

        public String getUpdated_date() {
            return updated_date;
        }

        public void setUpdated_date(String updated_date) {
            this.updated_date = updated_date;
        }

        public Customer getCustomer() {
            return customer;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }

    }
}
