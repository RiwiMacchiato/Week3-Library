package library.dao;

import library.config.ConnectionManager;
import library.model.Book;
import library.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO{

    @Override
    public void save(Book book) {
        Connection connection = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet generatedKeys = null;
        try{
            //obtaining the connection from the ConnectionManager
            connection = ConnectionManager.getInstance().getConnection();

            //inserting first the item
            stmt = connection.prepareStatement("INSERT INTO items(title,published_year,user_id) VALUES(?,?,?)");


            //preparing the sql query for the item
            stmt.setString(1,book.getTitle());
            stmt.setInt(2,book.getPublishedYear());
            stmt.setInt(3,book.getUserId());

            int rowAffected = stmt.executeUpdate();


            generatedKeys = stmt.getGeneratedKeys();

            int generatedKey = 0;

            if(generatedKeys.next()){
                generatedKey = generatedKeys.getInt(1);
            }

            stmt2 = connection.prepareStatement("INSERT INTO books(book_id,author,isbn,pages) VALUES(?,?,?,?)");

            stmt2.setInt(1,generatedKey);
            stmt2.setString(2,book.getAuthor());
            stmt2.setString(3,book.getIsbn());
            stmt2.setInt(4,book.getPages());

            int rowAffected1 = stmt2.executeUpdate();


        }catch (SQLException e){
            System.out.println("Error saving the book: " + e.getMessage());

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
    public void update(Book book) {
        System.out.println("No implementation for now");
    }

    @Override
    public Book findById(int id) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            connection = ConnectionManager.getInstance().getConnection();

            stmt = connection.prepareStatement("SELECT * FROM books b INNER JOIN items i ON b.book_id = i.item_id WHERE b.book_id=?");

            stmt.setInt(1,id);

            rs = stmt.executeQuery();

            if(rs.next()){
                int itemId = rs.getInt("item_id");
                String itemTitle = rs.getString("title");
                int itemPublishedYear = rs.getInt("published_year");
                int itemUserId = rs.getInt("user_id");

                String bookAuthor = rs.getString("author");
                String bookISBN = rs.getString("isbn");
                int bookPages = rs.getInt("pages");

                return new Book(itemId,itemTitle,itemPublishedYear,itemUserId,itemId,bookAuthor,bookISBN,bookPages);
            } else {
                System.out.println("Book not found with id: " + id);
                return null;
            }


        }catch (SQLException e){
            System.out.println("Error finding by id the book: " + e.getMessage());
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
    public List<Book> findAll() {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            connection = ConnectionManager.getInstance().getConnection();

            stmt = connection.prepareStatement("SELECT * FROM books b INNER JOIN items i ON b.book_id = i.item_id");


            rs = stmt.executeQuery();

            ArrayList<Book> books = new ArrayList<>();

            while(rs.next()){
                int itemId = rs.getInt("item_id");
                String itemTitle = rs.getString("title");
                int itemPublishedYear = rs.getInt("published_year");
                int itemUserId = rs.getInt("user_id");

                String bookAuthor = rs.getString("author");
                String bookISBN = rs.getString("isbn");
                int bookPages = rs.getInt("pages");

                books.add(new Book(itemId,itemTitle,itemPublishedYear,itemUserId,itemId,bookAuthor,bookISBN,bookPages));
            }

            return books;

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
}
