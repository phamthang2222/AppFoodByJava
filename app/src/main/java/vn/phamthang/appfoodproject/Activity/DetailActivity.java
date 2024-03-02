package vn.phamthang.appfoodproject.Activity;

import android.os.Bundle;
import com.bumptech.glide.Glide;

import vn.phamthang.appfoodproject.Domain.Foods;
import vn.phamthang.appfoodproject.Helper.ManagmentCart;
import vn.phamthang.appfoodproject.Helper.ManagmentWhisList;
import vn.phamthang.appfoodproject.R;
import vn.phamthang.appfoodproject.databinding.ActivityDetailBinding;

public class DetailActivity extends BaseActivity {
    ActivityDetailBinding binding;
    private Foods object;
    private int num = 1;
    private ManagmentCart managmentCart;
    private ManagmentWhisList managmentWhisList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(getResources().getColor(R.color.black));

        getIntentExtrax();
        getVariable();
    }

    private void getVariable() {

        managmentCart = new ManagmentCart(this);
        managmentWhisList = new ManagmentWhisList(this);
        binding.btBack.setOnClickListener(v -> finish());

        Glide.with(DetailActivity.this)
                .load(object.getImagePath())
                .into(binding.imageDetailFood);
        binding.tvPriceInDetailFood.setText("$"+object.getPrice());
        binding.tvTitileInDetailFood.setText(object.getTitle());
        binding.tvDescrip.setText(object.getDescription());
        binding.tvRating.setText("Đánh giá: "+object.getStar());
        binding.tvTimeInDetailFood.setText(object.getTimeValue()+" phút");
        binding.ratingBar.setRating((float)object.getStar());
        binding.tvTotalPrice.setText(num * object.getPrice()+"$");

        binding.btPlus.setOnClickListener(v -> {
            num += 1;
            binding.tvQuantity.setText(num+"");
            binding.tvTotalPrice.setText("$"+num*object.getPrice());

        });

        binding.btMinus.setOnClickListener(v -> {
            if(num > 1){
                num -= 1;
                binding.tvQuantity.setText(num+"");
                binding.tvTotalPrice.setText("$"+num*object.getPrice());
            }
        });

        binding.btAddToCart.setOnClickListener(v -> {
            object.setNumberInCart(num);
            managmentCart.insertFood(object);

        });
        binding.btAddToWhisList.setOnClickListener(v->{
            managmentWhisList.insertFood(object);
        });
    }
    private void getIntentExtrax() {
        object = (Foods) getIntent().getSerializableExtra("object");
        if(managmentCart != null){
            managmentCart.insertFood(object);
        }
        if(managmentWhisList != null){
            managmentWhisList.insertFood(object);
        }
    }

}