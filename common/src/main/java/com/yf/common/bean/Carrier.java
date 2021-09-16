package com.yf.common.bean;

import java.io.Serializable;


public class Carrier implements Serializable {
    /**主键（没有的话可以不用）*/
    private int id;
    /**托盘号*/
    private String trayNo;
    /**库位号*/
    private String locationNo;
    /**托盘EPC*/
    private String trayEPC;
    /**库位EPC*/
    private String locationEPC;

    public Carrier(String trayNo, String locationNo) {
        this.trayNo = trayNo;
        this.locationNo = locationNo;
    }

    public Carrier() {
    }

    public void clear(){
        this.id=0;
        this.trayNo="";
        this.locationNo="";
        this.trayEPC="";
        this.locationEPC="";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrayNo() {
        return trayNo;
    }

    public void setTrayNo(String trayNo) {
        this.trayNo = trayNo;
    }

    public String getLocationNo() {
        return locationNo;
    }

    public void setLocationNo(String locationNo) {
        this.locationNo = locationNo;
    }

    public String getTrayEPC() {
        return trayEPC;
    }

    public void setTrayEPC(String trayEPC) {
        this.trayEPC = trayEPC;
    }

    public String getLocationEPC() {
        return locationEPC;
    }

    public void setLocationEPC(String locationEPC) {
        this.locationEPC = locationEPC;
    }
}
