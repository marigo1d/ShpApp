package com.example.shpapp.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.shpapp.Adapter.CartAdapter;
import com.example.shpapp.Domain.ShoppingCart;
import com.example.shpapp.databinding.ActivityCartBinding;

public class CartActivity extends BaseActivity {
    private ActivityCartBinding binding;
    private ShoppingCart shoppingCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        shoppingCart = ShoppingCart.getInstance(getApplicationContext());

        setVariable();
        initList();
    }

    private void initList() {
        if (shoppingCart.getListCart().isEmpty()){
            binding.emptyTxt.setVisibility(View.VISIBLE);
            binding.scroll.setVisibility(View.GONE);
        } else {
            binding.emptyTxt.setVisibility(View.GONE);
            binding.scroll.setVisibility(View.VISIBLE);
        }

        binding.cartView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.cartView.setAdapter(new CartAdapter(shoppingCart.getListCart(), this, () -> calculatorOnCart()));
    }

    private void calculatorOnCart()
    {
        double percentTax = 0.02;
        double delivery = 10;
        double tax = Math.round(shoppingCart.getTotalFee() + percentTax * 100) / 100;
        double total = Math.round((shoppingCart.getTotalFee() + tax + delivery) * 100) / 100;
        double itemTotal = Math.round(shoppingCart.getTotalFee() * 100) / 100;

        binding.totalFeeTxt.setText("$" + itemTotal);
        binding.taxTxt.setText("$" + tax);
        binding.deliveryTxt.setText("$" + delivery);
        binding.totalTxt.setText("$" + total);

    }

    private void setVariable() {
        binding.backBtn.setOnClickListener(v -> finish());
    }
}