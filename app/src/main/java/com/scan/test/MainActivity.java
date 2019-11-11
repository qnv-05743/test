package com.scan.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.scan.test.adapter.DataAdapter;
import com.scan.test.model.Data;
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
    private Button button;
    private ArrayList<ListData> listDataArrayListData;
    private DataAdapter dataAdapter;
    String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        //button = findViewById(R.id.btn_get);
        addControl();
        getData();
    }


    private void getData() {

        final APIService apiService = APIClient.self().getRetrofit().create(APIService.class);
        long timeDate = System.currentTimeMillis();
        String token = Utils.md5("" + (timeDate / 1000));
        String param = "list_data";
        Call<DataObj> call = apiService.getData(param, token);
        call.enqueue(new Callback<DataObj>() {
            @Override
            public void onResponse(Call<DataObj> call, Response<DataObj> response) {
                Data data = response.body().getData();
                if (data != null && data.getList() != null && !data.getList().isEmpty()) {
                    for (int i = 0; i < data.getList().size(); i++) {
                        data.getList().add(listDataArrayListData.get(i));
                    }
                    addControl();
                    dataAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "List : " + " " + response.body().getData().getList().size(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DataObj> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void addControl() {
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        listDataArrayListData = new ArrayList<>();
        dataAdapter = new DataAdapter(listDataArrayListData, MainActivity.this);
        recyclerView.setAdapter(dataAdapter);

    }
}
