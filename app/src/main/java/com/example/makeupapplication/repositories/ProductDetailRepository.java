package com.example.makeupapplication.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.makeupapplication.database.MakeupDatabase;
import com.example.makeupapplication.database.ProductDao;
import com.example.makeupapplication.models.Product;

public class ProductDetailRepository {
    private final LiveData<Product> product;

    public ProductDetailRepository(Application application, int id){
        MakeupDatabase database = MakeupDatabase.getInstance(application);
        ProductDao productDao = database.productDao();
        product = productDao.getProduct(id);
    }

    public LiveData<Product> getProduct(){
        return product;
    }
}
