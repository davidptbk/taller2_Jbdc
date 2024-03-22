package model;

import database.CRUD;
import database.ConfigDB;
import entity.Autor;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Autor objAutor = (Autor) obj;
        try {
            String sql = "INSERT INTO autor (name,nationality) VALUES (?,?) ;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objAutor.getName());
            objPrepare.setString(2, objAutor.getNationality());

            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objAutor.setId(objResult.getInt("id"));
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return objAutor;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listAutors = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try{

            String sql = "SELECT * FROM autor;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){

                Autor objAutor = new Autor();

                objAutor.setId(objResult.getInt("id"));
                objAutor.setName(objResult.getString("name"));
                objAutor.setNationality(objResult.getString("nationality"));

                listAutors.add(objAutor);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        ConfigDB.closeConnection();
        return listAutors;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Autor objAutor = (Autor) obj;

        try{

            String sql = "UPDATE autor SET name=?,nationality=? WHERE id=?;";

            PreparedStatement objprepare = objConnection.prepareStatement(sql);

            objprepare.setString(1,objAutor.getName());
            objprepare.setString(2,objAutor.getNationality());
            objprepare.setInt(3,objAutor.getId());

            int totalRowsAfected = objprepare.executeUpdate();


            if (totalRowsAfected > 0){
                JOptionPane.showMessageDialog(null,"The update was successful" +objAutor.toString());
            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            return false;
        }finally {
            ConfigDB.closeConnection();
        }
        return true;
    }

    @Override
    public boolean delete(Object obj) {
        Autor objAutor = (Autor) obj;

        Connection objConnection = ConfigDB.openConnection();

        boolean isDelete = false;

        try{

            String sql = "DELETE * FROM autor WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objAutor.getId());

            int totalAfecteds = objPrepare.executeUpdate();

            if (totalAfecteds > 0 ){
                JOptionPane.showMessageDialog(null,"The update was successful");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDelete;
    }

    public Autor findById (int id){

        Connection objConnection = ConfigDB.openConnection();

        Autor objAutor = null;

        try{

            String sql = "SELECT * FROM autor WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,id);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()){
                objAutor = new Autor();
                objAutor.setNationality(objResult.getString("nationality"));
                objAutor.setName(objResult.getString("name"));
                objAutor.setId(objResult.getInt("id"));
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return objAutor;
    }

}
