package se.aroms.DevelopersDotCo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import se.aroms.Devdroids.Dishes;
import se.aroms.Devdroids.Order;
import se.aroms.Devdroids.items;

public class CustomRecyclerViewDishes extends RecyclerView.Adapter<MyViewHolderDishes> {
    private List<Dishes> dishesList;
    private int itemLayout;
    private Context mContext;
    private Order orderItems;
    public CustomRecyclerViewDishes(List<Dishes> items, Order orderItems, int itemLayout, Context context) {
        this.dishesList = items;
        this.itemLayout = itemLayout;
        this.mContext = context;
        this.orderItems = orderItems;
    }
    @NonNull
    @Override
    public MyViewHolderDishes onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View v = inflater.inflate(itemLayout, parent, false);

        // Return a new holder instance
        return new MyViewHolderDishes(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolderDishes holder, final int position) {
        if(dishesList != null && holder != null){
            final Dishes clickedOrder = getItem(position);
            //bind views here like text views and image views.
            holder.Name.setText("(" +(position+1) + ")  " +clickedOrder.getName());
            holder.Price.setText(Math.round(orderItems.getOrderItems().get(position).getPrice())+ " Rs");
            holder.Time.setText(clickedOrder.getTime()+" mins");
        }
    }

    @Override
    public int getItemCount() {
        if(dishesList != null)
            return dishesList.size();
        else
            return 0;
    }
    public void setItems(List<Dishes> c){
        dishesList = c;
    }
    public Dishes getItem(int index){
        return dishesList.get(index);
    }
}
