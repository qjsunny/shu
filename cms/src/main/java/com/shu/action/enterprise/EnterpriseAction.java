package com.shu.action.enterprise;

import com.alibaba.fastjson.JSONObject;
import com.shu.db.model.Order;
import com.shu.db.model.OrderSort;
import com.shu.db.model.enterprise.Enterprise;
import com.shu.services.enterprise.EnterpriseService;
import com.shu.utils.Const;
import com.shu.utils.MD5Util;
import com.shu.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by james on 2017/4/8.
 */
@Controller
@RequestMapping(value = "/Enterprise")
public class EnterpriseAction {

    @Autowired
    EnterpriseService enterpriseService;

    /**
     * 企业页面跳转
     * @return
     */
    @RequestMapping(value = "/", produces = "text/html;charset=UTF-8")
    public String index() {
        return "enterprise/index";
    }

    /**
     * 企业表显示
     * @return
     */
    @RequestMapping(value = "/show", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String show() {
        Enterprise enterprise = new Enterprise();
        enterprise.setIsdelete(0);
        Order order = new Order();
        order.addOrder("username", OrderSort.ASC);
        List<Enterprise> enterpriseList = enterpriseService.getEnterpriseListByParam(enterprise, order, null);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", enterpriseList);
        return jsonObject.toString();
    }

    /**
     * 需注册认证企业表显示
     * @return
     */
    @RequestMapping(value = "/authentication/certificate", produces = "text/html;charset=UTF-8")
    public String authenticationCertificate(Model model) {
        Enterprise enterprise = new Enterprise();
        enterprise.setIsdelete(1);
        Order order = new Order();
        order.addOrder("createTime", OrderSort.ASC);
        List<Enterprise> enterpriseList = enterpriseService.getEnterpriseListByParam(enterprise, order, null);
        model.addAttribute("enterpriseList", enterpriseList);
        return "/manager/certificate";
    }

    @RequestMapping(value = "/authentication/certificate/success", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String authenticationCertificate(String id) {
        Enterprise enterprise = enterpriseService.getEnterpriseById(id);
        enterprise.setIsdelete(2);
        enterpriseService.modifyEnterprise(enterprise);
        return new JSONObject().toString();
    }

    /**
     * 需身份认证企业表显示
     * @return
     */
    @RequestMapping(value = "/authentication/advertisement", produces = "text/html;charset=UTF-8")
    public String showCertificate(Model model) {
        Enterprise enterprise = new Enterprise();
        enterprise.setIsdelete(2);
        Order order = new Order();
        order.addOrder("createTime", OrderSort.ASC);
        List<Enterprise> enterpriseList = enterpriseService.getEnterpriseListByParam(enterprise, order, null);
        model.addAttribute("enterpriseList", enterpriseList);
        return "/manager/advertisement";
    }

    @RequestMapping(value = "/authentication/advertisement/success", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String authenticationAdvertisement(String id) {
        Enterprise enterprise = enterpriseService.getEnterpriseById(id);
        enterprise.setIsdelete(0);
        enterpriseService.modifyEnterprise(enterprise);
        return new JSONObject().toString();
    }

    /**
     * 单个企业信息显示
     * @param name
     * @return
     */
    @RequestMapping(value = "/show/{name}", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String showOne(@PathVariable("name") String name) throws UnsupportedEncodingException {
        JSONObject jsonObject = new JSONObject();
        name = URLDecoder.decode(name,"utf-8");
        Enterprise enterprise = new Enterprise();
        enterprise.setUsername(name);
        List<Enterprise> enterpriseList = enterpriseService.getEnterpriseListByParam(enterprise, null, null);
        jsonObject.put("enterprise", enterpriseList.get(0));
        return jsonObject.toString();
    }

    /**
     * 单个企业余额显示
     * @param name
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/money/{name}", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String moneyOne(@PathVariable("name") String name) throws UnsupportedEncodingException {
        JSONObject jsonObject = new JSONObject();
        name = URLDecoder.decode(name,"utf-8");
        Enterprise enterprise = new Enterprise();
        enterprise.setUsername(name);
        List<Enterprise> enterpriseList = enterpriseService.getEnterpriseListByParam(enterprise, null, null);
        jsonObject.put("money", enterpriseList.get(0).getMoney());
        return jsonObject.toString();
    }

    @RequestMapping(value = "/money/{name}/add", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String moneyOneAdd(@PathVariable("name") String name,
                           @RequestParam(value = "money")int money) throws UnsupportedEncodingException {
        name = URLDecoder.decode(name,"utf-8");
        Enterprise enterprise = new Enterprise();
        enterprise.setUsername(name);
        List<Enterprise> enterpriseList = enterpriseService.getEnterpriseListByParam(enterprise, null, null);
        enterprise = enterpriseList.get(0);
        enterprise.setMoney(enterprise.getMoney() + money);
        enterpriseService.modifyEnterprise(enterprise);
        return new JSONObject().toString();
    }

    /**
     * 企业注册页面跳转
     * @return
     */
    @RequestMapping(value = "/addIndex", produces = "text/html;charset=UTF-8")
    public String addIndex() {
        return "enterprise/add";
    }

    /**
     * 企业注册
     * @param enterprise
     * @return
     */
    @RequestMapping(value = "/add", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String add(Enterprise enterprise) {
        JSONObject jsonObject = new JSONObject();
        Enterprise enterprise1 = new Enterprise();
        enterprise1.setUsername(enterprise.getUsername());
        List<Enterprise> enterpriseList = enterpriseService.getEnterpriseListByParam(enterprise1, null, null);
        if (!enterpriseList.isEmpty()) {
            jsonObject.put("status", Const.STATUS_FAIL);
            return jsonObject.toString();
        }
        enterprise.setId(UUID.getID());
        enterprise.setPassword(MD5Util.MD5(MD5Util.MD5(enterprise.getPassword())));
        enterprise.setCertificate("");
        enterprise.setMoney(0);
        enterprise.setAdvertisement("");
        enterprise.setUpdateUser(enterprise.getUsername());
        enterprise.setIsdelete(0);
        enterpriseService.addEnterprise(enterprise);
        jsonObject.put("status", Const.STATUS_SUCCESS);
        return jsonObject.toString();
    }
}
