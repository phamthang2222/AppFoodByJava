package vn.phamthang.appfoodproject.Fragments.OrderCart;

import static vn.phamthang.appfoodproject.Activity.Admin.StatisticalActivity.listCartNow;
import static vn.phamthang.appfoodproject.Fragments.OrderCart.OrderCartFragment.mListCartNow;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;

import vn.phamthang.appfoodproject.Adapter.Admin.FinishOrderCartAdapter;
import vn.phamthang.appfoodproject.Objects.CartNow;
import vn.phamthang.appfoodproject.databinding.FragmentFinishedOrderCartBinding;


public class FinishedOrderCartFragment extends Fragment {

    private RecyclerView.Adapter mAdapter;
    public ArrayList<CartNow> mListCartNowFinished = new ArrayList<>();

    private Context context;
    FragmentFinishedOrderCartBinding binding;

    public FinishedOrderCartFragment ( Context context){
        this.context = context;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFinishedOrderCartBinding.inflate(getLayoutInflater());
        initData();
        return binding.getRoot();
    }
    private void initData() {
        binding.rcvFinishedOrderCart.setLayoutManager(new GridLayoutManager(context, 1));
        if(listCartNow!= null){
            for(CartNow value : listCartNow){
                if(value.isFinish() == true){
                    mListCartNowFinished.add(value);
                }
            }
        }
        mAdapter = new FinishOrderCartAdapter(mListCartNowFinished);
        binding.rcvFinishedOrderCart.setAdapter(mAdapter);
    }
}