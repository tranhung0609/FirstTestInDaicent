package dao;

import database.JDBCUtil;
import model.Category;
import model.CategoryDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static database.JDBCUtil.getConnection;

public class CategoryDetailDao {
    private static CategoryDetailDao instance = new CategoryDetailDao();

    public static CategoryDetailDao getInstance() {
        return instance;
    }

    private CategoryDAO categoryDAO = CategoryDAO.getInstance();

    private CategoryDetailDao() {
    }


    public CategoryDetail selectById(int id) {
        CategoryDetail categoryDetail = null;
        try {
            Connection connection = getConnection();
//            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM categorydetail WHERE categoryId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                categoryDetail = new CategoryDetail(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryDetail;
    }

    public int insert(CategoryDetail categoryDetail) {
        int id = 0;
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO categorydetail (name) VALUES ('" + categoryDetail.getName() + "')";
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

    public int update(CategoryDetail categoryDetail) {
        int result = 0;
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "UPDATE categorydetail SET name = '" + categoryDetail.getName() + "' WHERE categoryId = '" + categoryDetail.getId() + "'";
            result = statement.executeUpdate(sql);
            connection.close();
            System.out.println("Update successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(CategoryDetail categoryDetail) {
        int result = 0;
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM categorydetail WHERE categoryId = '" + categoryDetail.getId() + "'";
            result = statement.executeUpdate(sql);
            connection.close();
            System.out.println("Delete successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<CategoryDetail> selectAll() {
        List<CategoryDetail> categoryDetails = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM categorydetail";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("categoryDetailId");
                String name = resultSet.getString("name");
                CategoryDetail categoryDetail = new CategoryDetail(id, name);
                categoryDetails.add(categoryDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryDetails;
    }

    public List<CategoryDetail> selectByCondition(String condition) {
        List<CategoryDetail> categoryDetails = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM categorydetail WHERE " + condition;
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("categoryDetailId");
                String name = resultSet.getString("name");
                CategoryDetail categoryDetail = new CategoryDetail(id, name);
                categoryDetails.add(categoryDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryDetails;
    }

    public List<CategoryDetail> searchByCategoryDeTailName(String name) {
        List<CategoryDetail> categoryDetails = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM categorydetail WHERE name LIKE '%" + name + "%'";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("categoryDetailId");
                String cate = resultSet.getString("name");
                CategoryDetail categoryDetail = new CategoryDetail(id, cate);
                categoryDetails.add(categoryDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryDetails;
    }

    public CategoryDetail selectCategoryDetail(int id) {
        CategoryDetail categoryDetail = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from categoryDetail where categoryDetailId =?");) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int categoryId=rs.getInt("categoryId");
                Category category=categoryDAO.selectCategory(categoryId);
                categoryDetail = new CategoryDetail(id, name,category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryDetail;
    }


}
