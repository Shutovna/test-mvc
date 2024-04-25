package com.test.dao;

import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.util.BaseException;
import com.test.bean.User;
import com.test.DbManager;

/**
 * User: Nikitos
 * Date: 10.11.2009
 * Time: 1:06:24
 * Database operation for user objects
 */
public class UserDao {
    private static Logger log = Logger.getLogger(UserDao.class);

    public List<User> getUsers() throws BaseException {
        List<User> result = new ArrayList<User>();
        Statement st = null;
        try {
            st = DbManager.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT user_id, login, password, name, surname from user");

            while (rs.next()) {
                User u = new User(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                result.add(u);
            }

        } catch (SQLException e) {
            throw new BaseException("Error receiving users", e);

        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    /*ignore*/
                }
            }
        }
        log.debug("return " + result.size() + " users");
        return result;
    }


    public User getUser(String login, String password) throws BaseException {
        log.debug("getUser, login: " + login + ", pwd: " + password);
        User result = null;
        PreparedStatement st = null;
        try {
            st = DbManager.getConnection().prepareStatement(
                    "SELECT user_id, login, password, name, surname from user where login=? and password=?;"
            );
            st.setString(1, login);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result = new User(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            }

        } catch (SQLException e) {
            log.error("Error receiving user", e);

        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    /*ignore*/
                }
            }
        }
        return result;
    }

    public void create(String login, String password, String name, String surname) throws BaseException {
        PreparedStatement st = null;
        try {
            st = DbManager.getConnection().prepareStatement(
                    "INSERT INTO user(login, password, name, surname) VALUES(?,?,?,?);"
            );
            st.setString(1, login);
            st.setString(2, password);
            st.setString(3, name);
            st.setString(4, surname);

            st.executeUpdate();

        } catch (SQLException e) {
            throw new BaseException("Error creating user", e);

        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    /*ignore*/
                }
            }
        }
    }
}
