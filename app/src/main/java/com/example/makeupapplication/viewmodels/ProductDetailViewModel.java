package com.example.makeupapplication.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.makeupapplication.models.Product;
import com.example.makeupapplication.repositories.ProductDetailRepository;

public class ProductDetailViewModel extends AndroidViewModel {
    private final LiveData<Product> product;

    public ProductDetailViewModel(@NonNull Application application, int id) {
        super(application);
        ProductDetailRepository repository = new ProductDetailRepository(application, id);
        product = repository.getProduct();
    }

    public LiveData<Product> getProduct(){
        return product;
    }
}
