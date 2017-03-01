package com.shu.services.admin.impl;

import com.shu.db.dao.admin.TAdminMapper;
import com.shu.db.model.Order;
import com.shu.db.model.Pager;
import com.shu.db.model.admin.TAdmin;
import com.shu.services.admin.TAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by james on 2017/2/9.
 */
@Service("adminService")
@Transactional
public class TAdminServiceImpl implements TAdminService {
    @Autowired
    TAdminMapper tAdminMapper;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<TAdmin> getAdminListByParam(TAdmin admin, Order order, Pager page) {
        if (page != null) {
            int count = tAdminMapper.selectCountByParam(admin);
            page.setRecordCount(count);
        }
        return tAdminMapper.selectListByParam(admin, order, page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addAdmin(TAdmin admin) {
        tAdminMapper.insertSelective(admin);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyAdmin(TAdmin admin) {
        tAdminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    @Transactional(readOnly = true)
    public TAdmin getAdminById(String id) {
        return tAdminMapper.selectByPrimaryKey(id);
    }
}
