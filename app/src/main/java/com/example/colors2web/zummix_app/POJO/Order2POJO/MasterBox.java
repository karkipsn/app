package com.example.colors2web.zummix_app.POJO.Order2POJO;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MasterBox implements Parcelable {

    @SerializedName("auxiallary_label")
    private String mAuxiallaryLabel;
    @SerializedName("barcode_file_name")
    private String mBarcodeFileName;
    @SerializedName("barcode_number")
    private String mBarcodeNumber;
    @SerializedName("box_number")
    private String mBoxNumber;
    @SerializedName("city_bin")
    private String mCityBin;
    @SerializedName("city_bin_id")
    private Long mCityBinId;
    @SerializedName("commercial_invoice")
    private String mCommercialInvoice;
    @SerializedName("company_email")
    private String mCompanyEmail;
    @SerializedName("company_name")
    private String mCompanyName;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("created_by")
    private Long mCreatedBy;
    @SerializedName("customer_id")
    private Long mCustomerId;
    @SerializedName("height")
    private String mHeight;
    @SerializedName("id")
    private Long mId;
    @SerializedName("is_ready_to_ship")
    private Long mIsReadyToShip;
    @SerializedName("is_special")
    private Long mIsSpecial;
    @SerializedName("is_zummix_ownwed")
    private Long mIsZummixOwnwed;
    @SerializedName("length")
    private String mLength;
    @SerializedName("markup_percentage")
    private String mMarkupPercentage;
    @SerializedName("master_box_code")
    private String mMasterBoxCode;
    @SerializedName("master_box_tracking_code")
    private String mMasterBoxTrackingCode;
    @SerializedName("order_id")
    private String mOrderId;
    @SerializedName("order_number")
    private String mOrderNumber;
    @SerializedName("reason_for_shipping_outside")
    private String mReasonForShippingOutside;
    @SerializedName("ship_method")
    private String mShipMethod;
    @SerializedName("ship_to_email")
    private String mShipToEmail;
    @SerializedName("ship_to_name")
    private String mShipToName;
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

    public MasterBox() {

    }

    protected MasterBox(Parcel in) {
        mAuxiallaryLabel = in.readString();
        mBarcodeFileName = in.readString();
        mBarcodeNumber = in.readString();
        mBoxNumber = in.readString();
        mCityBin = in.readString();
        if (in.readByte() == 0) {
            mCityBinId = null;
        } else {
            mCityBinId = in.readLong();
        }
        mCommercialInvoice = in.readString();
        mCompanyEmail = in.readString();
        mCompanyName = in.readString();
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
        mHeight = in.readString();
        if (in.readByte() == 0) {
            mId = null;
        } else {
            mId = in.readLong();
        }
        if (in.readByte() == 0) {
            mIsReadyToShip = null;
        } else {
            mIsReadyToShip = in.readLong();
        }
        if (in.readByte() == 0) {
            mIsSpecial = null;
        } else {
            mIsSpecial = in.readLong();
        }
        if (in.readByte() == 0) {
            mIsZummixOwnwed = null;
        } else {
            mIsZummixOwnwed = in.readLong();
        }
        mLength = in.readString();
        mMarkupPercentage = in.readString();
        mMasterBoxCode = in.readString();
        mMasterBoxTrackingCode = in.readString();
        mOrderId = in.readString();
        mOrderNumber = in.readString();
        mReasonForShippingOutside = in.readString();
        mShipMethod = in.readString();
        mShipToEmail = in.readString();
        mShipToName = in.readString();
        mShipper = in.readString();
        mShippingCompany = in.readString();
        mShippingCost = in.readString();
        mShippingLabel = in.readString();
        mShippingMethod = in.readString();
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

    public static final Creator<MasterBox> CREATOR = new Creator<MasterBox>() {
        @Override
        public MasterBox createFromParcel(Parcel in) {
            return new MasterBox(in);
        }

        @Override
        public MasterBox[] newArray(int size) {
            return new MasterBox[size];
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

    public String getCityBin() {
        return mCityBin;
    }

    public void setCityBin(String cityBin) {
        mCityBin = cityBin;
    }

    public Long getCityBinId() {
        return mCityBinId;
    }

    public void setCityBinId(Long cityBinId) {
        mCityBinId = cityBinId;
    }

    public String getCommercialInvoice() {
        return mCommercialInvoice;
    }

    public void setCommercialInvoice(String commercialInvoice) {
        mCommercialInvoice = commercialInvoice;
    }

    public String getCompanyEmail() {
        return mCompanyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        mCompanyEmail = companyEmail;
    }

    public String getCompanyName() {
        return mCompanyName;
    }

    public void setCompanyName(String companyName) {
        mCompanyName = companyName;
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

    public Long getIsReadyToShip() {
        return mIsReadyToShip;
    }

    public void setIsReadyToShip(Long isReadyToShip) {
        mIsReadyToShip = isReadyToShip;
    }

    public Long getIsSpecial() {
        return mIsSpecial;
    }

    public void setIsSpecial(Long isSpecial) {
        mIsSpecial = isSpecial;
    }

    public Long getIsZummixOwnwed() {
        return mIsZummixOwnwed;
    }

    public void setIsZummixOwnwed(Long isZummixOwnwed) {
        mIsZummixOwnwed = isZummixOwnwed;
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

    public String getMasterBoxTrackingCode() {
        return mMasterBoxTrackingCode;
    }

    public void setMasterBoxTrackingCode(String masterBoxTrackingCode) {
        mMasterBoxTrackingCode = masterBoxTrackingCode;
    }

    public String getOrderId() {
        return mOrderId;
    }

    public void setOrderId(String orderId) {
        mOrderId = orderId;
    }

    public String getOrderNumber() {
        return mOrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        mOrderNumber = orderNumber;
    }

    public String getReasonForShippingOutside() {
        return mReasonForShippingOutside;
    }

    public void setReasonForShippingOutside(String reasonForShippingOutside) {
        mReasonForShippingOutside = reasonForShippingOutside;
    }

    public String getShipMethod() {
        return mShipMethod;
    }

    public void setShipMethod(String shipMethod) {
        mShipMethod = shipMethod;
    }

    public String getShipToEmail() {
        return mShipToEmail;
    }

    public void setShipToEmail(String shipToEmail) {
        mShipToEmail = shipToEmail;
    }

    public String getShipToName() {
        return mShipToName;
    }

    public void setShipToName(String shipToName) {
        mShipToName = shipToName;
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
        dest.writeString(mCityBin);
        if (mCityBinId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mCityBinId);
        }
        dest.writeString(mCommercialInvoice);
        dest.writeString(mCompanyEmail);
        dest.writeString(mCompanyName);
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
        dest.writeString(mHeight);
        if (mId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mId);
        }
        if (mIsReadyToShip == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mIsReadyToShip);
        }
        if (mIsSpecial == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mIsSpecial);
        }
        if (mIsZummixOwnwed == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mIsZummixOwnwed);
        }
        dest.writeString(mLength);
        dest.writeString(mMarkupPercentage);
        dest.writeString(mMasterBoxCode);
        dest.writeString(mMasterBoxTrackingCode);
        dest.writeString(mOrderId);
        dest.writeString(mOrderNumber);
        dest.writeString(mReasonForShippingOutside);
        dest.writeString(mShipMethod);
        dest.writeString(mShipToEmail);
        dest.writeString(mShipToName);
        dest.writeString(mShipper);
        dest.writeString(mShippingCompany);
        dest.writeString(mShippingCost);
        dest.writeString(mShippingLabel);
        dest.writeString(mShippingMethod);
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

