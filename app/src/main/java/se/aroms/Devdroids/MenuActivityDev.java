package se.aroms.Devdroids;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.EventListener;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import se.aroms.R;
import se.aroms.inventory_item;

public class MenuActivityDev extends AppCompatActivity implements adapter_for_dishes.OnViewListener {
    List<inventory_item> inventory_items;
    List<Dishes> Items;
    List<Ingredients> ingredients;
    FirebaseAuth auth;
    Context context;
    DatabaseReference menuDB;
    private RecyclerView dishes_rv;
    private RecyclerView.Adapter adapter_dishes;
    private  RecyclerView.LayoutManager layoutManager_dishes;
    ArrayList<Dishes> example_list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menudev);

        //getSupportActionBar().hide();
        //Toast.makeText(this,"Hi",Toast.LENGTH_LONG).show();
//        Intent intent=new Intent(MainActivity.this, Dishes.class);
//        startActivity(intent);
        Items=new ArrayList<>();
        context=this;
        menuDB= FirebaseDatabase.getInstance().getReference();
        auth=FirebaseAuth.getInstance();
        /*List<items>dishesordered;
        dishesordered=new ArrayList<>();
        dishesordered.add(new items("-Lts929T4YwIAXYnBAKO","Large",150.0,125.0));
        dishesordered.add(new items("-Lts929T4YwIAXYnBAKO","Regular",100.0,80.0));
        Order order=new Order(new Date(),dishesordered,"jnk",auth.getUid());
        DatabaseReference orderDB;
        orderDB=FirebaseDatabase.getInstance().getReference().child("Orders");
        String key=orderDB.push().getKey();
        order.setOrderId(key);
        orderDB.child(key).setValue(order);
        */
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Menu");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Items.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Items.add(postSnapshot.getValue(Dishes.class));

                }
                adapter_dishes.notifyDataSetChanged();
                    ingredients=new ArrayList<>();
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Ingredients");
                    ref.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            ingredients.clear();

                            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                Map<String,String> obj=(Map<String,String>)postSnapshot.getValue();
                                Iterator<Map.Entry<String, String>> itr=obj.entrySet().iterator();
                                while(itr.hasNext())
                                {
                                    Map.Entry<String, String> entry = itr.next();
                                    ingredients.add(new Ingredients(entry.getKey(),entry.getValue(),postSnapshot.getRef().getKey()));
                                }
                                adapter_dishes.notifyDataSetChanged();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                inventory_items=new ArrayList<>();
                DatabaseReference ref1=FirebaseDatabase.getInstance().getReference().child("Inventory");
                ref1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        inventory_items.clear();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            inventory_items.add(postSnapshot.getValue(inventory_item.class));

                        }
                        for(int i=0;i<Items.size();i++)
                        {
                            int available=1;
                            for(int j=0;j<ingredients.size();j++)
                            {
                                if(ingredients.get(j).getDishId().compareTo(Items.get(i).getUid())==0)
                                {
                                    for(int k=0;k<inventory_items.size();k++)
                                    {
                                        if(ingredients.get(j).getId().compareTo(inventory_items.get(k).getUid())==0)
                                        {
                                            String current_quantity=inventory_items.get(k).getQuantity();
                                            String req_quantity=ingredients.get(j).getQuantity();
                                            int x=Integer.parseInt(req_quantity);
                                            int y=Integer.parseInt(current_quantity);
                                            if(x>y)
                                            {
                                                available=0;
                                            }
                                        }
                                    }
                                }

                            }
                            Items.get(i).setAvailability(available);
                        }
                        adapter_dishes.notifyDataSetChanged();
                        //Toast.makeText(context,"I was changed",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // adding code for dishes to show in recycler view

        //ArrayList<String> Donut_Jelly=new ArrayList<>();
        //ArrayList<String> Cherry_pie=new ArrayList<>();
        //ArrayList<String> pizza=new ArrayList<>();
        //Donut_Jelly.add("https://firebasestorage.googleapis.com/v0/b/aroms-9adaf.appspot.com/o/donut_jelly_small.png?alt=media&token=dc44defb-c3ea-460d-8aeb-c69a2c096d38");
        //Cherry_pie.add("https://firebasestorage.googleapis.com/v0/b/aroms-9adaf.appspot.com/o/cherry_pie_slice_small.png?alt=media&token=ed26b360-177a-4e5c-84de-74573aac23fd");
        //pizza.add("https://firebasestorage.googleapis.com/v0/b/aroms-9adaf.appspot.com/o/pizza.png?alt=media&token=cb9f15fb-ac7d-44bb-923e-6328517e1ede");
        //example_list.add(new Dishes("Pizza is a savory dish of Italian origin, consisting of a usually round, flattened base of leavened wheat-based dough topped with tomatoes, cheese, and often various other ingredients baked at a high temperature, traditionally in a wood-fired oven","900","700","Pizza","700","500","45","Fast Food","1",pizza));
        //example_list.add(new Dishes("Cherry pie is a pie baked with a cherry filling. Traditionally, cherry pie is made with tart rather than tart cherries. ","900","700","Cherry pie","700","500","45","Baked","1",Cherry_pie));
        //example_list.add(new Dishes("a raised doughnut filled with jelly or jam. Berlin doughnut, bismark. raised doughnut - a doughnut made light with yeast rather than baking powder. ","900","700","Donut Jelly","700","500","45","Baked","1",Donut_Jelly));
        //example_list.add(new Dishes("Pizza is a savory dish of Italian origin, consisting of a usually round, flattened base of leavened wheat-based dough topped with tomatoes, cheese, and often various other ingredients baked at a high temperature, traditionally in a wood-fired oven","900","700","Pizza","700","500","45","Fast Food","1",pizza));
        //example_list.add(new Dishes("Cherry pie is a pie baked with a cherry filling. Traditionally, cherry pie is made with tart rather than tart cherries. ","900","700","Cherry pie","700","500","45","Baked","1",Cherry_pie));
        //example_list.add(new Dishes("a raised doughnut filled with jelly or jam. Berlin doughnut, bismark. raised doughnut - a doughnut made light with yeast rather than baking powder. ","900","700","Donut Jelly","700","500","45","Baked","1",Donut_Jelly));

        dishes_rv = findViewById(R.id.main_activity_recyclerView);
        dishes_rv.setHasFixedSize(true);
        layoutManager_dishes = new LinearLayoutManager(this);
        adapter_dishes = new adapter_for_dishes(Items,this);

        dishes_rv.setLayoutManager(layoutManager_dishes);
        dishes_rv.setAdapter(adapter_dishes);

    }

    @Override
    public void onViewClick(int position) {
        Intent intent=new Intent(MenuActivityDev.this, Dish_Details.class);
        intent.putExtra("item",Items.get(position));
        startActivity(intent);
    }
}
