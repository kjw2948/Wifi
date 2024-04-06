package step.database;


import zerobase.wifi.service.MariaConnection;

import java.sql.*;

public class DataBaseConnection extends MariaConnection {

    /**
     * 데이터 베이스 연결 테스트
     * sqlite에서 현재 시간을 가져오는 쿼리를 실행해 본다.
     * select date(), time();
     */
    public static void test() {
        MariaConnection mariaConnection = new MariaConnection();
        Connection connection  = mariaConnection.getConnect();
        try {
            //3. 스테이트먼트 객체 생성
            Statement statement = connection.createStatement();

            String sql = "select *\n" +
                    "from wifi_info";

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                double distance = rs.getDouble("거리");
                String operate_no = rs.getString("관리번호");
                String gugu = rs.getString("자치구");

                System.out.println(distance +" ," + operate_no + " , " + gugu);
            }
            mariaConnection.close(rs,statement,connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
