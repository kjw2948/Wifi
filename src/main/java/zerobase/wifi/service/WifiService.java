package zerobase.wifi.service;


import zerobase.wifi.dto.RowDto;
import zerobase.wifi.dto.WifiInfoDto;
import zerobase.wifi.model.WifiInfoDetailModel;
import zerobase.wifi.model.WifiInfoModel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.xml.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


public class WifiService extends HttpServlet{
     Connection conn = null;
     Statement statement = null;
     ResultSet rs = null;
     List<RowDto> list = new ArrayList();


     //와이파이 정보를 저장

    private boolean add(RowDto model) {
        int size = list.size();
        list.add(model);
        if (size == list.size()) {
            return false;
        } else {
            return true;
        }
    }


      //와이파이 정보의 목록을 리턴

    public List<RowDto> getList(WifiInfoModel parameter) {

        throw new RuntimeException("구현해 주세요.");
    }


     //오픈API에서 와이파이 정보를 가져오고,
     //그 내용을 데이터베이스에 저장하고,
     //최종적으로 저장한 개수를 리턴

    public int saveWifiInfo () throws RuntimeException, Exception{
        WifiApiComponent wac = new WifiApiComponent();
        WifiInfoDetailModel wifiInfoDetailModel = new WifiInfoDetailModel();
        WifiInfoDto dto = wac.getWipiInfoList(0,1);
        int totalCount = dto.getList_total_count();
        System.out.println("dto = " + dto);
            return totalCount;
        }


     //와이파이 상세 정보 리턴

    public WifiInfoDto getDetail(WifiInfoDetailModel parameter) {

        throw new RuntimeException("구현해 주세요.");
    }
}
