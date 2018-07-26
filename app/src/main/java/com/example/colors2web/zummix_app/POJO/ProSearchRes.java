
package com.example.colors2web.zummix_app.POJO;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class ProSearchRes {

    @SerializedName("customerItems")
    private List<CustomerItem> mCustomerItems;
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

}
