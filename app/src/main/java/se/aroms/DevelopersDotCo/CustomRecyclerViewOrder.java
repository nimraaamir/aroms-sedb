package se.aroms.DevelopersDotCo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import se.aroms.Devdroids.Order;

public class CustomRecyclerViewOrder extends RecyclerView.Adapter<MyViewHolderOrder> {

    private List<Order> ordersList;
    private int itemLayout;
    private Context mContext;
    public CustomRecyclerViewOrder(List<Order> items, int itemLayout, Context context) {
        this.ordersList = items;
        this.itemLayout = itemLayout;
        this.mContext = context;
    }
    @NonNull
    @Override
    public MyViewHolderOrder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View v = inflater.inflate(itemLayout, parent, false);

        // Return a new holder instance
        return new MyViewHolderOrder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolderOrder holder, final int position) {
        if(ordersList != null && holder != null){
            final Order clickedOrder = getItem(position);
            //bind views here like text views and image views.
            holder.OrderNumber.setText("Order # " + (position+1));
            if(clickedOrder.getPriority().equals("")){
                holder.Priority.setText("Normal");
            }
            else{
                holder.Priority.setText(clickedOrder.getPriority());
            }
            holder.Specialize.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, SpecializeOrder.class);
                    intent.putExtra("order",clickedOrder);
                    intent.putExtra("index",position);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(ordersList != null)
            return ordersList.size();
        else
            return 0;
    }
    public void setItems(List<Order> c){
        ordersList = c;
    }
    public Order getItem(int index){
        return ordersList.get(index);
    }

}