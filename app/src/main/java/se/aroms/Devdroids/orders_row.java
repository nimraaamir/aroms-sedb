package se.aroms.Devdroids;

public class orders_row {
    private String dishName;
    private String quantity;
    private String dishId;

    public orders_row(String dishName, String quantity, String dishId) {
        this.dishName = dishName;
        this.quantity = quantity;
        this.dishId = dishId;
    }
    public orders_row(){

    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }
}
