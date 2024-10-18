package train.common.dao;

import train.common.model.TrainStation;

public interface TrainStationMapper {
  int deleteByPrimaryKey(Integer id);

  int insert(TrainStation record);

  int insertSelective(TrainStation record);

  TrainStation selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(TrainStation record);

  int updateByPrimaryKey(TrainStation record);
}
