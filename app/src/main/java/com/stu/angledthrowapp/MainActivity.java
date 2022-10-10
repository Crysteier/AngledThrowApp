package com.stu.angledthrowapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText InputAngle, InputVelocity;
    Button BtnCalculateLocally;
    Button BtnCalculateByServer;
    Button BtnShowGraph;
    Button BtnShowAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BtnShowGraph = (Button) findViewById(R.id.btnShowGraph);
        BtnCalculateLocally = (Button) findViewById(R.id.btnCalculateLocally);
        InputAngle = (EditText) findViewById(R.id.inputAngle);
        InputVelocity = (EditText) findViewById(R.id.inputVelocity);

        BtnShowGraph.setEnabled(AngledThrowCalculator.IsCalculated);
    }

    public void OnLocallyClick(View v) {
        String velocity = InputVelocity.getText().toString();
        String angle = InputAngle.getText().toString();
        AngledThrowCalculator.velocity = Double.parseDouble(velocity);
        AngledThrowCalculator.angle = Double.parseDouble(angle);
        Intent i = new Intent(this, ListViewData.class);
        Animation anim =  AnimationUtils.loadAnimation(this,R.anim.bounce);
        BtnCalculateLocally.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    public void OnByServerClick(View v) {

    }

    public void GraphTest(View v){
        Intent i = new Intent(this, GraphActivity.class);
        Animation anim =  AnimationUtils.loadAnimation(this,R.anim.bounce);
        BtnShowGraph.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        BtnShowGraph.setEnabled(AngledThrowCalculator.IsCalculated);
    }

    @Override
    protected void onStart() {
        super.onStart();
        BtnShowGraph.setEnabled(AngledThrowCalculator.IsCalculated);
    }
}