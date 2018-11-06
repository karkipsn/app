
package com.example.colors2web.zummix_app.POJO.TicketOrderDetails;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderDetails implements Serializable {

    @SerializedName("agency_status")
    private String mAgencyStatus;
    @SerializedName("box_number")
    private String mBoxNumber;
    @SerializedName("cancel_note")
    private String mCancelNote;
    @SerializedName("comment")
    private String mComment;
    @SerializedName("coupon")
    private String mCoupon;
    @SerializedName("coupon_discount")
    private String mCouponDiscount;
    @SerializedName("coupon_no")
    private String mCouponNo;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("created_by")
    private Long mCreatedBy;
    @SerializedName("credit_type")
    private String mCreditType;
    @SerializedName("credit_type_name")
    private String mCreditTypeName;
    @SerializedName("credit_used")
    private String mCreditUsed;
    @SerializedName("deleted_at")
    private Object mDeletedAt;
    @SerializedName("delivered_at")
    private String mDeliveredAt;
    @SerializedName("delivered_by")
    private String mDeliveredBy;
    @SerializedName("delivered_status")
    private String mDeliveredStatus;
    @SerializedName("department_id")
    private Long mDepartmentId;
    @SerializedName("department_name")
    private String mDepartmentName;
    @SerializedName("employee_id")
    private Long mEmployeeId;
    @SerializedName("field_office_id")
    private Long mFieldOfficeId;
    @SerializedName("field_office_name")
    private String mFieldOfficeName;
    @SerializedName("gift_item")
    private String mGiftItem;
    @SerializedName("grand_total")
    private String mGrandTotal;
    @SerializedName("has_coin_collection_products")
    private String mHasCoinCollectionProducts;
    @SerializedName("has_hym_products")
    private String mHasHymProducts;
    @SerializedName("id")
    private Long mId;
    @SerializedName("impersonated_by")
    private Long mImpersonatedBy;
    @SerializedName("is_send_to_vox_client_portal")
    private String mIsSendToVoxClientPortal;
    @SerializedName("master_box_number")
    private String mMasterBoxNumber;
    @SerializedName("member_email")
    private String mMemberEmail;
    @SerializedName("member_id")
    private Long mMemberId;
    @SerializedName("member_name")
    private String mMemberName;
    @SerializedName("order_status")
    private String mOrderStatus;
    @SerializedName("postal_address")
    private String mPostalAddress;
    @SerializedName("print_item")
    private String mPrintItem;
    @SerializedName("receive_status")
    private String mReceiveStatus;
    @SerializedName("received_at")
    private String mReceivedAt;
    @SerializedName("received_by")
    private Long mReceivedBy;
    @SerializedName("sale_number")
    private String mSaleNumber;
    @SerializedName("ship_cost")
    private String mShipCost;
    @SerializedName("ship_flag")
    private String mShipFlag;
    @SerializedName("ship_method")
    private String mShipMethod;
    @SerializedName("tracking_number")
    private String mTrackingNumber;
    @SerializedName("updated_at")
    private String mUpdatedAt;
    @SerializedName("updated_by")
    private Long mUpdatedBy;
    @SerializedName("used_coin_credit")
    private String mUsedCoinCredit;
    @SerializedName("used_gift_credit")
    private String mUsedGiftCredit;
    @SerializedName("used_print_credit")
    private String mUsedPrintCredit;
    @SerializedName("used_token_credit")
    private String mUsedTokenCredit;
    @SerializedName("used_uniform_credit")
    private String mUsedUniformCredit;
    @SerializedName("zen_print")
    private String mZenPrint;

    public String getAgencyStatus() {
        return mAgencyStatus;
    }

    public void setAgencyStatus(String agencyStatus) {
        mAgencyStatus = agencyStatus;
    }

    public String getBoxNumber() {
        return mBoxNumber;
    }

    public void setBoxNumber(String boxNumber) {
        mBoxNumber = boxNumber;
    }

    public String getCancelNote() {
        return mCancelNote;
    }

    public void setCancelNote(String cancelNote) {
        mCancelNote = cancelNote;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }

    public String getCoupon() {
        return mCoupon;
    }

    public void setCoupon(String coupon) {
        mCoupon = coupon;
    }

    public String getCouponDiscount() {
        return mCouponDiscount;
    }

    public void setCouponDiscount(String couponDiscount) {
        mCouponDiscount = couponDiscount;
    }

    public String getCouponNo() {
        return mCouponNo;
    }

    public void setCouponNo(String couponNo) {
        mCouponNo = couponNo;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public Long getCreatedBy() {
        return mCreatedBy;
    }

    public void setCreatedBy(Long createdBy) {
        mCreatedBy = createdBy;
    }

    public String getCreditType() {
        return mCreditType;
    }

    public void setCreditType(String creditType) {
        mCreditType = creditType;
    }

    public String getCreditTypeName() {
        return mCreditTypeName;
    }

    public void setCreditTypeName(String creditTypeName) {
        mCreditTypeName = creditTypeName;
    }

    public String getCreditUsed() {
        return mCreditUsed;
    }

    public void setCreditUsed(String creditUsed) {
        mCreditUsed = creditUsed;
    }

    public Object getDeletedAt() {
        return mDeletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        mDeletedAt = deletedAt;
    }

    public String getDeliveredAt() {
        return mDeliveredAt;
    }

    public void setDeliveredAt(String deliveredAt) {
        mDeliveredAt = deliveredAt;
    }

    public String getDeliveredBy() {
        return mDeliveredBy;
    }

    public void setDeliveredBy(String deliveredBy) {
        mDeliveredBy = deliveredBy;
    }

    public String getDeliveredStatus() {
        return mDeliveredStatus;
    }

    public void setDeliveredStatus(String deliveredStatus) {
        mDeliveredStatus = deliveredStatus;
    }

    public Long getDepartmentId() {
        return mDepartmentId;
    }

    public void setDepartmentId(Long departmentId) {
        mDepartmentId = departmentId;
    }

    public String getDepartmentName() {
        return mDepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        mDepartmentName = departmentName;
    }

    public Long getEmployeeId() {
        return mEmployeeId;
    }

    public void setEmployeeId(Long employeeId) {
        mEmployeeId = employeeId;
    }

    public Long getFieldOfficeId() {
        return mFieldOfficeId;
    }

    public void setFieldOfficeId(Long fieldOfficeId) {
        mFieldOfficeId = fieldOfficeId;
    }

    public String getFieldOfficeName() {
        return mFieldOfficeName;
    }

    public void setFieldOfficeName(String fieldOfficeName) {
        mFieldOfficeName = fieldOfficeName;
    }

    public String getGiftItem() {
        return mGiftItem;
    }

    public void setGiftItem(String giftItem) {
        mGiftItem = giftItem;
    }

    public String getGrandTotal() {
        return mGrandTotal;
    }

    public void setGrandTotal(String grandTotal) {
        mGrandTotal = grandTotal;
    }

    public String getHasCoinCollectionProducts() {
        return mHasCoinCollectionProducts;
    }

    public void setHasCoinCollectionProducts(String hasCoinCollectionProducts) {
        mHasCoinCollectionProducts = hasCoinCollectionProducts;
    }

    public String getHasHymProducts() {
        return mHasHymProducts;
    }

    public void setHasHymProducts(String hasHymProducts) {
        mHasHymProducts = hasHymProducts;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Long getImpersonatedBy() {
        return mImpersonatedBy;
    }

    public void setImpersonatedBy(Long impersonatedBy) {
        mImpersonatedBy = impersonatedBy;
    }

    public String getIsSendToVoxClientPortal() {
        return mIsSendToVoxClientPortal;
    }

    public void setIsSendToVoxClientPortal(String isSendToVoxClientPortal) {
        mIsSendToVoxClientPortal = isSendToVoxClientPortal;
    }

    public String getMasterBoxNumber() {
        return mMasterBoxNumber;
    }

    public void setMasterBoxNumber(String masterBoxNumber) {
        mMasterBoxNumber = masterBoxNumber;
    }

    public String getMemberEmail() {
        return mMemberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        mMemberEmail = memberEmail;
    }

    public Long getMemberId() {
        return mMemberId;
    }

    public void setMemberId(Long memberId) {
        mMemberId = memberId;
    }

    public String getMemberName() {
        return mMemberName;
    }

    public void setMemberName(String memberName) {
        mMemberName = memberName;
    }

    public String getOrderStatus() {
        return mOrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        mOrderStatus = orderStatus;
    }

    public String getPostalAddress() {
        return mPostalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        mPostalAddress = postalAddress;
    }

    public String getPrintItem() {
        return mPrintItem;
    }

    public void setPrintItem(String printItem) {
        mPrintItem = printItem;
    }

    public String getReceiveStatus() {
        return mReceiveStatus;
    }

    public void setReceiveStatus(String receiveStatus) {
        mReceiveStatus = receiveStatus;
    }

    public String getReceivedAt() {
        return mReceivedAt;
    }

    public void setReceivedAt(String receivedAt) {
        mReceivedAt = receivedAt;
    }

    public Long getReceivedBy() {
        return mReceivedBy;
    }

    public void setReceivedBy(Long receivedBy) {
        mReceivedBy = receivedBy;
    }

    public String getSaleNumber() {
        return mSaleNumber;
    }

    public void setSaleNumber(String saleNumber) {
        mSaleNumber = saleNumber;
    }

    public String getShipCost() {
        return mShipCost;
    }

    public void setShipCost(String shipCost) {
        mShipCost = shipCost;
    }

    public String getShipFlag() {
        return mShipFlag;
    }

    public void setShipFlag(String shipFlag) {
        mShipFlag = shipFlag;
    }

    public String getShipMethod() {
        return mShipMethod;
    }

    public void setShipMethod(String shipMethod) {
        mShipMethod = shipMethod;
    }

    public String getTrackingNumber() {
        return mTrackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        mTrackingNumber = trackingNumber;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public Long getUpdatedBy() {
        return mUpdatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        mUpdatedBy = updatedBy;
    }

    public String getUsedCoinCredit() {
        return mUsedCoinCredit;
    }

    public void setUsedCoinCredit(String usedCoinCredit) {
        mUsedCoinCredit = usedCoinCredit;
    }

    public String getUsedGiftCredit() {
        return mUsedGiftCredit;
    }

    public void setUsedGiftCredit(String usedGiftCredit) {
        mUsedGiftCredit = usedGiftCredit;
    }

    public String getUsedPrintCredit() {
        return mUsedPrintCredit;
    }

    public void setUsedPrintCredit(String usedPrintCredit) {
        mUsedPrintCredit = usedPrintCredit;
    }

    public String getUsedTokenCredit() {
        return mUsedTokenCredit;
    }

    public void setUsedTokenCredit(String usedTokenCredit) {
        mUsedTokenCredit = usedTokenCredit;
    }

    public String getUsedUniformCredit() {
        return mUsedUniformCredit;
    }

    public void setUsedUniformCredit(String usedUniformCredit) {
        mUsedUniformCredit = usedUniformCredit;
    }

    public String getZenPrint() {
        return mZenPrint;
    }

    public void setZenPrint(String zenPrint) {
        mZenPrint = zenPrint;
    }

}
