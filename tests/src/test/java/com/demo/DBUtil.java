package com.demo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    private static Properties props = new Properties();
    public static ThreadLocal<Connection> connectionhandler = new ThreadLocal<>();

    static {
        InputStream ips = DBUtil.class.getClassLoader().getResourceAsStream("util/db.properties");
        try {
            props.load(ips);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = connectionhandler.get();
        if (conn == null) {
            conn = getConn();
            connectionhandler.set(conn);
        }
        return conn;
    }

    private static Connection getConn() throws SQLException {
        Connection conn = null;
        try {
            Class.forName(props.getProperty("drivername"));
            conn = DriverManager.getConnection(
                    props.getProperty("url"), props.getProperty("username"), props.getProperty("pwd"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw e;
        }

        return conn;
    }

    public static void close() throws SQLException {
        Connection conn = connectionhandler.get();
        if (conn != null) {
            conn.close();
            connectionhandler.set(null);
        }
    }

    public static void close(ResultSet rst, PreparedStatement prep, Connection conn) {
        if (rst != null) {
            try {
                rst.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (prep != null) {
            try {
                prep.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(getConnection());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
