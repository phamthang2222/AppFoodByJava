package vn.phamthang.appfoodproject.Activity.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vn.phamthang.appfoodproject.Activity.AllBestFoodActivity;
import vn.phamthang.appfoodproject.Activity.BaseActivity;
import vn.phamthang.appfoodproject.Adapter.Admin.UserAdapter;
import vn.phamthang.appfoodproject.Domain.User;
import vn.phamthang.appfoodproject.databinding.ActivityUserManagermentBinding;


public class UserManagermentActivity extends BaseActivity {
    ActivityUserManagermentBinding binding;
    private RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserManagermentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVariable();
        initListUser();
    }

    private void initListUser() {
        DatabaseReference myRef = database.getInstance().getReference(USER);
        binding.progressBar.setVisibility(View.VISIBLE);
        ArrayList<User> listUser = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot userSnapshot: snapshot.getChildren()){
                        User user = userSnapshot.getValue(User.class);
                        listUser.add(user);
                    }

                    if(listUser.size()>0){
                        binding.rcvUser.setLayoutManager(new GridLayoutManager(UserManagermentActivity.this,1));
                        adapter = new UserAdapter(listUser);
                        binding.rcvUser.setAdapter(adapter);
                    }
                    binding.progressBar.setVisibility(View.GONE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setVariable() {
        binding.btBack.setOnClickListener(v -> {
            finish();
        });
    }
}