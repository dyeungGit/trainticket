package train.backend.service;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;
import train.common.dao.TrainNumberDetailMapper;
import train.common.model.TrainNumberDetail;

@Service
public class TrainNumberDetailService {

  @Resource private TrainNumberDetailMapper trainNumberDetailMapper;

  public List<TrainNumberDetail> getAll() {
    return trainNumberDetailMapper.getAll();
  }
}
