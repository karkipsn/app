
package com.example.colors2web.zummix_app.POJO.Order2POJO;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class OrderDetails {

    @SerializedName("boxes")
    private List<Box> mBoxes;
    @SerializedName("item_details")
    private List<ItemDetail> mItemDetails;
    @SerializedName("masterBoxes")
    private String mMasterBoxes;
    @SerializedName("order")
    private Order mOrder;
    @SerializedName("orderLogs")
    private List<OrderLog> mOrderLogs;
    @SerializedName("orderShippingAddresses_details")
    private List<OrderShippingAddressesDetail> mOrderShippingAddressesDetails;
    @SerializedName("refundedItems")
    private List<Object> mRefundedItems;
    @SerializedName("return_company_name")
    private String mReturnCompanyName;

    public List<Box> getBoxes() {
        return mBoxes;
    }

    public void setBoxes(List<Box> boxes) {
        mBoxes = boxes;
    }

    public List<ItemDetail> getItemDetails() {
        return mItemDetails;
    }

    public void setItemDetails(List<ItemDetail> itemDetails) {
        mItemDetails = itemDetails;
    }

    public String getMasterBoxes() {
        return mMasterBoxes;
    }

    public void setMasterBoxes(String masterBoxes) {
        mMasterBoxes = masterBoxes;
    }

    public Order getOrder() {
        return mOrder;
    }

    public void setOrder(Order order) {
        mOrder = order;
    }

    public List<OrderLog> getOrderLogs() {
        return mOrderLogs;
    }

    public void setOrderLogs(List<OrderLog> orderLogs) {
        mOrderLogs = orderLogs;
    }

    public List<OrderShippingAddressesDetail> getOrderShippingAddressesDetails() {
        return mOrderShippingAddressesDetails;
    }

    public void setOrderShippingAddressesDetails(List<OrderShippingAddressesDetail> orderShippingAddressesDetails) {
        mOrderShippingAddressesDetails = orderShippingAddressesDetails;
    }

    public List<Object> getRefundedItems() {
        return mRefundedItems;
    }

    public void setRefundedItems(List<Object> refundedItems) {
        mRefundedItems = refundedItems;
    }

    public String getReturnCompanyName() {
        return mReturnCompanyName;
    }

    public void setReturnCompanyName(String returnCompanyName) {
        mReturnCompanyName = returnCompanyName;
    }

}
