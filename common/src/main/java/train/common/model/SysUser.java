package train.common.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysUser {
  private Integer id;

  private String username;

  private String telephone;

  private String mail;

  private String password;

  private Integer deptId;

  private Integer status;

  private String remark;

  private String operator;

  private Date operateTime;

  private String operateIp;
}
