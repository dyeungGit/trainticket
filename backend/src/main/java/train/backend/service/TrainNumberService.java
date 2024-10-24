package train.backend.service;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import train.common.dao.TrainNumberMapper;
import train.common.model.TrainNumber;

import java.util.List;

@Service
public class TrainNumberService {

  @Resource private TrainNumberMapper trainNumberMapper;

  public List<TrainNumber> getAll() {
    return trainNumberMapper.getAll();
  }
}
