
package com.example.colors2web.zummix_app.POJO.CityBins;

import com.google.gson.annotations.SerializedName;

public class CityEditResponse {

    @SerializedName("cityBin")
    private CityBin mCityBin;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("returnType")
    private String mReturnType;

    public CityBin getCityBin() {
        return mCityBin;
    }

    public void setCityBin(CityBin cityBin) {
        mCityBin = cityBin;
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
