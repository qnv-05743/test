package com.scan.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scan.test.R;
import com.scan.test.model.Employes;

import java.util.List;

public class EmployesAdapter extends RecyclerView.Adapter<EmployesAdapter.ShowHolder> {
    private List<Employes> employesList;
    private Context context;

    public EmployesAdapter(List<Employes> employesList, Context context) {
        this.employesList = employesList;
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
        Employes employes = employesList.get(position);
        holder.id.setText(employes.getId());
        holder.tvName.setText(employes.getEmployeeName());
        holder.tvSalary.setText(employes.getEmployeeSalary());
        holder.tvAge.setText(employes.getEmployeeAge());
    }

    @Override
    public int getItemCount() {
        return employesList.size();
    }

    public class ShowHolder extends RecyclerView.ViewHolder {
        // private LinearLayout topLayout;
        private TextView tvName;
        private TextView tvSalary;
        private TextView tvAge, id;


        public ShowHolder(@NonNull View itemView) {
            super(itemView);
            // topLayout = (LinearLayout) findViewById(R.id.top_layout);
            tvName = (TextView) itemView.findViewById(R.id.tv_Name);
            tvSalary = (TextView) itemView.findViewById(R.id.tv_salary);
            tvAge = (TextView) itemView.findViewById(R.id.tv_age);
            id = (TextView) itemView.findViewById(R.id.tv_id);

        }
    }
}
