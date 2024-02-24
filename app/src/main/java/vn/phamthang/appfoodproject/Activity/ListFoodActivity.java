package vn.phamthang.appfoodproject.Activity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vn.phamthang.appfoodproject.Adapter.FoodListAdapter;
import vn.phamthang.appfoodproject.Domain.Foods;
import vn.phamthang.appfoodproject.databinding.ActivityListFoodBinding;

public class ListFoodActivity extends BaseActivity {
    ActivityListFoodBinding binding;
    private RecyclerView.Adapter adapter;
    private int categoryId;
    private String categoryName;
    private String searchText;
    private int idLocation,idTime,idPrice;
    private boolean isSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        initList();
    }

    private void initList() {
        DatabaseReference myRef= database.getReference(FOODS);
        binding.progressBar.setVisibility(View.VISIBLE);
        ArrayList<Foods> list = new ArrayList<>();

        Query query;
        if(isSearch){
            query = myRef.orderByChild("Title").startAt(searchText).endAt(searchText +'\uf8ff');
        }else{
            query = myRef.orderByChild("CategoryId").equalTo(categoryId);
        }
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue: snapshot.getChildren()){
                        Foods food = issue.getValue(Foods.class);
                        if(food.getLocationId() == idLocation &&
                        food.getTimeId() == idTime &&
                        food.getPriceId() == idPrice){
                            list.add(food);
                        }
                        if(idLocation == 3 || idPrice ==3 || idTime==3){
                            list.add(food);
                        }
                    }
                    if(list.size()>0){
                        binding.rcvFoodListView.setLayoutManager(new GridLayoutManager(ListFoodActivity.this,2));
                        adapter = new FoodListAdapter(list);
                        binding.rcvFoodListView.setAdapter(adapter);
                    }

                    binding.progressBar.setVisibility(View.GONE);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getIntentExtra() {
        categoryId = getIntent().getIntExtra("CategoryId", 0);
        categoryName = getIntent().getStringExtra("CategoryName");
        searchText = getIntent().getStringExtra("text");
        isSearch = getIntent().getBooleanExtra("isSearch", false);
        idLocation = getIntent().getIntExtra("idLocation",-1);
        idTime = getIntent().getIntExtra("idTime",-1);
        idPrice = getIntent().getIntExtra("idPrice",-1);
        binding.tvTitle.setText(categoryName);
        binding.btBack.setOnClickListener(v -> finish());
        Log.d("idloc, idtime,idprice"," "+idLocation+" "+idTime+" "+idPrice);

    }
}