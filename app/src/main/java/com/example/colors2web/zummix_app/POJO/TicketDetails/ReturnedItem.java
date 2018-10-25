
package com.example.colors2web.zummix_app.POJO.TicketDetails;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ReturnedItem implements Parcelable {

    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("id")
    private Long mId;
    @SerializedName("product_name")
    private String mProductName;
    @SerializedName("quantity")
    private Long mQuantity;
    @SerializedName("reason")
    private String mReason;
    @SerializedName("rma_number")
    private String mRmaNumber;
    @SerializedName("sale_number")
    private String mSaleNumber;
    @SerializedName("sku_number")
    private String mSkuNumber;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("ticket_number")
    private String mTicketNumber;
    @SerializedName("updated_at")
    private String mUpdatedAt;

    public ReturnedItem() {
    }

    protected ReturnedItem(Parcel in) {
        mCreatedAt = in.readString();
        if (in.readByte() == 0) {
            mId = null;
        } else {
            mId = in.readLong();
        }
        mProductName = in.readString();
        if (in.readByte() == 0) {
            mQuantity = null;
        } else {
            mQuantity = in.readLong();
        }
        mReason = in.readString();
        mRmaNumber = in.readString();
        mSaleNumber = in.readString();
        mSkuNumber = in.readString();
        mStatus = in.readString();
        mTicketNumber = in.readString();
        mUpdatedAt = in.readString();
    }

    public static final Creator<ReturnedItem> CREATOR = new Creator<ReturnedItem>() {
        @Override
        public ReturnedItem createFromParcel(Parcel in) {
            return new ReturnedItem(in);
        }

        @Override
        public ReturnedItem[] newArray(int size) {
            return new ReturnedItem[size];
        }
    };

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getProductName() {
        return mProductName;
    }

    public void setProductName(String productName) {
        mProductName = productName;
    }

    public Long getQuantity() {
        return mQuantity;
    }

    public void setQuantity(Long quantity) {
        mQuantity = quantity;
    }

    public String getReason() {
        return mReason;
    }

    public void setReason(String reason) {
        mReason = reason;
    }

    public String getRmaNumber() {
        return mRmaNumber;
    }

    public void setRmaNumber(String rmaNumber) {
        mRmaNumber = rmaNumber;
    }

    public String getSaleNumber() {
        return mSaleNumber;
    }

    public void setSaleNumber(String saleNumber) {
        mSaleNumber = saleNumber;
    }

    public String getSkuNumber() {
        return mSkuNumber;
    }

    public void setSkuNumber(String skuNumber) {
        mSkuNumber = skuNumber;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getTicketNumber() {
        return mTicketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        mTicketNumber = ticketNumber;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mCreatedAt);
        if (mId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mId);
        }
        dest.writeString(mProductName);
        if (mQuantity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mQuantity);
        }
        dest.writeString(mReason);
        dest.writeString(mRmaNumber);
        dest.writeString(mSaleNumber);
        dest.writeString(mSkuNumber);
        dest.writeString(mStatus);
        dest.writeString(mTicketNumber);
        dest.writeString(mUpdatedAt);
    }
}
