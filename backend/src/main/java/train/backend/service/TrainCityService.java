package train.backend.service;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import train.common.dao.TrainCityMapper;
import train.common.model.TrainCity;

import java.util.List;

@Service
public class TrainCityService {

  @Resource private TrainCityMapper trainCityMapper;

  public List<TrainCity> getAll() {
    return trainCityMapper.getAll();
  }
}
