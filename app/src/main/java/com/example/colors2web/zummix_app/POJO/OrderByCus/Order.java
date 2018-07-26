
package com.example.colors2web.zummix_app.POJO.OrderByCus;


import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("customer_email")
    private String mCustomerEmail;
    @SerializedName("customer_id")
    private Long mCustomerId;
    @SerializedName("customer_name")
    private String mCustomerName;
    @SerializedName("no_of_orders")
    private Long mNoOfOrders;
    @SerializedName("no_of_ship_to_orders")
    private Long mNoOfShipToOrders;
    @SerializedName("no_of_vip_delivery_orders")
    private Long mNoOfVipDeliveryOrders;
    @SerializedName("no_of_will_calls_orders")
    private Long mNoOfWillCallsOrders;

    public String getCustomerEmail() {
        return mCustomerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        mCustomerEmail = customerEmail;
    }

    public Long getCustomerId() {
        return mCustomerId;
    }

    public void setCustomerId(Long customerId) {
        mCustomerId = customerId;
    }

    public String getCustomerName() {
        return mCustomerName;
    }

    public void setCustomerName(String customerName) {
        mCustomerName = customerName;
    }

    public Long getNoOfOrders() {
        return mNoOfOrders;
    }

    public void setNoOfOrders(Long noOfOrders) {
        mNoOfOrders = noOfOrders;
    }

    public Long getNoOfShipToOrders() {
        return mNoOfShipToOrders;
    }

    public void setNoOfShipToOrders(Long noOfShipToOrders) {
        mNoOfShipToOrders = noOfShipToOrders;
    }

    public Long getNoOfVipDeliveryOrders() {
        return mNoOfVipDeliveryOrders;
    }

    public void setNoOfVipDeliveryOrders(Long noOfVipDeliveryOrders) {
        mNoOfVipDeliveryOrders = noOfVipDeliveryOrders;
    }

    public Long getNoOfWillCallsOrders() {
        return mNoOfWillCallsOrders;
    }

    public void setNoOfWillCallsOrders(Long noOfWillCallsOrders) {
        mNoOfWillCallsOrders = noOfWillCallsOrders;
    }

}
