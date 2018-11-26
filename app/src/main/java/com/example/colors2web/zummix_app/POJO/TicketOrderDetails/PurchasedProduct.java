
package com.example.colors2web.zummix_app.POJO.TicketOrderDetails;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PurchasedProduct implements Parcelable {

    @SerializedName("category_id")
    private Long mCategoryId;
    @SerializedName("category_name")
    private String mCategoryName;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("deleted_at")
    private Object mDeletedAt;
    @SerializedName("free_item")
    private String mFreeItem;
    @SerializedName("gift_item")
    private String mGiftItem;
    @SerializedName("id")
    private Long mId;
    @SerializedName("is_coin_collection")
    private String mIsCoinCollection;
    @SerializedName("is_hym")
    private String mIsHym;
    @SerializedName("item_total_price")
    private String mItemTotalPrice;
    @SerializedName("ordered_quantity")
    private Long mOrderedQuantity;
    @SerializedName("pre_sale")
    private String mPreSale;
    @SerializedName("print_item")
    private String mPrintItem;
    @SerializedName("product_id")
    private Long mProductId;
    @SerializedName("product_image")
    private String mProductImage;
    @SerializedName("product_name")
    private String mProductName;
    @SerializedName("product_price")
    private String mProductPrice;
    @SerializedName("product_size")
    private String mProductSize;
    @SerializedName("product_sku_number")
    private String mProductSkuNumber;
    @SerializedName("project_id")
    private String mProjectId;
    @SerializedName("refunded_item_qty")
    private Long mRefundedItemQty;
    @SerializedName("sale_id")
    private Long mSaleId;
    @SerializedName("temp_section")
    private String mTempSection;
    @SerializedName("updated_at")
    private String mUpdatedAt;

    public PurchasedProduct() {
    }

    protected PurchasedProduct(Parcel in) {
        if (in.readByte() == 0) {
            mCategoryId = null;
        } else {
            mCategoryId = in.readLong();
        }
        mCategoryName = in.readString();
        mCreatedAt = in.readString();
        mFreeItem = in.readString();
        mGiftItem = in.readString();
        mItemTotalPrice = in.readString();
        if (in.readByte() == 0) {
            mId = null;
        } else {
            mId = in.readLong();
        }
        mIsCoinCollection = in.readString();
        mIsHym = in.readString();
//        if (in.readByte() == 0) {
//            mItemTotalPrice = null;
//        } else {
//            mItemTotalPrice = in.readLong();
//        }
        if (in.readByte() == 0) {
            mOrderedQuantity = null;
        } else {
            mOrderedQuantity = in.readLong();
        }
        mPreSale = in.readString();
        mPrintItem = in.readString();
        if (in.readByte() == 0) {
            mProductId = null;
        } else {
            mProductId = in.readLong();
        }
        mProductImage = in.readString();
        mProductName = in.readString();
        mProductPrice = in.readString();
        mProductSize = in.readString();
        mProductSkuNumber = in.readString();
        mProjectId = in.readString();
        if (in.readByte() == 0) {
            mRefundedItemQty = null;
        } else {
            mRefundedItemQty = in.readLong();
        }
        if (in.readByte() == 0) {
            mSaleId = null;
        } else {
            mSaleId = in.readLong();
        }
        mTempSection = in.readString();
        mUpdatedAt = in.readString();
    }

    public static final Creator<PurchasedProduct> CREATOR = new Creator<PurchasedProduct>() {
        @Override
        public PurchasedProduct createFromParcel(Parcel in) {
            return new PurchasedProduct(in);
        }

        @Override
        public PurchasedProduct[] newArray(int size) {
            return new PurchasedProduct[size];
        }
    };

    public Long getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(Long categoryId) {
        mCategoryId = categoryId;
    }

    public String getCategoryName() {
        return mCategoryName;
    }

    public void setCategoryName(String categoryName) {
        mCategoryName = categoryName;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public Object getDeletedAt() {
        return mDeletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        mDeletedAt = deletedAt;
    }

    public String getFreeItem() {
        return mFreeItem;
    }

    public void setFreeItem(String freeItem) {
        mFreeItem = freeItem;
    }

    public String getGiftItem() {
        return mGiftItem;
    }

    public void setGiftItem(String giftItem) {
        mGiftItem = giftItem;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getIsCoinCollection() {
        return mIsCoinCollection;
    }

    public void setIsCoinCollection(String isCoinCollection) {
        mIsCoinCollection = isCoinCollection;
    }

    public String getIsHym() {
        return mIsHym;
    }

    public void setIsHym(String isHym) {
        mIsHym = isHym;
    }

    public String getItemTotalPrice() {
        return mItemTotalPrice;
    }

    public void setItemTotalPrice(String itemTotalPrice) {
        mItemTotalPrice = itemTotalPrice;
    }

    public Long getOrderedQuantity() {
        return mOrderedQuantity;
    }

    public void setOrderedQuantity(Long orderedQuantity) {
        mOrderedQuantity = orderedQuantity;
    }

    public String getPreSale() {
        return mPreSale;
    }

    public void setPreSale(String preSale) {
        mPreSale = preSale;
    }

    public String getPrintItem() {
        return mPrintItem;
    }

    public void setPrintItem(String printItem) {
        mPrintItem = printItem;
    }

    public Long getProductId() {
        return mProductId;
    }

    public void setProductId(Long productId) {
        mProductId = productId;
    }

    public String getProductImage() {
        return mProductImage;
    }

    public void setProductImage(String productImage) {
        mProductImage = productImage;
    }

    public String getProductName() {
        return mProductName;
    }

    public void setProductName(String productName) {
        mProductName = productName;
    }

    public String getProductPrice() {
        return mProductPrice;
    }

    public void setProductPrice(String productPrice) {
        mProductPrice = productPrice;
    }

    public String getProductSize() {
        return mProductSize;
    }

    public void setProductSize(String productSize) {
        mProductSize = productSize;
    }

    public String getProductSkuNumber() {
        return mProductSkuNumber;
    }

    public void setProductSkuNumber(String productSkuNumber) {
        mProductSkuNumber = productSkuNumber;
    }

    public String getProjectId() {
        return mProjectId;
    }

    public void setProjectId(String projectId) {
        mProjectId = projectId;
    }

    public Long getRefundedItemQty() {
        return mRefundedItemQty;
    }

    public void setRefundedItemQty(Long refundedItemQty) {
        mRefundedItemQty = refundedItemQty;
    }

    public Long getSaleId() {
        return mSaleId;
    }

    public void setSaleId(Long saleId) {
        mSaleId = saleId;
    }

    public String getTempSection() {
        return mTempSection;
    }

    public void setTempSection(String tempSection) {
        mTempSection = tempSection;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (mCategoryId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mCategoryId);
        }
        dest.writeString(mCategoryName);
        dest.writeString(mCreatedAt);
        dest.writeString(mFreeItem);
        dest.writeString(mGiftItem);
        if (mId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mId);
        }
        dest.writeString(mIsCoinCollection);
        dest.writeString(mIsHym);
//        if (mItemTotalPrice == null) {
//            dest.writeByte((byte) 0);
//        } else {
//            dest.writeByte((byte) 1);
//            dest.writeLong(mItemTotalPrice);
//        }
        if (mOrderedQuantity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mOrderedQuantity);
        }
        dest.writeString(mPreSale);
        dest.writeString(mItemTotalPrice);
        dest.writeString(mPrintItem);
        if (mProductId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mProductId);
        }
        dest.writeString(mProductImage);
        dest.writeString(mProductName);
        dest.writeString(mProductPrice);
        dest.writeString(mProductSize);
        dest.writeString(mProductSkuNumber);
        dest.writeString(mProjectId);
        if (mRefundedItemQty == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mRefundedItemQty);
        }
        if (mSaleId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mSaleId);
        }
        dest.writeString(mTempSection);
        dest.writeString(mUpdatedAt);
    }
}
