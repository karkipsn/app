package com.example.colors2web.zummix_app.POJO.ProblemSKU;

public class PackageInput {

    String  customer_id;
    String  package_name;
    String  length;
    String  width;
    String  height;
    String  created_by;
    String  updated_by;
    String  weight;
    String  package_sku;
    String  package_cost;
    String  status;
    String  is_global_package;


    public PackageInput(String customer_id, String package_name, String length, String width, String height,
                        String created_by, String updated_by, String weight,
                        String package_sku, String package_cost, String status, String is_global_package) {

        this.customer_id = customer_id;
        this.package_name = package_name;
        this.length = length;
        this.width = width;
        this.height = height;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.weight = weight;
        this.package_sku = package_sku;
        this.package_cost = package_cost;
        this.status = status;
        this.is_global_package = is_global_package;
    }

    public PackageInput(String package_name, String length, String width,
                        String height, String updated_by, String weight, String package_cost, String status) {

        this.package_name = package_name;
        this.length = length;
        this.width = width;
        this.height = height;
        this.updated_by = updated_by;
        this.weight = weight;
        this.package_cost = package_cost;
        this.status = status;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPackage_sku() {
        return package_sku;
    }

    public void setPackage_sku(String package_sku) {
        this.package_sku = package_sku;
    }

    public String getPackage_cost() {
        return package_cost;
    }

    public void setPackage_cost(String package_cost) {
        this.package_cost = package_cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIs_global_package() {
        return is_global_package;
    }

    public void setIs_global_package(String is_global_package) {
        this.is_global_package = is_global_package;
    }
}
