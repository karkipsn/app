
package com.example.colors2web.zummix_app.POJO.TicketOrderDetails;

import com.google.gson.annotations.SerializedName;

public class TicketOrderDetailsResponse {

    @SerializedName("message")
    private String mMessage;
    @SerializedName("returnType")
    private String mReturnType;
    @SerializedName("supportOrderDetails")
    private SupportOrderDetails mSupportOrderDetails;

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

    public SupportOrderDetails getSupportOrderDetails() {
        return mSupportOrderDetails;
    }

    public void setSupportOrderDetails(SupportOrderDetails supportOrderDetails) {
        mSupportOrderDetails = supportOrderDetails;
    }

}
