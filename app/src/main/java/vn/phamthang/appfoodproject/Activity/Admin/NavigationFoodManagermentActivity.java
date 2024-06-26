package vn.phamthang.appfoodproject.Activity.Admin;

import android.content.Intent;
import android.os.Bundle;

import vn.phamthang.appfoodproject.Activity.User.BaseActivity;
import vn.phamthang.appfoodproject.databinding.ActivityNavigationFoodManagermentBinding;

public class NavigationFoodManagermentActivity extends BaseActivity {
    ActivityNavigationFoodManagermentBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavigationFoodManagermentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btBack.setOnClickListener(v -> {
            finish();
        });
        binding.ListFoodManagermentMenu.setOnClickListener(v -> {
            startActivity(new Intent(NavigationFoodManagermentActivity.this, FoodManagermentActivity.class));
        });
        binding.BestFoodManagerment.setOnClickListener(v -> {
            startActivity(new Intent(NavigationFoodManagermentActivity.this, OutstandingFoodActivity.class));
        });
    }

}