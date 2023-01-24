package com.example.makeupapplication.database;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.makeupapplication.models.Product;

import java.util.List;

@androidx.room.Dao
public interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Product> products);

    @Query("SELECT * FROM makeup_products")
    LiveData<List<Product>> getAllProducts();

    @Query("SELECT * FROM makeup_products WHERE id = :id")
    LiveData<Product> getProduct(int id);

    @Query("DELETE FROM makeup_products")
    void deleteAll();
}
