package com.shu.services.enterprise;

import com.shu.db.model.Order;
import com.shu.db.model.Pager;
import com.shu.db.model.enterprise.Enterprise;

import java.util.List;

/**
 * Created by james on 2017/4/8.
 */
public interface EnterpriseService {
    public List<Enterprise> getEnterpriseListByParam(Enterprise enterprise, Order order, Pager page);

    public void addEnterprise(Enterprise enterprise);

    public void modifyEnterprise(Enterprise enterprise);

    public Enterprise getEnterpriseById(String id);
}
