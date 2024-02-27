package vn.phamthang.appfoodproject.Activity.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import vn.phamthang.appfoodproject.Activity.BaseActivity;
import vn.phamthang.appfoodproject.Activity.LoginActivity;
import vn.phamthang.appfoodproject.databinding.ActivityAdminMainBinding;

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
            Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AdminMainActivity.this, UserManagermentActivity.class));
        });
    }
}