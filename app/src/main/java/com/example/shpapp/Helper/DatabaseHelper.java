package com.example.shpapp.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.shpapp.Domain.ItemDomain;
import com.example.shpapp.Domain.CartItem;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ecommerce.db";
    private static final int DATABASE_VERSION = 1;

    // 商品表
    private static final String TABLE_ITEMS = "items";
    private static final String COLUMN_ITEM_ID = "item_id";
    private static final String COLUMN_ITEM_TRADENAME = "tradename";
    private static final String COLUMN_ITEM_PRICE = "price";
    private static final String COLUMN_ITEM_PIC_URL = "pic_url";
    private static final String COLUMN_ITEM_SCORE = "score";
    private static final String COLUMN_ITEM_DESCRIPTION = "description";

    // 购物车表
    private static final String TABLE_CART = "cart_items";
    private static final String COLUMN_CART_ID = "id";
    private static final String COLUMN_QUANTITY = "quantity";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建商品表
        String createItemsTable = "CREATE TABLE " + TABLE_ITEMS + "("
                + COLUMN_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_ITEM_TRADENAME + " TEXT,"
                + COLUMN_ITEM_PRICE + " REAL,"
                + COLUMN_ITEM_PIC_URL + " TEXT,"
                + COLUMN_ITEM_SCORE + "REAL,"
                + COLUMN_ITEM_DESCRIPTION + "TEXT"
                + ")";
        db.execSQL(createItemsTable);

        // 创建购物车表
        String createCartTable = "CREATE TABLE " + TABLE_CART + "("
                + COLUMN_CART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_ITEM_ID + " INTEGER,"
                + COLUMN_QUANTITY + " INTEGER"
                + "FOREIGN KEY (" + COLUMN_ITEM_ID + ") REFERENCES " + TABLE_ITEMS + "(" + COLUMN_ITEM_ID + ") ON DELETE CASCADE"
                + ")";
        db.execSQL(createCartTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 数据库升级逻辑，例如删除旧表并创建新表
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        onCreate(db);
    }

    // 商品表操作

    // 添加商品到商品表
    public void addItem(ItemDomain item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM_TRADENAME, item.getTradename());
        values.put(COLUMN_ITEM_PRICE, item.getPrice());
        values.put(COLUMN_ITEM_PIC_URL, item.getPicUrl());
        values.put(COLUMN_ITEM_SCORE, item.getScore());
        values.put(COLUMN_ITEM_DESCRIPTION, item.getDescription());

        db.insert(TABLE_ITEMS, null, values);
        db.close();
    }

    public ItemDomain getItemById(int itemId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_ITEMS + " WHERE " + COLUMN_ITEM_ID + " = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(itemId)});

        ItemDomain item = null;
        if (cursor != null && cursor.moveToFirst()) {
            // ... 读取商品信息并创建 ItemDomain 对象
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ITEM_ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ITEM_TRADENAME));
            String picUrl = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ITEM_PIC_URL));
            double price = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_ITEM_PRICE));
            double score = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_ITEM_SCORE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ITEM_DESCRIPTION));

            item = new ItemDomain(id, title, picUrl, score, price, description);
            cursor.close();
        }

        db.close();
        return item;
    }

    // 更新商品信息
    public void updateItem(ItemDomain item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM_TRADENAME, item.getTradename());
        values.put(COLUMN_ITEM_PRICE, item.getPrice());
        values.put(COLUMN_ITEM_PIC_URL, item.getPicUrl());
        values.put(COLUMN_ITEM_SCORE, item.getScore());
        values.put(COLUMN_ITEM_DESCRIPTION, item.getDescription());

        db.update(TABLE_ITEMS, values, COLUMN_ITEM_ID + " = ?",
                new String[]{String.valueOf(item.getId())});
        db.close();
    }

    // 删除商品
    public void deleteItem(int itemId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ITEMS, COLUMN_ITEM_ID + " = ?",
                new String[]{String.valueOf(itemId)});
        db.close();
    }


    // 购物车表操作
    public void addCartItem(CartItem cartItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM_ID, cartItem.getId());
        values.put(COLUMN_QUANTITY, cartItem.getQuantity());
        db.insert(TABLE_CART, null, values);
        db.close();
    }

    public ArrayList<CartItem> getAllCartItems() {
        ArrayList<CartItem> cartItems = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_CART;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CART_ID));
                int itemId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ITEM_ID));
                int quantity = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY));

                ItemDomain item = getItemById(itemId);

                if (item != null) {
                    CartItem cartItem = new CartItem(id, item, quantity);
                    cartItems.add(cartItem);
                }
            } while (cursor.moveToNext());
            cursor.close();
        }

        db.close();
        return cartItems;
    }

    public void updateCartItem(CartItem cartItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_QUANTITY, cartItem.getQuantity());
        db.update(TABLE_CART, values, COLUMN_CART_ID + " = ?",
                new String[]{String.valueOf(cartItem.getId())});
        db.close();
    }

    public void deleteCartItem(int cartItemId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CART, COLUMN_CART_ID + " = ?",
                new String[]{String.valueOf(cartItemId)});
        db.close();
    }
}