package main;

import dao.CategoryDAO;
import dao.CategoryDetailDao;
import dao.ProductDAO;
import model.Category;
import model.CategoryDetail;
import model.Product;

public class Main {
    public static void main(String[] args) {
        ProductDAO productDAO = ProductDAO.getInstance();
        CategoryDAO categoryDAO = CategoryDAO.getInstance();
        CategoryDetailDao categoryDetailDao = CategoryDetailDao.getInstance();
        System.out.println(productDAO.findAllByCategory(1));
        System.out.println(categoryDetailDao.selectCategoryDetail(1));
        System.out.println(categoryDAO.selectCategory(1));




    }
}
