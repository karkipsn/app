
package com.example.colors2web.zummix_app.POJO.OrderEdit;

import com.google.gson.annotations.SerializedName;

public class OrderEditResponse {

    @SerializedName("message")
    private String mMessage;
    @SerializedName("orderShippingAddress")
    private OrderShippingAddress mOrderShippingAddress;
    @SerializedName("returnType")
    private String mReturnType;

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public OrderShippingAddress getOrderShippingAddress() {
        return mOrderShippingAddress;
    }

    public void setOrderShippingAddress(OrderShippingAddress orderShippingAddress) {
        mOrderShippingAddress = orderShippingAddress;
    }

    public String getReturnType() {
        return mReturnType;
    }

    public void setReturnType(String returnType) {
        mReturnType = returnType;
    }

}
