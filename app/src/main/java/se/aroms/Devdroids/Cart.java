package se.aroms.Devdroids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import se.aroms.R;
import android.view.View;
public class Cart extends AppCompatActivity implements adapter_for_cart.OnViewListener {
    FirebaseAuth auth;
    Context context;
    DatabaseReference cartDB;
    DatabaseReference orderDB;
    List<Cart_item> CartItems;
    private RecyclerView cart_rv;
    private RecyclerView.Adapter adapter_cart;
    private  RecyclerView.LayoutManager layoutManager_cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        CartItems=new ArrayList<>();
        auth=FirebaseAuth.getInstance();
        cartDB = FirebaseDatabase.getInstance().getReference().child("Cart");
        orderDB = FirebaseDatabase.getInstance().getReference().child("Orders Queue");
        context=this;
        cartDB.child(auth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    CartItems.clear();
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        CartItems.add(postSnapshot.getValue(Cart_item.class));

                    }
                    adapter_cart.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        cart_rv= findViewById(R.id.cart_recylcerview);
        cart_rv.setHasFixedSize(true);
        layoutManager_cart = new LinearLayoutManager(this);
        adapter_cart= new adapter_for_cart(CartItems,this);

        cart_rv.setLayoutManager(layoutManager_cart);
        cart_rv.setAdapter(adapter_cart);
    }

    @Override
    public void onViewClick(int position) {

        cartDB.child(auth.getUid()).child(CartItems.get(position).getItems().getItemID()+CartItems.get(position).getItems().getSize()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(context,"Removed",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void OnOrderClick (View view)
    {
        final List<order_queue_items> orderItems;
        orderItems=new ArrayList<>();
        for(int i=0;i<CartItems.size();i++)
        {
            for(int j=0;j<CartItems.get(i).getQuantity();j++)
            orderItems.add(new order_queue_items(CartItems.get(i).getItems().getItemID(),CartItems.get(i).getItems().getSize(),"0",CartItems.get(i).getItems().getTime(),"0",50.0));
        }
        order_queue orderQueue=new order_queue(new Date(),orderItems,"hell",auth.getUid(),"0","normal","NONE");
        String key = orderDB.push().getKey();
        orderQueue.setOrderId(key);
        orderDB.child(key).setValue(orderQueue).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(context,"Orders Placed",Toast.LENGTH_SHORT).show();
                cartDB.child(auth.getUid()).removeValue();
            }
        });

    }
}
