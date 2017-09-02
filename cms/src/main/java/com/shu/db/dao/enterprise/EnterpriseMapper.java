package com.shu.db.dao.enterprise;

import com.shu.db.dao.BaseMapper;
import com.shu.db.model.enterprise.Enterprise;

public interface EnterpriseMapper extends BaseMapper{
    int deleteByPrimaryKey(String id);

    int insert(Enterprise record);

    int insertSelective(Enterprise record);

    Enterprise selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Enterprise record);

    int updateByPrimaryKey(Enterprise record);
}