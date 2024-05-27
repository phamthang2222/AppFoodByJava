package vn.phamthang.appfoodproject.Ultils;

import java.util.ArrayList;

import vn.phamthang.appfoodproject.Objects.CartNow;

public class DataClickEvent {
    public final ArrayList<CartNow> data;

    public DataClickEvent(ArrayList<CartNow> data) {
        this.data = data;
    }
}
