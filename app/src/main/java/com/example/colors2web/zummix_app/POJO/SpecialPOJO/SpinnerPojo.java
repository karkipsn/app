package com.example.colors2web.zummix_app.POJO.SpecialPOJO;

import android.os.Parcel;
import android.os.Parcelable;

public class SpinnerPojo implements Parcelable {

    String name;
    Long cus_id;

    public SpinnerPojo(String name, Long cus_id) {
        this.name = name;
        this.cus_id = cus_id;
    }

    public SpinnerPojo() {

    }

    protected SpinnerPojo(Parcel in) {
        name = in.readString();
        if (in.readByte() == 0) {
            cus_id = null;
        } else {
            cus_id = in.readLong();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        if (cus_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(cus_id);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SpinnerPojo> CREATOR = new Creator<SpinnerPojo>() {
        @Override
        public SpinnerPojo createFromParcel(Parcel in) {
            return new SpinnerPojo(in);
        }

        @Override
        public SpinnerPojo[] newArray(int size) {
            return new SpinnerPojo[size];
        }
    };

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
//    What you intended to DIsplay there just return that in that format
//    https://www.codevoila.com/post/65/java-json-tutorial-and-example-json-java-orgjson//Javabeans Concept
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof SpinnerPojo) {
            SpinnerPojo c = (SpinnerPojo) obj;

            if (c.getName().equals(name) && c.getCus_id() == cus_id)
                return true;
        }

        return false;
    }
}
