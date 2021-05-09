package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.log4j.*;

public class Dbfactory {

    static final Logger logger =  Logger.getRootLogger();

    public static Connection getConnection(){
        logger.info("Dbfactory.getConnection Method entering ... ");
        Connection connection = null;
        try {
            String dbDriver = "com.mysql.cj.jdbc.Driver";
            String dbURL = "jdbc:mysql://localhost:3306/";
            String dbName = "reporting_system";
            String timeZone ="?serverTimezone=UTC&useSSL=false";
            String dbUsername = "root";
            String dbPassword = "1234";
            Class.forName(dbDriver);
            connection = DriverManager.getConnection(dbURL + dbName + timeZone, dbUsername, dbPassword);
        }
        catch(Exception e)
        {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        logger.info("Dbfactory.getConnection successfull getConnection Method exiting  ... ");
        return connection;
    }

}
