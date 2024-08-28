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
import com.example.shpapp.Activity.DetailActivity;
import com.example.shpapp.Domain.PopularDomain;
import com.example.shpapp.Helper.ChangeNumberItemsListener;
import com.example.shpapp.Helper.ManagmentCart;
import com.example.shpapp.databinding.ViewholderCartBinding;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Viewholder> {
    ArrayList<PopularDomain> items;
    Context context;
    ViewholderCartBinding binding;
    ChangeNumberItemsListener changeNumberItemsListener;
    ManagmentCart managmentCart;

    public CartAdapter(ArrayList<PopularDomain> items, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.items = items;
        this.context = context;
        this.changeNumberItemsListener = changeNumberItemsListener;
        managmentCart = new ManagmentCart(context);
    }

    @NonNull
    @Override
    public CartAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.Viewholder holder, int position) {
        binding.titleTxt.setText(items.get(position).getTitle());
        binding.feeEachItem.setText("$" + items.get(position).getPrice());
        binding.totalEachItem.setText("$" + Math.round(items.get(position).getNumberInCart() * items.get(position).getPrice()));
        binding.numPerItemTxt.setText(String.valueOf(items.get(position).getNumberInCart()));

        int drawableResourced = holder.itemView.getResources().getIdentifier(items.get(position).getPicUrl()
        , "drawable", context.getPackageName());  // 待修改

        Glide.with(context)
                .load(drawableResourced)
                .transform(new GranularRoundedCorners(30, 30, 0, 0))
                .into(binding.pic);

        binding.plusCartBtn.setOnClickListener(view -> managmentCart.plusNumberItem(items, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.change();
        }));

        binding.minusCartBtn.setOnClickListener(view -> managmentCart.minusNumberItem(items, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.change();
        }));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder{
        public Viewholder(@NonNull ViewholderCartBinding binding) {
            super(binding.getRoot());
        }
    }
}
