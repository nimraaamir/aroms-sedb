package se.aroms;


import android.view.View;
import android.widget.Button;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    public Button Specialize;


    public MyViewHolder(View view){
        super(view);
        Specialize = (Button) view.findViewById(R.id.SpecializeOrder);
    }

}
