package org.isj.ing3.isi.webservice.webservicerest.utils;

public class ParametresConnection {

    public static boolean connexionDistante=false;

    //public static final String serverDatabaseAddress="containers-us-west-147.railway.app";
    public static final String serverDatabaseAddress="localhost";
    public static final String serverDatabaseName="anciendb";
    public static final String serverDatabaseUsername="root";
    public static final String serverDatabasePassword="amizoneng";
    public static final String serverDatabaseConnectionOptions="?serverTimezone=UTC";
    public static final int serverDatabasePort=3306;

    /*public static final String clientDatabaseAddress="containers-us-west-147.railway.app";
    public static final String clientDatabaseName="anciendb";
    public static final String clientDatabaseUsername="root";
    public static final String clientDatabasePassword="5WvRDzl0TtFYZ0y72Gjr";
    public static final String clientDatabaseConnectionOptions="?serverTimezone=UTC";
    public static final int clientDatabasePort=6280;*/
    
    
    public static final String clientDatabaseAddress="localhost";
    public static final String clientDatabaseName="isjgestionnote";
    public static final String clientDatabaseUsername="root";
    public static final String clientDatabasePassword="";
    public static final String clientDatabaseConnectionOptions="?serverTimezone=UTC";
    public static final int clientDatabasePort=3306;

    public static boolean isConnexionDistante() {
        return connexionDistante;
    }

    public static String getDatabaseAddress() {
        return connexionDistante?clientDatabaseAddress:clientDatabaseAddress;
    }

    public static String getDatabaseName() {
        return connexionDistante?clientDatabaseName:clientDatabaseName;
    }

    public static String getDatabaseUsername() {
        return clientDatabaseUsername;
    }

    public static String getDatabasePassword() {
        return clientDatabasePassword;
    }
    public static int getDatabasePort() {
        return connexionDistante?serverDatabasePort:serverDatabasePort;
    }

    public static  String getDatabaseConnectionOptions() {
        return connexionDistante?serverDatabaseConnectionOptions:serverDatabaseConnectionOptions;
    }

    public static String getURL() {
        return "jdbc:mysql://"+getDatabaseAddress()+":"+getDatabasePort()+"/"+getDatabaseName()+"?zeroDateTimeBehavior=convertToNull&autoReconnect=true&serverTimezone=UTC";
    }
    public static String getURLLocal() {
        return "jdbc:mysql://"+clientDatabaseAddress+":"+clientDatabasePort+"/"+clientDatabaseName+"?zeroDateTimeBehavior=convertToNull&autoReconnect=true&serverTimezone=UTC";
    }
}
