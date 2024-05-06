package vn.phamthang.appfoodproject.Adapter.Admin;

import static vn.phamthang.appfoodproject.Activity.Admin.StatisticalActivity.listUser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.phamthang.appfoodproject.Objects.CartNow;
import vn.phamthang.appfoodproject.Objects.User;
import vn.phamthang.appfoodproject.R;

public class OrderCartAdapter extends RecyclerView.Adapter<OrderCartAdapter.viewHolder> {

    Context context;
    private ArrayList<CartNow> mListCartNow;

    public OrderCartAdapter(ArrayList<CartNow> mListCartNow) {
        this.mListCartNow = mListCartNow;
    }

    @NonNull
    @Override
    public OrderCartAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_item_in_ordercart, parent, false);
        return new viewHolder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderCartAdapter.viewHolder holder, int position) {

        ItemOrderInCartAdapter itemOrderInCartAdapter = new ItemOrderInCartAdapter(mListCartNow.get(position).getFoodsList());

        holder.tvUserName.setText(userName(mListCartNow.get(position).getId()));
        holder.tvUserAddress.setText(userAddress(mListCartNow.get(position).getId()));
        holder.tvTime.setText(mListCartNow.get(position).getDate());
        holder.tvCartPrice.setText(mListCartNow.get(position).getTotalPrice() + "$");
        holder.rcv.setLayoutManager(new GridLayoutManager(context, 1));
        holder.rcv.setAdapter(itemOrderInCartAdapter);
        holder.btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListCartNow.get(position).setFinish(true);
                Toast.makeText(context, "Đã hoàn thành", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListCartNow.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        RecyclerView rcv;
        Button btnFinish;
        TextView tvUserName, tvUserAddress, tvTime, tvCartPrice;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvUserName4124);
            tvUserAddress = itemView.findViewById(R.id.tvUserAddress);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvCartPrice = itemView.findViewById(R.id.tvCartPrice);
            rcv = itemView.findViewById(R.id.recyclerView);
            btnFinish = itemView.findViewById(R.id.buttonFinish);
        }
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

    private String userAddress(String idUser) {
        String address = "";
        for (User value : listUser) {
            if (idUser.equals(value.getId())) {
                address = value.getAddress();
                break;
            }
        }
        return address;
    }

}