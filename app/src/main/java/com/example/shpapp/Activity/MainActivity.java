package com.example.shpapp.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.shpapp.Adapter.PopularAdapter;
import com.example.shpapp.Domain.ItemDomain;
import com.example.shpapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    ActivityMainBinding binding;  // 针对xml文件，将使用xml驼峰文件名+Bindng的方式生成对应的java类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        initPopularRecyclerView();
        initBottomNavigation();
    }

    private void initBottomNavigation() {
        binding.cartBtn.setOnClickListener(view -> startActivity(new Intent(this, CartActivity.class)));

    }

    private void initPopularRecyclerView() {
        ArrayList<ItemDomain> itemDomains = new ArrayList<>();
        // 待修改，通过后端获取信息
        itemDomains.add(new ItemDomain("T-shirt black", "item_1", 15, 4, 500, "test"));
        itemDomains.add(new ItemDomain("Smart Watch", "item_2", 10, 4.5, 450, "test"));
        itemDomains.add(new ItemDomain("Phone", "item_3", 3, 4.9, 800, "test"));

        binding.PopularView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.PopularView.setAdapter(new PopularAdapter(itemDomains));
    }
}