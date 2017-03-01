package com.shu.action.login;

import com.alibaba.fastjson.JSONObject;
import com.shu.db.model.admin.TAdmin;
import com.shu.services.admin.TAdminService;
import com.shu.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Created by james on 2017/2/9.
 */
@Controller
@RequestMapping(value = "login")
public class LoginAction {

    @Autowired
    TAdminService tAdminService;

    @RequestMapping(value = "login.html", produces = "text/html;charset=UTF-8")
    public String loginHtml(Model model) {
        return "login/login";
    }

//    @RequestMapping(value = "login1.html", produces = "text/html;charset=UTF-8")
//    public String login1Html(Model model) {
//        return "login/login1";
//    }

    @RequestMapping(value = "login", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String login(TAdmin admin, HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        List<TAdmin> list1 = tAdminService.getAdminListByParam(admin, null, null);
        if (list1.size() == 0) {
            jsonObject.put("status", Const.STATUS_FAIL);
            return jsonObject.toString();
        }
        String name = admin.getName();
        session.setAttribute("name", name);
        jsonObject.put("status", Const.STATUS_SUCCESS);
        return jsonObject.toString();
    }

//    @RequestMapping(value = "login1", produces = "text/html;charset=UTF-8")
//    public String login1(HttpSession session, String name, String password,
//                         HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        TAdmin admin = new TAdmin();
//        admin.setName(name);
//        admin.setPassword(password);
//        List<TAdmin> list1 = tAdminService.getAdminListByParam(admin, null, null);
//        if (list1.size() == 0) {
//            String message = "用户名或密码错误！";
//            request.setAttribute("testMessage", message);
//            System.out.println("用户名或密码错误！！");
//            return "login/login1";
//        }
//        System.out.println(admin.getId());
//        session.setAttribute("name", name);
//        System.out.println("登陆成功！！");
//        return "redirect:/index/";
//    }

    @RequestMapping(value = "logout", produces = "text/html;charset=UTF-8")
    public String logout(Model model, HttpSession session) {
        Object object = session.getAttribute("name");
        if (object != null) {
            session.removeAttribute("name");
        }
        return "login/login";
    }
}
