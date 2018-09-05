package com.example.colors2web.zummix_app.POJO.OrderEdit;

public class EditOrderType {

    String order_type;
    String updated_by;

    public EditOrderType(String order_type, String updated_by) {
        this.order_type = order_type;
        this.updated_by = updated_by;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }
}
