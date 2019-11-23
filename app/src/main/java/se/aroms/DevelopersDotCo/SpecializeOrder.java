package se.aroms.DevelopersDotCo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.common.util.Base64Utils;
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
    private Switch priority;
    private RadioGroup complimentaryDishes;
    private Button confirmBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialize_order);
        order = (Order)getIntent().getSerializableExtra("order");
        orderNo = getIntent().getIntExtra("index",0);
        priority = (Switch) findViewById(R.id.priority);
        complimentaryDishes = (RadioGroup) findViewById(R.id.radioGroup);
        confirmBtn = (Button) findViewById(R.id.Confirm);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(priority.isChecked()){
                    order.setPriority("High");
                }
                else{
                    order.setPriority("Normal");
                }
                order.setStatus(Long.valueOf(3));
                if (complimentaryDishes.getCheckedRadioButtonId() == -1)
                {
                    order.setComplimentaryDish("None");
                }
                else
                {
                    int selectedBtnId = complimentaryDishes.getCheckedRadioButtonId();
                    RadioButton selectedBtn = (RadioButton) findViewById(selectedBtnId);
                    order.setComplimentaryDish((String) selectedBtn.getText());
                }
                DB= FirebaseDatabase.getInstance().getReference().child("Orders Queue").child(order.getOrderId());
                DB.setValue(order);
                finish();
            }
        });
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
