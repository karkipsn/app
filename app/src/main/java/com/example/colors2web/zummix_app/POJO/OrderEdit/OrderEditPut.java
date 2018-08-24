package com.example.colors2web.zummix_app.POJO.OrderEdit;

public class OrderEditPut {

    private   String customer_fname;
    private String customer_lname;
    private String customer_email;
    private String customer_phone1;

    public OrderEditPut(String customer_fname, String customer_lname, String customer_email, String customer_phone1,
                        String customer_address1, String customer_address2, String customer_city, String customer_country,
                        String customer_state, String customer_zip, String comment, String updated_by) {

        this.customer_fname = customer_fname;
        this.customer_lname = customer_lname;
        this.customer_email = customer_email;
        this.customer_phone1 = customer_phone1;
        this.customer_address1 = customer_address1;
        this.customer_address2 = customer_address2;
        this.customer_city = customer_city;
        this.customer_country = customer_country;
        this.customer_state = customer_state;
        this.customer_zip = customer_zip;
        this.comment = comment;
        this.updated_by = updated_by;
    }

    private String customer_address1;
    private String customer_address2;
    private String customer_city;
    private String customer_country;
    private String customer_state;
    private String customer_zip;
    private String comment;
    private String updated_by;

    public String getCustomer_fname() {
        return customer_fname;
    }

    public void setCustomer_fname(String customer_fname) {
        this.customer_fname = customer_fname;
    }

    public String getCustomer_lname() {
        return customer_lname;
    }

    public void setCustomer_lname(String customer_lname) {
        this.customer_lname = customer_lname;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_phone1() {
        return customer_phone1;
    }

    public void setCustomer_phone1(String customer_phone1) {
        this.customer_phone1 = customer_phone1;
    }

    public String getCustomer_address1() {
        return customer_address1;
    }

    public void setCustomer_address1(String customer_address1) {
        this.customer_address1 = customer_address1;
    }

    public String getCustomer_address2() {
        return customer_address2;
    }

    public void setCustomer_address2(String customer_address2) {
        this.customer_address2 = customer_address2;
    }

    public String getCustomer_city() {
        return customer_city;
    }

    public void setCustomer_city(String customer_city) {
        this.customer_city = customer_city;
    }

    public String getCustomer_country() {
        return customer_country;
    }

    public void setCustomer_country(String customer_country) {
        this.customer_country = customer_country;
    }

    public String getCustomer_state() {
        return customer_state;
    }

    public void setCustomer_state(String customer_state) {
        this.customer_state = customer_state;
    }

    public String getCustomer_zip() {
        return customer_zip;
    }

    public void setCustomer_zip(String customer_zip) {
        this.customer_zip = customer_zip;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }
}
