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

import vn.phamthang.appfoodproject.Activity.User.DetailActivity;
import vn.phamthang.appfoodproject.Objects.Foods;
import vn.phamthang.appfoodproject.R;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.viewHolder> {
    ArrayList<Foods> items;
    Context context;
    public FoodListAdapter(ArrayList<Foods> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public FoodListAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.viewholder_list_food,parent,false);

        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodListAdapter.viewHolder holder, int position) {
        holder.tvTitle.setText(items.get(position).getTitle());
        holder.tvTime.setText(items.get(position).getTimeValue()+" phút");
        holder.tvPrice.setText("$"+items.get(position).getPrice());
        holder.tvStar.setText(""+items.get(position).getStar());
        Glide.with(context)
                .load(items.get(position).getImagePath())
                .transform(new CenterCrop(),new RoundedCorners(30))
                .into(holder.imgPicFood);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("object",items.get(position));
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvPrice, tvTime,tvStar;
        ImageView imgPicFood;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitleInListFood);
            tvPrice = itemView.findViewById(R.id.tvPriceInListFood);
            tvTime = itemView.findViewById(R.id.tvTimeInListFood);
            tvStar = itemView.findViewById(R.id.tvStarInListFood);
            imgPicFood = itemView.findViewById(R.id.imgPic);
        }
    }
}
