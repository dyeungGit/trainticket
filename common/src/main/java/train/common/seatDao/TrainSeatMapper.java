package train.common.seatDao;

import train.common.model.TrainSeat;

public interface TrainSeatMapper {
  int deleteByPrimaryKey(Long id);

  int insert(TrainSeat record);

  int insertSelective(TrainSeat record);

  TrainSeat selectByPrimaryKey(Long id);

  int updateByPrimaryKeySelective(TrainSeat record);

  int updateByPrimaryKey(TrainSeat record);
}
