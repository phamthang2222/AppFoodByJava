package vn.phamthang.appfoodproject.Activity.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vn.phamthang.appfoodproject.Activity.BaseActivity;
import vn.phamthang.appfoodproject.Activity.ListFoodActivity;
import vn.phamthang.appfoodproject.Adapter.Admin.FoodManagermentAdapter;
import vn.phamthang.appfoodproject.Adapter.FoodListAdapter;
import vn.phamthang.appfoodproject.Domain.Foods;
import vn.phamthang.appfoodproject.R;
import vn.phamthang.appfoodproject.databinding.ActivityFoodManagermentBinding;

public class FoodManagermentActivity extends BaseActivity {
    ActivityFoodManagermentBinding binding;
    private RecyclerView.Adapter adapter;
    private String searchText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFoodManagermentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVariable();
        initListFood();
    }

    private void setVariable() {
        binding.btBack.setOnClickListener(v -> {
            finish();
        });
        binding.btnSearch.setOnClickListener(v -> {
            initFoodByFind();
            Handler handler = new Handler();
            handler.postDelayed(() -> binding.progressBar.setVisibility(View.GONE), 1000);
        });
    }

    private void initListFood() {
        DatabaseReference myRef = database.getInstance().getReference(FOODS);
        binding.progressBar.setVisibility(View.VISIBLE);
        ArrayList<Foods> listFoods = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot item: snapshot.getChildren()){
                        Foods food = item.getValue(Foods.class);
                        listFoods.add(food);
                    }
                }
                if(listFoods.size() > 0){
                    binding.rcvFoods.setLayoutManager(new GridLayoutManager(FoodManagermentActivity.this,1));
                    adapter = new FoodManagermentAdapter(listFoods);
                    binding.rcvFoods.setAdapter(adapter);
                }
                binding.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void initFoodByFind() {
        DatabaseReference myRef = database.getInstance().getReference(FOODS);
        Query query;
        binding.progressBar.setVisibility(View.VISIBLE);
        if(binding.edtFind.toString().isEmpty()){

        }else {
            ArrayList<Foods> listFoods2 = new ArrayList<>();
            searchText = binding.edtFind.getText().toString();
            query = myRef.orderByChild("Title").startAt(searchText).endAt(searchText +'\uf8ff');
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        for(DataSnapshot issue: snapshot.getChildren()){
                            Foods food = issue.getValue(Foods.class);
                            listFoods2.add(food);
                        }
                        if(listFoods2.size() > 0){
                            binding.rcvFoods.setLayoutManager(new GridLayoutManager(FoodManagermentActivity.this,1));
                            adapter = new FoodManagermentAdapter(listFoods2);
                            binding.rcvFoods.setAdapter(adapter);
                        }
                        binding.progressBar.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }
}