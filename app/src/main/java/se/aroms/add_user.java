package se.aroms;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_user extends AppCompatActivity {

    private FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    DatabaseReference usersDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        //FIREBASE AUTHENTICATION
        mAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null) {
                }
                else{
                    //startActivity(new Intent(RegisterPage.this, UserMainPage.class));
                }
            }
        };

        FirebaseApp.initializeApp(this);
        usersDB = FirebaseDatabase.getInstance().getReference("Users");
    }

    public void onClickBtn(View v){
        //get All details

        EditText d = (EditText) findViewById(R.id.add_inventory_name);
        String Name = d.getText().toString();

        EditText a = (EditText) findViewById(R.id.add_user_email);
        String email = a.getText().toString();

        EditText b = (EditText) findViewById(R.id.adi_quantity);
        String pass = b.getText().toString();

        EditText e = (EditText) findViewById(R.id.add_inventory_threshold);
        String phoneNo = e.getText().toString();

        EditText c = (EditText) findViewById(R.id.add_inventory_quantity);
        String cnic = c.getText().toString();

        EditText f = (EditText) findViewById(R.id.add_user_dob);
        String dob = f.getText().toString();

        String role = ((Spinner) findViewById(R.id.add_user_role)).getSelectedItem().toString();

        if(!email.isEmpty()&&!pass.isEmpty()&&!cnic.isEmpty()&&!Name.isEmpty()&&!phoneNo.isEmpty()&&!dob.isEmpty()&&!role.equals("Select role")){

            if(!Name.matches("[a-zA-Z ]*\\d+.*")){
                if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    if(pass.length()>6){
                        //EVERYTHING OK!
                        User user = new User(Name,cnic,phoneNo,email,pass,dob,role);
                        registerUser(email,pass,user);
                    }
                    else{
                        b.setError("Password is weak");
                    }
                }
                else{
                    a.setError("Email is not valid!");
                }

            }
            else{
                d.setError("Name should not contain numbers");
            }


        }
        else{
            if(Name.isEmpty())
                d.setError("Fill this field");

            if(email.isEmpty())
                a.setError("Fill this field");

            if(phoneNo.isEmpty())
                e.setError("Fill this field");

            if(pass.isEmpty())
                b.setError("Fill this field");

            if(cnic.isEmpty())
                c.setError("Fill this field");

            if(dob.isEmpty())
                f.setError("Fill this field");

            if(role.equals("Select role"))
                Toast.makeText(this,"Select a valid role",Toast.LENGTH_LONG).show();

        }



    }

    void registerUser(String email,String pass,final User a) {
        ((ProgressBar) findViewById(R.id.add_user_progressBar)).setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //progressDialog.dismiss();

                if (task.isSuccessful()){

                    FirebaseUser user = mAuth.getCurrentUser(); //You Firebase user
                    // user registered, start profile activity
                    String uid = user.getUid();

                    usersDB.child(uid).setValue(a);
                    Toast.makeText(getApplicationContext(),"User created successfully",Toast.LENGTH_LONG).show();
                    ((ProgressBar) findViewById(R.id.add_user_progressBar)).setVisibility(View.INVISIBLE);

                    finish();
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //i.putExtra("email",a.getEmail());
                    startActivity(i);
                }
                else{
                    ((ProgressBar) findViewById(R.id.add_user_progressBar)).setVisibility(View.INVISIBLE);
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                        Toast.makeText(getApplicationContext(),"Could not create account. Email already exists",Toast.LENGTH_SHORT).show();
                    else{Toast.makeText(getApplicationContext(),"Could not create account, Please try again.",Toast.LENGTH_SHORT).show();}
                }
            }
        });

    }
}
