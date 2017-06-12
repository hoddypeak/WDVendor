package number.android.wdvendor.cloud.entities.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UpdateOrderStatusReq {

    @SerializedName("vendor_id")
    @Expose
    public Integer vendorId;
    @SerializedName("order_id")
    @Expose
    public Integer orderId;
    @SerializedName("status")
    @Expose
    public Integer status;

    /**
     * No args constructor for use in serialization
     *
     */
    public UpdateOrderStatusReq() {
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}