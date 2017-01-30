package com.shu.action.login;

import com.alibaba.fastjson.JSONObject;
import com.shu.db.model.user.TUser;
import com.shu.services.user.TUserService;
import com.shu.utils.Const;
import com.shu.utils.UUID;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by admin on 2017/1/30.
 */
@Controller
@RequestMapping(value = "login")
public class LoginAction {

    @Autowired
    TUserService tUserService;

    @RequestMapping(value = "loginhtml", produces = "text/html;charset=UTF-8")
    public String loginhtml(Model model){
        return "login/login";
    }

    @RequestMapping(value = "login", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String login(TUser user){
        JSONObject jsonObject = new JSONObject();
        List<TUser> list1 = tUserService.getUserListByParam(user, null, null);
        if(list1.size() == 0){
            jsonObject.put("status", Const.STATUS_FAIL);
            return jsonObject.toString();
        }
        jsonObject.put("status", Const.STATUS_SUCCESS);
        return jsonObject.toString();
    }

    @RequestMapping(value = "register", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String register(TUser user){
        JSONObject jsonObject = new JSONObject();
//        List<Test> list1 = testService.getTestListByParam(test, null, null);
        user.setId(UUID.getID());
        user.setIszhubo(0);
        user.setLevel(0);
        tUserService.addUser(user);
        jsonObject.put("status", Const.STATUS_SUCCESS);
        return jsonObject.toString();
    }
}
