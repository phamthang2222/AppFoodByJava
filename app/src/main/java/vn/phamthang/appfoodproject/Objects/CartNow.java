package vn.phamthang.appfoodproject.Objects;

import java.util.List;

public class CartNow {
    private String idUser;
    private String idCartNow;
    private Double totalPrice;
    private List<Foods> foodsList;
    private String date;
    private boolean isFinish;



    public CartNow() {
    }

//    public CartNow(String id, Double totalPrice, List<Foods> foodsList, String date, Boolean isFinish) {
//        this.id = id;
//        this.totalPrice = totalPrice;
//        this.foodsList = foodsList;
//        this.date = date;
//        this.isFinish = false;
//    }

    public CartNow(String idUser, String idCartNow, Double totalPrice, List<Foods> foodsList, String date, boolean isFinish) {
        this.idUser = idUser;
        this.idCartNow = idCartNow;
        this.totalPrice = totalPrice;
        this.foodsList = foodsList;
        this.date = date;
        this.isFinish = isFinish;
    }

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdCartNow() {
        return idCartNow;
    }

    public void setIdCartNow(String idCartNow) {
        this.idCartNow = idCartNow;
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


}
