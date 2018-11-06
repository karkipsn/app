
package com.example.colors2web.zummix_app.POJO.TicketDetails;

import com.google.gson.annotations.SerializedName;

public class ReplyResponse {

    @SerializedName("message")
    private String mMessage;
    @SerializedName("reply")
    private Reply mReply;
    @SerializedName("returnType")
    private String mReturnType;

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public Reply getReply() {
        return mReply;
    }

    public void setReply(Reply reply) {
        mReply = reply;
    }

    public String getReturnType() {
        return mReturnType;
    }

    public void setReturnType(String returnType) {
        mReturnType = returnType;
    }

}
