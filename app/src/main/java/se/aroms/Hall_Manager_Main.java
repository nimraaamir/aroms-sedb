package se.aroms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Hall_Manager_Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_manager_main);
        //View Table on Click
        Button viewTables = findViewById(R.id.button2);
        viewTables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), View_tables.class);
                startActivity(intent);

            }
        });

        //waiters onlcick
        Button waiters = findViewById(R.id.waiters);
        waiters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), waiters.class);
                startActivity(intent);

            }
        });

        Button tablets = findViewById(R.id.assign_tablet);
        tablets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), tablets.class);
                startActivity(intent);

            }
        });

        Button payment = findViewById(R.id.payment_status);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), payment.class);
                startActivity(intent);

            }
        });

        Button serve = findViewById(R.id.ready_to_serve);
        serve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ready_to_serve.class);
                startActivity(intent);

            }
        });

        Button threshold = findViewById(R.id.threshold);
        threshold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Hall_Manager_Main.class);
                startActivity(intent);

            }
        });

    }
}
