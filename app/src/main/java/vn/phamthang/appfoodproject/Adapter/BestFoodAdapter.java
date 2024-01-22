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

import vn.phamthang.appfoodproject.Domain.Foods;
import vn.phamthang.appfoodproject.R;

public class BestFoodAdapter extends RecyclerView.Adapter<BestFoodAdapter.viewHolder> {

    ArrayList<Foods> items;
    Context context;

    public BestFoodAdapter(ArrayList<Foods> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public BestFoodAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_best_deal,parent,false);

        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull BestFoodAdapter.viewHolder holder, int position) {
        holder.tvTitle.setText(items.get(position).getTitle());
        holder.tvPrice.setText("$"+items.get(position).getPrice());
        holder.tvStart.setText(""+items.get(position).getStar());
        holder.tvTime.setText(items.get(position).getTimeValue()+" phút");
        Glide.with(context)
                .load(items.get(position).getImagePath())
                .transform(new CenterCrop(),new RoundedCorners(30)) // crop and border
                .into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewHolder extends  RecyclerView.ViewHolder{
        TextView tvTitle, tvPrice, tvStart,tvTime;
        ImageView pic;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvStart = itemView.findViewById(R.id.tvStart);
            tvTime = itemView.findViewById(R.id.tvTime);
            pic = itemView.findViewById(R.id.imgItem);

        }
    }
}
