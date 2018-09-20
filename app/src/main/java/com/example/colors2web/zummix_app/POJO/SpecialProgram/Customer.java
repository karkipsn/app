
package com.example.colors2web.zummix_app.POJO.SpecialProgram;

import com.google.gson.annotations.SerializedName;

public class Customer {

    @SerializedName("company_name")
    private String mCompanyName;
    @SerializedName("id")
    private Long mId;
    @SerializedName("no_of_programs")
    private Long mNoOfPrograms;

    public String getCompanyName() {
        return mCompanyName;
    }

    public void setCompanyName(String companyName) {
        mCompanyName = companyName;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Long getNoOfPrograms() {
        return mNoOfPrograms;
    }

    public void setNoOfPrograms(Long noOfPrograms) {
        mNoOfPrograms = noOfPrograms;
    }

}
