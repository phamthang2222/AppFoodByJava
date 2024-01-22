package vn.phamthang.appfoodproject.Domain;

public class Time {
    private int id;
    private String Value;

    public Time() {
    }

    public Time(int id, String value) {
        this.id = id;
        this.Value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        this.Value = value;
    }

    @Override
    public String toString() {
        return Value;
    }
}
