package com.shu.services.manager.impl;

import com.shu.db.dao.manager.ManagerMapper;
import com.shu.db.model.Order;
import com.shu.db.model.Pager;
import com.shu.db.model.manager.Manager;
import com.shu.services.manager.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by james on 2017/3/10.
 */
@Service("managerService")
@Transactional
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    ManagerMapper managerMapper;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<Manager> getManagerListByParam(Manager manager, Order order, Pager page) {
        if (page != null) {
            int count = managerMapper.selectCountByParam(manager);
            page.setRecordCount(count);
        }
        return managerMapper.selectListByParam(manager, order, page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addManager(Manager manager) {
        managerMapper.insertSelective(manager);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyManager(Manager manager) {
        managerMapper.updateByPrimaryKeySelective(manager);
    }

    @Override
    @Transactional(readOnly = true)
    public Manager getManagerById(String id) {
        return managerMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeManagerById(String id) {
        managerMapper.deleteByPrimaryKey(id);
    }
}
