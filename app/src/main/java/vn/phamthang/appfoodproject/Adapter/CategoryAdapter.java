package vn.phamthang.appfoodproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import java.util.List;

import vn.phamthang.appfoodproject.Activity.ListFoodActivity;
import vn.phamthang.appfoodproject.Domain.Category;
import vn.phamthang.appfoodproject.Domain.Foods;
import vn.phamthang.appfoodproject.R;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewHolder> {

    ArrayList<Category> items;
    Context context;

    public CategoryAdapter(ArrayList<Category> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CategoryAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent,false);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.tvTitle.setText(items.get(position).getName());
        switch (position){
            case 0:{
                holder.pic.setBackgroundResource(R.drawable.category0_background);
                break;}
            case 1:{
                holder.pic.setBackgroundResource(R.drawable.category1_background);
                break;}
            case 2:{
                holder.pic.setBackgroundResource(R.drawable.category2_background);
                break;}
            case 3:{
                holder.pic.setBackgroundResource(R.drawable.category3_background);
                break;}
            case 4:{
                holder.pic.setBackgroundResource(R.drawable.category4_background);
                break;}
            case 5:{
                holder.pic.setBackgroundResource(R.drawable.category5_background);
                break;}
            case 6:{
                holder.pic.setBackgroundResource(R.drawable.category6_background);
                break;}
            case 7:{
                holder.pic.setBackgroundResource(R.drawable.category7_background);
                break;}
        }
        int drawableResourceId = context.getResources().getIdentifier(items.get(position).getImagePath(),
                "drawable",holder.itemView.getContext().getPackageName());
        Glide.with(context)
                .load(drawableResourceId)
                .into(holder.pic);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ListFoodActivity.class);
            int pos = position;
//            intent.putExtra("CategoryId",items.get(position).getId());
            intent.putExtra("CategoryId",pos);
            intent.putExtra("CategoryName",items.get(position).getName());
            Log.d("TAG", "click in main: "+ position);
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class viewHolder extends  RecyclerView.ViewHolder{
        TextView tvTitle;
        ImageView pic;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvCategoryName);
            pic = itemView.findViewById(R.id.imgCategory);

        }
    }
}
