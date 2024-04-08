package zerobase.wifi.model;

import com.google.gson.Gson;
import zerobase.wifi.dto.FirstDto;
import zerobase.wifi.dto.WifiInfoDto;
public class JsonParser {
    private Gson gson = new Gson();

    public WifiInfoDto parse(String json) {
        try {
            FirstDto firstDto = gson.fromJson(json, FirstDto.class);
            return firstDto.getTbPublicWifiInfo();
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
