package train.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainNumberDetail {
  private Integer id;

  private Integer trainNumberId;

  private Integer fromStationId;

  private Integer fromCityId;

  private Integer toStationId;

  private Integer toCityId;

  private Integer stationIndex;

  private Integer relativeMinute;

  private Integer waitMinute;

  private String money;

  public String getMoney() {
    return money;
  }

  public void setMoney(String money) {
    this.money = money == null ? null : money.trim();
  }
}
