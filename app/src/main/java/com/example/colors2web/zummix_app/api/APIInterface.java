package com.example.colors2web.zummix_app.api;


import com.example.colors2web.zummix_app.POJO.CityBins.CityBins;
import com.example.colors2web.zummix_app.POJO.CityBins.CityBinsResponse;
import com.example.colors2web.zummix_app.POJO.CusGroupDetails.CustomerGroup;
import com.example.colors2web.zummix_app.POJO.OrderEdit.OrderCancel;
import com.example.colors2web.zummix_app.POJO.OrderEdit.OrderEditPut;
import com.example.colors2web.zummix_app.POJO.OrderEdit.OrderEditResponse;
import com.example.colors2web.zummix_app.POJO.OrderShippingAddressLogs.ShippingLogResponse;
import com.example.colors2web.zummix_app.POJO.PostSearch.PostResponse;
import com.example.colors2web.zummix_app.POJO.PostSearch.PostServer;
import com.example.colors2web.zummix_app.POJO.login.Login;
import com.example.colors2web.zummix_app.POJO.MasterBoxSearch.MasterBoxResponse;
import com.example.colors2web.zummix_app.POJO.Order2POJO.Order2Response;
import com.example.colors2web.zummix_app.POJO.OrderByCus.OrdGrpByCus;
import com.example.colors2web.zummix_app.POJO.OrderSearch.OrderSearchResponse;
import com.example.colors2web.zummix_app.POJO.OrderTrack.OrdertrackResponse;
import com.example.colors2web.zummix_app.POJO.ProductSearch.ProSearchRes;
import com.example.colors2web.zummix_app.POJO.login.ResponseLogin;
import com.example.colors2web.zummix_app.POJO.customers.CustomerResponse;
import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface APIInterface {

    //Login Authentication
    @POST("auth/login")
    Call<ResponseLogin> dologin(@Body Login login);

    //GropuByCustomer//homepage
    @GET("orders/groupedByCustomer")
    Call<OrdGrpByCus> getOrdersbyGroup(@Header("email") String email, @Header("Password") String password);

    //  CustomerDetails //hompage =>details ma click garda
    @GET("orders/customer/{order_number}")
    Call<CustomerGroup> getCustomerDetails(@Header("email") String email, @Header("Password") String password, @Path("order_number") String order_number);

    //Product search Dymanic way
    @GET("customerItems/search/{variable}")
    Call<ProSearchRes> getProduct1(@Header("email") String email, @Header("Password") String password, @Path("variable") String variable);


    //    OrderSearch this calls to ordersearch 2 //Zimdansh1
    @GET("orders/orderNumber/{order_number}")
    Call<OrderSearchResponse> getOrder1(@Header("email") String email, @Header("Password") String password, @Path("order_number") String order_number);

    //    To cancel the Order//order_number not order_id
    @PUT("orders/markOrderCancelled/{order_number}")
    Call<OrderCancel> putCancel(@Header("email") String email, @Header("Password") String password, @Path("order_number") String order_number, @Body OrderCancel editPut);

    //    to edit the order_shipping_address:
    @PUT("orders/editOrderShippingAddress/{order_number}")
    Call<OrderEditResponse> putEditOrder(@Header("email") String email, @Header("Password") String password, @Path("order_number") String order_number, @Body OrderEditPut editPut);

    //    Receiving The OrderSippingAddressLOgs
    @GET("orders/viewOrderShippingAddressLogs/{order_id}")
    Call<ShippingLogResponse> getOrderShippingLogs(@Header("email") String email, @Header("Password") String password, @Path("order_id") String order_id);


    //trace id from order search and send that id as variable here
//    orderSearch2
    @GET("orders/{order_number}")
    Call<JsonElement> getsecsearch(@Header("email") String email, @Header("Password") String password, @Path("order_number") Long order_number);

    // tracking number search api
    @GET("orders/searchOrderByTrackingNumber/{order_number}")
    Call<OrdertrackResponse> getOrderByTrack(@Header("email") String email, @Header("Password") String password, @Path("order_number") String order_number);

    // tracking number merge on dropdown search api
    @GET("orders/searchOrderByTrackingNumber/{order_number}")
    Call<Order2Response> getOrderByTrackingNumber(@Header("email") String email, @Header("Password") String password, @Path("order_number") String order_number);

    // box search api =>
    @GET("boxes/masterBoxes/{order_number}")
    Call<MasterBoxResponse> getMasterBoxes(@Header("email") String email, @Header("Password") String password, @Path("order_number") String order_number);

    // customers search by customer_id =>
    @GET("customers/{customer_id}")
    Call<CustomerResponse> getCustomer(@Header("email") String email, @Header("Password") String password, @Path("customer_id") String customer_id);

    // customers search by parent Customer Id =>
    @GET("customers/parentCustomer/{parent_id}")
    Call<CustomerResponse> getParentCustomer(@Header("email") String email, @Header("Password") String password, @Path("parent_id") String parent_id);

    // Activecustomers search  =>
    @GET("customers/activeCustomers")
    Call<CustomerResponse> getActiveCustomers(@Header("email") String email, @Header("Password") String password);

    // customers Itemssearch by customer_id =>
    @GET("customerItems/customer/{customer_id}")
    Call<CustomerResponse> getCustomerItems(@Header("email") String email, @Header("Password") String password, @Path("customer_id") String customer_id);

    //drShipments/searchItemDrHistory
    @POST("drShipments/searchItemDrHistory")
    Call<PostResponse> postItemDrHistory(@Header("email") String email, @Header("Password") String password, @Body PostServer itemdr);

    // report/getPickVelocityAndInventory
    @POST("report/getPickVelocityAndInventory")
    Call<PostResponse> postPickVelocityReport(@Header("email") String email, @Header("Password") String password, @Body PostServer itemdr);


    //orders/searchItemSalesHistory
    @POST("orders/searchItemSalesHistory")
    Call<PostResponse> postItemSalesHistory(@Header("email") String email, @Header("Password") String password, @Body PostServer itemdr);

    //    CityBins API
//    Get all customers
    @GET("cityBins/groupedByCustomer/all")
    Call<CityBinsResponse> getBinCustomers(@Header("email") String email, @Header("Password") String password);

    //    Retriving bins of individual customer
//    cityBins/boxCreate/customer/3
    @GET("cityBins/boxCreate/customer/{customer_id}")
    Call<CityBinsResponse> getBins(@Header("email") String email, @Header("Password") String password, @Path("customer_id") String cus_id);

//    update city bin api
    @PUT("cityBins/{id}")
    Call<CityBinsResponse>putCitybin(@Header("email") String email, @Header("Password") String password, @Path("id") String id, @Body CityBins bins);

    //    Create City Bins Either from Inside or Outside
    @POST("cityBins")
    Call<CityBinsResponse>CreateCitybin(@Header("email") String email, @Header("Password") String password, @Body CityBins bins);

}

//
//    @GET("/api/users?")
//    Call<UserList> doGetUserList(@Query("page") String page);// send in network as https://reqres.in/api/users?&page=2
//

//
//    @POST("/api/users")
//    Call<User> createUser(@Body User user);
//
//    @FormUrlEncoded
//    @POST("/api/users?")
//    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);
//
//    @PUT("/api/users/{id}")
//    Call<Patch> doPutUser(@Path("id") String id, @Body Patch userput);
//
//    @PATCH("/api/users/{id}")
//    Call<Patch> doPatchUser(@Path("id") String id, @Body Patch userput);
//
//    @DELETE("/api/users/{id}")
//    Call<Patch>delete(@Path("id") String id);

//For using query
// @GET("/data/2.5/weather?")
//    Call<City> getWeather(@Query("q") String location, @Query("appid") String key);
