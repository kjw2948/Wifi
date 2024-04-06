package zerobase.wifi.service;

import java.sql.*;

public class MariaConnection {
    //1. ip(domain) 2. port 3. 계정 4. 패스워드 5. 인스턴스
    public Connection getConnect() {
        String url = "jdbc:mariadb://localhost:3306/wifi_db";
        String dbUserId = "wifi";
        String dbPassword = "zerobase";

        //1. 드라이버 로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return connection;
    }

    public void close(ResultSet rs, Statement Statement, Connection connection) {

        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (Statement != null && !Statement.isClosed()) {
                Statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
