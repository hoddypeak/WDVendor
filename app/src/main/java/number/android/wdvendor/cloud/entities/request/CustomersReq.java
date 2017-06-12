
package number.android.wdvendor.cloud.entities.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CustomersReq {

    @SerializedName("vendor_id")
    @Expose
    public int vendor_id;

    /**
     * No args constructor for use in serialization
     *
     */
    public CustomersReq() {
    }

    /**
     *
     * @param vendor_id
     */
    public CustomersReq(int vendor_id) {
        this.vendor_id = vendor_id;
    }

    public int getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(int vendor_id) {
        this.vendor_id = vendor_id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}