package se.aroms;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
//import androidx.widget.LinearLayoutManager;
//import androidx.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class inventory extends AppCompatActivity {

    Context c;
    ViewGroup vg;

    RecyclerView recyclerView;
    DatabaseReference InventoryDB;
    FirebaseRecyclerOptions options;
    private FirebaseRecyclerAdapter<inventory_item, inventoryViewHolder> mFirebaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        recyclerView = (RecyclerView) findViewById(R.id.inventory_recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(c);
        c = getApplicationContext();

        FirebaseApp.initializeApp(this);
        InventoryDB = FirebaseDatabase.getInstance().getReference("Inventory");

        Query query = InventoryDB.orderByChild("name");
        options = new FirebaseRecyclerOptions.Builder<inventory_item>()
                .setQuery(query,inventory_item.class).build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<inventory_item, inventoryViewHolder>(options) {

            @Override
            protected void onBindViewHolder(inventoryViewHolder holder, int position, inventory_item model) {
                holder.setValues(model,c,vg);
            }

            @NonNull
            @Override
            public inventoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                vg = viewGroup;
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inventroy_layout,viewGroup,false);
                return new inventoryViewHolder(view);
            }
        };


        recyclerView.setLayoutManager(manager);
        mFirebaseAdapter.startListening();
        recyclerView.setAdapter(mFirebaseAdapter);

        ((Button) findViewById(R.id.inventory_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),add_inventory.class);
                startActivity(i);
            }
        });

    }



    @Override
    protected void onStop() {
        if(mFirebaseAdapter!=null)
            mFirebaseAdapter.stopListening();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mFirebaseAdapter!=null)
            mFirebaseAdapter.startListening();

    }

    public static class inventoryViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView quantity;
        LinearLayout mainLayout;

        public inventoryViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.inventory_layout_name);
            this.quantity = (TextView) itemView.findViewById(R.id.inventory_layout_quantity);
            this.mainLayout = mainLayout = (LinearLayout) itemView.findViewById(R.id.inventory_layout_LL);
        }

        public void setValues(final inventory_item a, final Context c, final ViewGroup vg){

            name.setText(a.getName());
            quantity.setText(a.getQuantity());

            if (Integer.parseInt(a.getQuantity())< Integer.parseInt(a.getThreshold())){
                quantity.setTextColor(Color.RED);
            }

            mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(vg.getContext(),edit_inventory.class);
                    i.putExtra("uid",a.getUid());
                    i.putExtra("name",a.getName());
                    i.putExtra("quantity",a.getQuantity());
                    i.putExtra("threshold",a.getThreshold());
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);;
                    c.startActivity(i);
                }
            });

        }
    }
}


