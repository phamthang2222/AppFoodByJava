package vn.phamthang.appfoodproject.Adapter.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.phamthang.appfoodproject.Objects.Foods;
import vn.phamthang.appfoodproject.R;

public class ItemOrderInCartAdapter extends RecyclerView.Adapter<ItemOrderInCartAdapter.viewHolder> {

    Context context;
    List<Foods> mListItemOrderCartNow;

    public ItemOrderInCartAdapter(List<Foods> mListItemOrderCartNow) {
        this.mListItemOrderCartNow = mListItemOrderCartNow;
    }

    @NonNull
    @Override
    public ItemOrderInCartAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_itemorderincart,parent,false);
        return new viewHolder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemOrderInCartAdapter.viewHolder holder, int position) {
        holder.tvStt.setText(position+1+". ");
        holder.tvFoodName.setText(mListItemOrderCartNow.get(position).getTitle());
        holder.tvSL.setText(mListItemOrderCartNow.get(position).getNumberInCart()+"");
    }

    @Override
    public int getItemCount() {
        return mListItemOrderCartNow.size();
    }

    public class viewHolder extends  RecyclerView.ViewHolder {
        TextView tvStt,tvFoodName,tvSL;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvStt = itemView.findViewById(R.id.tvStt);
            tvFoodName = itemView.findViewById(R.id.tvFoodName);
            tvSL = itemView.findViewById(R.id.tvSL);
        }
    }

//    private int getQuanity(double price){
//
//    }
}
