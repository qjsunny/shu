package com.shu.services.admin;

import com.shu.db.model.Order;
import com.shu.db.model.Pager;
import com.shu.db.model.admin.TAdmin;

import java.util.List;

/**
 * Created by james on 2017/2/9.
 */
public interface TAdminService {
    public List<TAdmin> getAdminListByParam(TAdmin admin, Order order, Pager page);

    public void addAdmin(TAdmin admin);

    public void modifyAdmin(TAdmin admin);

    public TAdmin getAdminById(String id);
}
