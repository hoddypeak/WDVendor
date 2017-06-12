
package number.android.wdvendor.cloud.entities.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class OrdersReq {

    @SerializedName("vendor_id")
    @Expose
    public int vendor_id;

    @SerializedName("month")
    @Expose
    public int month;

    @SerializedName("year")
    @Expose
    public int year;


    /**
     * No args constructor for use in serialization
     *
     */
    public OrdersReq() {
    }

    public int getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(int vendor_id) {
        this.vendor_id = vendor_id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


}