
package com.example.colors2web.zummix_app.POJO.Users;

import com.google.gson.annotations.SerializedName;


public class Group {

    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("id")
    private Long mId;
    @SerializedName("name")
    private String mName;
//    @SerializedName("permissions")
//    private Permissions mPermissions;
    @SerializedName("updated_at")
    private String mUpdatedAt;

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

//    public Permissions getPermissions() {
//        return mPermissions;
//    }
//
//    public void setPermissions(Permissions permissions) {
//        mPermissions = permissions;
//    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
