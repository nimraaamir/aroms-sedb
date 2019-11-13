package se.aroms;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class edit_dish extends AppCompatActivity {

    String uid;
    DatabaseReference menuDB;
    Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dish);

        Intent i = getIntent();
        if(i.hasExtra("uid")){
            uid = i.getStringExtra("uid");
            String name = i.getStringExtra("name");
            String reg_price = i.getStringExtra("reg_price");
            String large_price = i.getStringExtra("large_price");
            String time = i.getStringExtra("time");
            String type = i.getStringExtra("type");

            ((EditText)findViewById(R.id.edit_dish_name)).setText(name);
            ((EditText)findViewById(R.id.edit_dish_regular_price)).setText(reg_price);
            ((EditText)findViewById(R.id.edit_dish_large_price)).setText(large_price);
            ((EditText)findViewById(R.id.edit_dish_time)).setText(time);
            ((Spinner)findViewById(R.id.edit_dish_spinner)).setSelection(((ArrayAdapter<String>)((Spinner)findViewById(R.id.edit_dish_spinner)).getAdapter()).getPosition(type));

            FirebaseApp.initializeApp(this);
            menuDB = FirebaseDatabase.getInstance().getReference("Menu/"+uid);

            (findViewById(R.id.edit_dish_delete)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //onClickBtnDelete();
                    showPopup();
                }
            });
        }

    }


    public void onClickBtn(View v) {
        //((ProgressBar) findViewById(R.id.add_menu_progressBar)).setVisibility(View.VISIBLE);
        EditText a = (EditText) findViewById(R.id.edit_dish_name);
        String name = a.getText().toString();

        EditText b = (EditText) findViewById(R.id.edit_dish_regular_price);
        String reg_price = b.getText().toString();

        EditText c = (EditText) findViewById(R.id.edit_dish_large_price);
        String large_price = c.getText().toString();

        EditText d = (EditText) findViewById(R.id.edit_dish_time);
        String time = d.getText().toString();

        String type = ((Spinner) findViewById(R.id.edit_dish_spinner)).getSelectedItem().toString();

        if(!name.isEmpty()&&!reg_price.isEmpty()&&!large_price.isEmpty()&&!time.isEmpty()&&!type.equals("Select dish type")){

            menuDB.child("name").setValue(name);
            menuDB.child("reg_price").setValue(reg_price);
            menuDB.child("large_price").setValue(large_price);
            menuDB.child("time").setValue(time);
            menuDB.child("type").setValue(type);

            Toast.makeText(this,"Dish added successfully",Toast.LENGTH_LONG).show();
            //((ProgressBar) findViewById(R.id.add_menu_progressBar)).setVisibility(View.INVISIBLE);
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


    private void showPopup(){
        TextView close;
        Button accept;
        Button reject;


        mDialog = new Dialog(edit_dish.this);
        mDialog.setContentView(R.layout.notification_popup);
        close = mDialog.findViewById(R.id.txtClose);
        accept = mDialog.findViewById(R.id.btnAccept);
        reject = mDialog.findViewById(R.id.btnReject);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtnDelete();
                mDialog.dismiss();
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });


        mDialog.show();
    }


    public void onClickBtnDelete(){
        menuDB.removeValue();
        Toast.makeText(this,"Dish deleted successfully",Toast.LENGTH_LONG).show();
        //HAVE TO ADD DELETION OF INVENTORY ITEMS
        finish();
        Intent i = new Intent(getApplicationContext(),menu.class);
        startActivity(i);
    }

}
