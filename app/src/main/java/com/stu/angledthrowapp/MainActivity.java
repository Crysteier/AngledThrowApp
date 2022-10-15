package com.stu.angledthrowapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

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

        BtnShowAnimation = (Button) findViewById(R.id.btnShowAnimation);
        BtnShowGraph = (Button) findViewById(R.id.btnShowGraph);
        BtnCalculateLocally = (Button) findViewById(R.id.btnCalculateLocally);
        BtnCalculateByServer = (Button)findViewById(R.id.btnCalculateServer);
        InputAngle = (EditText) findViewById(R.id.inputAngle);
        InputVelocity = (EditText) findViewById(R.id.inputVelocity);

        BtnShowGraph.setEnabled(AngledThrowCalculator.IsCalculated);
        BtnShowAnimation.setEnabled(AngledThrowCalculator.IsCalculated);

    }

    public void OnLocallyClick(View v) {
        String velocity = InputVelocity.getText().toString();
        String angle = InputAngle.getText().toString();

        if (!CheckInput(velocity,angle)){
            return;
        }

        AngledThrowCalculator.velocity = Double.parseDouble(velocity);
        AngledThrowCalculator.angle = Double.parseDouble(angle);
        Intent i = new Intent(this, TableViewActivity.class);
        AngledThrowCalculator.CalculateLocally();
        Animation anim =  AnimationUtils.loadAnimation(this,R.anim.bounce);
        BtnCalculateLocally.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {   }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {    }
        });

    }

    public void OnByServerClick(View v) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String velocity = InputVelocity.getText().toString();
        String angle = InputAngle.getText().toString();

        if(!CheckInput(velocity,angle)){
            return;
        }
        String url = "http://10.0.2.2:5135/api/AngledThrow?angle="+angle+"&velocity="+velocity;
        System.out.println(url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        System.out.println("response is: "+response);
                        Gson gs = new Gson();
                        AngledThrowCalculator.responseAngledThrow = gs.fromJson(response,ResponseAngledThrow.class);
                        AngledThrowCalculator.lastChange = 2;
                        AngledThrowCalculator.IsCalculated = true;

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR--------------------------------------------------------");
                System.out.println(error.getMessage());
                System.out.println("ERROR--------------------------------------------------------");
                Toast.makeText(MainActivity.this, "Error connecting to server!", Toast.LENGTH_SHORT).show();
                return;
            }
        });
        queue.add(stringRequest);
        Intent i = new Intent(this, TableViewActivity.class);
        Animation anim =  AnimationUtils.loadAnimation(this,R.anim.bounce);
        BtnCalculateByServer.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {   }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {    }
        });
    }

    private boolean CheckInput(String velocity, String angle){
        if(velocity == null || angle == null || velocity.isEmpty() || angle.isEmpty()){
            Toast.makeText(MainActivity.this, "Angle & Velocity is required", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(Double.parseDouble(angle) > 90){
            Toast.makeText(MainActivity.this, "Angle must be less than 90 degrees!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(Double.parseDouble(velocity) <= 0){
            Toast.makeText(MainActivity.this, "Velocity must be greater than 0!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
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

    public void OnAnimationClick(View v){
        Intent i = new Intent(this, AnimationActivity.class);
        startActivity(i);
    }

    public void OnTest(View v){
        Intent i = new Intent(this, TestAPI.class);
        startActivity(i);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        BtnShowGraph.setEnabled(AngledThrowCalculator.IsCalculated);
        BtnShowAnimation.setEnabled(AngledThrowCalculator.IsCalculated);
        System.out.println(AngledThrowCalculator.lastChange+"lastcane!!!!!!!!!!");
    }

    @Override
    protected void onStart() {
        super.onStart();
        BtnShowGraph.setEnabled(AngledThrowCalculator.IsCalculated);
        BtnShowAnimation.setEnabled(AngledThrowCalculator.IsCalculated);
        System.out.println(AngledThrowCalculator.lastChange+"lastchange!!!!!!!!!!!!!!!");
    }
}