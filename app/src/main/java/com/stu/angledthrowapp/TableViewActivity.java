package com.stu.angledthrowapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class TableViewActivity extends AppCompatActivity {

    private static final String[] TABLE_HEADERS = {"Cas", "X", "Y"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_view);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        TableView tableView = (TableView) findViewById(R.id.tableView);
        tableView.setColumnCount(3);

        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this, TABLE_HEADERS));
        List<String[]> datas = new ArrayList<>();

        if (AngledThrowCalculator.lastChange == 1) {
            for (int i = 0; i < AngledThrowCalculator.xCoords.size(); i++) {
                String[] help = {AngledThrowCalculator.timePoints.get(i).toString(),
                        AngledThrowCalculator.xCoords.get(i).toString(),
                        AngledThrowCalculator.yCoords.get(i).toString()};
                datas.add(help);
            }
            tableView.setDataAdapter(new SimpleTableDataAdapter(this, datas));
        } else if (AngledThrowCalculator.lastChange == 2) {
            ArrayList<Double> steps = new ArrayList<>();
            for (double i = 0; i <= AngledThrowCalculator.responseAngledThrow.getFinishTime(); i += 0.1) {
                steps.add(i);
            }
            steps.add(AngledThrowCalculator.responseAngledThrow.getFinishTime());
            for (int i = 0; i < AngledThrowCalculator.responseAngledThrow.getCoords().size(); i++) {
                String[] help = {steps.get(i).toString(),
                        AngledThrowCalculator.responseAngledThrow.getCoords().get(i).getX().toString(),
                        AngledThrowCalculator.responseAngledThrow.getCoords().get(i).getY().toString()};
                datas.add(help);
            }
            tableView.setDataAdapter(new SimpleTableDataAdapter(this, datas));
        } else {
            throw new IllegalArgumentException();
        }

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
}