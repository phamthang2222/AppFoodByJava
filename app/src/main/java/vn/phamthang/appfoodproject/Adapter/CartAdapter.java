package vn.phamthang.appfoodproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.ArrayList;

import vn.phamthang.appfoodproject.Objects.Foods;
import vn.phamthang.appfoodproject.Helper.ManagmentCart;
import vn.phamthang.appfoodproject.Interface.ChangeNumberItemsListener;
import vn.phamthang.appfoodproject.R;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.viewHolder> {

    ArrayList<Foods> list ;
    private ManagmentCart managmentCart;
    public ChangeNumberItemsListener changeNumberItemsListener;

    public CartAdapter(ArrayList<Foods> list,Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.list = list;
        managmentCart = new ManagmentCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public CartAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cartview,parent,false);

        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.viewHolder holder, int position) {
        holder.tvTitle.setText(list.get(position).getTitle());
        holder.tvPriceInCart.setText("$"+list.get(position).getPrice());
        holder.tvFeeItem.setText("$"+(list.get(position).getNumberInCart() *list.get(position).getPrice() ));
        holder.tvQuantity.setText(""+list.get(position).getNumberInCart());
        Glide.with(holder.itemView.getContext())
                .load(list.get(position).getImagePath())
                .transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.imgItem);
        holder.btPlus.setOnClickListener(v -> {
            managmentCart.plusNumberItem(list, position, new ChangeNumberItemsListener() {
                @Override
                public void change() {
                    notifyDataSetChanged();
                    changeNumberItemsListener.change();
                }
            });
        });
        holder.btMinus.setOnClickListener(v -> {
            managmentCart.minusNumberItem(list, position, new ChangeNumberItemsListener() {
                @Override
                public void change() {
                    notifyDataSetChanged();
                    changeNumberItemsListener.change();
                }
            });
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvFeeItem, btMinus, btPlus, tvQuantity,tvPriceInCart;
        ImageView imgItem;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitleInCartView);
            imgItem = itemView.findViewById(R.id.imgFoodInCV);
            tvPriceInCart = itemView.findViewById(R.id.tvPriceInCart);
            btMinus = itemView.findViewById(R.id.btMinusInCardView);
            btPlus = itemView.findViewById(R.id.btPlusInCardView);
            tvQuantity = itemView.findViewById(R.id.tvQuantityInCardView);
            tvFeeItem = itemView.findViewById(R.id.tvFeeItem);

        }
    }
}
