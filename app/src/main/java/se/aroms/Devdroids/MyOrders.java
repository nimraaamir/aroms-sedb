package se.aroms.Devdroids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.EventListener;

import java.util.ArrayList;
import java.util.List;

import se.aroms.R;

public class MyOrders extends AppCompatActivity {

    List<order_queue> Orders;
    List<orders_row> rows;
    DatabaseReference orderQueue;
    FirebaseAuth auth;
    Context context;
    DatabaseReference menuDB;
    private RecyclerView cart_rv;
    private RecyclerView.Adapter adapter_cart;
    private  RecyclerView.LayoutManager layoutManager_order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Orders=new ArrayList<>();
        rows=new ArrayList<>();
        setContentView(R.layout.activity_my_orders);
        orderQueue= FirebaseDatabase.getInstance().getReference().child("Orders Queue");
        auth=FirebaseAuth.getInstance();
        menuDB=FirebaseDatabase.getInstance().getReference().child("Menu");
        orderQueue.orderByChild("uid").equalTo(auth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Orders.clear();
                rows.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Orders.add(postSnapshot.getValue(order_queue.class));

                }
                for (int i=0;i<Orders.size();i++)
                {
                    for(int j=0;j<Orders.get(i).getOrderItems().size();j++)
                    {
                        menuDB.child(Orders.get(i).getOrderItems().get(j).getItemID()).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Dishes dishes=dataSnapshot.getValue(Dishes.class);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
