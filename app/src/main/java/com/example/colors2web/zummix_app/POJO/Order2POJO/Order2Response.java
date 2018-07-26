
package com.example.colors2web.zummix_app.POJO.Order2POJO;


import com.google.gson.annotations.SerializedName;

public class Order2Response {

    @SerializedName("message")
    private String mMessage;
    @SerializedName("orderDetails")
    private OrderDetails mOrderDetails;
    @SerializedName("returnType")
    private String mReturnType;

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public OrderDetails getOrderDetails() {
        return mOrderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        mOrderDetails = orderDetails;
    }

    public String getReturnType() {
        return mReturnType;
    }

    public void setReturnType(String returnType) {
        mReturnType = returnType;
    }

}
