package com.stu.angledthrowapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.model.GradientColor;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GraphActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        //pre back button v actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        LineChart chart = (LineChart) findViewById(R.id.chart);
        CreateChart(chart);
    }

    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void CreateChart(LineChart chart) {
        List<Entry> entries = new ArrayList<Entry>();
        for (int i = 0; i < AngledThrowCalculator.timePoints.size(); i++) {
            float x = AngledThrowCalculator.timePoints.get(i).floatValue();
            float y = AngledThrowCalculator.yCoords.get(i).floatValue();
            entries.add(new Entry(x,y));
        }

        int startColor1 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
        int startColor2 = ContextCompat.getColor(this, android.R.color.holo_blue_light);
        int startColor3 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
        int startColor4 = ContextCompat.getColor(this, android.R.color.holo_green_light);
        int startColor5 = ContextCompat.getColor(this, android.R.color.holo_red_light);
        int endColor1 = ContextCompat.getColor(this, android.R.color.holo_blue_dark);
        int endColor2 = ContextCompat.getColor(this, android.R.color.holo_purple);
        int endColor3 = ContextCompat.getColor(this, android.R.color.holo_green_dark);
        int endColor4 = ContextCompat.getColor(this, android.R.color.holo_red_dark);
        int endColor5 = ContextCompat.getColor(this, android.R.color.holo_orange_dark);
        int lineColor = Color.parseColor("#00439a");
        int bkgColor = Color.parseColor("#FAFAFA");
        int black = ContextCompat.getColor(this, android.R.color.black);

        LineDataSet dataSet = new LineDataSet(entries, "Trajectory of projectile"); // add entries to dataset
        dataSet.setColor(black);
        dataSet.setValueTextColor(endColor4); // styling, ...
        dataSet.setValueTextSize(12);

        LineData lineData = new LineData(dataSet);

        Description description = new Description();
        description.setText("Y - vyska, X - cas");
        description.setTextSize(12);

        chart.setBackgroundColor(bkgColor);
        chart.setDescription(description);
        chart.setData(lineData);

        chart.getAxisRight().setEnabled(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.RED);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(AngledThrowCalculator.xCoords.size()-1,true);
        xAxis.setGranularityEnabled(true);
        xAxis.setGranularity(0.1f);
        xAxis.mAxisMinimum = 0;
        xAxis.mAxisMaximum = AngledThrowCalculator.timePoints.get(AngledThrowCalculator.xCoords.size()-1).floatValue();


        chart.invalidate(); // refresh
    }
}