package com.exerciseapp.mattiapalmas.fridgerecipes;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListMoviesActivity extends AppCompatActivity {

    private String urlData;
    private String searchInput;
    private RecyclerView recycleView;
    private RecyclerView.Adapter adapter;
    private List<ListItemModel> listItems;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movie);

        Intent intent = getIntent();
        String searchText = intent.getStringExtra("searchText");
        urlData = "http://food2fork.com/api/search?key=77ceade7727cb6f12a33fccedae91521&q="+ searchText;

        recycleView = (RecyclerView) findViewById(R.id.recycleView);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(this));


        listItems = new ArrayList<>();
        loadRecyclerViewData();
    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlData,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("recipes");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);
                                ListItemModel item = new ListItemModel(
                                        obj.getString("title"),
                                        obj.getString("publisher"),
                                        obj.getString("image_url")
                                );
                                listItems.add(item);
                            }
                            adapter = new AdaptorRecycleView(listItems,getApplicationContext());
                            recycleView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Log.d("error", " error: " + error);
                    }
                }
        );

        RequestQueue requesteQueue = Volley.newRequestQueue(this);
        requesteQueue.add(stringRequest);
    }
}
