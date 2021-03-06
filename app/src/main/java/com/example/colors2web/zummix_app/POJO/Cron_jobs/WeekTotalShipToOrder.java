package com.example.colors2web.zummix_app.POJO.Cron_jobs;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class WeekTotalShipToOrder implements Parcelable {

    protected WeekTotalShipToOrder(Parcel in) {
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

    public static final Creator<WeekTotalShipToOrder> CREATOR = new Creator<WeekTotalShipToOrder>() {
        @Override
        public WeekTotalShipToOrder createFromParcel(Parcel in) {
            return new WeekTotalShipToOrder(in);
        }

        @Override
        public WeekTotalShipToOrder[] newArray(int size) {
            return new WeekTotalShipToOrder[size];
        }
    };

    public Long getmNoOfOrders() {
        return mNoOfOrders;
    }

    public void setmNoOfOrders(Long mNoOfOrders) {
        this.mNoOfOrders = mNoOfOrders;
    }

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

    public WeekTotalShipToOrder() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
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
}
