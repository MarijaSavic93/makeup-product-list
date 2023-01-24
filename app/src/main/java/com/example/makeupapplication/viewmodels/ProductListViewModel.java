package com.example.makeupapplication.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.makeupapplication.models.Product;
import com.example.makeupapplication.repositories.ProductListRepository;

import java.util.List;

public class ProductListViewModel extends AndroidViewModel {
    private final ProductListRepository repository;
    private final LiveData<List<Product>> allProducts;

    public ProductListViewModel(@NonNull Application application) {
        super(application);
        repository = new ProductListRepository(application);
        allProducts = repository.getAllProducts();
    }

    public void makeRequest(){
        repository.insertFromNetwork();
    }

    public LiveData<List<Product>> getAllProducts(){
        return allProducts;
    }
}
