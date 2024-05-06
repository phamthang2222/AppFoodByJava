package vn.phamthang.appfoodproject.Activity.User;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import vn.phamthang.appfoodproject.Adapter.WhisListAdapter;
import vn.phamthang.appfoodproject.Helper.ManagmentWhisList;
import vn.phamthang.appfoodproject.Interface.RemoveItemFromList;
import vn.phamthang.appfoodproject.databinding.ActivityWishListBinding;

public class WishListActivity extends BaseActivity {
    ActivityWishListBinding binding;
    private RecyclerView.Adapter adapter;
    private ManagmentWhisList managmentWhisList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWishListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        managmentWhisList = new ManagmentWhisList(this);

        setVariable();
        initList();
    }

    private void initList() {
        if(managmentWhisList.getList().isEmpty()){
            binding.tvEmpty.setVisibility(View.VISIBLE);
        }else{
            binding.tvEmpty.setVisibility(View.GONE);
            binding.scrollViewWL.setVisibility(View.VISIBLE);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        binding.rcvWishList.setLayoutManager(linearLayoutManager);
        adapter = new WhisListAdapter(managmentWhisList.getList(), this, new RemoveItemFromList() {
            @Override
            public void remove() {

            }
        });
        binding.rcvWishList.setAdapter(adapter);
    }

    private void setVariable() {
        binding.btBack.setOnClickListener(v -> {
            finish();
        });
    }

}