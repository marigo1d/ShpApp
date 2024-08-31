package com.example.shpapp.Domain;

public class CartItem {
    private int id;  // 购物车项id，考虑存在相同商品但是不同项情况
    private ItemDomain itemDomain;
    private int quantity;

    public CartItem(int id, ItemDomain itemDomain, int quantity) {
        this.id = id;
        this.itemDomain = itemDomain;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ItemDomain getItemDomain() {
        return itemDomain;
    }

    public void setItemDomain(ItemDomain itemDomain) {
        this.itemDomain = itemDomain;
    }
}
