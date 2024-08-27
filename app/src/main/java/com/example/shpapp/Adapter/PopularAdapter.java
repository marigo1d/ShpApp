package com.example.shpapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.shpapp.Domain.PopularDomain;
import com.example.shpapp.databinding.ViewholderPupListBinding;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.Viewholder> {
    ArrayList<PopularDomain> items;
    Context context;
    ViewholderPupListBinding binding;

    public PopularAdapter(ArrayList<PopularDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public PopularAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.binding = ViewholderPupListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        this.context = parent.getContext();
        return new Viewholder(this.binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.Viewholder holder, int position) {
        binding.titleTxt.setText(items.get(position).getTitle());
        binding.feeTxt.setText("$" + items.get(position).getPrices());
        binding.scoreTxt.setText("" + items.get(position).getScore());

        int drawableResourced = holder.itemView.getResources().getIdentifier(items.get(position).getPicUrl()
        , "drawable", context.getPackageName());  // 待修改

        Glide.with(context)
                .load(drawableResourced)
                .transform(new GranularRoundedCorners(30, 30, 0, 0))
                .into(binding.pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder{
        public Viewholder(@NonNull ViewholderPupListBinding binding) {
            super(binding.getRoot());
        }
    }
}
