package library.dao;

import library.config.ConnectionManager;
import library.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO{
    private static final String INSERT_SQL = "INSERT INTO users (user_id, name,email) VALUES (?, ?,?)";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM users WHERE user_id=?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM users";


    @Override
    public void save(User user) {
        Connection connection = null;
        PreparedStatement stmt = null;
        try{
            //obtaining the conexion from the ConnectionManager
            connection = ConnectionManager.getInstance().getConnection();

            stmt = connection.prepareStatement(INSERT_SQL);

            //preparing the sql query
            stmt.setInt(1,user.getId());
            stmt.setString(2,user.getName());

            int rowAffected = stmt.executeUpdate();


        }catch (SQLException e){
            System.out.println("Error saving the user: " + e.getMessage());

        }finally {
            // close preparedStatement and connection
            try {
                if(stmt!=null) stmt.close();
            } catch (SQLException e){
                System.out.println("Error closing the statement: " + e.getMessage());
            }

        }
    }

    @Override
    public void update(User user) {
        System.out.println("No implementation for now");
    }

    @Override
    public User findById(int id) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            connection = ConnectionManager.getInstance().getConnection();

            stmt = connection.prepareStatement(SELECT_BY_ID_SQL);
            stmt.setLong(1,id);

            rs = stmt.executeQuery();

            if(rs.next()){
                int userId = rs.getInt("user_id");
                String userName = rs.getString("name");
                String userEmail = rs.getString(("email"));

                return new User(userName,userId,userEmail);
            } else {
                System.out.println("User not found with id: " + id);
                return null;
            }


        }catch (SQLException e){
            System.out.println("Error finding by id the user: " + e.getMessage());
        }finally {
            try {
                if(rs!=null) rs.close();
                if(stmt!=null) stmt.close();
            } catch (SQLException e){
                System.out.println("Error closing resources: " + e.getMessage());
            }
            ConnectionManager.getInstance().closeConnection(connection);
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            connection = ConnectionManager.getInstance().getConnection();

            stmt = connection.prepareStatement(SELECT_ALL_SQL);

            rs = stmt.executeQuery();

            ArrayList<User> users = new ArrayList<>();

            while(rs.next()){
                int userId = rs.getInt("user_id");
                String userName = rs.getString("name");
                String userEmail = rs.getString("email");

                users.add(new User(userName,userId, userEmail));
            }


        }catch (SQLException e){
            System.out.println("Error finding all the users: " + e.getMessage());
        }finally {
            try {
                if(rs!=null) rs.close();
                if(stmt!=null) stmt.close();
            } catch (SQLException e){
                System.out.println("Error closing resources: " + e.getMessage());
            }
            ConnectionManager.getInstance().closeConnection(connection);
        }
        return null;
    }
}
