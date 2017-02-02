package com.shu.services.follow.impl;

import com.shu.db.dao.follow.TFollowMapper;
import com.shu.db.model.Order;
import com.shu.db.model.Pager;
import com.shu.db.model.follow.TFollow;
import com.shu.services.follow.TFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by admin on 2017/2/2.
 */
@Service("followService")
@Transactional
public class TFollowServiceImpl implements TFollowService {

    @Autowired
    TFollowMapper tFollowMapper;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<TFollow> getFollowListByParam(TFollow follow, Order order, Pager page) {
        if (page != null) {
            int count = tFollowMapper.selectCountByParam(follow);
            page.setRecordCount(count);
        }
        return tFollowMapper.selectListByParam(follow, order, page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addFollow(TFollow follow) {
        tFollowMapper.insertSelective(follow);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyFollow(TFollow follow) {
        tFollowMapper.updateByPrimaryKeySelective(follow);
    }

    @Override
    @Transactional(readOnly = true)
    public TFollow getFollowById(String id) {
        return tFollowMapper.selectByPrimaryKey(id);
    }
}
