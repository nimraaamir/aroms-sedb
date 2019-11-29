package se.aroms;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_inventory extends AppCompatActivity {

    DatabaseReference inventoryDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inventory);

        FirebaseApp.initializeApp(this);
        inventoryDB = FirebaseDatabase.getInstance().getReference("Inventory");
    }

    public void onClickBtn(View v){
        ((ProgressBar) findViewById(R.id.add_inventory_progressBar)).setVisibility(View.VISIBLE);
        EditText a = (EditText) findViewById(R.id.add_inventory_name);
        String name = a.getText().toString();

        EditText b = (EditText) findViewById(R.id.add_inventory_quantity);
        String quantity = b.getText().toString();

        EditText c = (EditText) findViewById(R.id.add_inventory_threshold);
        String threshold = c.getText().toString();

        if(!name.isEmpty()&&!quantity.isEmpty()&&!threshold.isEmpty()){
            String key = inventoryDB.push().getKey();

            inventoryDB.child(key).child("uid").setValue(key);
            inventoryDB.child(key).child("name").setValue(name);
            inventoryDB.child(key).child("quantity").setValue(quantity);
            inventoryDB.child(key).child("threshold").setValue(threshold);

            Toast.makeText(this,"Item added successfully",Toast.LENGTH_LONG).show();
            ((ProgressBar) findViewById(R.id.add_inventory_progressBar)).setVisibility(View.INVISIBLE);
            finish();
            Intent i = new Intent(getApplicationContext(),inventory.class);
            startActivity(i);
        }
        else{
            if(name.isEmpty())
                a.setError("Fill this field");

            if(quantity.isEmpty())
                b.setError("Fill this field");

            if(threshold.isEmpty())
                c.setError("Fill this field");

            ((ProgressBar) findViewById(R.id.add_inventory_progressBar)).setVisibility(View.INVISIBLE);
        }

    }

}
