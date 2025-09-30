package library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library.config.ConnectionManager;
import library.model.Magazine;

public class MagazineDAOImpl implements MagazineDAO{
    @Override
    public void save(Magazine magazine) {
        Connection connection = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet generatedKeys = null;
        try{
            //obtaining the connection from the ConnectionManager
            connection = ConnectionManager.getInstance().getConnection();

            //inserting first the item
            stmt = connection.prepareStatement("INSERT INTO items(title,published_year,user_id) VALUES(?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);


            //preparing the sql query for the item
            stmt.setString(1,magazine.getTitle());
            stmt.setInt(2,magazine.getPublishedYear());
            stmt.setInt(3,magazine.getUserId());

            int rowAffected = stmt.executeUpdate();


            generatedKeys = stmt.getGeneratedKeys();

            int generatedKey = 0;

            if(generatedKeys.next()){
                generatedKey = generatedKeys.getInt(1);
            }

            stmt2 = connection.prepareStatement("INSERT INTO magazines(magazine_id,issue_number,publisher) VALUES(?,?,?)");

            stmt2.setInt(1,generatedKey);
            stmt2.setInt(2,magazine.getIssue_number());
            stmt2.setString(3,magazine.getPublisher());


            int rowAffected1 = stmt2.executeUpdate();


        }catch (SQLException e){
            System.out.println("Error saving the magazine: " + e.getMessage());

        }finally {
            // close preparedStatement and connection
            try {
                if(stmt!=null) stmt.close();
                if(stmt2!=null) stmt2.close();
            } catch (SQLException e){
                System.out.println("Error closing the statement: " + e.getMessage());
            }

        }
    }

    @Override
    public void update(Magazine magazine) {

    }

    @Override
    public Magazine findById(int id) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            connection = ConnectionManager.getInstance().getConnection();

            stmt = connection.prepareStatement("SELECT * FROM magazines m INNER JOIN items i ON m.magazine_id = i.item_id WHERE m.magazine_id=?");

            stmt.setInt(1,id);

            rs = stmt.executeQuery();

            if(rs.next()){
                int itemId = rs.getInt("item_id");
                String itemTitle = rs.getString("title");
                int itemPublishedYear = rs.getInt("published_year");
                int itemUserId = rs.getInt("user_id");

                int magazineIssueNumber = rs.getInt("issue_number");
                String magazinePublisher = rs.getString("publisher");

                return new Magazine(itemId,itemTitle,itemPublishedYear,itemUserId,itemId,magazineIssueNumber,magazinePublisher);
            } else {
                System.out.println("Magazine not found with id: " + id);
                return null;
            }


        }catch (SQLException e){
            System.out.println("Error finding by id the magazine: " + e.getMessage());
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
    public List<Magazine> findAll() {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            connection = ConnectionManager.getInstance().getConnection();

            stmt = connection.prepareStatement("SELECT * FROM magazines m INNER JOIN items i ON m.magazine_id = i.item_id");

            rs = stmt.executeQuery();

            ArrayList<Magazine> magazines = new ArrayList<>();

            while(rs.next()){
                int itemId = rs.getInt("item_id");
                String itemTitle = rs.getString("title");
                int itemPublishedYear = rs.getInt("published_year");
                int itemUserId = rs.getInt("user_id");

                int magazineIssueNumber = rs.getInt("issue_number");
                String magazinePublisher = rs.getString("publisher");

                magazines.add(new Magazine(itemId,itemTitle,itemPublishedYear,itemUserId,itemId,magazineIssueNumber,magazinePublisher));
            }
            return magazines;


        }catch (SQLException e){
            System.out.println("Error finding by id the magazine: " + e.getMessage());
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
