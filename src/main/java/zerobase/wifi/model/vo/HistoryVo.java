package zerobase.wifi.model.vo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoryVo {
    private String id;
    private Double lat;
    private Double lnt;
    private String date;
}
