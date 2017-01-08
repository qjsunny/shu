package com.shu.services.test;

import com.shu.db.model.Order;
import com.shu.db.model.Pager;
import com.shu.db.model.test.Test;

import java.util.List;
/**
 * Created by admin on 2017/1/8.
 */
public interface TestService {
    public List<Test> getTestListByParam(Test test, Order order, Pager page);

    public void addTest(Test test);

    public void modifyTest(Test test);

    public Test getTestById(String id);
}
