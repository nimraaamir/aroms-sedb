package se.aroms;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private Button loginBtn;
    private TextView email, password;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        loginBtn = findViewById(R.id.loginBtn);
        email = findViewById(R.id.name);
        password = findViewById(R.id.password);
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
//        View bg = findViewById(R.id.login_layout);
//        Drawable backgroundImg = bg.getBackground();
//        backgroundImg.setAlpha(200);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loginAuthenticate();
            }
        });
    }


    private void loginAuthenticate(){
        String emailTxt = email.getText().toString();
        String passwordTxt = password.getText().toString();
        mAuth.signInWithEmailAndPassword(emailTxt, passwordTxt)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Message: ", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            uid = user.getUid();
//                            Intent intent = new Intent(getBaseContext(), add_user.class);
//                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this, "Authentication Failed",
                                    Toast.LENGTH_LONG).show();
                        }

                        databaseReference.child(uid).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                User user= dataSnapshot.getValue(User.class);
                                String role=user.getRole();
                                String roleSelected = ((Spinner) findViewById(R.id.dropdown)).getSelectedItem().toString();
                                if(role.equals(roleSelected)){
                                    Toast.makeText(Login.this, "Authentication Successful",
                                            Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getBaseContext(), add_user.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(Login.this, "Authentication Failed",
                                            Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        // ...
                    }
                });
    }
}
