package com.test;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.test.util.BaseException;

/**
 * User: Nikitos
 * Date: 07.11.2009
 * Time: 22:45:28
 * Manages connection to database.
 * //todo use connection pool
 */
public class DbManager {
    private static Logger log = Logger.getLogger(DbManager.class);
    private static Connection con;

    public static void init() {
        try {
            log.debug("init connection...");
            Properties p = new Properties();
            java.io.InputStream is;

            is = DbManager.class.getResourceAsStream("/config.properties");
            if (is == null)
                throw new BaseException("Properties file not found");
            p.load(is);
            is.close();

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = String.format(
                    "jdbc:mysql://%s/%s?user=%s&password=%s",
                    p.getProperty("db.host"),
                    p.getProperty("db.name"),
                    p.getProperty("db.user"),
                    p.getProperty("db.password")
            );
            log.debug("url: " + url);
            con = DriverManager.getConnection(url);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public static void destroy() {
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return con;
    }
}
