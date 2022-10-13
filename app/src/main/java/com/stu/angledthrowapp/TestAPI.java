package com.stu.angledthrowapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.chromium.net.CronetEngine;
import org.chromium.net.CronetException;
import org.chromium.net.UrlRequest;
import org.chromium.net.UrlResponseInfo;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TestAPI extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_api);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        final TextView textView = (TextView) findViewById(R.id.textViewTEST);

        RequestQueue queue = Volley.newRequestQueue(this);
        //String url = "http://10.0.2.2:5135/api/AngledThrow/id";
        String url = "http://10.0.2.2:5135/api/AngledThrow?angle=45&velocity=15";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        textView.setText("Response is: " + response);
                        System.out.println("GOTTHIIIIIIS:::::::");
                        System.out.println("response is: "+response);
                        Gson gs = new Gson();

                        AngledThrowCalculator.responseAngledThrow = gs.fromJson(response,ResponseAngledThrow.class);
                        System.out.println(AngledThrowCalculator.responseAngledThrow.getFinishTime());
                        System.out.println("HERE!!!!!!!!!!!!!!");

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText(error.getMessage());
                System.out.println("ERROR--------------------------------------------------------");
                System.out.println(error.getMessage());
                System.out.println("ERROR--------------------------------------------------------");
            }
        });
        queue.add(stringRequest);
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


    public class GsonRequest<T> extends Request<T> {
        private final Gson gson = new Gson();
        private final Class<T> clazz;
        private final Map<String, String> headers;
        private final Response.Listener<T> listener;

        /**
         * Make a GET request and return a parsed object from JSON.
         *
         * @param url URL of the request to make
         * @param clazz Relevant class object, for Gson's reflection
         * @param headers Map of request headers
         */
        public GsonRequest(String url, Class<T> clazz, Map<String, String> headers,
                           Response.Listener<T> listener, Response.ErrorListener errorListener) {
            super(Method.GET, url, errorListener);
            this.clazz = clazz;
            this.headers = headers;
            this.listener = listener;
        }

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            return headers != null ? headers : super.getHeaders();
        }

        @Override
        protected void deliverResponse(T response) {
            listener.onResponse(response);
        }

        @Override
        protected Response<T> parseNetworkResponse(NetworkResponse response) {
            try {
                String json = new String(
                        response.data,
                        HttpHeaderParser.parseCharset(response.headers));
                return Response.success(
                        gson.fromJson(json, clazz),
                        HttpHeaderParser.parseCacheHeaders(response));
            } catch (UnsupportedEncodingException e) {
                return Response.error(new ParseError(e));
            } catch (JsonSyntaxException e) {
                return Response.error(new ParseError(e));
            }
        }
    }
}

