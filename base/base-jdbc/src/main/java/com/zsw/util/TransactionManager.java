package com.zsw.util;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    public static void begin() throws SQLException {
        try {
            Connection conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void commit() throws SQLException {
        try {
            Connection conn = DBUtil.getConnection();
            conn.commit();
            DBUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void rollback() throws SQLException {
        try {
            Connection conn = DBUtil.getConnection();
            conn.rollback();
            DBUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
