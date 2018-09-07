
package com.example.colors2web.zummix_app.POJO.BatchNumber;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class BatchResponse {

    @SerializedName("batchOrders")
    private List<BatchOrder> mBatchOrders;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("returnType")
    private String mReturnType;

    public List<BatchOrder> getBatchOrders() {
        return mBatchOrders;
    }

    public void setBatchOrders(List<BatchOrder> batchOrders) {
        mBatchOrders = batchOrders;
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
