package vn.phamthang.appfoodproject.Objects;

public class Cart {
    private String id;
    private Double totalPrice;
    private String date;

    public Cart(String id, Double totalPrice, String date) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public Cart() {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id='" + id + '\'' +
                ", totalPrice=" + totalPrice +
                ", date=" + date +
                '}';
    }
}
