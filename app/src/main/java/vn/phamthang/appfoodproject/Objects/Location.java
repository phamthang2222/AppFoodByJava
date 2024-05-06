package vn.phamthang.appfoodproject.Objects;

public class Location {
    private int Id;
    private String Loc;


    public Location() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getLoc() {
        return Loc;
    }

    public void setLoc(String loc) {
        this.Loc = loc;
    }

    @Override
    public String toString() {
        return Loc;
    }
}
