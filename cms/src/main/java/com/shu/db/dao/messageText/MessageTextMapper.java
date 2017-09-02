package com.shu.db.dao.messageText;

import com.shu.db.dao.BaseMapper;
import com.shu.db.model.messageText.MessageText;

public interface MessageTextMapper extends BaseMapper {
    int deleteByPrimaryKey(String id);

    int insert(MessageText record);

    int insertSelective(MessageText record);

    MessageText selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MessageText record);

    int updateByPrimaryKey(MessageText record);
}