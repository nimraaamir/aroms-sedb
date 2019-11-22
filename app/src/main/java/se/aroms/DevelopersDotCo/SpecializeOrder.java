package se.aroms.DevelopersDotCo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import se.aroms.Devdroids.Order;
import se.aroms.R;

public class SpecializeOrder extends AppCompatActivity {

    private RecyclerView recyclerView;
    private int orderNo;
    private Order order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialize_order);
        order = (Order)getIntent().getSerializableExtra("order");
        orderNo = getIntent().getIntExtra("index",0);
        TextView ordernumber = (TextView) findViewById(R.id.orderNumber);
        ordernumber.setText("Order Number " + orderNo);


    }
}
