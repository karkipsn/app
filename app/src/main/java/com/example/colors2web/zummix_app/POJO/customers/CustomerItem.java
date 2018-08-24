
package com.example.colors2web.zummix_app.POJO.customers;

import com.google.gson.annotations.SerializedName;

public class CustomerItem {

    @SerializedName("barcode_file_name")
    private String mBarcodeFileName;
    @SerializedName("country_origin")
    private String mCountryOrigin;
    @SerializedName("cpg_sku_number")
    private String mCpgSkuNumber;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("created_by")
    private Long mCreatedBy;
    @SerializedName("customer_id")
    private Long mCustomerId;
    @SerializedName("cycle_count_list")
    private String mCycleCountList;
    @SerializedName("exceeded_item_count")
    private Long mExceededItemCount;
    @SerializedName("id")
    private Long mId;
    @SerializedName("is_cpg")
    private String mIsCpg;
    @SerializedName("is_low_inventory_notifications_sent")
    private Long mIsLowInventoryNotificationsSent;
    @SerializedName("is_one_time_product")
    private String mIsOneTimeProduct;
    @SerializedName("is_over_size_product")
    private String mIsOverSizeProduct;
    @SerializedName("is_paused")
    private Long mIsPaused;
    @SerializedName("is_retire_product")
    private String mIsRetireProduct;
    @SerializedName("item_description")
    private String mItemDescription;
    @SerializedName("item_image")
    private String mItemImage;
    @SerializedName("item_name")
    private String mItemName;
    @SerializedName("item_reorder_trigger")
    private Long mItemReorderTrigger;
    @SerializedName("item_sku_number")
    private String mItemSkuNumber;
    @SerializedName("item_status")
    private String mItemStatus;
    @SerializedName("min_quantity")
    private Long mMinQuantity;
    @SerializedName("ordered_quantity")
    private Object mOrderedQuantity;
    @SerializedName("pick")
    private String mPick;
    @SerializedName("pick_balance")
    private Object mPickBalance;
    @SerializedName("price")
    private String mPrice;
    @SerializedName("replenish")
    private String mReplenish;
    @SerializedName("requested_quantity")
    private Object mRequestedQuantity;
    @SerializedName("special_program_number")
    private String mSpecialProgramNumber;
    @SerializedName("tr_quantity")
    private Long mTrQuantity;
    @SerializedName("UOM")
    private String mUOM;
    @SerializedName("updated_at")
    private String mUpdatedAt;
    @SerializedName("updated_by")
    private Long mUpdatedBy;
    @SerializedName("weight")
    private String mWeight;
    @SerializedName("zummix_sku_number")
    private String mZummixSkuNumber;

    public String getBarcodeFileName() {
        return mBarcodeFileName;
    }

    public void setBarcodeFileName(String barcodeFileName) {
        mBarcodeFileName = barcodeFileName;
    }

    public String getCountryOrigin() {
        return mCountryOrigin;
    }

    public void setCountryOrigin(String countryOrigin) {
        mCountryOrigin = countryOrigin;
    }

    public String getCpgSkuNumber() {
        return mCpgSkuNumber;
    }

    public void setCpgSkuNumber(String cpgSkuNumber) {
        mCpgSkuNumber = cpgSkuNumber;
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

    public String getCycleCountList() {
        return mCycleCountList;
    }

    public void setCycleCountList(String cycleCountList) {
        mCycleCountList = cycleCountList;
    }

    public Long getExceededItemCount() {
        return mExceededItemCount;
    }

    public void setExceededItemCount(Long exceededItemCount) {
        mExceededItemCount = exceededItemCount;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getIsCpg() {
        return mIsCpg;
    }

    public void setIsCpg(String isCpg) {
        mIsCpg = isCpg;
    }

    public Long getIsLowInventoryNotificationsSent() {
        return mIsLowInventoryNotificationsSent;
    }

    public void setIsLowInventoryNotificationsSent(Long isLowInventoryNotificationsSent) {
        mIsLowInventoryNotificationsSent = isLowInventoryNotificationsSent;
    }

    public String getIsOneTimeProduct() {
        return mIsOneTimeProduct;
    }

    public void setIsOneTimeProduct(String isOneTimeProduct) {
        mIsOneTimeProduct = isOneTimeProduct;
    }

    public String getIsOverSizeProduct() {
        return mIsOverSizeProduct;
    }

    public void setIsOverSizeProduct(String isOverSizeProduct) {
        mIsOverSizeProduct = isOverSizeProduct;
    }

    public Long getIsPaused() {
        return mIsPaused;
    }

    public void setIsPaused(Long isPaused) {
        mIsPaused = isPaused;
    }

    public String getIsRetireProduct() {
        return mIsRetireProduct;
    }

    public void setIsRetireProduct(String isRetireProduct) {
        mIsRetireProduct = isRetireProduct;
    }

    public String getItemDescription() {
        return mItemDescription;
    }

    public void setItemDescription(String itemDescription) {
        mItemDescription = itemDescription;
    }

    public String getItemImage() {
        return mItemImage;
    }

    public void setItemImage(String itemImage) {
        mItemImage = itemImage;
    }

    public String getItemName() {
        return mItemName;
    }

    public void setItemName(String itemName) {
        mItemName = itemName;
    }

    public Long getItemReorderTrigger() {
        return mItemReorderTrigger;
    }

    public void setItemReorderTrigger(Long itemReorderTrigger) {
        mItemReorderTrigger = itemReorderTrigger;
    }

    public String getItemSkuNumber() {
        return mItemSkuNumber;
    }

    public void setItemSkuNumber(String itemSkuNumber) {
        mItemSkuNumber = itemSkuNumber;
    }

    public String getItemStatus() {
        return mItemStatus;
    }

    public void setItemStatus(String itemStatus) {
        mItemStatus = itemStatus;
    }

    public Long getMinQuantity() {
        return mMinQuantity;
    }

    public void setMinQuantity(Long minQuantity) {
        mMinQuantity = minQuantity;
    }

    public Object getOrderedQuantity() {
        return mOrderedQuantity;
    }

    public void setOrderedQuantity(Object orderedQuantity) {
        mOrderedQuantity = orderedQuantity;
    }

    public String getPick() {
        return mPick;
    }

    public void setPick(String pick) {
        mPick = pick;
    }

    public Object getPickBalance() {
        return mPickBalance;
    }

    public void setPickBalance(Object pickBalance) {
        mPickBalance = pickBalance;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public String getReplenish() {
        return mReplenish;
    }

    public void setReplenish(String replenish) {
        mReplenish = replenish;
    }

    public Object getRequestedQuantity() {
        return mRequestedQuantity;
    }

    public void setRequestedQuantity(Object requestedQuantity) {
        mRequestedQuantity = requestedQuantity;
    }

    public String getSpecialProgramNumber() {
        return mSpecialProgramNumber;
    }

    public void setSpecialProgramNumber(String specialProgramNumber) {
        mSpecialProgramNumber = specialProgramNumber;
    }

    public Long getTrQuantity() {
        return mTrQuantity;
    }

    public void setTrQuantity(Long trQuantity) {
        mTrQuantity = trQuantity;
    }

    public String getUOM() {
        return mUOM;
    }

    public void setUOM(String uOM) {
        mUOM = uOM;
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

    public String getWeight() {
        return mWeight;
    }

    public void setWeight(String weight) {
        mWeight = weight;
    }

    public String getZummixSkuNumber() {
        return mZummixSkuNumber;
    }

    public void setZummixSkuNumber(String zummixSkuNumber) {
        mZummixSkuNumber = zummixSkuNumber;
    }

}
