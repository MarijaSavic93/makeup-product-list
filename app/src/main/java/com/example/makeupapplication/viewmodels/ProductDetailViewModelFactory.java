package com.example.makeupapplication.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ProductDetailViewModelFactory implements ViewModelProvider.Factory {
    private final Application application;
    private final int id;

    public ProductDetailViewModelFactory(Application application, int id) {
        this.application = application;
        this.id = id;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(ProductDetailViewModel.class)) {
            return (T) new ProductDetailViewModel(application, id);
        }
        throw new IllegalArgumentException("Unknown ViewModelClass");
    }
}
