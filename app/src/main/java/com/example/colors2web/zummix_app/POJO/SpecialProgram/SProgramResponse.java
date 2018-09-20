
package com.example.colors2web.zummix_app.POJO.SpecialProgram;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class SProgramResponse {

    @SerializedName("message")
    private String mMessage;
    @SerializedName("returnType")
    private String mReturnType;
    @SerializedName("specialPrograms")
    private List<SpecialProgram> mSpecialPrograms;

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

    public List<SpecialProgram> getSpecialPrograms() {
        return mSpecialPrograms;
    }

    public void setSpecialPrograms(List<SpecialProgram> specialPrograms) {
        mSpecialPrograms = specialPrograms;
    }

}
