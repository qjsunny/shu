package com.shu.test;

import com.shu.db.model.user.TUser;
import com.shu.db.model.user.TUserInfo;
import com.shu.services.test.TestService;
import com.shu.services.user.TUserInfoService;
import com.shu.services.user.TUserService;
import com.shu.utils.UUID;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by admin on 2017/1/8.
 */
public class JunitTest {

    /**
     * This is just a test
     */
    @Test
    public void testTest() {
        String[] paths = new String[] { "spring/applicationContext-bo.xml" };
        ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);

        TUserInfoService ts = (TUserInfoService) ctx.getBean("userinfoService");
        TUserInfo userInfo = ts.getUserinfoById("8a37c3c8cb3f4c979d2cb85832a1f9d2");

        System.out.println(userInfo.getId());

//        TUser user = new TUser();
//        user.setUsername("jinyu");
//        TUser user2 = ts.getUserById("1");
//        System.out.println(user2.getPassword());

//        test.setId(UUID.getID());
//        ts.addTest(test);


//        List<TUser> list1 = ts.getUserListByParam(user, null, null);
//        for(TUser x : list1){
//            System.out.println(x.getPassword());
//        }
    }
}
