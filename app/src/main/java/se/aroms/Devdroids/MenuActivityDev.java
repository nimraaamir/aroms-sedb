package se.aroms.Devdroids;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import se.aroms.R;

public class MenuActivityDev extends AppCompatActivity implements adapter_for_dishes.OnViewListener {

    private RecyclerView dishes_rv;
    private RecyclerView.Adapter adapter_dishes;
    private  RecyclerView.LayoutManager layoutManager_dishes;
    ArrayList<Dishes> example_list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menudev);
        //getSupportActionBar().hide();
        //Toast.makeText(this,"Hi",Toast.LENGTH_LONG).show();
//        Intent intent=new Intent(MainActivity.this, Dishes.class);
//        startActivity(intent);



        // adding code for dishes to show in recycler view

        ArrayList<String> Donut_Jelly=new ArrayList<>();
        ArrayList<String> Cherry_pie=new ArrayList<>();
        ArrayList<String> pizza=new ArrayList<>();
        Donut_Jelly.add("https://firebasestorage.googleapis.com/v0/b/aroms-9adaf.appspot.com/o/donut_jelly_small.png?alt=media&token=dc44defb-c3ea-460d-8aeb-c69a2c096d38");
        Cherry_pie.add("https://firebasestorage.googleapis.com/v0/b/aroms-9adaf.appspot.com/o/cherry_pie_slice_small.png?alt=media&token=ed26b360-177a-4e5c-84de-74573aac23fd");
        pizza.add("https://firebasestorage.googleapis.com/v0/b/aroms-9adaf.appspot.com/o/pizza.png?alt=media&token=cb9f15fb-ac7d-44bb-923e-6328517e1ede");
        example_list.add(new Dishes("Pizza is a savory dish of Italian origin, consisting of a usually round, flattened base of leavened wheat-based dough topped with tomatoes, cheese, and often various other ingredients baked at a high temperature, traditionally in a wood-fired oven","900","700","Pizza","700","500","45","Fast Food","1",pizza));
        example_list.add(new Dishes("Cherry pie is a pie baked with a cherry filling. Traditionally, cherry pie is made with tart rather than tart cherries. ","900","700","Cherry pie","700","500","45","Baked","1",Cherry_pie));
        example_list.add(new Dishes("a raised doughnut filled with jelly or jam. Berlin doughnut, bismark. raised doughnut - a doughnut made light with yeast rather than baking powder. ","900","700","Donut Jelly","700","500","45","Baked","1",Donut_Jelly));
        example_list.add(new Dishes("Pizza is a savory dish of Italian origin, consisting of a usually round, flattened base of leavened wheat-based dough topped with tomatoes, cheese, and often various other ingredients baked at a high temperature, traditionally in a wood-fired oven","900","700","Pizza","700","500","45","Fast Food","1",pizza));
        example_list.add(new Dishes("Cherry pie is a pie baked with a cherry filling. Traditionally, cherry pie is made with tart rather than tart cherries. ","900","700","Cherry pie","700","500","45","Baked","1",Cherry_pie));
        example_list.add(new Dishes("a raised doughnut filled with jelly or jam. Berlin doughnut, bismark. raised doughnut - a doughnut made light with yeast rather than baking powder. ","900","700","Donut Jelly","700","500","45","Baked","1",Donut_Jelly));

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
        intent.putExtra("item",example_list.get(position));
        startActivity(intent);
    }
}
