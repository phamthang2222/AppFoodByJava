package vn.phamthang.appfoodproject.Domain;

public class Location {
    private int id;
    private String loc;


    public Location() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public String toString() {
        return loc;
    }
}
