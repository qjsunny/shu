package com.shu.test.admin;

import com.shu.db.model.Order;
import com.shu.db.model.OrderBy;
import com.shu.db.model.OrderSort;
import com.shu.db.model.admin.TAdmin;
import com.shu.services.admin.TAdminService;
import com.shu.utils.UUID;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by james on 2017/2/9.
 */
public class TestAdmin {

    @Test
    public void testAdmin() {
        String[] paths = new String[] { "spring/applicationContext-bo.xml" };
        ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);

        TAdminService ts = (TAdminService) ctx.getBean("adminService");

//        TAdmin admin = new TAdmin();
//        admin.setId(UUID.getID());
//        admin.setName("sunny");
//        ts.addAdmin(admin);
//        TAdmin admin1 = ts.getAdminById("1");
//        System.out.println(admin1.getPassword());
//        admin.setName("sunny");
//        admin.setPassword("0");

        TAdmin admin = new TAdmin();
        Order order = new Order();
        order.addOrder("name", OrderSort.ASC);
        List<TAdmin> list1 = ts.getAdminListByParam(admin, order, null);
        for(TAdmin x : list1) {
            System.out.println("id:" + x.getId());
        }

//        test.setId(UUID.getID());
//        ts.addTest(test);
//        List<TAdmin> list1 = ts.getAdminListByParam(admin, null, null);

    }
}
