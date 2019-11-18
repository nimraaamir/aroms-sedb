package se.aroms;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firestore.v1.StructuredQuery;

import java.util.ArrayList;

public class kitchenManagerMain extends AppCompatActivity {
    //Get orders from Firebase. Currently Using hard coded Data for my own purposes.
    GestureDetector gestureDetector;
    RecyclerView recyclerView;
    CustomRecyclerView adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchenmanager);
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener()
        {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {

                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if(child != null)
                {
                    //Open Screen of Activity.
                }
                return true;
            }
        }

        );//Hard coded data. just get orders no need for dishes.
        Dishes dish = new Dishes(1,"Biryani","!0 min","300 Rs");
        ArrayList<Orders> orders = new ArrayList<>();
        Orders order = new Orders();
        order.addDishes(dish);
        orders.add(order);
        adapter = new CustomRecyclerView(orders,R.layout.kitchen_manager_order_row,this);
        recyclerView = (RecyclerView) findViewById(R.id.OrderDisplay);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return true;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }
        });
    }
}



