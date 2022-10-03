package main;

import dao.CategoryDAO;
import dao.CategoryDetailDao;
import dao.ProductDAO;
import model.Category;
import model.CategoryDetail;
import model.Product;

public class Main {
    public static void main(String[] args) {
//        Test insert
//        Product product1 = new Product(2, "Iphone 13", 20000, new CategoryDetail(7, "Iphone", new Category(1, "Apple")));
//        Product product2 = new Product(3, "Iphone 14", 200003, new CategoryDetail(7, "Iphone", new Category(1, "Apple")));
//        Product product3 = new Product(4, "Iphone 12", 200002, new CategoryDetail(7, "Iphone", new Category(1, "Apple")));
//        ProductDAO.getInstance().insert(product2);
//        ProductDAO.getInstance().insert(product3);

//        Test update
//        Product product2 = new Product(1, "Iphone 12 pro max", 20000000, new CategoryDetail(1, "Iphone", new Category(1, "Apple")));
//        ProductDAO.getInstance().update(product2);

//        Test delete
//        Product product3 = new Product(1, "Iphone 12 pro max", 20000000, new CategoryDetail(1, "Iphone", new Category(1, "Apple")));
//        ProductDAO.getInstance().delete(product3);

//        Test selectAll
//        ProductDAO.getInstance().selectAll().forEach(System.out::println);

////        Test Condition
//        ProductDAO.getInstance().selectByCondition("name = 'Iphone 12'").forEach(System.out::println);

//        Test CategoryDetail
//        CategoryDetail categoryDetail = new CategoryDetail(2, "SamSungUltra", new Category(1, "SamSung"));
//        CategoryDetailDao.getInstance().insert(categoryDetail);

//    Test updateCategoryDetail
//        CategoryDetail categoryDetail1 = new CategoryDetail(2, "SamSungUltra", new Category(1, "SamSung"));
//        CategoryDetailDao.getInstance().update(categoryDetail1);

//        Test deleteCategoryDetail
//        CategoryDetail categoryDetail2 = new CategoryDetail(2, "SamSungUltra", new Category(1, "SamSung"));
//        CategoryDetailDao.getInstance().delete(categoryDetail2);

//        Test selectAllCategoryDetail
//        CategoryDetailDao.getInstance().selectAll().forEach(System.out::println);

//        Test insertCategory
//        Category category = new Category(3, "Iphone");
//        CategoryDAO.getInstance().insert(category);

//        Test updateCategory
//        Category category1 = new Category(6, "Xiaomi");
//        CategoryDAO.getInstance().update(category1);

//        Test deleteCategory
//        Category category2 = new Category(6, "Xiaomi");
//        CategoryDAO.getInstance().delete(category2);

//        Test selectAllCategory
//        CategoryDAO.getInstance().selectAll().forEach(System.out::println);



//        Test search
//        ProductDAO.getInstance().search("Iphone").forEach(System.out::println);

//        Test searchByCategory
        CategoryDAO.getInstance().searchByCategory("A").forEach(System.out::println);

//        Test searchByCategoryDetail
//        CategoryDetailDao.getInstance().searchByCategoryDeTailName("s").forEach(System.out::println);

//        Test searchByProduct
//        ProductDAO.getInstance().search("Iphone").forEach(System.out::println);

//        Test searchByPrice
        ProductDAO.getInstance().searchByPrice(20000, 200000).forEach(System.out::println);
    }
}
