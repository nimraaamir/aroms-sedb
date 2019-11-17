package se.aroms.Devdroids;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import se.aroms.R;

public class Dish_Details extends AppCompatActivity {
    TextView desp;
    TextView regPrice;
    TextView largePrice;
    TextView cookTime;
    ImageView dish_image;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish__details);
        Intent intent=getIntent();
        Dishes dishes=intent.getParcelableExtra("item");
        desp=findViewById(R.id.dish_detail_desc);
        regPrice=findViewById(R.id.dish_detail_regprice);
        largePrice=findViewById(R.id.dish_detail_largeprice);
        progressBar=findViewById(R.id.dish_detail_progress);
        cookTime=findViewById(R.id.dish_detail_cooktime);
        dish_image=findViewById(R.id.dish_detail_img);
        desp.setText(dishes.getDescription());
        regPrice.setText(dishes.getReg_price());
        largePrice.setText(dishes.getLarge_price());
        cookTime.setText(dishes.getTime());

        Picasso.get().load(dishes.getImg_ids().get(0)).into(dish_image, new Callback() {
            @Override
            public void onSuccess() {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
    public void onClickCart(View v)
    {
        Toast.makeText(this,"Added",Toast.LENGTH_SHORT).show();
    }

}
