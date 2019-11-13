package se.aroms;

public class inventory_item {

    public String uid;
    public String name;
    public String quantity;
    public String threshold;

    inventory_item(){}

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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getThreshold() {
        return threshold;
    }

    public inventory_item(String uid, String name, String quantity, String threshold) {
        this.uid = uid;
        this.name = name;
        this.quantity = quantity;
        this.threshold = threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }
}
