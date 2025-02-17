package train.common.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainOrder {
  private Long id;

  private String orderId;

  private String ticket;

  private Long userId;

  private Integer trainNumberId;

  private Integer fromStationId;

  private Integer toStationId;

  private Date trainStart;

  private Date trainEnd;

  private Integer totalMoney;

  private Integer status;

  private Integer refundStatus;

  private Date createTime;

  private Date expireTime;

  private Date updateTime;
}
