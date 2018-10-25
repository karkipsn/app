
package com.example.colors2web.zummix_app.POJO.Tickets;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class ReturnTicketResponse {

    @SerializedName("message")
    private String mMessage;
    @SerializedName("returnTickets")
    private List<ReturnTicket> mReturnTickets;
    @SerializedName("returnType")
    private String mReturnType;

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public List<ReturnTicket> getReturnTickets() {
        return mReturnTickets;
    }

    public void setReturnTickets(List<ReturnTicket> returnTickets) {
        mReturnTickets = returnTickets;
    }

    public String getReturnType() {
        return mReturnType;
    }

    public void setReturnType(String returnType) {
        mReturnType = returnType;
    }

}
