package vn.phamthang.appfoodproject.Helper.OrderCart;

import vn.phamthang.appfoodproject.Objects.CartNow;

public class ItemClickEvent {
    public final CartNow data;

    public ItemClickEvent(CartNow data) {
        this.data = data;
    }
}