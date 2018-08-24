package com.example.colors2web.zummix_app.Activities.PostActivity;

public class SpinnerPojo {
    String name;
    Long cus_id;

    public SpinnerPojo(String name, Long cus_id) {
        this.name = name;
        this.cus_id = cus_id;
    }

    public SpinnerPojo() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCus_id() {
        return cus_id;
    }

    public void setCus_id(Long cus_id) {
        this.cus_id = cus_id;
    }

    //to display object as a string in spinner
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {

        if(obj instanceof SpinnerPojo){
            SpinnerPojo c = (SpinnerPojo ) obj;
            if(c.getName().equals(name) && c.getCus_id()== cus_id )
                return true;
        }

        return false;
    }
}
