package com.scan.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.scan.test.adapter.DataAdapter;
import com.scan.test.model.List;
import com.scan.test.service.APIService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    private Retrofit retrofit;
    private Button button;
    private ArrayList<List> listDataArrayList;
    private DataAdapter dataAdapter;
    String TAG = MainActivity.class.getSimpleName();
    String URL_GET = "http://192.168.200.254/testapi/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        button = findViewById(R.id.btn_get);
        addControl();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllProduct();
            }
        });
    }

    private void getAllProduct() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_GET)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List> call = apiService.getData("");
        call.enqueue(new Callback<List>() {
            @Override
            public void onResponse(Call<List> call, Response<List> response) {
                java.util.List listData = (java.util.List) response.body();
                for (int i = 0; i < listData.size(); i++) {
                    listDataArrayList.add(listDataArrayList.get(i));
                    Log.d("TAG", "onResponse" + listData.get(i).toString());
                }
                dataAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<List> call, Throwable t) {

            }
        });
    }

    private void addControl() {
        recyclerView.setHasFixedSize(true);
        // Create 2 col
        mLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        listDataArrayList = new ArrayList<>();
        dataAdapter = new DataAdapter(listDataArrayList, MainActivity.this);
        recyclerView.setAdapter(dataAdapter);
    }
}
