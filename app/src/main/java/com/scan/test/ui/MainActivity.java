package com.scan.test.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.scan.test.R;
import com.scan.test.adapter.DataAdapter;
import com.scan.test.model.DataObj;
import com.scan.test.model.ListData;
import com.scan.test.service.APIClient;
import com.scan.test.service.APIService;
import com.scan.test.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    private DataAdapter dataAdapter;
    private List<ListData> dataList;
    private ProgressBar progressBar;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.GONE);
        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        getData();
        addControl();
    }


    private void getData() {
        progressBar.setVisibility(View.VISIBLE);
        final APIService apiService = APIClient.self().getRetrofit().create(APIService.class);
        long timeDate = System.currentTimeMillis();
        String token = Utils.md5("" + (timeDate / 1000));
        String param = "list_data";
        Call<DataObj> call = apiService.getData(param, token);
        call.enqueue(new Callback<DataObj>() {
            @Override
            public void onResponse(Call<DataObj> call, Response<DataObj> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    dataList.clear();
                    dataList.addAll(response.body().getData().getList());
                }
                dataAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<DataObj> call, Throwable t) {
                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void addControl() {
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        dataList = new ArrayList<>();
        dataAdapter = new DataAdapter(dataList, MainActivity.this);
        recyclerView.setAdapter(dataAdapter);
    }
}
