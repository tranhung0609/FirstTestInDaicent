package model;

public class CategoryDetail {
    private int id;
    private String name;

    Category category_id;

    public CategoryDetail() {

    }

    public CategoryDetail(int id, String name, Category category_id) {
        this.id = id;
        this.name = name;
        this.category_id = category_id;
    }

    public CategoryDetail(int id, String name) {
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

    public Category getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Category category_id) {
        this.category_id = category_id;
    }

    @Override
    public String toString() {
        return "CategoryDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category_id=" + category_id +
                '}';
    }
}