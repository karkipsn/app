
package com.example.colors2web.zummix_app.POJO.OrderShippingAddressLogs;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ShippingLogResponse {

    @SerializedName("message")
    private String mMessage;
    @SerializedName("orderShippingAddressLogs")
    private List<OrderShippingAddressLog> mOrderShippingAddressLogs;
    @SerializedName("returnType")
    private String mReturnType;

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public List<OrderShippingAddressLog> getOrderShippingAddressLogs() {
        return mOrderShippingAddressLogs;
    }

    public void setOrderShippingAddressLogs(List<OrderShippingAddressLog> orderShippingAddressLogs) {
        mOrderShippingAddressLogs = orderShippingAddressLogs;
    }

    public String getReturnType() {
        return mReturnType;
    }

    public void setReturnType(String returnType) {
        mReturnType = returnType;
    }

}
