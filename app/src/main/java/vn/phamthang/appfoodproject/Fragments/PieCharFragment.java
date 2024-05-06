package vn.phamthang.appfoodproject.Fragments;

import static vn.phamthang.appfoodproject.Activity.Admin.StatisticalActivity.listCart;
import static vn.phamthang.appfoodproject.Activity.Admin.StatisticalActivity.listUser;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import vn.phamthang.appfoodproject.Objects.Cart;
import vn.phamthang.appfoodproject.Objects.User;
import vn.phamthang.appfoodproject.databinding.FragmentCharBinding;

public class PieCharFragment extends Fragment {
    FragmentCharBinding binding;
    public static ArrayList<PieEntry> statistics = new ArrayList<>();
    public static ArrayList<Cart> newCart = new ArrayList<>();

    public PieCharFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharBinding.inflate(getLayoutInflater(), container, false);
        initPieChart();
        return binding.getRoot();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        statistics.clear();
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
                if (listCart.get(i).getId().equals(newCart.get(j).getId())) {
                    // Tìm thấy phần tử trùng, cập nhật tổng giá tiền
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
            String label = userName(cart.getId());
            int value = Double.valueOf(cart.getTotalPrice()).intValue();
            statistics.add(new PieEntry(value, label));
        }

        PieDataSet pieDataSet = new PieDataSet(statistics, "");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);
        binding.pieChart.setData(pieData);
        binding.pieChart.getDescription().setEnabled(false);
        binding.pieChart.animate();
        binding.pieChart.animateXY(1000,1000);
//        binding.pieChart.invalidate(); // refresh biểu đồ


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
}