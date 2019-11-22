package se.aroms.Devdroids;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
    private Date orderTIme;
    private List<items> orderItems;
    private String orderId;//unique order if
    private String uid;//table no
    private String priority;

    public Order(Date orderTIme, List<items> orderItems, String orderId, String uid) {
        this.orderTIme = orderTIme;
        this.orderItems = orderItems;
        this.orderId = orderId;
        this.uid = uid;
        this.priority = "Normal";
    }
    public Order(Order order){
        this.orderId = order.orderId;
        this.orderItems = order.orderItems;
        this.uid = order.uid;
        this.orderTIme = order.orderTIme;
        this.priority = "Normal";
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Order(){
        this.priority = "Normal";
    }

    public Date getOrderTIme() {
        return orderTIme;
    }

    public void setOrderTIme(Date orderTIme) {
        this.orderTIme = orderTIme;
    }

    public List<items> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<items> orderItems) {
        this.orderItems = orderItems;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
