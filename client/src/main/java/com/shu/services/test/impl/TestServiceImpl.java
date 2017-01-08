package com.shu.services.test.impl;

import com.shu.db.dao.test.TestMapper;
import com.shu.db.model.Order;
import com.shu.db.model.Pager;
import com.shu.db.model.test.Test;
import com.shu.services.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * Created by admin on 2017/1/8.
 */
@Service("testService")
@Transactional
public class TestServiceImpl implements TestService {

    @Autowired
    TestMapper testMapper;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<Test> getTestListByParam(Test test, Order order, Pager page) {
        if (page != null) {
            int count = testMapper.selectCountByParam(test);
            page.setRecordCount(count);
        }
        return testMapper.selectListByParam(test, order, page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addTest(Test test) {
        testMapper.insertSelective(test);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyTest(Test test) {
        testMapper.updateByPrimaryKeySelective(test);
    }

    @Override
    @Transactional(readOnly = true)
    public Test getTestById(String id) {
        return testMapper.selectByPrimaryKey(id);
    }
}
