package com.example.makeupapplication.repositories;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.makeupapplication.api.RetrofitClient;
import com.example.makeupapplication.database.MakeupDatabase;
import com.example.makeupapplication.database.ProductDao;
import com.example.makeupapplication.models.Product;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListRepository {
    private static final String TAG = "ProductListRepository";

    private final ProductDao productDao;
    private final LiveData<List<Product>> allProducts;

    public ProductListRepository(Application application){
        MakeupDatabase database = MakeupDatabase.getInstance(application);
        productDao = database.productDao();
        allProducts = productDao.getAllProducts();
    }

    public void insert(List<Product> products){
        new InsertAsyncTask(productDao).insertData(products);
    }

    public LiveData<List<Product>> getAllProducts(){
        return allProducts;
    }

    public void insertFromNetwork(){
        Call<List<Product>> call = RetrofitClient.getInstance().getApi().getProducts("maybelline");
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(@NonNull Call<List<Product>> call, @NonNull Response<List<Product>> response) {
                if(response.isSuccessful()){
                    insert(response.body());
                }else{
                    Log.e(TAG, response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Product>> call, @NonNull Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private static class InsertAsyncTask {
        private final ProductDao productDao;

        public InsertAsyncTask(ProductDao productDao){
            this.productDao = productDao;
        }

        protected void insertData(List<Product> products){
            ExecutorService insertDataExecutor = Executors.newSingleThreadExecutor();
            insertDataExecutor.execute(() -> productDao.insert(products));

            insertDataExecutor.shutdown();
        }
    }
}
