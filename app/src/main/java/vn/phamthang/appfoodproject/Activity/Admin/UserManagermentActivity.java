package vn.phamthang.appfoodproject.Activity.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import vn.phamthang.appfoodproject.databinding.ActivityUserManagermentBinding;

public class UserManagermentActivity extends AppCompatActivity {
    ActivityUserManagermentBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserManagermentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVariable();
    }

    private void setVariable() {
        binding.btBack.setOnClickListener(v -> {
            finish();
        });
    }
}