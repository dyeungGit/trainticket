package train.common.db;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import java.util.Collection;

public class TrainSeatDatabaseShardingAlgorithm implements PreciseShardingAlgorithm<Integer> {
  private static final String PREFIX = "trainSeatDB";

  private String determineDB(int val) {
    int db = val % 5;
    if (db == 0) {
      db = 5;
    }
    return PREFIX + db;
  }

  @Override
  public String doSharding(
      Collection<String> availableTargetNames, PreciseShardingValue<Integer> shardingValue) {
    String actualDbName = determineDB(shardingValue.getValue());
    if (availableTargetNames.contains(actualDbName)) {
      return actualDbName;
    }
    throw new IllegalArgumentException();
  }
}
