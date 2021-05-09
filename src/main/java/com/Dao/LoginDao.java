package com.Dao;

import com.beans.User;
import com.db.Dbfactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Logger;


public class LoginDao {

    static Logger logger = Logger.getRootLogger();



    public User validateLogin(User user){
        logger.info("LoginDao.validateLogin entering ...");
        User user1 = null;
        try{
            Connection con= Dbfactory.getConnection();
            ResultSet rs;
            try (PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM reporting_system.users WHERE email = ? and password= ? " )) {
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPassword());

                rs = ps.executeQuery();

                while(rs.next()){
                    user1 = new User();
                    user1.setFullName(rs.getString("fullname"));
                    user1.setEmail(rs.getString("email"));
                    user1.setPassword(rs.getString("password"));
                    user1.setId(rs.getString("id"));
                }
            }
        }catch(Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return user1 ;
    }

}
