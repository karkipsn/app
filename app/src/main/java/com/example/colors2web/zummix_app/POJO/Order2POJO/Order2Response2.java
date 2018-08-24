package com.example.colors2web.zummix_app.POJO.Order2POJO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Order2Response2 {

        @SerializedName("message")
        private String mMessage;

        public OrderDetails getM2OrderDetails() {
            return m2OrderDetails;
        }

        public void setM2OrderDetails(OrderDetails m2OrderDetails) {
            this.m2OrderDetails = m2OrderDetails;
        }

        @SerializedName("orderDetails")
        private Order2Response2.OrderDetails m2OrderDetails;
        @SerializedName("returnType")
        private String mReturnType;

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

        public class OrderDetails {

            @SerializedName("boxes")
            private String mBox;
            @SerializedName("item_details")
            private List<ItemDetail> mItemDetails;
            @SerializedName("masterBoxes")
            private String mMasterBoxes;
            @SerializedName("order")
            private Order mOrder;
            @SerializedName("orderLogs")
            private List<OrderLog> mOrderLogs;
            @SerializedName("orderShippingAddresses_details")
            private List<OrderShippingAddressesDetail> mOrderShippingAddressesDetails;
            @SerializedName("refundedItems")
            private List<Object> mRefundedItems;
            @SerializedName("return_company_name")
            private String mReturnCompanyName;
            private String cusmessage;

            public String getCusmessage() {
                return cusmessage;
            }

            public void setCusmessage(String cusmessage) {
                this.cusmessage = cusmessage;
            }

            public String getBoxes() {
                return mBox;
            }

            public void setBoxes() {
                this.mBox =mBox;
            }

            public List<ItemDetail> getItemDetails() {
                return mItemDetails;
            }

            public void setItemDetails(List<ItemDetail> itemDetails) {
                mItemDetails = itemDetails;
            }

            public String getMasterBoxes() {
                return mMasterBoxes;
            }

            public void setMasterBoxes(String masterBoxes) {
                mMasterBoxes = masterBoxes;
            }

            public Order getOrder() {
                return mOrder;
            }

            public void setOrder(Order order) {
                mOrder = order;
            }

            public List<OrderLog> getOrderLogs() {
                return mOrderLogs;
            }

            public void setOrderLogs(List<OrderLog> orderLogs) {
                mOrderLogs = orderLogs;
            }

            public List<OrderShippingAddressesDetail> getOrderShippingAddressesDetails() {
                return mOrderShippingAddressesDetails;
            }

            public void setOrderShippingAddressesDetails(List<OrderShippingAddressesDetail> orderShippingAddressesDetails) {
                mOrderShippingAddressesDetails = orderShippingAddressesDetails;
            }

            public List<Object> getRefundedItems() {
                return mRefundedItems;
            }

            public void setRefundedItems(List<Object> refundedItems) {
                mRefundedItems = refundedItems;
            }

            public String getReturnCompanyName() {
                return mReturnCompanyName;
            }

            public void setReturnCompanyName(String returnCompanyName) {
                mReturnCompanyName = returnCompanyName;
            }

        }}

