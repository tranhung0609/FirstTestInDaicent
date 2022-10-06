package model;

public class Product implements Comparable<Product> {
    private int id;
    private String name;
    private double price;

    CategoryDetail categoryDetail_id;

    public Product() {

    }

    public Product(String name) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryDetail_id = categoryDetail_id;
    }

    public Product(int id, String name, double price, CategoryDetail detail) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CategoryDetail getCategoryDetail_id() {
        return categoryDetail_id;
    }

    public void setCategoryDetail_id(CategoryDetail categoryDetail_id) {
        this.categoryDetail_id = categoryDetail_id;
    }

    @Override
    public String toString() {
        return "Product{" +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", categoryDetail_Id=" + categoryDetail_id +
                '}';
    }

    @Override
    public int compareTo(Product o) {
        return this.name.compareTo(o.name);
    }
}