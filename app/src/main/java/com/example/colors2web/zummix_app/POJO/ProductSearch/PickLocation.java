
package com.example.colors2web.zummix_app.POJO.ProductSearch;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class PickLocation implements Serializable{

    @SerializedName("pick_aisle_id")
    private Long mPickAisleId;
    @SerializedName("pick_aisle_name")
    private String mPickAisleName;
    @SerializedName("pick_bin")
    private String mPickBin;
    @SerializedName("pick_bin_id")
    private Long mPickBinId;
    @SerializedName("section_id")
    private Long mSectionId;
    @SerializedName("section_level")
    private String mSectionLevel;
    @SerializedName("section_level_id")
    private Long mSectionLevelId;
    @SerializedName("section_number")
    private String mSectionNumber;
    @SerializedName("warehouse_id")
    private Long mWarehouseId;
    @SerializedName("warehouse_name")
    private String mWarehouseName;

    private String picku;

    public String getPicku() {
        return picku;
    }

    public void setPicku(String picku) {
        this.picku = picku;
    }

    public Long getPickAisleId() {
        return mPickAisleId;
    }

    public void setPickAisleId(Long pickAisleId) {
        mPickAisleId = pickAisleId;
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

    public Long getPickBinId() {
        return mPickBinId;
    }

    public void setPickBinId(Long pickBinId) {
        mPickBinId = pickBinId;
    }

    public Long getSectionId() {
        return mSectionId;
    }

    public void setSectionId(Long sectionId) {
        mSectionId = sectionId;
    }

    public String getSectionLevel() {
        return mSectionLevel;
    }

    public void setSectionLevel(String sectionLevel) {
        mSectionLevel = sectionLevel;
    }

    public Long getSectionLevelId() {
        return mSectionLevelId;
    }

    public void setSectionLevelId(Long sectionLevelId) {
        mSectionLevelId = sectionLevelId;
    }

    public String getSectionNumber() {
        return mSectionNumber;
    }

    public void setSectionNumber(String sectionNumber) {
        mSectionNumber = sectionNumber;
    }

    public Long getWarehouseId() {
        return mWarehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        mWarehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return mWarehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        mWarehouseName = warehouseName;
    }

}
