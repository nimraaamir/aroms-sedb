package se.aroms;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Order implements Serializable {

    private Date orderTime;
    private ArrayList<items> orderItems;
    private String orderId;
    private String uid;

    Order(ArrayList<items> orderItems, String orderId, String uid){
        this.orderItems = orderItems;
        this.orderId = orderId;
        this.uid = uid;
        orderTime = new Date();
    }
    void setOrderTime(String date){
        SimpleDateFormat dateformat2 = new SimpleDateFormat("dd-MM-yyyy");
        try {
            this.orderTime = dateformat2.parse(date);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    ArrayList <items> getOrderItems(){
        return  orderItems;
    }
    Date getOrderTime(){
        return orderTime;
    }

}
