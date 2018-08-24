
package com.example.colors2web.zummix_app.POJO.customers;

import com.google.gson.annotations.SerializedName;

public class ShipStationCredential {

    @SerializedName("company_identifier")
    private String mCompanyIdentifier;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("customer_id")
    private Long mCustomerId;
    @SerializedName("id")
    private Long mId;
    @SerializedName("key")
    private String mKey;
    @SerializedName("secret")
    private String mSecret;
    @SerializedName("store_id")
    private String mStoreId;
    @SerializedName("updated_at")
    private String mUpdatedAt;

    public String getCompanyIdentifier() {
        return mCompanyIdentifier;
    }

    public void setCompanyIdentifier(String companyIdentifier) {
        mCompanyIdentifier = companyIdentifier;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
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

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getSecret() {
        return mSecret;
    }

    public void setSecret(String secret) {
        mSecret = secret;
    }

    public String getStoreId() {
        return mStoreId;
    }

    public void setStoreId(String storeId) {
        mStoreId = storeId;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
