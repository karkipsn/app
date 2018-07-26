
package com.example.colors2web.zummix_app.POJO;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class OrdertrackResponse {

    @SerializedName("message")
    private String mMessage;
    @SerializedName("orderDetails")
    private List<OrderTDetails> mOrderDetails;
    @SerializedName("returnType")
    private String mReturnType;

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public List<OrderTDetails> getOrderDetails() {
        return mOrderDetails;
    }

    public void setOrderDetails(List<OrderTDetails> orderTDetails) {
        mOrderDetails = orderTDetails;
    }

    public String getReturnType() {
        return mReturnType;
    }

    public void setReturnType(String returnType) {
        mReturnType = returnType;
    }

}
