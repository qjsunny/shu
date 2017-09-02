package com.shu.test.manager;

import com.shu.db.model.Order;
import com.shu.db.model.OrderSort;
import com.shu.db.model.manager.Manager;
import com.shu.services.manager.ManagerService;
import com.shu.utils.MD5Util;
import com.shu.utils.UUID;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by james on 2017/3/10.
 */
public class TestManager {
    @Test
    public void testManager() {
        String[] paths = new String[] { "spring/applicationContext-bo.xml" };
        ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);

        ManagerService ts = (ManagerService) ctx.getBean("managerService");

        Manager manager = new Manager();
        manager.setId(UUID.getID());
        manager.setUsername("Admin");
        manager.setPassword("000000");
        manager.setPassword(MD5Util.MD5(MD5Util.MD5(manager.getPassword())));
        manager.setLevel(3);
        manager.setCreateuser(manager.getId());
        manager.setIsdelete(0);
        manager.setUpdateUser(manager.getId());
        ts.addManager(manager);

//        Manager manager = new Manager();
//        List<Manager> list = ts.getManagerListByParam(manager, null, null);
//        for (Manager x : list) {
//            System.out.println("id:" + x.getId());
//            System.out.println("username:" + x.getUsername());
//        }

//        TAdmin admin = new TAdmin();
//        Order order = new Order();
//        order.addOrder("name", OrderSort.ASC);
//        List<TAdmin> list1 = ts.getAdminListByParam(admin, order, null);
//        for(TAdmin x : list1) {
//            System.out.println("id:" + x.getId());
//        }

//        test.setId(UUID.getID());
//        ts.addTest(test);
//        List<TAdmin> list1 = ts.getAdminListByParam(admin, null, null);

    }
}
