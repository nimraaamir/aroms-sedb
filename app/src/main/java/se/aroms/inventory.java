package se.aroms;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
//import androidx.widget.LinearLayoutManager;
//import androidx.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class inventory extends AppCompatActivity {

    Context c;
    ViewGroup vg;

    RecyclerView recyclerView;
    DatabaseReference InventoryDB;
    FirebaseRecyclerOptions options;
    private FirebaseRecyclerAdapter<inventory_item, inventoryViewHolder> mFirebaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        recyclerView = (RecyclerView) findViewById(R.id.inventory_recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(c);
        c = getApplicationContext();

        FirebaseApp.initializeApp(this);
        InventoryDB = FirebaseDatabase.getInstance().getReference("Inventory");

        Query query = InventoryDB.orderByChild("name");
        options = new FirebaseRecyclerOptions.Builder<inventory_item>()
                .setQuery(query,inventory_item.class).build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<inventory_item, inventoryViewHolder>(options) {

            @Override
            protected void onBindViewHolder(inventoryViewHolder holder, int position, inventory_item model) {
                holder.setValues(model,c,vg);
            }

            @NonNull
            @Override
            public inventoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                vg = viewGroup;
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inventroy_layout,viewGroup,false);
                return new inventoryViewHolder(view);
            }
        };


        recyclerView.setLayoutManager(manager);
        mFirebaseAdapter.startListening();
        recyclerView.setAdapter(mFirebaseAdapter);

        ((Button) findViewById(R.id.inventory_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),add_inventory.class);
                startActivity(i);
            }
        });

    }



    @Override
    protected void onStop() {
        if(mFirebaseAdapter!=null)
            mFirebaseAdapter.stopListening();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mFirebaseAdapter!=null)
            mFirebaseAdapter.startListening();

    }

    int countid = 0;

    public class inventoryViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView quantity;
        LinearLayout mainLayout;


        public inventoryViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.inventory_layout_name);
            this.quantity = (TextView) itemView.findViewById(R.id.inventory_layout_quantity);
            this.mainLayout = mainLayout = (LinearLayout) itemView.findViewById(R.id.inventory_layout_LL);
        }

        public void showNotification(String message, String uid,String name,String quantity,String threshold,final ViewGroup vg,int id) {
            Intent i = new Intent(vg.getContext(), edit_inventory.class);
            i.putExtra("uid",uid);
            i.putExtra("name",name);
            i.putExtra("quantity",quantity);
            i.putExtra("threshold",threshold);
            //Toast.makeText(vg.getContext(),name,Toast.LENGTH_LONG).show();
            PendingIntent pi = PendingIntent.getActivity(vg.getContext(), 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

            String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";

            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);

                // Configure the notification channel.
                notificationChannel.setDescription("Channel description");
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.RED);
                notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
                notificationChannel.enableVibration(true);
                assert mNotificationManager != null;
                mNotificationManager.createNotificationChannel(notificationChannel);
            }

            Notification notification = new NotificationCompat.Builder(vg.getContext(),NOTIFICATION_CHANNEL_ID)
                    .setSmallIcon(R.drawable.logo_1)
                    .setContentTitle("Inventory Alert!")
                    .setContentText(message)
                    .setContentIntent(pi)
                    .setAutoCancel(true)
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(id, notification);
        }

        public void setValues(final inventory_item a, final Context c, final ViewGroup vg){

            name.setText(a.getName());
            quantity.setText(a.getQuantity());

            if (Integer.parseInt(a.getQuantity())< Integer.parseInt(a.getThreshold())){
                quantity.setTextColor(Color.RED);
                showNotification("Inventory item '"+a.getName()+"' has reached it's threshold.",a.getUid(),a.getName(),a.getQuantity(),a.getThreshold(),vg,countid);
                countid++;
            }
            else{quantity.setTextColor(Color.GREEN);}


            mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(vg.getContext(),edit_inventory.class);
                    i.putExtra("uid",a.getUid());
                    i.putExtra("name",a.getName());
                    i.putExtra("quantity",a.getQuantity());
                    i.putExtra("threshold",a.getThreshold());
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    c.startActivity(i);
                }
            });

        }
    }
}


