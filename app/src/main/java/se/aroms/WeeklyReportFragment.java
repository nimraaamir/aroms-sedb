package se.aroms;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WeeklyReportFragment extends Fragment {

    private ArrayList<Order> orders;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.weekly_report, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle bundle = this.getArguments();
        if(bundle != null){
            orders = (ArrayList<Order>) bundle.getSerializable("orders");
        }
        computeSalesAndProfit();
    }

    void computeSalesAndProfit(){

        Date temp = null;
        SimpleDateFormat dateformat2 = new SimpleDateFormat("dd-MM-yyyy");
        try {
            temp = dateformat2.parse("19-11-2019");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        double []sales = new double[7];
        double []incurredCosts = new double[7];
        double []profit = new double[7];
        for(int i=0; i < orders.size(); i++){
            long diffInMillies = Math.abs(temp.getTime() - orders.get(i).getOrderTime().getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            if(diff <=6){
                int index = (int)diff;
                for(int j=0; j < orders.get(i).getOrderItems().size(); j++){
                    sales[index] = sales[index] + orders.get(i).getOrderItems().get(j).getPrice();
                    incurredCosts[index] = incurredCosts[index] + orders.get(i).getOrderItems().get(j).getIncured_price();
                }
            }
        }

        for(int i=0; i < 7; i++){
            profit[i] = ((sales[i] - incurredCosts[i])/sales[i]) * 100;
        }

        GraphView salesGraph = (GraphView) getView().findViewById(R.id.salesGraph);
        LineGraphSeries<DataPoint> salesLine = new LineGraphSeries<>();
        GraphView profitGraph = (GraphView) getView().findViewById(R.id.profitGraph);
        LineGraphSeries<DataPoint> profitLine = new LineGraphSeries<>();

        for(int i=0; i <= 6; i++){
            salesLine.appendData(new DataPoint(i+1, sales[6-i]), true, 500);
            profitLine.appendData(new DataPoint(i+1, profit[6-i]), true, 500);
        }
        salesGraph.getViewport().setMinX(1);
        salesGraph.getViewport().setMaxX(7);
        salesGraph.getViewport().setScalable(true);
        salesGraph.getViewport().setScalableY(true);

        profitGraph.getViewport().setMinX(1);
        profitGraph.getViewport().setMaxX(7);
        profitGraph.getViewport().setScalable(true);
        profitGraph.getViewport().setScalableY(true);

        salesGraph.addSeries(salesLine);
        profitGraph.addSeries(profitLine);

    }


    void displaySalesAndProfit(){
        GraphView graph = (GraphView) getView().findViewById(R.id.salesGraph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        series.setColor(Color.RED);
        graph.addSeries(series);

        GraphView graph2 = (GraphView) getView().findViewById(R.id.profitGraph);
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        series2.setColor(Color.RED);
        graph2.addSeries(series2);
    }
}
