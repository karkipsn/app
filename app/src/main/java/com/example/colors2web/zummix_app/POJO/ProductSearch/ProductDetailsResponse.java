
package com.example.colors2web.zummix_app.POJO.ProductSearch;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class ProductDetailsResponse {

    @SerializedName("A2S")
    private Long mA2S;
    @SerializedName("customerItemsDetail")
    private List<CustomerItemsDetail> mCustomerItemsDetail;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("pick_balance")
    private Long mPickBalance;
    @SerializedName("pickLocation")
    private PickLocation mPickLocation;
    @SerializedName("returnType")
    private String mReturnType;

    public List<DrShipmentItemLocations> getmDrShipmentItemLocations() {
        return mDrShipmentItemLocations;
    }

    public void setmDrShipmentItemLocations(List<DrShipmentItemLocations> mDrShipmentItemLocations) {
        this.mDrShipmentItemLocations = mDrShipmentItemLocations;
    }

    public List<InventoryLogs> getmInventoryLogs() {
        return mInventoryLogs;
    }

    public void setmInventoryLogs(List<InventoryLogs> mInventoryLogs) {
        this.mInventoryLogs = mInventoryLogs;
    }

    @SerializedName("drShipmentItemLocations")
    private List<DrShipmentItemLocations> mDrShipmentItemLocations;

    @SerializedName("inventoryLogs")
    private List<InventoryLogs> mInventoryLogs;

    public Long getA2S() {
        return mA2S;
    }

    public void setA2S(Long a2S) {
        mA2S = a2S;
    }

    public List<CustomerItemsDetail> getCustomerItemsDetail() {
        return mCustomerItemsDetail;
    }

    public void setCustomerItemsDetail(List<CustomerItemsDetail> customerItemsDetail) {
        mCustomerItemsDetail = customerItemsDetail;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public Long getPickBalance() {
        return mPickBalance;
    }

    public void setPickBalance(Long pickBalance) {
        mPickBalance = pickBalance;
    }

    public PickLocation getPickLocation() {
        return mPickLocation;
    }

    public void setPickLocation(PickLocation pickLocation) {
        mPickLocation = pickLocation;
    }

    public String getReturnType() {
        return mReturnType;
    }

    public void setReturnType(String returnType) {
        mReturnType = returnType;
    }

}
