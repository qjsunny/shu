package com.shu.test;

import com.shu.services.test.TestService;
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

        TestService ts = (TestService) ctx.getBean("testService");

        com.shu.db.model.test.Test test = new com.shu.db.model.test.Test();
        test.setName("jin");
//        test.setId(UUID.getID());
//        ts.addTest(test);
        List<com.shu.db.model.test.Test> list1 = ts.getTestListByParam(test, null, null);
        for(com.shu.db.model.test.Test x : list1){
            System.out.println(x.getName());
        }
    }
}
