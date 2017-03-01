package com.shu.services.user;

import com.shu.db.model.Order;
import com.shu.db.model.Pager;
import com.shu.db.model.user.TUser;

import java.util.List;

/**
 * Created by admin on 2017/1/30.
 */
public interface TUserService {
    public List<TUser> getUserListByParam(TUser user, Order order, Pager page);

    public void addUser(TUser user);

    public void modifyUser(TUser user);

    public TUser getUserById(String id);
}
