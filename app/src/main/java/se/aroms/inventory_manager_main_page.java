package se.aroms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class inventory_manager_main_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_manager_main_page);

        ((Button)findViewById(R.id.IMMP_inventory)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),inventory.class);
                startActivity(i);
            }
        });

        ((Button)findViewById(R.id.IMMP_menu)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),menu.class);
                startActivity(i);
            }
        });

    }
}
