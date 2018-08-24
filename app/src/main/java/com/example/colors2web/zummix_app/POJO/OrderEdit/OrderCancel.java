package com.example.colors2web.zummix_app.POJO.OrderEdit;

import com.google.gson.annotations.SerializedName;

public class OrderCancel {
    String customer_id;
    String order_status;
    String group_type;
    String created_by;
    String updated_by;

    @SerializedName("message")
    private String mMessage;

    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public String getmReturnType() {
        return mReturnType;
    }

    public void setmReturnType(String mReturnType) {
        this.mReturnType = mReturnType;
    }

    @SerializedName("returnType")
    private String mReturnType;


    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getGroup_type() {
        return group_type;
    }

    public void setGroup_type(String group_type) {
        this.group_type = group_type;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public OrderCancel(String customer_id, String order_status, String group_type, String created_by, String updated_by) {
        this.customer_id = customer_id;
        this.order_status = order_status;
        this.group_type = group_type;
        this.created_by = created_by;
        this.updated_by = updated_by;
    }
}
