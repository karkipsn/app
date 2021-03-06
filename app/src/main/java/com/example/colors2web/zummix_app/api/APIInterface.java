package com.example.colors2web.zummix_app.api;


import com.example.colors2web.zummix_app.POJO.BatchNumber.BatchResponse;
import com.example.colors2web.zummix_app.POJO.CityBins.CityBins;
import com.example.colors2web.zummix_app.POJO.CityBins.CityBinsResponse;
import com.example.colors2web.zummix_app.POJO.CityBins.CityEditResponse;
import com.example.colors2web.zummix_app.POJO.Cron_Details.CompanyResponse;
import com.example.colors2web.zummix_app.POJO.Cron_Details.CronDetailsResponse;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.CronJobResponse;
import com.example.colors2web.zummix_app.POJO.CusGroupDetails.CustomerGroup;
import com.example.colors2web.zummix_app.POJO.InactiveCustomers.InactiveResponse;
import com.example.colors2web.zummix_app.POJO.OrderEdit.EditExpedite;
import com.example.colors2web.zummix_app.POJO.OrderEdit.EditOrderType;
import com.example.colors2web.zummix_app.POJO.OrderEdit.EditShipMethod;
import com.example.colors2web.zummix_app.POJO.OrderEdit.OrderCancel;
import com.example.colors2web.zummix_app.POJO.OrderEdit.OrderEditPut;
import com.example.colors2web.zummix_app.POJO.OrderEdit.OrderEditResponse;
import com.example.colors2web.zummix_app.POJO.OrderShippingAddressLogs.ShippingLogResponse;
import com.example.colors2web.zummix_app.POJO.PostSearch.PostResponse;
import com.example.colors2web.zummix_app.POJO.PostSearch.PostServer;
import com.example.colors2web.zummix_app.POJO.ProblemSKU.PackageInput;
import com.example.colors2web.zummix_app.POJO.ProblemSKU.ProblemInput;
import com.example.colors2web.zummix_app.POJO.ProblemSKU.ProblemResponse;
import com.example.colors2web.zummix_app.POJO.ProblemSKU.UOM;
import com.example.colors2web.zummix_app.POJO.ProductSearch.ProductDetailsResponse;
import com.example.colors2web.zummix_app.POJO.SpecialProgram.SpecialProgram;
import com.example.colors2web.zummix_app.POJO.SpecialProgram.SpecialProgramResponse;
import com.example.colors2web.zummix_app.POJO.TicketDetails.CommentInput;
import com.example.colors2web.zummix_app.POJO.TicketDetails.MarkInput;
import com.example.colors2web.zummix_app.POJO.TicketDetails.ReplyResponse;
import com.example.colors2web.zummix_app.POJO.TicketDetails.TicketDetialsResponse;
import com.example.colors2web.zummix_app.POJO.TicketOrderDetails.TicketOrderDetailsResponse;
import com.example.colors2web.zummix_app.POJO.Tickets.ReturnTicketResponse;
import com.example.colors2web.zummix_app.POJO.Tickets.TicketResponse;
import com.example.colors2web.zummix_app.POJO.Users.GroupResponse;
import com.example.colors2web.zummix_app.POJO.SpecialProgram.SProgramResponse;
import com.example.colors2web.zummix_app.POJO.Users.UserCreatePOJO;
import com.example.colors2web.zummix_app.POJO.Users.UsersResponse;
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
import retrofit2.http.DELETE;
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

    //Product search Dymanic way//ZUMX262543 //TEST150001
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


    //    editShipMethod
    @PUT("orders/editShipMethod/{order_id}")
    Call<OrderEditResponse> puteditShipMethod(@Header("email") String email, @Header("Password") String password, @Path("order_id") String order_number, @Body EditShipMethod editShipMethod);

    //   markExpedite  //9653
    @PUT("orders/markExpedite/{order_id}")
    Call<OrderEditResponse> putmarkExpedite(@Header("email") String email, @Header("Password") String password, @Path("order_id") String order_number, @Body EditExpedite editExpedite);


    //    editOrderType
    @PUT("orders/editOrderType/{order_id}")
    Call<OrderEditResponse> puteditOrderType(@Header("email") String email, @Header("Password") String password, @Path("order_id") String order_number, @Body EditOrderType editOrderType);


    //trace id from order search and send that id as variable here
    //    orderSearch2
    @GET("orders/{order_number}")
    Call<JsonElement> getsecsearch(@Header("email") String email, @Header("Password") String password, @Path("order_number") String order_number);

    // tracking number search api
    @GET("orders/searchOrderByTrackingNumber/{order_number}")
    Call<OrdertrackResponse> getOrderByTrack(@Header("email") String email, @Header("Password") String password, @Path("order_number") String order_number);

    // tracking number merge on dropdown search api
    @GET("orders/searchOrderByTrackingNumber/{order_number}")
    Call<Order2Response> getOrderByTrackingNumber(@Header("email") String email, @Header("Password") String password, @Path("order_number") String order_number);

    // box or masterbox search api =>
    @GET("boxes/masterBoxes/{order_number}")
    Call<MasterBoxResponse> getMasterBoxes(@Header("email") String email, @Header("Password") String password, @Path("order_number") String order_number);

    // customers search by customer_id =>
    @GET("customers/{customer_id}")
    Call<CustomerResponse> getCustomer(@Header("email") String email, @Header("Password") String password, @Path("customer_id") String customer_id);

    // customers search by parent Customer Id =>Hardcoded for now
    //    @GET("customers/parentCustomer/{parent_id}")
    @GET("customers/parentCustomer/0")
    Call<CustomerResponse> getParentCustomer(@Header("email") String email, @Header("Password") String password);

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


    //  get citybins with bin ind only in case of update
    @GET("cityBins/{id}")
    Call<CityEditResponse> getCitybin(@Header("email") String email, @Header("Password") String password, @Path("id") String id);


    //    update city bin api
    @PUT("cityBins/{id}")
    Call<CityBinsResponse> putCitybin(@Header("email") String email, @Header("Password") String password, @Path("id") String id, @Body CityBins bins);

    //    Create City Bins Either from Inside or Outside
    @POST("cityBins")
    Call<CityBinsResponse> CreateCitybin(@Header("email") String email, @Header("Password") String password, @Body CityBins bins);

    //    ProblenSKU
    @GET("problemSku")
    Call<ProblemResponse> getProblemSKU(@Header("email") String email, @Header("Password") String password);

    //    getUOM
    @GET("unitOfMeasurements")
    Call<ProblemResponse> getUOM(@Header("email") String email, @Header("Password") String password);

    @POST("unitOfMeasurements")
    Call<ProblemResponse> postUOM(@Header("email") String email, @Header("Password") String password, @Body UOM uom);


    //    post for problems
    //    /customerItems/edit/customer/'.$input['customer_id'].'/sku/'.$input['item_sku_number']
    @POST("customerItems/edit/customer/{customerID}/sku/{ItemSku}")
    Call<ProblemResponse> postProblem(@Header("email") String email, @Header("Password") String password, @Path("customerID") String cus_id, @Path("ItemSku") String item_sku, @Body ProblemInput input);

    //    Delete for problemsku
    //problemSku/'.$input['id'],
    @DELETE("problemSku/{id}")
    Call<ProblemResponse> deleteProblemsku(@Header("email") String email, @Header("Password") String password, @Path("id") String id);

    //    Update for problemsku
    @PUT("problemSku/{id}")
    Call<ProblemResponse> updateProblemsku(@Header("email") String email, @Header("Password") String password, @Path("id") String id, @Body ProblemInput input);

    //    Packages
    @GET("packages/customer/0")
    Call<ProblemResponse> getPackages(@Header("email") String email, @Header("Password") String password);

    //    post for packages
    @POST("packages")
    Call<ProblemResponse> postPackage(@Header("email") String email, @Header("Password") String password, @Body PackageInput input);

    //  update for packages
    @PUT("packages/{packageId}")
    Call<ProblemResponse> updatePackage(@Header("email") String email, @Header("Password") String password, @Path("packageId") String packageId, @Body PackageInput input);

    //    Box inside MasterBox
    //boxes/masterBoxes/2465840302/getAllBoxes
    @GET("boxes/masterBoxes/{masterBoxNumber}/getAllBoxes")
    Call<MasterBoxResponse> getMboxBox(@Header("email") String email, @Header("Password") String password, @Path("masterBoxNumber") String mBox);


    //  Items inside  Box inside MasterBox
    // boxes/lineItems/boxNumber/2047023661
    @GET("boxes/lineItems/boxNumber/{boxNumber}")
    Call<MasterBoxResponse> getMboxBoxItems(@Header("email") String email, @Header("Password") String password, @Path("boxNumber") String box);

    //    https://f860f607.ngrok.io/zummix-api/public/orderBatch/allBatchOrders/batchNumber/153596655211
    //  BatchSearch
    @GET("orderBatch/allBatchOrders/batchNumber/{batchnumber}")
    Call<BatchResponse> getBatchResponse(@Header("email") String email, @Header("Password") String password, @Path("batchnumber") String batchnumber);

    //    InactiveItems Search //https://f860f607.ngrok.io/zummix-api/public/customerItems/inactiveItems/customerId/15
    @GET("customerItems/inactiveItems/customerId/{customerid}")
    Call<InactiveResponse> getInactiveFilteredResponse(@Header("email") String email, @Header("Password") String password, @Path("customerid") String customerid);

    // ProductDetails//Address
    // https://40ecef9e.ngrok.io/zummix-api/public/customerItems/searchedCustomerItemsDetail/1334
    @GET("customerItems/searchedCustomerItemsDetail/{itemid}")
    Call<ProductDetailsResponse> getProductitems(@Header("email") String email, @Header("Password") String password, @Path("itemid") String itemid);


    //  drShipmentItemLocations/sku/TEST150001/customer/15
    @GET("drShipmentItemLocations/sku/{sku_id}/customer/{customer_id}")
    Call<ProductDetailsResponse> getProductLocations(@Header("email") String email, @Header("Password") String password, @Path("sku_id") String sku_id, @Path("customer_id") String customer_id);


    // ProductDetails//Logs
    //inventoryLogs/customerId/15/sku/TEST150001/eventType/all
    @GET("inventoryLogs/customerId/{customer_id}/sku/{sku_id}/eventType/all")
    Call<ProductDetailsResponse> getProductLogs(@Header("email") String email, @Header("Password") String password, @Path("customer_id") String customer_id, @Path("sku_id") String sku_id);

    //GET USERS
    @GET("users")
    Call<UsersResponse> getUsers(@Header("email") String email, @Header("Password") String password);

    //POST USERS
    @POST("users")
    Call<UsersResponse> createUsers(@Header("email") String email, @Header("Password") String password, @Body UserCreatePOJO pojo);

    //update users
    @PUT("users/{userId}")
    Call<UsersResponse> updateusers(@Header("email") String email, @Header("Password") String password, @Path("userId") String userId, @Body UserCreatePOJO pojo);


    //GET GROUPS
    @GET("groups")
    Call<GroupResponse> getGroups(@Header("email") String email, @Header("Password") String password);


    @GET("specialPrograms/getSpecialProgramsByCustomerId/customer/{customer_id}")
    Call<SProgramResponse> getSpecialPrograms(@Header("email") String email, @Header("Password") String password, @Path("customer_id") String customer_id);

    //ALl customers with specialprograms
    @GET("specialPrograms/getCustomersWithSpecialProgramsCount/getAllCustomers")
    Call<SpecialProgramResponse> getSpecialPCustomers(@Header("email") String email, @Header("Password") String password);

    //creating Special Response
    @POST("specialPrograms")
    Call<SpecialProgramResponse> createSpecialPrograms(@Header("email") String email, @Header("Password") String password, @Body SpecialProgram program);

    //updating Special Response
    @PUT("specialPrograms/{specialProgramId}")
    Call<SpecialProgramResponse> updateSpecialPrograms(@Header("email") String email, @Header("Password") String password, @Path("specialProgramId") String specialProgramId, @Body SpecialProgram program);

    //    Retreiving cron_job Reports
