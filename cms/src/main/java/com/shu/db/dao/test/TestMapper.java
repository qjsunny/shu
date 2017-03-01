package com.shu.db.dao.test;

import com.shu.db.dao.BaseMapper;
import com.shu.db.model.test.Test;

public interface TestMapper extends BaseMapper {
    int deleteByPrimaryKey(String id);

    int insert(Test record);

    int insertSelective(Test record);

    Test selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKey(Test record);
}