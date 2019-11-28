package se.aroms.Devdroids;

import java.util.Date;
import java.util.List;

public class order_queue {
    private Date orderTIme;
    private List<order_queue_items> orderItems;
    private String orderId;//unique order if
    private String uid;//table no
    private int order_status;// 0 in queue 1 cooking 2 cooked

    public order_queue() {
    }

    public order_queue(Date orderTIme, List<order_queue_items> orderItems, String orderId, String uid, int order_status) {
        this.orderTIme = orderTIme;
        this.orderItems = orderItems;
        this.orderId = orderId;
        this.uid = uid;
        this.order_status = order_status;
    }

    public Date getOrderTIme() {
        return orderTIme;
    }

    public void setOrderTIme(Date orderTIme) {
        this.orderTIme = orderTIme;
    }

    public List<order_queue_items> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<order_queue_items> orderItems) {
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

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }
    public void addItem(order_queue_items item)
    {
        this.orderItems.add(item);
    }
}
