package com.example.colors2web.zummix_app.POJO.SpecialPOJO;

public class StatesPOJO {
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

    public StatesPOJO(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public StatesPOJO() {
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Country) {
            StatesPOJO c = (StatesPOJO) obj;

            if (c.getName().equals(name) && c.getCode() == code)
                return true;
        }

        return false;
    }
}
