
package com.example.colors2web.zummix_app.POJO.ProblemSKU;

import com.google.gson.annotations.SerializedName;

public class UOM {

    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("created_by")
    private Long mCreatedBy;
    @SerializedName("id")
    private Long mId;
    @SerializedName("unit")
    private String mUnit;
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

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getUnit() {
        return mUnit;
    }

    public void setUnit(String unit) {
        mUnit = unit;
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
