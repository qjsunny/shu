package com.shu.services.manager;

import com.shu.db.model.Order;
import com.shu.db.model.Pager;
import com.shu.db.model.manager.Manager;

import java.util.List;

/**
 * Created by james on 2017/3/10.
 */
public interface ManagerService {
    public List<Manager> getManagerListByParam(Manager manager, Order order, Pager page);

    public void addManager(Manager manager);

    public void modifyManager(Manager manager);

    public Manager getManagerById(String id);

    public void removeManagerById(String id);
}
