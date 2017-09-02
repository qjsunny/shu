package com.shu.services.enterprise.impl;

import com.shu.db.dao.enterprise.EnterpriseMapper;
import com.shu.db.model.Order;
import com.shu.db.model.Pager;
import com.shu.db.model.enterprise.Enterprise;
import com.shu.services.enterprise.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by james on 2017/4/8.
 */
@Service("enterpriseService")
@Transactional
public class EnterpriseServiceImpl implements EnterpriseService{

    @Autowired
    EnterpriseMapper enterpriseMapper;


    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<Enterprise> getEnterpriseListByParam(Enterprise enterprise, Order order, Pager page) {
        if (page != null) {
            int count = enterpriseMapper.selectCountByParam(enterprise);
            page.setRecordCount(count);
        }
        return enterpriseMapper.selectListByParam(enterprise, order, page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addEnterprise(Enterprise enterprise) {
        enterpriseMapper.insertSelective(enterprise);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyEnterprise(Enterprise enterprise) {
        enterpriseMapper.updateByPrimaryKeySelective(enterprise);
    }

    @Override
    @Transactional(readOnly = true)
    public Enterprise getEnterpriseById(String id) {
        return enterpriseMapper.selectByPrimaryKey(id);
    }
}
