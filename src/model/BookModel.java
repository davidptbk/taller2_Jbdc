package model;

import database.CRUD;
import database.ConfigDB;
import entity.Book;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookModel implements CRUD {


    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Book objBook = (Book) obj;

        try{

            String sql = "INSERT INTO book (title,year_publication,price,id_autor) VALUES (?,?,?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objBook.getTitle());
            objPrepare.setString(2,objBook.getPublicationYear());
            objPrepare.setDouble(3,objBook.getPrice());
            objPrepare.setInt(4,objBook.getAutor());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objBook.setId(objResult.getInt(1));
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return objBook;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();

        List<Object> listBooks = new ArrayList<>();

        try{

            String sql = "SELECT * FROM book;";

            PreparedStatement objprepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objprepare.executeQuery();

            while (objResult.next()){
                Book objBook = new Book();

                objBook.setTitle(objResult.getString("title"));
                objBook.setPrice(objResult.getDouble("price"));
                objBook.setId(objResult.getInt("id"));
                objBook.setPublicationYear(objResult.getString("year_publication"));
                objBook.setIdAutor(objResult.getInt("id_autor"));

                listBooks.add(objBook);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return listBooks;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Book objBook = (Book) obj;

        try{

            String sql = "UPDATE book SET title =?, price=?,year_publication=? WHERE id =?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objBook.getTitle());
            objPrepare.setDouble(2,objBook.getPrice());
            objPrepare.setString(3,objBook.getPublicationYear());
            objPrepare.setInt(4,objBook.getId());

            int totalRowsAfected = objPrepare.executeUpdate();

            if (totalRowsAfected > 0){
                JOptionPane.showMessageDialog(null,"The update was successful");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Book objBook = (Book) obj;



        try{

            String sql = "DELETE FROM book WHERE id =?;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setInt(1,objBook.getId());

            int totalRowsAfected = objPrepared.executeUpdate();

            if (totalRowsAfected > 0){
                JOptionPane.showMessageDialog(null,"The update was successful");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            return false;
        }finally {
            ConfigDB.closeConnection();
        }

        return true;
    }

    public Book findByID(int id){

        Connection objConnection = ConfigDB.openConnection();

        Book objBooks = new Book();
        try{

            String slq = "SELECT * FROM book WHERE id=?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(slq);

            objPrepare.setInt(1,id);

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()){


                objBooks.setId(objResult.getInt("id"));
                objBooks.setTitle(objResult.getString("title"));
                objBooks.setPrice(objResult.getDouble("price"));
                objBooks.setPublicationYear(objResult.getString("year_publication"));
                objBooks.setIdAutor(objResult.getInt("id_autor"));
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return objBooks;
    }
}
