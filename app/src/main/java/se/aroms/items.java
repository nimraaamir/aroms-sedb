package se.aroms;

import java.io.Serializable;

public class items implements Serializable {

    private String ItemID;
    private String size;
    private double price;
    private double incured_price;

    items(String id, String size, double price, double incured_price){
        ItemID = id;
        this.size = size;
        this.price = price;
        this.incured_price = incured_price;
    }

    String getItemID(){
        return ItemID;
    }
    String getSize(){
        return size;
    }
    double getPrice(){
        return price;
    }
    double getIncured_price(){
        return incured_price;
    }

}
