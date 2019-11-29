package se.aroms;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class edit_inventory extends AppCompatActivity {

    String uid;
    DatabaseReference inventoryDB;
    Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_inventory);

        Intent i = getIntent();
        if(i.hasExtra("uid")){
            uid = i.getStringExtra("uid");
            String name = i.getStringExtra("name");
            String quantity = i.getStringExtra("quantity");
            String threshold = i.getStringExtra("threshold");
            //Toast.makeText(this,name,Toast.LENGTH_LONG).show();
            ((EditText)findViewById(R.id.edit_inventory_name)).setText(name);
            ((EditText)findViewById(R.id.edit_inventory_quantity)).setText(quantity);
            ((EditText)findViewById(R.id.edit_inventory_threshold)).setText(threshold);

            FirebaseApp.initializeApp(this);
            inventoryDB = FirebaseDatabase.getInstance().getReference("Inventory/"+uid);

            (findViewById(R.id.edit_inventory_delete)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //onClickBtnDelete();
                    showPopup();
                }
            });
        }
    }


    public void onClickBtnEdit(View v){
        //((ProgressBar) findViewById(R.id.add_inventory_progressBar)).setVisibility(View.VISIBLE);
        EditText a = (EditText) findViewById(R.id.edit_inventory_name);
        String name = a.getText().toString();

        EditText b = (EditText) findViewById(R.id.edit_inventory_quantity);
        String quantity = b.getText().toString();

        EditText c = (EditText) findViewById(R.id.edit_inventory_threshold);
        String threshold = c.getText().toString();

        if(!name.isEmpty()&&!quantity.isEmpty()&&!threshold.isEmpty()){


            inventoryDB.child("name").setValue(name);
            inventoryDB.child("quantity").setValue(quantity);
            inventoryDB.child("threshold").setValue(threshold);

            Toast.makeText(this,"Item updated successfully",Toast.LENGTH_LONG).show();
            //((ProgressBar) findViewById(R.id.add_inventory_progressBar)).setVisibility(View.INVISIBLE);
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

            //((ProgressBar) findViewById(R.id.add_inventory_progressBar)).setVisibility(View.INVISIBLE);
        }

    }


    private void showPopup(){
        TextView close;
        Button accept;
        Button reject;


        mDialog = new Dialog(edit_inventory.this);
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
        inventoryDB.removeValue();
        Toast.makeText(this,"Item deleted successfully",Toast.LENGTH_LONG).show();
        finish();
        Intent i = new Intent(getApplicationContext(),inventory.class);
        startActivity(i);
    }

}
