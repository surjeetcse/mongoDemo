package com.mongoDemo.mongoDemo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;


public class MongoDatabaseManager {
    private static final Logger log = LoggerFactory.getLogger(MongoDatabaseManager.class);

    protected static final String MONGO_PROPERTIES_FILE = "mongo.properties";
    private static final String HOST = "MONGO_HOST";
    private static final String PORT = "MONGO_PORT";
    private static final String DATABASE = "MONGO_DB_NAME";
    private static final String USER = "MONGO_USER";
    private static final String PASSWD = "MONGO_PASSWD";

    private static final String PMS_HOST = "MONGO_PMS_HOST";
    private static final String PMS_PORT = "MONGO_PMS_PORT";
    private static final String PMS_DATABASE = "MONGO_PMS_DB_NAME";
    private static final String PMS_USER = "MONGO_PMS_USER";
    private static final String PMS_PASSWD = "MONGO_PMS_PASSWD";

    private static final String LOG_SERVICE = "MONGO_SERVICE_URL";

    public static String host;
    public static int port;
    public static String database;
    public static String username;
    public static String password;

    public static String pmsHost;
    public static int pmsPort;
    public static String pmsDatabase;
    public static String pmsUsername;
    public static String pmsPassword;
    public static String log_service_url;

   static  {
        log.info("" +"Initializing Redis Database Manager.");
        try {
            Properties prop = new Properties();
            prop.load(new MongoDatabaseManager().getClass().getClassLoader().getResourceAsStream(MONGO_PROPERTIES_FILE));
            host = prop.getProperty(HOST).trim();
            port = Integer.parseInt(prop.getProperty(PORT).trim());
            username = prop.getProperty(USER).trim();
            password = prop.getProperty(PASSWD).trim();
            database = prop.getProperty(DATABASE).trim();

            pmsHost = prop.getProperty(PMS_HOST).trim();
            pmsPort = Integer.parseInt(prop.getProperty(PMS_PORT).trim());
            pmsUsername = prop.getProperty(PMS_USER).trim();
            pmsPassword = prop.getProperty(PMS_PASSWD).trim();
            pmsDatabase = prop.getProperty(PMS_DATABASE).trim();

            log_service_url = prop.getProperty(LOG_SERVICE).trim();

        } catch (Exception e) {
            log.error("Encountered error", "FATAL: Error intializing Mongo Database Manager", e);
            throw new RuntimeException();
        }
    }

    public static String getHost() {
        return host;
    }

    public static void setHost(String host) {
        MongoDatabaseManager.host = host;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        MongoDatabaseManager.port = port;
    }

    public static String getDatabase() {
        return database;
    }

    public static void setDatabase(String database) {
        MongoDatabaseManager.database = database;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        MongoDatabaseManager.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        MongoDatabaseManager.password = password;
    }

    public static String getPmsHost() {
        return pmsHost;
    }

    public static void setPmsHost(String pmsHost) {
        MongoDatabaseManager.pmsHost = pmsHost;
    }

    public static int getPmsPort() {
        return pmsPort;
    }

    public static void setPmsPort(int pmsPort) {
        MongoDatabaseManager.pmsPort = pmsPort;
    }

    public static String getPmsDatabase() {
        return pmsDatabase;
    }

    public static void setPmsDatabase(String pmsDatabase) {
        MongoDatabaseManager.pmsDatabase = pmsDatabase;
    }

    public static String getPmsUsername() {
        return pmsUsername;
    }

    public static void setPmsUsername(String pmsUsername) {
        MongoDatabaseManager.pmsUsername = pmsUsername;
    }

    public static String getPmsPassword() {
        return pmsPassword;
    }

    public static void setPmsPassword(String pmsPassword) {
        MongoDatabaseManager.pmsPassword = pmsPassword;
    }

    public static String getLog_service_url() {
        return log_service_url;
    }

    public static void setLog_service_url(String log_service_url) {
        MongoDatabaseManager.log_service_url = log_service_url;
    }
}
