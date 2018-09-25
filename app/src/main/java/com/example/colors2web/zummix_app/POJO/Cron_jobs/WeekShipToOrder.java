package com.example.colors2web.zummix_app.POJO.Cron_jobs;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class WeekShipToOrder implements Parcelable {
    public WeekShipToOrder() {
    }

    @SerializedName("customer_id")
    private Long mCustomerId;
    @SerializedName("customer_name")
    private String mCustomerName;
    @SerializedName("no_of_orders")
    private Long mNoOfOrders;
    @SerializedName("total_batched_orders")
    private Long mTotalBatchedOrders;
    @SerializedName("total_batched_overdue_orders")
    private Long mTotalBatchedOverdueOrders;
    @SerializedName("total_ior")
    private Long mTotalIor;
    @SerializedName("total_onhold_overdue_orders")
    private Long mTotalOnholdOverdueOrders;
    @SerializedName("total_open_orders")
    private Long mTotalOpenOrders;
    @SerializedName("total_shipped_orders")
    private Long mTotalShippedOrders;

    protected WeekShipToOrder(Parcel in) {
        if (in.readByte() == 0) {
            mCustomerId = null;
        } else {
            mCustomerId = in.readLong();
        }
        mCustomerName = in.readString();
        if (in.readByte() == 0) {
            mNoOfOrders = null;
        } else {
            mNoOfOrders = in.readLong();
        }
        if (in.readByte() == 0) {
            mTotalBatchedOrders = null;
        } else {
            mTotalBatchedOrders = in.readLong();
        }
        if (in.readByte() == 0) {
            mTotalBatchedOverdueOrders = null;
        } else {
            mTotalBatchedOverdueOrders = in.readLong();
        }
        if (in.readByte() == 0) {
            mTotalIor = null;
        } else {
            mTotalIor = in.readLong();
        }
        if (in.readByte() == 0) {
            mTotalOnholdOverdueOrders = null;
        } else {
            mTotalOnholdOverdueOrders = in.readLong();
        }
        if (in.readByte() == 0) {
            mTotalOpenOrders = null;
        } else {
            mTotalOpenOrders = in.readLong();
        }
        if (in.readByte() == 0) {
            mTotalShippedOrders = null;
        } else {
            mTotalShippedOrders = in.readLong();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (mCustomerId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mCustomerId);
        }
        dest.writeString(mCustomerName);
        if (mNoOfOrders == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mNoOfOrders);
        }
        if (mTotalBatchedOrders == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mTotalBatchedOrders);
        }
        if (mTotalBatchedOverdueOrders == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mTotalBatchedOverdueOrders);
        }
        if (mTotalIor == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mTotalIor);
        }
        if (mTotalOnholdOverdueOrders == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mTotalOnholdOverdueOrders);
        }
        if (mTotalOpenOrders == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mTotalOpenOrders);
        }
        if (mTotalShippedOrders == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mTotalShippedOrders);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WeekShipToOrder> CREATOR = new Creator<WeekShipToOrder>() {
        @Override
        public WeekShipToOrder createFromParcel(Parcel in) {
            return new WeekShipToOrder(in);
        }

        @Override
        public WeekShipToOrder[] newArray(int size) {
            return new WeekShipToOrder[size];
        }
    };

    public Long getmCustomerId() {
        return mCustomerId;
    }

    public void setmCustomerId(Long mCustomerId) {
        this.mCustomerId = mCustomerId;
    }

    public String getmCustomerName() {
        return mCustomerName;
    }

    public void setmCustomerName(String mCustomerName) {
        this.mCustomerName = mCustomerName;
    }

    public Long getmNoOfOrders() {
        return mNoOfOrders;
    }

    public void setmNoOfOrders(Long mNoOfOrders) {
        this.mNoOfOrders = mNoOfOrders;
    }

    public Long getmTotalBatchedOrders() {
        return mTotalBatchedOrders;
    }

    public void setmTotalBatchedOrders(Long mTotalBatchedOrders) {
        this.mTotalBatchedOrders = mTotalBatchedOrders;
    }

    public Long getmTotalBatchedOverdueOrders() {
        return mTotalBatchedOverdueOrders;
    }

    public void setmTotalBatchedOverdueOrders(Long mTotalBatchedOverdueOrders) {
        this.mTotalBatchedOverdueOrders = mTotalBatchedOverdueOrders;
    }

    public Long getmTotalIor() {
        return mTotalIor;
    }

    public void setmTotalIor(Long mTotalIor) {
        this.mTotalIor = mTotalIor;
    }

    public Long getmTotalOnholdOverdueOrders() {
        return mTotalOnholdOverdueOrders;
    }

    public void setmTotalOnholdOverdueOrders(Long mTotalOnholdOverdueOrders) {
        this.mTotalOnholdOverdueOrders = mTotalOnholdOverdueOrders;
    }

    public Long getmTotalOpenOrders() {
        return mTotalOpenOrders;
    }

    public void setmTotalOpenOrders(Long mTotalOpenOrders) {
        this.mTotalOpenOrders = mTotalOpenOrders;
    }

    public Long getmTotalShippedOrders() {
        return mTotalShippedOrders;
    }

    public void setmTotalShippedOrders(Long mTotalShippedOrders) {
        this.mTotalShippedOrders = mTotalShippedOrders;
    }
}
