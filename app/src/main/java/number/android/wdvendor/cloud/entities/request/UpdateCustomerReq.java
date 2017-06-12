
package number.android.wdvendor.cloud.entities.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UpdateCustomerReq {

    @SerializedName("user")
    @Expose
    public User user;
    @SerializedName("user_location")
    @Expose
    public UserLocation userLocation;

    /**
     * No args constructor for use in serialization
     *
     */
    public UpdateCustomerReq() {
    }

    /**
     *
     * @param userLocation
     * @param user
     */
    public UpdateCustomerReq(User user, UserLocation userLocation) {
        this.user = user;
        this.userLocation = userLocation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserLocation getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(UserLocation userLocation) {
        this.userLocation = userLocation;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


    public static class User {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("id")
        @Expose
        public int id;

        /**
         * No args constructor for use in serialization
         *
         */
        public User() {
        }

        /**
         *
         * @param id
         * @param name
         */
        public User(String name, int id) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class UserLocation {

        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;
        @SerializedName("street")
        @Expose
        private String street;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("postal_code")
        @Expose
        private String postalCode;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("landmark")
        @Expose
        private String landmark;

        /**
         * No args constructor for use in serialization
         */
        public UserLocation() {
        }

        /**
         * @param landmark
         * @param postalCode
         * @param address
         * @param street
         * @param state
         * @param longitude
         * @param latitude
         * @param country
         * @param city
         */
        public UserLocation(String latitude, String longitude, String street, String city, String state, String country, String postalCode, String address, String landmark) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.street = street;
            this.city = city;
            this.state = state;
            this.country = country;
            this.postalCode = postalCode;
            this.address = address;
            this.landmark = landmark;
        }

        /**
         * @return The latitude
         */
        public String getLatitude() {
            return latitude;
        }

        /**
         * @param latitude The latitude
         */
        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        /**
         * @return The longitude
         */
        public String getLongitude() {
            return longitude;
        }

        /**
         * @param longitude The longitude
         */
        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        /**
         * @return The street
         */
        public String getStreet() {
            return street;
        }

        /**
         * @param street The street
         */
        public void setStreet(String street) {
            this.street = street;
        }

        /**
         * @return The city
         */
        public String getCity() {
            return city;
        }

        /**
         * @param city The city
         */
        public void setCity(String city) {
            this.city = city;
        }

        /**
         * @return The state
         */
        public String getState() {
            return state;
        }

        /**
         * @param state The state
         */
        public void setState(String state) {
            this.state = state;
        }

        /**
         * @return The country
         */
        public String getCountry() {
            return country;
        }

        /**
         * @param country The country
         */
        public void setCountry(String country) {
            this.country = country;
        }

        /**
         * @return The postalCode
         */
        public String getPostalCode() {
            return postalCode;
        }

        /**
         * @param postalCode The postal_code
         */
        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        /**
         * @return The address
         */
        public String getAddress() {
            return address;
        }

        /**
         * @param address The address
         */
        public void setAddress(String address) {
            this.address = address;
        }

        /**
         * @return The landmark
         */
        public String getLandmark() {
            return landmark;
        }

        /**
         * @param landmark The landmark
         */
        public void setLandmark(String landmark) {
            this.landmark = landmark;
        }

    }
}