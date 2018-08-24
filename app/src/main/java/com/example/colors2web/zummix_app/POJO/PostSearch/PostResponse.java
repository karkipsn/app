
package com.example.colors2web.zummix_app.POJO.PostSearch;

import java.util.List;

import com.example.colors2web.zummix_app.POJO.Order2POJO.Box;
import com.google.gson.annotations.SerializedName;

public class PostResponse {

    @SerializedName("drShipments")
    private List<DrShipment> mDrShipments;

    @SerializedName("orders")
    private List<Orders> mOrders;

    @SerializedName("boxes")
    private List<Boxes> mBoxes;

    @SerializedName("customerItems")
    private List<CustomerItems> mCustomerItems;

    @SerializedName("message")
    private String mMessage;
    @SerializedName("returnType")
    private String mReturnType;


    public List<DrShipment> getmDrShipments() {
        return mDrShipments;
    }

    public void setmDrShipments(List<DrShipment> mDrShipments) {
        this.mDrShipments = mDrShipments;
    }

    public List<Orders> getmOrders() {
        return mOrders;
    }

    public void setmOrders(List<Orders> mOrders) {
        this.mOrders = mOrders;
    }

    public List<Boxes> getmBoxes() {
        return mBoxes;
    }

    public void setmBoxes(List<Boxes> mBoxes) {
        this.mBoxes = mBoxes;
    }

    public List<CustomerItems> getmCustomerItems() {
        return mCustomerItems;
    }

    public void setmCustomerItems(List<CustomerItems> mCustomerItems) {
        this.mCustomerItems = mCustomerItems;
    }

    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public String getmReturnType() {
        return mReturnType;
    }

    public void setmReturnType(String mReturnType) {
        this.mReturnType = mReturnType;
    }
}
