package com.example.colors2web.zummix_app.POJO.PostSearch;

import com.google.gson.annotations.SerializedName;

public class MergeBCI {


    public CustomerItems getCi() {
        return ci;
    }

    public void setCi(CustomerItems ci) {
        this.ci = ci;
    }

    public Boxes getBi() {
        return bi;
    }

    public void setBi(Boxes bi) {
        this.bi = bi;
    }


    private CustomerItems ci;
    private Boxes bi;

    public MergeBCI(CustomerItems ci) {
        this.ci = ci;
    }

    public MergeBCI(CustomerItems ci, Boxes bi) {
        this.ci =ci;
        this.bi =bi;

    }

}
