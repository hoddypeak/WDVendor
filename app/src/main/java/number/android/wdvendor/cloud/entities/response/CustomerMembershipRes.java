package number.android.wdvendor.cloud.entities.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CustomerMembershipRes {

    @SerializedName("message")
    @Expose
    public String message;

    /**
     * No args constructor for use in serialization
     *
     */
    public CustomerMembershipRes() {
    }

    /**
     *
     * @param message
     */
    public CustomerMembershipRes(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}