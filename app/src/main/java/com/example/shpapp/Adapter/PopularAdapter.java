package com.example.shpapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.shpapp.Activity.DetailActivity;
import com.example.shpapp.Domain.ItemDomain;
import com.example.shpapp.databinding.ViewholderPupListBinding;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.Viewholder> {
    ArrayList<ItemDomain> itemDomains;
    Context context;
    ViewholderPupListBinding binding;

    public PopularAdapter(ArrayList<ItemDomain> itemDomains) {
        this.itemDomains = itemDomains;
    }

    @NonNull
    @Override
    public PopularAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ViewholderPupListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        context = parent.getContext();
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.Viewholder holder, int position) {
        binding.titleTxt.setText(itemDomains.get(position).getTradename());
        binding.feeTxt.setText("$" + itemDomains.get(position).getPrice());
        binding.scoreTxt.setText("" + itemDomains.get(position).getScore());
        binding.reviewTxt.setText("" + itemDomains.get(position).getReview());

        int drawableResourced = holder.itemView.getResources().getIdentifier(itemDomains.get(position).getPicUrl()
        , "drawable", context.getPackageName());  // 待修改

        Glide.with(context)
                .load(drawableResourced)
                .transform(new GranularRoundedCorners(30, 30, 0, 0))
                .into(binding.pic);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("object", itemDomains.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return itemDomains.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder{
        public Viewholder(@NonNull ViewholderPupListBinding binding) {
            super(binding.getRoot());
        }
    }
}
