
package com.example.colors2web.zummix_app.POJO.MasterBoxSearch;

import com.google.gson.annotations.SerializedName;

public class LineItems {

    @SerializedName("box_quantity")
    private String mBoxQuantity;
    @SerializedName("customer_item_name")
    private String mCustomerItemName;
    @SerializedName("item_image")
    private String mItemImage;
    @SerializedName("item_name")
    private String mItemName;
    @SerializedName("item_sku")
    private String mItemSku;
    @SerializedName("order_number")
    private String mOrderNumber;
    @SerializedName("ordered_quantity")
    private Long mOrderedQuantity;

    public String getBoxQuantity() {
        return mBoxQuantity;
    }

    public void setBoxQuantity(String boxQuantity) {
        mBoxQuantity = boxQuantity;
    }

    public String getCustomerItemName() {
        return mCustomerItemName;
    }

    public void setCustomerItemName(String customerItemName) {
        mCustomerItemName = customerItemName;
    }

    public String getItemImage() {
        return mItemImage;
    }

    public void setItemImage(String itemImage) {
        mItemImage = itemImage;
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

    public String getOrderNumber() {
        return mOrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        mOrderNumber = orderNumber;
    }

    public Long getOrderedQuantity() {
        return mOrderedQuantity;
    }

    public void setOrderedQuantity(Long orderedQuantity) {
        mOrderedQuantity = orderedQuantity;
    }

}
