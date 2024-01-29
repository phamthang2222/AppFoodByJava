package vn.phamthang.appfoodproject.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import vn.phamthang.appfoodproject.Dialog.DialogConfirm;
import vn.phamthang.appfoodproject.Domain.User;
import vn.phamthang.appfoodproject.R;
import vn.phamthang.appfoodproject.databinding.ActivityProfileBinding;

public class ProfileActivity extends BaseActivity {

    private DialogConfirm dialogConfirm;
    ActivityProfileBinding binding;
    private String idUser="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVariable();
    }

    private void setVariable() {
        dialogConfirm = new DialogConfirm(ProfileActivity.this);

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            DatabaseReference myRef = database.getReference("User");

            // Truy vấn để tìm người dùng với UID phù hợp
            Query query = myRef.orderByChild("id").equalTo(userId);

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        // Lấy giá trị của trường "username" từ nhánh con có UID tương ứng
                        String username = snapshot.child(userId).child("userName").getValue(String.class);
                        String email = snapshot.child(userId).child("email").getValue(String.class);
                        String phoneNumber = snapshot.child(userId).child("numberPhone").getValue(String.class);
                        String address = snapshot.child(userId).child("address").getValue(String.class);
                        idUser = snapshot.child(userId).child("id").getValue(String.class);
                        String passWord = snapshot.child(userId).child("password").getValue(String.class);

                        binding.tvUserName.setText(username);
                        binding.tvEmailUser.setText(email);
                        binding.tvPhoneNumber.setText(phoneNumber);
                        binding.tvAddress.setText(address);
                        binding.tvPassword.setText(passWord);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Xử lý lỗi, nếu có
                }
            });
        }

        binding.btBack.setOnClickListener(v -> {
            dialogConfirm.show();
//            finish();
        });

        binding.btUpdate.setOnClickListener(v -> {
            String email = binding.tvEmailUser.getText().toString().trim();
            String username = binding.tvUserName.getText().toString().trim();
            String phoneNumber = binding.tvPhoneNumber.getText().toString().trim();
            String address = binding.tvAddress.getText().toString().trim();
            String passWord = binding.tvPassword.getText().toString().trim();
            User updateUser = new User(idUser,email,passWord,username,address,phoneNumber);

            if (currentUser != null) {
                String userId = currentUser.getUid();
                DatabaseReference currentUserRef =FirebaseDatabase.getInstance().getReference().child(USER).child(userId);
                currentUserRef.setValue(updateUser)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getApplicationContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                            }
                         })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }

}