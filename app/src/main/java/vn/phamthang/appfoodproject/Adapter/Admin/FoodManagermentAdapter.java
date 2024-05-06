package vn.phamthang.appfoodproject.Adapter.Admin;

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

import java.util.ArrayList;

import vn.phamthang.appfoodproject.Activity.Admin.EditFoodActivity;
import vn.phamthang.appfoodproject.Objects.Foods;
import vn.phamthang.appfoodproject.R;

public class FoodManagermentAdapter extends RecyclerView.Adapter<FoodManagermentAdapter.viewholder> {
    Context context;
    ArrayList<Foods> listFoodManagerment;

    public FoodManagermentAdapter(ArrayList<Foods> listFoodManagerment) {
        this.listFoodManagerment = listFoodManagerment;
    }

    @NonNull
    @Override
    public FoodManagermentAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_food_managerment,parent,false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodManagermentAdapter.viewholder holder, int position) {
        holder.tvTitleFood.setText(listFoodManagerment.get(position).getTitle());
        holder.tvPriceFood.setText("$"+listFoodManagerment.get(position).getPrice());
        holder.tvTimeDelivery.setText(listFoodManagerment.get(position).getTimeValue()+" phút");
        holder.tvStart.setText(""+listFoodManagerment.get(position).getStar());
        holder.tvLocation.setText("Vị trí: "+convert(listFoodManagerment.get(position).getLocationId()));
        Glide.with(context)
                .load(listFoodManagerment.get(position).getImagePath())
                .transform(new CenterCrop()) // crop and border
                .into(holder.imgFood);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditFoodActivity.class);
            intent.putExtra("items",listFoodManagerment.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listFoodManagerment.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView imgFood;
        TextView tvTitleFood, tvPriceFood,tvTimeDelivery,tvStart,tvLocation;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.imgFood);
            tvTitleFood = itemView.findViewById(R.id.tvTitleFood);
            tvPriceFood = itemView.findViewById(R.id.tvPriceFood);
            tvTimeDelivery = itemView.findViewById(R.id.tvTimeDelivery);
            tvStart = itemView.findViewById(R.id.tvStart);
            tvLocation = itemView.findViewById(R.id.tvLocation);

        }
    }
    public String convert(int position){
        String location = null;
        switch (position){
            case 0:
                location = "Cầu Giấy";
                break;
            case 1:
                location = "Bắc Từ Liêm";
                break;
            case 2:
                location = "Hoàn Kiếm";
                break;
        }
        return location;
    }

}
