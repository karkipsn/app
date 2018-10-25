
package com.example.colors2web.zummix_app.POJO.TicketDetails;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TicketDetialsResponse {

    @SerializedName("message")
    private String mMessage;
    @SerializedName("returnType")
    private String mReturnType;
    @SerializedName("returnedItems")
    private List<ReturnedItem> mReturnedItems;
    @SerializedName("store")
    private Store mStore;
    @SerializedName("support")
    private Support mSupport;
    @SerializedName("users")
    private List<User> mUsers;
    @SerializedName("comments")
    private List<Comments> mComments;

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

    public List<ReturnedItem> getReturnedItems() {
        return mReturnedItems;
    }

    public void setReturnedItems(List<ReturnedItem> returnedItems) {
        mReturnedItems = returnedItems;
    }

    public Store getStore() {
        return mStore;
    }

    public void setStore(Store store) {
        mStore = store;
    }

    public Support getSupport() {
        return mSupport;
    }

    public void setSupport(Support support) {
        mSupport = support;
    }

    public List<User> getUsers() {
        return mUsers;
    }

    public void setUsers(List<User> users) {
        mUsers = users;
    }

    public List<Comments> getmComments() {
        return mComments;
    }

    public void setmComments(List<Comments> mComments) {
        this.mComments = mComments;
    }
}
