package com.scan.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.JsonObject;
import com.scan.test.adapter.DataAdapter;
import com.scan.test.model.Data;
import com.scan.test.model.Example;
import com.scan.test.model.ListData;
import com.scan.test.service.APIService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    private Retrofit retrofit;
    private Button button;
    private ArrayList<ListData> listDataArrayListData;
    private DataAdapter dataAdapter;
    String TAG = MainActivity.class.getSimpleName();
    String URL_GET = "http://192.168.200.254/";

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
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        final String strRequestBody = "datas";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), strRequestBody);
        JSONObject paramObject = new JSONObject();
        try {
            paramObject.put("datas", "list_data");
            Call<Data> call = apiService.getData(paramObject.toString());
            call.enqueue(new Callback<Data>() {
                @Override
                public void onResponse(Call<Data> call, Response<Data> response) {
                    List<ListData> listData = response.body().getListData();
                for (int i = 0; i < listData.size(); i++) {
                    listDataArrayListData.add(listDataArrayListData.get(i));
                    Log.d("TAG", "onResponse" + listData.get(i).toString());
                    //    }
                }
                dataAdapter.notifyDataSetChanged();

                }

                @Override
                public void onFailure(Call<Data> call, Throwable t) {

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
