
package com.example.colors2web.zummix_app.POJO.BatchNumber;

import com.google.gson.annotations.SerializedName;

public class BatchOrder {

    @SerializedName("batch_number")
    private Long mBatchNumber;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("customer_email")
    private String mCustomerEmail;
    @SerializedName("customer_name")
    private String mCustomerName;
    @SerializedName("customer_office_name")
    private String mCustomerOfficeName;
    @SerializedName("id")
    private Long mId;
    @SerializedName("is_refunded")
    private String mIsRefunded;
    @SerializedName("order_number")
    private String mOrderNumber;
    @SerializedName("order_status")
    private String mOrderStatus;
    @SerializedName("order_type")
    private String mOrderType;
    @SerializedName("ship_to_name")
    private String mShipToName;
    @SerializedName("total_qty")
    private String mTotalQty;
    @SerializedName("total_remaining_qty")
    private String mTotalRemainingQty;

    public Long getBatchNumber() {
        return mBatchNumber;
    }

    public void setBatchNumber(Long batchNumber) {
        mBatchNumber = batchNumber;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getCustomerEmail() {
        return mCustomerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        mCustomerEmail = customerEmail;
    }

    public String getCustomerName() {
        return mCustomerName;
    }

    public void setCustomerName(String customerName) {
        mCustomerName = customerName;
    }

    public String getCustomerOfficeName() {
        return mCustomerOfficeName;
    }

    public void setCustomerOfficeName(String customerOfficeName) {
        mCustomerOfficeName = customerOfficeName;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getIsRefunded() {
        return mIsRefunded;
    }

    public void setIsRefunded(String isRefunded) {
        mIsRefunded = isRefunded;
    }

    public String getOrderNumber() {
        return mOrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        mOrderNumber = orderNumber;
    }

    public String getOrderStatus() {
        return mOrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        mOrderStatus = orderStatus;
    }

    public String getOrderType() {
        return mOrderType;
    }

    public void setOrderType(String orderType) {
        mOrderType = orderType;
    }

    public String getShipToName() {
        return mShipToName;
    }

    public void setShipToName(String shipToName) {
        mShipToName = shipToName;
    }

    public String getTotalQty() {
        return mTotalQty;
    }

    public void setTotalQty(String totalQty) {
        mTotalQty = totalQty;
    }

    public String getTotalRemainingQty() {
        return mTotalRemainingQty;
    }

    public void setTotalRemainingQty(String totalRemainingQty) {
        mTotalRemainingQty = totalRemainingQty;
    }

}
