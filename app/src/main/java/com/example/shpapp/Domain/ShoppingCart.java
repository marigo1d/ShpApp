package com.example.shpapp.Domain;

import android.content.Context;


import com.example.shpapp.Helper.DatabaseHelper;

import java.util.ArrayList;

public class ShoppingCart {
    private static ShoppingCart instance;
    private DatabaseHelper dbHelper;
    private ArrayList<CartItem> cartItems;

    private ShoppingCart(Context context) {
        dbHelper = new DatabaseHelper(context);
        cartItems = dbHelper.getAllCartItems(); // 从数据库加载购物车数据
    }

    // 获取单例实例
    public static synchronized ShoppingCart getInstance(Context context) {
        if (instance == null) {
            instance = new ShoppingCart(context.getApplicationContext());
        }
        return instance;
    }

    public void addItem(ItemDomain item) {
        boolean existAlready = false;
        for (CartItem cartItem : cartItems) {
            if (cartItem.getItem().getTitle().equals(item.getTradename())) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                dbHelper.updateCartItem(cartItem); // 更新数据库中的数量
                existAlready = true;
                break;
            }
        }
        if (!existAlready) {
            CartItem newItem = new CartItem(item, 1);
            cartItems.add(newItem);
            dbHelper.addCartItem(newItem); // 添加到数据库
        }
    }

    public ArrayList<CartItem> getListCart() {
        return cartItems;
    }

    public Double getTotalFee() {
        double fee = 0;
        for (CartItem cartItem : cartItems) {
            fee = fee + (cartItem.getItem().getPrice() * cartItem.getQuantity());
        }
        return fee;
    }

    public Double getTotalFee() {
        ArrayList<ItemDomain> listItemDomain = getListCart();
        double fee = 0;
        for (int i = 0; i < listItemDomain.size(); i++) {
            fee = fee + (listItemDomain.get(i).getPrice() * listItemDomain.get(i).getQuantity());
        }
        return fee;
    }

    public void minusNumberItem(int position) {
        if (cartItems.get(position).getQuantity() == 1) {
            dbHelper.deleteCartItem(cartItems.get(position).getId()); // 从数据库删除
            cartItems.remove(position);
        } else {
            cartItems.get(position).setQuantity(cartItems.get(position).getQuantity() - 1);
            dbHelper.updateCartItem(cartItems.get(position)); // 更新数据库中的数量
        }
    }

    public void plusNumberItem(int position) {
        cartItems.get(position).setQuantity(cartItems.get(position).getQuantity() + 1);
        dbHelper.updateCartItem(cartItems.get(position)); // 更新数据库中的数量
    }

    // 从购物车中移除商品
    public void removeItem(int position) {
        dbHelper.deleteCartItem(cartItems.get(position).getId()); // 从数据库删除
        cartItems.remove(position);
    }

    // 清空购物车
    public void clearCart() {
        cartItems.clear();
        // 添加清空数据库中购物车表的逻辑
        dbHelper.clearCartTable();
    }
}
