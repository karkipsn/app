
package com.example.colors2web.zummix_app.POJO.ProductSearch;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class DrShipmentItemLocations implements Parcelable{

    @SerializedName("aisle_id")
    private Long mAisleId;
    @SerializedName("aisle_name")
    private String mAisleName;
    @SerializedName("bin")
    private String mBin;
    @SerializedName("bin_id")
    private Long mBinId;
    @SerializedName("bin_level")
    private String mBinLevel;
    @SerializedName("bin_level_id")
    private Long mBinLevelId;
    @SerializedName("bin_number")
    private String mBinNumber;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("created_by")
    private Long mCreatedBy;
    @SerializedName("customer_id")
    private Long mCustomerId;
    @SerializedName("dr_shipment_id")
    private Long mDrShipmentId;
    @SerializedName("dr_shipment_item_id")
    private Long mDrShipmentItemId;
    @SerializedName("exceeded_item_count")
    private Long mExceededItemCount;
    @SerializedName("id")
    private Long mId;
    @SerializedName("is_case")
    private Long mIsCase;
    @SerializedName("item_count")
    private Long mItemCount;
    @SerializedName("item_sku_number")
    private String mItemSkuNumber;
    @SerializedName("lot_number_id")
    private Long mLotNumberId;
    @SerializedName("pallet_count")
    private String mPalletCount;
    @SerializedName("replenish_bin_id")
    private Long mReplenishBinId;
    @SerializedName("temp_case_id")
    private Long mTempCaseId;
    @SerializedName("updated_at")
    private String mUpdatedAt;
    @SerializedName("updated_by")
    private Long mUpdatedBy;
    @SerializedName("warehouse_id")
    private Long mWarehouseId;
    @SerializedName("warehouse_name")
    private String mWarehouseName;

    public DrShipmentItemLocations() {

    }

    protected DrShipmentItemLocations(Parcel in) {
        if (in.readByte() == 0) {
            mAisleId = null;
        } else {
            mAisleId = in.readLong();
        }
        mAisleName = in.readString();
        mBin = in.readString();
        if (in.readByte() == 0) {
            mBinId = null;
        } else {
            mBinId = in.readLong();
        }
        mBinLevel = in.readString();
        if (in.readByte() == 0) {
            mBinLevelId = null;
        } else {
            mBinLevelId = in.readLong();
        }
        mBinNumber = in.readString();
        mCreatedAt = in.readString();
        if (in.readByte() == 0) {
            mCreatedBy = null;
        } else {
            mCreatedBy = in.readLong();
        }
        if (in.readByte() == 0) {
            mCustomerId = null;
        } else {
            mCustomerId = in.readLong();
        }
        if (in.readByte() == 0) {
            mDrShipmentId = null;
        } else {
            mDrShipmentId = in.readLong();
        }
        if (in.readByte() == 0) {
            mDrShipmentItemId = null;
        } else {
            mDrShipmentItemId = in.readLong();
        }
        if (in.readByte() == 0) {
            mExceededItemCount = null;
        } else {
            mExceededItemCount = in.readLong();
        }
        if (in.readByte() == 0) {
            mId = null;
        } else {
            mId = in.readLong();
        }
        if (in.readByte() == 0) {
            mIsCase = null;
        } else {
            mIsCase = in.readLong();
        }
        if (in.readByte() == 0) {
            mItemCount = null;
        } else {
            mItemCount = in.readLong();
        }
        mItemSkuNumber = in.readString();
        if (in.readByte() == 0) {
            mLotNumberId = null;
        } else {
            mLotNumberId = in.readLong();
        }
        mPalletCount = in.readString();
        if (in.readByte() == 0) {
            mReplenishBinId = null;
        } else {
            mReplenishBinId = in.readLong();
        }
        if (in.readByte() == 0) {
            mTempCaseId = null;
        } else {
            mTempCaseId = in.readLong();
        }
        mUpdatedAt = in.readString();
        if (in.readByte() == 0) {
            mUpdatedBy = null;
        } else {
            mUpdatedBy = in.readLong();
        }
        if (in.readByte() == 0) {
            mWarehouseId = null;
        } else {
            mWarehouseId = in.readLong();
        }
        mWarehouseName = in.readString();
    }

    public static final Creator<DrShipmentItemLocations> CREATOR = new Creator<DrShipmentItemLocations>() {
        @Override
        public DrShipmentItemLocations createFromParcel(Parcel in) {
            return new DrShipmentItemLocations(in);
        }

        @Override
        public DrShipmentItemLocations[] newArray(int size) {
            return new DrShipmentItemLocations[size];
        }
    };

    public Long getAisleId() {
        return mAisleId;
    }

    public void setAisleId(Long aisleId) {
        mAisleId = aisleId;
    }

    public String getAisleName() {
        return mAisleName;
    }

    public void setAisleName(String aisleName) {
        mAisleName = aisleName;
    }

    public String getBin() {
        return mBin;
    }

    public void setBin(String bin) {
        mBin = bin;
    }

    public Long getBinId() {
        return mBinId;
    }

    public void setBinId(Long binId) {
        mBinId = binId;
    }

    public String getBinLevel() {
        return mBinLevel;
    }

    public void setBinLevel(String binLevel) {
        mBinLevel = binLevel;
    }

    public Long getBinLevelId() {
        return mBinLevelId;
    }

    public void setBinLevelId(Long binLevelId) {
        mBinLevelId = binLevelId;
    }

    public String getBinNumber() {
        return mBinNumber;
    }

    public void setBinNumber(String binNumber) {
        mBinNumber = binNumber;
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

    public Long getDrShipmentId() {
        return mDrShipmentId;
    }

    public void setDrShipmentId(Long drShipmentId) {
        mDrShipmentId = drShipmentId;
    }

    public Long getDrShipmentItemId() {
        return mDrShipmentItemId;
    }

    public void setDrShipmentItemId(Long drShipmentItemId) {
        mDrShipmentItemId = drShipmentItemId;
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

    public Long getIsCase() {
        return mIsCase;
    }

    public void setIsCase(Long isCase) {
        mIsCase = isCase;
    }

    public Long getItemCount() {
        return mItemCount;
    }

    public void setItemCount(Long itemCount) {
        mItemCount = itemCount;
    }

    public String getItemSkuNumber() {
        return mItemSkuNumber;
    }

    public void setItemSkuNumber(String itemSkuNumber) {
        mItemSkuNumber = itemSkuNumber;
    }

    public Long getLotNumberId() {
        return mLotNumberId;
    }

    public void setLotNumberId(Long lotNumberId) {
        mLotNumberId = lotNumberId;
    }

    public String getPalletCount() {
        return mPalletCount;
    }

    public void setPalletCount(String palletCount) {
        mPalletCount = palletCount;
    }

    public Long getReplenishBinId() {
        return mReplenishBinId;
    }

    public void setReplenishBinId(Long replenishBinId) {
        mReplenishBinId = replenishBinId;
    }

    public Long getTempCaseId() {
        return mTempCaseId;
    }

    public void setTempCaseId(Long tempCaseId) {
        mTempCaseId = tempCaseId;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (mAisleId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mAisleId);
        }
        dest.writeString(mAisleName);
        dest.writeString(mBin);
        if (mBinId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mBinId);
        }
        dest.writeString(mBinLevel);
        if (mBinLevelId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mBinLevelId);
        }
        dest.writeString(mBinNumber);
        dest.writeString(mCreatedAt);
        if (mCreatedBy == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mCreatedBy);
        }
        if (mCustomerId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mCustomerId);
        }
        if (mDrShipmentId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mDrShipmentId);
        }
        if (mDrShipmentItemId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mDrShipmentItemId);
        }
        if (mExceededItemCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mExceededItemCount);
        }
        if (mId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mId);
        }
        if (mIsCase == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mIsCase);
        }
        if (mItemCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mItemCount);
        }
        dest.writeString(mItemSkuNumber);
        if (mLotNumberId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mLotNumberId);
        }
        dest.writeString(mPalletCount);
        if (mReplenishBinId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mReplenishBinId);
        }
        if (mTempCaseId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mTempCaseId);
        }
        dest.writeString(mUpdatedAt);
        if (mUpdatedBy == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mUpdatedBy);
        }
        if (mWarehouseId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mWarehouseId);
        }
        dest.writeString(mWarehouseName);
    }
}
