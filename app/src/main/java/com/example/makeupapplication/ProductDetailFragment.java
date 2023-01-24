package com.example.makeupapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.makeupapplication.viewmodels.ProductDetailViewModel;
import com.example.makeupapplication.viewmodels.ProductDetailViewModelFactory;

public class ProductDetailFragment extends Fragment implements View.OnClickListener {

    private int productId;
    private ImageView ivProduct;
    private TextView tvTitle, tvBrand, tvPrice;
    private Context context;

    public ProductDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productId = ProductDetailFragmentArgs.fromBundle(getArguments()).getProductIdArg();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        initComponents(view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ProductDetailViewModel viewModel = new ViewModelProvider(this, new ProductDetailViewModelFactory(requireActivity().getApplication(), productId)).get(ProductDetailViewModel.class);

        viewModel.getProduct().observe(getViewLifecycleOwner(), product -> {
            Glide.with(context).load(product.getImageUrl())
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .placeholder(R.drawable.ic_pending)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(ivProduct);

            tvTitle.setText(product.getName());
            tvBrand.setText(String.format("Brand: %s", product.getBrand()));
            tvPrice.setText(String.format("Price: %s EUR", product.getPrice()));
        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    private void initComponents(@NonNull View view){
        ivProduct = view.findViewById(R.id.iv_image);

        tvTitle = view.findViewById(R.id.tv_title);

        tvBrand = view.findViewById(R.id.tv_brand);

        tvPrice = view.findViewById(R.id.tv_price);

        Button btnGoBack = view.findViewById(R.id.btn_go_back);
        btnGoBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        NavDirections action = ProductDetailFragmentDirections
                .actionProductDetailFragmentToProductListFragment();
        Navigation.findNavController(view).navigate(action);
    }
}