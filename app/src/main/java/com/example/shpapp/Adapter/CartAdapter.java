package com.example.shpapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.shpapp.Domain.ItemDomain;
import com.example.shpapp.Helper.ChangeNumberItemsListener;
import com.example.shpapp.Domain.ShoppingCart;
import com.example.shpapp.databinding.ViewholderCartBinding;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Viewholder> {
    ArrayList<ItemDomain> itemDomains;
    Context context;
    ViewholderCartBinding binding;
    ChangeNumberItemsListener changeNumberItemsListener;
    ShoppingCart shoppingCart;

    public CartAdapter(ArrayList<ItemDomain> itemDomains, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.itemDomains = itemDomains;
        this.context = context;
        this.changeNumberItemsListener = changeNumberItemsListener;
        shoppingCart = ShoppingCart.getInstance(context.getApplicationContext());
    }

    @NonNull
    @Override
    public CartAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.Viewholder holder, int position) {
        binding.titleTxt.setText(itemDomains.get(position).getTradename());
        binding.feeEachItem.setText("$" + itemDomains.get(position).getPrice());
        binding.totalEachItem.setText("$" + Math.round(itemDomains.get(position).getQuantity() * itemDomains.get(position).getPrice()));
        binding.numPerItemTxt.setText(String.valueOf(itemDomains.get(position).getQuantity()));

        int drawableResourced = holder.itemView.getResources().getIdentifier(itemDomains.get(position).getPicUrl()
        , "drawable", context.getPackageName());  // 待修改

        Glide.with(context)
                .load(drawableResourced)
                .transform(new GranularRoundedCorners(30, 30, 0, 0))
                .into(binding.pic);

        binding.plusCartBtn.setOnClickListener(view -> shoppingCart.plusNumberItem(itemDomains, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.change();
        }));

        binding.minusCartBtn.setOnClickListener(view -> shoppingCart.minusNumberItem(itemDomains, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.change();
        }));
    }

    @Override
    public int getItemCount() {
        return itemDomains.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder{
        public Viewholder(@NonNull ViewholderCartBinding binding) {
            super(binding.getRoot());
        }
    }
}
