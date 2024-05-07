package vn.phamthang.appfoodproject.Activity.Admin;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vn.phamthang.appfoodproject.Activity.User.BaseActivity;
import vn.phamthang.appfoodproject.Objects.Cart;
import vn.phamthang.appfoodproject.Objects.CartNow;
import vn.phamthang.appfoodproject.Objects.User;
import vn.phamthang.appfoodproject.databinding.ActivityStatisticalBinding;

public class StatisticalActivity extends BaseActivity {
    ActivityStatisticalBinding binding;
    public static ArrayList<User> listUser = new ArrayList<>();
    public static ArrayList<Cart> listCart = new ArrayList<>();
    public static ArrayList<CartNow> listCartNow = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStatisticalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initData();
        setVariable();
    }

    private void setVariable() {
        binding.btBack.setOnClickListener(v -> {
            finish();
        });
        binding.statisticsOverview.setOnClickListener(v ->{
            startActivity(new Intent(this,OverallStatisticsActivity.class));
        });
        binding.foodOrderNow.setOnClickListener(v ->{
            startActivity(new Intent(this,FoodOrderRealTimeActivity.class));
        });
    }
    private void initData() {
        DatabaseReference myRef = database.getInstance().getReference(USER);
        binding.progressBar4.setVisibility(View.VISIBLE);
        binding.statisticsOverview.setVisibility(View.INVISIBLE);
        binding.foodOrderNow.setVisibility(View.INVISIBLE);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        User user = userSnapshot.getValue(User.class);
                        listUser.add(user);
                    }
                }
                //lấy về all list cart
                DatabaseReference cartRef = database.getReference(CART);
                cartRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                            String userId = userSnapshot.getKey(); // Lấy ID của người dùng
                            for (DataSnapshot cartSnapshot : userSnapshot.getChildren()) {
                                Cart cart = cartSnapshot.getValue(Cart.class);
                                // Xử lý đối tượng cart nhận được ở đây
                                if (cart != null) {
                                    listCart.add(cart);
                                }
                            }
                        }
//                        Toast.makeText(StatisticalActivity.this, "Thanh cong", Toast.LENGTH_SHORT).show();

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Xử lý lỗi nếu có
                    }
                });


                //lấy về list cart now
                DatabaseReference cartNowRef = database.getReference("CartNow");
                cartNowRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                            String userId = userSnapshot.getKey(); // Lấy ID của người dùng
                            for (DataSnapshot cartSnapshot : userSnapshot.getChildren()) {
                                CartNow cartNow = cartSnapshot.getValue(CartNow.class);
                                // Xử lý đối tượng cart nhận được ở đây
                                if (cartNow != null) {
                                    listCartNow.add(cartNow);
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                binding.progressBar4.setVisibility(View.GONE);
                binding.statisticsOverview.setVisibility(View.VISIBLE);
                binding.foodOrderNow.setVisibility(View.VISIBLE);
                Log.d("USER_CREATE:", listUser.toString() + "");
                Log.d("USER_CREATE:", listCart.toString() + "");
                Log.d("USER_CREATE:", listCartNow.toString() + "");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}