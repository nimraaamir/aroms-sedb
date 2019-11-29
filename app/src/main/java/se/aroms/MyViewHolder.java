package se.aroms;


import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    public Button Specialize;
    public TextView Priority;
    public TextView OrderNumber;


    public MyViewHolder(View view){
        super(view);
        Specialize = (Button) view.findViewById(R.id.SpecializeOrder);
        Priority = (TextView) view.findViewById(R.id.Priority);
        OrderNumber = (TextView) view.findViewById(R.id.OrderNumber);
    }

}
