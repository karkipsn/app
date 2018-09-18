
package com.example.colors2web.zummix_app.POJO.Users;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class User implements Parcelable {

    @SerializedName("activated")
    private Boolean mActivated;
    @SerializedName("activated_at")
    private Object mActivatedAt;
    @SerializedName("client_name")
    private String mClientName;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("customer_id")
    private Long mCustomerId;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("first_name")
    private String mFirstName;
    @SerializedName("id")
    private Long mId;
    @SerializedName("last_login")
    private String mLastLogin;
    @SerializedName("last_name")
    private String mLastName;
    @SerializedName("login_token")
    private String mLoginToken;
    //    @SerializedName("permissions")
//    private List<Object> mPermissions;
    @SerializedName("reset_password")
    private String mResetPassword;
    @SerializedName("special_program")
    private String mSpecialProgram;
    @SerializedName("special_program_name")
    private String mSpecialProgramName;
    @SerializedName("updated_at")
    private String mUpdatedAt;
    @SerializedName("user")
    private User mUser;
    @SerializedName("userGroup")
    private List<String> mUserGroup;


    public User() {
    }

    protected User(Parcel in) {
        byte tmpMActivated = in.readByte();
        mActivated = tmpMActivated == 0 ? null : tmpMActivated == 1;
        mClientName = in.readString();
        mCreatedAt = in.readString();
        if (in.readByte() == 0) {
            mCustomerId = null;
        } else {
            mCustomerId = in.readLong();
        }
        mEmail = in.readString();
        mFirstName = in.readString();
        if (in.readByte() == 0) {
            mId = null;
        } else {
            mId = in.readLong();
        }
        mLastLogin = in.readString();
        mLastName = in.readString();
        mLoginToken = in.readString();
        mResetPassword = in.readString();
        mSpecialProgram = in.readString();
        mSpecialProgramName = in.readString();
        mUpdatedAt = in.readString();
        mUser = in.readParcelable(User.class.getClassLoader());
        mUserGroup = in.createStringArrayList();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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

    public String getClientName() {
        return mClientName;
    }

    public void setClientName(String clientName) {
        mClientName = clientName;
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

//    public List<Object> getPermissions() {
//        return mPermissions;
//    }
//
//    public void setPermissions(List<Object> permissions) {
//        mPermissions = permissions;
//    }

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

    public String getSpecialProgramName() {
        return mSpecialProgramName;
    }

    public void setSpecialProgramName(String specialProgramName) {
        mSpecialProgramName = specialProgramName;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public List<String> getUserGroup() {
        return mUserGroup;
    }

    public void setUserGroup(List<String> userGroup) {
        mUserGroup = userGroup;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (mActivated == null ? 0 : mActivated ? 1 : 2));
        dest.writeString(mClientName);
        dest.writeString(mCreatedAt);
        if (mCustomerId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mCustomerId);
        }
        dest.writeString(mEmail);
        dest.writeString(mFirstName);
        if (mId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mId);
        }
        dest.writeString(mLastLogin);
        dest.writeString(mLastName);
        dest.writeString(mLoginToken);
        dest.writeString(mResetPassword);
        dest.writeString(mSpecialProgram);
        dest.writeString(mSpecialProgramName);
        dest.writeString(mUpdatedAt);
        dest.writeParcelable(mUser, flags);
        dest.writeStringList(mUserGroup);
    }
}
