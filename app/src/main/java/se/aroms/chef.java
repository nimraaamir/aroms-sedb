package se.aroms;


import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class chef extends AppCompatActivity {
    private DatabaseReference mDatabaseRef;
    private RecyclerView mRecyclerView;
    private chef_adapter mAdapter;
    TextView tv;
    private List<chefs> mUploads;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef);

        mRecyclerView = findViewById(R.id.rcv);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUploads = new ArrayList<>();

     //   mAdapter = new chef_adapter(chef.this, mUploads);
   //     mAdapter.notifyDataSetChanged();
   //     mRecyclerView.setAdapter(mAdapter);

        FirebaseApp.initializeApp(this);
        FirebaseDatabase.getInstance().getReference().child("chef").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<chef_list> listRes = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    chefs n1=new chefs();
                            n1.name=ds.getValue(chefs.class).getname();
                            n1.speciality=ds.getValue(chefs.class).getSpeciality();
                            n1.queue=ds.getValue(chefs.class).getQueue();
                            n1.setKey(ds.getKey());//chef id
                      //      n1.setKeyoforder();
                    //chefs res=new chefs();

                    //chef_list conversation=ds.getValue(chef_list.class);
                    mUploads.add(n1);
                    mAdapter = new chef_adapter(chef.this, mUploads);
                    mAdapter.notifyDataSetChanged();
                    mRecyclerView.setAdapter(mAdapter);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }


}

