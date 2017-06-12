package number.android.wdvendor.cloud.entities.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by user on 11/14/2016.
 */

public class VendorVerificationRes {

    @SerializedName("vendor_id")
    @Expose
    public Integer vendorId;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("mobile")
    @Expose
    public String mobile;

    /**
     * No args constructor for use in serialization
     *
     */
    public VendorVerificationRes() {
    }

    /**
     *
     * @param address
     * @param name
     * @param vendorId
     * @param mobile
     */
    public VendorVerificationRes(Integer vendorId, String name, String address, String mobile) {
        this.vendorId = vendorId;
        this.name = name;
        this.address = address;
        this.mobile = mobile;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
