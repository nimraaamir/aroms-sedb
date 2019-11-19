package com.example.aroms;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class chef_adapter extends RecyclerView.Adapter<chef_adapter.chefViewHolder>  {
    private Context mContext;
    private List<chefs> mUploads;

    public chef_adapter(Context context, List<chefs> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public chef_adapter.chefViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.activity_chef_row, parent, false);
        return new chef_adapter.chefViewHolder(v);
    }

    @Override
    public void onBindViewHolder(chef_adapter.chefViewHolder holder, int position) {
        chefs uploadCurrent = mUploads.get(position);
        holder.item.setText(uploadCurrent.dish);


    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class chefViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView order;
        public TextView item;


        public chefViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            item=itemView.findViewById(R.id.d);
        }

        @Override
        public void onClick(View v) {
            int pos = getLayoutPosition();
            Context context = v.getContext();
            if (pos == 1) {
                //   Intent intent = new Intent(context, Book1.class);
                // context.startActivity(intent);
            }

        }
    }
}


