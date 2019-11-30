package se.aroms;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class chef_display extends AppCompatActivity {
    private DatabaseReference mDatabaseRef;
    private RecyclerView mRecyclerView;
    private chef_display_adapter mAdapter;
    TextView tv;
    private List<dishlist3> mUploads;
    private DatabaseReference mDatabase;
    private String size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_display);

        mRecyclerView = findViewById(R.id.rcv);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUploads = new ArrayList<>();
        tv=findViewById(R.id.name);
        Intent t = getIntent();
        final String na = t.getStringExtra("email");

        FirebaseApp.initializeApp(this);
        FirebaseDatabase.getInstance().getReference().child("chef").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot12) {

                for (DataSnapshot ds11 : dataSnapshot12.getChildren()) {
                        String e = ds11.child("email").getValue(String.class);
                        String n=ds11.child("name").getValue(String.class);
                        tv.setText(n);
                    if (na.equalsIgnoreCase(e))//match chef email
                    {
                        for(DataSnapshot ds : ds11.child("dishes").getChildren()) {
                        final String dish = ds.child("dish").getValue(String.class);
                        final String oid = ds.child("order_id").getValue(String.class);
                            String nameofdsh = findname(dish);

                        }

                    }

             }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    public String namedish;

    public String findname(final String dish1) {
        ///////

        FirebaseApp.initializeApp(this);
        FirebaseDatabase.getInstance().getReference().child("Menu").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot1) {
                for (DataSnapshot ds1 : dataSnapshot1.getChildren()) {
                    menus n1 = new menus();
                    n1 = ds1.getValue(menus.class);
                    if (n1.getuid().equalsIgnoreCase(dish1)) {

                        namedish = (n1.getName());
                        dishlist3 d = new dishlist3();
                        d.setDish(namedish);
                        mUploads.add(d);
                        mAdapter = new chef_display_adapter(chef_display.this, mUploads);
                        mAdapter.notifyDataSetChanged();
                        mRecyclerView.setAdapter(mAdapter);

                    }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        return namedish;
    }
}
