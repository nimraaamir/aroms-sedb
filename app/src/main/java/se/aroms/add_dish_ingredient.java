package se.aroms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class add_dish_ingredient extends AppCompatActivity {

    List<String> names;
    List<String> ids;
    ArrayList<inventory_item> ingredients;
    DatabaseReference inventoryDB,ingredientsDB;
    String dish_uid = "01";
    private RecyclerView recyclerView;
    private ingredientAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish_ingredient);


        names = new ArrayList<>();
        ids = new ArrayList<>();
        ingredients = new ArrayList<>();

        names.add("Select Ingredients");
        ids.add("-");
        //ingredients.add(new inventory_item("a","b","c","d"));

        FirebaseApp.initializeApp(this);
        inventoryDB = FirebaseDatabase.getInstance().getReference("Inventory");

        Intent i = getIntent();
        if(i.hasExtra("uid")){
            dish_uid = i.getStringExtra("uid");
        }
            ingredientsDB = FirebaseDatabase.getInstance().getReference("Ingredients/"+dish_uid);



            adapter = new ingredientAdapter(getApplicationContext(),ingredients);
            recyclerView = findViewById(R.id.adi_RV);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);





        Spinner spinner = (Spinner) findViewById(R.id.adi_spinner);

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,names);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);

        inventoryDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    String uid = singleSnapshot.getKey();
                    String name = singleSnapshot.child("name").getValue().toString();
                    names.add(name);
                    ids.add(uid);
                }
                spinnerArrayAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
//                        Log.e(TAG, "onCancelled", databaseError.toException());
            }
        });

        ingredientsDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    String uid = singleSnapshot.getKey();
                    String quan = singleSnapshot.getValue().toString();
                    //Toast.makeText(getApplicationContext(),uid,Toast.LENGTH_SHORT).show();
                    int ind = ids.indexOf(uid);
                    //Toast.makeText(getApplicationContext(),String.valueOf(ind),Toast.LENGTH_LONG).show();
                    String name = names.get(ind);

                    ingredients.add(new inventory_item(uid,name,quan,dish_uid));

                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
//                        Log.e(TAG, "onCancelled", databaseError.toException());
            }
        });

        ((Button)findViewById(R.id.adi_finish)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Ingredients updated sucessfully",Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(),menu.class);
                finish();
                startActivity(i);
            }
        });

    }

    public void btnpress_add(View v){
        String quantity = ((EditText)findViewById(R.id.adi_quantity)).getText().toString();
        int index = ((Spinner)findViewById(R.id.adi_spinner)).getSelectedItemPosition();


        if(!quantity.isEmpty() && (index!=0)){
            if(Integer.parseInt(quantity)>0){
                String uid = ids.get(index);
                String name = names.get(index);
                ingredientsDB.child(uid).setValue(quantity);

                boolean flag = false;

                for (inventory_item i : ingredients) {
                    if (i.uid.equals(uid)) {
                        i.setQuantity(quantity);
                        flag = true;
                        break;
                    }
                }

                if(!flag) {
                    ingredients.add(new inventory_item(uid,name,quantity,dish_uid));
                }

                adapter.notifyDataSetChanged();
                Toast.makeText(this,"Ingredient added successfully",Toast.LENGTH_LONG).show();
                ((EditText)findViewById(R.id.adi_quantity)).setText("");
                ((Spinner)findViewById(R.id.adi_spinner)).setSelection(0);
            }
            else{
                ((EditText)findViewById(R.id.adi_quantity)).setError("value > 0");
            }
        }
        else{
            if(quantity.isEmpty()){
                ((EditText)findViewById(R.id.adi_quantity)).setError("Fill this field");
            }
            if(index == 0){
                Toast.makeText(this,"Please select an ingredient from the list",Toast.LENGTH_LONG).show();
            }
        }

    }
}
