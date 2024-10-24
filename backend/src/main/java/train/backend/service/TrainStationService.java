package train.backend.service;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import train.common.dao.TrainStationMapper;
import train.common.model.TrainStation;

import java.util.List;

@Service
public class TrainStationService {

  @Resource private TrainStationMapper trainStationMapper;

  public List<TrainStation> getAll() {
    return trainStationMapper.getAll();
  }
}
