package vn.phamthang.appfoodproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import vn.phamthang.appfoodproject.Activity.Admin.AdminMainActivity;
import vn.phamthang.appfoodproject.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity {
    ActivityLoginBinding binding;
    private String TKadmin = "admin@food.vn";
    private String MKadmin = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVariable();
    }
    private void setVariable() {
        binding.edtEmail.setText(TKadmin);
        binding.edtPassword.setText(MKadmin);

        binding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.edtEmail.getText().toString();
                String password = binding.edtPassword.getText().toString();
                if(email.isEmpty()){
                    binding.edtEmail.setError("Không được để trống!");
                }else if(password.isEmpty()){
                    binding.edtPassword.setError("Không được để trống!");
                }else{
                    binding.progressBarLogin.setVisibility(View.VISIBLE);
                    signIn(email,password);
                }

            }
        });
        binding.btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        binding.btFbLogin.setOnClickListener(v -> {
            Toast.makeText(this, "Chưa hỗ trợ", Toast.LENGTH_SHORT).show();
        });
        binding.btGgLogin.setOnClickListener(v -> {
            Toast.makeText(this, "Chưa hỗ trợ", Toast.LENGTH_SHORT).show();
        });
        binding.btTwLogin.setOnClickListener(v -> {
            Toast.makeText(this, "Chưa hỗ trợ", Toast.LENGTH_SHORT).show();
        });
    }
    private void  signIn(String userName,String passWord ){
        mAuth.signInWithEmailAndPassword(userName,passWord)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        if(userName.equals("admin@food.vn")){
                            binding.progressBarLogin.setVisibility(View.INVISIBLE);
                            startActivity(new Intent(LoginActivity.this, AdminMainActivity.class));
                            finish();
                            Toast.makeText(LoginActivity.this, "Đăng nhập với vai trò admin", Toast.LENGTH_SHORT).show();
                        }else{
                            binding.progressBarLogin.setVisibility(View.INVISIBLE);
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        binding.progressBarLogin.setVisibility(View.INVISIBLE);
                        Toast.makeText(LoginActivity.this, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();

                    }
                });

    }
}