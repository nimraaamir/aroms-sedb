package se.aroms;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class MenuActivityDev extends AppCompatActivity implements adapter_for_dishes.OnViewListener {

    private RecyclerView dishes_rv;
    private RecyclerView.Adapter adapter_dishes;
    private  RecyclerView.LayoutManager layoutManager_dishes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menudev);
        getSupportActionBar().hide();
        //Toast.makeText(this,"Hi",Toast.LENGTH_LONG).show();
//        Intent intent=new Intent(MainActivity.this, Dishes.class);
//        startActivity(intent);



        // adding code for dishes to show in recycler view
        ArrayList<Dishes> example_list = new ArrayList<>();
        example_list.add(new Dishes(R.drawable.cherry_pie_slice_small, "chicken salad", "45 mins", "500 Rs only"));
        example_list.add(new Dishes(R.drawable.donut_jelly_small, "chicken pizza", "45 mins", "900 Rs only"));
        example_list.add(new Dishes(R.drawable.cherry_pie_slice_small, "chicken salad", "45 mins", "500 Rs only"));
        example_list.add(new Dishes(R.drawable.donut_jelly_small, "chicken pizza", "45 mins", "900 Rs only"));
        example_list.add(new Dishes(R.drawable.cherry_pie_slice_small, "chicken salad", "45 mins", "500 Rs only"));
        example_list.add(new Dishes(R.drawable.donut_jelly_small, "chicken pizza", "45 mins", "900 Rs only"));
        example_list.add(new Dishes(R.drawable.cherry_pie_slice_small, "chicken salad", "45 mins", "500 Rs only"));
        example_list.add(new Dishes(R.drawable.donut_jelly_small, "chicken pizza", "45 mins", "900 Rs only"));

        dishes_rv = findViewById(R.id.main_activity_recyclerView);
        dishes_rv.setHasFixedSize(true);
        layoutManager_dishes = new LinearLayoutManager(this);
        adapter_dishes = new adapter_for_dishes(example_list,this);

        dishes_rv.setLayoutManager(layoutManager_dishes);
        dishes_rv.setAdapter(adapter_dishes);

    }

    @Override
    public void onViewClick(int position) {
        Intent intent=new Intent(MenuActivityDev.this, Dish_Details.class);
        startActivity(intent);
    }
}
