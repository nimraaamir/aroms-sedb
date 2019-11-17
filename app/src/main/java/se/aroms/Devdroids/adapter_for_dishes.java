package se.aroms.Devdroids;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import se.aroms.R;

public class adapter_for_dishes extends RecyclerView.Adapter<adapter_for_dishes.view_holder> {
    private ArrayList<Dishes> my_array_of_dishes;
    private OnViewListener onViewListener;
    public static class view_holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView dish_image;
        public TextView dish_name;
        public TextView dish_cook_time;
        public TextView dish_reg_price;
        public TextView dish_large_price;
        public ProgressBar progressBar;
        adapter_for_dishes.OnViewListener onViewListener;
        public view_holder(View itemView,adapter_for_dishes.OnViewListener onViewListener)  {
            super(itemView);
            this.onViewListener=onViewListener;
            itemView.setOnClickListener(this);
            dish_image = itemView.findViewById(R.id.dish_img);
            dish_name = itemView.findViewById(R.id.dish_title);
            dish_cook_time = itemView.findViewById(R.id.dish_cook_time);
            dish_reg_price = itemView.findViewById(R.id.dish_reg_price);
            progressBar=itemView.findViewById(R.id.row_progress);
            dish_large_price=itemView.findViewById(R.id.dish_large_price);
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
        final view_holder viewHolder = (view_holder) holder;
        Picasso.get().load(current_item.getImg_ids().get(0)).into(viewHolder.dish_image, new Callback() {
            @Override
            public void onSuccess() {
                viewHolder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });
        holder.dish_name.setText(current_item.getName());
        holder.dish_cook_time.setText("Cook TIme: "+current_item.getTime());
        holder.dish_reg_price.setText("Regular Price: "+current_item.getReg_price());
        holder.dish_large_price.setText("Large Price: "+current_item.getLarge_price());
    }

    @Override
    public int getItemCount() {
        return my_array_of_dishes.size();
    }
    public interface OnViewListener{
        void onViewClick(int position);
    }
}
