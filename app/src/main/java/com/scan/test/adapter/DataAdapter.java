package com.scan.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.scan.test.R;
import com.scan.test.model.List;
import com.scan.test.onClickListener.itemOnClickListener;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ShowHolder> {
    private java.util.List dataList;
    private Context context;
    private itemOnClickListener itemClickListeners;

    public void setItemClickListeners(itemOnClickListener itemClickListeners) {
        this.itemClickListeners = itemClickListeners;
    }

    public DataAdapter(ArrayList<List> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ShowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);

        return new ShowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowHolder holder, int position) {
        List list = (List) dataList.get(position);
        holder.tv_title.setText(list.getTitle());
        holder.tv_detail.setText(list.getContent());
        Glide.with(context).load(list.getThumb())
                .override(150, 150).into(holder.image_title);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ShowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // private LinearLayout topLayout;
        private TextView tv_title, tv_detail;
        private ImageView image_title;


        public ShowHolder(@NonNull View itemView) {
            super(itemView);
            // topLayout = (LinearLayout) findViewById(R.id.top_layout);
            tv_title = (TextView) itemView.findViewById(R.id.txt_title);
            tv_detail = (TextView) itemView.findViewById(R.id.txt_detail);
            image_title = (ImageView) itemView.findViewById(R.id.image_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListeners.onClick(v, getAdapterPosition());
        }

    }
}
