package zerobase.wifi.service;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// 삭제 요망
import zerobase.wifi.dto.WifiInfoDto;
import zerobase.wifi.model.JsonParser;


public class WifiApiComponent{

    /**
     * OpenAPI를 통해서 와이파이 정보의 JSON문자열을 리턴함
     */

    public WifiInfoDto getWipiInfoList(int start, int end) throws Exception {
        String request = null;
        JsonParser jsonParser = new JsonParser();

        try {
            String url = String.format("http://openapi.seoul.go.kr:8088/4c654242786b6a7736387961454964/json/TbPublicWifiInfo/%s/%s/", start, end);

            URL url1 = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            //System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
            BufferedReader rd;

            // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
            request = sb.toString();
            JsonParser parser = new JsonParser();

        } catch (Exception e) {
            System.out.println("openApi error" + e.getMessage());
        }
        return jsonParser.parse(request);
    }

    public int getTotalCount() throws Exception {
        WifiInfoDto dto = getWipiInfoList(0, 1);
        int totalCount = dto.getList_total_count();
        return totalCount;
    }
    public int getTotalPageCount() throws Exception {
        WifiInfoDto dto = getWipiInfoList(0, 1);
        int totalCount = dto.getList_total_count();


        int count = (totalCount / 1000);

        if ((totalCount % 1000) > 0) {
            count++;
        }

        return count;
    }
}

