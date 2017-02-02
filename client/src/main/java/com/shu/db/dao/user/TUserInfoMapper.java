package com.shu.db.dao.user;

import com.shu.db.dao.BaseMapper;
import com.shu.db.model.user.TUserInfo;

/**
 * Created by admin on 2017/2/2.
 */
public interface TUserInfoMapper extends BaseMapper {
    int deleteByPrimaryKey(String id);

    int insert(TUserInfo record);

    int insertSelective(TUserInfo record);

    TUserInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TUserInfo record);

    int updateByPrimaryKey(TUserInfo record);
}
