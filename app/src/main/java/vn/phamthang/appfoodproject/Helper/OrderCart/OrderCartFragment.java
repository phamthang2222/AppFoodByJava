package vn.phamthang.appfoodproject.Helper.OrderCart;

import static vn.phamthang.appfoodproject.Activity.Admin.StatisticalActivity.listCartNow;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import vn.phamthang.appfoodproject.Adapter.Admin.OrderCartAdapter;
import vn.phamthang.appfoodproject.Objects.CartNow;
import vn.phamthang.appfoodproject.R;
import vn.phamthang.appfoodproject.databinding.FragmentOrderCartBinding;

public class OrderCartFragment extends Fragment {

    private OrderCartAdapter mAdapter;
    private Context context;
    public static ArrayList<CartNow> mListCartNow = new ArrayList<>();
    FragmentOrderCartBinding binding;

    public OrderCartFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderCartBinding.inflate(getLayoutInflater());
        initData();

        return binding.getRoot();
    }


    private void initData() {
        binding.rcvOrderCart.setLayoutManager(new GridLayoutManager(context, 1));

        if (listCartNow != null) {
            for (CartNow value : listCartNow) {
                if (value.isFinish() == false) {
                    if (isCheckNow(value.getDate(), getOrderToday())) {
                        mListCartNow.add(value);

                    }
                }
            }
        }

        mAdapter = new OrderCartAdapter(mListCartNow);
        binding.rcvOrderCart.setAdapter(mAdapter);

    }

    private int getOrderToday() {
        LocalDate currentDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            currentDate = LocalDate.now();
        }
        int day = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            day = currentDate != null ? currentDate.getDayOfMonth() : -1;
        }
        return day;
    }

    private boolean isCheckNow(String dateStringOfCart, int dayNow) {
        LocalDate date = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            date = LocalDate.parse(dateStringOfCart, DateTimeFormatter.ISO_LOCAL_DATE);
            int day = date.getDayOfMonth();
            if (day == dayNow) {
                return true;
            } else {
                return false;
            }
        }
        return false;

    }
}