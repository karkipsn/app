
package com.example.colors2web.zummix_app.POJO.customers;

import com.google.gson.annotations.SerializedName;

public class ShopifyCredential {

    @SerializedName("api_key")
    private String mApiKey;
    @SerializedName("company_identifier")
    private String mCompanyIdentifier;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("customer_id")
    private Long mCustomerId;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("id")
    private Long mId;
    @SerializedName("password")
    private String mPassword;
    @SerializedName("updated_at")
    private String mUpdatedAt;
    @SerializedName("vendor")
    private String mVendor;
    @SerializedName("webhook_secret_token")
    private String mWebhookSecretToken;

    public String getApiKey() {
        return mApiKey;
    }

    public void setApiKey(String apiKey) {
        mApiKey = apiKey;
    }

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

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public String getVendor() {
        return mVendor;
    }

    public void setVendor(String vendor) {
        mVendor = vendor;
    }

    public String getWebhookSecretToken() {
        return mWebhookSecretToken;
    }

    public void setWebhookSecretToken(String webhookSecretToken) {
        mWebhookSecretToken = webhookSecretToken;
    }

}
