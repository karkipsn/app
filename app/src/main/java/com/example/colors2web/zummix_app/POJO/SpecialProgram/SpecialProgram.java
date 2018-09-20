
package com.example.colors2web.zummix_app.POJO.SpecialProgram;

import com.google.gson.annotations.SerializedName;

public class SpecialProgram {

    @SerializedName("company_name")
    private String mCompanyName;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("customer_id")
    private Long mCustomerId;
    @SerializedName("deleted_at")
    private Object mDeletedAt;
    @SerializedName("id")
    private Long mId;
    @SerializedName("program_description")
    private String mProgramDescription;
    @SerializedName("program_name")
    private String mProgramName;
    @SerializedName("special_program_number")
    private String mSpecialProgramNumber;
    @SerializedName("updated_at")
    private String mUpdatedAt;

    public SpecialProgram() {
    }

    public SpecialProgram(Long mCustomerId, String mProgramDescription, String mProgramName, String mSpecialProgramNumber) {
        this.mCustomerId = mCustomerId;
        this.mProgramDescription = mProgramDescription;
        this.mProgramName = mProgramName;
        this.mSpecialProgramNumber = mSpecialProgramNumber;
    }

    public SpecialProgram(String mProgramDescription, String mProgramName) {
        this.mProgramDescription = mProgramDescription;
        this.mProgramName = mProgramName;
    }

    public String getCompanyName() {
        return mCompanyName;
    }

    public void setCompanyName(String companyName) {
        mCompanyName = companyName;
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

    public String getProgramDescription() {
        return mProgramDescription;
    }

    public void setProgramDescription(String programDescription) {
        mProgramDescription = programDescription;
    }

    public String getProgramName() {
        return mProgramName;
    }

    public void setProgramName(String programName) {
        mProgramName = programName;
    }

    public String getSpecialProgramNumber() {
        return mSpecialProgramNumber;
    }

    public void setSpecialProgramNumber(String specialProgramNumber) {
        mSpecialProgramNumber = specialProgramNumber;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
