package train.common.db;

import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
import io.shardingsphere.api.config.rule.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(
    basePackages = "train.common.seatDao",
    sqlSessionTemplateRef = "trainSeatSqlSessionTemplate")
public class SeatDataSourceConfig {

  @Bean(name = DataSources.TRAIN_SEAT_DB_1)
  @ConfigurationProperties(prefix = "spring.datasource.seat1")
  public DataSource trainSeatDB1() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = DataSources.TRAIN_SEAT_DB_2)
  @ConfigurationProperties(prefix = "spring.datasource.seat2")
  public DataSource trainSeatDB2() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = DataSources.TRAIN_SEAT_DB_3)
  @ConfigurationProperties(prefix = "spring.datasource.seat3")
  public DataSource trainSeatDB3() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = DataSources.TRAIN_SEAT_DB_4)
  @ConfigurationProperties(prefix = "spring.datasource.seat4")
  public DataSource trainSeatDB4() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = DataSources.TRAIN_SEAT_DB_5)
  @ConfigurationProperties(prefix = "spring.datasource.seat5")
  public DataSource trainSeatDB5() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "trainSeatShardingDataSource")
  public DataSource trainSeatShardingDataSource(
      @Qualifier(DataSources.TRAIN_SEAT_DB_1) DataSource trainSeatDB1,
      @Qualifier(DataSources.TRAIN_SEAT_DB_2) DataSource trainSeatDB2,
      @Qualifier(DataSources.TRAIN_SEAT_DB_3) DataSource trainSeatDB3,
      @Qualifier(DataSources.TRAIN_SEAT_DB_4) DataSource trainSeatDB4,
      @Qualifier(DataSources.TRAIN_SEAT_DB_5) DataSource trainSeatDB5)
      throws SQLException {
    ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();

    //    sharding data source reflection
    Map<String, DataSource> dataSourceMap =
        Map.of(
            DataSources.TRAIN_SEAT_DB_1,
            trainSeatDB1,
            DataSources.TRAIN_SEAT_DB_2,
            trainSeatDB2,
            DataSources.TRAIN_SEAT_DB_3,
            trainSeatDB3,
            DataSources.TRAIN_SEAT_DB_4,
            trainSeatDB4,
            DataSources.TRAIN_SEAT_DB_5,
            trainSeatDB5);

    //    table rule
    TableRuleConfiguration tableRuleConfiguration = new TableRuleConfiguration();
    //    TODO 1|6 DB1, 2|7 DB2, 3|8 DB3, 4|9 DB4, 5|10 DB5
    tableRuleConfiguration.setLogicTable("train_seat");
    tableRuleConfiguration.setActualDataNodes(
        DataSources.TRAIN_SEAT_DB_1
            + ".train_seat_1,"
            + DataSources.TRAIN_SEAT_DB_2
            + ".train_seat_2,"
            + DataSources.TRAIN_SEAT_DB_3
            + ".train_seat_3,"
            + DataSources.TRAIN_SEAT_DB_4
            + ".train_seat_4,"
            + DataSources.TRAIN_SEAT_DB_5
            + ".train_seat_5,"
            + DataSources.TRAIN_SEAT_DB_1
            + ".train_seat_6,"
            + DataSources.TRAIN_SEAT_DB_2
            + ".train_seat_7,"
            + DataSources.TRAIN_SEAT_DB_3
            + ".train_seat_8,"
            + DataSources.TRAIN_SEAT_DB_4
            + ".train_seat_9,"
            + DataSources.TRAIN_SEAT_DB_5
            + ".train_seat_10,");
    //    db sharding strategy
    tableRuleConfiguration.setDatabaseShardingStrategyConfig(
        new StandardShardingStrategyConfiguration(
            "train_number_id", new TrainSeatDatabaseShardingAlgorithm()));
    //    table sharding strategy
    tableRuleConfiguration.setTableShardingStrategyConfig(
        new StandardShardingStrategyConfiguration(
            "train_number_id", new TrainSeatTableShardingAlgorithm()));
    shardingRuleConfiguration.getTableRuleConfigs().add(tableRuleConfiguration);
    return ShardingDataSourceFactory.createDataSource(
        dataSourceMap, shardingRuleConfiguration, new ConcurrentHashMap<>(), new Properties());
  }

  @Bean("trainSeatShardingDataSource")
  public DataSourceTransactionManager dataSourceTransactionManager(
      @Qualifier("trainSeatShardingDataSource") DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean("trainSeatSqlSessionFactory")
  public SqlSessionFactory trainSeatSqlSessionFactory(
      @Qualifier("trainSeatShardingDataSource") DataSource dataSource) throws Exception {
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    bean.setDataSource(dataSource);
    bean.setMapperLocations(
        new PathMatchingResourcePatternResolver().getResources("classpath:seatMappers/*.xml"));
    return bean.getObject();
  }

  @Bean(name = "trainSeatSqlSessionTemplate")
  public SqlSessionTemplate trainSeatSqlSessionTemplate(
      @Qualifier("trainSeatSqlSessionFactory") SqlSessionFactory trainSeatSqlSessionFactory) {
    return new SqlSessionTemplate(trainSeatSqlSessionFactory);
  }
}
