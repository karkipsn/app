
package com.example.colors2web.zummix_app.POJO.MasterBoxSearch;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MasterBoxResponse {

    @SerializedName("company_details")
    private CompanyDetails mCompanyDetails;
    @SerializedName("fieldOffice")
    private FieldOffice mFieldOffice;
    @SerializedName("masterBoxes")
    private List<MasterBox> mMasterBoxes;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("orderShippingAddress")
    private OrderShippingAddress mOrderShippingAddress;
    @SerializedName("returnType")
    private String mReturnType;

    @SerializedName("boxes")
    private List<Boxes> mBoxes;



    @SerializedName("lineItems")
    private List<LineItems> mLineItems;

    public List<LineItems> getmLineItems() {
        return mLineItems;
    }

    public void setmLineItems(List<LineItems> mLineItems) {
        this.mLineItems = mLineItems;
    }

    public CompanyDetails getCompanyDetails() {
        return mCompanyDetails;
    }

    public void setCompanyDetails(CompanyDetails companyDetails) {
        mCompanyDetails = companyDetails;
    }

    public FieldOffice getFieldOffice() {
        return mFieldOffice;
    }

    public void setFieldOffice(FieldOffice fieldOffice) {
        mFieldOffice = fieldOffice;
    }

    public List<MasterBox> getMasterBoxes() {
        return mMasterBoxes;
    }

    public void setMasterBoxes(List<MasterBox> masterBoxes) {
        mMasterBoxes = masterBoxes;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public OrderShippingAddress getOrderShippingAddress() {
        return mOrderShippingAddress;
    }

    public void setOrderShippingAddress(OrderShippingAddress orderShippingAddress) {
        mOrderShippingAddress = orderShippingAddress;
    }

    public List<Boxes> getmBoxes() {
        return mBoxes;
    }

    public void setmBoxes(List<Boxes> mBoxes) {
        this.mBoxes = mBoxes;
    }

    public String getReturnType() {
        return mReturnType;
    }

    public void setReturnType(String returnType) {
        mReturnType = returnType;
    }

}
