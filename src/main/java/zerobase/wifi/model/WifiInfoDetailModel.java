package zerobase.wifi.model;

//vo : 읽기 전용 값

import zerobase.wifi.dto.RowDto;
import zerobase.wifi.service.MariaConnection;
import zerobase.wifi.model.vo.WifiVo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WifiInfoDetailModel extends MariaConnection {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void saveAllWifiDetail(List<RowDto> lists) throws Exception {

        String sql = "insert into wifi_info(x_swifi_mgr_no, x_swifi_wrdofc, x_swifi_main_nm, \n" +
                "                 x_swifi_adres1, x_swifi_adres2, x_swifi_instl_floor, \n" +
                "                 x_swifi_instl_ty, x_swifi_instl_mby, x_swifi_svc_se, \n" +
                "                 x_swifi_cmcwr, x_swifi_cnstc_year, x_swifi_inout_door, \n" +
                "                 x_swifi_remars3, lat, lnt, work_dttm) \n" +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


        try {
            conn = getConnect();   // 디비연결

            conn.setAutoCommit(false);


            ps = conn.prepareStatement(sql);


            int basicBactchSize = 1000; // 배치사이즈는 500 /  밖에서 1000개씩 짜르기로했으니까 배치를 총 2번만들겠다.

            int count = 0;  // 배치가 가득찾는지 확인하는 용도.

            for (int i = 0; i < lists.size(); i++) {   // 처음에 0~ 999까지 돌면서

                RowDto infoDto = lists.get(i);    // 1개씩( 16개)  RowInfoDto 객체하나
                ps.setString(1, infoDto.getMgrNo());
                ps.setString(2, infoDto.getWrdofc());
                ps.setString(3, infoDto.getMainNm());
                ps.setString(4, infoDto.getAdres1());
                ps.setString(5, infoDto.getAdres2());
                ps.setString(6, infoDto.getFloor());
                ps.setString(7, infoDto.getTy());
                ps.setString(8, infoDto.getMby());
                ps.setString(9, infoDto.getSvcSe());
                ps.setString(10, infoDto.getCmcwr());
                ps.setString(11, infoDto.getYear());
                ps.setString(12, infoDto.getDoor());
                ps.setString(13, infoDto.getRemars3());
                ps.setString(14, infoDto.getLat());
                ps.setString(15, infoDto.getLnt());
                ps.setString(16, infoDto.getDttm());

                ps.addBatch(); // 배치에 1개넣어주고
                count++; // 카운트값을 1 증가시켜주고

                if (basicBactchSize == count) {    //500 == count
                    ps.executeBatch(); // 배치를 실행하고 총2개날릴거 일단 1개
                    ps.clearBatch(); // 배치 청소
                    count = 0; // 카운트 초기화시켜주고
                }
            }
        /*
         18003개있는데 얘가 19번 반복할떄
         18번동안 실행안되다가 배치가 500씩 / 2번 날리는건데
         500 = 3 이 안되므로 그 나머지 3개에 대한부분을 마지막 횟수에서 처리해준다.
         */

            ps.executeBatch();
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
            close(rs,ps,conn);
        }
    }

    public List<WifiVo> search(Double latDouble, Double lntDouble) {
        String selectNearWifiQuery = "select * " +
                ", format((6371 * acos(cos(radians(" + lntDouble + ")) * cos(radians(lat)) * cos(radians(lnt) - radians(" + latDouble + ")) " +
                "+ sin(radians(" + lntDouble + ")) * sin(radians(lat)))), 4) as distance " +
                " from wifi " +
                " order by distance , X_SWIFI_MGR_NO" +
                " limit 20";

        try {
            conn = getConnect();
            ps = conn.prepareStatement(selectNearWifiQuery);
            rs = ps.executeQuery(); // 20개씩


            List<WifiVo> list = new ArrayList<>();


            while (rs.next()) {
                WifiVo wifiVO = new WifiVo(
                        rs.getString("distance"),
                        rs.getString("X_SWIFI_MGR_NO"),
                        rs.getString("X_SWIFI_WRDOFC"),
                        rs.getString("X_SWIFI_MAIN_NM"),
                        rs.getString("X_SWIFI_ADRES1"),
                        rs.getString("X_SWIFI_ADRES2"),
                        rs.getString("X_SWIFI_INSTL_FLOOR"),
                        rs.getString("X_SWIFI_INSTL_TY"),
                        rs.getString("X_SWIFI_INSTL_MBY"),
                        rs.getString("X_SWIFI_SVC_SE"),
                        rs.getString("X_SWIFI_CMCWR"),
                        rs.getString("X_SWIFI_CNSTC_YEAR"),
                        rs.getString("X_SWIFI_INOUT_DOOR"),
                        rs.getString("X_SWIFI_REMARS3"),
                        rs.getString("LAT"),
                        rs.getString("LNT"),
                        rs.getString("WORK_DTTM")
                );
                list.add(wifiVO);
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs,ps,conn);
        }
    }

    // 데이터 자꾸쌓이니까 삭제해줄 쿼리
    public int removeAllData(){

        Connection con = null;
        PreparedStatement ps = null;

        String removeOneQuery = "delete from wifi_info ";


        try {
            con = getConnect();
            // select 뺴고
            // insert,update,delete 트랜젝션
            con.setAutoCommit(false); // 트랜젝션시작

            ps = con.prepareStatement(removeOneQuery);

            int resultCount = ps.executeUpdate();
            con.commit();
            return resultCount;

        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            close(rs, ps, con);
        }
    }
}


