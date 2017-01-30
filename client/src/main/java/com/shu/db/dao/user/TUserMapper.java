package com.shu.db.dao.user;

import com.shu.db.dao.BaseMapper;
import com.shu.db.model.user.TUser;

/**
 * Created by admin on 2017/1/30.
 */
public interface TUserMapper extends BaseMapper {
    int deleteByPrimaryKey(String id);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);
}
