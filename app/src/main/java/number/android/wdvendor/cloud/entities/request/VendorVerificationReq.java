
package number.android.wdvendor.cloud.entities.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class VendorVerificationReq {

    @SerializedName("mobile_number")
    @Expose
    public String mobile_number;

    @SerializedName("verification_code")
    @Expose
    public String verificationCode;
    @SerializedName("device_token")
    @Expose
    public String deviceToken;
    @SerializedName("device_os")
    @Expose
    public String deviceOs;
    @SerializedName("device_os_version")
    @Expose
    public String deviceOsVersion;

    /**
     * No args constructor for use in serialization
     *
     */
    public VendorVerificationReq() {
    }

    /**
     *
     * @param verificationCode
     * @param deviceOsVersion
     * @param deviceToken
     * @param deviceOs
     */
    public VendorVerificationReq(String verificationCode, String deviceToken, String deviceOs, String deviceOsVersion, String mobile_number) {
        this.verificationCode = verificationCode;
        this.deviceToken = deviceToken;
        this.deviceOs = deviceOs;
        this.deviceOsVersion = deviceOsVersion;
        this.mobile_number = mobile_number;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getDeviceOs() {
        return deviceOs;
    }

    public void setDeviceOs(String deviceOs) {
        this.deviceOs = deviceOs;
    }

    public String getDeviceOsVersion() {
        return deviceOsVersion;
    }

    public void setDeviceOsVersion(String deviceOsVersion) {
        this.deviceOsVersion = deviceOsVersion;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}