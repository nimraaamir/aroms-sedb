package se.aroms;


import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ingredientAdapter extends RecyclerView.Adapter<ingredientAdapter.ingredientViewHolder> {

    Context c;
    ViewGroup vg;
    ArrayList<inventory_item> items;
    DatabaseReference ingredientsDB;

    ingredientAdapter(Context c, ArrayList<inventory_item> items){
        this.c = c;
        this.items = items;
        FirebaseApp.initializeApp(c);
        ingredientsDB = FirebaseDatabase.getInstance().getReference("Ingredients");
    }

    void setData(ArrayList<inventory_item> items){
        this.items = items;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ingredientViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        vg = viewGroup;
        View view = inflater.inflate(R.layout.inventroy_layout,viewGroup,false);
        return new ingredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ingredientViewHolder ViewHolder,final int i) {

        ViewHolder.setValues(items.get(i),c,vg);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ingredientViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView quantity;
        LinearLayout mainLayout;
        Dialog mDialog;

        public ingredientViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.inventory_layout_name);
            this.quantity = (TextView) itemView.findViewById(R.id.inventory_layout_quantity);
            this.mainLayout = mainLayout = (LinearLayout) itemView.findViewById(R.id.inventory_layout_LL);
        }

        private void showPopup(final inventory_item a){
            TextView close;
            Button accept;
            Button reject;


            mDialog = new Dialog(c);
            mDialog.setContentView(R.layout.notification_popup);
            close = mDialog.findViewById(R.id.txtClose);
            accept = mDialog.findViewById(R.id.btnAccept);
            reject = mDialog.findViewById(R.id.btnReject);
            mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                }
            });

            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //perform code
                    ingredientsDB.child(a.getThreshold()).child(a.getUid()).removeValue();
                    Toast.makeText(c,"Ingredient deleted successfully",Toast.LENGTH_LONG).show();
                    mDialog.dismiss();
                }
            });

            reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                }
            });


            mDialog.show();
        }

        public void setValues(final inventory_item a, final Context c, final ViewGroup vg){

            name.setText(a.getName());
            quantity.setText(a.getQuantity());
            mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //showPopup(a);
                    ingredientsDB.child(a.getThreshold()).child(a.getUid()).removeValue();
                    Toast.makeText(c,"Ingredient deleted successfully",Toast.LENGTH_LONG).show();
                    items.remove(a);
                    notifyDataSetChanged();
                }
            });
        }
    }
}

