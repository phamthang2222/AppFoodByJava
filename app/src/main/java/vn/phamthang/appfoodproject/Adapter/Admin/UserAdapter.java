package vn.phamthang.appfoodproject.Adapter.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.phamthang.appfoodproject.Objects.User;
import vn.phamthang.appfoodproject.R;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.viewholder> {
    ArrayList<User> list;
    Context context;

    public UserAdapter(ArrayList<User> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public UserAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_user,parent,false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.viewholder holder, int position) {
        holder.tvUserName.setText(list.get(position).getUserName());
        holder.tvEmailUser.setText("Email: "+list.get(position).getEmail());
        holder.tvPassWordUser.setText("Mật khẩu: "+list.get(position).getPassword());
        holder.tvPhoneNumberUser.setText("SĐT: "+list.get(position).getNumberPhone());
        holder.tvCreatedDate.setText("Ngày tạo: "+list.get(position).getDateCreated());
        holder.tvAddressUser.setText("Địa chỉ: "+list.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView tvUserName, tvEmailUser, tvPassWordUser,
                tvPhoneNumberUser,tvCreatedDate, tvAddressUser;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvEmailUser = itemView.findViewById(R.id.tvEmailUser);
            tvPassWordUser = itemView.findViewById(R.id.tvPassWordUser);
            tvPhoneNumberUser = itemView.findViewById(R.id.tvPhoneNumberUser);
            tvCreatedDate = itemView.findViewById(R.id.tvCreatedDate);
            tvAddressUser = itemView.findViewById(R.id.tvAddressUser);

        }
    }
}
