package vn.phamthang.appfoodproject.Activity.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import vn.phamthang.appfoodproject.Helper.ManagmentCart;
import vn.phamthang.appfoodproject.Helper.TinyDB;
import vn.phamthang.appfoodproject.databinding.ActivityDoneBinding;

public class DoneActivity extends AppCompatActivity {
    ActivityDoneBinding binding;
    private ManagmentCart managmentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDoneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        managmentCart = new ManagmentCart(getApplicationContext());
//        managmentCart.clearCart();
        managmentCart.clearList("CartList");

        setVariable();
    }

    private void setVariable() {
        Intent intent = getIntent();
        String date = intent.getStringExtra("date");

        binding.tvNotification.setText("Đơn hàng "+date);

        binding.btback.setOnClickListener(v -> {
            startActivity(new Intent(DoneActivity.this,MainActivity.class));
            finish();
        });
    }
}