package se.aroms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class assign_role extends AppCompatActivity {

    DatabaseReference rolesDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_role);

        Intent i =  getIntent();
        if(i.hasExtra("email")){
            String email = i.getStringExtra("email");
            ((EditText) findViewById(R.id.assign_role_email)).setText(email);
            ((EditText) findViewById(R.id.assign_role_email)).setEnabled(false);
        }

        FirebaseApp.initializeApp(this);
        rolesDB = FirebaseDatabase.getInstance().getReference("Roles");
        //rolesDB.setValue("hello");
    }

    public void onClickBtn(View v){
        EditText a = findViewById(R.id.assign_role_email);
        String email = a.getText().toString();

        String role = ((Spinner) findViewById(R.id.add_user_role)).getSelectedItem().toString();

        if(!email.isEmpty()&&!role.equals("Select type")){
            if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                rolesDB.child(email).setValue(role);
                Toast.makeText(this,"Role assigned successfully!",Toast.LENGTH_LONG).show();
            }
            else{a.setError("Invalid email type");}

        }
        else{
            if(email.isEmpty())
                a.setError("Fill this field");
            if(role.equals("Select type"))
                Toast.makeText(this,"Select a valid role",Toast.LENGTH_LONG).show();
        }
    }
}
