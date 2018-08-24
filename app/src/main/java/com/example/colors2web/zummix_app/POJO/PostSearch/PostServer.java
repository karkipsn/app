package com.example.colors2web.zummix_app.POJO.PostSearch;

public class PostServer {
    String item_sku;
    String customer_id;
    String from;
    String to;

    public PostServer(String item_sku, String customer_id, String from, String to) {
        this.item_sku = item_sku;
        this.customer_id = customer_id;
        this.from = from;
        this.to = to;
    }

    public PostServer(String customer_id, String from, String to) {
        this.customer_id = customer_id;
        this.from = from;
        this.to = to;
    }

    public String getItem_sku() {
        return item_sku;
    }

    public void setItem_sku(String item_sku) {
        this.item_sku = item_sku;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
