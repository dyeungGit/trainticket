package train.common.dao;

import train.common.model.TrainTraveller;

public interface TrainTravellerMapper {
  int deleteByPrimaryKey(Long id);

  int insert(TrainTraveller record);

  int insertSelective(TrainTraveller record);

  TrainTraveller selectByPrimaryKey(Long id);

  int updateByPrimaryKeySelective(TrainTraveller record);

  int updateByPrimaryKey(TrainTraveller record);
}
