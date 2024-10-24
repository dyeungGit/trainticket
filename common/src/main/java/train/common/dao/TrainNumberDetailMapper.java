package train.common.dao;

import java.util.List;
import train.common.model.TrainNumberDetail;

public interface TrainNumberDetailMapper {
  int deleteByPrimaryKey(Integer id);

  int insert(TrainNumberDetail record);

  int insertSelective(TrainNumberDetail record);

  TrainNumberDetail selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(TrainNumberDetail record);

  int updateByPrimaryKey(TrainNumberDetail record);

  List<TrainNumberDetail> getAll();
}
