
package com.example.colors2web.zummix_app.POJO.TicketDetails;

import com.google.gson.annotations.SerializedName;

public class CommentInput {

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

    public String getmTicketStatus() {
        return mTicketStatus;
    }

    public void setmTicketStatus(String mTicketStatus) {
        this.mTicketStatus = mTicketStatus;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }

    public String getReply() {
        return mReply;
    }

    public void setReply(String reply) {
        mReply = reply;
    }

    public String getStoreUrl() {
        return mStoreUrl;
    }

    public void setStoreUrl(String storeUrl) {
        mStoreUrl = storeUrl;
    }

    public String getTicketNumber() {
        return mTicketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        mTicketNumber = ticketNumber;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }



    public CommentInput(String mComment, String mReply, String mStoreUrl, String mTicketNumber, String mUserId) {
        this.mComment = mComment;
        this.mReply = mReply;
        this.mStoreUrl = mStoreUrl;
        this.mTicketNumber = mTicketNumber;
        this.mUserId = mUserId;
    }

    public CommentInput(String mComment, String mReply, String mStoreUrl, String mTicketNumber, String mTicketStatus, String mUserId) {
        this.mComment = mComment;
        this.mReply = mReply;
        this.mStoreUrl = mStoreUrl;
        this.mTicketNumber = mTicketNumber;
        this.mTicketStatus = mTicketStatus;
        this.mUserId = mUserId;
    }



}
