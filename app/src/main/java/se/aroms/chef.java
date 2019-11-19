package se.aroms;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

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

        mAdapter = new chef_adapter(chef.this, mUploads);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);

        FirebaseApp.initializeApp(this);
        FirebaseDatabase.getInstance().getReference().child("chef").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    chefs conversation=new chefs();
                    conversation.setDish(ds.child("dishes/dishname").getValue(String.class));


                    Toast.makeText(getApplicationContext(), "Selected: " + conversation.getDish(), Toast.LENGTH_LONG).show();

                    mUploads.add(conversation);
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

