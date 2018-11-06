
package com.example.colors2web.zummix_app.POJO.TicketDetails;

import com.google.gson.annotations.SerializedName;

public class Reply {

    @SerializedName("comment")
    private Comment mComment;
    @SerializedName("created_by")
    private String mCreatedBy;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("password")
    private String mPassword;
    @SerializedName("store_url")
    private String mStoreUrl;
    @SerializedName("support_id")
    private String mSupportId;
    @SerializedName("ticket_number")
    private String mTicketNumber;
    @SerializedName("ticket_status")
    private Long mTicketStatus;

    public Comment getComment() {
        return mComment;
    }

    public void setComment(Comment comment) {
        mComment = comment;
    }

    public String getCreatedBy() {
        return mCreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        mCreatedBy = createdBy;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getStoreUrl() {
        return mStoreUrl;
    }

    public void setStoreUrl(String storeUrl) {
        mStoreUrl = storeUrl;
    }

    public String getSupportId() {
        return mSupportId;
    }

    public void setSupportId(String supportId) {
        mSupportId = supportId;
    }

    public String getTicketNumber() {
        return mTicketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        mTicketNumber = ticketNumber;
    }

    public Long getTicketStatus() {
        return mTicketStatus;
    }

    public void setTicketStatus(Long ticketStatus) {
        mTicketStatus = ticketStatus;
    }

}
