package com.example.shpapp.Activity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.shpapp.Domain.ItemDomain;
import com.example.shpapp.Domain.ShoppingCart;
import com.example.shpapp.databinding.ActivityDetailBinding;

public class DetailActivity extends BaseActivity {
    private ActivityDetailBinding binding;
    private ItemDomain object;
    private int numberOrder = 1;
    private ShoppingCart shoppingCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        shoppingCart = ShoppingCart.getInstance(getApplicationContext());
        getBundles();
    }

    private void getBundles() {
        object = (ItemDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPicUrl(), "drawable",
                this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(binding.itemPic);

        binding.titleTxt.setText(object.getTradename());
        binding.priceTxt.setText("$" + object.getPrice());
        binding.descriptionTxt.setText(object.getDescription());
        binding.reviewTxt.setText(object.getReview() + "");
        binding.ratingTxt.setText(object.getScore() + "");

        binding.addToCartBtn.setOnClickListener(view -> {
            object.setQuantity(numberOrder);
            shoppingCart.addItem(object);
        });

        binding.backBtn.setOnClickListener(view -> finish());
    }
}