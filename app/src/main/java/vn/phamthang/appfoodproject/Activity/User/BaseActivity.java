package vn.phamthang.appfoodproject.Activity.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vn.phamthang.appfoodproject.Objects.Voucher;
import vn.phamthang.appfoodproject.R;

public class BaseActivity extends AppCompatActivity {
    public static final String FOODS = "Foods";
    public static final String LOCATION = "Location";
    public static final String TIME = "Time";
    public static final String PRICE = "Price";
    public static final String CATEGORY = "Category";
    public static final String USER = "User";
    public static final String CART = "Cart";

    public static ArrayList<Voucher> listVoucher = new ArrayList<>();
    public static FirebaseAuth mAuth;
    public static FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        initListVoucher();

        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void initListVoucher() {
        listVoucher.clear();
        DatabaseReference voucherRef = database.getReference("Voucher");
        voucherRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        Voucher voucher = snapshot1.getValue(Voucher.class);
                        listVoucher.add(voucher);
                    }
                    Log.d("LISTVOCHER",listVoucher.toString());
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}