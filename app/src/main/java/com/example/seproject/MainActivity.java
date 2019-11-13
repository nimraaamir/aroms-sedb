package com.example.seproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchenmanager);
    }

    public void onClickChangeStatus(View view) {
        if (view.getId()==R.id.order1appchangestatus_kitchenmanager)
        {

        }

        if (view.getId()==R.id.order1mainchangestatus_kitchenmanager)
        {

        }

        if (view.getId()==R.id.order2mainchangestatus_kitchenmanager)
        {

        }
    }
}
