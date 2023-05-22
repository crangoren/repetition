package io;

import java.io.Serializable;

public class Bike implements Serializable {

    private static final long version = 1l;

    private String name;

    private transient String serialNo;

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public Bike(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "name='" + name + '\'' +
                ", serialNo='" + serialNo + '\'' +
                '}';
    }
}
