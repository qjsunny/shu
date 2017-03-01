package com.shu.test.user;

import com.shu.db.model.user.TUser;
import com.shu.services.user.TUserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by james on 2017/2/12.
 */
public class TestUser {

    @Test
    public void testUser() {
        String[] paths = new String[] { "spring/applicationContext-bo.xml" };
        ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);

        TUserService ts = (TUserService) ctx.getBean("userService");

        TUser user = new TUser();
        List<TUser> list1 = ts.getUserListByParam(user, null, null);
        for(TUser x : list1) {
            System.out.println(x.getUsername());
        }
    }
}
