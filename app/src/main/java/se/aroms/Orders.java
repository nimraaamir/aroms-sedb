package se.aroms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Orders implements Serializable {
    private ArrayList<Dishes> dishes;
    private String Priority;

    public Orders(ArrayList<Dishes> dishes) {
        this.dishes = dishes;
        this.Priority = "Normal";
    }
    public Orders() {
        this.dishes = new ArrayList<>();
        this.Priority = "Normal";
    }

    public List<Dishes> getDishes() {
        return dishes;
    }

    public void setDishes(ArrayList<Dishes> dishes) {
        this.dishes = dishes;
    }
    public void addDishes(Dishes dish){this.dishes.add(dish);}

    public String getPriority() {
        return Priority;
    }

    public void setPriority(String priority) {
        Priority = priority;
    }
}
