
package com.example.colors2web.zummix_app.POJO.ProductSearch;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class InventoryLogs implements Parcelable{

    @SerializedName("aisle_id")
    private Long mAisleId;
    @SerializedName("bin_id")
    private Long mBinId;
    @SerializedName("bin_level_id")
    private Long mBinLevelId;
    @SerializedName("comment")
    private String mComment;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("created_by")
    private Long mCreatedBy;
    @SerializedName("current_qty")
    private Long mCurrentQty;
    @SerializedName("customer_id")
    private Long mCustomerId;
    @SerializedName("event_type")
    private String mEventType;
    @SerializedName("final_A2S")
    private Long mFinalA2S;
    @SerializedName("final_pick")
    private Long mFinalPick;
    @SerializedName("final_qoh")
    private Long mFinalQoh;
    @SerializedName("final_replenish")
    private Long mFinalReplenish;
    @SerializedName("first_name")
    private String mFirstName;
    @SerializedName("from_location")
    private String mFromLocation;
    @SerializedName("id")
    private Long mId;
    @SerializedName("item_id")
    private Long mItemId;
    @SerializedName("item_sku")
    private String mItemSku;
    @SerializedName("last_name")
    private String mLastName;
    @SerializedName("new_pallet_number")
    private Long mNewPalletNumber;
    @SerializedName("old_pallet_number")
    private Long mOldPalletNumber;
    @SerializedName("replenish_bin_id")
    private Long mReplenishBinId;
    @SerializedName("to_aisle_id")
    private Long mToAisleId;
    @SerializedName("to_bin_id")
    private Long mToBinId;
    @SerializedName("to_bin_level_id")
    private Long mToBinLevelId;
    @SerializedName("to_location")
    private String mToLocation;
    @SerializedName("to_replenish_bin_id")
    private Long mToReplenishBinId;
    @SerializedName("to_warehouse_id")
    private Long mToWarehouseId;
    @SerializedName("total_qty")
    private Long mTotalQty;
    @SerializedName("update_qty")
    private Long mUpdateQty;
    @SerializedName("updated_at")
    private String mUpdatedAt;
    @SerializedName("updated_by")
    private Long mUpdatedBy;
    @SerializedName("warehouse_id")
    private Long mWarehouseId;

    public InventoryLogs() {

    }

    public InventoryLogs(Parcel in) {
        if (in.readByte() == 0) {
            mAisleId = null;
        } else {
            mAisleId = in.readLong();
        }
        if (in.readByte() == 0) {
            mBinId = null;
        } else {
            mBinId = in.readLong();
        }
        if (in.readByte() == 0) {
            mBinLevelId = null;
        } else {
            mBinLevelId = in.readLong();
        }
        mComment = in.readString();
        mCreatedAt = in.readString();
        if (in.readByte() == 0) {
            mCreatedBy = null;
        } else {
            mCreatedBy = in.readLong();
        }
        if (in.readByte() == 0) {
            mCurrentQty = null;
        } else {
            mCurrentQty = in.readLong();
        }
        if (in.readByte() == 0) {
            mCustomerId = null;
        } else {
            mCustomerId = in.readLong();
        }
        mEventType = in.readString();
        if (in.readByte() == 0) {
            mFinalA2S = null;
        } else {
            mFinalA2S = in.readLong();
        }
        if (in.readByte() == 0) {
            mFinalPick = null;
        } else {
            mFinalPick = in.readLong();
        }
        if (in.readByte() == 0) {
            mFinalQoh = null;
        } else {
            mFinalQoh = in.readLong();
        }
        if (in.readByte() == 0) {
            mFinalReplenish = null;
        } else {
            mFinalReplenish = in.readLong();
        }
        mFirstName = in.readString();
        mFromLocation = in.readString();
        if (in.readByte() == 0) {
            mId = null;
        } else {
            mId = in.readLong();
        }
        if (in.readByte() == 0) {
            mItemId = null;
        } else {
            mItemId = in.readLong();
        }
        mItemSku = in.readString();
        mLastName = in.readString();
        if (in.readByte() == 0) {
            mNewPalletNumber = null;
        } else {
            mNewPalletNumber = in.readLong();
        }
        if (in.readByte() == 0) {
            mOldPalletNumber = null;
        } else {
            mOldPalletNumber = in.readLong();
        }
        if (in.readByte() == 0) {
            mReplenishBinId = null;
        } else {
            mReplenishBinId = in.readLong();
        }
        if (in.readByte() == 0) {
            mToAisleId = null;
        } else {
            mToAisleId = in.readLong();
        }
        if (in.readByte() == 0) {
            mToBinId = null;
        } else {
            mToBinId = in.readLong();
        }
        if (in.readByte() == 0) {
            mToBinLevelId = null;
        } else {
            mToBinLevelId = in.readLong();
        }
        mToLocation = in.readString();
        if (in.readByte() == 0) {
            mToReplenishBinId = null;
        } else {
            mToReplenishBinId = in.readLong();
        }
        if (in.readByte() == 0) {
            mToWarehouseId = null;
        } else {
            mToWarehouseId = in.readLong();
        }
        if (in.readByte() == 0) {
            mTotalQty = null;
        } else {
            mTotalQty = in.readLong();
        }
        if (in.readByte() == 0) {
            mUpdateQty = null;
        } else {
            mUpdateQty = in.readLong();
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
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (mAisleId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mAisleId);
        }
        if (mBinId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mBinId);
        }
        if (mBinLevelId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mBinLevelId);
        }
        dest.writeString(mComment);
        dest.writeString(mCreatedAt);
        if (mCreatedBy == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mCreatedBy);
        }
        if (mCurrentQty == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mCurrentQty);
        }
        if (mCustomerId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mCustomerId);
        }
        dest.writeString(mEventType);
        if (mFinalA2S == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mFinalA2S);
        }
        if (mFinalPick == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mFinalPick);
        }
        if (mFinalQoh == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mFinalQoh);
        }
        if (mFinalReplenish == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mFinalReplenish);
        }
        dest.writeString(mFirstName);
        dest.writeString(mFromLocation);
        if (mId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mId);
        }
        if (mItemId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mItemId);
        }
        dest.writeString(mItemSku);
        dest.writeString(mLastName);
        if (mNewPalletNumber == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mNewPalletNumber);
        }
        if (mOldPalletNumber == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mOldPalletNumber);
        }
        if (mReplenishBinId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mReplenishBinId);
        }
        if (mToAisleId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mToAisleId);
        }
        if (mToBinId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mToBinId);
        }
        if (mToBinLevelId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mToBinLevelId);
        }
        dest.writeString(mToLocation);
        if (mToReplenishBinId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mToReplenishBinId);
        }
        if (mToWarehouseId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mToWarehouseId);
        }
        if (mTotalQty == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mTotalQty);
        }
        if (mUpdateQty == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mUpdateQty);
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
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<InventoryLogs> CREATOR = new Creator<InventoryLogs>() {
        @Override
        public InventoryLogs createFromParcel(Parcel in) {
            return new InventoryLogs(in);
        }

        @Override
        public InventoryLogs[] newArray(int size) {
            return new InventoryLogs[size];
        }
    };

    public Long getAisleId() {
        return mAisleId;
    }

    public void setAisleId(Long aisleId) {
        mAisleId = aisleId;
    }

    public Long getBinId() {
        return mBinId;
    }

    public void setBinId(Long binId) {
        mBinId = binId;
    }

    public Long getBinLevelId() {
        return mBinLevelId;
    }

    public void setBinLevelId(Long binLevelId) {
        mBinLevelId = binLevelId;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
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

    public Long getCurrentQty() {
        return mCurrentQty;
    }

    public void setCurrentQty(Long currentQty) {
        mCurrentQty = currentQty;
    }

    public Long getCustomerId() {
        return mCustomerId;
    }

    public void setCustomerId(Long customerId) {
        mCustomerId = customerId;
    }

    public String getEventType() {
        return mEventType;
    }

    public void setEventType(String eventType) {
        mEventType = eventType;
    }

    public Long getFinalA2S() {
        return mFinalA2S;
    }

    public void setFinalA2S(Long finalA2S) {
        mFinalA2S = finalA2S;
    }

    public Long getFinalPick() {
        return mFinalPick;
    }

    public void setFinalPick(Long finalPick) {
        mFinalPick = finalPick;
    }

    public Long getFinalQoh() {
        return mFinalQoh;
    }

    public void setFinalQoh(Long finalQoh) {
        mFinalQoh = finalQoh;
    }

    public Long getFinalReplenish() {
        return mFinalReplenish;
    }

    public void setFinalReplenish(Long finalReplenish) {
        mFinalReplenish = finalReplenish;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getFromLocation() {
        return mFromLocation;
    }

    public void setFromLocation(String fromLocation) {
        mFromLocation = fromLocation;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Long getItemId() {
        return mItemId;
    }

    public void setItemId(Long itemId) {
        mItemId = itemId;
    }

    public String getItemSku() {
        return mItemSku;
    }

    public void setItemSku(String itemSku) {
        mItemSku = itemSku;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public Long getNewPalletNumber() {
        return mNewPalletNumber;
    }

    public void setNewPalletNumber(Long newPalletNumber) {
        mNewPalletNumber = newPalletNumber;
    }

    public Long getOldPalletNumber() {
        return mOldPalletNumber;
    }

    public void setOldPalletNumber(Long oldPalletNumber) {
        mOldPalletNumber = oldPalletNumber;
    }

    public Long getReplenishBinId() {
        return mReplenishBinId;
    }

    public void setReplenishBinId(Long replenishBinId) {
        mReplenishBinId = replenishBinId;
    }

    public Long getToAisleId() {
        return mToAisleId;
    }

    public void setToAisleId(Long toAisleId) {
        mToAisleId = toAisleId;
    }

    public Long getToBinId() {
        return mToBinId;
    }

    public void setToBinId(Long toBinId) {
        mToBinId = toBinId;
    }

    public Long getToBinLevelId() {
        return mToBinLevelId;
    }

    public void setToBinLevelId(Long toBinLevelId) {
        mToBinLevelId = toBinLevelId;
    }

    public String getToLocation() {
        return mToLocation;
    }

    public void setToLocation(String toLocation) {
        mToLocation = toLocation;
    }

    public Long getToReplenishBinId() {
        return mToReplenishBinId;
    }

    public void setToReplenishBinId(Long toReplenishBinId) {
        mToReplenishBinId = toReplenishBinId;
    }

    public Long getToWarehouseId() {
        return mToWarehouseId;
    }

    public void setToWarehouseId(Long toWarehouseId) {
        mToWarehouseId = toWarehouseId;
    }

    public Long getTotalQty() {
        return mTotalQty;
    }

    public void setTotalQty(Long totalQty) {
        mTotalQty = totalQty;
    }

    public Long getUpdateQty() {
        return mUpdateQty;
    }

    public void setUpdateQty(Long updateQty) {
        mUpdateQty = updateQty;
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

}
