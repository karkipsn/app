package com.example.colors2web.zummix_app.POJO.ProductSearch;

import java.io.Serializable;

public class CustomPOJO implements Serializable {

    String replenish;
    String pick;
    String qoh;
    String pick_balance;
    String o_quantity;
    String committed;
    String a2s;

    public CustomPOJO() {
    }

//    public CustomPOJO(String replenish, String pick, String qoh, String pick_balance,
//                      String o_quantity, String committed, String a2s) {
//        this.replenish = replenish;
//        this.pick = pick;
//        this.qoh = qoh;
//        this.pick_balance = pick_balance;
//        this.o_quantity = o_quantity;
//        this.committed = committed;
//        this.a2s = a2s;
//    }

    public String getReplenish() {
        return replenish;
    }

    public void setReplenish(String replenish) {
        this.replenish = replenish;
    }

    public String getPick() {
        return pick;
    }

    public void setPick(String pick) {
        this.pick = pick;
    }

    public String getQoh() {
        return qoh;
    }

    public void setQoh(String qoh) {
        this.qoh = qoh;
    }

    public String getPick_balance() {
        return pick_balance;
    }

    public void setPick_balance(String pick_balance) {
        this.pick_balance = pick_balance;
    }

    public String getO_quantity() {
        return o_quantity;
    }

    public void setO_quantity(String o_quantity) {
        this.o_quantity = o_quantity;
    }

    public String getCommitted() {
        return committed;
    }

    public void setCommitted(String committed) {
        this.committed = committed;
    }

    public String getA2s() {
        return a2s;
    }

    public void setA2s(String a2s) {
        this.a2s = a2s;
    }
}
