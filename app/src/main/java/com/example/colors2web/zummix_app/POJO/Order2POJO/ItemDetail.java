
package com.example.colors2web.zummix_app.POJO.Order2POJO;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class ItemDetail implements Parcelable {

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

    public ItemDetail() {

    }

    public ItemDetail(Parcel in) {
        mCreatedAt = in.readString();
        if (in.readByte() == 0) {
            mCreatedBy = null;

        } else {
            mCreatedBy = in.readLong();
        }
        mCustomerItemName = in.readString();
        if (in.readByte() == 0) {
            mId = null;
        } else {
            mId = in.readLong();
        }
        mIsRefunded = in.readString();
        mIsSpecialProgram = in.readString();
        mItemImage = in.readString();
        mItemName = in.readString();
        if (in.readByte() == 0) {
            mItemQuantity = null;
        } else {
            mItemQuantity = in.readLong();
        }
        mItemSku = in.readString();
        if (in.readByte() == 0) {
            mOrderId = null;
        } else {
            mOrderId = in.readLong();
        }
        mPick = in.readString();
        mPicked = in.readString();
        if (in.readByte() == 0) {
            mRemainingQuantityToBeBoxed = null;
        } else {
            mRemainingQuantityToBeBoxed = in.readLong();
        }
        mSpecialProgramNumber = in.readString();
        mUpdatedAt = in.readString();
        if (in.readByte() == 0) {
            mUpdatedBy = null;
        } else {
            mUpdatedBy = in.readLong();
        }
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mCreatedAt);
        if (mCreatedBy == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mCreatedBy);
        }
        dest.writeString(mCustomerItemName);
        if (mId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mId);
        }
        dest.writeString(mIsRefunded);
        dest.writeString(mIsSpecialProgram);
        dest.writeString(mItemImage);
        dest.writeString(mItemName);
        if (mItemQuantity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mItemQuantity);
        }
        dest.writeString(mItemSku);
        if (mOrderId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mOrderId);
        }
        dest.writeString(mPick);
        dest.writeString(mPicked);
        if (mRemainingQuantityToBeBoxed == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mRemainingQuantityToBeBoxed);
        }
        dest.writeString(mSpecialProgramNumber);
        dest.writeString(mUpdatedAt);
        if (mUpdatedBy == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mUpdatedBy);
        }
    }
    public static final Creator<ItemDetail> CREATOR = new Creator<ItemDetail>() {
        @Override
        public ItemDetail createFromParcel(Parcel in) {
            return new ItemDetail(in);
        }

        @Override
        public ItemDetail[] newArray(int size) {
            return new ItemDetail[size];
        }
    };
}
