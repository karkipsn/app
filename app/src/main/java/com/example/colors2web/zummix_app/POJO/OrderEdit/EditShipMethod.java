package com.example.colors2web.zummix_app.POJO.OrderEdit;

public class EditShipMethod {

    String ship_method;
    String updated_by;

    public String getShip_method() {
        return ship_method;
    }

    public void setShip_method(String ship_method) {
        this.ship_method = ship_method;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public EditShipMethod(String ship_method, String updated_by) {
        this.ship_method = ship_method;
        this.updated_by = updated_by;
    }
}
