package number.android.wdvendor.cloud.rest;


import number.android.wdvendor.cloud.entities.request.CustomerMembershipReq;
import number.android.wdvendor.cloud.entities.request.CustomersReq;
import number.android.wdvendor.cloud.entities.request.OrdersReq;
import number.android.wdvendor.cloud.entities.request.ProfileUpdateReq;
import number.android.wdvendor.cloud.entities.request.UpdateCustomerReq;
import number.android.wdvendor.cloud.entities.request.UpdateOrderStatusReq;
import number.android.wdvendor.cloud.entities.request.VendorVerificationReq;
import number.android.wdvendor.cloud.entities.response.CustomerMembershipRes;
import number.android.wdvendor.cloud.entities.response.CustomersRes;
import number.android.wdvendor.cloud.entities.response.OrdersRes;
import number.android.wdvendor.cloud.entities.response.ProfileUpdateRes;
import number.android.wdvendor.cloud.entities.response.UpdateCustomerRes;
import number.android.wdvendor.cloud.entities.response.UpdateOrderStatusRes;
import number.android.wdvendor.cloud.entities.response.VendorVerificationRes;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Routes {

    @POST("vendor/vendorverification")
    Call<VendorVerificationRes> VerifyVendor(@Body VendorVerificationReq vendorVerificationReq);

    @POST("vendor/vendorcustomers")
    Call<CustomersRes> Customers(@Body CustomersReq customersReq);

    @POST("vendor/customersrequest")
    Call<CustomerMembershipRes> CustomerMembership(@Body CustomerMembershipReq customerMembershipReq);

    @POST("vendor/orders")
    Call<OrdersRes> Orders(@Body OrdersReq ordersReq);

    @POST("vendor/addresschangerequest")
    Call<ProfileUpdateRes> ProfileUpdate(@Body ProfileUpdateReq profileUpdateReq);

    @POST("vendor/updatecustomerdetail")
    Call<UpdateCustomerRes> UpdateCustomerDetails(@Body UpdateCustomerReq updateUserReq);

    @POST("order/orderstatus")
    Call<UpdateOrderStatusRes> UpdateOrderStatus(@Body UpdateOrderStatusReq updateOrderStatusReq);

}
