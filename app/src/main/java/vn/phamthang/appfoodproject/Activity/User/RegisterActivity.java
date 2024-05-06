package vn.phamthang.appfoodproject.Activity.User;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;

import vn.phamthang.appfoodproject.Objects.User;
import vn.phamthang.appfoodproject.databinding.ActivityRegisterBinding;

public class RegisterActivity extends BaseActivity {

    ActivityRegisterBinding binding;
    public static String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setVariable();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void setVariable() {
        binding.btRegister.setOnClickListener(v -> {
            String email = binding.edtEmail.getText().toString();
            String name = binding.edtUserName.getText().toString();
            String password = binding.edtPassword.getText().toString();
            String password2 = binding.edtPassword2.getText().toString();

            mAuth = FirebaseAuth.getInstance();
            if(email.isEmpty()){
                binding.edtEmail.setError("Không được bỏ trống");
            }else if(name.isEmpty()){
                binding.edtUserName.setError("Không được bỏ trống");
            }else if(password.length()<6){
                Toast.makeText(RegisterActivity.this, "Mật khẩu ít nhất 6 kí tự ", Toast.LENGTH_SHORT).show();
                return;
            } else if(password2.equals(password) == false){
                binding.edtPassword2.setError("Lỗi!");
            }else {
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isComplete()) {
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            // Lấy thông tin người dùng
                            String userId = firebaseUser.getUid();

                            LocalDate currentDate = null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                currentDate = LocalDate.now();
                            }
                            date = currentDate.toString();

                            User newUser = new User(userId, email, password,name,null, null,date);
                            // Lưu thông tin người dùng vào database
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User");
                            databaseReference.child(userId).setValue(newUser);

                            DatabaseReference databaseWishList = FirebaseDatabase.getInstance().getReference("WhisList");
                            databaseWishList.child(userId).setValue("null");

                            DatabaseReference databaseCart = FirebaseDatabase.getInstance().getReference("Cart");
                            databaseCart.child(userId).setValue("null");

                            Toast.makeText(RegisterActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);

                            startActivity(intent);

                            finish();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Đăng kí thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });
        binding.btLogin.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            finish();
        });
    }



}