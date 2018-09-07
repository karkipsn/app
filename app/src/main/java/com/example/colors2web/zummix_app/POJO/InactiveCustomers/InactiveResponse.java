
package com.example.colors2web.zummix_app.POJO.InactiveCustomers;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class InactiveResponse {

    @SerializedName("message")
    private String mMessage;
    @SerializedName("returnType")
    private String mReturnType;
    @SerializedName("storeInactiveItems")
    private List<StoreInactiveItem> mStoreInactiveItems;

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

    public List<StoreInactiveItem> getStoreInactiveItems() {
        return mStoreInactiveItems;
    }

    public void setStoreInactiveItems(List<StoreInactiveItem> storeInactiveItems) {
        mStoreInactiveItems = storeInactiveItems;
    }

}
