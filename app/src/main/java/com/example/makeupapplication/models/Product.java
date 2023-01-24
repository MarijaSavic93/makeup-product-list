package com.example.makeupapplication.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "makeup_products", indices = @Index(value = {"id"}, unique = true))
public class Product {
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private int id;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    private final String name;

    @ColumnInfo(name = "brand")
    @SerializedName("brand")
    private final String brand;

    @ColumnInfo(name = "price")
    @SerializedName("price")
    private final String price;

    @ColumnInfo(name = "image_url")
    @SerializedName("image_link")
    private final String imageUrl;

    @ColumnInfo(name = "type")
    @SerializedName("product_type")
    private final String type;

    public Product(String name, String brand, String price, String imageUrl, String type){
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.imageUrl = imageUrl;
        this.type = type;
    }

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

}



