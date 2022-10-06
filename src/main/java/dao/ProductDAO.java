package dao;

import database.JDBCUtil;
import model.Category;
import model.CategoryDetail;
import model.Product;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.JDBCUtil.getConnection;

public class ProductDAO implements DAOInterface<Product> {

    public static ProductDAO getInstance() {
        return new ProductDAO();
    }

    private CategoryDetailDao categoryDetailDao = CategoryDetailDao.getInstance();

    @Override
    public int insert(Product t) {
        int result = 0;
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql =  "INSERT INTO product" + "  (name, price,categoryDetail) VALUES " +
                    " (?,?,?);";
            result = statement.executeUpdate(sql);
            connection.close();
            System.out.println("Insert successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    @Override
    public int update(Product t) {
        int result = 0;
        try {
            Connection connection = getConnection();
            String sql = "UPDATE product SET name = ?, price = ?, categoryDetailId = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, t.getName());
            preparedStatement.setDouble(2, t.getPrice());
            preparedStatement.setInt(3, t.getId());
            preparedStatement.setInt(4, t.getCategoryDetail_id().getId());
            result = preparedStatement.executeUpdate();
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
            Connection connection = getConnection();
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
    public List<Product> selectAll() {
        List<Product> products = new ArrayList<Product>();
        try {
            Connection connection = getConnection();
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
            Connection connection = getConnection();
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


    public List<Product> selectByCondition (String condition) {
        List<Product> products = new ArrayList<Product>();
        try {
            Connection connection = getConnection();
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


    public List<Product> search(String name) {
        List<Product> products = new ArrayList<Product>();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM product WHERE name LIKE '%" + name + "%'";
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

    public List<Product> searchByPrice(int min, int max) {
        List<Product> products = new ArrayList<Product>();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM product WHERE price BETWEEN " + min + " AND " + max;
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


    public List<Product> findAllByCategory(int id) {
        List<Product>products=new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(" select product.name,product.price, product.categoryDetailId from product\n" +
                     "join categorydetail c on c.categoryDetailId = product.categoryDetailId\n" +
                     "join category c2 on c2.categoryId = c.categoryId where c2.categoryId=?;");) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int categoryDetailId = rs.getInt("categoryDetailId");
                CategoryDetail detail=categoryDetailDao.selectCategoryDetail(categoryDetailId);
                Product product =new Product(id,name,price,detail);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
