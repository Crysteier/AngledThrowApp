package com.stu.angledthrowapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListViewData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_data);

        int i, iMax;
        String x = "", y = "", time = "";

        AngledThrowCalculator.CalculateLocally();
        iMax = AngledThrowCalculator.xCoords.size();
        System.out.println("max iterations: "+iMax);
        String[] dataList = new String[iMax];

        for (i = 0; i < iMax; i++) {
            x = String.valueOf(AngledThrowCalculator.xCoords.get(i));
            y = String.valueOf(AngledThrowCalculator.yCoords.get(i));
            time = String.valueOf(AngledThrowCalculator.timePoints.get(i));
            dataList[i] = "X pos: " + x + "\nY pos: " + y + "\ntime: " + time;
        }
        ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_list_view_item, dataList);
        ListView listView = (ListView) findViewById(R.id.dataView);
        listView.setAdapter(adapter);
    }
}