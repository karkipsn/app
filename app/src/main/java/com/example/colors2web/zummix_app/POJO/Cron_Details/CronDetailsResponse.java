
package com.example.colors2web.zummix_app.POJO.Cron_Details;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CronDetailsResponse {

    @SerializedName("message")
    private String mMessage;
    @SerializedName("orders")
    private List<Order> mOrders;
    @SerializedName("returnType")
    private String mReturnType;

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public List<Order> getOrders() {
        return mOrders;
    }

    public void setOrders(List<Order> orders) {
        mOrders = orders;
    }

    public String getReturnType() {
        return mReturnType;
    }

    public void setReturnType(String returnType) {
        mReturnType = returnType;
    }

}
