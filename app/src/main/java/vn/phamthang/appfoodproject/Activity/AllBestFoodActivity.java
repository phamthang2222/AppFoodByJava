package vn.phamthang.appfoodproject.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vn.phamthang.appfoodproject.Adapter.BestFoodAdapter;
import vn.phamthang.appfoodproject.Adapter.FoodListAdapter;
import vn.phamthang.appfoodproject.Domain.Foods;
import vn.phamthang.appfoodproject.R;
import vn.phamthang.appfoodproject.databinding.ActivityAllBestFoodBinding;

public class AllBestFoodActivity extends BaseActivity {
    ActivityAllBestFoodBinding binding;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllBestFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btBack.setOnClickListener(v -> {
            finish();
        });
        initBestFood();
    }

    private void initBestFood() {
        DatabaseReference myRef = database.getReference(FOODS);
        binding.progressBar.setVisibility(View.VISIBLE);
        ArrayList<Foods> list = new ArrayList<>();
        //câu lệnh truy vấn để lấy ra các giá trị true của bestFood
        Query query =myRef.orderByChild("BestFood").equalTo(true);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue: snapshot.getChildren()){
                        list.add(issue.getValue(Foods.class));
                    }
                    if(list.size()>0){
                        binding.rcvBestFood.setLayoutManager(new GridLayoutManager(AllBestFoodActivity.this,2));
                        adapter = new FoodListAdapter(list) ;
                        binding.rcvBestFood.setAdapter(adapter);
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