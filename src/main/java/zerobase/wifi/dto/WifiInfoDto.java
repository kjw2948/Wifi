package zerobase.wifi.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class WifiInfoDto {
    @SerializedName("list_total_count")
    private int list_total_count;

    @SerializedName("RESULT")
    private ResultDto result;

    @SerializedName("row")
    private List<RowDto> wifiDetails;
}


