
package com.example.colors2web.zummix_app.POJO.login;

import com.google.gson.annotations.SerializedName;


public class UserLogin {

    @SerializedName("activated")
    private Boolean mActivated;
    @SerializedName("activated_at")
    private Object mActivatedAt;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("customer_id")
    private Long mCustomerId;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("first_name")
    private String mFirstName;
    @SerializedName("group_type")
    private String mGroupType;
    @SerializedName("id")
    private Long mId;
    @SerializedName("last_login")
    private String mLastLogin;
    @SerializedName("last_name")
    private String mLastName;
    @SerializedName("login_token")
    private String mLoginToken;

    @SerializedName("reset_password")
    private String mResetPassword;
    @SerializedName("special_program")
    private String mSpecialProgram;
    @SerializedName("updated_at")
    private String mUpdatedAt;

    public Boolean getActivated() {
        return mActivated;
    }

    public void setActivated(Boolean activated) {
        mActivated = activated;
    }

    public Object getActivatedAt() {
        return mActivatedAt;
    }

    public void setActivatedAt(Object activatedAt) {
        mActivatedAt = activatedAt;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public Long getCustomerId() {
        return mCustomerId;
    }

    public void setCustomerId(Long customerId) {
        mCustomerId = customerId;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getGroupType() {
        return mGroupType;
    }

    public void setGroupType(String groupType) {
        mGroupType = groupType;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getLastLogin() {
        return mLastLogin;
    }

    public void setLastLogin(String lastLogin) {
        mLastLogin = lastLogin;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getLoginToken() {
        return mLoginToken;
    }

    public void setLoginToken(String loginToken) {
        mLoginToken = loginToken;
    }

    public String getResetPassword() {
        return mResetPassword;
    }

    public void setResetPassword(String resetPassword) {
        mResetPassword = resetPassword;
    }

    public String getSpecialProgram() {
        return mSpecialProgram;
    }

    public void setSpecialProgram(String specialProgram) {
        mSpecialProgram = specialProgram;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
