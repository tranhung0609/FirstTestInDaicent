package dao;

import database.JDBCUtil;
import model.Category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private static CategoryDAO instance = new CategoryDAO();

    public static CategoryDAO getInstance() {
        return instance;
    }

    private CategoryDAO() {
    }

    public int insert(Category category) {
        int id = 0;
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO category (name) VALUES ('" + category.getName() + "')";
            statement.execute(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            connection.close();
            System.out.println("Insert successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public int update(Category category) {
          int id = 0;
          try {
                Connection connection = JDBCUtil.getConnection();
                Statement statement = connection.createStatement();
                String sql = "UPDATE category SET name = '" + category.getName() + "' WHERE categoryId = " + category.getId();
                statement.execute(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                 id = resultSet.getInt(1);
                }
                connection.close();
                System.out.println("Update successfully!");
          } catch (SQLException e) {
                e.printStackTrace();
          }
          return id;
     }

     public int delete(Category category) {
          int id = 0;
          try {
                Connection connection = JDBCUtil.getConnection();
                Statement statement = connection.createStatement();
                String sql = "DELETE FROM category WHERE categoryId = " + category.getId();
                statement.execute(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                 id = resultSet.getInt(1);
                }
                connection.close();
                System.out.println("Delete successfully!");
          } catch (SQLException e) {
                e.printStackTrace();
          }
          return id;
     }

     public List<Category> selectAll() {
         List<Category> categories = new ArrayList<>();
          try {
                Connection connection = JDBCUtil.getConnection();
                Statement statement = connection.createStatement();
                String sql = "SELECT * FROM category";
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    int id = resultSet.getInt("categoryId");
                    String name = resultSet.getString("name");
                    Category category = new Category(id, name);
                    categories.add(category);
                }
          } catch (SQLException e) {
                e.printStackTrace();
          }
          return categories;
     }

     public List<Category> searchByCategory(String name) {
         List<Category> categories = new ArrayList<>();
          try {
                Connection connection = JDBCUtil.getConnection();
                Statement statement = connection.createStatement();
                String sql = "SELECT * FROM category WHERE name LIKE '%" + name + "%'";
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    int id = resultSet.getInt("categoryId");
                    String cate = resultSet.getString("name");
                    Category category = new Category(id, cate);
                    categories.add(category);
                }
          } catch (SQLException e) {
                e.printStackTrace();
          }
          return categories;
     }
}
