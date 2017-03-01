package com.shu.services.user.impl;

import com.shu.db.dao.user.TUserMapper;
import com.shu.db.model.Order;
import com.shu.db.model.Pager;
import com.shu.db.model.user.TUser;
import com.shu.services.user.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by admin on 2017/1/30.
 */
@Service("userService")
@Transactional
public class TUserServiceImpl implements TUserService {
    @Autowired
    TUserMapper tUserMapper;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<TUser> getUserListByParam(TUser user, Order order, Pager page) {
        if (page != null) {
            int count = tUserMapper.selectCountByParam(user);
            page.setRecordCount(count);
        }
        return tUserMapper.selectListByParam(user, order, page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser(TUser user) {
        tUserMapper.insertSelective(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyUser(TUser user) {
        tUserMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    @Transactional(readOnly = true)
    public TUser getUserById(String id) {
        return tUserMapper.selectByPrimaryKey(id);
    }
}
