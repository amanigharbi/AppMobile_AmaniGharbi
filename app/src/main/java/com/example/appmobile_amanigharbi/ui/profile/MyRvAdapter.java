package com.example.appmobile_amanigharbi.ui.profile;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmobile_amanigharbi.R;

import java.util.ArrayList;
import java.util.List;

class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.MyHolder> {
    List<ModelData> data;
    public MyRvAdapter(List<ModelData> data) {
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
        holder.tvNom.setText(data.get(position).getFirstName()+" "+data.get(position).getLastName());
        holder.tvAge.setText(data.get(position).getAge()+" Ans");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView tvNom;
        TextView tvAge;
        CardView cardView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvNom = itemView.findViewById(R.id.NomEnfant);
            tvAge = itemView.findViewById(R.id.AgeEnfant);
            cardView =  (CardView) itemView.findViewById(R.id.card1);
            cardView.setOnClickListener(this::onClick);
        }

        public void onClick(View v)
        {
            Intent intent = new Intent(v.getContext(),EditProfileFragment.class);
            intent.putExtra("message", "hh");
            v.getContext().startActivity(intent);
        }

        }


}