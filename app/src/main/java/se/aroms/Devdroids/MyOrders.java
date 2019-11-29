package se.aroms.Devdroids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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
    private List<Dishes> dishes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Orders=new ArrayList<>();
        rows=new ArrayList<>();
        dishes=new ArrayList<>();
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
                    order_queue order=(postSnapshot.getValue(order_queue.class));
                    if(order.getComplimentaryDish().compareTo("None")==0){
                        Orders.add(order);
                    }

                }
                for (int i=0;i<Orders.size();i++)
                {
                    for(int j=0;j<Orders.get(i).getOrderItems().size();j++) {
                        int chk=checkDishAlreadyDownloaded(Orders.get(i).getOrderItems().get(j).getItemID());
                        if (chk==-1) {
                            rows.add(new orders_row("","1",Orders.get(i).getOrderItems().get(j).getItemID()));
                        }
                        else
                        {
                            int quantity=Integer.parseInt(rows.get(chk).getQuantity());
                            quantity++;
                            rows.get(chk).setQuantity(String.valueOf(quantity));
                        }
                    }
                }
                int k=0;
                for(int i=0;i<rows.size();i++){
                    menuDB.child(rows.get(i).getDishId()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            dishes.add(dataSnapshot.getValue(Dishes.class));
                            if(dishes.size()==rows.size())
                            {
                                updateNames();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }

                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        cart_rv = findViewById(R.id.orders_rv);
        cart_rv.setHasFixedSize(true);
        layoutManager_order = new LinearLayoutManager(this);
        adapter_cart = new adapter_for_orders(rows);
        cart_rv.setLayoutManager(layoutManager_order);
        cart_rv.setAdapter(adapter_cart);
    }
    public int checkDishAlreadyDownloaded(String uid){
        for(int i=0;i<rows.size();i++){
            if(rows.get(i).getDishId().compareTo(uid)==0)
            {
                return i;
            }
        }
        return -1;
    }
    public void updateNames()
    {
        for (int i=0;i<rows.size();i++)
        {
            for(int j=0;j<dishes.size();j++)
            {
                if(rows.get(i).getDishId().compareTo(rows.get(i).getDishId())==0)
                {
                    rows.get(i).setDishName(dishes.get(j).getName());
                    break;
                }
            }
        }
        adapter_cart.notifyDataSetChanged();
    }

}
