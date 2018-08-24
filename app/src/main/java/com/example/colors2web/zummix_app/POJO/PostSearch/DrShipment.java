
package com.example.colors2web.zummix_app.POJO.PostSearch;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class DrShipment implements Serializable {

    @SerializedName("actual_box_count")
    private Long mActualBoxCount;
    @SerializedName("actual_item_count")
    private Long mActualItemCount;
    @SerializedName("actual_pallet_count")
    private Long mActualPalletCount;
    @SerializedName("all_items_moved_to_pick")
    private Long mAllItemsMovedToPick;
    @SerializedName("confirm_dr_note")
    private String mConfirmDrNote;
    @SerializedName("contact_email")
    private String mContactEmail;
    @SerializedName("contact_name")
    private String mContactName;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("created_by")
    private Long mCreatedBy;
    @SerializedName("customer_id")
    private Long mCustomerId;
    @SerializedName("customer_item_id")
    private Long mCustomerItemId;
    @SerializedName("customer_name")
    private String mCustomerName;
    @SerializedName("dr_description")
    private String mDrDescription;
    @SerializedName("dr_number")
    private Long mDrNumber;
    @SerializedName("dr_type")
    private String mDrType;
    @SerializedName("est_delivery_date")
    private String mEstDeliveryDate;
    @SerializedName("id")
    private Long mId;
    @SerializedName("isNon_confirm_dr")
    private String mIsNonConfirmDr;
    @SerializedName("item_name")
    private String mItemName;
    @SerializedName("item_sku_number")
    private String mItemSkuNumber;
    @SerializedName("queue_type")
    private String mQueueType;
    @SerializedName("said_box_count")
    private Long mSaidBoxCount;
    @SerializedName("said_item_count")
    private Long mSaidItemCount;
    @SerializedName("said_pallet_count")
    private Long mSaidPalletCount;
    @SerializedName("service_status")
    private String mServiceStatus;
    @SerializedName("tracking_number")
    private String mTrackingNumber;
    @SerializedName("updated_at")
    private String mUpdatedAt;
    @SerializedName("updated_by")
    private Long mUpdatedBy;
    @SerializedName("urgency_status")
    private String mUrgencyStatus;

    public Long getActualBoxCount() {
        return mActualBoxCount;
    }

    public void setActualBoxCount(Long actualBoxCount) {
        mActualBoxCount = actualBoxCount;
    }

    public Long getActualItemCount() {
        return mActualItemCount;
    }

    public void setActualItemCount(Long actualItemCount) {
        mActualItemCount = actualItemCount;
    }

    public Long getActualPalletCount() {
        return mActualPalletCount;
    }

    public void setActualPalletCount(Long actualPalletCount) {
        mActualPalletCount = actualPalletCount;
    }

    public Long getAllItemsMovedToPick() {
        return mAllItemsMovedToPick;
    }

    public void setAllItemsMovedToPick(Long allItemsMovedToPick) {
        mAllItemsMovedToPick = allItemsMovedToPick;
    }

    public String getConfirmDrNote() {
        return mConfirmDrNote;
    }

    public void setConfirmDrNote(String confirmDrNote) {
        mConfirmDrNote = confirmDrNote;
    }

    public String getContactEmail() {
        return mContactEmail;
    }

    public void setContactEmail(String contactEmail) {
        mContactEmail = contactEmail;
    }

    public String getContactName() {
        return mContactName;
    }

    public void setContactName(String contactName) {
        mContactName = contactName;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public Long getCreatedBy() {
        return mCreatedBy;
    }

    public void setCreatedBy(Long createdBy) {
        mCreatedBy = createdBy;
    }

    public Long getCustomerId() {
        return mCustomerId;
    }

    public void setCustomerId(Long customerId) {
        mCustomerId = customerId;
    }

    public Long getCustomerItemId() {
        return mCustomerItemId;
    }

    public void setCustomerItemId(Long customerItemId) {
        mCustomerItemId = customerItemId;
    }

    public String getCustomerName() {
        return mCustomerName;
    }

    public void setCustomerName(String customerName) {
        mCustomerName = customerName;
    }

    public String getDrDescription() {
        return mDrDescription;
    }

    public void setDrDescription(String drDescription) {
        mDrDescription = drDescription;
    }

    public Long getDrNumber() {
        return mDrNumber;
    }

    public void setDrNumber(Long drNumber) {
        mDrNumber = drNumber;
    }

    public String getDrType() {
        return mDrType;
    }

    public void setDrType(String drType) {
        mDrType = drType;
    }

    public String getEstDeliveryDate() {
        return mEstDeliveryDate;
    }

    public void setEstDeliveryDate(String estDeliveryDate) {
        mEstDeliveryDate = estDeliveryDate;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getIsNonConfirmDr() {
        return mIsNonConfirmDr;
    }

    public void setIsNonConfirmDr(String isNonConfirmDr) {
        mIsNonConfirmDr = isNonConfirmDr;
    }

    public String getItemName() {
        return mItemName;
    }

    public void setItemName(String itemName) {
        mItemName = itemName;
    }

    public String getItemSkuNumber() {
        return mItemSkuNumber;
    }

    public void setItemSkuNumber(String itemSkuNumber) {
        mItemSkuNumber = itemSkuNumber;
    }

    public String getQueueType() {
        return mQueueType;
    }

    public void setQueueType(String queueType) {
        mQueueType = queueType;
    }

    public Long getSaidBoxCount() {
        return mSaidBoxCount;
    }

    public void setSaidBoxCount(Long saidBoxCount) {
        mSaidBoxCount = saidBoxCount;
    }

    public Long getSaidItemCount() {
        return mSaidItemCount;
    }

    public void setSaidItemCount(Long saidItemCount) {
        mSaidItemCount = saidItemCount;
    }

    public Long getSaidPalletCount() {
        return mSaidPalletCount;
    }

    public void setSaidPalletCount(Long saidPalletCount) {
        mSaidPalletCount = saidPalletCount;
    }

    public String getServiceStatus() {
        return mServiceStatus;
    }

    public void setServiceStatus(String serviceStatus) {
        mServiceStatus = serviceStatus;
    }

    public String getTrackingNumber() {
        return mTrackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        mTrackingNumber = trackingNumber;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public Long getUpdatedBy() {
        return mUpdatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        mUpdatedBy = updatedBy;
    }

    public String getUrgencyStatus() {
        return mUrgencyStatus;
    }

    public void setUrgencyStatus(String urgencyStatus) {
        mUrgencyStatus = urgencyStatus;
    }

}
