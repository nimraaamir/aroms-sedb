package com.example.aroms;

public class order {
    String orderid;
    String itemid;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getChef() {
        return chef;
    }

    public void setChef(String chef) {
        this.chef = chef;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    String priority;
    String chef;
    String status;

    public order(String orderid, String itemid, String priority, String chef, String status) {
        this.orderid = orderid;
        this.itemid = itemid;
        this.priority = priority;
        this.chef = chef;
        this.status = status;
    }
    public order() {
        }


}
