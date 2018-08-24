
package com.example.colors2web.zummix_app.POJO.customers;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class ShippingMethods implements Parcelable {

    @SerializedName("account_number")
    private String mAccountNumber;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("created_by")
    private Long mCreatedBy;
    @SerializedName("customer_id")
    private Long mCustomerId;
    @SerializedName("id")
    private Long mId;
    @SerializedName("international_account_number")
    private String mInternationalAccountNumber;
    @SerializedName("shipping_account_type")
    private String mShippingAccountType;
    @SerializedName("shipping_company_type")
    private String mShippingCompanyType;
    @SerializedName("tax_account_number")
    private String mTaxAccountNumber;
    @SerializedName("updated_at")
    private String mUpdatedAt;
    @SerializedName("updated_by")
    private Long mUpdatedBy;

    public ShippingMethods() {

    }

    protected ShippingMethods(Parcel in) {
        mAccountNumber = in.readString();
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
            mId = null;
        } else {
            mId = in.readLong();
        }
        mInternationalAccountNumber = in.readString();
        mShippingAccountType = in.readString();
        mShippingCompanyType = in.readString();
        mTaxAccountNumber = in.readString();
        mUpdatedAt = in.readString();
        if (in.readByte() == 0) {
            mUpdatedBy = null;
        } else {
            mUpdatedBy = in.readLong();
        }
    }

    public static final Creator<ShippingMethods> CREATOR = new Creator<ShippingMethods>() {
        @Override
        public ShippingMethods createFromParcel(Parcel in) {
            return new ShippingMethods(in);
        }

        @Override
        public ShippingMethods[] newArray(int size) {
            return new ShippingMethods[size];
        }
    };

    public String getAccountNumber() {
        return mAccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        mAccountNumber = accountNumber;
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

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getInternationalAccountNumber() {
        return mInternationalAccountNumber;
    }

    public void setInternationalAccountNumber(String internationalAccountNumber) {
        mInternationalAccountNumber = internationalAccountNumber;
    }

    public String getShippingAccountType() {
        return mShippingAccountType;
    }

    public void setShippingAccountType(String shippingAccountType) {
        mShippingAccountType = shippingAccountType;
    }

    public String getShippingCompanyType() {
        return mShippingCompanyType;
    }

    public void setShippingCompanyType(String shippingCompanyType) {
        mShippingCompanyType = shippingCompanyType;
    }

    public String getTaxAccountNumber() {
        return mTaxAccountNumber;
    }

    public void setTaxAccountNumber(String taxAccountNumber) {
        mTaxAccountNumber = taxAccountNumber;
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
        dest.writeString(mAccountNumber);
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
        if (mId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mId);
        }
        dest.writeString(mInternationalAccountNumber);
        dest.writeString(mShippingAccountType);
        dest.writeString(mShippingCompanyType);
        dest.writeString(mTaxAccountNumber);
        dest.writeString(mUpdatedAt);
        if (mUpdatedBy == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mUpdatedBy);
        }
    }
}
