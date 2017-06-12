
package number.android.wdvendor.cloud.entities.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CustomerMembershipReq {

    @SerializedName("vendor_id")
    @Expose
    private int vendor_id;

    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("request_id")
    @Expose
    private int request_id;

    /**
     * No args constructor for use in serialization
     *
     */
    public CustomerMembershipReq() {
    }


    /**
     *
     * @param vendor_id
     * @param status
     * @param request_id
     */
    public CustomerMembershipReq(int vendor_id, int status, int request_id) {
        this.vendor_id = vendor_id;
        this.status = status;
        this.request_id = request_id;
    }

    public int getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(int vendor_id) {
        this.vendor_id = vendor_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}