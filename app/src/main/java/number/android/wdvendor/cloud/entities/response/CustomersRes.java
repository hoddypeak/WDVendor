
package number.android.wdvendor.cloud.entities.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

import number.android.wdvendor.cloud.entities.Customer;


public class CustomersRes {

    @SerializedName("customer_list")
    @Expose
    public List<CustomerList> customerList = new ArrayList<CustomerList>();


    /**
     * No args constructor for use in serialization
     */
    public CustomersRes() {
    }

    /**
     * @param customerList
     */
    public CustomersRes(List<CustomerList> customerList) {
        this.customerList = customerList;
    }

    public List<CustomerList> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<CustomerList> customerList) {
        this.customerList = customerList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


    public static class CustomerList {

        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("vendor_id")
        @Expose
        public Integer vendorId;
        @SerializedName("customer_pid")
        @Expose
        public Integer customerPid;
        @SerializedName("status")
        @Expose
        public Integer status;
        @SerializedName("customer")
        @Expose
        public Customer customer;

        /**
         * No args constructor for use in serialization
         */
        public CustomerList() {
        }

        /**
         * @param id
         * @param customerPid
         * @param status
         * @param customer
         * @param vendorId
         */
        public CustomerList(Integer id, Integer vendorId, Integer customerPid, Integer status, Customer customer) {
            this.id = id;
            this.vendorId = vendorId;
            this.customerPid = customerPid;
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

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
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
