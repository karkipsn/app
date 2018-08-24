
package com.example.colors2web.zummix_app.POJO.CityBins;

import com.google.gson.annotations.SerializedName;

public class CityBins {

    @SerializedName("address1")
    private String mAddress1;
    @SerializedName("address2")
    private String mAddress2;
    @SerializedName("bin")
    private String mBin;
    @SerializedName("city")
    private String mCity;
    @SerializedName("company_email")
    private String mCompanyEmail;
    @SerializedName("company_name")
    private String mCompanyName;
    @SerializedName("country")
    private String mCountry;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("customer_id")
    private String mCustomerId;
    @SerializedName("id")
    private Long mId;
    @SerializedName("manager1_email")
    private String mManager1Email;
    @SerializedName("manager1_name")
    private String mManager1Name;
    @SerializedName("manager2_email")
    private String mManager2Email;
    @SerializedName("manager2_name")
    private String mManager2Name;
    @SerializedName("manager2_phone")
    private String mManager2Phone;

    @SerializedName("manager1_phone")
    private String mManager1Phone;


//



    public String getmAddress1() {
        return mAddress1;
    }

    public void setmAddress1(String mAddress1) {
        this.mAddress1 = mAddress1;
    }

    public String getmManager2Phone() {
        return mManager2Phone;
    }

    public void setmManager2Phone(String mManager2Phone) {
        this.mManager2Phone = mManager2Phone;
    }

    public String getmManager1Phone() {
        return mManager1Phone;
    }

    public void setmManager1Phone(String mManager1Phone) {
        this.mManager1Phone = mManager1Phone;
    }

    @SerializedName("ship_to_name")
    private String mShipToName;
    @SerializedName("state")
    private String mState;
    @SerializedName("zip")
    private String mZip;
    @SerializedName("no_of_bins")
    private Long mNoOfBins;
    @SerializedName("created_by")
    private String mCreatedBy;
    @SerializedName("updated_by")
    private String mUpdatedBy;

    public String getmCreatedBy() {
        return mCreatedBy;
    }

    public void setmCreatedBy(String mCreatedBy) {
        this.mCreatedBy = mCreatedBy;
    }

    public String getmUpdatedBy() {
        return mUpdatedBy;
    }

    public void setmUpdatedBy(String mUpdatedBy) {
        this.mUpdatedBy = mUpdatedBy;
    }

    public CityBins() {
    }

    public CityBins(String mBin, String mShipToName, String mAddress1, String mAddress2, String mCity,
                    String mCountry, String mState, String mZip, String mManager1Name,
                    String mManager1Email, String mManager1Phone, String mManager2Name,
                    String mManager2Email, String mManager2Phone) {

        this.mBin = mBin;
        this.mShipToName = mShipToName;
        this.mAddress1 = mAddress1;
        this.mAddress2 = mAddress2;
        this.mCity = mCity;
        this.mCountry = mCountry;
        this.mState = mState;
        this.mZip = mZip;
        this.mManager1Name = mManager1Name;
        this.mManager1Email = mManager1Email;
        this.mManager1Phone = mManager1Phone;
        this.mManager2Name = mManager2Name;
        this.mManager2Email = mManager2Email;
        this.mManager2Phone = mManager2Phone;


    }


    public Long getmNoOfBins() {
        return mNoOfBins;
    }

    public void setmNoOfBins(Long mNoOfBins) {
        this.mNoOfBins = mNoOfBins;
    }

    public String getAddress1() {
        return mAddress1;
    }

    public void setAddress1(String address1) {
        mAddress1 = address1;
    }

    public String getAddress2() {
        return mAddress2;
    }

    public void setAddress2(String address2) {
        mAddress2 = address2;
    }

    public String getBin() {
        return mBin;
    }

    public void setBin(String bin) {
        mBin = bin;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getCompanyEmail() {
        return mCompanyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        mCompanyEmail = companyEmail;
    }

    public String getCompanyName() {
        return mCompanyName;
    }

    public void setCompanyName(String companyName) {
        mCompanyName = companyName;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getCustomerId() {
        return mCustomerId;
    }

    public void setCustomerId(String customerId) {
        mCustomerId = customerId;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getManager1Email() {
        return mManager1Email;
    }

    public void setManager1Email(String manager1Email) {
        mManager1Email = manager1Email;
    }

    public String getManager1Name() {
        return mManager1Name;
    }

    public void setManager1Name(String manager1Name) {
        mManager1Name = manager1Name;
    }

    public String getManager2Email() {
        return mManager2Email;
    }

    public void setManager2Email(String manager2Email) {
        mManager2Email = manager2Email;
    }

    public String getManager2Name() {
        return mManager2Name;
    }

    public void setManager2Name(String manager2Name) {
        mManager2Name = manager2Name;
    }

    public String getShipToName() {
        return mShipToName;
    }

    public void setShipToName(String shipToName) {
        mShipToName = shipToName;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

    public String getZip() {
        return mZip;
    }

    public void setZip(String zip) {
        mZip = zip;
    }

    public CityBins(String mAddress1, String mAddress2, String mBin, String mCity, String mCountry,
                    String mCustomerId, String mManager1Email, String mManager1Name, String mManager2Email,
                    String mManager2Name, String mManager2Phone, String mManager1Phone, String mShipToName,
                    String mState, String mZip, String mCreatedBy, String mUpdatedBy) {

        this.mAddress1 = mAddress1;
        this.mAddress2 = mAddress2;
        this.mBin = mBin;
        this.mCity = mCity;
        this.mCountry = mCountry;
        this.mCustomerId = mCustomerId;
        this.mManager1Email = mManager1Email;
        this.mManager1Name = mManager1Name;
        this.mManager2Email = mManager2Email;
        this.mManager2Name = mManager2Name;
        this.mManager2Phone = mManager2Phone;
        this.mManager1Phone = mManager1Phone;
        this.mShipToName = mShipToName;
        this.mState = mState;
        this.mZip = mZip;
        this.mCreatedBy = mCreatedBy;
        this.mUpdatedBy = mUpdatedBy;
    }
}
