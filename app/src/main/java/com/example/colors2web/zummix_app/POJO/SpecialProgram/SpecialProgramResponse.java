
package com.example.colors2web.zummix_app.POJO.SpecialProgram;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SpecialProgramResponse {

    @SerializedName("customers")
    private List<Customer> mCustomers;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("returnType")
    private String mReturnType;

    @SerializedName("specialPrograms")
    private List<SpecialProgram> mSpecialPrograms;

    public List<SpecialProgram> getmSpecialPrograms() {
        return mSpecialPrograms;
    }

    public void setmSpecialPrograms(List<SpecialProgram> mSpecialPrograms) {
        this.mSpecialPrograms = mSpecialPrograms;
    }

    public List<Customer> getCustomers() {
        return mCustomers;
    }

    public void setCustomers(List<Customer> customers) {
        mCustomers = customers;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getReturnType() {
        return mReturnType;
    }

    public void setReturnType(String returnType) {
        mReturnType = returnType;
    }

}
