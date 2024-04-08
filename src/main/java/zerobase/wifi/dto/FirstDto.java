package zerobase.wifi.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class FirstDto {
    @SerializedName("TbPublicWifiInfo")
    private WifiInfoDto tbPublicWifiInfo;
}
