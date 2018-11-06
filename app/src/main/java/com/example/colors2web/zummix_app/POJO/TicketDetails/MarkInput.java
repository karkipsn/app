package com.example.colors2web.zummix_app.POJO.TicketDetails;

import com.google.gson.annotations.SerializedName;

public class MarkInput  {
    @SerializedName("comment")
    private String mComment;
    @SerializedName("reply")
    private String mReply;
    @SerializedName("store_url")
    private String mStoreUrl;
    @SerializedName("ticket_number")
    private String mTicketNumber;
    @SerializedName("ticket_status")
    private String mTicketStatus;
    @SerializedName("user_id")
    private String mUserId;

    public MarkInput(String mReply, String mStoreUrl, String mTicketNumber, String mTicketStatus, String mUserId) {
        this.mReply = mReply;
        this.mStoreUrl = mStoreUrl;
        this.mTicketNumber = mTicketNumber;
        this.mTicketStatus = mTicketStatus;
        this.mUserId = mUserId;
    }

    public MarkInput(String mComment, String mReply, String mStoreUrl, String mTicketNumber, String mTicketStatus, String mUserId) {
        this.mComment = mComment;
        this.mReply = mReply;
        this.mStoreUrl = mStoreUrl;
        this.mTicketNumber = mTicketNumber;
        this.mTicketStatus = mTicketStatus;
        this.mUserId = mUserId;

    }

    public String getmComment() {

        return mComment;
    }

    public void setmComment(String mComment) {
        this.mComment = mComment;
    }

    public String getmReply() {
        return mReply;
    }

    public void setmReply(String mReply) {
        this.mReply = mReply;
    }

    public String getmStoreUrl() {
        return mStoreUrl;
    }

    public void setmStoreUrl(String mStoreUrl) {
        this.mStoreUrl = mStoreUrl;
    }

    public String getmTicketNumber() {
        return mTicketNumber;
    }

    public void setmTicketNumber(String mTicketNumber) {
        this.mTicketNumber = mTicketNumber;
    }

    public String getmTicketStatus() {
        return mTicketStatus;
    }

    public void setmTicketStatus(String mTicketStatus) {
        this.mTicketStatus = mTicketStatus;
    }

    public String getmUserId() {
        return mUserId;
    }

    public void setmUserId(String mUserId) {
        this.mUserId = mUserId;
    }
}
