package zerobase.wifi.service;

import java.sql.*;

public class MariaConnection {
    //1. ip(domain) 2. port 3. 계정 4. 패스워드 5. 인스턴스

    public static void main(String[] args){
        String url = "jdbc:mariadb://172.30.1.93:3306/wifi_db";
        String dbUserId = "wifi";
        String dbPassword = "zerobase";

        //1. 드라이버 로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //2. 커넥션 객체 생성
        try {
            Connection connection = DriverManager.getConnection(url, dbUserId, dbPassword);
            //3. 스테이트먼트 객체 생성
            Statement statement = connection.createStatement();

            String sql = "select *\n" +
                    "from wifi_info";

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                String location = rs.getString("location");

                System.out.println(name +" ," + location);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //4. 쿼리 실행
        //5. 결과 수행
        //6. 객체 연결 해제 (close)
    }
}