//    /cron_jobs/batchAndOrderDetailsV2/from/2017-09-01/to/2018-09-21
    @GET("cron_jobs/batchAndOrderDetailsV2/from/{from}/to/{to}")
    Call<CronJobResponse> getCronJobs(@Header("email") String email, @Header("Password") String password,
                                      @Path("from") String from, @Path("to") String to);

    //    http://732b4c9a.ngrok.io/zummix-api/public/orders/status/totalOrders/customer/all/orderType/1/from/2018-09-01/to/2018-09-21
//    Details on clicking cronjobs fields
    @GET("orders/status/{orderstatus}/customer/{customer_id}/orderType/{ordertype}/from/{from}/to/{to}")
    Call<CronDetailsResponse> getCronJobsDetails(@Header("email") String email, @Header("Password") String password,
                                                 @Path("orderstatus") String orderstatus, @Path("customer_id") String customer_id, @Path("ordertype") String ordertype,
                                                 @Path("from") String from, @Path("to") String to);


    //    FOr cron job's company name //2
    @GET("customers/{customer_id}")
    Call<CompanyResponse> getCOmpanyName(@Header("email") String email, @Header("password") String password);

    //    FOr supportportal Tickets
    @GET("tickets")
    Call<TicketResponse> getTickets(@Header("email") String email, @Header("password") String password);

    //    For Return Tickets
    @GET("returnTickets")
    Call<ReturnTicketResponse> getReturnTickets(@Header("email") String email, @Header("password") String password);

    //    GET Ticket Support Details
    @GET("stores/{id}/supports/{supportid}")
    Call<TicketDetialsResponse> getTicketDetails(@Header("email") String email, @Header("password") String password,
                                                 @Path("id") String id, @Path("supportid") String supportid);

    //    GET Ticket Order Details
    @GET("stores/{id}/supports/{supportid}/order/{order_id}")
    Call<TicketOrderDetailsResponse> getTicketOrderDetails(@Header("email") String email, @Header("password") String password,
                                                           @Path("id") String id, @Path("supportid") String supportid,
                                                           @Path("order_id") String order_id );

    //    For Return Tickets
    @GET("search_tickets/{ticket_number}")
    Call<TicketResponse> getSearchTickets(@Header("email") String email, @Header("password") String password, @Path("ticket_number") String ticket_number);

//    For Comment reply
    @PUT("stores/{support_id}")
    Call<ReplyResponse> putCommentReply(@Header("email") String email, @Header("password") String password, @Path("support_id") String support_id, @Body CommentInput input);

//    For Mark CLose  stores/status/2
   @PUT("stores/status/{support_id}")
   Call<ReplyResponse> putMarkClose(@Header("email") String email, @Header("password") String password, @Path("support_id") String support_id, @Body MarkInput input);

    @GET("stores/reopen1/{support_id}")
    Call<ReplyResponse> getReopen(@Header("email") String email, @Header("password") String password, @Path("support_id") String support_id);

}


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
