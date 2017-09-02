package com.shu.action.test;

import com.alibaba.fastjson.JSONObject;
import com.shu.db.model.test.Test;
import com.shu.services.test.TestService;
import com.shu.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by admin on 2017/1/8.
 */
@Controller
@RequestMapping(value = "/test")
public class TestAction {

    @Autowired
    TestService testService;

    @RequestMapping("/redirect")
    public String redirect() {
        return "redirect:http://www.baidu.com/";
    }

    @RequestMapping("/forward")
    public String dispatch() {
        return "forward:/test/redirect";
    }

    @RequestMapping(value = "test")
    public String test(Model model){
        Test test = new Test();
        List<Test> list1 = testService.getTestListByParam(test, null, null);
        List<String> list2 = new LinkedList<>();
        list2.add(0, "sdf");
        list2.add(1, "qwer");
        model.addAttribute("list1", list1);
        return "test/test";
    }

    @RequestMapping(value = "getage", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAge(Test test){
        JSONObject jsonObject = new JSONObject();
//        List<TestPublic> list1 = testService.getTestListByParam(test, null, null);
        if(StringUtils.isEmpty(test.getId())){
            jsonObject.put("status", "false");
            jsonObject.put("age", 0);
            return jsonObject.toString();
        }
        Test test1 = testService.getTestById(test.getId());
        int age = test1.getAge();
        jsonObject.put("status", Const.STATUS_SUCCESS);
        jsonObject.put("age", age);
        return jsonObject.toString();
    }
}
