package vn.phamthang.appfoodproject.Activity.Admin;

import static vn.phamthang.appfoodproject.Activity.Admin.StatisticalActivity.listCart;
import static vn.phamthang.appfoodproject.Activity.Admin.StatisticalActivity.listUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.os.Bundle;

import vn.phamthang.appfoodproject.Adapter.ViewPagerAdapter.ChartViewPagerAdapter;
import vn.phamthang.appfoodproject.Fragments.Chart.BarChartFragment;
import vn.phamthang.appfoodproject.Fragments.Chart.PieCharFragment;
import vn.phamthang.appfoodproject.Fragments.OrderCart.FinishedOrderCartFragment;
import vn.phamthang.appfoodproject.Fragments.OrderCart.OrderCartFragment;
import vn.phamthang.appfoodproject.R;
import vn.phamthang.appfoodproject.databinding.ActivityFoodOrderRealTimeBinding;

public class FoodOrderRealTimeActivity extends AppCompatActivity {
    ActivityFoodOrderRealTimeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFoodOrderRealTimeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    private void initView() {
        binding.btBack.setOnClickListener(v -> {
            finish();
        });

        binding.tabLayout.setupWithViewPager(binding.viewPager);
        ChartViewPagerAdapter chartViewPagerAdapter = new ChartViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        chartViewPagerAdapter.addFragment(new OrderCartFragment(this), "Đang chờ");
        chartViewPagerAdapter.addFragment(new FinishedOrderCartFragment(this), "Đã hoàn thành");
        binding.viewPager.setAdapter(chartViewPagerAdapter);
    }
}