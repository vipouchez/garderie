package dao;


import config.Config;
import error.NotFoundException;
import models.*;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class AssignementDao {
    // ***************************
    // singleton design pattern

    // private constructor so that client code is forced to use the getInstance() method to create instance (new StudentDao() will not be accepted)
    private AssignementDao(){
    }

    // instance to return when any client asks for it.
    private static AssignementDao instance = new AssignementDao();

    // the only possible way for a client code to get the instance. (exp. see how StudentService asks for an instance of StudentDao using the getInstance())
    public static AssignementDao getInstance() {
        return instance;
    }
    // ***************************



    public List<Assignement> findAll() throws Exception{
        List<Assignement> result = new LinkedList<>();

        String sql = "SELECT * FROM assignements ";
        try(Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery(); // execute the database insert query

            while ( rs.next() == true){
                // create new empty activity entity
                Group group = GroupDao.getInstance().findById(rs.getString(2));
                Activity activity = ActivityDao.getInstance().findById(rs.getInt(3));
                Employee employee = EmployeeDao.getInstance().findById(rs.getInt(4));

                Assignement a = new Assignement();
                a.setGroup(group);
                a.setActivity(activity);
                a.setEmployee(employee);
                result.add(a);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    public Assignement save(Assignement a){

        String sql = "insert into assignements (group_id, activity_id, employee_id) values (?,?,?) ";
        try (
                Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, a.getGroup().getName());
            stmt.setInt(2, a.getActivity().getId());
            stmt.setInt(3, a.getEmployee().getId());
            stmt.executeUpdate(); // execute the database insert query
            return a;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean deleteById(int id) {
        String sql = "DELETE FROM assignements WHERE id = ? ";
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




    public Assignement findById(int id) {
        Assignement a = new Assignement();
        String sql = "SELECT * FROM assignements  where id = ? ";
        try (
                Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            boolean foundSomething = rs.next();
            if (!foundSomething) {
                throw new NotFoundException();
            }

            Group group = GroupDao.getInstance().findById(rs.getString(2));
            Activity activity = ActivityDao.getInstance().findById(rs.getInt(3));
            Employee employee = EmployeeDao.getInstance().findById(rs.getInt(4));

            a.setGroup(group);
            a.setActivity(activity);
            a.setEmployee(employee);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return a;
    }


}
