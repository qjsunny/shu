package com.shu.db.dao.mail;

import com.shu.db.dao.BaseMapper;
import com.shu.db.model.mail.TMail;

/**
 * Created by admin on 2017/2/2.
 */
public interface TMailMapper extends BaseMapper {
    int deleteByPrimaryKey(String id);

    int insert(TMail record);

    int insertSelective(TMail record);

    TMail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TMail record);

    int updateByPrimaryKeyWithBLOBs(TMail record);

    int updateByPrimaryKey(TMail record);
}
