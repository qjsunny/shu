package com.shu.services.follow;

import com.shu.db.model.Order;
import com.shu.db.model.Pager;
import com.shu.db.model.follow.TFollow;

import java.util.List;

/**
 * Created by admin on 2017/2/2.
 */
public interface TFollowService {
    public List<TFollow> getFollowListByParam(TFollow follow, Order order, Pager page);

    public void addFollow(TFollow follow);

    public void modifyFollow(TFollow follow);

    public TFollow getFollowById(String id);
}
