package com.scan.test.adapter;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.scan.test.R;
import com.scan.test.model.ListData;
import com.scan.test.onClickListener.itemOnClickListener;
import com.scan.test.ui.DetailActivity;
import com.willowtreeapps.spruce.Spruce;
import com.willowtreeapps.spruce.animation.DefaultAnimations;
import com.willowtreeapps.spruce.sort.LinearSort;

import java.util.List;

import kotlin.random.Random;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ShowHolder> {
    private List<ListData> dataList;
    private Context context;


    public DataAdapter(List<ListData> dataListData, Context context) {
        this.dataList = dataListData;
        this.context = context;
    }

    @NonNull
    @Override
    public ShowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
        return new ShowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShowHolder holder, int position) {
        final ListData listData = dataList.get(position);
        holder.tv_title.setText(listData.getTitle());
        Glide.with(context).load(listData.getThumb())
                .override(200, 200).into(holder.image_title);
        holder.tv_detail.setText(listData.getContent());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("detail", holder.tv_detail.getText());
                intent.putExtra("title",holder.tv_title.getText());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }

    public class ShowHolder extends RecyclerView.ViewHolder {
        private TextView tv_title, tv_detail;
        private ImageView image_title;
        private ConstraintLayout constraintLayout;

        public ShowHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.titleView);
            tv_detail = (TextView) itemView.findViewById(R.id.DesView);
            image_title = (ImageView) itemView.findViewById(R.id.image_View);
            constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.layout_constraint);
        }


    }
}
