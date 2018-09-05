
package com.example.colors2web.zummix_app.POJO.ProblemSKU;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class ProblemResponse {

    @SerializedName("message")
    private String mMessage;
    @SerializedName("returnType")
    private String mReturnType;
    @SerializedName("UOMs")
    private List<UOM> mUOMs;

    @SerializedName("problemSkus")
    private List<ProblemSKUs> mProblemSKUs;

    @SerializedName("packages")
    private List<Packages> mPackages;

    public List<Packages> getmPackages() {
        return mPackages;
    }

    public void setmPackages(List<Packages> mPackages) {
        this.mPackages = mPackages;
    }

    public List<ProblemSKUs> getmProblemSKUs() {
        return mProblemSKUs;
    }

    public void setmProblemSKUs(List<ProblemSKUs> mProblemSKUs) {
        this.mProblemSKUs = mProblemSKUs;
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

    public List<UOM> getUOMs() {
        return mUOMs;
    }

    public void setUOMs(List<UOM> uOMs) {
        mUOMs = uOMs;
    }

}
