
package com.example.colors2web.zummix_app.POJO.PostSearch;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CustomerItems implements Serializable {

    @SerializedName("item_id")
    private Long mItemId;
    @SerializedName("item_name")
    private String mItemName;
    @SerializedName("item_sku_number")
    private String mItemSkuNumber;
    @SerializedName("pick")
    private Long mPick;
    @SerializedName("pick_aisle_name")
    private String mPickAisleName;
    @SerializedName("pick_bin")
    private String mPickBin;
    @SerializedName("replenish")
    private String mReplenish;
    @SerializedName("section_level")
    private String mSectionLevel;
    @SerializedName("section_number")
    private String mSectionNumber;
    @SerializedName("warehouse_name")
    private String mWarehouseName;

    public Long getItemId() {
        return mItemId;
    }

    public void setItemId(Long itemId) {
        mItemId = itemId;
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

    public Long getPick() {
        return mPick;
    }

    public void setPick(Long pick) {
        mPick = pick;
    }

    public String getPickAisleName() {
        return mPickAisleName;
    }

    public void setPickAisleName(String pickAisleName) {
        mPickAisleName = pickAisleName;
    }

    public String getPickBin() {
        return mPickBin;
    }

    public void setPickBin(String pickBin) {
        mPickBin = pickBin;
    }

    public String getReplenish() {
        return mReplenish;
    }

    public void setReplenish(String replenish) {
        mReplenish = replenish;
    }

    public String getSectionLevel() {
        return mSectionLevel;
    }

    public void setSectionLevel(String sectionLevel) {
        mSectionLevel = sectionLevel;
    }

    public String getSectionNumber() {
        return mSectionNumber;
    }

    public void setSectionNumber(String sectionNumber) {
        mSectionNumber = sectionNumber;
    }

    public String getWarehouseName() {
        return mWarehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        mWarehouseName = warehouseName;
    }

}
