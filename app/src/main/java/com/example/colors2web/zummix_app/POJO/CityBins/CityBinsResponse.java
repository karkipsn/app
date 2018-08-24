
package com.example.colors2web.zummix_app.POJO.CityBins;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CityBinsResponse {

    @SerializedName("cityBins")
    private List<CityBins> mCityBins;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("returnType")
    private String mReturnType;

    public List<CityBins> getCityBins() {
        return mCityBins;
    }

    public void setCityBins(List<CityBins> cityBins) {
        mCityBins = cityBins;
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
