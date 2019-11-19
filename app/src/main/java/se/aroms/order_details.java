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

public class order_details extends AppCompatActivity {
    private DatabaseReference mDatabaseRef;
    private RecyclerView mRecyclerView;
    private order_adapter mAdapter;
    TextView tv;
    private List<getorderdetails> mUploads;
    private DatabaseReference mDatabase;

// ...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        mRecyclerView = findViewById(R.id.rcv);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUploads = new ArrayList<>();
        getorderdetails conversation=new getorderdetails("1","1","1","s","d");
        mUploads.add(conversation);


        mAdapter = new order_adapter(order_details.this, mUploads);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);

        /*database.child("orderId").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List notes = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Note note = noteDataSnapshot.getValue(Note.class);
                    notes.add(note);
                }
                adapter.updateList(notes);
            }
        });*/


     DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("chef");
        FirebaseApp.initializeApp(this);
        FirebaseDatabase.getInstance().getReference().child("Orders").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    final getorderdetails conversation=new getorderdetails();
                    conversation.setStatus(ds.child("orderItems/0/status").getValue(String.class));
                    conversation.setOrderid(ds.child("orderId").getValue(String.class));
                    conversation.setItemid("0");
                    final String type= ds.child("orderItems/0/type").getValue(String.class);
                    conversation.setChef(ds.child("orderItems/0/chef").getValue(String.class));
                    //conversation.setPriority(ds.child("orderItems/0/priority").getValue(lom.class).toString());
      boolean ans=false;
                 final   DatabaseReference dbb = FirebaseDatabase.getInstance().getReference().child("chef");
                 dbb.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot dss : dataSnapshot.getChildren()) {
                                String chefname = dss.child("speciality").getValue(String.class);
                                if (chefname.equalsIgnoreCase(type)==true)
                                {
                                    dbb.child("dishes").setValue(conversation.getItemid());
                                }
}}
                     @Override
                     public void onCancelled(@NonNull DatabaseError databaseError) {
                         Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                     }
                 });
                             mUploads.add(conversation);
                    conversation.setStatus(ds.child("orderItems/1/status").getValue(String.class));
                    conversation.setOrderid(ds.child("orderId").getValue(String.class));
                    conversation.setItemid("1");
                    conversation.setChef(ds.child("orderItems/1/chef").getValue(String.class));
                   final String  type2= ds.child("orderItems/1/type").getValue(String.class);
                   // conversation.setPriority(ds.child("orderItems/1/priority").getValue(long.class).toString());
                    mUploads.add(conversation);
                //    final   DatabaseReference dbb = FirebaseDatabase.getInstance().getReference().child("chef");
                    dbb.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot dss : dataSnapshot.getChildren()) {
                                String chefname = dss.child("speciality").getValue(String.class);
                                if (chefname.equalsIgnoreCase(type2)==true)
                                {
                                    dbb.child("dishes").setValue(conversation.getItemid());
                                }
                            }}
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                   // String name=conversation.getStatus().toString();
                    Toast.makeText(getApplicationContext(),conversation.getStatus() , Toast.LENGTH_LONG).show();
                    mAdapter = new order_adapter(order_details.this, mUploads);
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
