package com.example.project;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<String> titles;
    List<Integer> images;
    List<Data> data;
    Adapter adapter;
    private static String url="https://picsum.photos/list";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.dataList);


        data=new ArrayList<>();
        titles = new ArrayList<>();
        images = new ArrayList<>();

        getData();
    }

    private void getData() {
        RequestQueue queue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        Data d = new Data();
                        d.setAuthor(jsonObject.getString("author").toString());
                        d.setUrl(jsonObject.getString("id").toString());
                        data.add(d);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                adapter = new Adapter(getApplicationContext(),data);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2, GridLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "onErrorResponse: "+error.getMessage());
            }
        });
    queue.add(jsonArrayRequest);

    }
}
