package vn.phamthang.appfoodproject.Adapter;

import android.content.Context;
import android.content.Intent;
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

import vn.phamthang.appfoodproject.Activity.DetailActivity;
import vn.phamthang.appfoodproject.Domain.Foods;
import vn.phamthang.appfoodproject.Helper.ManagmentWhisList;
import vn.phamthang.appfoodproject.Interface.RemoveItemFromList;
import vn.phamthang.appfoodproject.R;

public class WhisListAdapter extends RecyclerView.Adapter<WhisListAdapter.viewHolder> {

    ArrayList<Foods> list;
    Context context;
    private ManagmentWhisList managmentWhisList;
    RemoveItemFromList removeItemFromList;

    public WhisListAdapter(ArrayList<Foods> list, Context context, RemoveItemFromList removeItemFromList) {
        this.list = list;
        managmentWhisList = new ManagmentWhisList(context);
        this.removeItemFromList = removeItemFromList;
    }

    @NonNull
    @Override
    public WhisListAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       context = parent.getContext();
        View infalate = LayoutInflater
               .from(parent.getContext())
               .inflate(R.layout.viewholder_whislist_item,parent,false);

        return new viewHolder(infalate);
    }

    @Override
    public void onBindViewHolder(@NonNull WhisListAdapter.viewHolder holder, int position) {
        holder.tvTitle.setText(list.get(position).getTitle());
        holder.tvPrice.setText("$"+list.get(position).getPrice());
        holder.tvStar.setText(""+list.get(position).getStar());
        Glide.with(holder.imgItem.getContext())
                .load(list.get(position).getImagePath())
                .transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.imgItem);
        holder.btRemove.setOnClickListener(v -> {
            managmentWhisList.removeWhisList(list, position, new RemoveItemFromList() {
                @Override
                public void remove() {
                    notifyDataSetChanged();
                    removeItemFromList.remove();
                }
            });
        });
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("object",list.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvPrice, tvStar;
        ImageView imgItem, btRemove;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitleInWL);
            tvPrice = itemView.findViewById(R.id.tvPriceInWL);
            tvStar = itemView.findViewById(R.id.tvStartInWL);
            imgItem = itemView.findViewById(R.id.imgFoodInWL);
            btRemove = itemView.findViewById(R.id.btRemove);
        }
    }
}
