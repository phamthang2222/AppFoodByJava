package vn.phamthang.appfoodproject.Activity.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import vn.phamthang.appfoodproject.Activity.User.BaseActivity;
import vn.phamthang.appfoodproject.Adapter.Admin.VoucherAdapter;
import vn.phamthang.appfoodproject.Objects.Voucher;
import vn.phamthang.appfoodproject.R;
import vn.phamthang.appfoodproject.databinding.ActivityManagermentVoucherBinding;

public class ManagementVoucherActivity extends BaseActivity {

    ActivityManagermentVoucherBinding binding;
    private RecyclerView.Adapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityManagermentVoucherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
        initListVoucher();
//        initData();
    }

    private void initData() {
        mAdapter = new VoucherAdapter(listVoucher);
        binding.rcvVoucher.setAdapter(mAdapter);
        binding.rcvVoucher.setLayoutManager(new GridLayoutManager(ManagementVoucherActivity.this,1));

    }

    private void initView() {
        binding.btBack.setOnClickListener(v -> {
            finish();
        });
        binding.addVoucher.setOnClickListener(v -> {
            startActivity(new Intent(this, AddVoucherActivity.class));
            finish();

        });
    }

    private void initListVoucher() {
        binding.progressBar3.setVisibility(View.VISIBLE);
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
                initData();
                binding.progressBar3.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}