
package com.example.colors2web.zummix_app.POJO.customers;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CustomerResponse {

    @SerializedName("customers")
    private List<Customers> mCustomer;


    public Customers getM1Customer() {
        return m1Customer;
    }

    public void setM1Customer(Customers m1Customer) {
        this.m1Customer = m1Customer;
    }

    @SerializedName("customer")
    private Customers m1Customer;

    public List<ShippingMethods> getmShippingMethods() {
        return mShippingMethods;
    }

    public void setmShippingMethods(List<ShippingMethods> mShippingMethods) {
        this.mShippingMethods = mShippingMethods;
    }

    @SerializedName("shippingMethods")
    private List<ShippingMethods> mShippingMethods;

    @SerializedName("customerItems")
    private List<CustomerItem> mCustomerItems;

    public ShopifyCredential getmShopifyCredential() {
        return mShopifyCredential;
    }

    public void setmShopifyCredential(ShopifyCredential mShopifyCredential) {
        this.mShopifyCredential = mShopifyCredential;
    }

    public ShipStationCredential getmShipStationCredential() {
        return mShipStationCredential;
    }

    public void setmShipStationCredential(ShipStationCredential mShipStationCredential) {
        this.mShipStationCredential = mShipStationCredential;
    }

    @SerializedName("shopifyCredential")
    private ShopifyCredential mShopifyCredential;

    @SerializedName("shipStationCredential")
    private ShipStationCredential mShipStationCredential;

    @SerializedName("message")
    private String mMessage;
    @SerializedName("returnType")
    private String mReturnType;

    public List<CustomerItem> getCustomerItems() {
        return mCustomerItems;
    }

    public void setCustomerItems(List<CustomerItem> customerItems) {
        mCustomerItems = customerItems;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getReturnType() {
        return mReturnType;
    }

    public void setReturnType(String returnType) {
        mReturnType = returnType;
    }

    public List<Customers> getmCustomer() {
        return mCustomer;
    }

    public void setmCustomer(List<Customers> mCustomer) {
        this.mCustomer = mCustomer;
    }


}
