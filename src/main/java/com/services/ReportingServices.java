package com.services;

import com.beans.User;
import com.beans.WorkingHour;
import com.db.Dbfactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ReportingServices {

    static Logger logger = Logger.getRootLogger();


    public static boolean addUser(User user) throws SQLException {

        logger.info("ReportingServices.addUser entering ... ");

        Connection con = Dbfactory.getConnection();
        try
        {
            Statement st = con.createStatement();
            String sql = ("INSERT INTO reporting_system.users VALUES ( ? , ? , ? , ? ); ");
            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setString (1, user.getId());
            preparedStmt.setString (2,  user.getFullName());
            preparedStmt.setString (3, user.getPassword());
            preparedStmt.setString (4, user.getEmail());
            preparedStmt.execute();
        }
        catch(Exception e)
        {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        finally {
            con.close();
        }
        return true;
    }


    private static Timestamp getCurrentTimeStamp()
    {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp;
    }




    public static boolean cardStamping(User user, String action) throws SQLException {
        boolean isCardStampedOK = true;
        Connection con = Dbfactory.getConnection();
         try
        {
            Statement st = con.createStatement();
            String sql = ("INSERT INTO reporting_system.work_hours VALUES ( ? , ? , ? , ? ); ");
            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setInt(1, 0);
            preparedStmt.setTimestamp (2, "enter".equals(action) ?  getCurrentTimeStamp() : null);//entrance field
            preparedStmt.setTimestamp   (3, "exit".equals(action) ? getCurrentTimeStamp()  : null );//exit field
            preparedStmt.setString(4, user.getFullName());
            preparedStmt.execute();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {
            con.close();
        }
        return isCardStampedOK;
    }



    public static boolean getById(String id) throws SQLException {
        Connection con = Dbfactory.getConnection();
        try
        {
            Statement st = con.createStatement();
            String sql = ("SELECT * FROM REPORTING_SYSTEM.WORK_HOURS WHERE ID = ? ; ");
            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setString(1, id );
            preparedStmt.execute();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {
            con.close();
        }
        return false;
    }

    public static List<WorkingHour> fetchAllWorkingHoursByUser(User user) throws SQLException {
        Connection con = Dbfactory.getConnection();
        List<WorkingHour> list = new ArrayList<WorkingHour>();
        try
        {
            Statement st = con.createStatement();
            String sql = (" SELECT * FROM reporting_system.work_hours ;");
             ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                String userName = (String)rs.getString("user");
                if(user.getFullName().contains(userName))
                {
                    WorkingHour bean = new WorkingHour();
                    bean.setIdentity(rs.getInt("id"));
                    bean.setEnterance(rs.getTimestamp("enter"));
                    bean.setExit(rs.getTimestamp("exit"));
                    bean.setUser(rs.getString("user"));
                    list.add(bean);
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {
            con.close();
        }
        return list;
    }

    public static List<User> fetchAllUsers() throws SQLException {
        Connection con = Dbfactory.getConnection();
        List<User> list = new ArrayList<User>();
        try
        {
            Statement st = con.createStatement();
            String sql = (" SELECT * FROM reporting_system.users ;");
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                User bean = new User();
                bean.setId(rs.getString("id"));
                bean.setFullName(rs.getString("fullname"));
                bean.setPassword(rs.getString("password"));
                bean.setEmail(rs.getString("email"));
                list.add(bean);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {
            con.close();
        }
        return list;
    }


    public static boolean update(WorkingHour bean) throws SQLException {
        Connection con = Dbfactory.getConnection();
        try
        {
            Statement st = con.createStatement();
            String sql = ("UPDATE REPORTING_SYSTEM.WORK_HOURS SET entrance = ? ,exit = ? , user = ?  where id = ?");
            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setDate (1, (Date) bean.getEnterance());
            preparedStmt.setDate   (2, (Date) bean.getExit());
            preparedStmt.setString(3, bean.getUser());
            preparedStmt.setInt (4, bean.getIdentity());
            preparedStmt.execute();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {
            con.close();
        }
        return false;
    }

    public static boolean delete(String id) throws SQLException {
        Connection con = Dbfactory.getConnection();
        try
        {
            Statement st = con.createStatement();
            String sql = ("DELETE * FROM REPORTING_SYSTEM.WORK_HOURS WHERE ID = ? ; ");
            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setString(1, id );
            preparedStmt.execute();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {
            con.close();
        }
        return false;
    }


    public static boolean deleteUser(String id) throws SQLException {
        Connection con = Dbfactory.getConnection();
        try
        {
            Statement st = con.createStatement();
            String sql = ("DELETE FROM REPORTING_SYSTEM.USERS WHERE ID = ? ; ");
            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setString(1, id );
            preparedStmt.execute();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {
            con.close();
        }
        return false;
    }


}
