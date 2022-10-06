package model;

public class Category implements Comparable<Category> {
    private int id;
    private String name;

    public Category(String categoryname, String categorydetailname) {
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Category o) {
        return this.name.compareTo(o.name);
    }
}
