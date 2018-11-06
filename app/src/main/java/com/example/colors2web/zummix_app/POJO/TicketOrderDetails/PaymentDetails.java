
package com.example.colors2web.zummix_app.POJO.TicketOrderDetails;

import com.google.gson.annotations.SerializedName;

public class PaymentDetails {

    @SerializedName("amount")
    private String mAmount;
    @SerializedName("batch_id")
    private String mBatchId;
    @SerializedName("card_expire_date")
    private String mCardExpireDate;
    @SerializedName("card_num_last_4_digit")
    private String mCardNumLast4Digit;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("deleted_at")
    private Object mDeletedAt;
    @SerializedName("id")
    private Long mId;
    @SerializedName("payment_method")
    private String mPaymentMethod;
    @SerializedName("payment_status")
    private String mPaymentStatus;
    @SerializedName("sale_id")
    private Long mSaleId;
    @SerializedName("settled_status")
    private String mSettledStatus;
    @SerializedName("transaction_id")
    private String mTransactionId;
    @SerializedName("updated_at")
    private String mUpdatedAt;

    public String getAmount() {
        return mAmount;
    }

    public void setAmount(String amount) {
        mAmount = amount;
    }

    public String getBatchId() {
        return mBatchId;
    }

    public void setBatchId(String batchId) {
        mBatchId = batchId;
    }

    public String getCardExpireDate() {
        return mCardExpireDate;
    }

    public void setCardExpireDate(String cardExpireDate) {
        mCardExpireDate = cardExpireDate;
    }

    public String getCardNumLast4Digit() {
        return mCardNumLast4Digit;
    }

    public void setCardNumLast4Digit(String cardNumLast4Digit) {
        mCardNumLast4Digit = cardNumLast4Digit;
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

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getPaymentMethod() {
        return mPaymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        mPaymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return mPaymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        mPaymentStatus = paymentStatus;
    }

    public Long getSaleId() {
        return mSaleId;
    }

    public void setSaleId(Long saleId) {
        mSaleId = saleId;
    }

    public String getSettledStatus() {
        return mSettledStatus;
    }

    public void setSettledStatus(String settledStatus) {
        mSettledStatus = settledStatus;
    }

    public String getTransactionId() {
        return mTransactionId;
    }

    public void setTransactionId(String transactionId) {
        mTransactionId = transactionId;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
