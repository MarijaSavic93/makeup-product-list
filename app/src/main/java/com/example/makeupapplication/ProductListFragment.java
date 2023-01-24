package com.example.makeupapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.makeupapplication.adapters.OnItemClickListener;
import com.example.makeupapplication.adapters.ProductsAdapter;
import com.example.makeupapplication.models.Product;
import com.example.makeupapplication.viewmodels.ProductListViewModel;

import java.util.ArrayList;

public class ProductListFragment extends Fragment implements OnItemClickListener {

    private Context context;
    private RecyclerView rvProducts;
    private ProductsAdapter productsAdapter;

    public ProductListFragment() {
    }

    public static ProductListFragment newInstance() {
        return new ProductListFragment();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        ArrayList<Product> productList = new ArrayList<>();
        productsAdapter = new ProductsAdapter(productList, this, context);

        rvProducts = view.findViewById(R.id.rv_product);
        rvProducts.setLayoutManager(new GridLayoutManager(context, 2));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ProductListViewModel viewModel = new ViewModelProvider(this).get(ProductListViewModel.class);

        viewModel.makeRequest();

        viewModel.getAllProducts().observe(getViewLifecycleOwner(), products -> {
            rvProducts.setAdapter(productsAdapter);
            productsAdapter.setProductList(products);
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onItemClick(View view, int position) {
        NavDirections action = ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(productsAdapter.getProductList().get(position).getId());
        Navigation.findNavController(view).navigate(action);

    }
}