package com.shu.db.dao.message;

import com.shu.db.dao.BaseMapper;
import com.shu.db.model.message.Message;

public interface MessageMapper extends BaseMapper{
    int deleteByPrimaryKey(String id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
}