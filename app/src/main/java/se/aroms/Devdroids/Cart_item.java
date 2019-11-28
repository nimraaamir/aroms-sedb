package se.aroms.Devdroids;



public class Cart_item {
    private cart_Items Items;
    private int quantity;

    public Cart_item(cart_Items items, int quantity) {
        Items = items;
        this.quantity = quantity;
    }
    public Cart_item(){

    }


    public cart_Items getItems() {
        return Items;
    }

    public void setItems(cart_Items items) {
        Items = items;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

