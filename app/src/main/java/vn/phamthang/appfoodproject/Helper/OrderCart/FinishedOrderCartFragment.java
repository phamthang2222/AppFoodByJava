package vn.phamthang.appfoodproject.Helper.OrderCart;

import static vn.phamthang.appfoodproject.Activity.Admin.StatisticalActivity.listCartNow;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import vn.phamthang.appfoodproject.Adapter.Admin.FinishOrderCartAdapter;
import vn.phamthang.appfoodproject.Objects.CartNow;
import vn.phamthang.appfoodproject.databinding.FragmentFinishedOrderCartBinding;


public class FinishedOrderCartFragment extends Fragment {

    private RecyclerView.Adapter mAdapter;
    public ArrayList<CartNow> mListCartNowFinished = new ArrayList<>();
    private Context context;
    FragmentFinishedOrderCartBinding binding;
    private static final String TAG = "FinishedOrderCartFragme";

    public FinishedOrderCartFragment ( Context context){
        this.context = context;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        Log.d(TAG, "onStart: ");

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
//        EventBus.getDefault().register(this);

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
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
//        if(listCartNow!= null){
//            for(CartNow value : listCartNow){
//                if(value.isFinish() == true){
//                    mListCartNowFinished.add(value);
//                }
//            }
//        }
        mAdapter = new FinishOrderCartAdapter(mListCartNowFinished);
        binding.rcvFinishedOrderCart.setAdapter(mAdapter);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onItemClickEvent(ItemClickEvent event) {
        CartNow data = event.data;
        mListCartNowFinished.add(data);
        mAdapter = new FinishOrderCartAdapter(mListCartNowFinished);
        binding.rcvFinishedOrderCart.setAdapter(mAdapter);
    }
}