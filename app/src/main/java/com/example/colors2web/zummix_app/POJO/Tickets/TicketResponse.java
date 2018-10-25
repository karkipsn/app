package com.example.colors2web.zummix_app.POJO.Tickets;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TicketResponse {

        @SerializedName("tickets")
        private List<Tickets> mTickets;
        @SerializedName("message")
        private String mMessage;
        @SerializedName("returnType")
        private String mReturnType;

        public List<Tickets> getTickets() {
            return mTickets;
        }

        public void setTickets(List<Tickets> tickets) {
            mTickets = tickets;
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
