
package com.example.colors2web.zummix_app.POJO.OrderSearch;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class OrderDetails implements Parcelable {

    @SerializedName("applied_credit")
    private String mAppliedCredit;
    @SerializedName("batch_number")
    private Long mBatchNumber;
    @SerializedName("commit")
    private String mCommit;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("created_by")
    private Long mCreatedBy;
    @SerializedName("csv_action_log_id")
    private Long mCsvActionLogId;
    @SerializedName("customer_company_name")
    private Object mCustomerCompanyName;
    @SerializedName("customer_email")
    private String mCustomerEmail;
    @SerializedName("customer_id")
    private Long mCustomerId;
    @SerializedName("customer_name")
    private String mCustomerName;
    @SerializedName("department")
    private String mDepartment;
    @SerializedName("donot_arrive_before")
    private Object mDonotArriveBefore;
    @SerializedName("edit_shipping_address")
    private String mEditShippingAddress;
    @SerializedName("employee_id")
    private String mEmployeeId;
    @SerializedName("group_ship_id")
    private Long mGroupShipId;
    @SerializedName("id")
    private Long mId;
    @SerializedName("in_hands_by")
    private Object mInHandsBy;
    @SerializedName("is_batch_printed")
    private Long mIsBatchPrinted;
    @SerializedName("is_claimed")
    private String mIsClaimed;
    @SerializedName("is_paused")
    private String mIsPaused;
    @SerializedName("is_pp_request_created")
    private Long mIsPpRequestCreated;
    @SerializedName("is_ready_to_batch")
    private String mIsReadyToBatch;
    @SerializedName("is_refunded")
    private String mIsRefunded;
    @SerializedName("is_return_label")
    private String mIsReturnLabel;
    @SerializedName("is_ship_it_now_batch")
    private Long mIsShipItNowBatch;
    @SerializedName("order_channel")
    private String mOrderChannel;
    @SerializedName("order_date")
    private String mOrderDate;
    @SerializedName("order_number")
    private String mOrderNumber;
    @SerializedName("order_special_instruction")
    private String mOrderSpecialInstruction;
    @SerializedName("order_status")
    private String mOrderStatus;
    @SerializedName("order_token")
    private String mOrderToken;
    @SerializedName("order_type")
    private String mOrderType;
    @SerializedName("pre_sale")
    private String mPreSale;
    @SerializedName("print_item")
    private String mPrintItem;
    @SerializedName("problem_order")
    private String mProblemOrder;
    @SerializedName("refund_note")
    private String mRefundNote;
    @SerializedName("refund_type")
    private String mRefundType;
    @SerializedName("refunded_at")
    private String mRefundedAt;
    @SerializedName("refunded_user_email")
    private String mRefundedUserEmail;
    @SerializedName("refunded_user_id")
    private Long mRefundedUserId;
    @SerializedName("refunded_user_name")
    private String mRefundedUserName;
    @SerializedName("ship_method")
    private String mShipMethod;
    @SerializedName("shopping_list_number")
    private String mShoppingListNumber;
    @SerializedName("special_program_order")
    private String mSpecialProgramOrder;
    @SerializedName("tag")
    private String mTag;
    @SerializedName("updated_at")
    private String mUpdatedAt;
    @SerializedName("updated_by")
    private Long mUpdatedBy;
    @SerializedName("was_token_order")
    private String mWasTokenOrder;

    public OrderDetails() {

        }

    public OrderDetails(Parcel in) {
        mAppliedCredit = in.readString();
        if (in.readByte() == 0) {
            mBatchNumber = null;
        } else {
            mBatchNumber = in.readLong();
        }
        mCommit = in.readString();
        mCreatedAt = in.readString();
        if (in.readByte() == 0) {
            mCreatedBy = null;
        } else {
            mCreatedBy = in.readLong();
        }
        if (in.readByte() == 0) {
            mCsvActionLogId = null;
        } else {
            mCsvActionLogId = in.readLong();
        }
        mCustomerEmail = in.readString();
        if (in.readByte() == 0) {
            mCustomerId = null;
        } else {
            mCustomerId = in.readLong();
        }
        mCustomerName = in.readString();
        mDepartment = in.readString();
        mEditShippingAddress = in.readString();
        mEmployeeId = in.readString();
        if (in.readByte() == 0) {
            mGroupShipId = null;
        } else {
            mGroupShipId = in.readLong();
        }
        if (in.readByte() == 0) {
            mId = null;
        } else {
            mId = in.readLong();
        }
        if (in.readByte() == 0) {
            mIsBatchPrinted = null;
        } else {
            mIsBatchPrinted = in.readLong();
        }
        mIsClaimed = in.readString();
        mIsPaused = in.readString();
        if (in.readByte() == 0) {
            mIsPpRequestCreated = null;
        } else {
            mIsPpRequestCreated = in.readLong();
        }
        mIsReadyToBatch = in.readString();
        mIsRefunded = in.readString();
        mIsReturnLabel = in.readString();
        if (in.readByte() == 0) {
            mIsShipItNowBatch = null;
        } else {
            mIsShipItNowBatch = in.readLong();
        }
        mOrderChannel = in.readString();
        mOrderDate = in.readString();
        mOrderNumber = in.readString();
        mOrderSpecialInstruction = in.readString();
        mOrderStatus = in.readString();
        mOrderToken = in.readString();
        mOrderType = in.readString();
        mPreSale = in.readString();
        mPrintItem = in.readString();
        mProblemOrder = in.readString();
        mRefundNote = in.readString();
        mRefundType = in.readString();
        mRefundedAt = in.readString();
        mRefundedUserEmail = in.readString();
        if (in.readByte() == 0) {
            mRefundedUserId = null;
        } else {
            mRefundedUserId = in.readLong();
        }
        mRefundedUserName = in.readString();
        mShipMethod = in.readString();
        mShoppingListNumber = in.readString();
        mSpecialProgramOrder = in.readString();
        mTag = in.readString();
        mUpdatedAt = in.readString();
        if (in.readByte() == 0) {
            mUpdatedBy = null;
        } else {
            mUpdatedBy = in.readLong();
        }
        mWasTokenOrder = in.readString();
    }

    public static final Creator<OrderDetails> CREATOR = new Creator<OrderDetails>() {
        @Override
        public OrderDetails createFromParcel(Parcel in) {
            return new OrderDetails(in);
        }

        @Override
        public OrderDetails[] newArray(int size) {
            return new OrderDetails[size];
        }
    };

    public String getAppliedCredit() {
        return mAppliedCredit;
    }

    public void setAppliedCredit(String appliedCredit) {
        mAppliedCredit = appliedCredit;
    }

    public Long getBatchNumber() {
        return mBatchNumber;
    }

    public void setBatchNumber(Long batchNumber) {
        mBatchNumber = batchNumber;
    }

    public String getCommit() {
        return mCommit;
    }

    public void setCommit(String commit) {
        mCommit = commit;
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

    public Long getCsvActionLogId() {
        return mCsvActionLogId;
    }

    public void setCsvActionLogId(Long csvActionLogId) {
        mCsvActionLogId = csvActionLogId;
    }

    public Object getCustomerCompanyName() {
        return mCustomerCompanyName;
    }

    public void setCustomerCompanyName(Object customerCompanyName) {
        mCustomerCompanyName = customerCompanyName;
    }

    public String getCustomerEmail() {
        return mCustomerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        mCustomerEmail = customerEmail;
    }

    public Long getCustomerId() {
        return mCustomerId;
    }

    public void setCustomerId(Long customerId) {
        mCustomerId = customerId;
    }

    public String getCustomerName() {
        return mCustomerName;
    }

    public void setCustomerName(String customerName) {
        mCustomerName = customerName;
    }

    public String getDepartment() {
        return mDepartment;
    }

    public void setDepartment(String department) {
        mDepartment = department;
    }

    public Object getDonotArriveBefore() {
        return mDonotArriveBefore;
    }

    public void setDonotArriveBefore(Object donotArriveBefore) {
        mDonotArriveBefore = donotArriveBefore;
    }

    public String getEditShippingAddress() {
        return mEditShippingAddress;
    }

    public void setEditShippingAddress(String editShippingAddress) {
        mEditShippingAddress = editShippingAddress;
    }

    public String getEmployeeId() {
        return mEmployeeId;
    }

    public void setEmployeeId(String employeeId) {
        mEmployeeId = employeeId;
    }

    public Long getGroupShipId() {
        return mGroupShipId;
    }

    public void setGroupShipId(Long groupShipId) {
        mGroupShipId = groupShipId;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Object getInHandsBy() {
        return mInHandsBy;
    }

    public void setInHandsBy(Object inHandsBy) {
        mInHandsBy = inHandsBy;
    }

    public Long getIsBatchPrinted() {
        return mIsBatchPrinted;
    }

    public void setIsBatchPrinted(Long isBatchPrinted) {
        mIsBatchPrinted = isBatchPrinted;
    }

    public String getIsClaimed() {
        return mIsClaimed;
    }

    public void setIsClaimed(String isClaimed) {
        mIsClaimed = isClaimed;
    }

    public String getIsPaused() {
        return mIsPaused;
    }

    public void setIsPaused(String isPaused) {
        mIsPaused = isPaused;
    }

    public Long getIsPpRequestCreated() {
        return mIsPpRequestCreated;
    }

    public void setIsPpRequestCreated(Long isPpRequestCreated) {
        mIsPpRequestCreated = isPpRequestCreated;
    }

    public String getIsReadyToBatch() {
        return mIsReadyToBatch;
    }

    public void setIsReadyToBatch(String isReadyToBatch) {
        mIsReadyToBatch = isReadyToBatch;
    }

    public String getIsRefunded() {
        return mIsRefunded;
    }

    public void setIsRefunded(String isRefunded) {
        mIsRefunded = isRefunded;
    }

    public String getIsReturnLabel() {
        return mIsReturnLabel;
    }

    public void setIsReturnLabel(String isReturnLabel) {
        mIsReturnLabel = isReturnLabel;
    }

    public Long getIsShipItNowBatch() {
        return mIsShipItNowBatch;
    }

    public void setIsShipItNowBatch(Long isShipItNowBatch) {
        mIsShipItNowBatch = isShipItNowBatch;
    }

    public String getOrderChannel() {
        return mOrderChannel;
    }

    public void setOrderChannel(String orderChannel) {
        mOrderChannel = orderChannel;
    }

    public String getOrderDate() {
        return mOrderDate;
    }

    public void setOrderDate(String orderDate) {
        mOrderDate = orderDate;
    }

    public String getOrderNumber() {
        return mOrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        mOrderNumber = orderNumber;
    }

    public String getOrderSpecialInstruction() {
        return mOrderSpecialInstruction;
    }

    public void setOrderSpecialInstruction(String orderSpecialInstruction) {
        mOrderSpecialInstruction = orderSpecialInstruction;
    }

    public String getOrderStatus() {
        return mOrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        mOrderStatus = orderStatus;
    }

    public String getOrderToken() {
        return mOrderToken;
    }

    public void setOrderToken(String orderToken) {
        mOrderToken = orderToken;
    }

    public String getOrderType() {
        return mOrderType;
    }

    public void setOrderType(String orderType) {
        mOrderType = orderType;
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

    public String getProblemOrder() {
        return mProblemOrder;
    }

    public void setProblemOrder(String problemOrder) {
        mProblemOrder = problemOrder;
    }

    public String getRefundNote() {
        return mRefundNote;
    }

    public void setRefundNote(String refundNote) {
        mRefundNote = refundNote;
    }

    public String getRefundType() {
        return mRefundType;
    }

    public void setRefundType(String refundType) {
        mRefundType = refundType;
    }

    public String getRefundedAt() {
        return mRefundedAt;
    }

    public void setRefundedAt(String refundedAt) {
        mRefundedAt = refundedAt;
    }

    public String getRefundedUserEmail() {
        return mRefundedUserEmail;
    }

    public void setRefundedUserEmail(String refundedUserEmail) {
        mRefundedUserEmail = refundedUserEmail;
    }

    public Long getRefundedUserId() {
        return mRefundedUserId;
    }

    public void setRefundedUserId(Long refundedUserId) {
        mRefundedUserId = refundedUserId;
    }

    public String getRefundedUserName() {
        return mRefundedUserName;
    }

    public void setRefundedUserName(String refundedUserName) {
        mRefundedUserName = refundedUserName;
    }

    public String getShipMethod() {
        return mShipMethod;
    }

    public void setShipMethod(String shipMethod) {
        mShipMethod = shipMethod;
    }

    public String getShoppingListNumber() {
        return mShoppingListNumber;
    }

    public void setShoppingListNumber(String shoppingListNumber) {
        mShoppingListNumber = shoppingListNumber;
    }

    public String getSpecialProgramOrder() {
        return mSpecialProgramOrder;
    }

    public void setSpecialProgramOrder(String specialProgramOrder) {
        mSpecialProgramOrder = specialProgramOrder;
    }

    public String getTag() {
        return mTag;
    }

    public void setTag(String tag) {
        mTag = tag;
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

    public String getWasTokenOrder() {
        return mWasTokenOrder;
    }

    public void setWasTokenOrder(String wasTokenOrder) {
        mWasTokenOrder = wasTokenOrder;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mAppliedCredit);
        if (mBatchNumber == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mBatchNumber);
        }
        dest.writeString(mCommit);
        dest.writeString(mCreatedAt);
        if (mCreatedBy == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mCreatedBy);
        }
        if (mCsvActionLogId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mCsvActionLogId);
        }
        dest.writeString(mCustomerEmail);
        if (mCustomerId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mCustomerId);
        }
        dest.writeString(mCustomerName);
        dest.writeString(mDepartment);
        dest.writeString(mEditShippingAddress);
        dest.writeString(mEmployeeId);
        if (mGroupShipId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mGroupShipId);
        }
        if (mId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mId);
        }
        if (mIsBatchPrinted == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mIsBatchPrinted);
        }
        dest.writeString(mIsClaimed);
        dest.writeString(mIsPaused);
        if (mIsPpRequestCreated == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mIsPpRequestCreated);
        }
        dest.writeString(mIsReadyToBatch);
        dest.writeString(mIsRefunded);
        dest.writeString(mIsReturnLabel);
        if (mIsShipItNowBatch == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mIsShipItNowBatch);
        }
        dest.writeString(mOrderChannel);
        dest.writeString(mOrderDate);
        dest.writeString(mOrderNumber);
        dest.writeString(mOrderSpecialInstruction);
        dest.writeString(mOrderStatus);
        dest.writeString(mOrderToken);
        dest.writeString(mOrderType);
        dest.writeString(mPreSale);
        dest.writeString(mPrintItem);
        dest.writeString(mProblemOrder);
        dest.writeString(mRefundNote);
        dest.writeString(mRefundType);
        dest.writeString(mRefundedAt);
        dest.writeString(mRefundedUserEmail);
        if (mRefundedUserId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mRefundedUserId);
        }
        dest.writeString(mRefundedUserName);
        dest.writeString(mShipMethod);
        dest.writeString(mShoppingListNumber);
        dest.writeString(mSpecialProgramOrder);
        dest.writeString(mTag);
        dest.writeString(mUpdatedAt);
        if (mUpdatedBy == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mUpdatedBy);
        }
        dest.writeString(mWasTokenOrder);
    }
}
