package se.aroms;

public class menu_item {

    public String uid;
    public String name;
    public String reg_price;
    public String large_price;
    public String time;
    public String type;

    menu_item(){}

    public menu_item(String uid, String name, String reg_price, String large_price, String time, String type) {
        this.uid = uid;
        this.name = name;
        this.reg_price = reg_price;
        this.large_price = large_price;
        this.time = time;
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReg_price() {
        return reg_price;
    }

    public void setReg_price(String reg_price) {
        this.reg_price = reg_price;
    }

    public String getLarge_price() {
        return large_price;
    }

    public void setLarge_price(String large_price) {
        this.large_price = large_price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
