package vn.phamthang.appfoodproject.Activity.Admin;


import static vn.phamthang.appfoodproject.Activity.Admin.StatisticalActivity.listCart;
import static vn.phamthang.appfoodproject.Activity.Admin.StatisticalActivity.listCartNow;
import static vn.phamthang.appfoodproject.Activity.Admin.StatisticalActivity.listUser;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentStatePagerAdapter;


import vn.phamthang.appfoodproject.Activity.User.BaseActivity;
import vn.phamthang.appfoodproject.Adapter.ViewPagerAdapter.ChartViewPagerAdapter;
import vn.phamthang.appfoodproject.Fragments.Chart.PieCharFragment;
import vn.phamthang.appfoodproject.Fragments.Chart.BarChartFragment;
import vn.phamthang.appfoodproject.databinding.ActivityOverallStatisticsBinding;

public class OverallStatisticsActivity extends BaseActivity {
    ActivityOverallStatisticsBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOverallStatisticsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    private void initView() {
        binding.btBack.setOnClickListener(v -> {
            listCart.clear();
            listUser.clear();
            listCartNow.clear();
            startActivity(new Intent(this,StatisticalActivity.class));
            finish();
        });

        binding.tabLayout.setupWithViewPager(binding.s1);
        ChartViewPagerAdapter chartViewPagerAdapter = new ChartViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        chartViewPagerAdapter.addFragment(new PieCharFragment(), "Doanh thu theo người mua");
        chartViewPagerAdapter.addFragment(new BarChartFragment(), "Doanh thu theo tháng");
        binding.s1.setAdapter(chartViewPagerAdapter);


    }


}