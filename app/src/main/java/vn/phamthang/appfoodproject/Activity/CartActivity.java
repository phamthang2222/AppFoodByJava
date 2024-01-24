package vn.phamthang.appfoodproject.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import vn.phamthang.appfoodproject.Adapter.CartAdapter;
import vn.phamthang.appfoodproject.Helper.ManagmentCart;
import vn.phamthang.appfoodproject.Interface.ChangeNumberItemsListener;
import vn.phamthang.appfoodproject.R;
import vn.phamthang.appfoodproject.databinding.ActivityCartBinding;

public class CartActivity extends BaseActivity {

    ActivityCartBinding binding;
    private RecyclerView.Adapter adapter;
    private ManagmentCart managmentCart;
    private double tax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        managmentCart = new ManagmentCart(this);
        
        setVariable();
        cacularCart();
        initList();

    }

    private void initList() {
        if(managmentCart.getListCart().isEmpty()){
            binding.tvEmpty.setVisibility(View.VISIBLE);
        }else{
            binding.tvEmpty.setVisibility(View.GONE);
            binding.scrollViewCart.setVisibility(View.VISIBLE);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        binding.rcvCartView.setLayoutManager(linearLayoutManager);
        adapter = new CartAdapter(managmentCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void change() {
                cacularCart();
            }
        });
        binding.rcvCartView.setAdapter(adapter);
    }

    private void cacularCart() {
        double percentTax = 0.02; // 2%
        double delivery  = 10.0; // 10$

        tax = Math.round(managmentCart.getTotalFee()*percentTax*100.0)/100.0;
        double total = Math.round((managmentCart.getTotalFee() + tax + delivery)*100.0)/100.0;
        double itemTotal = Math.round((managmentCart.getTotalFee())*100.0)/100.0;

        binding.tvTotalFree.setText("$"+itemTotal);
        binding.tvDelivery.setText("$"+tax+" ("+percentTax*100+"%)");
        binding.tvTax.setText(percentTax*100+"%");
        binding.tvTotalCart.setText("$"+total);
    }

    private void setVariable() {
        binding.btBack.setOnClickListener(v -> {
            finish();
        });

    }
}