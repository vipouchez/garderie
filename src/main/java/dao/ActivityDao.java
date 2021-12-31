
package dao;

import config.Config;
import models.Activity;
import models.Employee;
import models.Group;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class ActivityDao   {

    // ***************************
    // singleton design pattern

    // private constructor so that client code is forced to use the getInstance() method to create instance (new StudentDao() will not be accepted)
    private ActivityDao(){
    }

    // instance to return when any client asks for it.
    private static ActivityDao instance = new ActivityDao();

    // the only possible way for a client code to get the instance. (exp. see how StudentService asks for an instance of StudentDao using the getInstance())
    public static ActivityDao getInstance() {
        return instance;
    }
    // ***************************



    public List<Activity> findAll() throws Exception{
        List<Activity> result = new LinkedList<>();

        String sql = "SELECT * FROM activity";
        try(Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery(); // execute the database insert query

            while ( rs.next() == true){
                // create new empty activity entity
                Activity a = new Activity();
                a.setId(rs.getInt(1));
                a.setLabel(rs.getString(2));
                // then add the new created activity to the list of activities as follows:
                result.add(a);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    public Activity save(Activity a){

        String sql = "insert into activity (label) values (?) ";
        try (
                Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, a.getLabel());
            stmt.executeUpdate(); // execute the database insert query
            return a;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }





    public boolean deleteById(int id) {
        Activity a = findById(id);
        String sql = "DELETE FROM activity WHERE id = ? ";
        try (
                Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public Activity findById(int id){
        //todo
        return null;
    }

}
