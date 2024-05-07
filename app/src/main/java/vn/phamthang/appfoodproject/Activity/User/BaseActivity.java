package vn.phamthang.appfoodproject.Activity.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

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

        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }



}