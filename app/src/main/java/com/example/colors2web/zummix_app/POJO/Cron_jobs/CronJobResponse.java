
package com.example.colors2web.zummix_app.POJO.Cron_jobs;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CronJobResponse {

    @SerializedName("officeOrders")
    private List<OfficeOrder> mOfficeOrders;
    @SerializedName("returnType")
    private String mReturnType;
    @SerializedName("shipToOrders")
    private List<ShipToOrder> mShipToOrders;
    @SerializedName("totalOfficeOrders")
    private List<TotalOfficeOrder> mTotalOfficeOrders;
    @SerializedName("totalShipToOrders")
    private List<TotalShipToOrder> mTotalShipToOrders;
    @SerializedName("totalVipOrders")
    private List<TotalVipOrder> mTotalVipOrders;
    @SerializedName("vipOrders")
    private List<VipOrder> mVipOrders;
    @SerializedName("weeklyOfficeOrders")
    private List<WeekOfficeOrder> mWeeklyOfficeOrders;
    @SerializedName("weeklyShipToOrders")
    private List<WeekShipToOrder> mWeeklyShipToOrders;
    @SerializedName("weeklyTotalOfficeOrders")
    private List<WeekTotalOfficeOrder> mWeeklyTotalOfficeOrders;
    @SerializedName("weeklyTotalShipToOrders")
    private List<WeekTotalShipToOrder> mWeeklyTotalShipToOrders;
    @SerializedName("weeklyTotalVipOrders")
    private List<WeekTotalVipOrder> mWeeklyTotalVipOrders;
    @SerializedName("weeklyVipOrders")
    private List<WeekVipOrder> mWeeklyVipOrders;



    public List<OfficeOrder> getmOfficeOrders() {
        return mOfficeOrders;
    }

    public void setmOfficeOrders(List<OfficeOrder> mOfficeOrders) {
        this.mOfficeOrders = mOfficeOrders;
    }

    public String getmReturnType() {
        return mReturnType;
    }

    public void setmReturnType(String mReturnType) {
        this.mReturnType = mReturnType;
    }

    public List<ShipToOrder> getmShipToOrders() {
        return mShipToOrders;
    }

    public void setmShipToOrders(List<ShipToOrder> mShipToOrders) {
        this.mShipToOrders = mShipToOrders;
    }

    public List<TotalOfficeOrder> getmTotalOfficeOrders() {
        return mTotalOfficeOrders;
    }

    public void setmTotalOfficeOrders(List<TotalOfficeOrder> mTotalOfficeOrders) {
        this.mTotalOfficeOrders = mTotalOfficeOrders;
    }

    public List<TotalShipToOrder> getmTotalShipToOrders() {
        return mTotalShipToOrders;
    }

    public void setmTotalShipToOrders(List<TotalShipToOrder> mTotalShipToOrders) {
        this.mTotalShipToOrders = mTotalShipToOrders;
    }

    public List<TotalVipOrder> getmTotalVipOrders() {
        return mTotalVipOrders;
    }

    public void setmTotalVipOrders(List<TotalVipOrder> mTotalVipOrders) {
        this.mTotalVipOrders = mTotalVipOrders;
    }

    public List<VipOrder> getmVipOrders() {
        return mVipOrders;
    }

    public void setmVipOrders(List<VipOrder> mVipOrders) {
        this.mVipOrders = mVipOrders;
    }

    public List<WeekOfficeOrder> getmWeeklyOfficeOrders() {
        return mWeeklyOfficeOrders;
    }

    public void setmWeeklyOfficeOrders(List<WeekOfficeOrder> mWeeklyOfficeOrders) {
        this.mWeeklyOfficeOrders = mWeeklyOfficeOrders;
    }

    public List<WeekShipToOrder> getmWeeklyShipToOrders() {
        return mWeeklyShipToOrders;
    }

    public void setmWeeklyShipToOrders(List<WeekShipToOrder> mWeeklyShipToOrders) {
        this.mWeeklyShipToOrders = mWeeklyShipToOrders;
    }

    public List<WeekTotalOfficeOrder> getmWeeklyTotalOfficeOrders() {
        return mWeeklyTotalOfficeOrders;
    }

    public void setmWeeklyTotalOfficeOrders(List<WeekTotalOfficeOrder> mWeeklyTotalOfficeOrders) {
        this.mWeeklyTotalOfficeOrders = mWeeklyTotalOfficeOrders;
    }

    public List<WeekTotalShipToOrder> getmWeeklyTotalShipToOrders() {
        return mWeeklyTotalShipToOrders;
    }

    public void setmWeeklyTotalShipToOrders(List<WeekTotalShipToOrder> mWeeklyTotalShipToOrders) {
        this.mWeeklyTotalShipToOrders = mWeeklyTotalShipToOrders;
    }

    public List<WeekTotalVipOrder> getmWeeklyTotalVipOrders() {
        return mWeeklyTotalVipOrders;
    }

    public void setmWeeklyTotalVipOrders(List<WeekTotalVipOrder> mWeeklyTotalVipOrders) {
        this.mWeeklyTotalVipOrders = mWeeklyTotalVipOrders;
    }

    public List<WeekVipOrder> getmWeeklyVipOrders() {
        return mWeeklyVipOrders;
    }

    public void setmWeeklyVipOrders(List<WeekVipOrder> mWeeklyVipOrders) {
        this.mWeeklyVipOrders = mWeeklyVipOrders;
    }
}
