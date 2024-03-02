package vn.phamthang.appfoodproject.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import vn.phamthang.appfoodproject.Dialog.DialogConfirm;
import vn.phamthang.appfoodproject.R;
import vn.phamthang.appfoodproject.databinding.ActivityIntroBinding;

public class IntroActivity extends BaseActivity {

    ActivityIntroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding =ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setStatusBarColor(Color.parseColor("#FFE4B5"));
        setVaribale();
    }

    private void setVaribale() {

        binding.regisBtn.setOnClickListener(v -> {
            startActivity(new Intent(IntroActivity.this, RegisterActivity.class));
            finish();
            }
        );
        binding.loginBtn.setOnClickListener(v -> {
            if(mAuth.getCurrentUser() != null){
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
                finish();
            }else{
                startActivity(new Intent(IntroActivity.this, LoginActivity.class));
                finish();

            }
        });
    }


}