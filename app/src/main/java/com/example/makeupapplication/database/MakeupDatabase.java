package com.example.makeupapplication.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.makeupapplication.models.Product;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@androidx.room.Database(entities = {Product.class}, version = 1, exportSchema = false)
public abstract class MakeupDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "makeup";
    public abstract ProductDao productDao();
    private static MakeupDatabase instance;

    public static synchronized MakeupDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, MakeupDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static final Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDatabaseAsync(instance).removeOldData();
        }
    };

    private static class PopulateDatabaseAsync {
        private final ProductDao productDao;

        private PopulateDatabaseAsync(@NonNull MakeupDatabase database){
            productDao = database.productDao();
        }

        protected void removeOldData(){
            ExecutorService populateExecutorService = Executors.newSingleThreadExecutor();

            populateExecutorService.execute(() -> productDao.deleteAll());

            populateExecutorService.shutdown();
        }
    }
}
