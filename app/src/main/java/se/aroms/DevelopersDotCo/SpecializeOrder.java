package se.aroms.DevelopersDotCo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import se.aroms.Devdroids.Dishes;
import se.aroms.Devdroids.Order;
import se.aroms.R;

public class SpecializeOrder extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomRecyclerViewDishes adapter;
    private DatabaseReference DB;
    private ArrayList<Dishes> dishes;
    private int orderNo;
    private Order order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialize_order);
        order = (Order)getIntent().getSerializableExtra("order");
        orderNo = getIntent().getIntExtra("index",0);
        TextView ordernumber = (TextView) findViewById(R.id.orderNumber);
        ordernumber.setText("Order Number " + orderNo);
        dishes = new ArrayList<>();
        dishes.clear();
        adapter = new CustomRecyclerViewDishes(dishes,order,R.layout.specialize_order_dish_row,this);
        recyclerView = (RecyclerView) findViewById(R.id.DishesRV);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DB= FirebaseDatabase.getInstance().getReference().child("Menu");
        DB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    for (int i=0;i < order.getOrderItems().size();i++){
                        if(postSnapshot.getKey().equals(order.getOrderItems().get(i).getItemID())){
                            dishes.add(postSnapshot.getValue(Dishes.class));
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
