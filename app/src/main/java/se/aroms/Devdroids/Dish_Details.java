package se.aroms.Devdroids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import se.aroms.R;

public class Dish_Details extends AppCompatActivity {
    TextView desp;
    TextView name;
    TextView regPrice;
    TextView largePrice;
    TextView cookTime;
    ImageView dish_image;
    ProgressBar progressBar;
    TextView orderNo;
    Dishes dishes;
    RadioGroup radioGroup;
    Button addToCat;
    EditText quantity;
    DatabaseReference cartDB;
    FirebaseAuth auth;
    List<Cart_item> CartItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish__details);
        Intent intent=getIntent();
        cartDB = FirebaseDatabase.getInstance().getReference().child("Cart");
        auth=FirebaseAuth.getInstance();
        CartItems=new ArrayList<>();
        dishes=intent.getParcelableExtra("item");
        desp=findViewById(R.id.dish_detail_desc);
        regPrice=findViewById(R.id.dish_detail_regprice);
        largePrice=findViewById(R.id.dish_detail_largeprice);
        progressBar=findViewById(R.id.dish_detail_progress);
        quantity=findViewById(R.id.dish_detail_quantity);
        cookTime=findViewById(R.id.dish_detail_cooktime);
        dish_image=findViewById(R.id.dish_detail_img);
        addToCat=findViewById(R.id.addToCart);
        orderNo=findViewById(R.id.cartNo1);
        radioGroup=findViewById(R.id.sizeRadio);
        desp.setText(dishes.getDescription());
        regPrice.setText("Regular: "+dishes.getReg_price()+"RS");
        largePrice.setText("Large: "+dishes.getLarge_price()+"RS");
        cookTime.setText(dishes.getTime()+" Minutes");
        name=findViewById(R.id.dish_detail_name);
        name.setText(dishes.getName());
        if(dishes.getPicture()!=null) {

            Picasso.get().load(dishes.getPicture()).fit().into(dish_image, new Callback() {
                @Override
                public void onSuccess() {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onError(Exception e) {
                    progressBar.setVisibility(View.GONE);
                    dish_image.setImageResource(R.drawable.notavailable);
                }
            });

        }
        else
        {
            progressBar.setVisibility(View.GONE);
            dish_image.setImageResource(R.drawable.notavailable);
        }
        if(dishes.getAvailability()==0)
        {
            addToCat.setBackgroundColor(getResources().getColor(R.color.grey));
        }
        cartDB.child(auth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null)
                {
                    CartItems.clear();
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        CartItems.add(postSnapshot.getValue(Cart_item.class));

                    }
                    orderNo.setText(" "+CartItems.size());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void onClickCart(View v) {
        if(dishes.getAvailability()==0)
        {
            Toast.makeText(this, "Sorry item currently out of stock", Toast.LENGTH_SHORT).show();
            return;
        }
        String size = "Regular";
        boolean found = false;
        int selected = radioGroup.getCheckedRadioButtonId();
        if (selected == R.id.Largesize) {
            size = "Large";
        }
        for (int i = 0; i < CartItems.size(); i++) {
            if (CartItems.get(i).getItems().getItemID().compareTo(dishes.getUid()) == 0 && CartItems.get(i).getItems().getSize().compareTo(size) == 0) {
                found = true;
            }
        }
        if (!found) {
            Cart_item item = new Cart_item(new Item_cart(dishes.getUid(),size,dishes.getName(),dishes.getDescription(),dishes.getPicture(),dishes.getTime()),Integer.parseInt(quantity.getText().toString()));
            cartDB.child(auth.getUid()).child(dishes.getUid() + size).setValue(item);
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Item Already in Cart", Toast.LENGTH_SHORT).show();
        }
    }

}
