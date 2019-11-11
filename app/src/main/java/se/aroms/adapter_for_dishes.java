package se.aroms;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class adapter_for_dishes extends RecyclerView.Adapter<adapter_for_dishes.view_holder> {
    private ArrayList<Dishes> my_array_of_dishes;
    private OnViewListener onViewListener;
    public static class view_holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView dish_image;
        public TextView dish_name;
        public TextView dish_cook_time;
        public TextView dish_price;
        adapter_for_dishes.OnViewListener onViewListener;
        public view_holder(View itemView,adapter_for_dishes.OnViewListener onViewListener)  {
            super(itemView);
            this.onViewListener=onViewListener;
            itemView.setOnClickListener(this);
            dish_image = itemView.findViewById(R.id.dish_img);
            dish_name = itemView.findViewById(R.id.dish_title);
            dish_cook_time = itemView.findViewById(R.id.dish_cook_time);
            dish_price = itemView.findViewById(R.id.dish_price);
        }

        @Override
        public void onClick(View v) {
            onViewListener.onViewClick(getAdapterPosition());
        }
    }

    public adapter_for_dishes(ArrayList<Dishes> dish_list,OnViewListener onViewListener){
        my_array_of_dishes= dish_list;
        this.onViewListener=onViewListener;

    }

    @Override
    public view_holder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hamza_dish, parent, false);
        view_holder vh = new view_holder(v,onViewListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(view_holder holder, int position) {
        Dishes current_item = my_array_of_dishes.get(position);
        holder.dish_image.setImageResource(current_item.getDish_image());
        holder.dish_name.setText(current_item.getDish_name());
        holder.dish_cook_time.setText(current_item.getCook_time());
        holder.dish_price.setText(current_item.getDish_price());
    }

    @Override
    public int getItemCount() {
        return my_array_of_dishes.size();
    }
    public interface OnViewListener{
        void onViewClick(int position);
    }
}
