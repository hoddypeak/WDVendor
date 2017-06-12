package number.android.wdvendor.cloud.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Created by user on 11/21/2016.
 */

public class CustomerAddress implements Serializable {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("landmark")
    @Expose
    public String landmark;
    @SerializedName("street")
    @Expose
    public String street;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("postcode")
    @Expose
    public String postcode;

    /**
     * No args constructor for use in serialization
     */
    public CustomerAddress() {
    }

    /**
     * @param id
     * @param landmark
     * @param address
     * @param street
     * @param state
     * @param postcode
     * @param country
     * @param city
     */
    public CustomerAddress(Integer id, String address, String landmark, String street, String city, String state, String country, String postcode) {
        this.id = id;
        this.address = address;
        this.landmark = landmark;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postcode = postcode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
