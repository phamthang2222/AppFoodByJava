package vn.phamthang.appfoodproject.Activity.Admin;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

import vn.phamthang.appfoodproject.Activity.User.BaseActivity;
import vn.phamthang.appfoodproject.Activity.User.LoginActivity;
import vn.phamthang.appfoodproject.databinding.ActivityAdminMainBinding;
import vn.phamthang.appfoodproject.databinding.ActivityMainBinding;

public class AdminMainActivity extends BaseActivity {
    ActivityAdminMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVariable();
    }

    private void setVariable() {
        binding.btLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(AdminMainActivity.this, LoginActivity.class));
            finish();
        });
        binding.UserManagerment.setOnClickListener(v ->{
            startActivity(new Intent(AdminMainActivity.this, UserManagermentActivity.class));
        });
        binding.FoodManagement.setOnClickListener(v -> {
            startActivity(new Intent(AdminMainActivity.this, NavigationFoodManagermentActivity.class));
        });
        binding.statistical.setOnClickListener(v ->{
            startActivity(new Intent(AdminMainActivity.this, StatisticalActivity.class));
        });
    }
}