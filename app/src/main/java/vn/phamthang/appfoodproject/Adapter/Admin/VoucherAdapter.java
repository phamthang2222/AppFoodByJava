package vn.phamthang.appfoodproject.Adapter.Admin;

import static vn.phamthang.appfoodproject.Activity.User.BaseActivity.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.phamthang.appfoodproject.Objects.Voucher;
import vn.phamthang.appfoodproject.R;


public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.viewHolder> {

    private ArrayList<Voucher> mListVoucher;
    Context context;

    public VoucherAdapter(ArrayList<Voucher> mListVoucher) {
        this.mListVoucher = mListVoucher;
    }


    @NonNull
    @Override
    public VoucherAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_list_voucher, parent, false);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherAdapter.viewHolder holder, int position) {
        holder.tvIdVoucher.setText(mListVoucher.get(position).getId());
        holder.tvCodeVoucher.setText(mListVoucher.get(position).getCode());
        holder.tvValueVoucher.setText(mListVoucher.get(position).getValue()+"%");
        holder.btnDelete.setOnClickListener(v -> {
            mListVoucher.remove(position);
            updateVoucherInFireBase(mListVoucher);
            notifyDataSetChanged();
            Toast.makeText(context, "Xóa Vouch", Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public int getItemCount() {
        return mListVoucher.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvIdVoucher, tvCodeVoucher, tvValueVoucher;
        ImageView btnDelete;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            tvIdVoucher = itemView.findViewById(R.id.tvIdVoucher);
            tvCodeVoucher = itemView.findViewById(R.id.tvCodeVoucher);
            tvValueVoucher = itemView.findViewById(R.id.tvValueVoucher);
            btnDelete = itemView.findViewById(R.id.btnDeleteVoucher);


        }
    }

    private void updateVoucherInFireBase(ArrayList<Voucher> listVoucher) {
        database.getReference("Voucher").removeValue().addOnSuccessListener(unused -> {
            if (listVoucher != null) {
                for (Voucher voucher : listVoucher) {
                    String idVoucher = database.getReference("Voucher").push().getKey();
                    if (idVoucher != null) {
                        database.getReference("Voucher").child(idVoucher).setValue(voucher);
                    }
                }
            }
        });
    }
}
