package com.shu.db.dao.admin;

import com.shu.db.dao.BaseMapper;
import com.shu.db.model.admin.TAdmin;

/**
 * Created by james on 2017/2/9.
 */
public interface TAdminMapper extends BaseMapper{
    int deleteByPrimaryKey(String id);

    int insert(TAdmin record);

    int insertSelective(TAdmin record);

    TAdmin selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TAdmin record);

    int updateByPrimaryKey(TAdmin record);
}
