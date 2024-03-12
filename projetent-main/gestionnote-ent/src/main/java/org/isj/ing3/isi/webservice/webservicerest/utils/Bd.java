package org.isj.ing3.isi.webservice.webservicerest.utils;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Bd {

    static DriverManagerDataSource driverManager = new DriverManagerDataSource();
    static Connection connection=null;

    public static Connection getConnection() throws SQLException {

        if(connection==null) {
            String url = ParametresConnection.getURL();
            String username = ParametresConnection.getDatabaseUsername();
            String pwd = ParametresConnection.getDatabasePassword();
            driverManager.setUrl(url);
            connection = driverManager.getConnection(username, pwd);
        }
        return connection;
    }


}
