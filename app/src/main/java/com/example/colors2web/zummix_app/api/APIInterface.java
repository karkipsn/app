package com.example.colors2web.zummix_app.api;


import com.example.colors2web.zummix_app.POJO.CusGroupDetails.CustomerGroup;
import com.example.colors2web.zummix_app.POJO.Login;
import com.example.colors2web.zummix_app.POJO.MasterBoxResponse;
import com.example.colors2web.zummix_app.POJO.Order2POJO.Order2Response;
import com.example.colors2web.zummix_app.POJO.OrderByCus.OrdGrpByCus;
import com.example.colors2web.zummix_app.POJO.OrderSearchResponse;
import com.example.colors2web.zummix_app.POJO.OrdertrackResponse;
import com.example.colors2web.zummix_app.POJO.ProSearchRes;
import com.example.colors2web.zummix_app.POJO.ResponseLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface APIInterface {

    //Login Authentication
    @POST("auth/login")
    Call<ResponseLogin> dologin(@Body Login login);


    //GropuByCustomer
    @GET("orders/groupedByCustomer")
    Call<OrdGrpByCus> getOrdersbyGroup(@Header("email") String email, @Header("Password") String password);

    //  CustomerDetails
    @GET("orders/customer/{order_number}")
    Call<CustomerGroup> getCustomerDetails(@Header("email") String email, @Header("Password") String password, @Path("order_number") String order_number);

    // Product search
    @GET("customerItems/search/ZUMX262543")
    Call<ProSearchRes> getProduct(@Header("email") String email, @Header("Password") String password);

    //Product search Dymanic way
    @GET("customerItems/search/{variable}")
    Call<ProSearchRes> getProduct1(@Header("email") String email, @Header("Password") String password, @Path("variable") String variable);

    //Order Search
    @GET("orders/orderNumber/Zimdansh1")
    Call<OrderSearchResponse> getOrder(@Header("email") String email, @Header("Password") String password);

    //trace id from order search and send that id as variable here
    @GET("orders/{order_number}")
    Call<Order2Response> getsecsearch(@Header("email") String email, @Header("Password") String password, @Path("order_number") Long order_number);


    @GET("orders/orderNumber/{order_number}")
    Call<OrderSearchResponse> getOrder1(@Header("email") String email, @Header("Password") String password, @Path("order_number") String order_number);

    // tracking number search api
    @GET("orders/searchOrderByTrackingNumber/{order_number}")
    Call<OrdertrackResponse> getOrderByTrack(@Header("email") String email, @Header("Password") String password, @Path("order_number") String order_number);

    // tracking number merge on dropdown search api
    @GET("orders/searchOrderByTrackingNumber/{order_number}")
    Call<Order2Response> getOrderByTrackingNumber(@Header("email") String email, @Header("Password") String password, @Path("order_number") String order_number);



    // box search api =>
    @GET("boxes/masterBoxes/{order_number}")
    Call<MasterBoxResponse> getMasterBoxes(@Header("email") String email, @Header("Password") String password, @Path("order_number") String order_number);

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
