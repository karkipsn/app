package com.example.colors2web.zummix_app.POJO.SpecialPOJO;

import com.example.colors2web.zummix_app.POJO.PostSearch.Boxes;
import com.example.colors2web.zummix_app.POJO.PostSearch.CustomerItems;

public class MergeBCI {
// TO merge two Arrays of two lists with their Current position in single POJO Class

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
