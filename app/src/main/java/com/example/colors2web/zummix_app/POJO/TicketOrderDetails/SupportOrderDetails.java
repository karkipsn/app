
package com.example.colors2web.zummix_app.POJO.TicketOrderDetails;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SupportOrderDetails {

    @SerializedName("billingDetails")
    private BillingDetails mBillingDetails;
    @SerializedName("orderDetails")
    private OrderDetails mOrderDetails;
    @SerializedName("paymentDetails")
    private PaymentDetails mPaymentDetails;
    @SerializedName("purchasedProducts")
    private List<PurchasedProduct> mPurchasedProducts;
    @SerializedName("shippingDetails")
    private ShippingDetails mShippingDetails;
    @SerializedName("store")
    private Store mStore;
    @SerializedName("support")
    private Support mSupport;

    public BillingDetails getBillingDetails() {
        return mBillingDetails;
    }

    public void setBillingDetails(BillingDetails billingDetails) {
        mBillingDetails = billingDetails;
    }

    public OrderDetails getOrderDetails() {
        return mOrderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        mOrderDetails = orderDetails;
    }

    public PaymentDetails getPaymentDetails() {
        return mPaymentDetails;
    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        mPaymentDetails = paymentDetails;
    }

    public List<PurchasedProduct> getPurchasedProducts() {
        return mPurchasedProducts;
    }

    public void setPurchasedProducts(List<PurchasedProduct> purchasedProducts) {
        mPurchasedProducts = purchasedProducts;
    }

    public ShippingDetails getShippingDetails() {
        return mShippingDetails;
    }

    public void setShippingDetails(ShippingDetails shippingDetails) {
        mShippingDetails = shippingDetails;
    }

    public Store getStore() {
        return mStore;
    }

    public void setStore(Store store) {
        mStore = store;
    }

    public Support getSupport() {
        return mSupport;
    }

    public void setSupport(Support support) {
        mSupport = support;
    }

}
