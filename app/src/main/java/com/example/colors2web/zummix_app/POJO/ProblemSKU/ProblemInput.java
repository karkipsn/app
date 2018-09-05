package com.example.colors2web.zummix_app.POJO.ProblemSKU;

public class ProblemInput {
    String price;
    String weight;
    String uom;
    String country_origin;

    public ProblemInput(String price, String weight, String uom, String country_origin) {
        this.price = price;
        this.weight = weight;
        this.uom = uom;
        this.country_origin = country_origin;
    }

    public String getPrice() {
        return price;

    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getCountry_origin() {
        return country_origin;
    }

    public void setCountry_origin(String country_origin) {
        this.country_origin = country_origin;
    }
}
