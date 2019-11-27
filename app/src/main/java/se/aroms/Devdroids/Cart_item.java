package se.aroms.Devdroids;



public class Cart_item {
    private Item_cart Items;
    private int quantity;

    public Cart_item(Item_cart items, int quantity) {
        Items = items;
        this.quantity = quantity;
    }
    public Cart_item(){

    }


    public Item_cart getItems() {
        return Items;
    }

    public void setItems(Item_cart items) {
        Items = items;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

