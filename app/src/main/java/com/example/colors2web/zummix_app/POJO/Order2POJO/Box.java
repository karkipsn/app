
package com.example.colors2web.zummix_app.POJO.Order2POJO;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Box implements Parcelable{

    public Box() {

    }

    @SerializedName("auxiallary_label")

    private String mAuxiallaryLabel;
    @SerializedName("barcode_file_name")
    private String mBarcodeFileName;
    @SerializedName("barcode_number")
    private String mBarcodeNumber;
    @SerializedName("box_number")
    private String mBoxNumber;
    @SerializedName("box_quantity")
    private Long mBoxQuantity;
    @SerializedName("box_type")
    private String mBoxType;
    @SerializedName("city_bin")
    private Object mCityBin;
    @SerializedName("city_bin_id")
    private Object mCityBinId;
    @SerializedName("commercial_invoice")
    private String mCommercialInvoice;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("created_by")
    private Long mCreatedBy;
    @SerializedName("customer_barcode_file_name")
    private String mCustomerBarcodeFileName;
    @SerializedName("first_name")
    private String mFirstName;
    @SerializedName("height")
    private String mHeight;
    @SerializedName("id")
    private Long mId;
    @SerializedName("is_pallet_completed")
    private Object mIsPalletCompleted;
    @SerializedName("item_name")
    private String mItemName;
    @SerializedName("item_sku")
    private String mItemSku;
    @SerializedName("last_name")
    private String mLastName;
    @SerializedName("length")
    private String mLength;
    @SerializedName("markup_percentage")
    private String mMarkupPercentage;
    @SerializedName("master_box_code")
    private String mMasterBoxCode;
    @SerializedName("order_id")
    private Long mOrderId;
    @SerializedName("ordered_quantity")
    private Long mOrderedQuantity;
    @SerializedName("pallet_barcode_file_name")
    private Object mPalletBarcodeFileName;
    @SerializedName("pallet_number")
    private Object mPalletNumber;
    @SerializedName("product_id")
    private Long mProductId;
    @SerializedName("reason_for_shipping_outside")
    private String mReasonForShippingOutside;
    @SerializedName("remaining_quantity")
    private Long mRemainingQuantity;
    @SerializedName("shipper")
    private String mShipper;
    @SerializedName("shipping_company")
    private String mShippingCompany;
    @SerializedName("shipping_cost")
    private String mShippingCost;
    @SerializedName("shipping_label")
    private String mShippingLabel;
    @SerializedName("shipping_method")
    private String mShippingMethod;
    @SerializedName("tracking_code")
    private String mTrackingCode;
    @SerializedName("updated_at")
    private String mUpdatedAt;
    @SerializedName("updated_by")
    private Long mUpdatedBy;
    @SerializedName("weight")
    private String mWeight;
    @SerializedName("width")
    private String mWidth;
    @SerializedName("zummix_shipping_cost")
    private String mZummixShippingCost;

    public Box(Parcel in) {
        mAuxiallaryLabel = in.readString();
        mBarcodeFileName = in.readString();
        mBarcodeNumber = in.readString();
        mBoxNumber = in.readString();
        if (in.readByte() == 0) {
            mBoxQuantity = null;
        } else {
            mBoxQuantity = in.readLong();
        }
        mBoxType = in.readString();
        mCommercialInvoice = in.readString();
        mCreatedAt = in.readString();
        if (in.readByte() == 0) {
            mCreatedBy = null;
        } else {
            mCreatedBy = in.readLong();
        }
        mCustomerBarcodeFileName = in.readString();
        mFirstName = in.readString();
        mHeight = in.readString();
        if (in.readByte() == 0) {
            mId = null;
        } else {
            mId = in.readLong();
        }
        mItemName = in.readString();
        mItemSku = in.readString();
        mLastName = in.readString();
        mLength = in.readString();
        mMarkupPercentage = in.readString();
        mMasterBoxCode = in.readString();
        if (in.readByte() == 0) {
            mOrderId = null;
        } else {
            mOrderId = in.readLong();
        }
        if (in.readByte() == 0) {
            mOrderedQuantity = null;
        } else {
            mOrderedQuantity = in.readLong();
        }
        if (in.readByte() == 0) {
            mProductId = null;
        } else {
            mProductId = in.readLong();
        }
        mReasonForShippingOutside = in.readString();
        if (in.readByte() == 0) {
            mRemainingQuantity = null;
        } else {
            mRemainingQuantity = in.readLong();
        }
        mShipper = in.readString();
        mShippingCompany = in.readString();
        mShippingCost = in.readString();
        mShippingLabel = in.readString();
        mShippingMethod = in.readString();
        mTrackingCode = in.readString();
        mUpdatedAt = in.readString();
        if (in.readByte() == 0) {
            mUpdatedBy = null;
        } else {
            mUpdatedBy = in.readLong();
        }
        mWeight = in.readString();
        mWidth = in.readString();
        mZummixShippingCost = in.readString();
    }

    public static final Creator<Box> CREATOR = new Creator<Box>() {
        @Override
        public Box createFromParcel(Parcel in) {
            return new Box(in);
        }

        @Override
        public Box[] newArray(int size) {
            return new Box[size];
        }
    };

    public String getAuxiallaryLabel() {
        return mAuxiallaryLabel;
    }

    public void setAuxiallaryLabel(String auxiallaryLabel) {
        mAuxiallaryLabel = auxiallaryLabel;
    }

    public String getBarcodeFileName() {
        return mBarcodeFileName;
    }

    public void setBarcodeFileName(String barcodeFileName) {
        mBarcodeFileName = barcodeFileName;
    }

    public String getBarcodeNumber() {
        return mBarcodeNumber;
    }

    public void setBarcodeNumber(String barcodeNumber) {
        mBarcodeNumber = barcodeNumber;
    }

    public String getBoxNumber() {
        return mBoxNumber;
    }

    public void setBoxNumber(String boxNumber) {
        mBoxNumber = boxNumber;
    }

    public Long getBoxQuantity() {
        return mBoxQuantity;
    }

    public void setBoxQuantity(Long boxQuantity) {
        mBoxQuantity = boxQuantity;
    }

    public String getBoxType() {
        return mBoxType;
    }

    public void setBoxType(String boxType) {
        mBoxType = boxType;
    }

    public Object getCityBin() {
        return mCityBin;
    }

    public void setCityBin(Object cityBin) {
        mCityBin = cityBin;
    }

    public Object getCityBinId() {
        return mCityBinId;
    }

    public void setCityBinId(Object cityBinId) {
        mCityBinId = cityBinId;
    }

    public String getCommercialInvoice() {
        return mCommercialInvoice;
    }

    public void setCommercialInvoice(String commercialInvoice) {
        mCommercialInvoice = commercialInvoice;
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

    public String getCustomerBarcodeFileName() {
        return mCustomerBarcodeFileName;
    }

    public void setCustomerBarcodeFileName(String customerBarcodeFileName) {
        mCustomerBarcodeFileName = customerBarcodeFileName;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getHeight() {
        return mHeight;
    }

    public void setHeight(String height) {
        mHeight = height;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Object getIsPalletCompleted() {
        return mIsPalletCompleted;
    }

    public void setIsPalletCompleted(Object isPalletCompleted) {
        mIsPalletCompleted = isPalletCompleted;
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

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getLength() {
        return mLength;
    }

    public void setLength(String length) {
        mLength = length;
    }

    public String getMarkupPercentage() {
        return mMarkupPercentage;
    }

    public void setMarkupPercentage(String markupPercentage) {
        mMarkupPercentage = markupPercentage;
    }

    public String getMasterBoxCode() {
        return mMasterBoxCode;
    }

    public void setMasterBoxCode(String masterBoxCode) {
        mMasterBoxCode = masterBoxCode;
    }

    public Long getOrderId() {
        return mOrderId;
    }

    public void setOrderId(Long orderId) {
        mOrderId = orderId;
    }

    public Long getOrderedQuantity() {
        return mOrderedQuantity;
    }

    public void setOrderedQuantity(Long orderedQuantity) {
        mOrderedQuantity = orderedQuantity;
    }

    public Object getPalletBarcodeFileName() {
        return mPalletBarcodeFileName;
    }

    public void setPalletBarcodeFileName(Object palletBarcodeFileName) {
        mPalletBarcodeFileName = palletBarcodeFileName;
    }

    public Object getPalletNumber() {
        return mPalletNumber;
    }

    public void setPalletNumber(Object palletNumber) {
        mPalletNumber = palletNumber;
    }

    public Long getProductId() {
        return mProductId;
    }

    public void setProductId(Long productId) {
        mProductId = productId;
    }

    public String getReasonForShippingOutside() {
        return mReasonForShippingOutside;
    }

    public void setReasonForShippingOutside(String reasonForShippingOutside) {
        mReasonForShippingOutside = reasonForShippingOutside;
    }

    public Long getRemainingQuantity() {
        return mRemainingQuantity;
    }

    public void setRemainingQuantity(Long remainingQuantity) {
        mRemainingQuantity = remainingQuantity;
    }

    public String getShipper() {
        return mShipper;
    }

    public void setShipper(String shipper) {
        mShipper = shipper;
    }

    public String getShippingCompany() {
        return mShippingCompany;
    }

    public void setShippingCompany(String shippingCompany) {
        mShippingCompany = shippingCompany;
    }

    public String getShippingCost() {
        return mShippingCost;
    }

    public void setShippingCost(String shippingCost) {
        mShippingCost = shippingCost;
    }

    public String getShippingLabel() {
        return mShippingLabel;
    }

    public void setShippingLabel(String shippingLabel) {
        mShippingLabel = shippingLabel;
    }

    public String getShippingMethod() {
        return mShippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        mShippingMethod = shippingMethod;
    }

    public String getTrackingCode() {
        return mTrackingCode;
    }

    public void setTrackingCode(String trackingCode) {
        mTrackingCode = trackingCode;
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

    public String getWidth() {
        return mWidth;
    }

    public void setWidth(String width) {
        mWidth = width;
    }

    public String getZummixShippingCost() {
        return mZummixShippingCost;
    }

    public void setZummixShippingCost(String zummixShippingCost) {
        mZummixShippingCost = zummixShippingCost;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mAuxiallaryLabel);
        dest.writeString(mBarcodeFileName);
        dest.writeString(mBarcodeNumber);
        dest.writeString(mBoxNumber);
        if (mBoxQuantity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mBoxQuantity);
        }
        dest.writeString(mBoxType);
        dest.writeString(mCommercialInvoice);
        dest.writeString(mCreatedAt);
        if (mCreatedBy == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mCreatedBy);
        }
        dest.writeString(mCustomerBarcodeFileName);
        dest.writeString(mFirstName);
        dest.writeString(mHeight);
        if (mId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mId);
        }
        dest.writeString(mItemName);
        dest.writeString(mItemSku);
        dest.writeString(mLastName);
        dest.writeString(mLength);
        dest.writeString(mMarkupPercentage);
        dest.writeString(mMasterBoxCode);
        if (mOrderId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mOrderId);
        }
        if (mOrderedQuantity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mOrderedQuantity);
        }
        if (mProductId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mProductId);
        }
        dest.writeString(mReasonForShippingOutside);
        if (mRemainingQuantity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mRemainingQuantity);
        }
        dest.writeString(mShipper);
        dest.writeString(mShippingCompany);
        dest.writeString(mShippingCost);
        dest.writeString(mShippingLabel);
        dest.writeString(mShippingMethod);
        dest.writeString(mTrackingCode);
        dest.writeString(mUpdatedAt);
        if (mUpdatedBy == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mUpdatedBy);
        }
        dest.writeString(mWeight);
        dest.writeString(mWidth);
        dest.writeString(mZummixShippingCost);
    }
}
