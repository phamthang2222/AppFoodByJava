package vn.phamthang.appfoodproject.Domain;

public class Foods {
    private int CategoryId;
    private String Description;
    private Boolean BestFood;
    private int Id;
    private int LocationId;
    private double Price;
    private String ImagePath;
    private int PriceId;
    private double Star;
    private int TimeId;
    private int TimeValue;
    private String Title;
    private int numberInCart;

    public Foods(int categoryId, String description, Boolean bestFood, int id,
                 int locationId, double price, String imagePath, int priceId,
                 double star, int timeId, int timeValue, String title, int numberInCart) {
        this.CategoryId = categoryId;
        this.Description = description;
        this.BestFood = bestFood;
        this.Id = id;
        this.LocationId = locationId;
        this.Price = price;
        this.ImagePath = imagePath;
        this.PriceId = priceId;
        this.Star = star;
        this.TimeId = timeId;
        this.TimeValue = timeValue;
        this.Title = title;
        this.numberInCart = numberInCart;
    }

    public Foods() {
    }

    @Override
    public String toString() {
        return Title;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        this.CategoryId = categoryId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public Boolean getBestFood() {
        return BestFood;
    }

    public void setBestFood(Boolean bestFood) {
        this.BestFood = bestFood;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public int getLocationId() {
        return LocationId;
    }

    public void setLocationId(int locationId) {
        this.LocationId = locationId;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        this.Price = price;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        this.ImagePath = imagePath;
    }

    public int getPriceId() {
        return PriceId;
    }

    public void setPriceId(int priceId) {
        this.PriceId = priceId;
    }

    public double getStar() {
        return Star;
    }

    public void setStar(double star) {
        this.Star = star;
    }

    public int getTimeId() {
        return TimeId;
    }

    public void setTimeId(int timeId) {
        this.TimeId = timeId;
    }

    public int getTimeValue() {
        return TimeValue;
    }

    public void setTimeValue(int timeValue) {
        this.TimeValue = timeValue;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
