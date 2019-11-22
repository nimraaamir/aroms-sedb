package se.aroms;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import se.aroms.Devdroids.Order;
import se.aroms.DevelopersDotCo.CustomRecyclerView;

public class kitchenManagerMain extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CustomRecyclerView adapter;
    private DatabaseReference menuDB;
    private ArrayList<Order> orders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchenmanager);


        orders = new ArrayList<>();
        menuDB= FirebaseDatabase.getInstance().getReference().child("Orders");
        menuDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                orders.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    orders.add(postSnapshot.getValue(Order.class));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        adapter = new CustomRecyclerView(orders,R.layout.kitchen_manager_order_row,this);
        recyclerView = (RecyclerView) findViewById(R.id.OrderDisplay);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }
}



