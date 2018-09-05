package com.example.colors2web.zummix_app.POJO.SpecialPOJO;

public class Country {

    String name;
    String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Country(String name, String cus_id) {
        this.name = name;
        this.code = cus_id;
    }

    public Country() {

    }



    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Country) {
            Country c = (Country) obj;

            if (c.getName().equals(name) && c.getCode() == code)
                return true;
        }

        return false;
    }
}


