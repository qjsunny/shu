package com.shu.db.dao.manager;

import com.shu.db.dao.BaseMapper;
import com.shu.db.model.manager.Manager;

public interface ManagerMapper extends BaseMapper {
    int deleteByPrimaryKey(String id);

    int insert(Manager record);

    int insertSelective(Manager record);

    Manager selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);
}