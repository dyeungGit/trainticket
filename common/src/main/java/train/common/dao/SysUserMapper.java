package train.common.dao;

import train.common.model.SysUser;

public interface SysUserMapper {
  int deleteByPrimaryKey(Integer id);

  int insert(SysUser record);

  int insertSelective(SysUser record);

  SysUser selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(SysUser record);

  int updateByPrimaryKey(SysUser record);
}
