package com.example.appmobile_amanigharbi.ui.profile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmobile_amanigharbi.R;

import java.util.ArrayList;

class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.MyHolder> {
    ArrayList<String> data;

    public MyRvAdapter(ArrayList<String> data) {
        this.data = data;
    }



    @NonNull
    @Override

        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
            return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.tvNom.setText(data.get(position));
        holder.tvAge.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView tvNom;
        TextView tvAge;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvNom = itemView.findViewById(R.id.NomEnfant);
            tvAge = itemView.findViewById(R.id.AgeEnfant);
        }
    }

}