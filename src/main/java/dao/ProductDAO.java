package dao;

import database.JDBCUtil;
import model.Category;
import model.CategoryDetail;
import model.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductDAO implements DAOInterface<Product> {

    public static ProductDAO getInstance() {
        return new ProductDAO();
    }

    private CategoryDetailDao categoryDetailDao = CategoryDetailDao.getInstance();

    @Override
    public int insert(Product t) {
        int result = 0;
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO product (id, name, price, categoryDetailid) VALUES (" + t.getId() + ", '" + t.getName() + "', " + t.getPrice() + ", " + t.getCategoryDetail_id().getId() + ")";
            result = statement.executeUpdate(sql);
            connection.close();
            System.out.println("Insert successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(Product t) {
        int result = 0;
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement statement = connection.createStatement();
            String sql = "UPDATE product SET name = '" + t.getName() + "', price = " + t.getPrice() + ", categoryDetailid = " + t.getCategoryDetail_id().getId() + " WHERE id = " + t.getId();
            result = statement.executeUpdate(sql);
            connection.close();
            System.out.println("Update successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(Product t) {
        int result = 0;
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM product WHERE id = " + t.getId();
            result = statement.executeUpdate(sql);
            connection.close();
            System.out.println("Delete successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Product> selectAll() {
        ArrayList<Product> products = new ArrayList<Product>();
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM product";
            java.sql.ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getInt("price"));
                product.setCategoryDetail_id(categoryDetailDao.selectById(resultSet.getInt("categoryDetailid")));
                products.add(product);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product selectById(Product t) {
        Product product = null;
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM product WHERE id = " + t.getId();
            java.sql.ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getInt("price"));
                product.setCategoryDetail_id(categoryDetailDao.selectById(resultSet.getInt("categoryDetailid")));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public ArrayList<Product> selectByCondition(String condition) {
        ArrayList<Product> products = new ArrayList<Product>();
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM product WHERE " + condition;
            java.sql.ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getInt("price"));
                product.setCategoryDetail_id(categoryDetailDao.selectById(resultSet.getInt("categoryDetailid")));
                products.add(product);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }


}
