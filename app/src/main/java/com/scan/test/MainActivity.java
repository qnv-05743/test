package com.scan.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.scan.test.adapter.EmployesAdapter;
import com.scan.test.model.Employes;
import com.scan.test.service.APIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    private Button button;
    private ArrayList<Employes> employesArrayList;
    private EmployesAdapter employesAdapter;
    String TAG = MainActivity.class.getSimpleName();
    String URL_GET_PRODUCT = "http://dummy.restapiexample.com/";

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
                .baseUrl(URL_GET_PRODUCT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<Employes>> call = apiService.getAllProduct();
        call.enqueue(new Callback<List<Employes>>() {
            @Override
            public void onResponse(Call<List<Employes>> call, Response<List<Employes>> response) {
                List<Employes> productsList = response.body();
                for (int i = 0; i < productsList.size(); i++) {
                    employesArrayList.add(productsList.get(i));
                    Log.d(TAG, "onResponse" + productsList.get(i).toString());
                }
                employesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Employes>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }


    private void addControl() {
        recyclerView.setHasFixedSize(true);
        // Create 2 col
        mLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        employesArrayList = new ArrayList<>();
        employesAdapter = new EmployesAdapter(employesArrayList, MainActivity.this);
        recyclerView.setAdapter(employesAdapter);
    }
}
