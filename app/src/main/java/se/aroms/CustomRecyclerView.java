package se.aroms;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import se.aroms.MyViewHolder;

public class CustomRecyclerView extends RecyclerView.Adapter<MyViewHolder> {

    private List<Orders> ordersList;
    private int itemLayout;
    private Context mContext;
    public CustomRecyclerView(List<Orders> items, int itemLayout,Context context) {
        this.ordersList = items;
        this.itemLayout = itemLayout;
        this.mContext = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View v = inflater.inflate(itemLayout, parent, false);

        // Return a new holder instance
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        if(ordersList != null && holder != null){
            //bind views here like text views and image views.
            holder.OrderNumber.setText("Order Number " + (position+1));
            holder.Specialize.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Orders clickedOrder = getItem(position);
                    Intent intent = new Intent(mContext,SpecializeOrder.class);
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
    public void setItems(List<Orders> c){
        ordersList = c;
    }
    public Orders getItem(int index){
        return ordersList.get(index);
    }

}