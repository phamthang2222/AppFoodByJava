package vn.phamthang.appfoodproject.Objects;

import java.util.List;

public class CartNow {
    private String id;
    private Double totalPrice;
    private List<Foods> foodsList;
    private String date;
    private boolean isFinish;



    public CartNow() {
    }

    public CartNow(String id, Double totalPrice, List<Foods> foodsList, String date, Boolean isFinish) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.foodsList = foodsList;
        this.date = date;
        this.isFinish = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Foods> getFoodsList() {
        return foodsList;
    }

    public void setFoodsList(List<Foods> foodsList) {
        this.foodsList = foodsList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {

        this.date = date;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    @Override
    public String toString() {
        return "CartNow{" +
                "id='" + id + '\'' +
                ", totalPrice=" + totalPrice +
                ", foodsList=" + foodsList +
                ", date='" + date + '\'' +
                ", isFinish=" + isFinish +
                '}';
    }
}
