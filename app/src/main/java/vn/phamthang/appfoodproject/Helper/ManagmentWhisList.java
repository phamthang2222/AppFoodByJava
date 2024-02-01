package vn.phamthang.appfoodproject.Helper;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import vn.phamthang.appfoodproject.Domain.Foods;
import vn.phamthang.appfoodproject.Interface.RemoveItemFromList;

public class ManagmentWhisList {
    private Context context;
    private TinyDB tinyDB;

    public ManagmentWhisList(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(Foods item){
        ArrayList<Foods> wishList  = getList();
        boolean existAlready = false;

        int n = 0 ;
        for (int i = 0; i < wishList.size(); i++) {
            if (wishList.get(i).getTitle().equals(item.getTitle())) {
                existAlready = true;
                n = i;
                break;
            }
        }
        if(existAlready){
            wishList.remove(item);
            Toast.makeText(context, "Đã xóa khỏi yêu thích 2", Toast.LENGTH_SHORT).show();
            removeWhisList(wishList, n, new RemoveItemFromList() {
                @Override
                public void remove() {
                    tinyDB.putListObject("WhisList",wishList);
                }
            });
        }else{
            wishList.add(item);
        }
        tinyDB.putListObject("WhisList",wishList);
        Toast.makeText(context, "Đã thêm vào yêu thích", Toast.LENGTH_SHORT).show();

    }

    public ArrayList<Foods> getList()
    {
        return tinyDB.getListObject("WhisList");
    }
    public void removeWhisList(ArrayList<Foods> listItem, int position, RemoveItemFromList removeItemFromList){
        listItem.remove(position);
        Toast.makeText(context, "Đã xóa khỏi yêu thích", Toast.LENGTH_SHORT).show();
        tinyDB.putListObject("WhisList",listItem);
        removeItemFromList.remove();
    }
}
