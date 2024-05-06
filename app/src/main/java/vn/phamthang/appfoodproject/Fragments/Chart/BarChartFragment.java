package vn.phamthang.appfoodproject.Fragments.Chart;

import static vn.phamthang.appfoodproject.Activity.Admin.StatisticalActivity.listCart;
import static vn.phamthang.appfoodproject.Activity.Admin.StatisticalActivity.listUser;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import vn.phamthang.appfoodproject.Objects.Cart;
import vn.phamthang.appfoodproject.Objects.User;
import vn.phamthang.appfoodproject.databinding.FragmentLineBinding;


public class BarChartFragment extends Fragment {
    FragmentLineBinding binding;
    public static ArrayList<BarEntry> barEntries = new ArrayList<>();
    public static ArrayList<Cart> newCart = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLineBinding.inflate(getLayoutInflater(), container, false);
        initPieChart();
        return binding.getRoot();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        barEntries.clear();
        newCart.clear();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private void initPieChart() {

        newCart.add(listCart.get(0));

        for (int i = 1; i < listCart.size(); i++) {
            boolean found = false;
            for (int j = 0; j < newCart.size(); j++) {
                if (getMonth(listCart.get(i).getDate()) == getMonth(newCart.get(j).getDate())) {
                    Double price = listCart.get(i).getTotalPrice();
                    newCart.get(j).setTotalPrice(newCart.get(j).getTotalPrice() + price);
                    found = true;
                    break;
                }
            }
            if (!found) {
                // Không tìm thấy phần tử trùng, thêm vào newCart
                newCart.add(listCart.get(i));
            }
        }
        for (Cart cart : newCart) {
            int month = getMonth(cart.getDate());
            float value = Double.valueOf(cart.getTotalPrice()).intValue();
            barEntries.add(new BarEntry((float)month ,value,"Tháng"+month));
        }

        BarDataSet barDataSet = new BarDataSet(barEntries,"Doanh thu theo tháng");
        barDataSet.setColors(Color.BLUE);
        barDataSet.setValueTextColor(Color.BLACK);

        barDataSet.setValueTextSize(16f);


        BarData barData = new BarData(barDataSet);

        ValueFormatter xAxisFormatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return "Tháng "+(int)value;
            }
        };

        XAxis xAxis = binding.barChart.getXAxis();
        xAxis.setValueFormatter(xAxisFormatter);
        binding.barChart.setFitBars(true);
        binding.barChart.setData(barData);
        binding.barChart.getDescription().setEnabled(true);
        binding.barChart.animate();
        binding.barChart.setDescription(null);
        binding.barChart.animateY(1000);
        binding.barChart.invalidate(); // refresh biểu đồ



    }

    private String userName(String idUser) {
        String username = "";
        for (User value : listUser) {
            if (idUser.equals(value.getId())) {
                username = value.getUserName();
                break;
            }
        }
        return username;
    }
    private int getMonth(String time){
        DateTimeFormatter formatter = null;
        LocalDate date = null;
        int month = -1;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(time, formatter);
            month = date.getMonthValue();
        }
        return  month;
    }
}