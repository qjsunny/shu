package com.shu.test.enterprise;

import com.shu.db.model.enterprise.Enterprise;
import com.shu.services.enterprise.EnterpriseService;
import com.shu.utils.MD5Util;
import com.shu.utils.UUID;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by james on 2017/4/8.
 */
public class TestEnterprise {

    String[] paths = new String[] { "spring/applicationContext-bo.xml" };
    ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);

    EnterpriseService es = (EnterpriseService) ctx.getBean("enterpriseService");

    @Test
    public void test1() {
        Enterprise enterprise = new Enterprise();
        enterprise.setId(UUID.getID());
        enterprise.setUsername("企业1");
        enterprise.setPassword("1");
        enterprise.setPassword(MD5Util.MD5(MD5Util.MD5(enterprise.getPassword())));
        enterprise.setEmail("496481930@qq.com");
        enterprise.setPhonenumber("13095975175");
        enterprise.setAddress("上大路98号");
        enterprise.setCertificate("证书链接");
        enterprise.setMoney(101);
        enterprise.setAdvertisement("广告链接");
        es.addEnterprise(enterprise);
    }
}
