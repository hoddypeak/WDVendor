package number.android.wdvendor.adapters.entities;

import java.io.Serializable;

import number.android.wdvendor.cloud.entities.CustomerAddress;

/***
 * Created by user on 9/4/2016.
 ***/
public class Customer implements Serializable {
    private int id;
    private int request_id;
    private String name;
    private String mobile_number;
    private int status;
    private CustomerAddress customerAddress;

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public CustomerAddress getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(CustomerAddress customerAddress) {
        this.customerAddress = customerAddress;
    }
}
