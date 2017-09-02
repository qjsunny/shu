package com.shu.action.login;

import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.shu.db.model.enterprise.Enterprise;
import com.shu.db.model.manager.Manager;
import com.shu.services.enterprise.EnterpriseService;
import com.shu.services.manager.ManagerService;
import com.shu.utils.Const;
import com.shu.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * Created by james on 2017/2/9.
 */
@Controller
@RequestMapping(value = "/login")
public class LoginAction {

    @Autowired
    ManagerService managerService;

    @Autowired
    EnterpriseService enterpriseService;

    @Autowired
    private Producer captchaProducer;

    /**
     * 登录页面跳转
     * @param model
     * @return
     */
    @RequestMapping(value = "/", produces = "text/html;charset=UTF-8")
    public String loginHtml(Model model) {
        return "login/login";
    }

    /**
     * 管理员登录
     * @param manager
     * @param session
     * @return
     */
    @RequestMapping(value = "/Manager", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String managerLogin(Manager manager, HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        manager.setPassword(MD5Util.MD5(MD5Util.MD5(manager.getPassword())));
        List<Manager> managerList = managerService.getManagerListByParam(manager, null, null);
        if (managerList.size() == 0) {
            jsonObject.put("status", Const.STATUS_FAIL);
            return jsonObject.toString();
        }
        session.setAttribute("manager", managerList.get(0));
        jsonObject.put("status", Const.STATUS_SUCCESS);
        return jsonObject.toString();
    }

    /**
     * 企业登录
     * @param enterprise
     * @param session
     * @return
     */
    @RequestMapping(value = "/Enterprise", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String enterpriseLogin(Enterprise enterprise, HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        enterprise.setPassword(MD5Util.MD5(MD5Util.MD5(enterprise.getPassword())));
        List<Enterprise> enterpriseList = enterpriseService.getEnterpriseListByParam(enterprise, null, null);
        if (enterpriseList.size() == 0) {
            jsonObject.put("status", Const.STATUS_FAIL);
            return jsonObject.toString();
        }
        session.setAttribute("enterprise", enterpriseList.get(0));
        jsonObject.put("status", Const.STATUS_SUCCESS);
        return jsonObject.toString();
    }

    /**
     * 用户登出
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout", produces = "text/html;charset=UTF-8")
    public String logout(Model model, HttpSession session) {
        if (session.getAttribute("manager") != null) {
            session.removeAttribute("manager");
        }
        if (session.getAttribute("enterprise") != null) {
            session.removeAttribute("enterprise");
        }
        return "redirect:/login/login";
    }

    /**
     * 获取图形验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/getCaptcha", produces = "text/html;charset=UTF-8")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0L);
        // return a jpeg
        response.setContentType("image/jpeg");
        String capText = captchaProducer.createText();
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpeg", out);
        out.flush();
        out.close();
    }

    /**
     * 验证图形验证码
     * @param verifyCode
     * @param request
     * @return
     */
    @RequestMapping(value = "/verifyCaptcha", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String verifyCaptcha(@RequestParam("verifyCode")String verifyCode, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String kaptchaExpected = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String kaptchaReceived = verifyCode;
        if (kaptchaReceived == null || !kaptchaReceived.equalsIgnoreCase(kaptchaExpected)) {
            jsonObject.put("status", Const.STATUS_FAIL);
            return jsonObject.toString();
        }
        jsonObject.put("status", Const.STATUS_SUCCESS);
        return jsonObject.toString();
    }
}
