package vn.phamthang.appfoodproject.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vn.phamthang.appfoodproject.Adapter.BestFoodAdapter;
import vn.phamthang.appfoodproject.Adapter.CategoryAdapter;
import vn.phamthang.appfoodproject.Dialog.DialogConfirm;
import vn.phamthang.appfoodproject.Domain.Category;
import vn.phamthang.appfoodproject.Domain.Foods;
import vn.phamthang.appfoodproject.Domain.Location;
import vn.phamthang.appfoodproject.Domain.Price;
import vn.phamthang.appfoodproject.Domain.Time;
import vn.phamthang.appfoodproject.Domain.User;
import vn.phamthang.appfoodproject.R;
import vn.phamthang.appfoodproject.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;
    private int idLocation,idTime,idPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initUserNameCurent();
        initLocation();
        initTime();
        initPrice();
        initBestFood();
        initCategory();
        setVariable();

    }

    private void setVariable() {;
        binding.btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
            }
        });

        binding.btnSearch.setOnClickListener(v -> {
            String findText = binding.edtFind.getText().toString();
            if(!findText.isEmpty()){
                Intent intent = new Intent(MainActivity.this, ListFoodActivity.class);
                intent.putExtra("text",findText);
                intent.putExtra("isSearch", true);
                intent.putExtra("idLocation",idLocation);
                intent.putExtra("idTime",idTime);
                intent.putExtra("idPrice",idPrice);
                startActivity(intent);
            }
        });
        binding.btnCart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,CartActivity.class);
            startActivity(intent);
        });
        binding.imgAvtUser.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
            startActivity(intent);
            finish();
        });
        binding.btViewAll.setOnClickListener(v ->{
            startActivity(new Intent(this,AllBestFoodActivity.class));
        } );
        binding.btWishList.setOnClickListener(v -> {
            startActivity(new Intent(this,WishListActivity.class));
        });


    }

    private void initBestFood() {
        DatabaseReference myRef = database.getReference(FOODS);
        binding.progressBarBestFood.setVisibility(View.VISIBLE);
        ArrayList<Foods> list = new ArrayList<>();
        //câu lệnh truy vấn để lấy ra các giá trị true của bestFood
        Query query = myRef.orderByChild("BestFood").equalTo(true);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue: snapshot.getChildren()){
                        list.add(issue.getValue(Foods.class));
                    }
                    if(list.size()>0){
                        binding.rcvBestFood.setLayoutManager(new LinearLayoutManager(MainActivity.this,
                                LinearLayoutManager.HORIZONTAL,false));
                        RecyclerView.Adapter adapter = new BestFoodAdapter(list);
                        binding.rcvBestFood.setAdapter(adapter);
                    }
                    binding.progressBarBestFood.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void initCategory() {
        DatabaseReference myRef = database.getReference(CATEGORY);
        binding.progressBar2.setVisibility(View.VISIBLE);
        ArrayList<Category> list = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue: snapshot.getChildren()){
                        list.add(issue.getValue(Category.class));
                    }
                    if(list.size()>0){
                        binding.rcvCategoryFood.setLayoutManager(new GridLayoutManager(MainActivity.this,4));
                        RecyclerView.Adapter adapter = new CategoryAdapter(list);
                        binding.rcvCategoryFood.setAdapter(adapter);
                    }
                    binding.progressBar2.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initLocation() {
        DatabaseReference myRef = database.getReference(LOCATION);
        ArrayList<Location> list = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Location.class));
                    }
                    ArrayAdapter<Location> adapter =new ArrayAdapter<>(MainActivity.this,R.layout.sp_item,list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.locationSp.setAdapter(adapter);
                    binding.locationSp.setSelection(3);
                    binding.locationSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                            Location selectedLoc = (Location) parentView.getSelectedItem();
                            idLocation = selectedLoc.getId();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parentView) {
                        }
                    });

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void initTime() {
        DatabaseReference myRef = database.getReference(TIME);
        ArrayList<Time> list = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Time.class));
                    }
                    ArrayAdapter<Time> adapter =new ArrayAdapter<>(MainActivity.this,R.layout.sp_item,list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.timeSp.setAdapter(adapter);
                    binding.timeSp.setSelection(3);
                    binding.timeSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                            Time selectedTime = (Time) parentView.getSelectedItem();
                            idTime = selectedTime.getId();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parentView) {
                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void initPrice() {
        DatabaseReference myRef = database.getReference(PRICE);
        ArrayList<Price> list = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Price.class));
                    }
                    ArrayAdapter<Price> adapter =new ArrayAdapter<>(MainActivity.this,R.layout.sp_item,list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.priceSp.setAdapter(adapter);
                    binding.priceSp.setSelection(3);
                    binding.priceSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                            Price selectedPrice = (Price) parentView.getSelectedItem();
                            idPrice = selectedPrice.getId();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parentView) {
                        }
                    });

                }else{

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void initUserNameCurent(){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            DatabaseReference myRef = database.getReference("User");
            Query query = myRef.orderByChild("id").equalTo(userId);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String username = snapshot.child(userId).child("userName").getValue(String.class);
                       binding.tvUserName.setText(username);
                       Log.d("TAG","onCreate: "+username);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }

    }
}