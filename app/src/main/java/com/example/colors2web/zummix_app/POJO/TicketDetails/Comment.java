
package com.example.colors2web.zummix_app.POJO.TicketDetails;

import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("comment")
    private String mComment;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("created_by")
    private String mCreatedBy;
    @SerializedName("id")
    private Long mId;
    @SerializedName("support_id")
    private String mSupportId;
    @SerializedName("updated_at")
    private String mUpdatedAt;

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getCreatedBy() {
        return mCreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        mCreatedBy = createdBy;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getSupportId() {
        return mSupportId;
    }

    public void setSupportId(String supportId) {
        mSupportId = supportId;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
