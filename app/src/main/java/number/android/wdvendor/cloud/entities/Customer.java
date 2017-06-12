package number.android.wdvendor.cloud.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import number.android.wdvendor.cloud.entities.response.CustomersRes;

/**
 * Created by user on 11/21/2016.
 */

public class Customer {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("mobile_number")
    @Expose
    public String mobileNumber;
    @SerializedName("address_id")
    @Expose
    public Integer addressId;
    @SerializedName("customer_address")
    @Expose
    public CustomerAddress customerAddress;

    /**
     * No args constructor for use in serialization
     */
    public Customer() {
    }

    /**
     * @param id
     * @param name
     * @param customerAddress
     * @param mobileNumber
     * @param addressId
     */
    public Customer(Integer id, String name, String mobileNumber, Integer addressId, CustomerAddress customerAddress) {
        this.id = id;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.addressId = addressId;
        this.customerAddress = customerAddress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public CustomerAddress getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(CustomerAddress customerAddress) {
        this.customerAddress = customerAddress;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
