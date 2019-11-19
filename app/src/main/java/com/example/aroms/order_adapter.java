package com.example.aroms;

import android.app.Activity;
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
public class order_adapter extends RecyclerView.Adapter<order_adapter.orderViewHolder> implements AdapterView.OnItemSelectedListener {
        private Context mContext;
        private List<order> mUploads;

    public order_adapter(Context context, List<order> uploads) {
            mContext = context;
            mUploads = uploads;
        }

        @Override
        public order_adapter.orderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.activity_order_row, parent, false);
            return new order_adapter.orderViewHolder(v);
        }

        @Override
        public void onBindViewHolder(order_adapter.orderViewHolder holder, int position) {
            //String uploadCurrent = mUploads.get(position);
            //holder.fn.setText(uploadCurrent);
            order uploadCurrent = mUploads.get(position);
            holder.order.setText(uploadCurrent.orderid);
            holder.item.setText(uploadCurrent.itemid);
            holder.pr.setText(uploadCurrent.priority);
            String[] items = new String[]{"waiting", "being cooked", "cooked"};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, items);
            holder.spinner.setAdapter(adapter);




        }

        @Override
        public int getItemCount() {
            return mUploads.size();
        }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public class orderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public TextView order;
            public TextView item;
            public TextView pr;
            public Spinner spinner;


            public orderViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                order = itemView.findViewById(R.id.orderid);
                item=itemView.findViewById(R.id.itemid);
                pr=itemView.findViewById(R.id.p);
                spinner = itemView.findViewById(R.id.spinner);
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

