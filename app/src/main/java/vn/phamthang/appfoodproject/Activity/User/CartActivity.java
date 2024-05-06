package vn.phamthang.appfoodproject.Activity.User;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;

import vn.phamthang.appfoodproject.Adapter.CartAdapter;
import vn.phamthang.appfoodproject.Objects.Cart;
import vn.phamthang.appfoodproject.Helper.ManagmentCart;
import vn.phamthang.appfoodproject.Interface.ChangeNumberItemsListener;
import vn.phamthang.appfoodproject.Objects.CartNow;
import vn.phamthang.appfoodproject.databinding.ActivityCartBinding;

public class CartActivity extends BaseActivity {

    ActivityCartBinding binding;
    private RecyclerView.Adapter adapter;
    private ManagmentCart managmentCart ;
    private double tax;
    private double total;

    private String idUser="";
    private LocalDate date= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        managmentCart = new ManagmentCart(this);
        
        setVariable();
        setInformationUser();
        calculatorCart();
        initList();


    }

    private void initList() {
        if(managmentCart.getListCart().isEmpty()){
            binding.tvEmpty.setVisibility(View.VISIBLE);
            binding.scrollViewCart.setVisibility(View.GONE);
        }else{
            binding.tvEmpty.setVisibility(View.GONE);
            binding.scrollViewCart.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
            binding.rcvCartView.setLayoutManager(linearLayoutManager);
            adapter = new CartAdapter(managmentCart.getListCart(), this, new ChangeNumberItemsListener() {
                @Override
                public void change() {
                    calculatorCart();
                }
            });
            binding.rcvCartView.setAdapter(adapter);
        }

    }

    private void calculatorCart() {
        double percentTax = 0.02; // 2%
        double delivery  = 10.0; // 10$

        tax = Math.round(managmentCart.getTotalFee()*percentTax*100.0)/100.0;
        double itemTotal = Math.round((managmentCart.getTotalFee())*100.0)/100.0;
        total = Math.round((managmentCart.getTotalFee() + tax + delivery)*100.0)/100.0;

        binding.tvTotalFee.setText("$"+itemTotal);
        binding.tvDelivery.setText("$"+delivery);
        binding.tvTax.setText("$"+tax+" ("+percentTax*100+"%)");
        binding.tvTotalCart.setText("$"+total);
    }

    private void setVariable() {
        binding.btBack.setOnClickListener(v -> {
            finish();
        });
        binding.btEdit.setOnClickListener(v -> {
            startActivity(new Intent(this, ProfileActivity.class));
            finish();
        });
        binding.btnFinish.setOnClickListener(v ->{
            informationCart();
            Intent intent = new Intent(this,DoneActivity.class);
            intent.putExtra("date",date.toString());

            startActivity(intent);
            finish();
        });

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
               date = LocalDate.now();
        }
    }
    private void setInformationUser(){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            DatabaseReference myRef = database.getReference("User");

            // truy vấn để tìm người dùng với UID phù hợp
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
                        binding.tvAddressUser.setText(address);
                        binding.tvUserNumberPhone.setText(phoneNumber);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
    private void informationCart(){

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();

            Cart cart = new Cart(userId, total, date.toString());
            String cartId = database.getReference("Cart").child(userId).push().getKey();
            database.getReference("Cart").child(userId).child(cartId).setValue(cart);

            if (currentUser != null) {
                CartNow cartNow = new CartNow(userId, total,managmentCart.getListCart(), date.toString(),false);
                cartId = database.getReference("Cart").child(userId).push().getKey();
                database.getReference("CartNow").child(userId).child(cartId).setValue(cartNow);
            }
        }


    }
}