package vn.phamthang.appfoodproject.Objects;

public class Category {
    private int id;
    private String ImagePath;
    private String Name;

    public Category(int id, String imagePath, String name) {
        this.id = id;
        this.ImagePath = imagePath;
        this.Name = name;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        this.ImagePath = imagePath;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }
}
