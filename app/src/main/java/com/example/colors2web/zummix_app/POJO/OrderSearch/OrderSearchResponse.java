
package com.example.colors2web.zummix_app.POJO.OrderSearch;

import com.example.colors2web.zummix_app.POJO.OrderSearch.OrderDetails;
import com.google.gson.annotations.SerializedName;


public class OrderSearchResponse {

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
