package com.stu.angledthrowapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText InputAngle, InputVelocity;
    Button BtnCalculateLocally, CalculateByServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BtnCalculateLocally = (Button) findViewById(R.id.btnCalculateLocally);
        InputAngle = (EditText) findViewById(R.id.inputAngle);
        InputVelocity = (EditText) findViewById(R.id.inputVelocity);
    }

    public void OnLocallyClick(View v) {
        String velocity = InputVelocity.getText().toString();
        String angle = InputAngle.getText().toString();
        AngledThrowCalculator.velocity = Double.parseDouble(velocity);
        AngledThrowCalculator.angle = Double.parseDouble(angle);
        Intent i = new Intent(this, ListViewData.class);
        startActivity(i);
    }

    public void OnByServerClick(View v) {

    }
}