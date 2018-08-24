
package com.example.colors2web.zummix_app.POJO.PostSearch;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Boxes implements Serializable {

    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("item_sku")
    private String mItemSku;
    @SerializedName("totalPickedQty")
    private String mTotalPickedQty;
    @SerializedName("unique_orders")
    private Long mUniqueOrders;

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getItemSku() {
        return mItemSku;
    }

    public void setItemSku(String itemSku) {
        mItemSku = itemSku;
    }

    public String getTotalPickedQty() {
        return mTotalPickedQty;
    }

    public void setTotalPickedQty(String totalPickedQty) {
        mTotalPickedQty = totalPickedQty;
    }

    public Long getUniqueOrders() {
        return mUniqueOrders;
    }

    public void setUniqueOrders(Long uniqueOrders) {
        mUniqueOrders = uniqueOrders;
    }

}
