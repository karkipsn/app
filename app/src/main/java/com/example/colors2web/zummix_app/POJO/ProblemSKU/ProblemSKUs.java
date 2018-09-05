
package com.example.colors2web.zummix_app.POJO.ProblemSKU;

import com.google.gson.annotations.SerializedName;

public class ProblemSKUs {

    @SerializedName("company_name")
    private String mCompanyName;
    @SerializedName("country")
    private String mCountry;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("customer_id")
    private Long mCustomerId;
    @SerializedName("id")
    private Long mId;
    @SerializedName("item_name")
    private String mItemName;
    @SerializedName("item_sku")
    private String mItemSku;
    @SerializedName("orderQty")
    private String mOrderQty;
    @SerializedName("price")
    private String mPrice;
    @SerializedName("totalOrder")
    private String mTotalOrder;
    @SerializedName("uom")
    private String mUom;
    @SerializedName("updated_at")
    private String mUpdatedAt;
    @SerializedName("weight")
    private String mWeight;

    public String getCompanyName() {
        return mCompanyName;
    }

    public void setCompanyName(String companyName) {
        mCompanyName = companyName;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
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

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getItemName() {
        return mItemName;
    }

    public void setItemName(String itemName) {
        mItemName = itemName;
    }

    public String getItemSku() {
        return mItemSku;
    }

    public void setItemSku(String itemSku) {
        mItemSku = itemSku;
    }

    public String getOrderQty() {
        return mOrderQty;
    }

    public void setOrderQty(String orderQty) {
        mOrderQty = orderQty;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public String getTotalOrder() {
        return mTotalOrder;
    }

    public void setTotalOrder(String totalOrder) {
        mTotalOrder = totalOrder;
    }

    public String getUom() {
        return mUom;
    }

    public void setUom(String uom) {
        mUom = uom;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public String getWeight() {
        return mWeight;
    }

    public void setWeight(String weight) {
        mWeight = weight;
    }

}
