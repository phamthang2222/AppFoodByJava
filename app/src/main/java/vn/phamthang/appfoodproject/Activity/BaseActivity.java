package vn.phamthang.appfoodproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import vn.phamthang.appfoodproject.R;

public class BaseActivity extends AppCompatActivity {
    public static final String FOODS = "Foods";
    public static final String LOCATION = "Location";
    public static final String TIME = "Time";
    public static final String PRICE = "Price";
    public static final String CATEGORY = "Category";
    public static final String USER = "User";
    FirebaseAuth mAuth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();


        getWindow().setStatusBarColor(getResources().getColor(R.color.black));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}