package dao;

import database.JDBCUtil;
import model.Category;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static database.JDBCUtil.getConnection;

public class CategoryDAO {
    private static CategoryDAO instance = new CategoryDAO();

    public static CategoryDAO getInstance() {
        return instance;
    }

    private CategoryDAO() {
    }

    private String jdbcURL = "jdbc:mysql://localhost:3306/testdaicent?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

//    public int insert(Category category) {
//        int id = 0;
//        try {
//            Connection connection = getConnection();
//            Statement statement = connection.createStatement();
//            String sql = "INSERT INTO category (name) VALUES ('" + category.getName() + "')";
//            statement.execute(sql, Statement.RETURN_GENERATED_KEYS);
//            ResultSet resultSet = statement.getGeneratedKeys();
//            if (resultSet.next()) {
//                id = resultSet.getInt(1);
//            }
//            connection.close();
//            System.out.println("Insert successfully!");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return id;
//    }
//
//    public int update(Category category) {
//        int id = 0;
//        try {
//            Connection connection = getConnection();
//            Statement statement = connection.createStatement();
//            String sql = "UPDATE category SET name = '" + category.getName() + "' WHERE categoryId = " + category.getId();
//            statement.execute(sql, Statement.RETURN_GENERATED_KEYS);
//            ResultSet resultSet = statement.getGeneratedKeys();
//            if (resultSet.next()) {
//                id = resultSet.getInt(1);
//            }
//            connection.close();
//            System.out.println("Update successfully!");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return id;
//    }
//
//    public int delete(Category category) {
//        int id = 0;
//        try {
//            Connection connection = getConnection();
//            Statement statement = connection.createStatement();
//            String sql = "DELETE FROM category WHERE categoryId = " + category.getId();
//            statement.execute(sql, Statement.RETURN_GENERATED_KEYS);
//            ResultSet resultSet = statement.getGeneratedKeys();
//            if (resultSet.next()) {
//                id = resultSet.getInt(1);
//            }
//            connection.close();
//            System.out.println("Delete successfully!");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return id;
//    }
//
//    public List<Category> selectAll() {
//        List<Category> categories = new ArrayList<>();
//        try {
//            Connection connection = getConnection();
//            Statement statement = connection.createStatement();
//            String sql = "SELECT * FROM category";
//            ResultSet resultSet = statement.executeQuery(sql);
//            while (resultSet.next()) {
//                int id = resultSet.getInt("categoryId");
//                String name = resultSet.getString("name");
//                Category category = new Category(id, name);
//                categories.add(category);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return categories;
//    }
//
//    public List<Category> searchByCategory(String name) {
//        List<Category> categories = new ArrayList<>();
//        try {
//            Connection connection = getConnection();
//            Statement statement = connection.createStatement();
//            String sql = "SELECT * FROM category WHERE name LIKE '%" + name + "%'";
//            ResultSet resultSet = statement.executeQuery(sql);
//            while (resultSet.next()) {
//                int id = resultSet.getInt("categoryId");
//                String cate = resultSet.getString("name");
//                Category category = new Category(id, cate);
//                categories.add(category);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return categories;
//    }

    public Category selectCategory(int id) {
        Category category = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select categoryId,name from category where categoryId =?");) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                category = new Category(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

}
