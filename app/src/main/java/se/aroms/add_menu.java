package se.aroms;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_menu extends AppCompatActivity {

    DatabaseReference menuDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);

        FirebaseApp.initializeApp(this);
        menuDB = FirebaseDatabase.getInstance().getReference("Menu");
        //menuDB.setValue("hello");
    }

    public void onClickBtn(View v) {
        ((ProgressBar) findViewById(R.id.add_menu_progressBar)).setVisibility(View.VISIBLE);
        EditText a = (EditText) findViewById(R.id.add_menu_name);
        String name = a.getText().toString();

        EditText b = (EditText) findViewById(R.id.add_menu_regular_price);
        String reg_price = b.getText().toString();

        EditText c = (EditText) findViewById(R.id.add_menu_large_price);
        String large_price = c.getText().toString();

        EditText d = (EditText) findViewById(R.id.add_menu_time);
        String time = d.getText().toString();

        String type = ((Spinner) findViewById(R.id.add_menu_spinner)).getSelectedItem().toString();

        if(!name.isEmpty()&&!reg_price.isEmpty()&&!large_price.isEmpty()&&!time.isEmpty()&&!type.equals("Select dish type")){
            String key = menuDB.push().getKey();

            menuDB.child(key).child("name").setValue(name);
            menuDB.child(key).child("reg_price").setValue(reg_price);
            menuDB.child(key).child("large_price").setValue(large_price);
            menuDB.child(key).child("time").setValue(time);
            menuDB.child(key).child("type").setValue(type);

            Toast.makeText(this,"Dish added successfully",Toast.LENGTH_LONG).show();
            ((ProgressBar) findViewById(R.id.add_menu_progressBar)).setVisibility(View.INVISIBLE);
            finish();
            Intent i = new Intent(getApplicationContext(),add_dish_ingredient.class);
            startActivity(i);
        }
        else{
            ((ProgressBar) findViewById(R.id.add_menu_progressBar)).setVisibility(View.INVISIBLE);
            if(name.isEmpty())
                a.setError("Fill this field");

            if(reg_price.isEmpty())
                b.setError("Fill this field");

            if(large_price.isEmpty())
                c.setError("Fill this field");

            if(time.isEmpty())
                d.setError("Fill this field");

            if(type.equals("Select dish type"))
                Toast.makeText(this,"Select a valid dish type",Toast.LENGTH_LONG).show();
        }


    }
}