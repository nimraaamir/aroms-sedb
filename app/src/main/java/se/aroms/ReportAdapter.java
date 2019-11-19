package se.aroms;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class ReportAdapter extends FragmentStatePagerAdapter {

    int countTab;
    public ReportAdapter(@NonNull FragmentManager fm, int countTab) {
        super(fm);
        this.countTab = countTab;
    }

    ArrayList<Order> getOrders(){
        ArrayList<Order> orders = new ArrayList<>();

        items item1 = new items("1", "medium", 500, 400);
        items item2 = new items("2", "medium", 700, 300);
        items item3 = new items("3", "medium", 600, 500);
        items item4 = new items("4", "medium", 400, 200);
        items item5 = new items("5", "medium", 300, 50);
        items item6 = new items("6", "medium", 200, 100);
        items item7 = new items("7", "medium", 900, 400);

        ArrayList<items> itemArr1 = new ArrayList<>();
        ArrayList<items> itemArr2 = new ArrayList<>();
        ArrayList<items> itemArr3 = new ArrayList<>();
        ArrayList<items> itemArr4 = new ArrayList<>();
        ArrayList<items> itemArr5 = new ArrayList<>();
        ArrayList<items> itemArr6 = new ArrayList<>();

        itemArr1.add(item1);
        itemArr2.add(item4);
        itemArr3.add(item3);
        itemArr4.add(item2);
        itemArr5.add(item1);
        itemArr6.add(item7);
        itemArr1.add(item6);
        itemArr2.add(item5);
        itemArr3.add(item2);
        itemArr4.add(item5);
        itemArr5.add(item4);
        itemArr6.add(item4);
        itemArr5.add(item3);
        itemArr2.add(item6);
        itemArr1.add(item4);
        itemArr2.add(item1);
        itemArr3.add(item6);
        itemArr4.add(item3);

        Order o1 = new Order(itemArr1, "1", "1");
        Order o2 = new Order(itemArr2, "2", "2");
        Order o3 = new Order(itemArr3, "3", "3");
        Order o4 = new Order(itemArr4, "4", "4");
        Order o5 = new Order(itemArr5, "5", "5");
        Order o6 = new Order(itemArr6, "6", "6");
        Order o7 = new Order(itemArr6, "7", "7");

        o1.setOrderTime("19-11-2019");
        o2.setOrderTime("18-11-2019");
        o3.setOrderTime("17-11-2019");
        o4.setOrderTime("16-11-2019");
        o5.setOrderTime("15-11-2019");
        o6.setOrderTime("14-11-2019");
        o7.setOrderTime("13-11-2019");

        orders.add(o1);
        orders.add(o2);
        orders.add(o3);
        orders.add(o4);
        orders.add(o5);
        orders.add(o6);
        orders.add(o7);

        return orders;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        ArrayList<Order> orders = getOrders();
        Bundle bundle = new Bundle();
        bundle.putSerializable("orders", orders);
        if(position == 0){
            WeeklyReportFragment f = new WeeklyReportFragment();
            f.setArguments(bundle);
            return f;
        }
        else if(position == 1){
            MonthlyReportFragment f = new MonthlyReportFragment();
            f.setArguments(bundle);
            return f;
        }
        else if(position == 2){
            YearlyReportFragment f = new YearlyReportFragment();
            f.setArguments(bundle);
            return f;
        }
        else return null;
    }

    @Override
    public int getCount() {
        return countTab;
    }
}
