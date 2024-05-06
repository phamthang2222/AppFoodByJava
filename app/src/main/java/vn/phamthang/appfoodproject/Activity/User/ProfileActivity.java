package vn.phamthang.appfoodproject.Activity.User;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import vn.phamthang.appfoodproject.Dialog.DialogConfirm;
import vn.phamthang.appfoodproject.Objects.User;
import vn.phamthang.appfoodproject.databinding.ActivityProfileBinding;

public class ProfileActivity extends BaseActivity {

    private DialogConfirm dialogConfirm;
    ActivityProfileBinding binding;
    private String idUser="";
    private String dateCreated ="";

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
                        dateCreated = snapshot.child(userId).child("dateCreated").getValue(String.class);

                        binding.tvUserName4124.setText(username);
                        binding.tvEmailUser.setText(email);
                        binding.tvPhoneNumber.setText(phoneNumber);
                        binding.tvAddress.setText(address);
                        binding.tvPassword.setText(passWord);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }

        binding.btBack.setOnClickListener(v -> {
            dialogConfirm.show();
//            finish();
        });

        binding.btUpdate.setOnClickListener(v -> {
            String email = binding.tvEmailUser.getText().toString().trim();
            String username = binding.tvUserName4124.getText().toString().trim();
            String phoneNumber = binding.tvPhoneNumber.getText().toString().trim();
            String address = binding.tvAddress.getText().toString().trim();
            String passWord = binding.tvPassword.getText().toString().trim();
            User updateUser = new User(idUser,email,passWord,username,address,phoneNumber,dateCreated);

            if (currentUser != null) {
                String userId = currentUser.getUid();
                DatabaseReference currentUserRef =FirebaseDatabase.getInstance().getReference().child(USER).child(userId);
                currentUserRef.setValue(updateUser)
                        .addOnSuccessListener(unused ->
                                Toast.makeText(getApplicationContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show())
                        .addOnFailureListener(e ->
                                Toast.makeText(getApplicationContext(), "Cập nhật thất bại", Toast.LENGTH_SHORT).show());
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        dialogConfirm.show();
    }
}