package com.auidbook.prototype.enums;

/**
 * Created by mgundappan on 14-06-2016.
 */
public enum RequestState {

    Others("Others"),
    Pending("Pending"),
    Approved("Approved"),
    Donated("Donated"),
    Rejected("Rejected");

    private String value;
    RequestState(String value){
        this.value=value;
    }

    public static RequestState getEnum(String value) {
        for(RequestState v : values())
            if(v.getValue().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }

    public String getValue(){
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
