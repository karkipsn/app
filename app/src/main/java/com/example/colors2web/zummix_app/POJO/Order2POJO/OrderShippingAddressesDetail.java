
package com.example.colors2web.zummix_app.POJO.Order2POJO;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class OrderShippingAddressesDetail implements Parcelable {

    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("created_by")
    private Long mCreatedBy;
    @SerializedName("customer_address1")
    private String mCustomerAddress1;
    @SerializedName("customer_address2")
    private String mCustomerAddress2;
    @SerializedName("customer_city")
    private String mCustomerCity;
    @SerializedName("customer_country")
    private String mCustomerCountry;
    @SerializedName("customer_email")
    private String mCustomerEmail;
    @SerializedName("customer_fname")
    private String mCustomerFname;
    @SerializedName("customer_lname")
    private String mCustomerLname;
    @SerializedName("customer_mname")
    private Object mCustomerMname;
    @SerializedName("customer_office_name")
    private String mCustomerOfficeName;
    @SerializedName("customer_phone1")
    private String mCustomerPhone1;
    @SerializedName("customer_phone2")
    private String mCustomerPhone2;
    @SerializedName("customer_state")
    private String mCustomerState;
    @SerializedName("customer_zip")
    private String mCustomerZip;
    @SerializedName("dept_code")
    private String mDeptCode;
    @SerializedName("id")
    private Long mId;
    @SerializedName("note")
    private String mNote;
    @SerializedName("order_id")
    private Long mOrderId;
    @SerializedName("updated_at")
    private String mUpdatedAt;
    @SerializedName("updated_by")
    private Long mUpdatedBy;

    public OrderShippingAddressesDetail() {

    }

    protected OrderShippingAddressesDetail(Parcel in) {
        mCreatedAt = in.readString();
        if (in.readByte() == 0) {
            mCreatedBy = null;
        } else {
            mCreatedBy = in.readLong();
        }
        mCustomerAddress1 = in.readString();
        mCustomerAddress2 = in.readString();
        mCustomerCity = in.readString();
        mCustomerCountry = in.readString();
        mCustomerEmail = in.readString();
        mCustomerFname = in.readString();
        mCustomerLname = in.readString();
        mCustomerOfficeName = in.readString();
        mCustomerPhone1 = in.readString();
        mCustomerPhone2 = in.readString();
        mCustomerState = in.readString();
        mCustomerZip = in.readString();
        mDeptCode = in.readString();
        if (in.readByte() == 0) {
            mId = null;
        } else {
            mId = in.readLong();
        }
        mNote = in.readString();
        if (in.readByte() == 0) {
            mOrderId = null;
        } else {
            mOrderId = in.readLong();
        }
        mUpdatedAt = in.readString();
        if (in.readByte() == 0) {
            mUpdatedBy = null;
        } else {
            mUpdatedBy = in.readLong();
        }
    }

    public static final Creator<OrderShippingAddressesDetail> CREATOR = new Creator<OrderShippingAddressesDetail>() {
        @Override
        public OrderShippingAddressesDetail createFromParcel(Parcel in) {
            return new OrderShippingAddressesDetail(in);
        }

        @Override
        public OrderShippingAddressesDetail[] newArray(int size) {
            return new OrderShippingAddressesDetail[size];
        }
    };

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

    public String getCustomerAddress1() {
        return mCustomerAddress1;
    }

    public void setCustomerAddress1(String customerAddress1) {
        mCustomerAddress1 = customerAddress1;
    }

    public String getCustomerAddress2() {
        return mCustomerAddress2;
    }

    public void setCustomerAddress2(String customerAddress2) {
        mCustomerAddress2 = customerAddress2;
    }

    public String getCustomerCity() {
        return mCustomerCity;
    }

    public void setCustomerCity(String customerCity) {
        mCustomerCity = customerCity;
    }

    public String getCustomerCountry() {
        return mCustomerCountry;
    }

    public void setCustomerCountry(String customerCountry) {
        mCustomerCountry = customerCountry;
    }

    public String getCustomerEmail() {
        return mCustomerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        mCustomerEmail = customerEmail;
    }

    public String getCustomerFname() {
        return mCustomerFname;
    }

    public void setCustomerFname(String customerFname) {
        mCustomerFname = customerFname;
    }

    public String getCustomerLname() {
        return mCustomerLname;
    }

    public void setCustomerLname(String customerLname) {
        mCustomerLname = customerLname;
    }

    public Object getCustomerMname() {
        return mCustomerMname;
    }

    public void setCustomerMname(Object customerMname) {
        mCustomerMname = customerMname;
    }

    public String getCustomerOfficeName() {
        return mCustomerOfficeName;
    }

    public void setCustomerOfficeName(String customerOfficeName) {
        mCustomerOfficeName = customerOfficeName;
    }

    public String getCustomerPhone1() {
        return mCustomerPhone1;
    }

    public void setCustomerPhone1(String customerPhone1) {
        mCustomerPhone1 = customerPhone1;
    }

    public String getCustomerPhone2() {
        return mCustomerPhone2;
    }

    public void setCustomerPhone2(String customerPhone2) {
        mCustomerPhone2 = customerPhone2;
    }

    public String getCustomerState() {
        return mCustomerState;
    }

    public void setCustomerState(String customerState) {
        mCustomerState = customerState;
    }

    public String getCustomerZip() {
        return mCustomerZip;
    }

    public void setCustomerZip(String customerZip) {
        mCustomerZip = customerZip;
    }

    public String getDeptCode() {
        return mDeptCode;
    }

    public void setDeptCode(String deptCode) {
        mDeptCode = deptCode;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getNote() {
        return mNote;
    }

    public void setNote(String note) {
        mNote = note;
    }

    public Long getOrderId() {
        return mOrderId;
    }

    public void setOrderId(Long orderId) {
        mOrderId = orderId;
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
        dest.writeString(mCustomerAddress1);
        dest.writeString(mCustomerAddress2);
        dest.writeString(mCustomerCity);
        dest.writeString(mCustomerCountry);
        dest.writeString(mCustomerEmail);
        dest.writeString(mCustomerFname);
        dest.writeString(mCustomerLname);
        dest.writeString(mCustomerOfficeName);
        dest.writeString(mCustomerPhone1);
        dest.writeString(mCustomerPhone2);
        dest.writeString(mCustomerState);
        dest.writeString(mCustomerZip);
        dest.writeString(mDeptCode);
        if (mId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mId);
        }
        dest.writeString(mNote);
        if (mOrderId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mOrderId);
        }
        dest.writeString(mUpdatedAt);
        if (mUpdatedBy == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mUpdatedBy);
        }
    }
}
