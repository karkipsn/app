
package com.example.colors2web.zummix_app.POJO.Order2POJO;


import com.google.gson.annotations.SerializedName;


public class ItemDetail {

    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("created_by")
    private Long mCreatedBy;
    @SerializedName("customer_item_name")
    private String mCustomerItemName;
    @SerializedName("id")
    private Long mId;
    @SerializedName("is_refunded")
    private String mIsRefunded;
    @SerializedName("is_special_program")
    private String mIsSpecialProgram;
    @SerializedName("item_image")
    private String mItemImage;
    @SerializedName("item_name")
    private String mItemName;
    @SerializedName("item_quantity")
    private Long mItemQuantity;
    @SerializedName("item_sku")
    private String mItemSku;
    @SerializedName("order_id")
    private Long mOrderId;
    @SerializedName("pick")
    private String mPick;
    @SerializedName("picked")
    private String mPicked;
    @SerializedName("remaining_quantity_to_be_boxed")
    private Long mRemainingQuantityToBeBoxed;
    @SerializedName("replenish")
    private Object mReplenish;
    @SerializedName("special_program_number")
    private String mSpecialProgramNumber;
    @SerializedName("updated_at")
    private String mUpdatedAt;
    @SerializedName("updated_by")
    private Long mUpdatedBy;

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

    public String getCustomerItemName() {
        return mCustomerItemName;
    }

    public void setCustomerItemName(String customerItemName) {
        mCustomerItemName = customerItemName;
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

    public String getIsSpecialProgram() {
        return mIsSpecialProgram;
    }

    public void setIsSpecialProgram(String isSpecialProgram) {
        mIsSpecialProgram = isSpecialProgram;
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

    public Long getItemQuantity() {
        return mItemQuantity;
    }

    public void setItemQuantity(Long itemQuantity) {
        mItemQuantity = itemQuantity;
    }

    public String getItemSku() {
        return mItemSku;
    }

    public void setItemSku(String itemSku) {
        mItemSku = itemSku;
    }

    public Long getOrderId() {
        return mOrderId;
    }

    public void setOrderId(Long orderId) {
        mOrderId = orderId;
    }

    public String getPick() {
        return mPick;
    }

    public void setPick(String pick) {
        mPick = pick;
    }

    public String getPicked() {
        return mPicked;
    }

    public void setPicked(String picked) {
        mPicked = picked;
    }

    public Long getRemainingQuantityToBeBoxed() {
        return mRemainingQuantityToBeBoxed;
    }

    public void setRemainingQuantityToBeBoxed(Long remainingQuantityToBeBoxed) {
        mRemainingQuantityToBeBoxed = remainingQuantityToBeBoxed;
    }

    public Object getReplenish() {
        return mReplenish;
    }

    public void setReplenish(Object replenish) {
        mReplenish = replenish;
    }

    public String getSpecialProgramNumber() {
        return mSpecialProgramNumber;
    }

    public void setSpecialProgramNumber(String specialProgramNumber) {
        mSpecialProgramNumber = specialProgramNumber;
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

}
